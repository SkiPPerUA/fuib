package org.example.qaTransactionTeam.backEnd.scarecrow;

import org.apache.log4j.Logger;
import org.example.qaTransactionTeam.backEnd.utils.RabbitMQ;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeoutException;

public class Send_tds extends ScarecrowRabbit{

    private Logger logger = Logger.getLogger(Send_tds.class);
    private String mess;


    public void sendToRabbit(String token, String value) throws URISyntaxException, KeyManagementException, TimeoutException, NoSuchAlgorithmException, IOException {
        if(rabbit == null){
            rabbit = new RabbitMQ("tsys");
        }
        mess = "{\n" +
                "               \t\"session_id\":\"999999999999\",\n" +
                "               \t\"token\":\""+token+"\",\n" +
                "                \"portal_id\":\"9564166CB48FC7898F54E5149BEFC940\",\n" +
                "               \t\"three_dsmethod_data\": \""+value+"\",\n" +
                "                 \"step\": \"TDS\"\n" +
                "               }\n";

        rabbit.getChannel().queueDeclare("send_tds", true, false, false, null);
        rabbit.getChannel().exchangeDeclare("tsys_3ds", "direct", true);

        rabbit.getChannel().basicPublish("tsys_3ds", "send_tds",
                rabbit.getProperties().correlationId("vlad2").replyTo("qasdf1").contentType("application/json").build(),
                mess.getBytes());
        logger.info("Отправлено сообщение в Rabbit -> " + mess);
        System.out.println("Результат -> https://rmq01.dev-fuib.com/#/queues/tsys/qasdf1");

    }


}
