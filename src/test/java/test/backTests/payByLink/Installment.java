package test.backTests.payByLink;

import org.example.qaTransactionTeam.BaseTest;
import org.example.qaTransactionTeam.backEnd.payByLink.InvoiceBusiness;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Installment extends BaseTest {

    InvoiceBusiness invoiceBusiness;
    String merchant_config_id = "fd4af5db-2a6a-4175-b6b6-41249ad7c70a";
    int amount = 50000;
    String body;
    JSONObject json;

    @Test
    public void createInstallmentPositive(){
        logStartTest("createInstallmentPositive");
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
        body = "\"amount\": "+amount+",\n" +
                "  \"description\": \"VladAutomation\",\n" +
                "  \"client_ip\": \"176.38.216.44\",\n" +
                "  \"hold\": false,\n" +
                "  \"valid_till\": \""+timeStamp+day+"T16:58:00.000Z\",\n" +
                "  \"disposable\": true,\n" +
                "  \"merchant_config_id\": \""+merchant_config_id+"\",\n"+
                "\"installment_options\": {\n" +
                "    \"max_term\": 8,\n" +
                "    \"point_id\": \"6b381682-46e0-4ceb-8b7d-43b554bc4256\",\n" +
                "    \"goods\": [\n" +
                "      {\n" +
                "        \"title\": \"comp\",\n" +
                "        \"price\": "+amount+",\n" +
                "        \"quantity\": 1\n" +
                "      }\n" +
                "    ]\n" +
                "  }";

        InvoiceBusiness.statusCode = 200;
        invoiceBusiness = new InvoiceBusiness(body,true);
        json = new JSONObject(invoiceBusiness.getResponse());
        Assert.assertTrue(json.getBoolean("is_installment"));

    logFinishTest("createInstallmentPositive");

    }

    @Test
    public void createInstallmentNegative(){
        logStartTest("createInstallmentNegative");
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
        body = "\"amount\": 49999,\n" +
                "  \"description\": \"VladAutomation\",\n" +
                "  \"client_ip\": \"176.38.216.44\",\n" +
                "  \"hold\": false,\n" +
                "  \"valid_till\": \""+timeStamp+day+"T16:58:00.000Z\",\n" +
                "  \"disposable\": true,\n" +
                "  \"merchant_config_id\": \""+merchant_config_id+"\",\n"+
                "\"installment_options\": {\n" +
                "    \"max_term\": 8,\n" +
                "    \"point_id\": \"6b381682-46e0-4ceb-8b7d-43b554bc4256\",\n" +
                "    \"goods\": [\n" +
                "      {\n" +
                "        \"title\": \"comp\",\n" +
                "        \"price\": 49999,\n" +
                "        \"quantity\": 1\n" +
                "      }\n" +
                "    ]\n" +
                "  }";

        InvoiceBusiness.statusCode = 400;
        invoiceBusiness = new InvoiceBusiness(body,true);
        logger.info("Сумма < 500 грн = ОК");

        body = "\"amount\": 50000,\n" +
                "  \"description\": \"VladAutomation\",\n" +
                "  \"client_ip\": \"176.38.216.44\",\n" +
                "  \"hold\": false,\n" +
                "  \"valid_till\": \""+timeStamp+day+"T16:58:00.000Z\",\n" +
                "  \"disposable\": true,\n" +
                "  \"merchant_config_id\": \""+merchant_config_id+"\",\n"+
                "\"installment_options\": {\n" +
                "    \"max_term\": 8,\n" +
                "    \"point_id\": \"6b381682-46e0-4ceb-8b7d-43b554bc4256\",\n" +
                "    \"goods\": [\n" +
                "      {\n" +
                "        \"title\": \"comp\",\n" +
                "        \"price\": 50001,\n" +
                "        \"quantity\": 1\n" +
                "      }\n" +
                "    ]\n" +
                "  }";

        invoiceBusiness = new InvoiceBusiness(body,true);
        logger.info("Суммы не совпадают = ОК");

        body = "\"amount\": 10000001,\n" +
                "  \"description\": \"VladAutomation\",\n" +
                "  \"client_ip\": \"176.38.216.44\",\n" +
                "  \"hold\": false,\n" +
                "  \"valid_till\": \""+timeStamp+day+"T16:58:00.000Z\",\n" +
                "  \"disposable\": true,\n" +
                "  \"merchant_config_id\": \""+merchant_config_id+"\",\n"+
                "\"installment_options\": {\n" +
                "    \"max_term\": 8,\n" +
                "    \"point_id\": \"6b381682-46e0-4ceb-8b7d-43b554bc4256\",\n" +
                "    \"goods\": [\n" +
                "      {\n" +
                "        \"title\": \"comp\",\n" +
                "        \"price\": 10000001,\n" +
                "        \"quantity\": 1\n" +
                "      }\n" +
                "    ]\n" +
                "  }";

        invoiceBusiness = new InvoiceBusiness(body,true);
        logger.info("Сумма > 100 000 грн = ОК");

        logFinishTest("createInstallmentNegative");

    }



}
