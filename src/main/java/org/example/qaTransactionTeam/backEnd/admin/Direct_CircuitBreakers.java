package org.example.qaTransactionTeam.backEnd.admin;

import io.restassured.http.ContentType;
import org.example.qaTransactionTeam.backEnd.token.Auth_token;
import org.example.qaTransactionTeam.backEnd.token.Trans_token_payhub;
import org.example.qaTransactionTeam.backEnd.utils.Restful;

import static io.restassured.RestAssured.given;

public class Direct_CircuitBreakers extends Restful {

    private static Auth_token token = new Trans_token_payhub();

    public void get_circuitBreakers(String type, String scheme){
        request(given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer "+token.getToken())
                .when()
                .get(token.getHost()+"/admin/direct-transfers/circuit-breakers?transfer_type="+type+"&processing_scheme="+scheme));
    }
}
