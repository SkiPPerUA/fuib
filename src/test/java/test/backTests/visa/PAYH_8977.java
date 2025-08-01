package test.backTests.visa;

import org.example.qaTransactionTeam.BaseTest;
import org.example.qaTransactionTeam.backEnd.visa.visaAllias.Allias;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PAYH_8977 extends BaseTest {
    //Провести тесты Visa Alias

    long account = 1000000000024532l;
    String guid = "57a4b5a4c2b70472f306f300090516a792103459832555985346658a5d4342";

    long mobile = 935567970l;
    Allias allias = new Allias();
    JSONObject json;
    JSONArray array;
    int testNumber = 1;

    @Test
    public void craetePositiveMobileMandatoryFields(){

        logStartTest("craetePositiveMobileMandatoryFields");
        String body = "{\n" +
                "    \"country\": \"UA\",\n" +
                "    \"recipient_last_name\": \"S.\",\n" +
                "    \"recipient_first_name\": \"Vlad\",\n" +
                "    \"recipient_primary_account_number\": \""+account+testNumber+"\",\n" +
                "    \"guid\": \""+guid+""+testNumber+""+"\",\n" +
                "    \"issuer_name\": \"FUIB\",\n" +
                "    \"card_type\": \"Visa Classic\",\n" +
                "    \"expiry_date\": \"2023-11\",\n" +
                "    \"consent_date_time\": \"2023-08-30 10:12:12\",\n" +
                "    \"alias_type\": \"01\",\n" +
                "    \"alias\": \"380933943736\"\n" +
                "\t    }";
        logger.info("Account = "+account+testNumber);
        logger.info("Guid = "+guid+""+testNumber+"");
        allias.create(body);
        json = new JSONObject(allias.getResponse());
        Assert.assertEquals(json.getString("error_code"),"00");
        Assert.assertEquals(json.getJSONObject("data").getString("guid"),guid+""+testNumber+"");
        testNumber++;

        logFinishTest("craetePositiveMobileMandatoryFields");

    }

    @Test
    public void craetePositiveMobileAllFields(){

        logStartTest("craetePositiveMobileAllFields");
        String body = "{\n" +
                "    \"country\": \"UA\",\n" +
                "    \"recipient_last_name\": \"Test\",\n" +
                "    \"recipient_middle_name\": \"Tiktok\",\n" +
                "    \"city\": \"Kiev\",\n" +
                "    \"address2\": \"Region 1\",\n" +
                "    \"recipient_first_name\": \"Vlad\",\n" +
                "    \"recipient_primary_account_number\": \""+account+testNumber+"\",\n" +
                "    \"address1\": \"Street 1\",\n" +
                "    \"issuer_name\": \"UA Bank 1\",\n" +
                "    \"postal_code\": \"00111\",\n" +
                "    \"card_type\": \"Visa Classic\",\n" +
                "\t  \"contact_email\": \"savchukvi12@gmail.com\",\n" +
                "    \"consent_date_time\": \"2021-09-20 18:12:12\",\n" +
                "    \"alias_type\": \"01\",\n" +
                "    \"guid\": \""+guid+""+testNumber+""+"\",\n" +
                "    \"alias\": \"0933943736\",\n" +
                "    \"contact_phone_number\": \"088888888888\",\n" +
                "    \"expiry_date\": \"2022-11\",\n" +
                "    \"is_default\": \"true\",\n" +
                "    \"payment_type\": \"PAN\",\n" +
                "    \"recipient_first_name_local\": \"Vlad11\",\n" +
                "    \"recipient_last_name_local\": \"Тест\",\n" +
                "    \"recipient_middle_name_local\": \"Тик-Ток\"  \n" +
                "}";
        logger.info("Account = "+account+testNumber);
        logger.info("Guid = "+guid+""+testNumber+"");
        allias.create(body);
        json = new JSONObject(allias.getResponse());
        Assert.assertEquals(json.getString("error_code"),"00");
        Assert.assertEquals(json.getJSONObject("data").getString("guid"),guid+""+testNumber+"");
        testNumber++;
        logFinishTest("craetePositiveMobileAllFields");

    }

    @Test
    public void craeteNegativeMobileMandatoryFieldsSamePAN(){

        logStartTest("craeteNegativeMobileMandatoryFieldsSamePAN");
        craetePositiveMobileMandatoryFields();
        String body = "{\n" +
                "    \"country\": \"UA\",\n" +
                "    \"recipient_last_name\": \"T.\",\n" +
                "    \"recipient_first_name\": \"Vlad\",\n" +
                "    \"recipient_primary_account_number\": \""+account+(testNumber-1)+"\",\n" +
                "    \"guid\": \""+guid+""+testNumber+""+"\",\n" +
                "    \"issuer_name\": \"TestAutomation\",\n" +
                "    \"card_type\": \"Visa Classic\",\n" +
                "    \"consent_date_time\": \"2021-09-20 18:12:12\",\n" +
                "    \"alias_type\": \"01\",\n" +
                "    \"expiry_date\": \"2022-11\", "+
                "    \"alias\": \"0935556677\"\n" +
                "\t    }";
        logger.info("Account = "+account+(testNumber-1));
        logger.info("Guid = "+guid+""+testNumber+"");
        allias.create(body);
        json = new JSONObject(allias.getResponse());
        Assert.assertEquals(json.getString("error_code"),"VA02");
        Assert.assertEquals(json.getJSONObject("error_message").getString("reason"),"validation-error");
        Assert.assertEquals(json.getJSONObject("error_message").getString("message"),"VAD-1050 Record Exists: recipientPrimaryAccountNumber");
        testNumber++;
        logFinishTest("craeteNegativeMobileMandatoryFieldsSamePAN");

    }

    @Test
    public void craeteNegativeInvalidMobile(){

        logStartTest("craeteNegativeInvalidMobile");
        String body = "{\n" +
                "    \"country\": \"UA\",\n" +
                "    \"recipient_last_name\": \"T.\",\n" +
                "    \"recipient_first_name\": \"Vlad\",\n" +
                "    \"recipient_primary_account_number\": \""+account+testNumber+"\",\n" +
                "    \"guid\": \""+guid+""+testNumber+""+"\",\n" +
                "    \"issuer_name\": \"TestAutomation\",\n" +
                "    \"card_type\": \"Visa Classic\",\n" +
                "    \"consent_date_time\": \"2021-09-20 18:12:12\",\n" +
                "    \"alias_type\": \"01\",\n" +
                "    \"alias\": \"+0935556677\"\n" +
                "\t    }";
        logger.info("Account = "+account+testNumber);
        logger.info("Guid = "+guid+""+testNumber+"");
        allias.create(body);
        json = new JSONObject(allias.getResponse());
        Assert.assertEquals(json.getString("error_code"),"VA02");
        Assert.assertEquals(json.getJSONObject("error_message").getString("reason"),"validation-error");
        Assert.assertEquals(json.getJSONObject("error_message").getString("message"),"VAD-1010 Invalid value: alias");
        testNumber++;
        logFinishTest("craeteNegativeInvalidMobile");

    }

    @Test
    public void craeteNegativeInvalidAcc444555(){

        logStartTest("craeteNegativeInvalidAcc444555");
        String body = "{\n" +
                "    \"country\": \"UA\",\n" +
                "    \"recipient_last_name\": \"T.\",\n" +
                "    \"recipient_first_name\": \"Vlad\",\n" +
                "    \"recipient_primary_account_number\": \"4445559630567162\",\n" +
                "    \"guid\": \""+guid+""+testNumber+""+"\",\n" +
                "    \"issuer_name\": \"TestAutomation\",\n" +
                "    \"card_type\": \"Visa Classic\",\n" +
                "    \"consent_date_time\": \"2021-09-20 18:12:12\",\n" +
                "    \"alias_type\": \"01\",\n" +
                "    \"alias\": \"0935556677\"\n" +
                "\t    }";
        logger.info("Account = 4445559630567162");
        logger.info("Guid = "+guid+""+testNumber+"");
        allias.create(body);
        json = new JSONObject(allias.getResponse());
        Assert.assertEquals(json.getString("error_code"),"VA02");
        Assert.assertEquals(json.getJSONObject("error_message").getString("reason"),"validation-error : validation-error");
        Assert.assertEquals(json.getJSONObject("error_message").getString("message"),"VAD-1160 AccountLookup Error- API Validation Error: 3001. Invalid Account.");
        testNumber++;
        logFinishTest("craeteNegativeInvalidAcc444555");

    }

    @Test
    public void craeteNegativeInvalidAccInActive(){

        logStartTest("craeteNegativeInvalidAccInActive");
        String body = "{\n" +
                "    \"country\": \"UA\",\n" +
                "    \"recipient_last_name\": \"T.\",\n" +
                "    \"recipient_first_name\": \"Vlad\",\n" +
                "    \"recipient_primary_account_number\": \"4294504000979078\",\n" +
                "    \"guid\": \""+guid+""+testNumber+""+"\",\n" +
                "    \"issuer_name\": \"TestAutomation\",\n" +
                "    \"card_type\": \"Visa Classic\",\n" +
                "    \"consent_date_time\": \"2021-09-20 18:12:12\",\n" +
                "    \"alias_type\": \"01\",\n" +
                "    \"alias\": \"0935556677\"\n" +
                "\t    }";
        logger.info("Account = 4294504000979078");
        logger.info("Guid = "+guid+""+testNumber+"");
        allias.create(body);
        json = new JSONObject(allias.getResponse());
        Assert.assertEquals(json.getString("error_code"),"VA02");
        Assert.assertEquals(json.getJSONObject("error_message").getString("reason"),"validation-error : validation-error");
        Assert.assertEquals(json.getJSONObject("error_message").getString("message"),"VAD-1160 AccountLookup Error- API Validation Error: 3001. Invalid Account.");
        testNumber++;
        logFinishTest("craeteNegativeInvalidAccInActive");

    }

    @Test
    public void craeteNegativeMoreThan16digsInAcc(){

        logStartTest("craeteNegativeMoreThan16digsInAcc");
        String body = "{\n" +
                "    \"country\": \"UA\",\n" +
                "    \"recipient_last_name\": \"T.\",\n" +
                "    \"recipient_first_name\": \"Vlad\",\n" +
                "    \"recipient_primary_account_number\": \"42945040009790781\",\n" +
                "    \"guid\": \""+guid+""+testNumber+""+"\",\n" +
                "    \"issuer_name\": \"TestAutomation\",\n" +
                "    \"card_type\": \"Visa Classic\",\n" +
                "    \"consent_date_time\": \"2021-09-20 18:12:12\",\n" +
                "    \"alias_type\": \"01\",\n" +
                "    \"alias\": \"0935556677\"\n" +
                "\t    }";
        logger.info("Account = 42945040009790781");
        logger.info("Guid = "+guid+""+testNumber+"");
        allias.create(body);
        json = new JSONObject(allias.getResponse());
        Assert.assertEquals(json.getString("error_code"),"VA02");
        Assert.assertEquals(json.getJSONObject("error_message").getString("reason"),"validation-error");
        Assert.assertEquals(json.getJSONObject("error_message").getString("message"),"VAD-1180 Invalid account number: recipientPrimaryAccountNumber");
        testNumber++;
        logFinishTest("craeteNegativeMoreThan16digsInAcc");

    }

    @Test
    public void getPositiveMobile(){

        logStartTest("getPositiveMobile");
        craetePositiveMobileMandatoryFields();
        logger.info("Guid = "+guid+""+(testNumber-1)+"");
        allias.status(guid+(testNumber-1));
        json = new JSONObject(allias.getResponse());
        Assert.assertEquals(json.getString("error_code"),"00");
        Assert.assertEquals(json.getJSONObject("data").getString("status"),"ACTIVE");
        Assert.assertEquals(json.getJSONObject("data").getJSONArray("aliases").getJSONObject(0).getString("alias"),"0"+mobile+(testNumber-1)+"");
        array = json.getJSONObject("data").getJSONArray("aliases");
        Assert.assertEquals(array.length(),1);
        testNumber++;
        logFinishTest("getPositiveMobile");

    }

    @Test
    public void getNegativeWrongGuid(){

        logStartTest("getNegativeWrongGuid");
        logger.info("Guid = 57a4b5a4c2b70472f306f300099515a789092348899999975343637a4d3201");
        allias.status("57a4b5a4c2b70472f306f300099515a789092348899999975343637a4d3201");
        json = new JSONObject(allias.getResponse());
        Assert.assertEquals(json.getString("error_code"),"VA01");
        Assert.assertEquals(json.getJSONObject("error_message").getString("reason"),"not-found");
        Assert.assertEquals(json.getJSONObject("error_message").getString("message"),"VAD-1040 Record Not Found");
        testNumber++;
        logFinishTest("getNegativeWrongGuid");

    }

    @Test
    public void resolvePositiveMandatoryFields(){

        logStartTest("resolvePositiveMandatoryFields");
        //craetePositiveMobileMandatoryFields();
        //logger.info("Mobile = 0"+mobile+(testNumber-1)+"");
        String body = "{\n" +
                "    \"business_application_id\": \"PP\",\n" +
                "    \"account_look_up\": \"Y\",\n" +
                "    \"alias\": \"380930890162\"\n" +
                "   \t    }";
        allias.resolve(body);
        json = new JSONObject(allias.getResponse());
        Assert.assertEquals(json.getString("error_code"),"00");
        //Assert.assertEquals(json.getJSONObject("data").getString("recipient_primary_account_number"),""+account+(testNumber-1)+"");
        //testNumber++;
        logFinishTest("resolvePositiveMandatoryFields");

    }

    @Test
    public void resolvePositiveAllFields(){

        logStartTest("resolvePositiveAllFields");
        craetePositiveMobileMandatoryFields();
        logger.info("Mobile = 0"+mobile+(testNumber-1)+"");
        String body = "{\n" +
                "    \"business_application_id\": \"PP\",\n" +
                "\t \"account_look_up\": \"N\",\n" +
                "    \"alias\": \"0"+mobile+(testNumber-1)+"\",\n" +
                "\t\"invite_sms_parameters\":{\n" +
                "\t\t\"amount\":\"120\",\n" +
                "\t\t\"language\":\"en\",\n" +
                "\t\t\"sender_name\":\"vasya\"\n" +
                "\t} \t    \n" +
                "}";
        allias.resolve(body);
        json = new JSONObject(allias.getResponse());
        Assert.assertEquals(json.getString("error_code"),"00");
        Assert.assertEquals(json.getJSONObject("data").getString("recipient_primary_account_number"),""+account+(testNumber-1)+"");
        testNumber++;
        logFinishTest("resolvePositiveAllFields");

    }

    @Test
    public void resolvePositiveLookUpN(){

        logStartTest("resolvePositiveLookUpN");
        String body = "{\n" +
                "    \"business_application_id\": \"PP\",\n" +
                "\t \"account_look_up\": \"N\",\n" +
                "    \"alias\": \"0933943738\",\n" +
                "\t\"invite_sms_parameters\":{\n" +
                "\t\t\"amount\":\"120\",\n" +
                "\t\t\"language\":\"en\",\n" +
                "\t\t\"sender_name\":\"vasya\"\n" +
                "\t}\n" +
                "   \t    \n" +
                "}";
        allias.resolve(body);
        json = new JSONObject(allias.getResponse());
        Assert.assertEquals(json.getString("error_code"),"00");
        Assert.assertEquals(json.getJSONObject("data").getString("recipient_primary_account_number"),"4731219630567166");
        testNumber++;
        logFinishTest("resolvePositiveLookUpN");

    }

    @Test
    public void resolvePositiveLookUpY(){

        logStartTest("resolvePositiveLookUpY");
        String body = "{\n" +
                "    \"business_application_id\": \"PP\",\n" +
                "\t \"account_look_up\": \"Y\",\n" +
                "    \"alias\": \"0933943738\",\n" +
                "\t\"invite_sms_parameters\":{\n" +
                "\t\t\"amount\":\"120\",\n" +
                "\t\t\"language\":\"en\",\n" +
                "\t\t\"sender_name\":\"vasya\"\n" +
                "\t}\n" +
                "   \t    \n" +
                "}";
        allias.resolve(body);
        json = new JSONObject(allias.getResponse());
        Assert.assertEquals(json.getString("error_code"),"00");
        Assert.assertEquals(json.getJSONObject("data").getString("recipient_primary_account_number"),"4731219630567166");
        Assert.assertNotNull(json.getJSONObject("data").getJSONObject("account_look_up_info"));
        testNumber++;
        logFinishTest("resolvePositiveLookUpY");

    }

    @Test
    public void resolveNegativeWrongMobile(){

        logStartTest("resolveNegativeWrongMobile");
        logger.info("Mobile = 0977773334");
        String body = "{\n" +
                "    \"business_application_id\": \"PP\",\n" +
                "    \"alias\": \"00977773334\"\n" +
                "   \t    }";
        allias.resolve(body);
        json = new JSONObject(allias.getResponse());
        Assert.assertEquals(json.getString("error_code"),"VA99");
        Assert.assertEquals(json.getJSONObject("error_message").getString("message"),"No value present");
        testNumber++;
        logFinishTest("resolveNegativeWrongMobile");

    }

    @Test
    public void resolveNegativeWithoutMandatoryField(){

        logStartTest("resolveNegativeWithoutMandatoryField");
        String body = "{\n" +
                "    \"alias\": \"0753943738\"\n" +
                "   \t    }";
        allias.resolve(body);
        json = new JSONObject(allias.getResponse());
        Assert.assertEquals(json.getString("error_code"),"VA11");
        Assert.assertEquals(json.getJSONObject("error_message").getString("code"),"VAD-1000");
        Assert.assertEquals(json.getJSONObject("error_message").getString("reason"),"validation-error");
        Assert.assertEquals(json.getJSONObject("error_message").getString("message"),"Required Field missing: businessApplicationId");
        testNumber++;
        logFinishTest("resolveNegativeWithoutMandatoryField");

    }

    @Test
    public void resolveNegativeWrongBisinessAppId(){

        logStartTest("resolveNegativeWrongBisinessAppId");
        String body = "{\n" +
                "    \"business_application_id\": \"ZZ\",\n" +
                "    \"alias\": \"0753943738\"\n" +
                "   \t    }";
        allias.resolve(body);
        json = new JSONObject(allias.getResponse());
        Assert.assertEquals(json.getString("error_code"),"VA11");
        Assert.assertEquals(json.getJSONObject("error_message").getString("code"),"VAD-1010");
        Assert.assertEquals(json.getJSONObject("error_message").getString("reason"),"validation-error");
        Assert.assertEquals(json.getJSONObject("error_message").getString("message"),"Invalid value: businessApplicationId");
        testNumber++;
        logFinishTest("resolveNegativeWrongBisinessAppId");

    }

    @Test
    public void resolveNegativeWrongAliasType(){

        logStartTest("resolveNegativeWrongAliasType");
        String body = "{\n" +
                "    \"business_application_id\": \"PP\",\n" +
                "    \"alias\": \"s0953943738\"\n" +
                "   \t    }";
        allias.resolve(body);
        json = new JSONObject(allias.getResponse());
        Assert.assertEquals(json.getString("error_code"),"VA99");
        Assert.assertEquals(json.getJSONObject("error_message").getString("message"),"No value present");
        testNumber++;
        logFinishTest("resolveNegativeWrongAliasType");

    }

    @Test
    public void updatePositiveNewMobile(){
        logStartTest("updatePositiveNewMobile");
        craetePositiveMobileMandatoryFields();
        logger.info("Guid = "+guid+""+(testNumber)+"");
        logger.info("Old mobile = "+mobile+(testNumber-1)+"");
        long newMobile = mobile+testNumber;
        logger.info("New Mobile = "+newMobile);
        String bodyUpdate = "{\n" +
                "    \"guid\": \"57a4b5a4c2b70472f306f300090515a791102459832455975345638a5d43521\",\n" +
                "\t  \"alias_type\":\"01\",\n" +
                "\t\t\"alias\":\"380986061134\"\n" +
                "\t\n" +
                "}";
        allias.update(bodyUpdate);
        json = new JSONObject(allias.getResponse());
        Assert.assertEquals(json.getString("error_code"),"00");
        Assert.assertEquals(json.getJSONObject("data").getString("guid"),guid+""+(testNumber-1)+"");
        allias.status(guid+""+(testNumber-1));
        json = new JSONObject(allias.getResponse());
        Assert.assertEquals(json.getString("error_code"),"00");
        Assert.assertEquals(json.getJSONObject("data").getString("guid"),guid+""+(testNumber-1)+"");
        Assert.assertEquals(json.getJSONObject("data").getString("status"),"ACTIVE");
        Assert.assertEquals(json.getJSONObject("data").getJSONArray("aliases").getJSONObject(0).getString("alias"),String.valueOf(newMobile));
        String bodyResolve = "{\n" +
                "    \"business_application_id\": \"PP\",\n" +
                "    \"alias\": \""+newMobile+"\"\n" +
                "   \t    }";
        allias.resolve(bodyResolve);
        json = new JSONObject(allias.getResponse());
        Assert.assertEquals(json.getString("error_code"),"00");
        Assert.assertEquals(json.getJSONObject("data").getString("recipient_primary_account_number"),""+account+(testNumber-1)+"");
        testNumber++;
        logFinishTest("updatePositiveNewMobile");
    }

    @Test
    public void updatePositiveNewGuid(){
        logStartTest("updatePositiveNewGuid");
        craetePositiveMobileMandatoryFields();
        String oldGuid = ""+guid+""+(testNumber-1)+"";
        logger.info("Old guid = "+oldGuid);
        int oldTestNumber = (testNumber-1);
        testNumber++;
        String newGuid = ""+guid+""+testNumber+"";
        String bodyUpdate = "{\n" +
                "    \"guid\": \""+oldGuid+"\",\n" +
                " \"new_guid\":\""+newGuid+"\"" +
                "}";
        allias.update(bodyUpdate);
        json = new JSONObject(allias.getResponse());
        Assert.assertEquals(json.getString("error_code"),"00");
        Assert.assertEquals(json.getJSONObject("data").getString("guid"),newGuid);
        allias.status(oldGuid);
        json = new JSONObject(allias.getResponse());
        Assert.assertEquals(json.getString("error_code"),"00");
        Assert.assertEquals(json.getJSONObject("data").getString("guid"),oldGuid);
        Assert.assertEquals(json.getJSONObject("data").getString("status"),"DISABLED");
        allias.status(newGuid);
        json = new JSONObject(allias.getResponse());
        Assert.assertEquals(json.getString("error_code"),"00");
        Assert.assertEquals(json.getJSONObject("data").getString("guid"),newGuid);
        Assert.assertEquals(json.getJSONObject("data").getString("status"),"ACTIVE");
        Assert.assertEquals(json.getJSONObject("data").getJSONArray("aliases").getJSONObject(0).getString("alias"),"0"+mobile+oldTestNumber+"");
        testNumber++;
        logFinishTest("updatePositiveNewGuid");
    }

    @Test
    public void updatePositiveNewPan(){
        logStartTest("updatePositiveNewPan");
        craetePositiveMobileMandatoryFields();
        logger.info("Guid = "+guid+""+(testNumber)+"");
        String oldGuid = ""+guid+""+(testNumber-1)+"";
        logger.info("Old pan = "+account+(testNumber-1)+"");
        testNumber++;
        String bodyUpdate = "{\n" +
                "    \"guid\": \""+oldGuid+"\",\n" +
                "\t  \"recipient_primary_account_number\":\""+account+testNumber+"\",\n" +
                "\t\t\"issuer_name\":\"AutomationVlad\",\n" +
                "\t\t\"card_type\":\"Visa Classic\"\n" +
                "\t\n" +
                "}";
        allias.update(bodyUpdate);
        json = new JSONObject(allias.getResponse());
        Assert.assertEquals(json.getString("error_code"),"00");
        Assert.assertEquals(json.getJSONObject("data").getString("guid"),oldGuid);
        allias.status(oldGuid);
        json = new JSONObject(allias.getResponse());
        Assert.assertEquals(json.getString("error_code"),"00");
        Assert.assertEquals(json.getJSONObject("data").getString("guid"),oldGuid);
        Assert.assertEquals(json.getJSONObject("data").getString("status"),"ACTIVE");
        Assert.assertEquals(json.getJSONObject("data").getString("recipient_primary_account_number"),""+account+testNumber+"");
        testNumber++;
        logFinishTest("updatePositiveNewPan");
    }

    @Test
    public void updatePositiveNewCity(){
        logStartTest("updatePositiveNewCity");
        craetePositiveMobileMandatoryFields();
        logger.info("Guid = "+guid+""+(testNumber)+"");
        String bodyUpdate = "{\n" +
                "    \"guid\": \""+guid+""+(testNumber)+"\",\n" +
                "    \"city\":\"Город\""+
                "}";
        allias.update(bodyUpdate);
        json = new JSONObject(allias.getResponse());
        Assert.assertEquals(json.getString("error_code"),"00");
        Assert.assertEquals(json.getJSONObject("data").getString("guid"),""+guid+""+(testNumber)+"");
        testNumber++;
        logFinishTest("updatePositiveNewCity");
    }

    @Test
    public void updateNegativeWrongNumber(){
        logStartTest("updateNegativeWrongNumber");
        craetePositiveMobileMandatoryFields();
        logger.info("Guid = "+guid+""+(testNumber)+"");
        String bodyUpdate = "{\n" +
                "    \"guid\": \""+guid+""+(testNumber)+"\",\n" +
                "\t  \"alias_type\":\"01\",\n" +
                "\t\t\"alias\":\"+0977777777\"\n" +
                "\t\n" +
                "}";
        allias.update(bodyUpdate);
        json = new JSONObject(allias.getResponse());
        Assert.assertEquals(json.getString("error_code"),"VA03");
        Assert.assertEquals(json.getJSONObject("error_message").getString("reason"),"validation-error");
        Assert.assertEquals(json.getJSONObject("error_message").getString("message"),"VAD-1010 Invalid value: alias");
        testNumber++;
        logFinishTest("updateNegativeWrongNumber");
    }

    @Test
    public void updateNegativeWrongGUID(){
        logStartTest("updateNegativeWrongGUID");
        logger.info("Guid = "+guid+""+testNumber+"");
        String bodyUpdate = "{\n" +
                "    \"guid\": \""+guid+""+testNumber+"\",\n" +
                "\t  \"alias_type\":\"01\",\n" +
                "\t\t\"alias\":\"0977777777\"\n" +
                "\t\n" +
                "}";
        allias.update(bodyUpdate);
        json = new JSONObject(allias.getResponse());
        Assert.assertEquals(json.getString("error_code"),"VA03");
        Assert.assertEquals(json.getJSONObject("error_message").getString("reason"),"not-found");
        Assert.assertEquals(json.getJSONObject("error_message").getString("message"),"VAD-1040 Record Not Found");
        testNumber++;
        logFinishTest("updateNegativeWrongGUID");
    }

    @Test
    public void updateNegativeInvalidAcc444555(){
        logStartTest("updateNegativeInvalidAcc444555");
        craetePositiveMobileMandatoryFields();
        logger.info("Guid = "+guid+""+(testNumber)+"");
        String bodyUpdate = "{\n" +
                "    \"guid\": \""+guid+""+(testNumber)+"\",\n" +
                "\t  \"recipient_primary_account_number\":\""+account+testNumber+"\",\n" +
                "\t\t\"issuer_name\":\"AutomationVlad\",\n" +
                "\t\t\"card_type\":\"Visa Classic\"\n" +
                "\t\n" +
                "}";
        allias.update(bodyUpdate);
        json = new JSONObject(allias.getResponse());
        Assert.assertEquals(json.getString("error_code"),"VA03");
        Assert.assertEquals(json.getJSONObject("error_message").getString("reason"),"validation-error : validation-error");
        Assert.assertEquals(json.getJSONObject("error_message").getString("message"),"VAD-1160 AccountLookup Error- API Validation Error: 3001. Invalid Account.");
        testNumber++;
        logFinishTest("updateNegativeInvalidAcc444555");
    }

    @Test
    public void updateNegativeInactiveAcc(){
        logStartTest("updateNegativeInactiveAcc");
        craetePositiveMobileMandatoryFields();
        logger.info("Guid = "+guid+""+(testNumber)+"");
        String bodyUpdate = "{\n" +
                "    \"guid\": \""+guid+""+(testNumber)+"\",\n" +
                "\t  \"recipient_primary_account_number\":\""+account+testNumber+"\",\n" +
                "\t\t\"issuer_name\":\"AutomationVlad\",\n" +
                "\t\t\"card_type\":\"Visa Classic\"\n" +
                "\t\n" +
                "}";
        allias.update(bodyUpdate);
        json = new JSONObject(allias.getResponse());
        Assert.assertEquals(json.getString("error_code"),"VA03");
        Assert.assertEquals(json.getJSONObject("error_message").getString("reason"),"validation-error");
        Assert.assertEquals(json.getJSONObject("error_message").getString("message"),"VAD-1180 Invalid account number: recipientPrimaryAccountNumber");
        testNumber++;
        logFinishTest("updateNegativeInactiveAcc");
    }

    @Test
    public void deletePositive(){
        logStartTest("deletePositive");
        craetePositiveMobileMandatoryFields();
        logger.info("Guid = "+guid+""+(testNumber-1)+"");
        String bodyDelete = "{\n" +
                "    \"guid\": \""+guid+(testNumber-1)+"\",\n" +
                "\"alias\": \""+mobile+(testNumber-1)+"\"\n" +
                "}";
        allias.delete(bodyDelete);
        json = new JSONObject(allias.getResponse());
        Assert.assertEquals(json.getString("error_code"),"00");
        Assert.assertEquals(json.getJSONObject("data").getString("guid"),""+guid+""+(testNumber-1)+"");
        allias.status(guid+""+(testNumber-1));
        json = new JSONObject(allias.getResponse());
        Assert.assertEquals(json.getString("error_code"),"00");
        Assert.assertEquals(json.getJSONObject("data").getString("status"),"DISABLED");
        String bodyResolve = "{\n" +
                "    \"business_application_id\": \"PP\",\n" +
                "    \"alias\": \"0"+mobile+(testNumber-1)+"\"\n" +
                "   \t    }";
        allias.resolve(bodyResolve);
        json = new JSONObject(allias.getResponse());
        Assert.assertEquals(json.getString("error_code"),"VA99");
        Assert.assertEquals(json.getJSONObject("error_message").getString("message"),"No value present");
        testNumber++;
        logFinishTest("deletePositive");
    }

    @Test
    public void deleteNegative(){
        logStartTest("deleteNegative");
        craetePositiveMobileMandatoryFields();
        logger.info("Guid = "+guid+""+(testNumber-1)+"");
        String bodyDelete = "{\n" +
                "    \"guid\": \""+guid+""+(testNumber-1)+"\",\n" +
                "\"alias\": \"0943946666\"" +
                "}";
        allias.delete(bodyDelete);
        json = new JSONObject(allias.getResponse());
        Assert.assertEquals(json.getString("error_code"),"VA04");
        Assert.assertEquals(json.getJSONObject("error_message").getString("reason"),"not-found");
        Assert.assertEquals(json.getJSONObject("error_message").getString("message"),"VAD-1040 Record Not Found");
        testNumber++;
        logFinishTest("deleteNegative");
    }

    @Test
    public void reportCheck(){
        logStartTest("reportCheck");
        craetePositiveMobileMandatoryFields();
        logger.info("Guid = "+guid+""+testNumber+"");
        String bodyReport = "{\n" +
                "    \"type\": \"CONSUMER\"\n" +
                "}";
        allias.makeReport(bodyReport);
        json = new JSONObject(allias.getResponse());
        Assert.assertEquals(json.getString("error_code"),"00");
        StringBuffer curlReport = new StringBuffer(json.getJSONObject("data").getString("report_location"));
        curlReport.delete(0,61);
        allias.getReport(curlReport.toString());
        json = new JSONObject(allias.getResponse());
        Assert.assertEquals(json.getString("error_code"),"00");
        testNumber++;
        logFinishTest("reportCheck");
    }

}
