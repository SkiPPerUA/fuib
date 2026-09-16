package test.backTests.transactionsBlocker;

import org.example.qaTransactionTeam.BaseTest;
import org.example.qaTransactionTeam.backEnd.utils.Card;
import org.example.qaTransactionTeam.backEnd.utils.Card_param;
import org.example.qaTransactionTeam.backEnd.utils.Cards_data;
import org.example.qaTransactionTeam.backEnd.utils.RabbitMQ_http;
import org.testng.annotations.Test;

@Test
public class Amq_TransBlocker_test extends BaseTest {

    public void positive_checkCardBlock(){
        RabbitMQ_http rabbitMQHttp = new RabbitMQ_http("checkCardBlock","TransactionsBlocker.input");
        rabbitMQHttp.sendHttp("{" +
                " \"card\":\"4761739999000213\"," +
                " \"validation_required\":true " +
                "}");
    }
}
