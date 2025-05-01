package org.example.qaTransactionTeam.frontEnd.pageBlocks;

import org.example.qaTransactionTeam.frontEnd.utils.BlockObject;
import org.example.qaTransactionTeam.frontEnd.utils.Locator;
import org.example.qaTransactionTeam.frontEnd.utils.widget.GUIButton;
import org.example.qaTransactionTeam.frontEnd.utils.widget.GUITextBox;

public class OtpBlock extends BlockObject {

    public GUITextBox otpField = new GUITextBox(Locator.create("//nb-card-body/form/input"));
    public GUIButton confirmButton = new GUIButton(Locator.create("//nb-card-body/form/div/button[2]"));
    public GUIButton cancelButton = new GUIButton(Locator.create("//nb-card-body/form/div/button[1]"));

    @Override
    protected Locator readyLocator() {
        return otpField.getLocator();
    }
}
