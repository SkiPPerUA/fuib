package test.backTests.ronan;

import org.example.qaTransactionTeam.BaseTest;
import org.example.qaTransactionTeam.backEnd.ronan.DomesticCard;
import org.example.qaTransactionTeam.backEnd.utils.Card;
import org.example.qaTransactionTeam.backEnd.utils.Card_param;
import org.example.qaTransactionTeam.backEnd.utils.Cards_data;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckDomestic extends BaseTest {

    @Test
    void ukrCard(){
        logStartTest("ukrCard");
        DomesticCard domesticCard = new DomesticCard("http://localhost:8020");
        Assert.assertTrue(domesticCard.checkDomestic(Cards_data.getData(Card.FUIB_VISA, Card_param.pan)));
        logFinishTest("ukrCard");
    }

    @Test
    void notUkrCard(){
        logStartTest("notUkrCard");
        DomesticCard domesticCard = new DomesticCard("http://localhost:8020");
        Assert.assertFalse(domesticCard.checkDomestic("1111111"));
        logFinishTest("notUkrCard");
    }
}
