package test.backTests.admin;

import org.example.qaTransactionTeam.BaseTest;
import org.example.qaTransactionTeam.backEnd.admin.Banks;
import org.example.qaTransactionTeam.backEnd.utils.BDpostgre;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

@Test
public class Banks_test extends BaseTest {

    Banks banks = new Banks();
    ResultSet res;

    public void positive_getBin() throws SQLException {
        banks.setExpectedResponseCode(200);
        banks.getCrossborderBins();
        res = BDpostgre.selectSQL("SELECT * FROM public.bin_crossborder_wl");
        while (res.next()) {
            Assert.assertTrue(banks.getResponse().contains("\"bin\":"+res.getInt(2)));
        }

    }

    public void positive_add_deleteBin() throws SQLException {
        String bin = "400001";
        banks.setExpectedResponseCode(200);
        banks.addCrossborderBins("{\n" +
                "\"bin\": \""+bin+"\"\n" +
                "}");
        res = BDpostgre.selectSQL("SELECT * FROM public.bin_crossborder_wl where bin = "+bin);
        Assert.assertTrue(res.next());

        banks.setExpectedResponseCode(204);
        banks.deleteCrossborderBins(bin);
        res = BDpostgre.selectSQL("SELECT * FROM public.bin_crossborder_wl where bin = "+bin);
        Assert.assertFalse(res.next());
    }

    @BeforeTest
    void start() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        BDpostgre.BDpostgre("banks", "dev","password");
    }

    @AfterTest
    void end() throws SQLException {
        BDpostgre.closeConn();
    }
}
