package org.example.qaTransactionTeam.backEnd.payHub.frames;

public class C2AFrame extends Frames{

    private String type = "c2a";

    public C2AFrame(String body){
        super.type = type;
        makeFrame(body);
    }

}
