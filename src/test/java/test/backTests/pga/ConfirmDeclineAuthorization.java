package test.backTests.pga;


import org.example.qaTransactionTeam.BaseTest;
import org.example.qaTransactionTeam.backEnd.pgaTransactions.typeTrans.C2A;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ConfirmDeclineAuthorization extends BaseTest {

    @Test
    public void confirmAuth() throws IOException, InterruptedException {
        logStartTest("confirmAuth");
        Map<String,String> body = new HashMap<>();
        body.put("description","Start trx without CPA-02001003DS00504");
        body.put("merchantId","1431ECAE35C80F82409D");
        body.put("state.redirect","post_params");
        body.put("returnUrl","https://innsmouth.payhub.com.ua/frames-ui/main/pga?link_id=cc6effdb-578f-4f4b-96c1-d87149be9c43&signature=3Ht5ynNXk2f2BiGKD2hgAdf5fV7N6kvMXj8EkPn9oLfoPLAkX5kg2WsVvEGWfYgsWLXrZZb4W65TzsgWbQBbg6XgMP5y28B3yLPmZonk4UDcF545NtRrzQBrea69Xrx67iq6mgK5SfajdeEx");
        body.put("src.type","card");
        body.put("src.pan","5355280201969423");
        body.put("src.expiry","0123");
        body.put("amount","256");
        body.put("currency","UAH");
        body.put("accountId","001");
        body.put("state.in_progress","yes");
        body.put("src.csc","107");
        body.put("3ds2.supported","true");
        body.put("params.mer_trx_id","ST-151324_703b6d9b-5d73-4422-be83-c71f4d58d418");
        body.put("state.offer","never");
        body.put("preauthorize","true");
        body.put("lang","ru");
        body.put("merchantTrx","ST-151324_703b6d9b-5d73-4422-be83-c71f4d58d418");
        body.put("params.longPurchaseDesc","Оплата за металопрокат №1880 від 25.10.2021 - 1.70 грн, в т.ч. ПДВ. Ivan Skryma");
        body.put("params.shortPurchaseDesc","Оплата замовлення 1880");
        body.put("commission","0");
        C2A c2a = new C2A(body);
        c2a.authorizationConfirm();
        logFinishTest("confirmAuth");
    }

    @Test
    public void declineAuth() throws IOException, InterruptedException {
        logStartTest("declineAuth");
        Map<String,String> body = new HashMap<>();
        body.put("description","Start trx without CPA-02001003DS00504");
        body.put("merchantId","1431ECAE35C80F82409D");
        body.put("state.redirect","post_params");
        body.put("returnUrl","https://innsmouth.payhub.com.ua/frames-ui/main/pga?link_id=cc6effdb-578f-4f4b-96c1-d87149be9c43&signature=3Ht5ynNXk2f2BiGKD2hgAdf5fV7N6kvMXj8EkPn9oLfoPLAkX5kg2WsVvEGWfYgsWLXrZZb4W65TzsgWbQBbg6XgMP5y28B3yLPmZonk4UDcF545NtRrzQBrea69Xrx67iq6mgK5SfajdeEx");
        body.put("src.type","card");
        body.put("src.pan","5355280201969423");
        body.put("src.expiry","0123");
        body.put("amount","256");
        body.put("currency","UAH");
        body.put("accountId","001");
        body.put("state.in_progress","yes");
        body.put("src.csc","107");
        body.put("3ds2.supported","true");
        body.put("params.mer_trx_id","ST-151324_703b6d9b-5d73-4422-be83-c71f4d58d418");
        body.put("state.offer","never");
        body.put("preauthorize","true");
        body.put("lang","ru");
        body.put("merchantTrx","ST-151324_703b6d9b-5d73-4422-be83-c71f4d58d418");
        body.put("params.longPurchaseDesc","Оплата за металопрокат №1880 від 25.10.2021 - 1.70 грн, в т.ч. ПДВ. Ivan Skryma");
        body.put("params.shortPurchaseDesc","Оплата замовлення 1880");
        body.put("commission","0");
        C2A c2a = new C2A(body);
        c2a.authorizationDecline();
        logFinishTest("declineAuth");
    }
}
