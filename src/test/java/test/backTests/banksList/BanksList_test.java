package test.backTests.banksList;

import org.example.qaTransactionTeam.BaseTest;
import org.example.qaTransactionTeam.backEnd.utils.RabbitMQ_http;
import org.testng.annotations.Test;

@Test
public class BanksList_test extends BaseTest {

    public void list() {
        RabbitMQ_http rabbitMQHttp = new RabbitMQ_http("ListBanks","BankService:input");
        rabbitMQHttp.sendHttp("{" +
                "\"limit\":100," +
                "\"offset\":0," +
                "\"is_request_to_pay\":true" +
                "}");
    }
}
