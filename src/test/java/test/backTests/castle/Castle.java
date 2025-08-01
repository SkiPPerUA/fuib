package test.backTests.castle;

import org.example.qaTransactionTeam.backEnd.helper.BodyHelper;
import org.example.qaTransactionTeam.backEnd.helper.Uuid_helper;
import org.example.qaTransactionTeam.backEnd.utils.RabbitMQ_http;
import org.testng.annotations.Test;

import java.util.List;

@Test
public class Castle {
    String event_type = "APP_A2C";
    String client_id = "6241781";

    public void test_castle(){

//        List.of("APP_A2SEP","APP_A2C","APP_A2P","APP_A2X","APP_A2CSBRD","APP_A2FUIB","APP_A2T").forEach(x -> {event_type = x;
//            //castle_add("1");
//            castle_confirm("1","failed");
//            //castle_getLimit("NBU_Memo_Stat"); //NBU_A2CSBRD_Stat  NBU_Transfer_Stat  NBU_Memo_Stat
//        });
//        castle_add("120");
        castle_confirm("500","failed");
//        castle_getLimit("NBU_Memo_Stat");
//        castle_getLimit("NBU_Transfer_Stat");//NBU_A2CSBRD_Stat  NBU_Transfer_Stat  NBU_Memo_Stat

    }

    private void castle_add(String amount) {
        RabbitMQ_http rabbitMQHttp = new RabbitMQ_http("GetColorsSh2","Castle:input");
        rabbitMQHttp.sendHttp("{" +
                "    \"operations\": [" +
                "        {" +
                "            \"event_type\": \""+event_type+"\"," +
                "            \"payer_source\": \"IBAN\"," +
                "            \"payer_value\": \"UA973348510000026203118348898\"," +
                "            \"payer_client_id\": \"1299125\"," +
                "            \"receiver_value\": \"537541******5988\"," +
                "            \"receiver_source\": \"PAN\"," +
                "            \"card_payhub_atm\": "+amount+"," +
                "            \"ip\": \"2.22.90.51\"," +
                "            \"client_id\": \""+client_id+"\"," + //2189387   544881
                "            \"ekb_id\": 8531524," +
                "            \"unique_id\": \""+Uuid_helper.generate_uuid() +"\"," +
                "            \"currency\": \"UAH\"" +
                "        }" +
                "    ]" +
                "}");
    }

    private void castle_getLimit(String limit) {
        RabbitMQ_http rabbitMQHttp = new RabbitMQ_http("GetCounterSh2","Castle:input");
        rabbitMQHttp.sendHttp("{" +
                "            \"event_type\": \""+limit+"\"," +
                "            \"ekb_id\": 8531524," +
                "            \"client_id\": \""+client_id+"\"" + //2189387   544881
                "}");
    }

    private void castle_confirm(String amount, String status) {
        RabbitMQ_http rabbitMQHttp = new RabbitMQ_http("ConfirmOperationsSh2","Castle:input");
        rabbitMQHttp.sendHttp("{" +
                "    \"operations\": [" +
                "        {" +
                "            \"transaction_status\":\""+status+"\","+  //processed  failed
                "            \"event_type\": \""+event_type+"\"," +
                "            \"payer_source\": \"IBAN\"," +
                "            \"payer_value\": \"UA973348510000026203118348898\"," +
                "            \"payer_client_id\": \"2189388\"," +
                "            \"receiver_value\": \"53754*****5988\"," +
                "            \"receiver_source\": \"PAN\"," +
                "            \"card_payhub_atm\": "+amount+"," +
                "            \"ip\": \"2.22.90.51\"," +
                "            \"client_id\": \""+client_id+"\"," +
                "            \"ekb_id\": 8531524," +
                "            \"unique_id\": \""+Uuid_helper.generate_uuid() +"\"," +
                "            \"currency\": \"UAH\"" +
                "        }" +
                "    ]" +
                "}");
    }
}
