package org.example.qaTransactionTeam.backEnd.admin;

import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.example.qaTransactionTeam.backEnd.token.Trans_token_payhub;
import org.example.qaTransactionTeam.backEnd.utils.Restful;

import static io.restassured.RestAssured.given;

public class Banks extends Restful {

    private static final Logger logger = Logger.getLogger(Banks.class);
    private final Trans_token_payhub token = new Trans_token_payhub();

    public void getCrossborderBins(){
        logger.info("Get crossborder-bins");
        request(given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJMU1dxSEYzWUF0UDkzMmNXdm5ESXBzOHhqaHRQeWxiN0JOV2prVWhqcVhrIn0.eyJleHAiOjE3NDcwNDI0MDksImlhdCI6MTc0NzA0MTUwOSwianRpIjoiZmRkZWY4YzMtYjI3My00ZmQ4LWFhMzAtMDQ4Mzk1NTY3MTdhIiwiaXNzIjoiaHR0cHM6Ly9hdXRoLnRlc3QtZnVpYi5jb20vYXV0aC9yZWFsbXMvcHVtYiIsImF1ZCI6WyJ0cmFuc2FjdGVyIiwiUEhUUk4iLCJQSEFETVAiLCJhY2NvdW50IiwicGF5aHViX2FkbWluIl0sInN1YiI6IjY3ZjZjMjQ2LWI4Y2YtNGU0NS05OGQ1LWM0ZTE1NGZlMzA1NyIsInR5cCI6IkJlYXJlciIsImF6cCI6InRyYW5zYWN0ZXIiLCJzZXNzaW9uX3N0YXRlIjoiNzI1NGM3YzQtNWNlMi00ZTUwLWIyMGUtYjk4NGRkOGRjOTA3IiwicmVhbG1fYWNjZXNzIjp7InJvbGVzIjpbIm9mZmxpbmVfYWNjZXNzIiwidW1hX2F1dGhvcml6YXRpb24iLCJkZWZhdWx0LXJvbGVzLXB1bWIiXX0sInJlc291cmNlX2FjY2VzcyI6eyJQSFRSTiI6eyJyb2xlcyI6WyJwaF90ZyJdfSwiUEhBRE1QIjp7InJvbGVzIjpbInBibF9vcCIsInRybl92aWV3IiwicGhwcl9wcmMiLCJwYmxfbW5nIiwiY2FiX2VkaWdfc2YiLCJwYmxfcDRwIiwicGJsX2FjcSIsInBibF9zdXAiLCJ0cm5fcnBydCIsInBoYWNxIiwicGhhY3FfbWdyIiwicGhwciJdfSwiYWNjb3VudCI6eyJyb2xlcyI6WyJtYW5hZ2UtYWNjb3VudCIsIm1hbmFnZS1hY2NvdW50LWxpbmtzIiwidmlldy1wcm9maWxlIl19LCJwYXlodWJfYWRtaW4iOnsicm9sZXMiOlsicGhhZG1fY3Rybl9pbmYiLCJjaGtfb3AiLCJwaGFkbV9jdHJuIiwicGhfb3B3cyIsInBoYWRtX2dycF9wbnRzIiwidm9jIiwicGhhZG1fY3Rybl9tZXJjaCIsImNuY2xfb3AiLCJwaGFkbV9zcnZfbmMiLCJwaGFkbV9zcnZfdmlldyIsInBoYWRtX21lcmNoX2VkaXQiLCJwaF9leHRfY2RiIiwicGhhZG1fZ3JwX2NtbSIsInZvY19tbmciLCJwaGFkbV9jdHJuX2VkIiwicGhhZG1fZ3JwX3NydiIsInBoX2lwc19vcHIiLCJwaF9pcHNfYWRtIiwicGhhZG1fc3J2X3BiIiwiZXB0X3Byb2MiLCJlcHRfb3BzIiwicGhfc2Jfb3AiLCJlcnIiLCJyZWdfb3AiLCJlcHQiLCJwaGFkbV9tZXJjaCIsInBoX29wdyIsInBoX2hoX29wIiwicGhfb3ByIiwia3Z0X3NuZCIsInBoYWRtX3Nydl9tbmciLCJwaGFkbV9tZXJjaF92aWV3IiwicGhhZG1fc3J2X2VkaXQiXX19LCJzY29wZSI6IiIsInNpZCI6IjcyNTRjN2M0LTVjZTItNGU1MC1iMjBlLWI5ODRkZDhkYzkwNyIsIm1lcmNoYW50X2lkIjoiM2JmZGE2YTctMzZlZC00NDJiLWE4NDAtODBkNzVkMGZiN2M2IiwicHJlZmVycmVkX3VzZXJuYW1lIjoic3ZjX3BoX3Rlc3RfdHJuIiwiY2xpZW50X2lkIjoiNWFmMmQyM2EtMzQ4ZS00MjEyLTgyY2EtMjQ3YjcyNzY5MmQ4In0.Gh5UqUFOUUgZI-_IXQwCOd4uwX9V3cuq9blYewqO9IJ1B8jnTddusMuRBBEA-66MZvcdQXZg2zGqe6f-hyEOgJwwWyZYTpskMqSmc2Br8Y-vWvg1t9HVm2JOUARYc-BrTRvCYRl0AGNteo_MXMVFlaJWPyNWxnWTadQRgUWiBF0Pch-yXssawCNq4ylY0whAPKNpl3FP8N0HhDAY_epR2OtAul8q5a7yCNuaJwC_xpmcoFLyCMNwfRllHqbfFmDyxZZimubkJednTaB97A1Jregj3JT5-Oi2ssRbgL9h52UEkLfrBbWszF-Q5tsBeyQEg3ZGMTJgoXWr9KWVHVkfrw")
                .when()
                .get(token.getHost()+"/admin/banks/crossborder-bins"));
    }

    public void addCrossborderBins(String body){
        logger.info("Add crossborder-bins");
        request(given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer "+token.getToken())
                .body(body)
                .when()
                .post(token.getHost()+"/admin/banks/crossborder-bins"));
    }

    public void deleteCrossborderBins(String bin){
        logger.info("Delete crossborder-bins");
        request(given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer "+token.getToken())
                .when()
                .delete(token.getHost()+"/admin/banks/crossborder-bins/"+bin));
    }

}
