package test.backTests.userManager;

import org.example.qaTransactionTeam.BaseTest;
import org.example.qaTransactionTeam.backEnd.utils.RabbitMQ_http;
import org.testng.annotations.Test;

@Test
public class UserManager extends BaseTest {

    public void find(){
        RabbitMQ_http rabbitMQHttp = new RabbitMQ_http("Find","UserManager:input");
        rabbitMQHttp.sendHttp("{" +
                        "    \"ekb_id\": \"7115468\"," +
                        "    \"fields\": {" +
                        "         \"general\" : true," +
                        "         \"extra\" : true," +
                        "         \"documents\" : true," +
                        "         \"addresses\" : true" +
                        "     }" +
                        "}");
    }
}
