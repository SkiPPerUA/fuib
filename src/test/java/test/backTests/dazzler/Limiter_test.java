package test.backTests.dazzler;

import org.example.qaTransactionTeam.BaseTest;
import org.example.qaTransactionTeam.backEnd.dazzler.Limiter;
import org.testng.annotations.Test;

@Test
public class Limiter_test extends BaseTest {

    Limiter limiter = new Limiter();

    public void positive_getLimits(){
        limiter.getClientLimits("{\n" +
                "\"amount\": 101,\n" +
                "\"kind\": \"NBU_MEMO\",\n" +
                "\"direction\": \"EXPENSE\",\n" +
                "\"customer_id\":\"7326479\",\n" +
                "\"ekb_id\": \"11278210\"\n" +
                "}");
    }
}
