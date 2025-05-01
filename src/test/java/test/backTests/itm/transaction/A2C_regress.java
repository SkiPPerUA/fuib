package test.backTests.itm.transaction;

import org.example.qaTransactionTeam.BaseTest;
import org.example.qaTransactionTeam.backEnd.transaction.typeTrans_itm.A2C;
import org.example.qaTransactionTeam.backEnd.utils.Card;
import org.example.qaTransactionTeam.backEnd.utils.Card_param;
import org.example.qaTransactionTeam.backEnd.utils.Cards_data1;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class A2C_regress extends BaseTest {

    public String amount = "100";
    Map<String, String> body = new HashMap<>();

    @Test
    void a2c(){
        make_a2c(Card.FUIB_VISA);
    }

    public A2C make_a2c(Card card){
        body.put("senderAccount","26202111828383");
        body.put("amount",amount);
        body.put("operationId","1");
        body.put("customFee","10");
        body.put("receiverCardNumber", Cards_data1.getData(card, Card_param.pan));
        body.put("receiverFirstName","recipientFirstName");
        body.put("receiverLastName","recipientLastName");
        body.put("receiverAccountNumberType","recipientAccountNumberType");
        body.put("senderFirstName","senderFirstName");
        body.put("senderLastName","senderLastName");
        body.put("senderAccountNumber","UA193348510000026200111677871");
        body.put("senderAccountNumberType","senderAccountNumberType");
        body.put("senderReferenceNumber","UA213223130000026007233566001");
        body.put("senderAddress","senderAddress");
        body.put("senderCity","senderCity");
        body.put("senderCountry","senderCountry");
        body.put("senderPostalCode","senderPostalCode");
        body.put("senderDocument","senderDocument");
        body.put("senderBirthday","01-01-2000");
        body.put("senderIpn","senderIpn");
        body.put("details.additionalMessage","additionalMessage");
        body.put("details.merchantUrl","merchantUrl");
        body.put("details.paymentUrl","paymentUrl");
        body.put("details.source","03");
        body.put("details.independentSalesOrganizationId","independentSalesOrganizationId");
        return new A2C(body);
    }

    public A2C make_a2c1(Card card){
        body.put("amount","110000");
        body.put("operationId","20");
        body.put("customFee","0");
        body.put("receiverAccountNumber", "UA193348510000026200111677871");
        body.put("receiverFirstName","DMYTRO");
        body.put("receiverLastName","KOZHYNOV");
        body.put("senderFirstName","Denis");
        body.put("senderLastName","Ishchenko");
        body.put("senderAccountNumber","UA193348510000026200111677871");
        body.put("senderAddress","California Street 111");
        body.put("senderCity","San Francisco");
        body.put("senderCountry","UKR");
        body.put("senderPostalCode","67890");
        body.put("receiverPostalCode","12345");
        body.put("details.source","03");
        body.put("senderPhone","80938888888");
        body.put("receiverPhoneNumber","80937777777");
        return new A2C(body);
    }

    @Test
    public void test111(){
        String am = "20100";
        String acc = "UA963348510000026204114082919";
        List<String> pan = List.of("4206520000093865", "5218320000197748","4314032000026456","5167540500300937", "5168745028518063");
        pan.forEach(x -> {
            body.put("senderAccount","26202111828383");
            body.put("amount",am);
            body.put("operationId","1");
            body.put("customFee","10");
            body.put("receiverCardNumber", "4206520000093865");
            body.put("receiverFirstName","recipientFirstName");
            body.put("receiverLastName","recipientLastName");
            body.put("receiverAccountNumberType","recipientAccountNumberType");
            body.put("senderFirstName","senderFirstName");
            body.put("senderLastName","senderLastName");
            body.put("senderAccountNumber",acc);
            body.put("senderAccountNumberType","senderAccountNumberType");
            body.put("senderReferenceNumber",acc);
            body.put("senderAddress","senderAddress");
            body.put("senderCity","senderCity");
            body.put("senderCountry","senderCountry");
            body.put("senderPostalCode","senderPostalCode");
            body.put("senderDocument","senderDocument");
            body.put("senderBirthday","01-01-2000");
            body.put("senderIpn","senderIpn");
            body.put("details.additionalMessage","additionalMessage");
            body.put("details.merchantUrl","merchantUrl");
            body.put("details.paymentUrl","paymentUrl");
            body.put("details.source","03");
            body.put("details.independentSalesOrganizationId","independentSalesOrganizationId");
            try {
                new A2C(body);
            }catch (Throwable e){}});

    }
}
