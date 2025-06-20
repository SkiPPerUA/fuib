package test.backTests.commissions;

import org.example.qaTransactionTeam.BaseTest;
import org.example.qaTransactionTeam.backEnd.commissions.Commissions;
import org.testng.annotations.Test;

@Test
public class CommCalculate extends BaseTest {

    Commissions commissions = new Commissions();

    public void positive(){
        commissions.commissionsCalculate("?service_id=41014&amount=2312");
    }

}
