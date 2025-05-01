package org.example.qaTransactionTeam.backEnd.itm;

import org.apache.log4j.Logger;
import org.example.qaTransactionTeam.backEnd.utils.BDas400;
import org.example.qaTransactionTeam.backEnd.utils.Configs1;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TokenCard {

    private static final Logger logger = Logger.getLogger(TokenCard.class);
    private String tokenCard;

    public void findToken(String pan) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        BDas400.BDas400("ITMTST", Configs1.ITMTST_ALL_NAME, Configs1.ITMTST_ALL_PASSWORD);
        BDas400.callProcedureWithOutResult("CALL fuibgpl.itm22");
        ResultSet res1 = null;
        try {
            res1 = BDas400.selectSQL("SELECT trim(itm22r.gettkn('"+pan+"')) FROM \"SYSIBM\".sysdummy1");
        } catch (SQLException e) {
            logger.error(e);
        }
        try {
            res1.next();
        } catch (SQLException e) {
            logger.error(e);
        }
        try {
            tokenCard = res1.getString(1);
        } catch (SQLException e) {
            logger.error(e);
            logger.error("Токен не найден");;
        }
        logger.info("Токен для карты - "+pan+" = "+tokenCard);

        BDas400.closeConn();
    }


}
