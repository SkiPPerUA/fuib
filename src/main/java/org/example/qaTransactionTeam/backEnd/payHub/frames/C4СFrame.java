package org.example.qaTransactionTeam.backEnd.payHub.frames;

public class C4СFrame extends Frames{

    private String type = "deferred/c2c";

    public C4СFrame(String body){
        super.type = type;
        makeFrame(body);
    }

}
