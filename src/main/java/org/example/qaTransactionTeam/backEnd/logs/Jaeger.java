package org.example.qaTransactionTeam.backEnd.logs;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.example.qaTransactionTeam.backEnd.utils.Restful;
import org.json.JSONObject;
import static io.restassured.RestAssured.given;

public class Jaeger extends Restful {

    JSONObject json;
    String result = "";
    String traceID;
    int maxTryCount = 3;
    int tryCount = 0;
    private static final Logger logger = Logger.getLogger(Jaeger.class);

    public String find(String serviceName, String tags, String logName){
        RestAssured.useRelaxedHTTPSValidation();
        tryCount++;
        request(false,
                given()
                .auth().basic("savchukv","Sk1pper1029384756&")
                .contentType(ContentType.JSON)
                .queryParam("end",System.currentTimeMillis()*1000)
                .queryParam("limit","20")
                .queryParam("lookback","15m")
                .queryParam("service",serviceName)
                .queryParam("start",System.currentTimeMillis()*1000 - 900000000)
                .queryParam("tags",tags)
                .when()
                .get("https://ph-jaeger.test-fuib.com/jaeger/api/traces"));
        new JSONObject(getResponse()).getJSONArray("data").forEach(x->{
            if (x.toString().contains(logName)){
                traceID = new JSONObject(x.toString()).get("traceID").toString();
            }
        });

        if (traceID == null && (tryCount <= maxTryCount)){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            this.find(serviceName,tags,logName);
        }else if (traceID != null){
            logger.info("Jaeger ["+logName+"] -> https://ph-jaeger.test-fuib.com/jaeger/trace/"+traceID);
        }
        return traceID;
    }

    public String getLog(String id, String operName, String logName){
        RestAssured.useRelaxedHTTPSValidation();
        request(false,
                given()
                .auth().basic("savchukv","Sk1pper1029384756&")
                .contentType(ContentType.JSON)
                .when()
                .get("https://ph-jaeger.test-fuib.com/jaeger/api/v3/traces/"+id));
        json = new JSONObject(getResponse());

        json.getJSONObject("result").getJSONArray("resourceSpans").forEach(x->{
            if (x.toString().contains(operName)){
                JSONObject json = new JSONObject(x.toString());
                json.getJSONArray("scopeSpans").getJSONObject(0).getJSONArray("spans").getJSONObject(0)
                        .getJSONArray("events").forEach(y->{
                    if (y.toString().contains(logName)) {
                        result = new JSONObject(y.toString()).getJSONArray("attributes").getJSONObject(1).getJSONObject("value")
                                .get("stringValue").toString();
                    }
                });
            }
        });
        return result;
    }
}
