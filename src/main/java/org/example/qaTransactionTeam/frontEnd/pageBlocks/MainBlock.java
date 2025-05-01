package org.example.qaTransactionTeam.frontEnd.pageBlocks;

import org.example.qaTransactionTeam.frontEnd.utils.BlockObject;
import org.example.qaTransactionTeam.frontEnd.utils.Locator;
import org.example.qaTransactionTeam.frontEnd.utils.widget.GUIButton;

public class MainBlock extends BlockObject {

    public final GUIButton logOutButton = new GUIButton(Locator.create("//*[@data-name=\"log-out\"]"));
    public final GUIButton personalAreaButton = new GUIButton(Locator.create("//nb-icon[@class=\"menu-icon ng-tns-c128-3 ng-star-inserted\"]"));

    @Override
    protected Locator readyLocator() {
        return logOutButton.getLocator();
    }
}
