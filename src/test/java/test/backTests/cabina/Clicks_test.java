package test.backTests.cabina;

import org.example.qaTransactionTeam.BaseTest;
import org.example.qaTransactionTeam.backEnd.cabina.Clicks;
import org.example.qaTransactionTeam.backEnd.utils.BDpostgre;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Test
public class Clicks_test extends BaseTest {

    Clicks clicks = new Clicks();

    public void positive() throws SQLException {
        String merchant_id = "3bfda6a7-36ed-442b-a840-80d75d0fb7c6";
        List<String> pga_config_names = new ArrayList<>();

        BDpostgre.updateSQL("delete FROM public.digital_clicks where merchant_id = '"+merchant_id+"'");

        clicks.setExpectedResponseCode(204);
        clicks.sendMerchantInfo();

        ResultSet res = BDpostgre.selectSQL("SELECT * FROM public.digital_clicks where merchant_id = '"+merchant_id+"'");
        while (res.next()){
            pga_config_names.add(res.getString("pga_config_name"));
        }
        Assert.assertTrue(pga_config_names.size() > 1);
        System.out.println(pga_config_names);
    }

    @BeforeTest
    void openConn() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        BDpostgre.BDpostgre("cabina", "dev","password");
    }

    @AfterTest
    void closeConn() throws SQLException {
        BDpostgre.closeConn();
    }
}
