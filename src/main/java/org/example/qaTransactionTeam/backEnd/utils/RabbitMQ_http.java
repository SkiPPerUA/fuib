package org.example.qaTransactionTeam.backEnd.utils;

import io.restassured.RestAssured;
import org.apache.log4j.Logger;
import org.example.qaTransactionTeam.backEnd.helper.Uuid_helper;
import org.testng.Assert;

import static io.restassured.RestAssured.given;

public class RabbitMQ_http extends Restful{

    public static final Logger logger = Logger.getLogger(RabbitMQ_http.class);
    private String auth = "YWRtaW46YWRtaW4=";
    private String uuid = Uuid_helper.generate_uuid();
    private String handler;
    private String routing_key;
    private String host = "https://ph-rabbit-admin.test-fuib.com"; //test http://10.56.36.75:15672

    public RabbitMQ_http(String handler, String routing_key){
        RestAssured.useRelaxedHTTPSValidation();
        this.handler = handler;
        this.routing_key = routing_key;
    }

    public void sendHttp(String body){
        addQueue_toRabbit();
        String body1 = "{\n" +
                "   \"properties\":{\n" +
                "      \"delivery_mode\":1,\n" +
                "      \"reply_to\":\"POSTMAN_RESPONSE:"+uuid+"\",\n" +
                "      \"headers\":{\n" +
                "         \"handler\":\""+handler+"\",\n" +
                "         \"contentType\":\"application/json\",\n" +
                "         \"uber-trace-id\":\""+uuid+":"+uuid+":0:1\"\n" +
                "      }\n" +
                "   },\n" +
                "   \"routing_key\":\""+routing_key+"\",\n" +
                "   \"payload\":\""+body.replaceAll("\"","\\\\\"")+"\",\n" +
                "   \"payload_encoding\":\"string\"\n" +
                "}";
        request(given()
                .header("Authorization", "Basic "+auth)
                .body(body1)
                .post(host+"/api/exchanges/test/amq.default/publish"));
        Assert.assertEquals(getResponse(),"{\"routed\":true}");
    }

    private void addQueue_toRabbit(){
        request_off_checkResponseCode(given()
                .header("Authorization", "Basic "+auth)
                .body("{\"durable\":true}")
                .put(host+"/api/queues/test/POSTMAN_RESPONSE:"+ uuid));
    }

}
