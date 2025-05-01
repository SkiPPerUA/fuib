package test.backTests.scriptsFotTests;

import org.example.qaTransactionTeam.BaseTest;
import org.example.qaTransactionTeam.backEnd.itm.UnlockCardsForTrans;
import org.example.qaTransactionTeam.backEnd.utils.Card;
import org.example.qaTransactionTeam.backEnd.utils.Card_param;
import org.example.qaTransactionTeam.backEnd.utils.Cards_data1;
import org.testng.annotations.Test;

import java.sql.SQLException;

public class UnlockCard extends BaseTest {

    @Test
    public void unlock() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        logStartTest("unlock");

        String [] cards = {Cards_data1.getData(Card.FUIB_MC, Card_param.token), Cards_data1.getData(Card.FUIB_VISA, Card_param.token)};

        UnlockCardsForTrans unlock = new UnlockCardsForTrans();
        for(int i = 0;i<cards.length;i++) {
            unlock.unlockCard(cards[i]);
        }
        logFinishTest("unlock");
    }
}
