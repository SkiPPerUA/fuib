package org.example.qaTransactionTeam.backEnd.ronan;

import org.apache.log4j.Logger;
import org.example.qaTransactionTeam.backEnd.utils.Configs1;
import org.example.qaTransactionTeam.backEnd.utils.RabbitMQ;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeoutException;

import static io.restassured.RestAssured.given;


public class Ronan {

    private Logger logger = Logger.getLogger(Ronan.class);
    public RabbitMQ rabbit;
    private String token;
    private String sessionId;
    protected String localURL;
    public String reference;
    public String acqId = "2101";

    public void token() throws URISyntaxException, KeyManagementException, TimeoutException, NoSuchAlgorithmException, IOException, InterruptedException, JSONException {
        rabbit = new RabbitMQ("tsys");

        String mess = "{\n" +
                "  \"login\" : \""+ Configs1.RONAN_LOGIN_2101+"\",\n" +
                "  \"password\" : \""+ Configs1.RONAN_PASSWORD_2101+"\",\n" +
                "  \"acq_id\" : \""+acqId+"\"\n" +
                "}";

        rabbit.getChannel().queueDeclare("a2c_token", true, false, false, null);
        rabbit.getChannel().queuePurge("A2CRonan");
        rabbit.getChannel().exchangeDeclare("tsys_a2c", "direct", true);

        rabbit.getChannel().basicPublish("tsys_a2c", "a2c_token",
                rabbit.getProperties().correlationId("vlad2").replyTo("A2CRonan").build(),
                mess.getBytes());


        JSONObject ob = new JSONObject(listenerA2CRonan());
        token = ob.getString("token");
    }

    protected void register(String pan) throws IOException, InterruptedException, JSONException {
        String mess = "{\n" +
                "  \"acquirer_id\" : "+acqId+",\n" +
                "  \"receiver_card_number\" : \""+pan+"\",\n" +
                "  \"amount\" : 100,\n" +
                "  \"currency\" : \"UAH\",\n" +
                "  \"custom_fee\" : 10,\n" +
                "  \"operation_id\" : 1,\n" +
                "  \"ip\" : \"10.233.107.90\",\n" +
                "  \"token\" : \""+token+"\"\n" +
                "}";

        rabbit.getChannel().basicPublish("tsys_a2c", "a2c_payment_reg",
                rabbit.getProperties().correlationId("vlad2").replyTo("A2CRonan").build(),
                mess.getBytes());

        JSONObject ob = new JSONObject(listenerA2CRonan());
        sessionId = ob.getString("session_id");

    }

    protected void process() throws IOException, InterruptedException, JSONException {
        String mess = "{\n" +
                "  \"session_id\" : \""+sessionId+"\",\n" +
                "  \"acquirer_id\" : "+acqId+"\n" +
                "}";

        rabbit.getChannel().basicPublish("tsys_a2c", "a2c_payment",
                rabbit.getProperties().correlationId("vlad2").replyTo("A2CRonan").build(),
                mess.getBytes());

        JSONObject ob = new JSONObject(listenerA2CRonan());
        reference = ob.getString("payment_number");
        logger.info("Результат транзакции - "+ob);

        rabbit.closeConn();
    }

    private String listenerA2CRonan () throws IOException, InterruptedException {
        final BlockingQueue<String> response = new ArrayBlockingQueue<>(1);

        String ctag = rabbit.getChannel().basicConsume("A2CRonan", true, (consumerTag, delivery) -> {
            if (delivery.getProperties().getCorrelationId().equals("vlad2")) {
                response.offer(new String(delivery.getBody(), "UTF-8"));
            }
        }, consumerTag -> {
        });

        String result = response.take();
        rabbit.getChannel().basicCancel(ctag);
        return result;
    }

    protected boolean checkCountryCard(String pan){
        String res = given()
                .when()
                .get(localURL+"/bin_range/"+pan)
                .then()
                .statusCode(200)
                .extract().response().asString();

        logger.info("Проверка карты по 'Выпуску в Украине' - "+res);
        JSONObject jsonObject = new JSONObject(res);
        return jsonObject.getBoolean("domestic_card");
    }

    public String getToken() {
        return token;
    }
}
