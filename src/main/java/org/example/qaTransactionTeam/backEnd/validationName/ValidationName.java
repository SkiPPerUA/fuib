package org.example.qaTransactionTeam.backEnd.validationName;

import org.apache.log4j.Logger;
import org.example.qaTransactionTeam.backEnd.utils.Configs;
import org.example.qaTransactionTeam.backEnd.utils.RabbitMQ;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ValidationName {

    private static final Logger logger = Logger.getLogger(ValidationName.class);
    private RabbitMQ rabbit;

    public void validName(String body){
        try {
            rabbit = new RabbitMQ(Configs.ACCESS_TO_RABBIT_phd_rb_003,"test");
            Map header = new HashMap();
            header.put("handler","validateCashToCardIdentification");

            rabbit.getChannel().queueDeclare("ValidName", true, false, false, null);
            rabbit.getChannel().queuePurge("ValidName");
            rabbit.getChannel().basicPublish("", "transacter.input",
                    rabbit.getProperties().correlationId("vlad2").headers(header).replyTo("ValidName").build(),
                    body.getBytes());

            JSONObject ob = new JSONObject(listenerA2CRonan());
            System.out.println(ob);
        }catch (Throwable e){
            logger.error(e);
        }finally {
            try {
                rabbit.closeConn();
            } catch (IOException e) {
                logger.error(e);
            }
        }
    }

    private String listenerA2CRonan () throws IOException, InterruptedException {
        final BlockingQueue<String> response = new ArrayBlockingQueue<>(1);

        String ctag = rabbit.getChannel().basicConsume("ValidName", true, (consumerTag, delivery) -> {
            if (delivery.getProperties().getCorrelationId().equals("vlad2")) {
                response.offer(new String(delivery.getBody(), "UTF-8"));
            }
        }, consumerTag -> {
        });

        String result = response.take();
        rabbit.getChannel().basicCancel(ctag);
        return result;
    }
}
