package test.backTests.itm.transaction;

import org.example.qaTransactionTeam.BaseTest;
import org.example.qaTransactionTeam.backEnd.transaction.typeTrans_itm.C2A;
import org.example.qaTransactionTeam.backEnd.utils.Card;
import org.example.qaTransactionTeam.backEnd.utils.Card_param;
import org.example.qaTransactionTeam.backEnd.utils.Cards_data1;
import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.Map;

public class C2A_regress extends BaseTest {

    public String amount = "100";
    Map<String, String> body = new HashMap<>();

    @Test
    void c2a(){
        make_c2a(Card.MONO_MC);
    }

    @Test
    void c2a_with3ds2(){
        make_c2a_threeDS(Card.MONO_MC);
    }

    @Test
    void c2a_mVisa(){
        body.put("senderCardNumber", "4441111136277021");
        body.put("amount",amount);
        body.put("senderCity","Lviv");
        body.put("senderAddress","vyl. Lvivska 10");
        body.put("senderCountry","UKR");
        body.put("expDate","2808");
        body.put("details.source","01");
        body.put("receiverQR","0002010216521567107130523752046012530398054047.505802UA5914MVISA_PORTMONE6004Kiev62210105162400808TestDesc");
        body.put("receiverFirstName","recipientFirstName");
        body.put("receiverLastName","recipientLastName");
        body.put("receiverAccountNumber","UA193348510000026200111677871");
        C2A c2a = new C2A();
        c2a.setToken("3004");
        c2a.setBodyRequest(body);
        c2a.setThreeDS(0);
        c2a.setCvv("294");
        c2a.makeTrans();
    }

    public C2A make_c2a(Card card) {
        set_body(card);
        return new C2A(body);
    }

    public C2A make_c2a_threeDS(Card card){
        set_body(card);
        return new C2A(body,"250",2);
    }

    private void set_body(Card card){
        body.put("senderCardNumber", Cards_data1.getData(card,Card_param.pan));
        body.put("amount",amount);
        body.put("operationId","2");
        body.put("customFee","0");
        //body.put("receiverAccount","26202111828383");
        body.put("expDate", Cards_data1.getData(card,Card_param.expire));
        body.put("ip","127.0.0.1");
        body.put("fingerprint","tests25");
        body.put("receiverFirstName","recipientFirstName");
        body.put("receiverLastName","recipientLastName");
        body.put("receiverAccountNumber","UA193348510000026200111677871");
        body.put("receiverAccountNumberType","recipientAccountNumberType");
        body.put("senderFirstName","senderFirstName");
        body.put("senderLastName","senderLastName");
        body.put("senderAccountNumber","UA193348510000026200111677871");
        body.put("senderAccountNumberType","senderAccountNumberType");
        body.put("senderReferenceNumber","senderReferenceNumber");
        body.put("senderAddress","senderAddress");
        body.put("senderCity","senderCity");
        body.put("senderCountry","senderCountry");
        body.put("senderPostalCode","senderPostalCode");
        body.put("senderDocument","senderDocument");
        body.put("senderDocumentType","senderDocumentType");
        body.put("senderBirthday","01-01-2000");
        body.put("senderIpn","senderIpn");
        body.put("details.merchantUrl","merchantUrl");
        body.put("details.paymentUrl","paymentUrl");
        body.put("details.additionalMessage","additionalMessage");
        body.put("details.source","01");
        body.put("details.independentSalesOrganizationId","independentSalesOrganizationId");
    }

}
