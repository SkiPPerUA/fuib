package org.example.qaTransactionTeam.backEnd.payHub;

import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.example.qaTransactionTeam.backEnd.token.Trans_token_payhub;
import org.example.qaTransactionTeam.backEnd.payHub.frames.Frames;
import org.json.JSONException;

import java.util.UUID;

import static io.restassured.RestAssured.given;

public class Recurrent {

    private static final Logger logger = Logger.getLogger(Recurrent.class);
    private String response;
    private String externalId;
    private Trans_token_payhub token;

    {
        try {
            token = new Trans_token_payhub("svc_ph_test_ptrn", "quxS2&56xvatPZz66LKG8sJQHn3ZYVSA", "transacter");
        } catch (JSONException e) {
            logger.error(e);
        }
    }

    public Recurrent(String transactionId){

        externalId = UUID.randomUUID().toString();
        logger.info("External ID = "+externalId);
        logger.info("Transaction ID = "+transactionId);
        String body1 = "{\n" +
                "    \"amount\": 376,\n" +
                "    \"external_id\": \""+externalId+"\",\n" +
                "    \"payer\": {\n" +
                "        \"source\": \"RECURRENT_TRANSACTION\",\n" +
                "        \"transaction_id\": \""+transactionId+"\",\n" +
                "        \"recurring_expiry\": \"2030-01-01\",\n" +
                "        \"recurring_frequency\": 2\n" +
                "    },\n" +
                "    \"description\": \"Payment description\",\n" +
                "    \"short_description\": \"Short payment description\",\n" +
                "    \"client_ip\": \"121.40.16.41\",\n" +
                "    \"3ds2_supported\": true,\n" +
                "    \"params\": {\n" +
                "        \"recurrent\": \"true\"\n" +
                "    },\n" +
                "    \"merchant_config_id\": \"fe18e213-d70c-4f49-b6db-8c40aca019ca\",\n" +
                "    \"config_id\": \"ff7e604d-d12a-42fd-9d03-aece7678a260\"\n" +
                "}";

        response = given()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer "+token.getToken())
                .body(body1)
                .when()
                .post(token.getHost()+"/pga/transactions")
                .then()
//                .statusCode(200)
                .extract().response().asString();

        logger.info("Выполнение Recurrent - "+response);

    }

    public Recurrent(Frames trans){

        externalId = UUID.randomUUID().toString();
        logger.info("External ID = "+externalId);
        String body1 = "{\n" +
                "    \"amount\": 376,\n" +
                "    \"external_id\": \""+externalId+"\",\n" +
                "    \"payer\": {\n" +
                "        \"source\": \"RECURRENT_TRANSACTION\",\n" +
                "        \"transaction_id\": \""+trans.getTransactionId()+"\"\n" +
                "    },\n" +
                "    \"description\": \"Payment description\",\n" +
                "    \"short_description\": \"Short payment description\",\n" +
                "    \"client_ip\": \"121.40.16.41\",\n" +
                "    \"merchant_config_id\": \"219d8135-0295-40e1-9711-30a6ac3ecbf5\",\n" +
                "    \"config_id\": \"ff7e604d-d12a-42fd-9d03-aece7678a260\"\n" +
                "}";

        response = given()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer "+token.getToken())
                .body(body1)
                .when()
                .post(trans.getToken().getHost()+"/"+trans.getType()+"/transactions")
                .then()
                .statusCode(200)
                .extract().response().asString();

        logger.info("Выполнение Recurrent - "+response);

    }

}
