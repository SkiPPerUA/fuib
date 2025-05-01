package org.example.qaTransactionTeam.backEnd.itm;

import org.apache.log4j.Logger;
import org.example.qaTransactionTeam.backEnd.utils.BDas400;
import org.example.qaTransactionTeam.backEnd.utils.Configs1;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UnlockCardsForTrans {

    private static final Logger logger = Logger.getLogger(UnlockCardsForTrans.class);

    public void unlockCard(String tokenCard) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        BDas400.BDas400("ITMTST", Configs1.ITMTST_ALL_NAME, Configs1.ITMTST_ALL_PASSWORD);
        ResultSet res = BDas400.callProcedure("call CBA22.CBS00FRX('D', ' ', '001', '1999-01-01', '2039-12-31', 'STI', '"+tokenCard+"', 'Y')");
        res.next();
        String code = res.getString("CODE");

        if(code.equals("00")){
            logger.info("Карта "+tokenCard+" разблокирована для транзакций");
        }else {
            logger.error("Карта "+tokenCard+" НЕ разблокирована для транзакций");
        }

        BDas400.closeConn();
    }
}
