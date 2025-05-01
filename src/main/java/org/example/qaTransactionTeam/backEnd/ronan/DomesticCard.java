package org.example.qaTransactionTeam.backEnd.ronan;

public class DomesticCard extends Ronan{

    public DomesticCard(String url){
        localURL = url;
    }

    public boolean checkDomestic(String pan){
        return checkCountryCard(pan);
    }
}
