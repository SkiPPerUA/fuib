package test.backTests.transfers;

import org.example.qaTransactionTeam.BaseTest;
import org.example.qaTransactionTeam.backEnd.transfers.ExternalCardholder;
import org.example.qaTransactionTeam.backEnd.utils.Card;
import org.example.qaTransactionTeam.backEnd.utils.Card_param;
import org.example.qaTransactionTeam.backEnd.utils.Cards_data;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class ExternalCardholder_test extends BaseTest {

    ExternalCardholder externalCardholder = new ExternalCardholder();

    public void positive_externalCardholder_token() {
    externalCardholder.getExternalCardholder("{\n" +
            " \"is_verified\":true,\n" +
            " \"itm_token\":\"?C89XG4XWX0X95X4\"\n" +
            "}");
    }

    public void positive_externalCardholder_pan() {
        externalCardholder.getExternalCardholder("{\n" +
                " \"is_verified\":true,\n" +
                " \"pan\":\"4441111067616619\"\n" +
                "}");
    }
}
