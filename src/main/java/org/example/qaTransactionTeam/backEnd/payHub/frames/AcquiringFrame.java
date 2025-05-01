package org.example.qaTransactionTeam.backEnd.payHub.frames;


public class AcquiringFrame extends Frames {

    private String type = "pga";

    public AcquiringFrame(String body) {
        super.type = type;
        makeFrame(body);
    }

    public void complete(){
        status();
        completeHold("{\n" +
                "  \"transaction_id\": \""+transactionId+"\"\n" +
                "}", linkId);
    }
}
