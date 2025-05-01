package org.example.qaTransactionTeam.backEnd.pushes;

import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.json.JSONException;



import static io.restassured.RestAssured.given;

public class PushesControl {

    private Logger logger = Logger.getLogger(PushesControl.class);
    private String accessToken;
    private String environment;

    public PushesControl(String environment) throws JSONException {
        PushesToken token = new PushesToken(PushConfigs.CONFIG_PUSH[0], PushConfigs.CONFIG_PUSH[1], PushConfigs.CONFIG_PUSH[2], PushConfigs.CONFIG_PUSH[3], PushConfigs.CONFIG_PUSH[4]);
        token.setEnvironment(environment);
        token.requestToken_change_push();
        this.accessToken = token.getAccess_token();
        this.environment = token.getEnvironment();
    }

    public void getPush(String part, String card){
        String request = given()
                .header("X-Flow-ID","7777q")
                .header("X-SYSTEMCODE","7272")
                .header("Authorization","Bearer "+accessToken)
                .when()
                .get("https://api."+environment+"-fuib.com/tsys/in-betweenerer/v1/protection-pushes/parts/"+part+"/cards/"+card+"")
                .then()
                .statusCode(200)
                .extract().response().prettyPrint();

        logger.info("Ответ на Get - "+request);
    }

    public void deletePush(String part, String card){
        String request = given()
                .header("X-Flow-ID","7777q")
                .header("X-SYSTEMCODE","7272")
                .header("Authorization","Bearer "+accessToken)
                .when()
                .delete("https://api."+environment+"-fuib.com/tsys/in-betweenerer/v1/protection-pushes/parts/"+part+"/cards/"+card+"")
                .then()
                .statusCode(204)
                .extract().response().prettyPrint();

        logger.info("Ответ на Delete - "+request);

    }

    public void postPush(String part, String card, String body){
        String request = given()
                .header("X-Flow-ID","7777q")
                .header("X-SYSTEMCODE","7272")
                .header("Authorization","Bearer "+accessToken)
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post("https://api."+environment+"-fuib.com/tsys/in-betweenerer/v1/protection-pushes/parts/"+part+"/cards/"+card+"")
                .then()
                .statusCode(201)
                .extract().response().prettyPrint();

        logger.info("Ответ на Post - "+request);
    }

    public void putPush(String part, String card, String body){
        String request = given()
                .header("X-Flow-ID","7777q")
                .header("X-SYSTEMCODE","7272")
                .header("Authorization","Bearer "+accessToken)
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .put("https://api."+environment+"-fuib.com/tsys/in-betweenerer/v1/protection-pushes/parts/"+part+"/cards/"+card+"")
                .then()
                .statusCode(200)
                .extract().response().prettyPrint();

        logger.info("Ответ на Put - "+request);
    }

}
