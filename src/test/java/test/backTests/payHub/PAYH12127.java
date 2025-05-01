package test.backTests.payHub;

import org.example.qaTransactionTeam.BaseTest;
import org.example.qaTransactionTeam.backEnd.payHub.CardResidence;
import org.example.qaTransactionTeam.backEnd.utils.Card;
import org.example.qaTransactionTeam.backEnd.utils.Card_param;
import org.example.qaTransactionTeam.backEnd.utils.Cards_data;
import org.example.qaTransactionTeam.backEnd.utils.Configs;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PAYH12127 extends BaseTest {
    //Розробити RPC-клієнт для взаємодії з мікросервісом для визначення резидентності карти

    @Test
    void positiveTest(){
        CardResidence cardResidence = new CardResidence();
        cardResidence.checkResidenceCard(Cards_data.getData(Card.FUIB_VISA, Card_param.pan));
        Assert.assertEquals(cardResidence.response,"true");
        cardResidence.checkResidenceCard(Cards_data.getData(Card.FUIB_MC, Card_param.pan));
        Assert.assertEquals(cardResidence.response,"true");
    }

    @Test
    void negativeTest(){
        CardResidence cardResidence = new CardResidence();
        cardResidence.checkResidenceCard("4165988006212079");
        //Assert.assertEquals(cardResidence.response,"false");
    }
}
