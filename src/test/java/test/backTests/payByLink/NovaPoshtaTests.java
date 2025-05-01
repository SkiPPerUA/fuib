package test.backTests.payByLink;

import org.example.qaTransactionTeam.BaseTest;
import org.example.qaTransactionTeam.backEnd.payByLink.InvoiceBusiness;
import org.example.qaTransactionTeam.backEnd.payByLink.NovaPoshta;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class NovaPoshtaTests extends BaseTest {

    NovaPoshta novaPoshta = new NovaPoshta();

    @Test
    void positiveAuthNP(){
        logStartTest("positiveAuthNP");
        novaPoshta.setStatusCode(204);
        novaPoshta.addKeyNP("e6d262f26bfb0d35d126f61102c9016c");
        logFinishTest("positiveAuthNP");
    }

    @Test
    void negativeAuthNP(){
        logStartTest("negativeAuthNP");
        novaPoshta.setStatusCode(400);
        novaPoshta.addKeyNP("e6d262f26bfb0d35d1");
        logFinishTest("negativeAuthNP");
    }

    @Test
    void negativeAuthNPEmpty(){
        logStartTest("negativeAuthNPEmpty");
        novaPoshta.setStatusCode(400);
        novaPoshta.addKeyNP("");
        logFinishTest("negativeAuthNPEmpty");
    }

    @Test
    void positiveSendersNP(){
        logStartTest("positiveSendersNP");
        novaPoshta.setStatusCode(204);
        novaPoshta.addKeyNP("e6d262f26bfb0d35d126f61102c9016c");
        novaPoshta.setStatusCode(200);
        novaPoshta.senders();
        Assert.assertEquals(novaPoshta.getIdSenders(),"da8eeb85-9942-11ea-8513-b88303659df5");
        logFinishTest("positiveSendersNP");
    }

    @Test
    void positiveContractsNP(){
        logStartTest("positiveContractsNP");
        positiveSendersNP();
        novaPoshta.contracts();
        JSONObject array = new JSONArray(novaPoshta.getResponse()).getJSONObject(0);
        Assert.assertEquals(array.getString("description"),"Савчук Владислав Игоревич");
        Assert.assertEquals(array.getString("email"),"savchukvi12@gmail.com");
        logFinishTest("positiveContractsNP");
    }

    @Test
    void positiveCreateNPDoc(){
        logStartTest("positiveCreateNPDoc");
        String timeStamp = new SimpleDateFormat("yyyy-MM-").format(Calendar.getInstance().getTime());
        String dayStamp = new SimpleDateFormat("dd").format(Calendar.getInstance().getTime());
        String day;
        if(Integer.parseInt(dayStamp) < 9){
            day = "0"+(Integer.parseInt(dayStamp)+1);
        }else if (Integer.parseInt(dayStamp) > 8 && Integer.parseInt(dayStamp) < 30){
            day = String.valueOf(Integer.parseInt(dayStamp) + 1);
        }else {
            day = "05";
        }

        String body1 = "\"amount\": 146,\n" +
                "  \"description\": \"VladAutomation\",\n" +
                "  \"client_ip\": \"176.38.216.44\",\n" +
                "  \"hold\": false,\n" +
                "  \"valid_till\": \""+timeStamp+day+"T16:58:00.000Z\",\n" +
                "  \"disposable\": true,\n" +
                "  \"merchant_config_id\": \"fd4af5db-2a6a-4175-b6b6-41249ad7c70a\"\n";

        InvoiceBusiness invoiceBusiness = new InvoiceBusiness(body1,false);
        invoiceBusiness.payInvoice();
        positiveSendersNP();
        String body = "{\n" +
                "  \"sender\": {\n" +
                "    \"id\": \""+novaPoshta.getIdSenders()+"\",\n" +
                "    \"settlement_id\": \"e718a680-4b33-11e4-ab6d-005056801329\",\n" +
                "    \"phone\": \"380933666636\",\n" +
                "    \"contact_id\": \"e45bf2f4-9942-11ea-8513-b88303659df5\",\n" +
                "    \"address_id\": \"1ec09d88-e1c2-11e3-8c4a-0050568002cf\"\n" +
                "  },\n" +
                "  \"recipient\": {\n" +
                "    \"name\": \"ыфвфы фафч ч\",\n" +
                "    \"settlement_name\": \"Київ\",\n" +
                "    \"warehouse_number\": 2,\n" +
                "    \"phone\": \"380505555554\"\n" +
                "  },\n" +
                "  \"payment_method\": \"CASH\",\n" +
                "  \"payer_type\": \"RECEIVER\",\n" +
                "  \"cargo_type\": \"CARGO\",\n" +
                "  \"seats\": 11,\n" +
                "  \"cost\": 1,\n" +
                "  \"description\": \"description\",\n" +
                "  \"dimensions\": {\n" +
                "    \"width\": null,\n" +
                "    \"height\": null,\n" +
                "    \"length\": 1\n" +
                "  },\n" +
                "  \"weight\": 1.1\n" +
                "}";
        novaPoshta.createNPDoc(body,invoiceBusiness.getInvoiceId());
        JSONObject jsonObject = new JSONObject(novaPoshta.getResponse());
        Assert.assertEquals(jsonObject.getString("status"),"NEW");
        logFinishTest("positiveCreateNPDoc");
    }
}
