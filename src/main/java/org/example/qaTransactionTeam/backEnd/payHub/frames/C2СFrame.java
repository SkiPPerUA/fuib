package org.example.qaTransactionTeam.backEnd.payHub.frames;

public class C2СFrame extends Frames{

    private String type = "c2c";

    public C2СFrame(String body){
        super.type = type;
        makeFrame(body);
    }

    public C2СFrame(){
        super.type = type;
    }

}
