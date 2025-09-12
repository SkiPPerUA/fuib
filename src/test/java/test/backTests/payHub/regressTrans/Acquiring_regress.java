package test.backTests.payHub.regressTrans;

import org.example.qaTransactionTeam.BaseTest;
import org.example.qaTransactionTeam.backEnd.helper.Time_helper;
import org.example.qaTransactionTeam.backEnd.payHub.AcquiringTrans;
import org.example.qaTransactionTeam.backEnd.transaction.Payer_constructor;
import org.example.qaTransactionTeam.backEnd.utils.Card;
import org.example.qaTransactionTeam.backEnd.utils.Cards_data;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Acquiring_regress extends BaseTest {

    AcquiringTrans trans;

    @Test
    public void acquiring3DSversion2() {
        String body = "\"amount\":100,\n" +
                "    \"commission\": 0,\n" +
                "    \"merchant_config_id\":\"fe18e213-d70c-4f49-b6db-8c40aca019ca\",\n" +
                Payer_constructor.PAN_payer(Cards_data.getData(Card.FUIB_VISA))+","+
                "    \"description\": \"3041309906\",\n" +
                "    \"identification\": {\n" +
                "         \"requirements\":[" +
                "               {\n" +
                "      \"recipient\":{\n" +
                "         \"first_name\":\"TESTTESTTEST\",\n" +
                "         \"last_name\":\"jkasdfjkdsfnakjdsfnakdjsfnksajdfnsadkjfnaksjdnfakк\",\n" +
                "         \"amount\":\"1212\",\n" +
                "         \"account_number\":\"UA953348510000026201112609803\"\n" +
                "      },\n" +
                "      \"sender\":{\n" +
                "         \"first_name\":\"jkasdfjkdsfnakjdsfnakdjsfnksajdfnsadkjfnaksjdnfakф\",\n" +
                "         \"last_name\":\"jkasdfjkdsfnakjdsfnakdjsfnksajdfnsadkjfnaksjdnfakф\",\n" +
                "         \"account_number\":\"UA953348510000026201112609803\",\n" +
                "         \"reference_number\":\"1111111111111111\"\n" +
                "      },\n" +
                "      \"details\":{\n" +
                "         \"additional_message\":\"11111111221222222233333333334444444444555555555666666666677777111\",\n" +
                "         \"source\":\"07\",\n" +
                "         \"submerchant_url\":\"https://jira.fuib.com/projects/PAYH/issues/PAYH-23149?filter=myopenissues\",\n" +
                "         \"independent_sales_organization_id\":\"3016715233\"\n" +
                "      }\n" +
                "      }]},\n" +
                "    \"hold\": false,\n" +
                "    \"short_description\": \"Заказ "+Time_helper.current_time()+"\",\n" +
                "    \"return_url\": \"https://innsmouth.payhub.com.ua\",\n" +
                "    \"client_ip\":  \"2001:0db8:85a3:0000:0000:8a2e:0370:7334\"," +
                "    \"3ds2_supported\": true";
        trans = new AcquiringTrans(body);
        trans.status();
//        trans.complete_hold(100);
//        trans.status();
        Assert.assertEquals(new JSONObject(trans.getResponse()).getString("status"),"PROCESSED");
    }

    @Test
    public void acquiring3DSversion2_hold() {
        String body = "\"amount\": 500,\n" +
                "    \"commission\": 0,\n" +
                "    \"merchant_config_id\":\"fe18e213-d70c-4f49-b6db-8c40aca019ca\",\n" +
                Payer_constructor.PAN_payer(Cards_data.getData(Card.FUIB_MC))+","+
                "    \"description\": \"оплата Заказа "+ Time_helper.current_time() +"\",\n" +
                "    \"hold\": true,\n" +
                "    \"short_description\": \"Заказ "+Time_helper.current_time()+"\",\n" +
                "    \"return_url\": \"https://innsmouth.payhub.com.ua\",\n" +
                "    \"client_ip\":  \"2001:0db8:85a3:0000:0000:8a2e:0370:7334\"," +
                "    \"3ds2_supported\": true";
        trans = new AcquiringTrans(body);
    }

    @Test
    public void acquiringToken_hold() {
        String body = "\"amount\": 500,\n" +
                "    \"commission\": 0,\n" +
                "    \"merchant_config_id\":\"b7c4fd54-8d44-43ea-a805-0584b6e3d1c9\",\n" +
                Payer_constructor.ITM_payer(Cards_data.getData(Card.FUIB_MC))+","+
                "    \"description\": \"оплата Заказа "+ Time_helper.current_time() +"\",\n" +
                "    \"hold\": true,\n" +
                "    \"short_description\": \"Заказ "+Time_helper.current_time()+"\",\n" +
                "    \"return_url\": \"https://innsmouth.payhub.com.ua\",\n" +
                "    \"client_ip\":  \"2001:0db8:85a3:0000:0000:8a2e:0370:7334\"," +
                "    \"3ds2_supported\": true";
        trans = new AcquiringTrans(body);
        trans.complete_hold(0);
    }

    @Test
    public void acquiringStatus(){
        AcquiringTrans trans = new AcquiringTrans();
        trans.status("8bde5910-5d22-4cd4-abd1-0e51532c5bb4");
    }

    @Test
    public void refund(){
        trans = new AcquiringTrans();
        String tr = "28a110c2-8066-4fcc-b552-a4573ab34c7e";
        trans.status(tr);
        trans.refund(tr,376);
        trans.status(tr);
    }

    @Test
    public void complete_hold(){
        trans = new AcquiringTrans();
        String tr = "ba17fd27-cd69-45ae-8a97-451f20ed4c71";
        trans.status(tr);
        trans.complete_hold(tr,0);
        trans.status(tr);
    }

    @Test
    public void get_3ds(){
        AcquiringTrans trans = new AcquiringTrans();
        String body = "{\n" +
                "    \"merchant_config_id\" : \"ccc77655-1c60-4ed9-bf13-3f586fb998a5\",\n" +
                Payer_constructor.PAN_payer(Cards_data.getData(Card.FUIB_VISA))+","+
                "  }";
        trans.get_3ds_type(body);
    }
}
