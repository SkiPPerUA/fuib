package test.backTests.a2aTransfers;

import org.example.qaTransactionTeam.backEnd.utils.RabbitMQ_http;
import org.testng.annotations.Test;

@Test
public class A2Avalidate {
    RabbitMQ_http rabbitMQHttp = new RabbitMQ_http("validateA2ATransfer", "A2A.transfers.input");

    public void test() {
//        rabbitMQHttp.sendHttp("{" +
//                "  \"receiver\" : {" +
//                "    \"type\" : \"IBAN\"," +
//                "    \"value\" : \"UA253052990000026207671635945\"" +
//                "  }," +
//                "  \"sender\" : \"UA713348510000026201116887159\"," +
//                "  \"currency\" : \"980\"," +
//                "  \"amount\" : 1001" +
//                "}");

        rabbitMQHttp.sendHttp("{" +
                "   \"sender\":{" +
                "      \"sirius_client_id\":714259" +
                "   }," +
                "   \"recipient\":{" +
                "      \"value\":\"UA633052990000026009050032237\"," +
                "      \"source\":\"IBAN\"" +
                "   }," +
                "   \"amount\":112," +
                "   \"currency\":\"UAH\"" +
                "}");
    }
}
