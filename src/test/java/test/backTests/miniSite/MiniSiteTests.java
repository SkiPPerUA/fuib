package test.backTests.miniSite;

import org.example.qaTransactionTeam.BaseTest;
import org.example.qaTransactionTeam.backEnd.miniSites.MiniSite;
import org.example.qaTransactionTeam.backEnd.miniSites.ProductMiniSite;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;


public class MiniSiteTests extends BaseTest {

    MiniSite miniSite = new MiniSite();
    ProductMiniSite productMiniSite = new ProductMiniSite();
    String body;
    JSONObject json;

    @Test
    void createMiniSitePositive(){
        logStartTest("createMiniSitePositive");

        miniSite.setStatusCode(200);
        body = "{\n" +
                "\"name\": \"VladTests\",\n" +
                "\"phone\": \"+380939999998\",\n" +
                "\"show_contacts\": true,\n" +
                "\"show_about_company\": true,\n" +
                "\"extra_contact_data\": \"инфо11\",\n" +
                "\"about_title\": \"заголов12\",\n" +
                "\"about_description\": \"<p>тестИнфо</p>\"\n" +
                "}";
        miniSite.createMiniSite(body);
        miniSite.getMiniSite("/"+miniSite.getSiteId());
        json = new JSONObject(miniSite.getResponse());
        Assert.assertEquals(json.getString("name"),"VladTests");
        Assert.assertEquals(json.getString("phone_number"),"+380939999998");
        Assert.assertEquals(json.getString("extra_contact_data"),"инфо11");
        Assert.assertEquals(json.getString("about_description"),"<p>тестИнфо</p>");
        Assert.assertEquals(json.getString("about_title"),"заголов12");
        Assert.assertTrue(json.getBoolean("show_contacts"));
        Assert.assertTrue(json.getBoolean("show_about_company"));
        Assert.assertEquals(json.getString("status"),"ACTIVE");
        miniSite.setStatusCode(204);
        miniSite.deleteMiniSite(miniSite.getSiteId());


        miniSite.setStatusCode(200);
        body = "{\n" +
                "\"name\": \"VladTests\",\n" +
                "\"phone\": \"+380939999998\",\n" +
                "\"show_contacts\": false,\n" +
                "\"show_about_company\": false,\n" +
                "\"extra_contact_data\": \"инфо112\",\n" +
                "\"about_title\": \"заголов12\",\n" +
                "\"about_description\": \"<p>тестИнфо</p>\"\n" +
                "}";
        miniSite.createMiniSite(body);
        miniSite.getMiniSite("/"+miniSite.getSiteId());
        json = new JSONObject(miniSite.getResponse());
        Assert.assertEquals(json.getString("name"),"VladTests");
        Assert.assertEquals(json.getString("phone_number"),"+380939999998");
        Assert.assertEquals(json.getString("extra_contact_data"),"инфо112");
        Assert.assertEquals(json.getString("about_description"),"<p>тестИнфо</p>");
        Assert.assertEquals(json.getString("about_title"),"заголов12");
        Assert.assertFalse(json.getBoolean("show_contacts"));
        Assert.assertFalse(json.getBoolean("show_about_company"));
        Assert.assertEquals(json.getString("status"),"ACTIVE");
        miniSite.setStatusCode(204);
        miniSite.deleteMiniSite(miniSite.getSiteId());


        miniSite.setStatusCode(200);
        body = "{\n" +
                "\"name\": \"VladTests\",\n" +
                "\"phone\": \"+380939999998\",\n" +
                "\"show_contacts\": true,\n" +
                "\"show_about_company\": false,\n" +
                "\"extra_contact_data\": \"инфо112\",\n" +
                "\"about_title\": \"заголов12\",\n" +
                "\"about_description\": \"<p>тестИнфо</p>\"\n" +
                "}";
        miniSite.createMiniSite(body);
        miniSite.getMiniSite("/"+miniSite.getSiteId());
        json = new JSONObject(miniSite.getResponse());
        Assert.assertEquals(json.getString("name"),"VladTests");
        Assert.assertEquals(json.getString("phone_number"),"+380939999998");
        Assert.assertEquals(json.getString("extra_contact_data"),"инфо112");
        Assert.assertEquals(json.getString("about_description"),"<p>тестИнфо</p>");
        Assert.assertEquals(json.getString("about_title"),"заголов12");
        Assert.assertTrue(json.getBoolean("show_contacts"));
        Assert.assertFalse(json.getBoolean("show_about_company"));
        Assert.assertEquals(json.getString("status"),"ACTIVE");
        miniSite.setStatusCode(204);
        miniSite.deleteMiniSite(miniSite.getSiteId());


        miniSite.setStatusCode(200);
        body = "{\n" +
                "\"name\": \"VladTests\",\n" +
                "\"phone\": \"+380939999998\",\n" +
                "\"show_contacts\": false,\n" +
                "\"show_about_company\": true,\n" +
                "\"extra_contact_data\": \"инфо112\",\n" +
                "\"about_title\": \"заголов12\",\n" +
                "\"about_description\": \"<p>тестИнфо</p>\"\n" +
                "}";
        miniSite.createMiniSite(body);
        miniSite.getMiniSite("/"+miniSite.getSiteId());
        json = new JSONObject(miniSite.getResponse());
        Assert.assertEquals(json.getString("name"),"VladTests");
        Assert.assertEquals(json.getString("phone_number"),"+380939999998");
        Assert.assertEquals(json.getString("extra_contact_data"),"инфо112");
        Assert.assertEquals(json.getString("about_description"),"<p>тестИнфо</p>");
        Assert.assertEquals(json.getString("about_title"),"заголов12");
        Assert.assertFalse(json.getBoolean("show_contacts"));
        Assert.assertTrue(json.getBoolean("show_about_company"));
        Assert.assertEquals(json.getString("status"),"ACTIVE");
        miniSite.setStatusCode(204);
        miniSite.deleteMiniSite(miniSite.getSiteId());


        logFinishTest("createMiniSitePositive");
    }

    @Test
    void createMiniSiteNegative(){
        logStartTest("createMiniSiteNegative");

        miniSite.setStatusCode(400);
        //phone
        String [] phoneVariants = {"null", "380939999998", "", "380139999998", "default", "3809399999981"};
        for (int i = 0; i < phoneVariants.length; i++) {
            body = "{\n" +
                    "\"name\": \"VladTests\",\n" +
                    "\"phone\": \""+phoneVariants[i]+"\",\n" +
                    "\"show_contacts\": true,\n" +
                    "\"show_about_company\": true,\n" +
                    "\"extra_contact_data\": \"инфо11\",\n" +
                    "\"about_title\": \"заголов12\",\n" +
                    "\"about_description\": \"<p>тестИнфо</p>\"\n" +
                    "}";
            miniSite.createMiniSite(body);
        }

        //name = ""
        body = "{\n" +
                    "\"name\": \"\",\n" +
                    "\"phone\": \"+380939999998\",\n" +
                    "\"show_contacts\": true,\n" +
                    "\"show_about_company\": true,\n" +
                    "\"extra_contact_data\": \"инфо11\",\n" +
                    "\"about_title\": \"заголов12\",\n" +
                    "\"about_description\": \"<p>тестИнфо</p>\"\n" +
                    "}";
        miniSite.createMiniSite(body);

        //check mandatory parameters
        body = "{\n" +
                "\"phone\": \"+380939999998\",\n" +
                "\"show_contacts\": true,\n" +
                "\"show_about_company\": true,\n" +
                "\"extra_contact_data\": \"инфо11\",\n" +
                "\"about_title\": \"заголов12\",\n" +
                "\"about_description\": \"<p>тестИнфо</p>\"\n" +
                "}";
        miniSite.createMiniSite(body);

        body = "{\n" +
                "\"name\": \"VladTests\",\n" +
                "\"show_contacts\": true,\n" +
                "\"show_about_company\": true,\n" +
                "\"extra_contact_data\": \"инфо11\",\n" +
                "\"about_title\": \"заголов12\",\n" +
                "\"about_description\": \"<p>тестИнфо</p>\"\n" +
                "}";
        miniSite.createMiniSite(body);

        logFinishTest("createMiniSiteNegative");
    }

    @Test
    void changeStatusMiniSitePositive(){
        logStartTest("changeStatusMiniSitePositive");

        miniSite.setStatusCode(200);
        body = "{\n" +
                "\"name\": \"VladTests\",\n" +
                "\"phone\": \"+380939999998\",\n" +
                "\"show_contacts\": true,\n" +
                "\"show_about_company\": true,\n" +
                "\"extra_contact_data\": \"инфо11\",\n" +
                "\"about_title\": \"заголов12\",\n" +
                "\"about_description\": \"<p>тестИнфо</p>\"\n" +
                "}";
        miniSite.createMiniSite(body);

        String [] status = {"DRAFT", "DELETED","ACTIVE"};
        for (int i = 0; i < status.length; i++){
            body = "{\n" +
                    "  \"status\": \""+status[i]+"\"\n" +
                    "}";
            miniSite.setStatusCode(204);
            miniSite.changeStatusForMiniSite(body, miniSite.getSiteId());
            miniSite.setStatusCode(200);
            miniSite.getMiniSite("/"+miniSite.getSiteId());
            json = new JSONObject(miniSite.getResponse());
            Assert.assertEquals(json.getString("status"),status[i]);
        }

        miniSite.setStatusCode(204);
        miniSite.deleteMiniSite(miniSite.getSiteId());

        logFinishTest("changeStatusMiniSitePositive");
    }

    @Test
    void changeStatusMiniSiteNegative(){
        logStartTest("changeStatusMiniSitePositive");

        miniSite.setStatusCode(200);
        body = "{\n" +
                "\"name\": \"VladTests\",\n" +
                "\"phone\": \"+380939999998\",\n" +
                "\"show_contacts\": true,\n" +
                "\"show_about_company\": true,\n" +
                "\"extra_contact_data\": \"инфо11\",\n" +
                "\"about_title\": \"заголов12\",\n" +
                "\"about_description\": \"<p>тестИнфо</p>\"\n" +
                "}";
        miniSite.createMiniSite(body);

        String [] status = {"DRAFT1", ""};
        for (int i = 0; i < status.length; i++){
            body = "{\n" +
                    "  \"status\": \""+status[i]+"\"\n" +
                    "}";
            miniSite.setStatusCode(400);
            miniSite.changeStatusForMiniSite(body, miniSite.getSiteId());
        }

        //НЕ существующий магазин
        body = "{\n" +
                "  \"status\": \"DRAFT\"\n" +
                "}";
        miniSite.setStatusCode(400);
        miniSite.changeStatusForMiniSite(body, "da8dc962-352f-47c1-be48-ad764032e2cc");

        logFinishTest("changeStatusMiniSitePositive");
    }

    @Test
    void updateMiniSitePositive(){
        logStartTest("updateMiniSitePositive");

        miniSite.setStatusCode(200);
        body = "{\n" +
                "\"name\": \"VladTests\",\n" +
                "\"phone\": \"+380939999998\",\n" +
                "\"show_contacts\": true,\n" +
                "\"show_about_company\": true,\n" +
                "\"extra_contact_data\": \"инфо11\",\n" +
                "\"about_title\": \"заголов12\",\n" +
                "\"about_description\": \"<p>тестИнфо</p>\"\n" +
                "}";
        miniSite.createMiniSite(body);
        miniSite.getMiniSite("/"+miniSite.getSiteId());
        json = new JSONObject(miniSite.getResponse());
        Assert.assertEquals(json.getString("name"),"VladTests");
        Assert.assertEquals(json.getString("phone_number"),"+380939999998");
        Assert.assertEquals(json.getString("extra_contact_data"),"инфо11");
        Assert.assertEquals(json.getString("about_description"),"<p>тестИнфо</p>");
        Assert.assertEquals(json.getString("about_title"),"заголов12");
        Assert.assertTrue(json.getBoolean("show_contacts"));
        Assert.assertTrue(json.getBoolean("show_about_company"));
        Assert.assertEquals(json.getString("status"),"ACTIVE");

        body = "{\n" +
                "  \"name\": \"66666\",\n" +
                "  \"phone\": \"+380939933388\",\n" +
                "  \"show_contacts\": false,\n" +
                "  \"show_about_company\": false,\n" +
                "  \"extra_contact_data\": \"sfsd166\",\n" +
                "  \"about_title\": \"заголов1111666\",\n" +
                "  \"about_description\": \"<p>тестИнфо1111</p>\"\n" +
                "}";
        miniSite.setStatusCode(204);
        miniSite.editMiniSite(body);
        miniSite.setStatusCode(200);
        miniSite.getMiniSite("/"+miniSite.getSiteId());
        json = new JSONObject(miniSite.getResponse());
        Assert.assertEquals(json.getString("name"),"66666");
        Assert.assertEquals(json.getString("phone_number"),"+380939933388");
        Assert.assertEquals(json.getString("extra_contact_data"),"sfsd166");
        Assert.assertEquals(json.getString("about_description"),"<p>тестИнфо1111</p>");
        Assert.assertEquals(json.getString("about_title"),"заголов1111666");
        Assert.assertFalse(json.getBoolean("show_contacts"));
        Assert.assertFalse(json.getBoolean("show_about_company"));
        Assert.assertEquals(json.getString("status"),"ACTIVE");

        miniSite.setStatusCode(204);
        miniSite.deleteMiniSite(miniSite.getSiteId());

        logFinishTest("updateMiniSitePositive");
    }

    @Test
    void updateMiniSiteNegative(){
        logStartTest("updateMiniSiteNegative");

        miniSite.setStatusCode(200);
        body = "{\n" +
                "\"name\": \"VladTests\",\n" +
                "\"phone\": \"+380939999998\",\n" +
                "\"show_contacts\": true,\n" +
                "\"show_about_company\": true,\n" +
                "\"extra_contact_data\": \"инфо11\",\n" +
                "\"about_title\": \"заголов12\",\n" +
                "\"about_description\": \"<p>тестИнфо</p>\"\n" +
                "}";
        miniSite.createMiniSite(body);
        miniSite.getMiniSite("/"+miniSite.getSiteId());
        json = new JSONObject(miniSite.getResponse());
        Assert.assertEquals(json.getString("name"),"VladTests");
        Assert.assertEquals(json.getString("phone_number"),"+380939999998");
        Assert.assertEquals(json.getString("extra_contact_data"),"инфо11");
        Assert.assertEquals(json.getString("about_description"),"<p>тестИнфо</p>");
        Assert.assertEquals(json.getString("about_title"),"заголов12");
        Assert.assertTrue(json.getBoolean("show_contacts"));
        Assert.assertTrue(json.getBoolean("show_about_company"));
        Assert.assertEquals(json.getString("status"),"ACTIVE");

        miniSite.setStatusCode(400);
        //name is empty
        body = "{\n" +
                "  \"name\": \"\",\n" +
                "  \"phone\": \"+380939999988\",\n" +
                "  \"show_contacts\": false,\n" +
                "  \"show_about_company\": false,\n" +
                "  \"extra_contact_data\": \"sfsd166\",\n" +
                "  \"about_title\": \"заголов1111666\",\n" +
                "  \"about_description\": \"<p>тестИнфо1111</p>\"\n" +
                "}";
        miniSite.editMiniSite(body);

        miniSite.setStatusCode(204);
        miniSite.deleteMiniSite(miniSite.getSiteId());

        logFinishTest("updateMiniSiteNegative");
    }

    @Test
    void addProductForMiniSitePositive(){
        logStartTest("addProductForMiniSitePositive");

        miniSite.setStatusCode(200);
        body = "{\n" +
                "\"name\": \"VladTests\",\n" +
                "\"phone\": \"+380939999998\",\n" +
                "\"show_contacts\": true,\n" +
                "\"show_about_company\": true,\n" +
                "\"extra_contact_data\": \"инфо11\",\n" +
                "\"about_title\": \"заголов12\",\n" +
                "\"about_description\": \"<p>тестИнфо</p>\"\n" +
                "}";
        miniSite.createMiniSite(body);

        productMiniSite.setStatusCode(200);
        body = "{\n" +
                "  \"name\": \"рубашка\",\n" +
                "  \"price\": 12,\n" +
                "  \"sku\": \"qqq\",\n" +
                "  \"position\": 1,\n" +
                "  \"description\": \"тестПродукта\",\n" +
                "  \"units\": \"шт.\"\n" +
                "}";
        productMiniSite.addProductForMiniSite(body, miniSite.getSiteId());
        productMiniSite.getProductForMiniSite(miniSite.getSiteId());

        miniSite.setStatusCode(204);
        miniSite.deleteMiniSite(miniSite.getSiteId());


        logFinishTest("addProductForMiniSitePositive");
    }

    @Test
    void addProductForMiniSiteNegative(){
        logStartTest("addProductForMiniSiteNegative");

        miniSite.setStatusCode(200);
        body = "{\n" +
                "\"name\": \"VladTests\",\n" +
                "\"phone\": \"+380939999998\",\n" +
                "\"show_contacts\": true,\n" +
                "\"show_about_company\": true,\n" +
                "\"extra_contact_data\": \"инфо11\",\n" +
                "\"about_title\": \"заголов12\",\n" +
                "\"about_description\": \"<p>тестИнфо</p>\"\n" +
                "}";
        miniSite.createMiniSite(body);

        body = "{\n" +
                "  \"name\": \"рубашка\",\n" +
                "  \"price\": 12,\n" +
                "  \"sku\": \"qqq\",\n" +
                "  \"position\": 1,\n" +
                "  \"description\": \"тестПродукта\",\n" +
                "  \"units\": \"шт.\"\n" +
                "}";
        productMiniSite.addProductForMiniSite(body, miniSite.getSiteId());

        productMiniSite.setStatusCode(400);
        //Дубль sku
        productMiniSite.addProductForMiniSite(body, miniSite.getSiteId());

        //name is empty
        body = "{\n" +
                "  \"name\": \"\",\n" +
                "  \"price\": 12,\n" +
                "  \"sku\": \"qqq\",\n" +
                "  \"position\": 1,\n" +
                "  \"description\": \"тестПродукта\",\n" +
                "  \"units\": \"шт.\"\n" +
                "}";
        productMiniSite.addProductForMiniSite(body, miniSite.getSiteId());

        //price is null
        body = "{\n" +
                "  \"name\": \"Рубашка\",\n" +
                "  \"price\": null,\n" +
                "  \"sku\": \"qqq\",\n" +
                "  \"position\": 1,\n" +
                "  \"description\": \"тестПродукта\",\n" +
                "  \"units\": \"шт.\"\n" +
                "}";
        productMiniSite.addProductForMiniSite(body, miniSite.getSiteId());

        //sku is empty
        body = "{\n" +
                "  \"name\": \"Рубашка\",\n" +
                "  \"price\": 12,\n" +
                "  \"sku\": \"\",\n" +
                "  \"position\": 1,\n" +
                "  \"description\": \"тестПродукта\",\n" +
                "  \"units\": \"шт.\"\n" +
                "}";
        productMiniSite.addProductForMiniSite(body, miniSite.getSiteId());

        //position is null
        body = "{\n" +
                "  \"name\": \"Рубашка\",\n" +
                "  \"price\": 12,\n" +
                "  \"sku\": \"qqq\",\n" +
                "  \"position\": null,\n" +
                "  \"description\": \"тестПродукта\",\n" +
                "  \"units\": \"шт.\"\n" +
                "}";
        productMiniSite.addProductForMiniSite(body, miniSite.getSiteId());

        //tests mandatory fields
        //name
        body = "{\n" +
                "  \"price\": 12,\n" +
                "  \"sku\": \"qqq\",\n" +
                "  \"position\": 1,\n" +
                "  \"description\": \"тестПродукта\",\n" +
                "  \"units\": \"шт.\"\n" +
                "}";
        productMiniSite.addProductForMiniSite(body, miniSite.getSiteId());

        //price
        body = "{\n" +
                "  \"name\": \"рубашка\",\n" +
                "  \"sku\": \"qqq\",\n" +
                "  \"position\": 1,\n" +
                "  \"description\": \"тестПродукта\",\n" +
                "  \"units\": \"шт.\"\n" +
                "}";
        productMiniSite.addProductForMiniSite(body, miniSite.getSiteId());

        //sku
        body = "{\n" +
                "  \"name\": \"рубашка\",\n" +
                "  \"price\": 12,\n" +
                "  \"position\": 1,\n" +
                "  \"description\": \"тестПродукта\",\n" +
                "  \"units\": \"шт.\"\n" +
                "}";
        productMiniSite.addProductForMiniSite(body, miniSite.getSiteId());

        //position
        body = "{\n" +
                "  \"name\": \"рубашка\",\n" +
                "  \"price\": 12,\n" +
                "  \"sku\": \"qqq\",\n" +
                "  \"description\": \"тестПродукта\",\n" +
                "  \"units\": \"шт.\"\n" +
                "}";
        productMiniSite.addProductForMiniSite(body, miniSite.getSiteId());

        //units
        body = "{\n" +
                "  \"name\": \"рубашка\",\n" +
                "  \"price\": 12,\n" +
                "  \"sku\": \"qqq\",\n" +
                "  \"position\": 1,\n" +
                "  \"description\": \"тестПродукта\",\n" +
                "}";
        productMiniSite.addProductForMiniSite(body, miniSite.getSiteId());

        //description
        body = "{\n" +
                "  \"name\": \"рубашка\",\n" +
                "  \"price\": 12,\n" +
                "  \"sku\": \"qqq\",\n" +
                "  \"position\": 1,\n" +
                "  \"units\": \"шт.\"\n" +
                "}";
        productMiniSite.addProductForMiniSite(body, miniSite.getSiteId());

        miniSite.setStatusCode(204);
        miniSite.deleteMiniSite(miniSite.getSiteId());


        logFinishTest("addProductForMiniSiteNegative");
    }

    @Test
    void changeStatusProductForMiniSitePositive(){
        logStartTest("changeStatusProductForMiniSitePositive");

        miniSite.setStatusCode(200);
        body = "{\n" +
                "\"name\": \"VladTests\",\n" +
                "\"phone\": \"+380939999998\",\n" +
                "\"show_contacts\": true,\n" +
                "\"show_about_company\": true,\n" +
                "\"extra_contact_data\": \"инфо11\",\n" +
                "\"about_title\": \"заголов12\",\n" +
                "\"about_description\": \"<p>тестИнфо</p>\"\n" +
                "}";
        miniSite.createMiniSite(body);

        productMiniSite.setStatusCode(200);
        body = "{\n" +
                "  \"name\": \"рубашка\",\n" +
                "  \"price\": 12,\n" +
                "  \"sku\": \"qqq\",\n" +
                "  \"position\": 1,\n" +
                "  \"description\": \"тестПродукта\",\n" +
                "  \"units\": \"шт.\"\n" +
                "}";
        productMiniSite.addProductForMiniSite(body, miniSite.getSiteId());

        String [] status = {"DRAFT", "DELETED","ACTIVE"};
        for (int i = 0; i < status.length; i++){
            productMiniSite.setStatusCode(204);
            productMiniSite.changeStatusProduct(status[i], miniSite.getSiteId(), productMiniSite.getProductId());
            productMiniSite.setStatusCode(200);
            productMiniSite.getProductForMiniSite(miniSite.getSiteId());
            json = new JSONObject(productMiniSite.getResponse());
            Assert.assertEquals( json.getJSONArray("products").getJSONObject(0).getString("status"),status[i]);
        }

        miniSite.setStatusCode(204);
        miniSite.deleteMiniSite(miniSite.getSiteId());


        logFinishTest("changeStatusProductForMiniSitePositive");
    }

    @Test
    void changeStatusProductForMiniSiteNegative(){
        logStartTest("changeStatusProductForMiniSiteNegative");

        miniSite.setStatusCode(200);
        body = "{\n" +
                "\"name\": \"VladTests\",\n" +
                "\"phone\": \"+380939999998\",\n" +
                "\"show_contacts\": true,\n" +
                "\"show_about_company\": true,\n" +
                "\"extra_contact_data\": \"инфо11\",\n" +
                "\"about_title\": \"заголов12\",\n" +
                "\"about_description\": \"<p>тестИнфо</p>\"\n" +
                "}";
        miniSite.createMiniSite(body);

        body = "{\n" +
                "  \"name\": \"рубашка\",\n" +
                "  \"price\": 12,\n" +
                "  \"sku\": \"qqq\",\n" +
                "  \"position\": 1,\n" +
                "  \"description\": \"тестПродукта\",\n" +
                "  \"units\": \"шт.\"\n" +
                "}";
        productMiniSite.addProductForMiniSite(body, miniSite.getSiteId());
        String old_prodID = productMiniSite.getProductId();

        String [] status = {"DRAFT1", ""};
        for (int i = 0; i < status.length; i++){
            productMiniSite.setStatusCode(400);
            productMiniSite.changeStatusProduct(status[i], miniSite.getSiteId(), productMiniSite.getProductId());
        }

        //Не существующий ид продукта
        productMiniSite.changeStatusProduct("DRAFT", miniSite.getSiteId(), "8705797b-e469-4e1a-9526-4d899f307fb6");

        miniSite.setStatusCode(204);
        miniSite.deleteMiniSite(miniSite.getSiteId());

        //Продукт ИД от другого магазина
        miniSite.setStatusCode(200);
        body = "{\n" +
                "\"name\": \"VladTests\",\n" +
                "\"phone\": \"+380939999998\",\n" +
                "\"show_contacts\": true,\n" +
                "\"show_about_company\": true,\n" +
                "\"extra_contact_data\": \"инфо11\",\n" +
                "\"about_title\": \"заголов12\",\n" +
                "\"about_description\": \"<p>тестИнфо</p>\"\n" +
                "}";
        miniSite.createMiniSite(body);

        body = "{\n" +
                "  \"name\": \"рубашка\",\n" +
                "  \"price\": 12,\n" +
                "  \"sku\": \"qqq\",\n" +
                "  \"position\": 1,\n" +
                "  \"description\": \"тестПродукта\",\n" +
                "  \"units\": \"шт.\"\n" +
                "}";
        productMiniSite.setStatusCode(200);
        productMiniSite.addProductForMiniSite(body, miniSite.getSiteId());
        productMiniSite.setStatusCode(400);
        productMiniSite.changeStatusProduct("DRAFT", miniSite.getSiteId(), old_prodID);

        miniSite.setStatusCode(204);
        miniSite.deleteMiniSite(miniSite.getSiteId());

        logFinishTest("changeStatusProductForMiniSiteNegative");
    }

}
