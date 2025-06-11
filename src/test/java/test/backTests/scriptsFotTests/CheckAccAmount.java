package test.backTests.scriptsFotTests;

import org.example.qaTransactionTeam.BaseTest;
import org.example.qaTransactionTeam.backEnd.utils.*;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CheckAccAmount extends BaseTest {

    String env = "ITMTST";
    String card = Cards_data.getData(Card.FUIB_MC,Card_param.pan);

    @BeforeTest
    public void connBD() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        BDas400.BDas400(env, "savchukv", "skipper11223");
    }

    @Test
    public void checkMoneyOnCard() throws SQLException {
        BDas400.callProcedureWithOutResult("CALL fuibgpl.itm22");
        ResultSet res1 = BDas400.selectSQL("SELECT trim(itm22r.gettkn('"+card+"')) FROM \"SYSIBM\".sysdummy1");
        res1.next();
        String token = res1.getString(1);
        logger.info("Token = "+token);

        ResultSet res2 = BDas400.selectSQL("SELECT CAACCT\n" +
                "        FROM  ASID144402.ZCMSCH0P AS ZCMSCH\n" +
                "        JOIN ASID144402.zcdbac0p zcdbac ON  ZCMSCH.CHPCID = zcdbac.CAPCID AND ZCMSCH.CHUSAS = zcdbac.CASEQN\n" +
                "        JOIN itm22d.ZCARDID z  ON ZCMSCH.CHCARD = z.\"CRD#\"\n" +
                "        WHERE 1=1\n" +
                "        AND ZCMSCH.CHCARD = '"+token+"'");
        res2.next();
        String account = res2.getString(1);
        logger.info("Account = "+account);

        if(account.toCharArray()[0] == '?'){
            logger.info("Карта - кредитка");
            ResultSet res3 = BDas400.selectSQL("SELECT TKACCT FROM ASID144402.cacttk0p WHERE TKTAV  = '"+account+"'");
            res3.next();
            checkMoneyCredit(res3.getString(1));
        }else {
            logger.info("Карта - дебетова");
            checkMoneyDebit(account);
        }
    }

    private void checkMoneyDebit(String account) throws SQLException {
        ResultSet res = BDas400.selectSQL("Select * from ASID144402.ZASIBL0P  WHERE TPACCT = '"+account+"'");
        res.next();
        System.out.println(res.getString("TPAVAL"));
    }

    private void checkMoneyCredit(String account) throws SQLException {
        ResultSet res = BDas400.selectSQL("Select * from ASID144402.cmemms0p WHERE MMACCT = '"+account+"'");
        res.next();
        System.out.println(res);
    }

    @AfterTest
    public void closeBD() throws SQLException {
        BDas400.closeConn();
    }
}
