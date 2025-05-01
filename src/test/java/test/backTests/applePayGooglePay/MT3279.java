package test.backTests.applePayGooglePay;

import org.example.qaTransactionTeam.BaseTest;
import org.example.qaTransactionTeam.backEnd.utils.BDas400;
import org.example.qaTransactionTeam.backEnd.utils.Configs;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MT3279 extends BaseTest {
    //Обертка над таблицей с банками участниками

    @BeforeTest
    public void ConnBD() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        BDas400.BDas400("ITMTST", Configs.ITMTST_ALL_NAME,Configs.ITMTST_ALL_PASSWORD);
    }

    @Test
    public void positiveTest() throws SQLException {
        int count = 10;
        String [][]arr = new String[count][2];


        //Поиск корректных данных в БД
        ResultSet res = BDas400.selectSQL("SELECT * FROM ASID144402.ZPARCT0P ORDER BY PCINST DESC LIMIT "+count+"");
        for(int i = 0;i<arr.length;i++){
            res.next();
            arr[i][0] = res.getString("PCPART");
            arr[i][1] = res.getString("PCINST");
            logger.info("PCPART - "+res.getString("PCPART")+", PCINST - "+res.getString("PCINST"));
        }

        //Проверка функции
        for(int i = 0; i<arr.length;i++){
            ResultSet res1 = BDas400.selectSQL("SELECT ITM22R.GETACQINST('"+arr[i][0]+"') FROM \"SYSIBM\".SYSDUMMY1");
            res1.next();
            String check = res1.getString(1);
            logger.info("Ответ от функции - "+check);
            Assert.assertEquals(check,arr[i][1]);
        }
    }

    @AfterTest
    public void closeConn() throws SQLException {
        BDas400.closeConn();
    }
}
