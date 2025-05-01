package test.backTests.accounts;

import org.example.qaTransactionTeam.BaseTest;
import org.example.qaTransactionTeam.backEnd.utils.RabbitMQ_http;
import org.testng.annotations.Test;

@Test
public class Accounts extends BaseTest {

    public void convertAccount(){
        RabbitMQ_http rabbitMQHttp = new RabbitMQ_http("convertAccount","Accounts:Input");
        rabbitMQHttp.sendHttp("{" +
                "from_type:\"CARD_ID\"," +
                "to_type:\"IBAN\"," +
                "value:\"024079377033\"" +
                "}");
    }
}
