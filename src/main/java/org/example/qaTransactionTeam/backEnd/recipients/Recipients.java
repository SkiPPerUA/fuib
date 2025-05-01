package org.example.qaTransactionTeam.backEnd.recipients;

import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.example.qaTransactionTeam.backEnd.token.Auth_token;
import org.example.qaTransactionTeam.backEnd.token.Trans_token_payhub;
import org.example.qaTransactionTeam.backEnd.utils.Restful;

import static io.restassured.RestAssured.given;

public class Recipients extends Restful {

    private final Logger logger = Logger.getLogger(Recipients.class);
    private Auth_token token = new Trans_token_payhub();

    public void search(String iban){
        logger.info("Search");
        request(given()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJMU1dxSEYzWUF0UDkzMmNXdm5ESXBzOHhqaHRQeWxiN0JOV2prVWhqcVhrIn0.eyJleHAiOjE3MzcwMjM3MjQsImlhdCI6MTczNzAyMjgyNCwianRpIjoiYjQ0MTgyZWMtNGRkYi00YTc3LTk5OGYtMjhiZWU5Mjk1ODkxIiwiaXNzIjoiaHR0cHM6Ly9hdXRoLnRlc3QtZnVpYi5jb20vYXV0aC9yZWFsbXMvcHVtYiIsImF1ZCI6WyJ0cmFuc2FjdGVyIiwiUlBPIiwiUEhUUk4iLCJJVE0iLCJCUFMiLCJHQlMiLCJPREIiLCJFS0IiLCJDT01DIiwiYWNjb3VudCJdLCJzdWIiOiJmY2Q1YjgzMy01MzJjLTQ4OTctYThlYy1jMzU3ZWRhOGM3ZTIiLCJ0eXAiOiJCZWFyZXIiLCJhenAiOiJ0cmFuc2FjdGVyIiwic2Vzc2lvbl9zdGF0ZSI6IjQ1YzdhYmFkLTMwZWItNGU2NC05MGQ2LWI1NjAyZGU4OTQyMyIsInJlYWxtX2FjY2VzcyI6eyJyb2xlcyI6WyJvZmZsaW5lX2FjY2VzcyIsInVtYV9hdXRob3JpemF0aW9uIl19LCJyZXNvdXJjZV9hY2Nlc3MiOnsiUlBPIjp7InJvbGVzIjpbImNyZF9yIiwicHVvX2ludCIsInZhX3IiXX0sIlBIVFJOIjp7InJvbGVzIjpbInBodW50ciIsInBoY29tIiwicGhfc2UiLCJwaF9jYl9vcCIsInBoX2N1X2V4IiwicGhfcG5fdGtuIiwicGhfc3dfcmVxIl19LCJJVE0iOnsicm9sZXMiOlsiY3JkX3IiLCJwdW9faW50Il19LCJ0cmFuc2FjdGVyIjp7InJvbGVzIjpbInZvY2FidWxhcmllcyIsInBoX3BwX2FkbSIsInBoX2RpbmYiLCJwaF9wcF9vcCJdfSwiQlBTIjp7InJvbGVzIjpbInB1b19pbnQiXX0sIkdCUyI6eyJyb2xlcyI6WyJjdXN0X3IiLCJwdW9faW50Il19LCJPREIiOnsicm9sZXMiOlsiY3JkX3IiLCJwdW9faW50IiwiYWNjX2xtdF9yIiwicGF5X2RvY19jcnQiLCJjcmRfYWNjX3IiLCJwYXlfZG9jX2RlbCJdfSwiRUtCIjp7InJvbGVzIjpbImN1c3RfciIsInB1b19pbnQiXX0sIkNPTUMiOnsicm9sZXMiOlsicHVvX2ludCJdfSwiYWNjb3VudCI6eyJyb2xlcyI6WyJtYW5hZ2UtYWNjb3VudCIsIm1hbmFnZS1hY2NvdW50LWxpbmtzIiwidmlldy1wcm9maWxlIl19fSwic2NvcGUiOiIiLCJzaWQiOiI0NWM3YWJhZC0zMGViLTRlNjQtOTBkNi1iNTYwMmRlODk0MjMiLCJtZXJjaGFudF9pZCI6IjEwNTQ2MTk3LTBkMmYtNDA1OS1iOWEyLWQwMWNiOTdlYmE2MSIsInByZWZlcnJlZF91c2VybmFtZSI6InN2Y190cHVvX3BoIn0.TSlTYXCF8Oxkcgos81jJpWN80oFJ0zXMNaC_gJBG5eVOM-jaG_joIteqcEIW5fqIvzLKR_9kWpq3BQeLTnQH8kSKu41MbEHYj-fkoIvBKrrK-vbKvgf4erPLD_tR5gf7Kx_YytOHA5leUNb05XubIIBlAqvoGXMO6eUZkv4Ggl5Yk2DVUkWvK9pp9IqKdf0BHRXYDRdw_xd3UQQDQqrQVizr6_F1xKOg6e8EA2iKAitDQDi_fJWBq8FhfjgcVtaywWuVPhQNBHwzAqbzPiLjUPXLEghTLsJq1tvhE6iPqImkdTvK8ywK46QCCq5GH9PCbf17i9duIGqs0tik9LYqfw")
                .header("X-Systemcode","1234")
                .header("X-Flow-id","123")
                .body("{\n" +
                        "    \"iban\": \""+iban+"\"\n" +
                        "}")
                .when()
                .post(token.getHost()+"/recipients/search")
        );
    }
}
