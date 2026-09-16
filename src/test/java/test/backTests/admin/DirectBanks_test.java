package test.backTests.admin;

import org.example.qaTransactionTeam.BaseTest;
import org.example.qaTransactionTeam.backEnd.admin.DirectBanks;
import org.example.qaTransactionTeam.backEnd.utils.Card;
import org.example.qaTransactionTeam.backEnd.utils.Card_param;
import org.example.qaTransactionTeam.backEnd.utils.Cards_data;
import org.testng.annotations.Test;

@Test
public class DirectBanks_test extends BaseTest {

    DirectBanks api = new DirectBanks();

    public void checkCards_positive(){
        api.checkCards("MONOBANK", "4444034440000680");
    }
}
