package test.backTests.pga;

import org.example.qaTransactionTeam.BaseTest;
import org.example.qaTransactionTeam.backEnd.payHub.frames.AcquiringFrameRecurrent;
import org.testng.annotations.Test;

@Test
public class FrameRecurrent extends BaseTest {

    public void positive_test(){
        String body = "{\n" +
                "  \"initial_transaction_id\": \"8284cd68-2cc5-475e-923a-60e3ec2fca0c\",\n" +
                "  \"amount\": 101\n" +
                "}";
        new AcquiringFrameRecurrent("e1846c13-3224-4ee9-a110-577f1f0f8aab",body);
    }
}
