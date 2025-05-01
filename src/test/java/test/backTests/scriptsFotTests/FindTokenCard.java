package test.backTests.scriptsFotTests;

import org.example.qaTransactionTeam.BaseTest;
import org.example.qaTransactionTeam.backEnd.utils.*;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FindTokenCard extends BaseTest {

    String env = "ITMTST";
    String card = "5355280012765291";

    @BeforeTest
    public void connBD() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        BDas400.BDas400(env, Configs1.ITMTST_ALL_NAME, Configs1.ITMTST_ALL_PASSWORD);
    }

    @Test
    public void findTokenByCard() throws SQLException {
        BDas400.callProcedureWithOutResult("CALL fuibgpl.itm22");
        ResultSet res1 = BDas400.selectSQL("SELECT trim(itm22r.gettkn('"+card+"')) FROM \"SYSIBM\".sysdummy1");
        res1.next();
        String token = res1.getString(1);
        logger.info("Token = "+token);
    }

    @AfterTest
    public void closeConn() throws SQLException {
        BDas400.closeConn();
    }
}
