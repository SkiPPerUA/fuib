package test.backTests.temporaryRegistries;

import org.example.qaTransactionTeam.BaseTest;
import org.example.qaTransactionTeam.backEnd.utils.Card;
import org.example.qaTransactionTeam.backEnd.utils.Card_param;
import org.example.qaTransactionTeam.backEnd.utils.Cards_data;
import org.example.qaTransactionTeam.backEnd.utils.RabbitMQ_http;
import org.testng.annotations.Test;

@Test
public class Amq_TR_test extends BaseTest {

    public void positive_getCardIdentifiers(){
        RabbitMQ_http rabbitMQHttp = new RabbitMQ_http("getCardIdentifiers","Cards:input");
        rabbitMQHttp.sendHttp("{" +
                "   \"pan\":\""+ Cards_data.getData(Card.FUIB_MC, Card_param.pan) +"\"" +
                "}");
    }

    public void positive_searchExternalCardholder(){
        RabbitMQ_http rabbitMQHttp = new RabbitMQ_http("searchExternalCardholder","RecipientRegistry:input");
        rabbitMQHttp.sendHttp("{" +
                " \"is_verified\":\"VERIFIED\"," +
                " \"return_short_name\":true, " +
                " \"params\":{" +
                "     \"pan_list\":\"4441111067616619\"" +
                "}" +
                "}");
    }
}
