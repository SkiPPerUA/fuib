package org.example.qaTransactionTeam.backEnd.userManager;

import org.apache.log4j.Logger;
import org.example.qaTransactionTeam.backEnd.utils.RabbitMQ;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeoutException;


public class UserManager {

    private static final Logger logger = Logger.getLogger(UserManager.class);
    private RabbitMQ rabbit;

    public void findUserInfo() throws IOException, InterruptedException, NoSuchAlgorithmException, URISyntaxException, KeyManagementException, TimeoutException {
        rabbit = new RabbitMQ("tsys");

        String mess = "{\n" +
                "    \"ekb_id\": \"6162681\",\n" +
                "    \"fields\": {\n" +
                "         \"general\" : true,\n" +
                "         \"extra\" : true,\n" +
                "         \"documents\" : true,\n" +
                "         \"addresses\" : true\n" +
                "     }\n" +
                "}";

        Map<String, Object> heders = new HashMap<>();
        heders.put("handler", "Find");
        rabbit.getChannel().queueDeclare("UserManager:input", true, false, false, null);
        rabbit.getChannel().queuePurge("savchukvTest");
        //rabbit.getChannel().exchangeDeclare("tsys_a2c", "direct", true);

        rabbit.getChannel().basicPublish("", "UserManager:input",
                rabbit.getProperties().headers(heders).replyTo("savchukvTest").build(),
                mess.getBytes());


        JSONObject ob = new JSONObject(listener());
        System.out.println(ob);
    }

    private String listener() throws IOException, InterruptedException {
        final BlockingQueue<String> response = new ArrayBlockingQueue<>(1);

        String ctag = rabbit.getChannel().basicConsume("savchukvTest", true, (consumerTag, delivery) -> {
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
