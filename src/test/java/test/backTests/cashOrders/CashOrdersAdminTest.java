package test.backTests.cashOrders;

import org.example.qaTransactionTeam.BaseTest;
import org.example.qaTransactionTeam.backEnd.cashOrders.CashOrders;
import org.example.qaTransactionTeam.backEnd.helper.Uuid_helper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Test
public class CashOrdersAdminTest extends BaseTest {

    String merch_id = "80bbbe8b-c207-428b-98d4-654bb3b8e606";
    CashOrders cashOrders = new CashOrders();

    public void test_admin_config(){
        String body = "{\n" +
                "  \"frame_id\": \"\",\n" +
                "  \"ui_id\": \"currency-order\",\n" +
                "  \"service_id\": \"40638\",\n" +
                "  \"merchant_config_id\": \"fd4af5db-2a6a-4175-b6b6-41249ad7c70a\",\n" +
                "  \"external_id\": \""+ Uuid_helper.generate_uuid() +"\",\n" +
                "  \"title\": \"title\",\n" +
                "  \"description\": \"description\",\n" +
                "  \"short_description\": \"short_description\",\n" +
                "  \"merchant_id\": \"80bbbe8b-c207-428b-98d4-654bb3b8e606\",\n" +
                "  \"url\": \"https://innsmouth.payhub.com.ua/frames-ui/currency-order\"\n" +
                "}";
        cashOrders.setConfigs(body);
        String config_id = new JSONObject(cashOrders.getResponse()).getString("internal_id");
        cashOrders.getConfigs(merch_id);
        Assert.assertTrue(cashOrders.getResponse().contains(config_id));

        int random = new Random().nextInt(10000);
        body = "{\n" +
                "  \"status\": \"ACTIVE\",\n" +
                "  \"frame_id\": \""+config_id+"\",\n" +
                "  \"service_id\": \""+random+"\",\n" +
                "  \"title\": \"title\",\n" +
                "  \"destination\": \"\",\n" +
                "  \"url\": \"https://innsmouth.payhub.com.ua/frames-ui/cash-order?link_id=935a8e11-088c-4e41-820c-3e528931da8f&signature=3Ht5ynNXk2f2BiGKH8ddW8SFy82urK3trYe9uupAfibG2hwx2nfXhh6TpnFCdt6Wu1yyAhRnUmaNDGoTRjks21TyZLgCnc6rkFW43iamPhp6oFaf7mXLnvc5bwEmtvABE9rsKQ2NpsGSgcKv\",\n" +
                "  \"lang\": \"UK\",\n" +
                "  \"ui_id\": \"currency-order\",\n" +
                "  \"shop_url\": \"\",\n" +
                "  \"short_url\": \"\",\n" +
                "  \"cancel_url\": \"\",\n" +
                "  \"description\": \"description\",\n" +
                "  \"ga\": true,\n" +
                "  \"gpay\": {\n" +
                "    \"merchant_name\": \"apple\\\\google pay config\",\n" +
                "    \"merchant_id\": \"80bbbe8b-c207-428b-98d4-654bb3b8e606\",\n" +
                "    \"google_merchant_id\": \"test2\"\n" +
                "  }\n" +
                "}";
        cashOrders.putConfigs(body, config_id);
        cashOrders.getConfigs(merch_id);
        List<String> listJson = new ArrayList<>();
        JSONArray array = new JSONArray(cashOrders.getResponse());
        array.forEach(x -> listJson.add(x.toString()));
        Assert.assertTrue(listJson.stream().anyMatch(x -> new JSONObject(x).getInt("service_id") == random),String.format("service_id = %s - не найден",random));

    }

}
