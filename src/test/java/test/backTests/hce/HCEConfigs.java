package test.backTests.hce;

import com.bettercloud.vault.VaultException;
import org.example.qaTransactionTeam.backEnd.hce.VtsEncryptDataResponse;
import org.testng.annotations.Test;

public class HCEConfigs {

//    Инфо
//    https://confluence.fuib.com/pages/viewpage.action?pageId=52830568
//    https://confluence.fuib.com/pages/viewpage.action?pageId=9668753

    //https://api.dev-fuib.com   https://tnfc.fuib.com
    public static final String ENDPOINT = "https://tnfc.fuib.com";

    public static final String REQUEST_ID = "ed8c8b78-9294-4f82-a8e7-e42d3d0e2ee3";

    //Генерация сертификата
    @Test
    public void generSert() throws VaultException {
        String json = "{\n" +
                "  \"cardholderInfo\": {\n" +
                "    \"primaryAccountNumber\": \"4030216666660008\",\n" +
                "    \"cvv2\": \"123\",\n" +
                "    \"name\": \"Name SecondName\",\n" +
                "    \"expirationDate\": {\n" +
                "      \"month\": \"10\",\n" +
                "      \"year\": \"2023\"\n" +
                "    },\n" +
                "    \"billingAddress\": {\n" +
                "      \"line1\": \"Blalbalba 19/21\",\n" +
                "      \"postalCode\": \"01030\"\n" +
                "    },\n" +
                "    \"highValueCustomer\": false,\n" +
                "    \"riskAssessmentScore\": \"9\"\n" +
                "  },\n" +
                "  \"riskInformation\": {\n" +
                "    \"walletProviderAccountScore\": \"4\",\n" +
                "    \"walletProviderDeviceScore\": \"2\",\n" +
                "    \"walletProviderReasonCodes\": \"A0\",\n" +
                "    \"walletProviderRiskAssessment\": \"0\",\n" +
                "    \"accountHolderName\": \"Name SecondName\",\n" +
                "    \"visaTokenScore\": \"08\",\n" +
                "    \"visaTokenDecisioning\": \"00\",\n" +
                "    \"riskAssessmentScore\": \"9\"\n" +
                "  }\n" +
                "}\n";
        System.out.println(VtsEncryptDataResponse.encryptOutputData(json));
    }
}