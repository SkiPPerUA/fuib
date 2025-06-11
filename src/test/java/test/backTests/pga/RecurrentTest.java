package test.backTests.pga;

import org.example.qaTransactionTeam.BaseTest;
import org.example.qaTransactionTeam.backEnd.payHub.AcquiringTrans;
import org.example.qaTransactionTeam.backEnd.payHub.Recurrent;
import org.example.qaTransactionTeam.backEnd.payHub.frames.AcquiringFrame;
import org.example.qaTransactionTeam.backEnd.utils.Card;
import org.example.qaTransactionTeam.backEnd.utils.Card_param;
import org.example.qaTransactionTeam.backEnd.utils.Cards_data;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.time.LocalDateTime;

public class RecurrentTest extends BaseTest {

    @Test
    public void recurrentTransFromFrame(){
        String body = "\"options\": {\n" +
                "        \"ttl\": 0,\n" +
                "        \"create_short_url\": true,\n" +
                "        \"backurl\": {\n" +
                "            \"success\": \"https://yourapp.test/payment/success\",\n" +
                "            \"error\": \"https://yourapp.test/payment/error\",\n" +
                "            \"cancel\": \"https://yourapp.test/payment/cancel\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"lang\": \"UK\",\n" +
                "    \"amount\": 100,\n" +
                "    \"title\": \"Merchant name\",\n" +
                "    \"description\": \"Payment description\",\n" +
                "    \"short_description\": \"Short payment description\",\n" +
                "    \"merchant_config_id\": \"219d8135-0295-40e1-9711-30a6ac3ecbf5\",\n" +
                "    \"config_id\": \"ff7e604d-d12a-42fd-9d03-aece7678a260\",\n" +
                "    \"params\": {\n" +
                "        \"shop_url\": \"https://yourapp.test\"\n" +
                "    }";
        AcquiringFrame frame = new AcquiringFrame(body);
        frame.status();
        org.example.qaTransactionTeam.backEnd.payHub.Recurrent recurrent = new org.example.qaTransactionTeam.backEnd.payHub.Recurrent(frame.getTransactionId());
    }

    @Test
    public void recurrentTrans(){
        String body = "\"amount\": 250,\n" +
                "    \"commission\": 20,\n" +
                "    \"merchant_config_id\":\"fe18e213-d70c-4f49-b6db-8c40aca019ca\",\n" + //abdd92eb-ef36-45b5-b1a0-e00f4001726e  fb7b84d4-2477-4a53-862f-814fc3b3010b
                "     \"payer\": {\n" +
                "      \"source\": \"PAN\",\n" +
                "      \"value\": \""+ Cards_data.getData(Card.FUIB_MC, Card_param.pan)+"\",\n" +
                "      \"expire\": \"2602\",\n" +
                "      \"cvv\": \""+ Cards_data.getData(Card.FUIB_MC, Card_param.cvv)+"\"\n" +
                "    }," +
                "    \"description\": \"оплата Заказа "+ LocalDateTime.now()+"\",\n" +
                "    \"hold\": false,\n" +
                "    \"short_description\": \"Заказ "+LocalDateTime.now()+"\",\n" +
                "    \"return_url\": \"https://innsmouth.payhub.com.ua\",\n" +
                "    \"client_ip\":  \"2001:0db8:85a3:0000:0000:8a2e:0370:7334\"," +  //2001:0db8:85a3:0000:0000:8a2e:0370:7334   127.0.0.1
                "    \"3ds2_supported\": true";
        AcquiringTrans trans = new AcquiringTrans(body);
        //trans.confirmTrans();
        String status = "";
        while (!status.equals("PROCESSED")&&!status.equals("FAILED")&&!status.equals("HOLDED")){
            trans.status();
            JSONObject json = new JSONObject(trans.getResponse());
            status = json.getString("status");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        new Recurrent(trans.getTransactionId());
    }

    @Test
    public void rec(){
        new Recurrent("0642c971-51d3-4f4c-9835-4a75e4438567");
    }
}
