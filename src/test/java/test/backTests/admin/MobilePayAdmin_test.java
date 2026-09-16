package test.backTests.admin;

import org.example.qaTransactionTeam.BaseTest;
import org.example.qaTransactionTeam.backEnd.utils.RabbitMQ_http;
import org.testng.annotations.Test;

@Test
public class MobilePayAdmin_test extends BaseTest {

    RabbitMQ_http rabbitMQHttp = new RabbitMQ_http("GetStatusBatch","MobilePayAdmin:input");

    public void positive_(){
        rabbitMQHttp.sendHttp("{" +
                "   \"sessions\":[" +
                "      \"035588042243\"," +
                "      \"035588037848\"" +
                "   ]" +
                "}");
    }

}
