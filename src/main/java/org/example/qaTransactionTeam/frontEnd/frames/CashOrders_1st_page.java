package org.example.qaTransactionTeam.frontEnd.frames;

import org.example.qaTransactionTeam.frontEnd.utils.Configs;
import org.example.qaTransactionTeam.frontEnd.utils.Locator;
import org.example.qaTransactionTeam.frontEnd.utils.PageObject;
import org.example.qaTransactionTeam.frontEnd.utils.Session;
import org.example.qaTransactionTeam.frontEnd.utils.widget.GUIButton;
import org.example.qaTransactionTeam.frontEnd.utils.widget.GUITextBox;

public class CashOrders_1st_page extends PageObject {

    private final GUIButton nextButton = new GUIButton(Locator.create("//button[contains(@class, 'rounded')]"));
    private final GUITextBox phoneBox = new GUITextBox(Locator.create("//input[@id='phoneNumber']"));
    private final GUITextBox nameBox = new GUITextBox(Locator.create("//input[@id='name']"));
    private final GUITextBox emailBox = new GUITextBox(Locator.create("//input[@id='email']"));
    private final GUIButton cityButton = new GUIButton(Locator.create("//div[@id='mat-select-value-1']"));
    private final GUIButton streetButton = new GUIButton(Locator.create("//div[@id='mat-select-value-3']"));

    @Override
    protected Locator readyLocator() {
        return nextButton.getLocator();
    }

    public CashOrders_1st_page openPage(String url){
        Session.getWD().get(Configs.environmentFrames+url);
        confirmPage();
        return this;
    }

    public CashOrders_1st_page choiseCity(String city){
        cityButton.click();
        GUIButton choiseCity = new GUIButton(Locator.create("//span[text()='"+city+"']"));
        choiseCity.click();
        return this;
    }

    public CashOrders_1st_page choiseStreet(String street){
        streetButton.click();
        GUIButton choiseStreet = new GUIButton(Locator.create("//span[text()='"+street+"']"));
        choiseStreet.click();
        return this;
    }

    public CashOrders_1st_page setPhoneBox(String phone){
        phoneBox.setValue(phone);
        return this;
    }

    public CashOrders_1st_page setEmailBox(String email){
        emailBox.setValue(email);
        return this;
    }

    public CashOrders_1st_page setNameBox(String name){
        nameBox.setValue(name);
        return this;
    }

    public CashOrders_2st_page clickNextButton(){
        nextButton.click();
        return new CashOrders_2st_page();
    }


}
