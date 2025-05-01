package org.example.qaTransactionTeam.backEnd.transaction;

public interface Transaction {

    void makeTrans();

    String status();

    String getResponse();

    String getTransactionId();

    void setExpectedStatus(int status);

    void setBodyRequest(String bodyRequest);

    void setThreeDS(int threeDS);
}
