package test.backTests.productCatalog;


import io.restassured.http.ContentType;
import org.example.qaTransactionTeam.backEnd.utils.Apiman;
import org.example.qaTransactionTeam.backEnd.utils.BDas400;
import org.example.qaTransactionTeam.backEnd.utils.Configs;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


import java.sql.ResultSet;
import java.sql.SQLException;

import static io.restassured.RestAssured.given;

public class MT2727 {
    //Реализовать REST для получения ценовых параметров карточных продуктов из ICCS

    final String partId = "001";
    final String bin = "00100072";

    private Apiman token;

    @BeforeTest
    public void token() throws JSONException, ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        this.token = new Apiman("ITM", "crd_prd");
        BDas400.BDas400("ITMTST", Configs.ITMTST_ALL_NAME,Configs.ITMTST_ALL_PASSWORD);

    }

    @Test(priority = 0)
    public void sendRequest() throws SQLException, JSONException {

        String url = "https://api."+token.getEnvironment()+"-fuib.com/tsys/the-riddler/v1/card-products/parts/"+partId+"/bins/"+bin+"";

        String re = given()
                .contentType(ContentType.JSON)
                .header("X-Flow-ID", "VladAutoTest")
                .header("Authorization", "Bearer "+token.getToken())
                .when()
                .get(url)
                .then()
                .statusCode(200)
                .extract()
                .response().prettyPrint();

        JSONObject js = new JSONObject(re);
        double interest_rate_1 = js.getDouble("interest_rate_1");
        double interest_rate_2 = js.getDouble("interest_rate_2");
        double interest_rate_pastdu_1 = js.getDouble("interest_rate_pastdu_1");
        double interest_rate_pastdu_2 = js.getDouble("interest_rate_pastdu_2");
        double fee_pastdu = js.getDouble("fee_pastdu");
        double min_prct_bal = js.getDouble("min_prct_bal");

        ResultSet res = BDas400.callProcedure("CALL ITM22R.GETCRDPRMS('"+partId+"', '"+bin+"')");
        res.next();

        Assert.assertEquals(interest_rate_1,res.getDouble("INTRRATE1"));
        Assert.assertEquals(interest_rate_2,res.getDouble("INTRRATE2"));
        Assert.assertEquals(interest_rate_pastdu_1,res.getDouble("PASTDUERATE1"));
        Assert.assertEquals(interest_rate_pastdu_2,res.getDouble("PASTDUERATE2"));
        Assert.assertEquals(fee_pastdu,res.getDouble("PASTDUEFEE"));
        Assert.assertEquals(min_prct_bal,res.getDouble("MININSTPERC"));


    }

    @Test (priority = 1)
    public void getMetrics() throws JSONException {
        String res = given()
                .contentType(ContentType.JSON)
                .when()
                .get("https://api."+token.getEnvironment()+"-fuib.com/tsys/the-riddler/v1/metrics")
                .then()
                .extract().response().asString();

        JSONObject ob = new JSONObject(res);
        JSONArray arr = new JSONArray(ob.getString("names"));

        for (int i = 0; i < arr.length(); i++){
            String word = arr.getString(i);
            if (word.contains("card_products")){
                System.out.println("-------------------------------------------------");
                String rs = given()
                        .contentType(ContentType.JSON)
                        .when()
                        .get("https://api."+token.getEnvironment()+"-fuib.com/tsys/the-riddler/v1/metrics/"+arr.getString(i)+"")
                        .then()
                        .extract().response().prettyPrint();
            }
        }
    }

    @AfterTest
    public void CloseBDConn() throws SQLException {
        BDas400.closeConn();
    }
}
