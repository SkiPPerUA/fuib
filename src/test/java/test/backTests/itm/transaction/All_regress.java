package test.backTests.itm.transaction;

import org.example.qaTransactionTeam.BaseTest;
import org.example.qaTransactionTeam.backEnd.transaction.typeTrans_itm.A2C;
import org.example.qaTransactionTeam.backEnd.transaction.typeTrans_itm.C2A;
import org.example.qaTransactionTeam.backEnd.transaction.typeTrans_itm.P2P;
import org.example.qaTransactionTeam.backEnd.transaction.typeTrans_itm.P4P;
import org.example.qaTransactionTeam.backEnd.utils.Card;
import org.testng.annotations.Test;

@Test
public class All_regress extends BaseTest {
    Card cardSender = Card.FUIB_MC;
    Card cardReceiver = Card.FUIB_MC;

    public void all_regress(){
        new A2C_regress().make_a2c(cardReceiver);
        new C2A_regress().make_c2a(cardSender);
        new P2P_regress().make_p2p(cardSender, cardReceiver);
        new P4P_regress().payment_test(cardSender, cardReceiver);
    }
}
