package org.example.qaTransactionTeam.backEnd.payoneer;

import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.example.qaTransactionTeam.backEnd.helper.Uuid_helper;
import org.example.qaTransactionTeam.backEnd.token.Auth_token;
import org.example.qaTransactionTeam.backEnd.token.Trans_token_payhub;
import org.example.qaTransactionTeam.backEnd.utils.RabbitMQ_http;
import org.example.qaTransactionTeam.backEnd.utils.Restful;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.given;

public class Payoneer extends Restful {

    private static final Logger logger = Logger.getLogger(Payoneer.class);
    private final Auth_token token = new Trans_token_payhub(544881);
    private final Map<String,String> headers = new HashMap<>();
    private String transaction_id;

    public Payoneer(){
        headers.put("x-flow-id", Uuid_helper.generate_uuid());
        headers.put("Authorization", "Bearer "+token.getToken());
        headers.put("x-systemcode", Uuid_helper.generate_uuid());
    }

    public void registrationLinks(String body){
        logger.info("Registration links");
        request(given()
                .headers(headers)
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post(token.getHost()+"/luna/payoneer-transfers/registration-links"));
    }

    public void getBalance(String body){
        logger.info("Get balance");
        request(given()
                .headers(headers)
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post(token.getHost()+"/luna/payoneer-transfers/balances-and-accounts"));
    }

    public void makeTransaction(String body){
        logger.info("Make transaction");
        request(given()
                .headers(headers)
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post(token.getHost()+"/luna/payoneer-transfers/payments"));
        if (response.statusCode() == 200){
            transaction_id = response.then().extract().response().jsonPath().getString("id");
        }
    }

    public void statusTransaction(String ekbID){
        statusTransaction(transaction_id,ekbID);
    }

    public void statusTransaction(String transaction_id, String ekbID){
        logger.info("Status transaction");
        request(given()
                .headers(headers)
                .contentType(ContentType.JSON)
                .when()
                .get(token.getHost()+"/luna/payoneer-transfers/payments/"+transaction_id+"?ekb_id="+ekbID));
    }

    public void accountDetails(String ekbID){
        logger.info("Account details");
        request(given()
                .headers(headers)
                .contentType(ContentType.JSON)
                .when()
                .get(token.getHost()+"/luna/payoneer-transfers/accounts/details?ekb_id="+ekbID));
    }

    public void getProgramBalance(){
        RabbitMQ_http rabbitMQHttp = new RabbitMQ_http("","");
        rabbitMQHttp.setHost("https://ph-rabbitmq.test-fuib.com");
        rabbitMQHttp.sendHttpWithConfig("{\n" +
                "   \"vhost\":\"test\",\n" +
                "   \"name\":\"amq.default\",\n" +
                "   \"properties\":{\n" +
                "      \"delivery_mode\":1,\n" +
                "      \"headers\":{\n" +
                "         \"handler\":\"getProgramBalance\",\n" +
                "         \"contentType\":\"application/json\"\n" +
                "      },\n" +
                "      \"reply_to\":\"POSTMAN_RESPONSE:"+ rabbitMQHttp.getUuid()+"\"\n" +
                "   },\n" +
                "   \"routing_key\":\"payoneer.input\",\n" +
                "   \"delivery_mode\":\"1\",\n" +
                "   \"payload\":\"{}\",\n" +
                "   \"payload_encoding\":\"string\",\n" +
                "   \"headers\":{\n" +
                "      \"handler\":\"getProgramBalance\",\n" +
                "      \"contentType\":\"application/json\"\n" +
                "   }\n" +
                "}");
    }
}
