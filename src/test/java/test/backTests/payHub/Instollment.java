package test.backTests.payHub;

import org.example.qaTransactionTeam.BaseTest;
import org.example.qaTransactionTeam.backEnd.payByLink.InvoiceBusiness;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Instollment extends BaseTest {

    InvoiceBusiness invoiceBusiness;
    JSONObject jsonObject;

    @Test
    public void positiveCreateInstollmentOneGoods(){
        logStartTest("positiveCreateInstollmentOneGoods");
        String body = "\"amount\": 100000,\n" +
                "  \"description\": \"tewts\",\n" +
                "  \"client_ip\": \"176.38.216.44\",\n" +
                "  \"hold\": false,\n" +
                "  \"valid_till\": \"2023-01-31T22:00:00.000Z\",\n" +
                "  \"disposable\": true,\n" +
                "  \"merchant_config_id\": \"fd4af5db-2a6a-4175-b6b6-41249ad7c70a\",\n" +
                "  \"installment_options\": {\n" +
                "    \"max_term\": 8,\n" +
                "    \"point_id\": \"6b381682-46e0-4ceb-8b7d-43b554bc4256\",\n" +
                "    \"goods\": [\n" +
                "      {\n" +
                "        \"title\": \"comp\",\n" +
                "        \"price\": 100000,\n" +
                "        \"quantity\": 1\n" +
                "      }" +
                "    ]\n" +
                "  }";
        invoiceBusiness = new InvoiceBusiness(body,false);
        jsonObject = new JSONObject(invoiceBusiness.getResponse());
        Assert.assertEquals(jsonObject.getString("status"),"CREATED");
        Assert.assertTrue(jsonObject.getBoolean("is_installment"));
        logFinishTest("positiveCreateInstollmentOneGoods");
    }

    @Test
    public void positiveCreateInstollmentThreeGoods(){
        logStartTest("positiveCreateInstollmentThreeGoods");
        String body = "\"amount\": 100000,\n" +
                "  \"description\": \"tewts\",\n" +
                "  \"client_ip\": \"176.38.216.44\",\n" +
                "  \"hold\": false,\n" +
                "  \"valid_till\": \"2023-01-31T22:00:00.000Z\",\n" +
                "  \"disposable\": true,\n" +
                "  \"merchant_config_id\": \"fd4af5db-2a6a-4175-b6b6-41249ad7c70a\",\n" +
                "  \"installment_options\": {\n" +
                "    \"max_term\": 8,\n" +
                "    \"point_id\": \"6b381682-46e0-4ceb-8b7d-43b554bc4256\",\n" +
                "    \"goods\": [\n" +
                "      {\n" +
                "        \"title\": \"comp1\",\n" +
                "        \"price\": 50000,\n" +
                "        \"quantity\": 1\n" +
                "      }," +
                "      {\n" +
                "        \"title\": \"comp2\",\n" +
                "        \"price\": 30000,\n" +
                "        \"quantity\": 1\n" +
                "      }," +
                "      {\n" +
                "        \"title\": \"comp3\",\n" +
                "        \"price\": 20000,\n" +
                "        \"quantity\": 1\n" +
                "      }" +
                "    ]\n" +
                "  }";
        invoiceBusiness = new InvoiceBusiness(body,false);
        jsonObject = new JSONObject(invoiceBusiness.getResponse());
        Assert.assertEquals(jsonObject.getString("status"),"CREATED");
        Assert.assertTrue(jsonObject.getBoolean("is_installment"));
        logFinishTest("positiveCreateInstollmentThreeGoods");
    }

    @Test
    public void negativeCreateInstollmentDifferentSumOneGoods(){
        logStartTest("negativeCreateInstollmentDifferentSumOneGoods");
        InvoiceBusiness.statusCode = 500;
        String body = "\"amount\": 100000,\n" +
                "  \"description\": \"tewts\",\n" +
                "  \"client_ip\": \"176.38.216.44\",\n" +
                "  \"hold\": false,\n" +
                "  \"valid_till\": \"2023-01-31T22:00:00.000Z\",\n" +
                "  \"disposable\": true,\n" +
                "  \"merchant_config_id\": \"fd4af5db-2a6a-4175-b6b6-41249ad7c70a\",\n" +
                "  \"installment_options\": {\n" +
                "    \"max_term\": 8,\n" +
                "    \"point_id\": \"6b381682-46e0-4ceb-8b7d-43b554bc4256\",\n" +
                "    \"goods\": [\n" +
                "      {\n" +
                "        \"title\": \"comp\",\n" +
                "        \"price\": 100001,\n" +
                "        \"quantity\": 1\n" +
                "      }" +
                "    ]\n" +
                "  }";
        invoiceBusiness = new InvoiceBusiness(body,false);
        jsonObject = new JSONObject(invoiceBusiness.getResponse());
        Assert.assertEquals(jsonObject.getString("code"),"BAD_REQUEST");
        Assert.assertEquals(jsonObject.getString("message"),"Загальна вартість товарів не співпадає із заявленою сумою");
        invoiceBusiness.setStatusCode(200);
        logFinishTest("negativeCreateInstollmentDifferentSumOneGoods");
    }

    @Test
    public void negativeCreateInstollmentDifferentSumTwoGoods(){
        logStartTest("negativeCreateInstollmentDifferentSumTwoGoods");
        InvoiceBusiness.statusCode = 500;
        String body = "\"amount\": 100000,\n" +
                "  \"description\": \"tewts\",\n" +
                "  \"client_ip\": \"176.38.216.44\",\n" +
                "  \"hold\": false,\n" +
                "  \"valid_till\": \"2023-01-31T22:00:00.000Z\",\n" +
                "  \"disposable\": true,\n" +
                "  \"merchant_config_id\": \"fd4af5db-2a6a-4175-b6b6-41249ad7c70a\",\n" +
                "  \"installment_options\": {\n" +
                "    \"max_term\": 8,\n" +
                "    \"point_id\": \"6b381682-46e0-4ceb-8b7d-43b554bc4256\",\n" +
                "    \"goods\": [\n" +
                "      {\n" +
                "        \"title\": \"comp\",\n" +
                "        \"price\": 10000,\n" +
                "        \"quantity\": 1\n" +
                "      }," +
                "      {\n" +
                "        \"title\": \"comp3\",\n" +
                "        \"price\": 20000,\n" +
                "        \"quantity\": 1\n" +
                "      }" +
                "    ]\n" +
                "  }";
        invoiceBusiness = new InvoiceBusiness(body,false);
        jsonObject = new JSONObject(invoiceBusiness.getResponse());
        Assert.assertEquals(jsonObject.getString("code"),"BAD_REQUEST");
        Assert.assertEquals(jsonObject.getString("message"),"Загальна вартість товарів не співпадає із заявленою сумою");
        invoiceBusiness.setStatusCode(200);
        logFinishTest("negativeCreateInstollmentDifferentSumTwoGoods");
    }

    @Test
    public void negativeCreateInstollmentMoreThanTenGoods(){
        logStartTest("negativeCreateInstollmentMoreThanTenGoods");
        InvoiceBusiness.statusCode = 500;
        String body = "\"amount\": 100000,\n" +
                "  \"description\": \"tewts\",\n" +
                "  \"client_ip\": \"176.38.216.44\",\n" +
                "  \"hold\": false,\n" +
                "  \"valid_till\": \"2023-01-31T22:00:00.000Z\",\n" +
                "  \"disposable\": true,\n" +
                "  \"merchant_config_id\": \"fd4af5db-2a6a-4175-b6b6-41249ad7c70a\",\n" +
                "  \"installment_options\": {\n" +
                "    \"max_term\": 8,\n" +
                "    \"point_id\": \"6b381682-46e0-4ceb-8b7d-43b554bc4256\",\n" +
                "    \"goods\": [\n" +
                "      {\n" +
                "        \"title\": \"comp1\",\n" +
                "        \"price\": 10000,\n" +
                "        \"quantity\": 1\n" +
                "      }," +
                "      {\n" +
                "        \"title\": \"comp2\",\n" +
                "        \"price\": 10000,\n" +
                "        \"quantity\": 1\n" +
                "      }," +
                "      {\n" +
                "        \"title\": \"comp3\",\n" +
                "        \"price\": 10000,\n" +
                "        \"quantity\": 1\n" +
                "      }," +
                "      {\n" +
                "        \"title\": \"comp4\",\n" +
                "        \"price\": 10000,\n" +
                "        \"quantity\": 1\n" +
                "      }," +
                "      {\n" +
                "        \"title\": \"comp5\",\n" +
                "        \"price\": 10000,\n" +
                "        \"quantity\": 1\n" +
                "      }," +
                "      {\n" +
                "        \"title\": \"comp6\",\n" +
                "        \"price\": 10000,\n" +
                "        \"quantity\": 1\n" +
                "      }," +
                "      {\n" +
                "        \"title\": \"comp7\",\n" +
                "        \"price\": 10000,\n" +
                "        \"quantity\": 1\n" +
                "      }," +
                "      {\n" +
                "        \"title\": \"comp8\",\n" +
                "        \"price\": 10000,\n" +
                "        \"quantity\": 1\n" +
                "      }," +
                "      {\n" +
                "        \"title\": \"comp9\",\n" +
                "        \"price\": 10000,\n" +
                "        \"quantity\": 1\n" +
                "      }," +
                "      {\n" +
                "        \"title\": \"comp10\",\n" +
                "        \"price\": 5000,\n" +
                "        \"quantity\": 1\n" +
                "      }," +
                "      {\n" +
                "        \"title\": \"comp11\",\n" +
                "        \"price\": 5000,\n" +
                "        \"quantity\": 1\n" +
                "      }" +
                "    ]\n" +
                "  }";
        invoiceBusiness = new InvoiceBusiness(body,false);
        jsonObject = new JSONObject(invoiceBusiness.getResponse());
        Assert.assertEquals(jsonObject.getString("code"),"BAD_REQUEST");
        Assert.assertEquals(jsonObject.getString("message"),"Перевищено максимально допустиму кількість товарів: 10 одиниць");
        invoiceBusiness.setStatusCode(200);
        logFinishTest("negativeCreateInstollmentMoreThanTenGoods");
    }

    @Test
    public void negativeCreateInstollmentSumMore100000(){
        logStartTest("negativeCreateInstollmentSumMore100000");
        InvoiceBusiness.statusCode = 500;
        String body = "\"amount\": 10000001,\n" +
                "  \"description\": \"tewts\",\n" +
                "  \"client_ip\": \"176.38.216.44\",\n" +
                "  \"hold\": false,\n" +
                "  \"valid_till\": \"2023-01-31T22:00:00.000Z\",\n" +
                "  \"disposable\": true,\n" +
                "  \"merchant_config_id\": \"fd4af5db-2a6a-4175-b6b6-41249ad7c70a\",\n" +
                "  \"installment_options\": {\n" +
                "    \"max_term\": 8,\n" +
                "    \"point_id\": \"6b381682-46e0-4ceb-8b7d-43b554bc4256\",\n" +
                "    \"goods\": [\n" +
                "      {\n" +
                "        \"title\": \"comp\",\n" +
                "        \"price\": 10000001,\n" +
                "        \"quantity\": 1\n" +
                "      }" +
                "    ]\n" +
                "  }";
        invoiceBusiness = new InvoiceBusiness(body,false);
        jsonObject = new JSONObject(invoiceBusiness.getResponse());
        Assert.assertEquals(jsonObject.getString("code"),"BAD_REQUEST");
        Assert.assertEquals(jsonObject.getString("message"),"Перевищено максимальну суму кредиту: 100000 грн.");
        invoiceBusiness.setStatusCode(200);
        logFinishTest("negativeCreateInstollmentSumMore100000");
    }

    @Test
    public void negativeCreateInstollmentSumLess500(){
        logStartTest("negativeCreateInstollmentSumLess500");
        InvoiceBusiness.statusCode = 500;
        String body = "\"amount\": 49999,\n" +
                "  \"description\": \"tewts\",\n" +
                "  \"client_ip\": \"176.38.216.44\",\n" +
                "  \"hold\": false,\n" +
                "  \"valid_till\": \"2023-01-31T22:00:00.000Z\",\n" +
                "  \"disposable\": true,\n" +
                "  \"merchant_config_id\": \"fd4af5db-2a6a-4175-b6b6-41249ad7c70a\",\n" +
                "  \"installment_options\": {\n" +
                "    \"max_term\": 8,\n" +
                "    \"point_id\": \"6b381682-46e0-4ceb-8b7d-43b554bc4256\",\n" +
                "    \"goods\": [\n" +
                "      {\n" +
                "        \"title\": \"comp\",\n" +
                "        \"price\": 49999,\n" +
                "        \"quantity\": 1\n" +
                "      }" +
                "    ]\n" +
                "  }";
        invoiceBusiness = new InvoiceBusiness(body,false);
        jsonObject = new JSONObject(invoiceBusiness.getResponse());
        Assert.assertEquals(jsonObject.getString("code"),"BAD_REQUEST");
        Assert.assertEquals(jsonObject.getString("message"),"Мінімальна сума для кредиту: 500 грн.");
        invoiceBusiness.setStatusCode(200);
        logFinishTest("negativeCreateInstollmentSumLess500");
    }

}
