package org.example.qaTransactionTeam.backEnd.mobyPay;

import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.example.qaTransactionTeam.backEnd.token.Trans_token_payhub;
import org.example.qaTransactionTeam.backEnd.utils.Configs;
import org.json.JSONException;
import org.json.JSONObject;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import static io.restassured.RestAssured.given;

class MobyPayTrans {

    private static final Logger logger = Logger.getLogger(MobyPayTrans.class);
    private String response;
    private String id;
    public static int responseCode = 200;
    private int threeDS;
    private String merchant_config_id = "cab290f8-5460-4cbd-a0a8-2799bdce8fed";
    //private String merchant_config_id = "d2aaa362-1c86-4057-b916-3a0d3d5071d1";
    private Trans_token_payhub token;
    {
        try {
            token = new Trans_token_payhub();
            //token = new TokenPayHub("svc_ph_Ccuxalsxt", "Lamx!yAgp9sA7VRiReHwQd2jiXFEpFL3", Configs.MOBYPAY_CLIENT,"https://rlyeh.payhub.com.ua"); //PROD
        } catch (JSONException e) {
            logger.error(e);
        }
    }

    protected void makeTrans(boolean hold, String amount,String payer) throws JSONException {

        String external = UUID.randomUUID().toString();
        String body = "{\n" +
                "  \"amount\": "+amount+",\n" +
                "  \"external_id\": \""+external+"\",\n" +
                "  \"client_ip\": \"127.0.0.1\",\n" +
                "  \"description\": \"description1234\",\n" +
                "  \"without_confirmation\": true,\n" +
                "   \"identification\": {\n" +
                "    \"requirements\": {\n" +
                "        \"recipient\":  {\n" +
                "            \"first_name\": \"Павло\",\n" +
                "            \"last_name\": \"Тичина\",\n" +
                "            \"amount\": 1234500,\n" +
                "            \"account_number\": \"UA213223130000026007233566001\"\n" +
                "        },\n" +
                "        \"sender\": {\n" +
                "            \"first_name\": \"Максим\",\n" +
                "            \"last_name\": \"Рильський\",\n" +
                "            \"account_number\": \"UA213223130000026007233566001\",\n" +
                "            \"reference_number\": 12345678\n" +
                "        },\n" +
                "        \"details\": {\n" +
                "            \"additional_message\": \"Авторе, пиши ще...\",\n" +
                "            \"source\": \"01\",\n" +
                "            \"submerchant_url\": \"https://submerchant-url.com\",\n" +
                "            \"independent_sales_organization_id\": \"3056715233\"\n" +
                "        }\n" +
                "    }\n" +
                "},"+
                "  \"merchant_config_id\": \""+merchant_config_id+"\",\n" +
                "\t\"hold\": "+hold+",\n" +
                "  \"payer\": {\n" +
                ""+payer+"" +
                "  }\n" +
                "}";

        response = given()
                 .contentType(ContentType.JSON)
                 .header("Authorization","Bearer "+token.getToken())
                 .body(body)
                 .when()
                 .post(Configs.PAYHUB_HOST +"/mobile-pay/transactions")
                 .then()
//                 .statusCode(responseCode)
                 .extract().response().asString();

        logger.info("Регистрация транзакции - "+response);
        JSONObject ob = new JSONObject(response);
        id = ob.getString("id");
    }

    protected void makeTrans(boolean hold, String amount,String payer,int threeDS) throws JSONException, IOException {

        String external = UUID.randomUUID().toString();
        logger.info("External ID = "+external);
        this.threeDS = threeDS;
        String three_ds = "";
        if (threeDS == 2){
            three_ds = "\"threed\": {\n" +
                    "              \"channel\": \"BRW\",\n" +
                    "              \"without_3ds\": false,\n" +
                    "              \"ip\": \"192.168.0.2\",\n" +
                    "              \"version\": \"2.2.0\",\n" +
                    "              \"javascript_enabled\": true,\n" +
                    "              \"fingerprint\": \"test\",\n" +
                    "              \"java_enabled\": false,\n" +
                    "              \"accept_header\": \"*\",\n" +
                    "              \"language\": \"RU\",\n" +
                    "              \"color_depth\": 32,\n" +
                    "              \"screen_width\": 1920,\n" +
                    "              \"screen_height\": 1080,\n" +
                    "              \"time_zone\": 120,\n" +
                    "              \"challenge_window_size\": \"02\",\n" +
                    "              \"return_url\": \"https://service.fuib.com\",\n" +
                    "              \"user_agent\": \"Gecko\"\n" +
                    "  },";
        }
        String body = "{\n" +
                "  \"amount\": "+amount+",\n" +
                "  \"external_id\": \""+external+"\",\n" +
                "  \"client_ip\": \"128.10.20.31\",\n" +
                "  \"decrypted_message\": \"{\\\"applicationPrimaryAccountNumber\\\":\\\"5355611173009543\\\",\\\"applicationExpirationDate\\\":\\\"260731\\\",\\\"currencyCode\\\":\\\"980\\\",\\\"transactionAmount\\\":100,\\\"deviceManufacturerIdentifier\\\":\\\"050110030273\\\",\\\"paymentDataType\\\":\\\"3DSecure\\\",\\\"paymentData\\\":{\\\"onlinePaymentCryptogram\\\":\\\"AHae/5F+sCmfAKc1v/sUAoABFA==\\\"}}\"," +
                "  \"without_confirmation\": false,\n" +
                "   \"identification\": {\n" +
                "    \"requirements\": {\n" +
                "        \"recipient\":  {\n" +
                "            \"first_name\": \"ТзОВ «Торговий Дім «Міст Експрес»\",\n" +
                "            \"last_name\": \"ТзОВ «Торговий Дім «Міст Експрес»\",\n" +
                "            \"amount\": \"2628\",\n" +
                "            \"account_number\": \"UA673252680000000002600838627\"\n" +
                "        },\n" +
                "        \"sender\": {\n" +
                "            \"first_name\": \"Максим\",\n" +
                "            \"last_name\": \"Рильський\",\n" +
                "            \"account_number\": \"UA213223130000026007233566001\",\n" +
                "            \"reference_number\": 12345678\n" +
                "        },\n" +
                "        \"details\": {\n" +
                "            \"additional_message\": \"\",\n" +
                "            \"source\": \"01\",\n" +
                "            \"submerchant_url\": \"https://meest-express.com.ua\",\n" +
                "            \"independent_sales_organization_id\": \"36152228\"\n" +
                "        }\n" +
                "    }\n" +
                "},"+
                "  \"description\": \"ычфы\",\n" +
                "  \"merchant_config_id\": \""+merchant_config_id+"\",\n" +
                "  \"additional_data\": {\n" +
                "       \"recipients\": [\n" +
                "           {\n" +
                "               \"first_name\": \"Taras\",\n" +
                "               \"last_name\": \"VOvk\",\n" +
                "\t\t\t\t\"amount\": 1000,\n" +
                "\t\t\t\t\"account_number\": \"UA1234567654323456\",\n" +
                "\t\t\t\t\"independent_sales_organization_id\": \"0123456789\",\n" +
                "\t\t\t\t\"merchant_url\": \"Vsssakddddd\",\n" +
                "\t\t\t\t\"payment_url\": \"ddddd\"\n" +
                "\t\t\t},\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"first_name\": \"Taras\",\n" +
                "\t\t\t\t\"last_name\": \"VOvk\",\n" +
                "\t\t\t\t\"amount\": 1000,\n" +
                "\t\t\t\t\"account_number\": \"UA1234567654323456\",\n" +
                "\t\t\t\t\"independent_sales_organization_id\": \"0123456789\",\n" +
                "\t\t\t\t\"merchant_url\": \"Vsssakddddd\",\n" +
                "\t\t\t\t\"payment_url\": \"ddddd\"\n" +
                "\t\t\t}\n" +
                "\t\t],\n" +
                "\t\t\"sender\": {\n" +
                "\t\t\t\"first_name\": \"Taras\",\n" +
                "\t\t\t\"last_name\": \"Vovk\"\n" +
                "\t\t}\n" +
                "\t}," +
                "\t\"hold\": "+hold+",\n" +
                three_ds +
                "  \"payer\": {\n" +
                ""+payer+"" +
                "  }\n" +
                "}";

        //System.out.println(body);
        response = given()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer "+token.getToken())
                .body(body)
                .when()
                .post(Configs.PAYHUB_HOST +"/mobile-pay/transactions")
                .then()
                //.statusCode(responseCode)
                .extract().response().asString();

        logger.info("Регистрация транзакции - "+response);
        JSONObject ob = new JSONObject(response);
        id = ob.getString("id");
        System.out.println(id);
        if (ob.getString("status").equals("PENDING") && !ob.getJSONObject("threed").getString("mode").equals("iframe-hidden")) {
            createIFramePereq(ob.getJSONObject("threed").getString("acs_url"), ob.getJSONObject("threed").getString("c_req"));
            //Ожидание прохождения 3дс
            try {
                Thread.sleep(20000);
                response = given()
                        .contentType(ContentType.JSON)
                        .header("Authorization", "Bearer " + token.getToken())
                        .body("{\n" +
                                "  \"client_ip\": \"192.168.0.2\",\n" +
                                "  \"c_res\": \"eyJhY3NUcmFuc0lEIjoiZWNhZWFmMjAtNDZlYy00MWVjLThiM2MtODNlOTc2NDM5OTc0IiwidHJhbnNTdGF0dXMiOiJZIiwidGhyZWVEU1NlcnZlclRyYW5zSUQiOiJlYzIwMDNiMC00NmVjLTQxZWMtOGIzYy1lMmVkYmVkNDQ2M2UiLCJtZXNzYWdlVHlwZSI6IkNSZXMiLCJtZXNzYWdlVmVyc2lvbiI6IjIuMS4wIn0\"\n" +
                                "}")
                        .when()
                        .put(Configs.PAYHUB_HOST + "/mobile-pay/transactions/" + id + "/3ds")
                        .then()
                        .statusCode(responseCode)
                        .extract().response().asString();
            } catch (InterruptedException e) {
                logger.error(e);
            }
        }else if (ob.getString("status").equals("PENDING") && ob.getJSONObject("threed").getString("mode").equals("iframe-hidden")){
            //createHiddenIFrame(ob.getJSONObject("threed").getString("acs_url"), ob.getJSONObject("threed").getString("tds_method_data"));
            threeDs_complete(id,ob.getJSONObject("threed").getString("tds_method_data"));
        }
    }

    protected void makeTrans(boolean hold, String amount,String payer, String identification, int threeDS) throws JSONException, IOException {

        String external = UUID.randomUUID().toString();
        logger.info("External ID = "+external);
        this.threeDS = threeDS;
        String three_ds = null;
        if (threeDS == 2){
            three_ds = "\"threed\": {\n" +
                    "              \"channel\": \"BRW\",\n" +
                    "              \"without_3ds\": false,\n" +
                    "              \"ip\": \"192.168.0.2\",\n" +
                    "              \"version\": \"2.1.0\",\n" +
                    "              \"fingerprint\": \"test\",\n" +
                    "              \"java_enabled\": false,\n" +
                    "              \"accept_header\": \"*\",\n" +
                    "              \"language\": \"RU\",\n" +
                    "              \"color_depth\": 32,\n" +
                    "              \"screen_width\": 1920,\n" +
                    "              \"screen_height\": 1080,\n" +
                    "              \"time_zone\": 120,\n" +
                    "              \"challenge_window_size\": \"02\",\n" +
                    "              \"return_url\": \"https://service.fuib.com\",\n" +
                    "              \"user_agent\": \"Gecko\"\n" +
                    "  },\n";
        }
        String body = "{\n" +
                "  \"amount\": "+amount+",\n" +
                "  \"external_id\": \""+external+"\",\n" +
                "  \"client_ip\": \"127.0.0.1\",\n" +
                "  \"without_confirmation\": false,\n" +
                "  \"merchant_config_id\": \""+merchant_config_id+"\",\n" +
                "\t\"hold\": "+hold+",\n" +
                three_ds +
                "  \"payer\": {\n" +
                ""+payer+"" +
                "  },\n" +
                identification +
                "  }\n" +
                "}";


        System.out.println(body);
        response = given()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer "+token.getToken())
                .body(body)
                .when()
                .post(Configs.PAYHUB_HOST +"/mobile-pay/transactions")
                .then()
//                .statusCode(responseCode)
                .extract().response().asString();

        logger.info("Регистрация транзакции - "+response);
        JSONObject ob = new JSONObject(response);
        id = ob.getString("id");
        createIFramePereq(ob.getJSONObject("threed").getString("acs_url"),ob.getJSONObject("threed").getString("c_req"));
        //Ожидание прохождения 3дс
        try {
            Thread.sleep(20000);
            response = given()
                    .contentType(ContentType.JSON)
                    .header("Authorization","Bearer "+token.getToken())
                    .body("{\n" +
                            "  \"client_ip\": \"192.168.0.2\",\n" +
                            "  \"c_res\": \"eyJhY3NUcmFuc0lEIjoiZWNhZWFmMjAtNDZlYy00MWVjLThiM2MtODNlOTc2NDM5OTc0IiwidHJhbnNTdGF0dXMiOiJZIiwidGhyZWVEU1NlcnZlclRyYW5zSUQiOiJlYzIwMDNiMC00NmVjLTQxZWMtOGIzYy1lMmVkYmVkNDQ2M2UiLCJtZXNzYWdlVHlwZSI6IkNSZXMiLCJtZXNzYWdlVmVyc2lvbiI6IjIuMS4wIn0\"\n" +
                            "}")
                    .when()
                    .put(Configs.PAYHUB_HOST +"/mobile-pay/transactions/"+id+"/3ds")
                    .then()
                    .statusCode(responseCode)
                    .extract().response().asString();
        } catch (InterruptedException e) {
            logger.error(e);
        }
    }

    protected void makeTrans(String body, int threeDS) throws JSONException, IOException {

        String external = UUID.randomUUID().toString();
        logger.info("External ID = "+external);
        this.threeDS = threeDS;
        String body1 = "{  \"external_id\": \""+external+"\",\n" +
                body+"}";
        //System.out.println(body);
        response = given()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer "+token.getToken())
                .body(body1)
                .when()
                .post(Configs.PAYHUB_HOST +"/mobile-pay/transactions")
                .then()
//                .statusCode(responseCode)
                .extract().response().asString();

        logger.info("Регистрация транзакции - "+response);
        JSONObject ob = new JSONObject(response);
        id = ob.getString("id");
        createIFramePereq(ob.getJSONObject("threed").getString("acs_url"),ob.getJSONObject("threed").getString("c_req"));
        //Ожидание прохождения 3дс
        try {
            Thread.sleep(20000);
            response = given()
                    .contentType(ContentType.JSON)
                    .header("Authorization","Bearer "+token.getToken())
                    .body("{\n" +
                            "  \"client_ip\": \"192.168.0.2\",\n" +
                            "  \"c_res\": \"eyJhY3NUcmFuc0lEIjoiZWNhZWFmMjAtNDZlYy00MWVjLThiM2MtODNlOTc2NDM5OTc0IiwidHJhbnNTdGF0dXMiOiJZIiwidGhyZWVEU1NlcnZlclRyYW5zSUQiOiJlYzIwMDNiMC00NmVjLTQxZWMtOGIzYy1lMmVkYmVkNDQ2M2UiLCJtZXNzYWdlVHlwZSI6IkNSZXMiLCJtZXNzYWdlVmVyc2lvbiI6IjIuMS4wIn0\"\n" +
                            "}")
                    .when()
                    .put(Configs.PAYHUB_HOST +"/mobile-pay/transactions/"+id+"/3ds")
                    .then()
                    .statusCode(responseCode)
                    .extract().response().asString();
        } catch (InterruptedException e) {
            logger.error(e);
        }
    }

    public void status(){
        status(id);
    }

    public void status(String id){
        response = given()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer "+token.getToken())
                .when()
                .get(Configs.PAYHUB_HOST +"/mobile-pay/transactions/"+id)
                .then()
                .statusCode(responseCode)
                .extract().response().asString();

        logger.info("Инфо по транзакции - "+response);
    }

    public void complete_hold(String amountHold){
        response = given()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer "+token.getToken())
                .body("{\n" +
                        "  \"amount\": "+amountHold+"\n" +
                        "}")
                .when()
                .post(Configs.PAYHUB_HOST +"/mobile-pay/transactions/"+id+"/complete_hold")
                .then()
                .statusCode(responseCode)
                .extract().response().asString();

        logger.info("Завершение холда на сумму: "+amountHold+" - "+response);
    }

    public void refund(String amountRefund){
        refund(id,amountRefund);
    }

    public void refund(String id, String amountRefund){
        response = given()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer "+token.getToken())
                .body("{\n" +
                        "  \"amount\": "+amountRefund+"\n" +
                        "}")
                .when()
                .post(Configs.PAYHUB_HOST +"/mobile-pay/transactions/"+id+"/refund")
                .then()
//                .statusCode(responseCode)
                .extract().response().asString();

        logger.info("Завершение рефанда на сумму: "+amountRefund+" - "+response);
    }

    public String getResponse() {
        return response;
    }

    private void createHiddenIFrame(String url,String tds_method_data) throws IOException {
        String htmlpareq = "<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\n" +
                "<html>\n" +
                "<body onload=\"document.getElementById('acs').submit()\">\n" +
                "<iframe name=\"area\" width=\"0\" height=\"0\" border=\"0\"></iframe>\n" +
                "<form id=\"acs\" action=\""+url+"\" target=\"area\" method=\"post\">\n" +
                "<input type=\"hidden\" id=\"threeDSMethodData\" value=\""+tds_method_data+"\" name=\"threeDSMethodData\">\n" +
                "</form>\n" +
                "</body>\n" +
                "</html>";

        Files.write(Paths.get("/Users/user/Documents/Тесты/hiddenFrame.html"), htmlpareq.getBytes(StandardCharsets.UTF_8));
        Desktop desktop = Desktop.getDesktop();
        desktop.open(new File("/Users/user/Documents/Тесты/hiddenFrame.html"));
    }

    private void createIFramePereq(String url,String pareq) throws IOException {
        if(threeDS==1) {
            String htmlpareq = "<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\n" +
                    "<html>\n" +
                    "  <body onload=\"document.getElementById('acs').submit()\">\n" +
                    "    <form id=\"acs\" method=\"post\" action=\"" + url + "\">\n" +
                    "      <input style=\"display:none\" type=\"submit\">\n" +
                    "      <input type=\"hidden\" id=\"MD\" value=\"md\" name=\"MD\">\n" +
                    "      <input type=\"hidden\" id=\"TermUrl\" value=\"https://service.fuib.com/\" name=\"TermUrl\">\n" +
                    "      <input type=\"hidden\" id=\"PaReq\" value=\"" + pareq + "\" name=\"PaReq\">\n" +
                    "      </form>\n" +
                    "  </body>\n" +
                    "</html>";

            Files.write(Paths.get("/Users/user/Documents/Тесты/acs.html"), htmlpareq.getBytes(StandardCharsets.UTF_8));
            Desktop desktop = Desktop.getDesktop();
            desktop.open(new File("/Users/user/Documents/Тесты/acs.html"));
        }else if (threeDS == 2){
            String htmlCreq = "<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\n" +
                    "<html>\n" +
                    "  <body onload=\"document.getElementById('acs').submit()\">\n" +
                    "    <form id=\"acs\" method=\"post\" action=\""+url+"\">\n" +
                    "      <input style=\"display:none\" type=\"submit\">\n" +
                    "      <input type=\"hidden\" id=\"creq\" value=\""+pareq+"\" name=\"creq\">\n" +
                    "      </form>\n" +
                    "  </body>\n" +
                    "</html>";

            Files.write(Paths.get("/Users/user/Documents/Тесты/creq.html"), htmlCreq.getBytes(StandardCharsets.UTF_8));
            Desktop desktop = Desktop.getDesktop();
            desktop.open(new File("/Users/user/Documents/Тесты/creq.html"));
        }
    }

    public void threeDs_complete(String id, String threed_data){
        response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token.getToken())
                .body("{\n" +
                        "  \"client_ip\": \"192.168.0.2\",\n" +
                        "  \"threed_data\": \""+threed_data+"\"\n" +
                        "}")
                .when()
                .put(Configs.PAYHUB_HOST + "/mobile-pay/transactions/" + id + "/3ds")
                .then()
                .statusCode(responseCode)
                .extract().response().asString();
        logger.info("threeDs_complete -> "+response);
    }

    private void complete_trans(){
        response = given()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer "+token.getToken())
                .body("{\n" +
                        "  \"client_ip\": \"192.168.0.2\",\n" +
                        "  \"c_res\": \"eyJhY3NUcmFuc0lEIjoiZWNhZWFmMjAtNDZlYy00MWVjLThiM2MtODNlOTc2NDM5OTc0IiwidHJhbnNTdGF0dXMiOiJZIiwidGhyZWVEU1NlcnZlclRyYW5zSUQiOiJlYzIwMDNiMC00NmVjLTQxZWMtOGIzYy1lMmVkYmVkNDQ2M2UiLCJtZXNzYWdlVHlwZSI6IkNSZXMiLCJtZXNzYWdlVmVyc2lvbiI6IjIuMS4wIn0\"\n" +
                        "}")
                .when()
                .put(Configs.PAYHUB_HOST +"/mobile-pay/transactions/"+id+"/3ds")
                .then()
                .statusCode(responseCode)
                .extract().response().asString();

    }

}
