package test.backTests.socketQueue;
//C2A транзакции через Rabbit (socketQueue)

import org.example.qaTransactionTeam.BaseTest;
import org.example.qaTransactionTeam.backEnd.utils.RabbitMQ;
import org.junit.Test;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

public class AddA2CToRabb extends BaseTest {
    //L6UpsHDcX5LRZtwMsxSaFA.iTnC77s6lQOvIIUfUC8NTQ - visa
    //wYF6OUk1IQGOyPMjP6Ck6A.TATAV1oGQriRFNAc8MOdrw - mc
    String corelation;


    String bodyTrans = "{\"0\":\"0200\"," +
            "\"2\":\"L6UpsHDcX5LRZtwMsxSaFA.iTnC77s6lQOvIIUfUC8NTQ\"," +
            "\"3\":\"260000\"," +
            "\"4\":109," +
            "\"11\":654321," +
            "\"18\":\"6012\"," +
            "\"22\":\"010\"," +
            "\"25\":\"59\","+
            "\"32\":\"676271\"," +
            "\"37\":\"013000000668\"," +
            "\"41\":\"KIE00001\"," +
            "\"42\":\"0200100FMT00022\"," +
            "\"43\":\"IBOX CASH TO CARD      KYIV           UA\"," +
            "\"48\":\"    044207\"," +
            "\"49\":980," +
            "\"61\":\"00051000060008040000000000\"}";

   // String bodyTrans = "{\"0\":\"0200\",\"2\":\"wYF6OUk1IQGOyPMjP6Ck6A.TATAV1oGQriRFNAc8MOdrw\",\"3\":\"260000\",\"4\":110,\"11\":654326,\"18\":\"6012\",\"22\":\"812\",\"32\":\"676271\",\"37\":\"013000000123\",\"41\":\"KIE00001\",\"42\":\"0200100FMT00022\",\"43\":\"#test3112\",\"48\":\"    0542210\",\"49\":980,\"61\":\"00051000060008040000000000\"}";


    @Test
    public void makeTrans() throws IOException, URISyntaxException, TimeoutException, NoSuchAlgorithmException, KeyManagementException {
        corelation = UUID.randomUUID().toString();
        logger.info("Corelation = "+corelation);
        RabbitMQ addMess = new RabbitMQ("tsys");

        addMess.getChannel().exchangeDeclare("network_messages_request", "direct", true);
        addMess.getChannel().queueBind("network_requests_queue", "network_messages_request", "");

        Map<String, Object> headers = new HashMap<String, Object>();
        headers.put("initiator_response_queue", "fdds3");

        addMess.getProperties().contentType("application/json");
        addMess.getProperties().appId("appTestA2C");
        addMess.getProperties().correlationId(corelation);


        addMess.getChannel().basicPublish("network_messages_request", "", addMess.getProperties().headers(headers).build(), bodyTrans.getBytes());
        addMess.closeConn();
        System.out.println("Ответ по транзакции -> https://rmq01.dev-fuib.com/#/queues/tsys/network_responses_queue_appTestA2C" );
    }

}