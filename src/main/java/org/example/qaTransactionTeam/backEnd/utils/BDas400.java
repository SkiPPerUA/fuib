package org.example.qaTransactionTeam.backEnd.utils;

import org.apache.log4j.Logger;

import java.sql.*;

public class BDas400 {

    private static final Logger logger = Logger.getLogger(BDas400.class);
    private static Connection conn;
    private static String nameBD;

    public static void BDas400(String base, String user, String password) throws ClassNotFoundException, SQLException, IllegalAccessException, InstantiationException {
        Class.forName(com.ibm.as400.access.AS400JDBCDriver.class.getName()).newInstance();
        conn = DriverManager.getConnection("jdbc:as400://"+base+";libraries=LVFUIBCURR LVFUIBDDM CBA22 ITMRFUIB ITMDFUIB ITM22R ITM22D ASIT144402 ASIC144402 ASID144402 ASIR144402 ASIB144402 ASILIB FUIBGPL ITMSYNC QGPL QTEMP;prompt=false;translate binary=true;naming=system", user, password);
        nameBD = base;
        logger.info("Открыт коннект к базе "+nameBD);

    }

    public static ResultSet callProcedure(String procedure) throws SQLException {
        CallableStatement call = conn.prepareCall(procedure);
        ResultSet res = call.executeQuery();

        return res;
    }

    public static void callProcedureWithOutResult(String procedure) throws SQLException {
        PreparedStatement stmtClear = conn.prepareStatement(procedure);
        stmtClear.execute();
    }

    public static ResultSet selectSQL(String request) throws SQLException {
        Statement st = conn.createStatement();
        ResultSet res = st.executeQuery(request);

        return res;
    }

    public static void updateSQL(String reqeust) throws SQLException {
        Statement st = conn.createStatement();
        st.executeUpdate(reqeust);
    }

    public static Connection getConn(){
        return conn;
    }

    public static void closeConn() throws SQLException {
        conn.close();
        logger.info("Закрыт коннект к базе "+nameBD);
    }
}
