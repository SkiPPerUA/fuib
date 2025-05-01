package org.example.qaTransactionTeam.backEnd.utils;

import org.apache.log4j.Logger;

import java.sql.*;

public class BDpostgre {

    private static final Logger logger = Logger.getLogger(BDpostgre.class);
    private static Connection conn;
    private static String nameBD;

    public static void BDpostgre(String base, String user, String password) throws ClassNotFoundException, SQLException, IllegalAccessException, InstantiationException {
        Class.forName("org.postgresql.Driver");
        conn= DriverManager.getConnection("jdbc:postgresql://dc3-phdb-002-vm.test-fuib.com:5432/"+base, user,password); //TEST
        //conn= DriverManager.getConnection("jdbc:postgresql://dc3-phd-db-002-vs.dev-fuib.com:5432/"+base+"", user,password); //DEV
        nameBD = base;
        //logger.info("Открыт коннект к базе "+base);
    }

    public static void BDpostgre(String host, String base, String user, String password) throws ClassNotFoundException, SQLException, IllegalAccessException, InstantiationException {
        Class.forName("org.postgresql.Driver");
        conn= DriverManager.getConnection("jdbc:postgresql://"+host+base, user,password); //TEST
        nameBD = base;
        //logger.info("Открыт коннект к базе "+base);
    }

    public static ResultSet callProcedure(String procedure) throws SQLException {
        CallableStatement call = conn.prepareCall(procedure);
        ResultSet res = call.executeQuery();

        return res;
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

    public static void closeConn() throws SQLException {

        //conn.close();
        //logger.info("Закрыт коннект к базе "+nameBD);

    }

    public static Connection getConn(){
        return conn;
    }
}
