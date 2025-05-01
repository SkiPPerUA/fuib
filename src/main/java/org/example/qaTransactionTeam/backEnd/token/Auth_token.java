package org.example.qaTransactionTeam.backEnd.token;

public interface Auth_token {

    void create_token();

    String getHost();

    String getToken();

    String getAcqID();
}
