package test.backTests.pushes;
//Аутенфикация банка партнера


import io.restassured.http.ContentType;
import org.example.qaTransactionTeam.backEnd.utils.Mock;
import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static io.restassured.RestAssured.given;


public class TestAuthBankPartner {

    @Test
    public void testAuth(){

        //Перед тестом - нужно локально развернуть приложение ин-битвинер
        final String code = "bG9naW46cGFzc3dvcmQ=1";

        Mock.Mock(8182);
        Mock.getMock().stubFor(post(urlEqualTo("/v1/authenticationRequests")).willReturn(aResponse()
                .withHeader("Content-Type","application/json")
                .withBody("{\"refID\":\"563854092349681065\",\"responseResult\":\"ok\"}")));
    //011105971317
        String req = given()
                .contentType(ContentType.JSON)
                .body("\n" +
                        "{\n" +
                        "  \"ref_id\": \"563854092349681065\",\n" +
                       // "  \"pan\": \"ATM4NTQwOTIzNDk2ODEwNjUwkLWOfjnd/lkuckZvjxkAgfhVmuVFEZUW/PqE1WUytvnTei80inkMJM7+A42NDOUJa8oU9pLt7pZfOuUA92te\",\n" +
                        "  \"acquirer_bin\": \"414892\",\n" +
                        "  \"merchant_id\": \"9999999021\",\n" +
                        "  \"merchant_name\": \"Test Merchant\",\n" +
                        "  \"merchant_country\": \"203\",\n" +
                        "  \"merchant_url\": \"https://www.gpwebpay.cz\",\n" +
                        "  \"purchase_date\": \"2019-01-01T00:00:00.000Z\",\n" +
                        "  \"amount\": \"25555\",\n" +
                        "  \"currency\": \"196\",\n" +
                        "  \"when_expire\": 100,\n" +
                        "  \"client_info\": \"VEReq-Remote-Address\\u003dnull\\u0026User-Agent\\u003dnullPaReqc-cReq-Remote-Address\\u003dnull\",\n" +
                        "  \"3ds_protocol_version\": \"2.1.0\",\n" +
                        "  \"purchase_exponent\": \"2\",\n" +
                        "  \"external_id\": \"013366121327\"\n" +
                        "  \"masked_pan\": \"*****1231231\",\n" +
                        "  \"device_id\": \"000000245811\"\n" +
                        "}")
                .when()
                .post("http://localhost:8140/v1/authenticationRequests")
                .then()
                .statusCode(200)
                .extract()
                .response().asString();

        Mock.getMock().verify(postRequestedFor(urlEqualTo("/v1/authenticationRequests"))
                .withHeader("authorization",equalTo("Basic "+code+"")));


        final byte[] decoded = Base64.getDecoder().decode(code.getBytes());
        final String s = new String(decoded, StandardCharsets.UTF_8);
        System.out.println("Значение токена в хедере (разшифровка) - "+s);

        Mock.stopMock();


    }
}
