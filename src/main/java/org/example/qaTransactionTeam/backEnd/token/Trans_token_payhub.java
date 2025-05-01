package org.example.qaTransactionTeam.backEnd.token;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.example.qaTransactionTeam.backEnd.utils.Configs;
import org.json.JSONException;
import org.json.JSONObject;


import static io.restassured.RestAssured.given;

public class Trans_token_payhub implements Auth_token{

    private String token;
    private String response;
    private String host = Configs.PAYHUB_HOST;
    private String data;

    public Trans_token_payhub() throws JSONException {
        data = "{\n" +
                "\t\"login\": \""+Configs.PAYHUB_LOGIN +"\",\n" +
                "\t\"password\": \""+Configs.PAYHUB_PASSWORD +"\",\n" +
                "\t\"client\": \""+Configs.PAYHUB_CLIENT +"\"\n" +
                "}";
//        data = "{\n" +
//                "\t\"login\": \"svc_ph_trn2_t\",\n" +
//                "\t\"password\": \"HNviY3YJnEBTf&tSBnC3gRn4v%y&fn&B\",\n" + // a2c рефакторинг НА пумб
//                "\t\"client\": \""+Configs.PAYHUB_CLIENT +"\"\n" +
//                "}";
        create_token();
    }

    public Trans_token_payhub(String host) throws JSONException {
        this.host = host;
        data = "{\n" +
                "\t\"login\": \""+Configs.PAYHUB_LOGIN +"\",\n" +
                "\t\"password\": \""+Configs.PAYHUB_PASSWORD +"\",\n" +
                "\t\"client\": \""+Configs.PAYHUB_CLIENT +"\"\n" +
                "}";
        create_token();
    }

    public Trans_token_payhub(String login, String password, String client) throws JSONException {
        data = "{\n" +
                "\t\"login\": \""+login+"\",\n" +
                "\t\"password\": \""+password+"\",\n" +
                "\t\"client\": \""+client+"\"\n" +
                "}";
        create_token();
    }

    public Trans_token_payhub(String login, String password, String client, String host) throws JSONException {
        this.host = host;
        data = "{\n" +
                "\t\"login\": \""+login+"\",\n" +
                "\t\"password\": \""+password+"\",\n" +
                "\t\"client\": \""+client+"\"\n" +
                "}";
        create_token();
    }

    public Trans_token_payhub(boolean withClient) throws JSONException {
        data = "{\n" +
                "  \"params\": {\n" +
                "    \"login\": \"svc_ph_test_ib\",\n" +
                "    \"password\": \"ApBMQ7zx4F3K3TXyQkyXRHPp37CFtq\",\n" +
                "    \"client\": \"transacter\"\n" +
                "  },\n" +
                "  \"data\": {\n" +
                "  \"terminal_id\": \"ib_android\",\n" +
                "  \"client\": {\n" +
                "    \"id\": \"6241781\",\n" +  //6646497  //1004834 //544881 //6779548
                "    \"source\": \"SIRIUS\"\n" +
                "  }\n" +
                "  }\n" +
                "}";
//        data = "{\n" +
//                "  \"params\": {\n" +
//                "    \"login\": \"svc_ph_Hylrxrhvdd\",\n" +
//                "    \"password\": \"ecL7C7H6uRdgsKHBc8r3yAuJ#EW7ChVi\",\n" +
//                "    \"client\": \"transacter\"\n" +
//                "  }\n" +
//                "}";
        create_token();
        }

    public Trans_token_payhub(int client_id) throws JSONException {
        data = "{\n" +
                "  \"params\": {\n" +
                "    \"login\": \"svc_ph_test_ib\",\n" +
                "    \"password\": \"ApBMQ7zx4F3K3TXyQkyXRHPp37CFtq\",\n" +
                "    \"client\": \"transacter\"\n" +
                "  },\n" +
                "  \"data\": {\n" +
                "  \"client\": {\n" +
                "    \"id\": \""+client_id+"\",\n" +
                "    \"source\": \"SIRIUS\"\n" +
                "  }\n" +
                "  }\n" +
                "}";
        create_token();
    }


    public String getToken() {
        return token;
    }

    public String getHost() {
        return host;
    }

    @Override
    public void create_token() {
        RestAssured.useRelaxedHTTPSValidation();

        response = given()
                .contentType(ContentType.JSON)
                .body(data)
                .when()
                .post(host+"/auth/token")
                .then()
                .extract().response().asString();

        JSONObject ob = new JSONObject(response).getJSONObject("data");
        token = ob.getString("access_token");
    }

    @Override
    public String getAcqID() {
        return "";
    }
}
