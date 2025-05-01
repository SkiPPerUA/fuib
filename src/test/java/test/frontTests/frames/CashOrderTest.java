package test.frontTests.frames;

import org.example.qaTransactionTeam.BaseTest;
import org.example.qaTransactionTeam.backEnd.utils.Card;
import org.example.qaTransactionTeam.backEnd.utils.Card_param;
import org.example.qaTransactionTeam.backEnd.utils.Cards_data;
import org.example.qaTransactionTeam.backEnd.utils.Configs;
import org.example.qaTransactionTeam.frontEnd.frames.CashOrders_1st_page;
import org.example.qaTransactionTeam.frontEnd.utils.Session;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Test
public class CashOrderTest extends BaseTest {

    int wait = 25000;

    public void currency_order(){
        new CashOrders_1st_page()
                .openPage("frames-ui/currency-order")
                .setPhoneBox("999999975")
                .setNameBox("Влад автотест")
                .setEmailBox("savchukvi12@gmail.com")
                .choiseCity("Вінниця")
                .choiseStreet(" вул. Миколи Оводова, 38 ")
                .clickNextButton()
                .choiseCurrency("EUR")
                .setCardBox(Cards_data.getData(Card.FUIB_MC, Card_param.pan),Cards_data.getData(Card.FUIB_MC, Card_param.expire_month),Cards_data.getData(Card.FUIB_MC, Card_param.expire_year),Cards_data.getData(Card.FUIB_MC, Card_param.cvv))
                .clickNextButton();
        wait_payment();
    }

    public void cash_order(){
        new CashOrders_1st_page()
                .openPage("frames-ui/cash-order")
                .setPhoneBox("933943736")
                .setNameBox("Влад автотест")
                .setEmailBox("ssss@ss.ss")
                .choiseCity("Вінниця")
                .choiseStreet(" вул. Миколи Оводова, 38 ")
                .clickNextButton()
                .changeCash("9000")
                .setCardBox(Cards_data.getData(Card.FUIB_MC, Card_param.pan),Cards_data.getData(Card.FUIB_MC, Card_param.expire_month),Cards_data.getData(Card.FUIB_MC, Card_param.expire_year),Cards_data.getData(Card.FUIB_MC, Card_param.cvv))
                .clickNextButton();
        wait_payment();
    }

    private void wait_payment(){
        try {
            Thread.sleep(wait);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @BeforeTest
    public void setConn(){
        Session.Session();
    }

    @AfterTest
    public void closeConn(){
        Session.closeSession();
    }
}
