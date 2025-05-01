package test.backTests.clickToPay;

import io.restassured.RestAssured;
import org.example.qaTransactionTeam.BaseTest;
import org.example.qaTransactionTeam.backEnd.helper.Uuid_helper;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

@Test
public class VisaClickToPay extends BaseTest {

    String request_trace_id = "01966d1c-5e1c-2222-a113-38bd022cbf6a";

    public void check_request(){
        RestAssured.useRelaxedHTTPSValidation();
        given()
                .header("authorization", "Basic YWRtaW46YWRtaW4=")
                .body("{\n" +
                        "  \"vhost\": \"tsys\",\n" +
                        "  \"name\": \"amq.default\",\n" +
                        "  \"properties\": {\n" +
                        "    \"delivery_mode\": 1,\n" +
                        "    \"headers\": {\n" +
                        "      \"request_trace_id\": \""+request_trace_id+"\"\n" +
                        "    },\n" +
                        "    \"correlation_id\": \"1\",\n" +
                        "    \"reply_to\": \"visaTest\"\n" +
                        "  },\n" +
                        "  \"routing_key\": \"visa-click-to-pay-requests.request_status\",\n" +
                        "  \"delivery_mode\": \"1\",\n" +
                        "  \"payload\": \"\",\n" +
                        "  \"payload_encoding\": \"string\",\n" +
                        "  \"headers\": {\n" +
                        "    \"request_trace_id\": \""+request_trace_id+"\"\n" +
                        "  },\n" +
                        "  \"props\": {\n" +
                        "    \"correlation_id\": \"1\",\n" +
                        "    \"reply_to\": \"visaTest\"\n" +
                        "  }\n" +
                        "}")
                .post("https://rabbitmq-all.stage-fuib.com/api/exchanges/tsys/amq.default/publish")
                .then().extract().response().prettyPrint();
    }

    public void get_data(){
        sendMess("get_data","{" +
                "    \"intent\": {" +
                "      \"type\": \"PRODUCT_CODE\"," +
                "      \"value\": \"CLICK_TO_PAY\"" +
                "    }," +
                "    \"consumer_information\": {" +
                "      \"external_consumer_id\": \"b591e262-c60d-4004-b79e-f4f599c356e8\"" +
                "    }" +
                "  }");
    }

    public void delete_payment_instruments(){
        sendMess("delete_payment_instruments","{" +
                "  \"intent\": {" +
                "    \"type\": \"PRODUCT_CODE\"," +
                "    \"value\": \"CLICK_TO_PAY\"" +
                "  }," +
                "  \"payment_instruments\": {" +
                "    \"type\": \"CARD\"," +
                "    \"account_number\": \"4314140209916839\"" +
                "  }," +
                "  \"consumer_information\": {" +
                "    \"external_consumer_id\": \"b591e262-c60d-4004-b79e-f4f599c356e8\"" +
                "  }" +
                "}");
    }

    public void delete_consumer_information(){
        sendMess("delete_consumer_information","{" +
                "  \"intent\": {" +
                "    \"type\": \"PRODUCT_CODE\"," +
                "    \"value\": \"CLICK_TO_PAY\"" +
                "  }," +
                "  \"consumer_information\": {" +
                "    \"external_consumer_id\": \"b591e262-c60d-4004-b79e-f4f599c356e8\"" +
                "  }" +
                "}");
    }

    public void enroll_data_ConsumerInformationOnly(){
        sendMess("enroll_data","{" +
                "  \"intent\": {" +
                "    \"type\": \"PRODUCT_CODE\"," +
                "    \"value\": \"CLICK_TO_PAY\"" +
                "  }," +
                "  \"consumer_information\": {" +
                "    \"emails\": [" +
                "      \"alex123@hotmail.com\"" +
                "    ]," +
                "    \"locale\": \"en_US\"," +
                "    \"phones\": [" +
                "      \"16504005555\"" +
                "    ]," +
                "    \"consent\": {" +
                "      \"version\": \"1.0\"," +
                "      \"presenter\": \"Bank A\"," +
                "      \"time_of_consent\": \"2020-05-05T12:12:12.000Z\"" +
                "    }," +
                "    \"last_name\": \"Miller\"," +
                "    \"first_name\": \"Alex\"," +
                "    \"middle_name\": \"Robert\"," +
                "    \"country_code\": \"USA\"," +
                "    \"external_consumer_id\": \""+Uuid_helper.generate_uuid()+"\"," +
                "    \"national_identifiers\": [" +
                "      {" +
                "        \"type\": \"PASSPORT\"," +
                "        \"value\": \"A123456\"" +
                "      }" +
                "    ]" +
                "  }" +
                "}");
    }

    public void enroll_data_EnrollDataAndPaymentInstrumentTogether (){
        sendMess("enroll_data","{" +
                "  \"intent\": {" +
                "    \"type\": \"PRODUCT_CODE\"," +
                "    \"value\": \"CLICK_TO_PAY\"" +
                "  }," +
                "  \"payment_instruments\": [" +
                        "    {" +
                        "      \"type\": \"CARD\"," +
                        "      \"card_type\": \"Visa Platinum\"," +
                        "      \"issuer_name\": \"FUIB\"," +
                        "      \"name_on_card\": \"TETIANA KHMELYNSKA\"," +
                        "      \"account_number\": \"4314140209916839\"," +
                        "      \"billing_address\": {" +
                        "        \"city\": \"San Francisco\"," +
                        "        \"state\": \"CA\"," +
                        "        \"country\": \"USA\"," +
                        "        \"postal_code\": \"94105\"," +
                        "        \"address_line1\": \"1000 Market Street\"," +
                        "        \"address_line2\": \"Building 56\"," +
                        "        \"address_line3\": \"Suite 101\"" +
                        "      }," +
                        "      \"expiration_date\": \"2027-04\"" +
                        "    }" +
                        "  ]," +
                "  \"consumer_information\": {" +
                "    \"emails\": [" +
                "      \"alex32123@gmail.com\"" +
                "    ]," +
                "    \"locale\": \"en_US\"," +
                "    \"phones\": [" +
                "      \"117304005555\"" +
                "    ]," +
                "    \"consent\": {" +
                "      \"version\": \"1.0\"," +
                "      \"presenter\": \"Bank A\"," +
                "      \"time_of_consent\": \"2020-05-05T12:12:12.000Z\"" +
                "    }," +
                "    \"last_name\": \"Miller\"," +
                "    \"first_name\": \"Alex\"," +
                "    \"middle_name\": \"Robert\"," +
                "    \"country_code\": \"USA\"," +
                "    \"external_consumer_id\": \""+ Uuid_helper.generate_uuid() +"\"," +
                "    \"national_identifiers\": [" +
                "      {" +
                "        \"type\": \"PASSPORT\"," +
                "        \"value\": \"A123456\"" +
                "      }" +
                "    ]" +
                "  }" +
                "}");
    }

    public void enroll_data_NewUserEnrollmentWithMultipleEmailAddressesInTheRequest (){
        sendMess("enroll_data","{" +
                "  \"intent\": {" +
                "    \"type\": \"PRODUCT_CODE\"," +
                "    \"value\": \"CLICK_TO_PAY\"" +
                "  }," +
                "  \"payment_instruments\": [" +
                "    {" +
                "      \"type\": \"CARD\"," +
                "      \"card_type\": \"Visa Platinum\"," +
                "      \"issuer_name\": \"Bank A\"," +
                "      \"name_on_card\": \"John Doe\"," +
                "      \"account_number\": \"4314140209371993\"," +
                "      \"billing_address\": {" +
                "        \"city\": \"San Francisco\"," +
                "        \"state\": \"CA\"," +
                "        \"country\": \"USA\"," +
                "        \"postal_code\": \"94105\"," +
                "        \"address_line1\": \"1000 Market Street\"," +
                "        \"address_line2\": \"Building 56\"," +
                "        \"address_line3\": \"Suite 101\"" +
                "      }," +
                "      \"expiration_date\": \"2026-09\"" +
                "    }" +
                "  ]," +
                "  \"consumer_information\": {" +
                "    \"emails\": [" +
                "      \"alex123@hotmail.com\"," +
                "      \"alex12345@hotmail.com\"," +
                "      \"alex123455464@hotmail.com\"" +
                "    ]," +
                "    \"locale\": \"en_US\"," +
                "    \"phones\": [" +
                "      \"112404015555\"" +
                "    ]," +
                "    \"consent\": {" +
                "      \"version\": \"1.0\"," +
                "      \"presenter\": \"Bank A\"," +
                "      \"time_of_consent\": \"2020-05-05T12:12:12.000Z\"" +
                "    }," +
                "    \"last_name\": \"Miller\"," +
                "    \"first_name\": \"Alex\"," +
                "    \"middle_name\": \"Robert\"," +
                "    \"country_code\": \"USA\"," +
                "    \"external_consumer_id\": \""+Uuid_helper.generate_uuid()+"\"," +
                "    \"national_identifiers\": [" +
                "      {" +
                "        \"type\": \"PASSPORT\"," +
                "        \"value\": \"A123456\"" +
                "      }" +
                "    ]" +
                "  }" +
                "}");
    }

    public void enroll_data_EnrollmentWithoutExternalCustomerId (){
        sendMess("enroll_data","{" +
                "  \"intent\": {" +
                "    \"type\": \"PRODUCT_CODE\"," +
                "    \"value\": \"CLICK_TO_PAY\"" +
                "  }," +
                "  \"payment_instruments\": [" +
                "    {" +
                "      \"type\": \"CARD\"," +
                "      \"card_type\": \"Visa Platinum\"," +
                "      \"issuer_name\": \"Bank A\"," +
                "      \"name_on_card\": \"John Doe\"," +
                "      \"account_number\": \"4111111111111111\"," +
                "      \"billing_address\": {" +
                "        \"city\": \"San Francisco\"," +
                "        \"state\": \"CA\"," +
                "        \"country\": \"USA\"," +
                "        \"postal_code\": \"94105\"," +
                "        \"address_line1\": \"1000 Market Street\"," +
                "        \"address_line2\": \"Building 56\"," +
                "        \"address_line3\": \"Suite 101\"" +
                "      }," +
                "      \"expiration_date\": \"2030-01\"" +
                "    }" +
                "  ]," +
                "  \"consumer_information\": {" +
                "    \"emails\": [" +
                "      \"alex123@hotmail.com\"" +
                "    ]," +
                "    \"locale\": \"en_US\"," +
                "    \"phones\": [" +
                "      \"16504005555\"" +
                "    ]," +
                "    \"consent\": {" +
                "      \"version\": \"1.0\"," +
                "      \"presenter\": \"Bank A\"," +
                "      \"time_of_consent\": \"2020-05-05T12:12:12.000Z\"" +
                "    }," +
                "    \"last_name\": \"Miller\"," +
                "    \"first_name\": \"Alex\"," +
                "    \"middle_name\": \"Robert\"," +
                "    \"country_code\": \"USA\"," +
                "    \"national_identifiers\": [" +
                "      {" +
                "        \"type\": \"PASSPORT\"," +
                "        \"value\": \"A123456\"" +
                "      }" +
                "    ]" +
                "  }" +
                "}");
    }

    public void enroll_data_EnrollmentWithMultipleCardsInASingleRequest (){
        sendMess("enroll_data","{" +
                "  \"intent\": {" +
                "    \"type\": \"PRODUCT_CODE\"," +
                "    \"value\": \"CLICK_TO_PAY\"" +
                "  }," +
                "  \"payment_instruments\": [" +
                "    {" +
                "      \"type\": \"CARD\"," +
                "      \"card_type\": \"Visa Platinum\"," +
                "      \"issuer_name\": \"Bank A\"," +
                "      \"name_on_card\": \"John Doe\"," +
                "      \"account_number\": \"4314140209371993\"," +
                "      \"billing_address\": {" +
                "        \"city\": \"San Francisco\"," +
                "        \"state\": \"CA\"," +
                "        \"country\": \"USA\"," +
                "        \"postal_code\": \"94105\"," +
                "        \"address_line1\": \"1000 Market Street\"," +
                "        \"address_line2\": \"Building 56\"," +
                "        \"address_line3\": \"Suite 101\"" +
                "      }," +
                "      \"expiration_date\": \"2026-09\"" +
                "    }," +
                "    {" +
                "      \"type\": \"CARD\"," +
                "      \"card_type\": \"Visa Platinum\"," +
                "      \"issuer_name\": \"Bank B\"," +
                "      \"name_on_card\": \"John Doe\"," +
                "      \"account_number\": \"4314140209916839\"," +
                "      \"billing_address\": {" +
                "        \"city\": \"San Francisco\"," +
                "        \"state\": \"CA\"," +
                "        \"country\": \"USA\"," +
                "        \"postal_code\": \"94105\"," +
                "        \"address_line1\": \"1000 Market Street\"," +
                "        \"address_line2\": \"Building 56\"," +
                "        \"address_line3\": \"Suite 101\"" +
                "      }," +
                "      \"expiration_date\": \"2027-04\"" +
                "    }" +
                "  ]," +
                "  \"consumer_information\": {" +
                "    \"emails\": [" +
                "      \"alex123@hotmail.com\"" +
                "    ]," +
                "    \"locale\": \"en_US\"," +
                "    \"phones\": [" +
                "      \"175645040055\"" +
                "    ]," +
                "    \"consent\": {" +
                "      \"version\": \"1.0\"," +
                "      \"presenter\": \"Bank A\"," +
                "      \"time_of_consent\": \"2020-05-05T12:12:12.000Z\"" +
                "    }," +
                "    \"last_name\": \"Miller\"," +
                "    \"first_name\": \"Alex\"," +
                "    \"middle_name\": \"Robert\"," +
                "    \"country_code\": \"USA\"," +
                "    \"external_consumer_id\": \""+Uuid_helper.generate_uuid()+"\"," +
                "    \"national_identifiers\": [" +
                "      {" +
                "        \"type\": \"PASSPORT\"," +
                "        \"value\": \"A123456\"" +
                "      }" +
                "    ]" +
                "  }" +
                "}");
    }

    public void manage_consumer_information_UpdateEmailAddress(){
        sendMess("manage_consumer_information","{" +
                "  \"intent\": {" +
                "    \"type\": \"PRODUCT_CODE\"," +
                "    \"value\": \"CLICK_TO_PAY\"" +
                "  }," +
                " \"consumer_information\":{" +
                        "      \"emails\":[" +
                        "         \"12alex3retst@gmail.com\"" +
                        "      ]," +
                        "      \"locale\":\"en_US\"," +
                        "      \"phones\":[" +
                        "         \"12304005555\"" +
                        "      ]," +
                        "      \"last_name\":\"Miller\"," +
                        "      \"first_name\":\"Alex\"," +
                        "      \"middle_name\":\"Robert\"," +
                        "      \"country_code\":\"USA\"," +
                        "      \"external_consumer_id\":\"b591e262-c60d-4004-b79e-f4f599c356e8\"," +
                        "      \"national_identifiers\":[" +
                        "         {" +
                        "            \"type\":\"PASSPORT\"," +
                        "            \"value\":\"A123456\"" +
                        "         }" +
                        "      ]" +
                        "   }"+
                "}");
    }

    public void manage_consumer_information_FirstMiddleLastNames(){
        sendMess("manage_consumer_information","{" +
                "  \"intent\": {" +
                "    \"type\": \"PRODUCT_CODE\"," +
                "    \"value\": \"CLICK_TO_PAY\"" +
                "  }," +
                " \"consumer_information\":{" +
                "      \"emails\":[" +
                "         \"12alex32123@gmail.com\"" +
                "      ]," +
                "      \"locale\":\"en_US\"," +
                "      \"phones\":[" +
                "         \"12304005555\"" +
                "      ]," +
                "      \"last_name\":\"Miller1\"," +
                "      \"first_name\":\"Alex2\"," +
                "      \"middle_name\":\"Robert3\"," +
                "      \"country_code\":\"USA\"," +
                "      \"external_consumer_id\":\"b591e262-c60d-4004-b79e-f4f599c356e8\"," +
                "      \"national_identifiers\":[" +
                "         {" +
                "            \"type\":\"PASSPORT\"," +
                "            \"value\":\"A123456\"" +
                "         }" +
                "      ]" +
                "   }"+
                "}");
    }

    public void manage_consumer_information_NationalId(){
        sendMess("manage_consumer_information","{" +
                "   \"intent\":{" +
                "      \"type\":\"PRODUCT_CODE\"," +
                "      \"value\":\"CLICK_TO_PAY\"" +
                "   }," +
                "   \"payment_instruments\":[" +
                "    {" +
                "      \"type\": \"CARD\"," +
                "      \"name_on_card\": \"TETIANA KHMELYNSKA\"," +
                "      \"account_number\": \"4314140209916839\"," +
                "      \"billing_address\": {" +
                "        \"city\": \"San Francisco\"," +
                "        \"state\": \"CA\"," +
                "        \"country\": \"USA\"," +
                "        \"postal_code\": \"94105\"," +
                "        \"address_line1\": \"1000 Market Street\"," +
                "        \"address_line2\": \"Building 56\"," +
                "        \"address_line3\": \"Suite 101\"" +
                "      }," +
                "         \"expiration_date\":\"2027-04\"" +
                "      }" +
                "   ]," +
                "  \"consumer_information\": {" +
                "    \"emails\": [" +
                "      \"alex32123@gmail.com\"" +
                "    ]," +
                "    \"locale\": \"en_US\"," +
                "    \"phones\": [" +
                "      \"117304005555\"" +
                "    ]," +
                "    \"consent\": {" +
                "      \"version\": \"1.0\"," +
                "      \"presenter\": \"Bank A\"," +
                "      \"time_of_consent\": \"2020-05-05T12:12:12.000Z\"" +
                "    }," +
                "    \"last_name\": \"Miller\"," +
                "    \"first_name\": \"Alex\"," +
                "    \"middle_name\": \"Robert\"," +
                "      \"country_code\":\"UKR\"," +
                "      \"external_consumer_id\":\"b591e262-c60d-4004-b79e-f4f599c356e8\"," +
                "      \"national_identifiers\":[" +
                "         {" +
                "            \"type\":\"PASSPORT\"," +
                "            \"value\":\"A663466\"" +
                "         }" +
                "      ]" +
                "   }" +
                "}");
    }

    public void manage_payment_instruments_UpdateExpire(){
        sendMess("manage_payment_instruments","{" +
                "   \"intent\":{" +
                "      \"type\":\"PRODUCT_CODE\"," +
                "      \"value\":\"CLICK_TO_PAY\"" +
                "   }," +
                "   \"payment_instruments\":[" +
                "    {" +
                "      \"type\": \"CARD\"," +
                "      \"name_on_card\": \"TETIANA KHMELYNSKA\"," +
                "      \"account_number\": \"4314140209916839\"," +
                "      \"billing_address\": {" +
                "        \"city\": \"San Francisco\"," +
                "        \"state\": \"CA\"," +
                "        \"country\": \"USA\"," +
                "        \"postal_code\": \"94105\"," +
                "        \"address_line1\": \"1000 Market Street\"," +
                "        \"address_line2\": \"Building 56\"," +
                "        \"address_line3\": \"Suite 101\"" +
                "      }," +
                "         \"expiration_date\":\"2033-01\"" +
                "      }" +
                "   ]," +
                "   \"consumer_information\":{" +
                "      \"external_consumer_id\":\"b591e262-c60d-4004-b79e-f4f599c356e8\"," +
                "   }" +
                "}");
    }

    public void manage_payment_instruments_BillingAddress(){
        sendMess("manage_payment_instruments","{" +
                "   \"intent\":{" +
                        "      \"type\":\"PRODUCT_CODE\"," +
                        "      \"value\":\"CLICK_TO_PAY\"" +
                        "   }," +
                "   \"payment_instruments\":[" +
                "    {" +
                "      \"type\": \"CARD\"," +
                "      \"name_on_card\": \"TETIANA KHMELYNSKA\"," +
                "      \"account_number\": \"4314140209916839\"," +
                "      \"billing_address\": {" +
                "        \"city\": \"New York\"," +
                "        \"state\": \"CA\"," +
                "        \"country\": \"USA\"," +
                "        \"postal_code\": \"94105\"," +
                "        \"address_line1\": \"1000 Market Street\"," +
                "        \"address_line2\": \"Building 56\"," +
                "        \"address_line3\": \"Suite 101\"" +
                "      }," +
                "         \"expiration_date\":\"2027-04\"" +
                "      }" +
                "   ]," +
                "  \"consumer_information\": {" +
                "    \"emails\": [" +
                "      \"alex32123@gmail.com\"" +
                "    ]," +
                "    \"locale\": \"en_US\"," +
                "    \"phones\": [" +
                "      \"117304005555\"" +
                "    ]," +
                "    \"consent\": {" +
                "      \"version\": \"1.0\"," +
                "      \"presenter\": \"Bank A\"," +
                "      \"time_of_consent\": \"2020-05-05T12:12:12.000Z\"" +
                "    }," +
                "    \"last_name\": \"Miller\"," +
                "    \"first_name\": \"Alex\"," +
                "    \"middle_name\": \"Robert\"," +
                "      \"country_code\":\"UKR\"," +
                        "      \"external_consumer_id\":\"b591e262-c60d-4004-b79e-f4f599c356e8\"," +
                        "      \"national_identifiers\":[" +
                        "         {" +
                        "            \"type\":\"PASSPORT\"," +
                        "            \"value\":\"A663466\"" +
                        "         }" +
                        "      ]" +
                        "   }" +
                        "}");
    }

    public void manage_payment_instruments_NameOnCard(){
        sendMess("manage_payment_instruments","{" +
                "   \"intent\":{" +
                        "      \"type\":\"PRODUCT_CODE\"," +
                        "      \"value\":\"CLICK_TO_PAY\"" +
                        "   }," +
                "   \"payment_instruments\":[" +
                "    {" +
                "      \"type\": \"CARD\"," +
                "      \"name_on_card\": \"TETIANA KHMELYNSKAqqqq\"," +
                "      \"account_number\": \"4314140209916839\"," +
                "      \"billing_address\": {" +
                "        \"city\": \"New York\"," +
                "        \"state\": \"CA\"," +
                "        \"country\": \"USA\"," +
                "        \"postal_code\": \"94105\"," +
                "        \"address_line1\": \"1000 Market Street\"," +
                "        \"address_line2\": \"Building 56\"," +
                "        \"address_line3\": \"Suite 101\"" +
                "      }," +
                "         \"expiration_date\":\"2027-04\"" +
                "      }" +
                "   ]," +
                "  \"consumer_information\": {" +
                "    \"emails\": [" +
                "      \"alex32123@gmail.com\"" +
                "    ]," +
                "    \"locale\": \"en_US\"," +
                "    \"phones\": [" +
                "      \"117304005555\"" +
                "    ]," +
                "    \"consent\": {" +
                "      \"version\": \"1.0\"," +
                "      \"presenter\": \"Bank A\"," +
                "      \"time_of_consent\": \"2020-05-05T12:12:12.000Z\"" +
                "    }," +
                "    \"last_name\": \"Miller\"," +
                "    \"first_name\": \"Alex\"," +
                "    \"middle_name\": \"Robert\"," +
                "      \"country_code\":\"UKR\"," +
                "      \"external_consumer_id\":\"b591e262-c60d-4004-b79e-f4f599c356e8\"," +
                        "      \"national_identifiers\":[" +
                        "         {" +
                        "            \"type\":\"PASSPORT\"," +
                        "            \"value\":\"A663466\"" +
                        "         }" +
                        "      ]" +
                        "   }" +
                        "}");
    }

    private void sendMess(String method,String body){
        RestAssured.useRelaxedHTTPSValidation();
        given()
                .header("authorization", "Basic YWRtaW46YWRtaW4=")
                .body("{\n" +
                        "    \"vhost\": \"tsys\"," +
                        "    \"name\": \"amq.default\"," +
                        "    \"properties\": {" +
                        "        \"delivery_mode\": 1," +
                        "        \"headers\": {}," +
                        "        \"content_type\": \"application/json\"," +
                        "        \"correlation_id\": \"12\"," +
                        "        \"reply_to\": \"visaTest\"" +
                        "    }," +
                        "    \"routing_key\": \"visa-click-to-pay-requests."+method+"\"," +
                        "    \"delivery_mode\": \"1\"," +
                        "    \"payload\": \""+body.replaceAll("\"","\\\\\"")+"\"," +
                        "    \"payload_encoding\": \"string\"," +
                        "    \"headers\": {}," +
                        "    \"props\": {" +
                        "        \"content_type\": \"application/json\"," +
                        "        \"correlation_id\": \"12\"," +
                        "        \"reply_to\": \"visaTest\"" +
                        "    }" +
                        "}")
                .post("https://rabbitmq-all.stage-fuib.com/api/exchanges/tsys/amq.default/publish")
                .then().extract().response().prettyPrint();
        System.out.println(body);
    }



}
