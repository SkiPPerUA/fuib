package test.backTests.mobyPay;

import org.example.qaTransactionTeam.BaseTest;
import org.example.qaTransactionTeam.backEnd.mobyPay.MobyTrans;
import org.testng.annotations.Test;

@Test
public class MobyPayStatus_test extends BaseTest {

    MobyTrans moby = new MobyTrans();

    public void positive(){
        moby.statusByExternalID("2196","0cdeedb9-abfd-4532-901d-4ae85b762e17");
    }
}
