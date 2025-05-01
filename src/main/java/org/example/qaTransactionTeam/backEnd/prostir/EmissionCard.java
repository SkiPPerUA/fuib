package org.example.qaTransactionTeam.backEnd.prostir;


import org.apache.log4j.Logger;
import org.example.qaTransactionTeam.backEnd.utils.BDas400;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmissionCard {

    private static final Logger logger = Logger.getLogger(EmissionCard.class);

    public void deleteMatchedFiles(String idFile) throws SQLException {
        //Поиск сматченых файлов
        ResultSet findMatch = BDas400.selectSQL("SELECT * FROM ASID144402.ZTRANS0P WHERE \"TRRRF#\" = (select \"trrrn#\" FROM itm22d.PRTRNTR0P WHERE TRFHID = "+idFile+")");

            //Удаление сматченых файлов
            if (findMatch.next()){
                BDas400.updateSQL("UPDATE ASID144402.ZTRANS0P SET \"TRRRF#\" = '5555' WHERE \"TRRRF#\" = (select \"trrrn#\" FROM itm22d.PRTRNTR0P WHERE TRFHID = "+idFile+")");
                ResultSet res = BDas400.selectSQL("SELECT * FROM ASID144402.ZTRANS0P WHERE \"TRRRF#\" = '5555'");
                res.next();
                String trstan = res.getString("TRSTAN");
                logger.info("Связка с ASID144402.ZTRANS0P удалена для "+idFile+". Проапдейтен TRSTAN из ASID144402.ZTRANS0P = "+trstan);
            } else {
                logger.info("Для "+idFile+" нет сматченых записей с ASID144402.ZTRANS0P");
            }
    }

    public void changeMTI(int mti, String idFile) throws SQLException {
        ResultSet res = BDas400.selectSQL("SELECT * FROM ITM22D.PRTRNTR0P WHERE TRFHID = "+idFile+"");
        res.next();
        String actualMTI = res.getString("TRMTI");
        logger.info("actualMTI для "+idFile+" = "+actualMTI);

        if(Integer.parseInt(actualMTI) != mti){
            BDas400.updateSQL("UPDATE ITM22D.PRTRNTR0P SET TRMTI = '"+mti+"' WHERE TRFHID = "+idFile+"");
            logger.info("MTI после Update для "+idFile+" = "+mti);
        }else {
            logger.info("MTI не нужно Update, он уже = "+mti);
        }
    }

    public String checkFileStatus(String idFile) throws SQLException {
        ResultSet res = BDas400.selectSQL("select FHFLER FROM itm22d.PRFHDFH0P p WHERE FHKYID = "+idFile+"");
        res.next();
        String status = res.getString(1);
        logger.info("Статус файла - "+status);
        return status;
    }

    public void addMatchedFiles(String idFile) throws SQLException {

        //Поиск сматченых файлов
        ResultSet findMatch = BDas400.selectSQL("SELECT * FROM ASID144402.ZTRANS0P WHERE \"TRRRF#\" = (select \"trrrn#\" FROM itm22d.PRTRNTR0P WHERE TRFHID = "+idFile+")");

            if (findMatch.next()) {
            logger.info(idFile+" уже сматченый с ASID144402.ZTRANS0P");
            }else {

                BDas400.updateSQL("UPDATE ASID144402.ZTRANS0P SET \"TRRRF#\" = (select \"trrrn#\" FROM itm22d.PRTRNTR0P WHERE TRFHID = " + idFile + ") LIMIT 1");
                ResultSet res = BDas400.selectSQL("SELECT * FROM ASID144402.ZTRANS0P WHERE \"TRRRF#\" = (select \"trrrn#\" FROM itm22d.PRTRNTR0P WHERE TRFHID = " + idFile + ")");
                res.next();
                String trstan = res.getString("TRSTAN");
                logger.info("Привязка файла готова. TRSTAN из ASID144402.ZTRANS0P = " + trstan);
            }

    }
}
