package test.backTests.socketQueue;
//Поиск криптограммы по номеру карты

import org.example.qaTransactionTeam.BaseTest;
import org.example.qaTransactionTeam.backEnd.utils.*;
import org.testng.annotations.Test;


import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeoutException;

public class FindCryptogram extends BaseTest {

    String card = Cards_data.getData(Card.FUIB_MC, Card_param.pan);

    RabbitMQ consumerMess;

    @Test
    public void cardCryptogram() throws IOException, URISyntaxException, TimeoutException, NoSuchAlgorithmException, KeyManagementException, InterruptedException {

        String st = "{\"operation\":\"get_cryptogram\",\"pan\":\""+card+"\"}";

        consumerMess = new RabbitMQ("tsys");
        consumerMess.getChannel().queueDeclare("consumerJSQT", true,false,false,null);
        consumerMess.getChannel().queueDeclare("responseQ", true,false,false,null);

        consumerMess.getChannel().queuePurge("responseQ");

        consumerMess.getProperties().contentType("application/json");
        consumerMess.getProperties().replyTo("responseQ");
        consumerMess.getProperties().correlationId("vlad2");
        consumerMess.getChannel().basicPublish("", "consumerJSQT", consumerMess.getProperties().build(), st.getBytes());

        System.out.println(readResponseQ());

        consumerMess.closeConn();
    }

    private String readResponseQ() throws InterruptedException, IOException {
        final BlockingQueue<String> response = new ArrayBlockingQueue<>(1);

        String ctag = consumerMess.getChannel().basicConsume("responseQ", true, (consumerTag, delivery) -> {
            if (delivery.getProperties().getCorrelationId().equals("vlad2")) {
                response.offer(new String(delivery.getBody(), "UTF-8"));
            }
        }, consumerTag -> {
        });

        String result = response.take();
        consumerMess.getChannel().basicCancel(ctag);
        return result;

    }

}
