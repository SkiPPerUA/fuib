package org.example.qaTransactionTeam.backEnd.wine;

import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.example.qaTransactionTeam.backEnd.utils.Apiman;
import org.example.qaTransactionTeam.backEnd.utils.Restful;

import static io.restassured.RestAssured.given;

public class Wine extends Restful {

    private final Logger logger = Logger.getLogger(Wine.class);
    Apiman token = new Apiman("EKB", "manager");

    public void getClient(String ekb_id) {
        logger.info("Get client info from EKB");
        request(given()
                .headers("Authorization", "Bearer " + token.getToken())
                .headers("X-Username", "12")
                .headers("X-Systemcode", "12")
                .headers("X-Flow-ID", "12")
                .queryParams("is_param_codes_history", "true")
                .queryParams("aml_risk_links", "FIZ.AML_BIG_DEPOSIT_4,A01")
                .queryParams("is_aml_beneficials", "true")
                .queryParams("is_aml_stockholders", "true")
                .queryParams("param_codes", "Education,educatinst,ChildrenNumber,Segment_FIZ")
                .queryParams("wb_client_id", ekb_id)
                .get("https://core-dbgate.test-fuib.com/wine/v2/retail-customers")
        );
    }

    public void changeClient(String body) {
        logger.info("Change client info in EKB");
        Apiman token = new Apiman("EKB", "cust_opr");
        request(given()
                .contentType(ContentType.JSON)
                .headers("Authorization", "Bearer " + token.getToken())
                .headers("X-Username", "12")
                .headers("X-Systemcode", "12")
                .headers("X-Flow-ID", "12")
                .body(body)
                .patch("https://core-dbgate.test-fuib.com/wine/v2/retail-customers")
        );
    }
}

//https://confluence.fuib.com/pages/viewpage.action?pageId=188547108
//https://confluence.fuib.com/pages/viewpage.action?pageId=188550056
//    "{\n" +
//            "    \"wb_client_id\": 8531524,\n" +
//            "    \"wb_client_info\": {\n" +
//            "       \"addresses\": [\n" +
//            "        {\n" +
//            "            \"address_type\": 1,\n" +
//            "            \"postal_code\": \"01001\",\n" +
//            "            \"country_code\": \"804\",\n" +
//            "            \"province\": \"Київ\",\n" +
//            "            \"district\": \"\",\n" +
//            "            \"locality_type\": \"місто\",\n" +
//            "            \"address_locality_id\": null,\n" +
//            "            \"locality\": \"Київ\",\n" +
//            "            \"address_street_id\": null,\n" +
//            "            \"street_type\": \"вул.\",\n" +
//            "            \"street\": \"Лаврухіна\",\n" +
//            "            \"dwelling_type\": \"буд.\",\n" +
//            "            \"address_house_nums_id\": null,\n" +
//            "            \"house\": \"11\",\n" +
//            "            \"apartment_type\": null,\n" +
//            "            \"apartment\": \"203\",\n" +
//            "            \"date\": null,\n" +
//            "            \"main\": false,\n" +
//            "            \"status\": 1,\n" +
//            "            \"address_source\": null\n" +
//            "        },\n" +
//            "        {\n" +
//            "            \"address_type\": 2,\n" +
//            "            \"postal_code\": \"01001\",\n" +
//            "            \"country_code\": \"804\",\n" +
//            "            \"province\": \"Київ\",\n" +
//            "            \"district\": \"\",\n" +
//            "            \"locality_type\": \"місто\",\n" +
//            "            \"address_locality_id\": null,\n" +
//            "            \"locality\": \"Київ\",\n" +
//            "            \"address_street_id\": null,\n" +
//            "            \"street_type\": \"вул.\",\n" +
//            "            \"street\": \"Лаврухіна\",\n" +
//            "            \"dwelling_type\": \"буд.\",\n" +
//            "            \"address_house_nums_id\": null,\n" +
//            "            \"house\": \"11\",\n" +
//            "            \"apartment_type\": null,\n" +
//            "            \"apartment\": \"203\",\n" +
//            "            \"date\": null,\n" +
//            "            \"main\": false,\n" +
//            "            \"status\": 1,\n" +
//            "            \"address_source\": null\n" +
//            "        }\n" +
//            "    ]\n" +
//            "    }\n" +
//            "}"

