package org.example.qaTransactionTeam.frontEnd.pages;

import org.example.qaTransactionTeam.frontEnd.pageBlocks.OtpBlock;
import org.example.qaTransactionTeam.frontEnd.utils.Configs;
import org.example.qaTransactionTeam.frontEnd.utils.Locator;
import org.example.qaTransactionTeam.frontEnd.utils.PageObject;
import org.example.qaTransactionTeam.frontEnd.utils.Session;
import org.example.qaTransactionTeam.frontEnd.utils.widget.GUIButton;
import org.example.qaTransactionTeam.frontEnd.utils.widget.GUITextBox;

public class LoginPage extends PageObject {

    private final String url = "auth/login";
    public final OtpBlock blockOtp = new OtpBlock();
    public final GUITextBox loginField = new GUITextBox(Locator.create("//*[@id=\"input-login\"]"));
    public final GUITextBox passwordField = new GUITextBox(Locator.create("//*[@id=\"input-password\"]"));
    public final GUIButton enterButton = new GUIButton(Locator.create("//app-login/form/button"));

    @Override
    protected Locator readyLocator() {
        return loginField.getLocator();
    }

    public void loginInCab(){

        Session.getWD().get(Configs.environmentDev +url);
        confirmPage();
        loginField.setValue(Configs.loginDev);
        passwordField.setValue(Configs.password);
        enterButton.click();
        blockOtp.confirmBlock();
        blockOtp.otpField.setValue("1111");
        blockOtp.confirmButton.click();

    }

}
