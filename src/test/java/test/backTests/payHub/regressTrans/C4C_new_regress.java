package test.backTests.payHub.regressTrans;

import org.example.qaTransactionTeam.BaseTest;
import org.example.qaTransactionTeam.backEnd.helper.Uuid_helper;
import org.example.qaTransactionTeam.backEnd.transaction.ThreeDS;
import org.example.qaTransactionTeam.backEnd.transaction.typeTrans_payhub.C4C_new;
import org.example.qaTransactionTeam.backEnd.utils.Card;
import org.example.qaTransactionTeam.backEnd.utils.Card_param;
import org.example.qaTransactionTeam.backEnd.utils.Cards_data;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Random;

@Test
public class C4C_new_regress extends BaseTest {

    Card sender = Card.FUIB_MC;
    Card receiver = Card.FUIB_VISA;
    C4C_new c4cNew = new C4C_new();

    public void positive_sameCard_not3ds(){

        c4cNew.setExpectedStatus(200);
        c4cNew.initTransfers("{\n" +
                "    \"external_id\": \""+new Random().nextInt() +"\",\n" +
                "    \"amount\": 1000,\n" +
                "    \"commission\": 10,\n" +
                "    \"description\": \"test description\",\n" +
                "    \"destination\": \"test destination\",\n" +
                "    \"payer\": {\n" +
                "        \"source\": \"PAN\",\n" +
                "        \"value\": \""+ Cards_data.getData(sender, Card_param.pan) +"\",\n" +
                "        \"expire\": \""+ Cards_data.getData(sender, Card_param.expire)+"\"\n" +
                "    },\n" +
                "    \"receiver\": {\n" +
                "        \"source\": \"PAN\",\n" +
                "        \"value\": \""+ Cards_data.getData(receiver, Card_param.pan)+"\"\n" +
                "    },"+
                "    \"identification\": {\n" +
                "        \"requirements\": {\n" +
                "            \"sender\": {\n" +
                "                \"first_name\": \"firstname\",\n" +
                "                \"last_name\": \"last\",\n" +
                "                \"tax_id\": \"1234567890\",\n" +
                "                \"city\":\"City\",\n" +
                "                \"country\":\"UKR\",\n" +
                "                \"address\":\"address\",\n" +
                "                \"account_number\": \"UA953348510000026201112609803\"\n" +
                "            },\n" +
                "            \"recipient\": {\n" +
                "                \"first_name\": \"test\",\n" +
                "                \"last_name\": \"test\",\n" +
                "                \"account_number\": \"UA953348510000026201112609803\"\n" +
                "            },\n" +
                "            \"details\": {\n" +
                "                \"source\": \"07\",\n" +
                "                \"submerchant_url\": \"https://jira.fuib.com/projects/PAYH/issues/PAYH-41057\",\n" +
                "                \"independent_sales_organization_id\": \"3016715233\",\n" +
                "                \"additional_message\": \"test\"\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "}");
        waiter(30);
        c4cNew.status();
        Assert.assertTrue(c4cNew.getResponse().contains("\"available_amount\":1000"));
        Assert.assertTrue(c4cNew.getResponse().contains("\"status\":\"ACTIVE\""));

        c4cNew.enroll(c4cNew.getDebitId(), "{\n" +
                "    \"external_id\": \""+Uuid_helper.generate_uuid()+"\",\n" +
                "    \"operation_id\": \""+c4cNew.getDebitId()+"\",\n" +
                "    \"amount\": 500,\n" +
                "    \"fee\": 52,\n" +
                " \"identification\": {\n" +
                "        \"requirements\": {\n" +
                "            \"sender\": {\n" +
                "                \"first_name\": \"firstname\",\n" +
                "                \"last_name\": \"last\",\n" +
                "                \"city\":\"City\",\n" +
                "                \"country\":\"UKR\",\n" +
                "                \"address\":\"address\",\n" +
                "                \"tax_id\": \"1234567890\",\n" +
                "                 \"account_number\": \"UA953348510000026201112609803\"\n" +
                "            },\n" +
                "            \"recipient\": {\n" +
                "                \"first_name\": \"test\",\n" +
                "                \"last_name\": \"test\",\n" +
                "                \"account_number\": \"UA953348510000026201112609803\"\n" +
                "            },\n" +
                "            \"details\": {\n" +
                "            \n" +
                "                \"submerchant_url\": \"https://jira.fuib.com/projects/PAYH/issues/PAYH-41057\",\n" +
                "                \"independent_sales_organization_id\": \"3016715233\",\n" +
                "                \"additional_message\": \"test\"\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "}");
        waiter(30);
        c4cNew.statusEnroll(c4cNew.getDebitId(), c4cNew.getEnrollId());
        Assert.assertTrue(c4cNew.getResponse().contains("\"status\":\"PROCESSED\""));

        c4cNew.refund(100);
        Assert.assertTrue(c4cNew.getResponse().contains("\"status\":\"CREATED\""));

        waiter(30);
        c4cNew.statusRefund();
        Assert.assertTrue(c4cNew.getResponse().contains("\"status\":\"PROCESSED\""));

        c4cNew.status();
        Assert.assertTrue(c4cNew.getResponse().contains("\"available_amount\":400"));
        Assert.assertTrue(c4cNew.getResponse().contains("\"total_refunded_amount\":100"));
        Assert.assertTrue(c4cNew.getResponse().contains("\"status\":\"ACTIVE\""));
    }

    public void positive_newCard(){
        c4cNew.setExpectedStatus(200);
        c4cNew.initTransfers("{\n" +
                "    \"external_id\": \""+new Random().nextInt() +"\",\n" +
                "    \"amount\": 1000,\n" +
                "    \"description\": \"test description\",\n" +
                "    \"destination\": \"test destination\",\n" +
                "    \"payer\": {\n" +
                "        \"source\": \"PAN\",\n" +
                "        \"value\": \""+ Cards_data.getData(sender, Card_param.pan) +"\",\n" +
                "        \"expire\": \""+ Cards_data.getData(sender, Card_param.expire)+"\"\n" +
                "    },\n" +
                "    \"receiver\": {\n" +
                "        \"source\": \"PAN\",\n" +
                "        \"value\": \""+ Cards_data.getData(sender, Card_param.pan)+"\"\n" +
                "    },"+
                "    \"identification\": {\n" +
                "        \"requirements\": {\n" +
                "            \"sender\": {\n" +
                "                \"first_name\": \"firstname\",\n" +
                "                \"last_name\": \"last\",\n" +
                "                \"tax_id\": \"1234567890\",\n" +
                "                 \"account_number\": \"UA953348510000026201112609803\"\n" +
                "            },\n" +
                "            \"recipient\": {\n" +
                "                \"first_name\": \"test\",\n" +
                "                \"last_name\": \"test\",\n" +
                "                \"account_number\": \"UA953348510000026201112609803\"\n" +
                "            },\n" +
                "            \"details\": {\n" +
                "                \"source\": \"07\",\n" +
                "                \"submerchant_url\": \"https://jira.fuib.com/projects/PAYH/issues/PAYH-41057\",\n" +
                "                \"independent_sales_organization_id\": \"3016715233\",\n" +
                "                \"additional_message\": \"test\"\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "}");
        waiter(30);
        c4cNew.status();
        Assert.assertTrue(c4cNew.getResponse().contains("\"available_amount\":1000"));
        Assert.assertTrue(c4cNew.getResponse().contains("\"status\":\"ACTIVE\""));

        c4cNew.enroll(c4cNew.getDebitId(), "{\n" +
                "    \"external_id\": \""+ Uuid_helper.generate_uuid() +"\",\n" +
                "    \"operation_id\": \""+c4cNew.getDebitId()+"\",\n" +
                "    \"amount\": 500,\n" +
                "    \"receiver\": {\n" +
                "        \"source\": \"PAN\",\n" +
                "        \"value\": \""+ Cards_data.getData(receiver, Card_param.pan)+"\"\n" +
                "    },"+
                "    \"identification\": {\n" +
                "        \"requirements\": {\n" +
                "            \"sender\": {\n" +
                "                \"first_name\": \"firstname\",\n" +
                "                \"last_name\": \"last\",\n" +
                "                \"tax_id\": \"1234567890\",\n" +
                "                 \"account_number\": \"UA953348510000026201112609803\"\n" +
                "            },\n" +
                "            \"recipient\": {\n" +
                "                \"first_name\": \"test\",\n" +
                "                \"last_name\": \"test\",\n" +
                "                \"account_number\": \"UA953348510000026201112609803\"\n" +
                "            },\n" +
                "            \"details\": {\n" +
                "            \n" +
                "                \"submerchant_url\": \"https://jira.fuib.com/projects/PAYH/issues/PAYH-41057\",\n" +
                "                \"independent_sales_organization_id\": \"3016715233\",\n" +
                "                \"additional_message\": \"test\"\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "}");
        waiter(30);
        c4cNew.statusEnroll(c4cNew.getDebitId(), c4cNew.getEnrollId());
        c4cNew.refund(100);
        waiter(30);
        c4cNew.statusRefund();
        c4cNew.status();
        Assert.assertTrue(c4cNew.getResponse().contains("\"available_amount\":400"));
        Assert.assertTrue(c4cNew.getResponse().contains("\"total_refunded_amount\":100"));
        Assert.assertTrue(c4cNew.getResponse().contains("\"status\":\"ACTIVE\""));
    }

    public void positive_notReceiver_onInit(){
        c4cNew.setExpectedStatus(200);
        c4cNew.initTransfers("{\n" +
                "    \"external_id\": \""+new Random().nextInt() +"\",\n" +
                "    \"amount\": 1000,\n" +
                "    \"description\": \"test description\",\n" +
                "    \"destination\": \"test destination\",\n" +
                "    \"payer\": {\n" +
                "        \"source\": \"PAN\",\n" +
                "        \"value\": \""+ Cards_data.getData(sender, Card_param.pan) +"\",\n" +
                "        \"expire\": \""+ Cards_data.getData(sender, Card_param.expire)+"\"\n" +
                "    },\n" +
                "    \"identification\": {\n" +
                "        \"requirements\": {\n" +
                "            \"sender\": {\n" +
                "                \"first_name\": \"firstname\",\n" +
                "                \"last_name\": \"last\",\n" +
                "                \"tax_id\": \"1234567890\",\n" +
                "                 \"account_number\": \"UA953348510000026201112609803\"\n" +
                "            },\n" +
                "            \"recipient\": {\n" +
                "                \"first_name\": \"test\",\n" +
                "                \"last_name\": \"test\",\n" +
                "                \"account_number\": \"UA953348510000026201112609803\"\n" +
                "            },\n" +
                "            \"details\": {\n" +
                "                \"source\": \"07\",\n" +
                "                \"submerchant_url\": \"https://jira.fuib.com/projects/PAYH/issues/PAYH-41057\",\n" +
                "                \"independent_sales_organization_id\": \"3016715233\",\n" +
                "                \"additional_message\": \"test\"\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "}");
        waiter(30);
        c4cNew.status();
        Assert.assertTrue(c4cNew.getResponse().contains("\"available_amount\":1000"));
        Assert.assertTrue(c4cNew.getResponse().contains("\"status\":\"ACTIVE\""));

        c4cNew.enroll(c4cNew.getDebitId(), "{\n" +
                "    \"external_id\": \""+ Uuid_helper.generate_uuid() +"\",\n" +
                "    \"operation_id\": \""+c4cNew.getDebitId()+"\",\n" +
                "    \"amount\": 500,\n" +
                "    \"receiver\": {\n" +
                "        \"source\": \"PAN\",\n" +
                "        \"value\": \""+ Cards_data.getData(receiver, Card_param.pan)+"\"\n" +
                "    },"+
                "    \"identification\": {\n" +
                "        \"requirements\": {\n" +
                "            \"sender\": {\n" +
                "                \"first_name\": \"firstname\",\n" +
                "                \"last_name\": \"last\",\n" +
                "                \"tax_id\": \"1234567890\",\n" +
                "                 \"account_number\": \"UA953348510000026201112609803\"\n" +
                "            },\n" +
                "            \"recipient\": {\n" +
                "                \"first_name\": \"test\",\n" +
                "                \"last_name\": \"test\",\n" +
                "                \"account_number\": \"UA953348510000026201112609803\"\n" +
                "            },\n" +
                "            \"details\": {\n" +
                "            \n" +
                "                \"submerchant_url\": \"https://jira.fuib.com/projects/PAYH/issues/PAYH-41057\",\n" +
                "                \"independent_sales_organization_id\": \"3016715233\",\n" +
                "                \"additional_message\": \"test\"\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "}");
        waiter(30);
        c4cNew.statusEnroll(c4cNew.getDebitId(), c4cNew.getEnrollId());
        c4cNew.refund(100);
        waiter(30);
        c4cNew.status();
        Assert.assertTrue(c4cNew.getResponse().contains("\"available_amount\":400"));
        Assert.assertTrue(c4cNew.getResponse().contains("\"total_refunded_amount\":100"));
        Assert.assertTrue(c4cNew.getResponse().contains("\"status\":\"ACTIVE\""));
    }

    public void positive_twoEnrolls(){
        c4cNew.setExpectedStatus(200);
        c4cNew.initTransfers("{\n" +
                "    \"external_id\": \""+new Random().nextInt() +"\",\n" +
                "    \"amount\": 1000,\n" +
                "    \"description\": \"test description\",\n" +
                "    \"destination\": \"test destination\",\n" +
                "    \"payer\": {\n" +
                "        \"source\": \"PAN\",\n" +
                "        \"value\": \""+ Cards_data.getData(sender, Card_param.pan) +"\",\n" +
                "        \"expire\": \""+ Cards_data.getData(sender, Card_param.expire)+"\"\n" +
                "    },\n" +
                "    \"receiver\": {\n" +
                "        \"source\": \"PAN\",\n" +
                "        \"value\": \""+ Cards_data.getData(sender, Card_param.pan)+"\"\n" +
                "    },"+
                "    \"identification\": {\n" +
                "        \"requirements\": {\n" +
                "            \"sender\": {\n" +
                "                \"first_name\": \"firstname\",\n" +
                "                \"last_name\": \"last\",\n" +
                "                \"tax_id\": \"1234567890\",\n" +
                "                 \"account_number\": \"UA953348510000026201112609803\"\n" +
                "            },\n" +
                "            \"recipient\": {\n" +
                "                \"first_name\": \"test\",\n" +
                "                \"last_name\": \"test\",\n" +
                "                \"account_number\": \"UA953348510000026201112609803\"\n" +
                "            },\n" +
                "            \"details\": {\n" +
                "                \"source\": \"07\",\n" +
                "                \"submerchant_url\": \"https://jira.fuib.com/projects/PAYH/issues/PAYH-41057\",\n" +
                "                \"independent_sales_organization_id\": \"3016715233\",\n" +
                "                \"additional_message\": \"test\"\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "}");
        waiter(30);
        c4cNew.status();
        Assert.assertTrue(c4cNew.getResponse().contains("\"available_amount\":1000"));
        Assert.assertTrue(c4cNew.getResponse().contains("\"status\":\"ACTIVE\""));

        c4cNew.enroll(c4cNew.getDebitId(), "{\n" +
                "    \"external_id\": \"" + Uuid_helper.generate_uuid() + "\",\n" +
                "    \"operation_id\": \"" + c4cNew.getDebitId() + "\",\n" +
                "    \"amount\": 400,\n" +
                "    \"receiver\": {\n" +
                "        \"source\": \"PAN\",\n" +
                "        \"value\": \"" + Cards_data.getData(sender, Card_param.pan) + "\"\n" +
                "    }," +
                "    \"identification\": {\n" +
                "        \"requirements\": {\n" +
                "            \"sender\": {\n" +
                "                \"first_name\": \"firstname\",\n" +
                "                \"last_name\": \"last\",\n" +
                "                \"tax_id\": \"1234567890\",\n" +
                "                 \"account_number\": \"UA953348510000026201112609803\"\n" +
                "            },\n" +
                "            \"recipient\": {\n" +
                "                \"first_name\": \"test\",\n" +
                "                \"last_name\": \"test\",\n" +
                "                \"account_number\": \"UA953348510000026201112609803\"\n" +
                "            },\n" +
                "            \"details\": {\n" +
                "            \n" +
                "                \"submerchant_url\": \"https://jira.fuib.com/projects/PAYH/issues/PAYH-41057\",\n" +
                "                \"independent_sales_organization_id\": \"3016715233\",\n" +
                "                \"additional_message\": \"test\"\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "}");
        waiter(30);
        c4cNew.statusEnroll(c4cNew.getDebitId(), c4cNew.getEnrollId());

        c4cNew.enroll(c4cNew.getDebitId(), "{\n" +
                "    \"external_id\": \"" + Uuid_helper.generate_uuid() + "\",\n" +
                "    \"operation_id\": \"" + c4cNew.getDebitId() + "\",\n" +
                "    \"amount\": 400,\n" +
                "    \"receiver\": {\n" +
                "        \"source\": \"PAN\",\n" +
                "        \"value\": \"" + Cards_data.getData(receiver, Card_param.pan) + "\"\n" +
                "    }," +
                "    \"identification\": {\n" +
                "        \"requirements\": {\n" +
                "            \"sender\": {\n" +
                "                \"first_name\": \"firstname\",\n" +
                "                \"last_name\": \"last\",\n" +
                "                \"tax_id\": \"1234567890\",\n" +
                "                 \"account_number\": \"UA953348510000026201112609803\"\n" +
                "            },\n" +
                "            \"recipient\": {\n" +
                "                \"first_name\": \"test\",\n" +
                "                \"last_name\": \"test\",\n" +
                "                \"account_number\": \"UA953348510000026201112609803\"\n" +
                "            },\n" +
                "            \"details\": {\n" +
                "            \n" +
                "                \"submerchant_url\": \"https://jira.fuib.com/projects/PAYH/issues/PAYH-41057\",\n" +
                "                \"independent_sales_organization_id\": \"3016715233\",\n" +
                "                \"additional_message\": \"test\"\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "}");
        waiter(30);
        c4cNew.statusEnroll(c4cNew.getDebitId(), c4cNew.getEnrollId());
        c4cNew.refund(100);
        waiter(30);
        c4cNew.status();
        Assert.assertTrue(c4cNew.getResponse().contains("\"available_amount\":100"));
        Assert.assertTrue(c4cNew.getResponse().contains("\"total_refunded_amount\":100"));
        Assert.assertTrue(c4cNew.getResponse().contains("\"status\":\"ACTIVE\""));
    }

    public void negative_threeEnrolls(){
        c4cNew.setExpectedStatus(200);
        c4cNew.initTransfers("{\n" +
                "    \"external_id\": \""+new Random().nextInt() +"\",\n" +
                "    \"amount\": 1000,\n" +
                "    \"description\": \"test description\",\n" +
                "    \"destination\": \"test destination\",\n" +
                "    \"payer\": {\n" +
                "        \"source\": \"PAN\",\n" +
                "        \"value\": \""+ Cards_data.getData(sender, Card_param.pan) +"\",\n" +
                "        \"expire\": \""+ Cards_data.getData(sender, Card_param.expire)+"\"\n" +
                "    },\n" +
                "    \"receiver\": {\n" +
                "        \"source\": \"PAN\",\n" +
                "        \"value\": \""+ Cards_data.getData(sender, Card_param.pan)+"\"\n" +
                "    },"+
                "    \"identification\": {\n" +
                "        \"requirements\": {\n" +
                "            \"sender\": {\n" +
                "                \"first_name\": \"firstname\",\n" +
                "                \"last_name\": \"last\",\n" +
                "                \"tax_id\": \"1234567890\",\n" +
                "                 \"account_number\": \"UA953348510000026201112609803\"\n" +
                "            },\n" +
                "            \"recipient\": {\n" +
                "                \"first_name\": \"test\",\n" +
                "                \"last_name\": \"test\",\n" +
                "                \"account_number\": \"UA953348510000026201112609803\"\n" +
                "            },\n" +
                "            \"details\": {\n" +
                "                \"source\": \"07\",\n" +
                "                \"submerchant_url\": \"https://jira.fuib.com/projects/PAYH/issues/PAYH-41057\",\n" +
                "                \"independent_sales_organization_id\": \"3016715233\",\n" +
                "                \"additional_message\": \"test\"\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "}");
        waiter(30);
        c4cNew.status();
        Assert.assertTrue(c4cNew.getResponse().contains("\"available_amount\":1000"));
        Assert.assertTrue(c4cNew.getResponse().contains("\"status\":\"ACTIVE\""));

        for (int i = 0; i < 3; i++) {
            if (i == 2){
                c4cNew.setExpectedStatus(400);
            }
            c4cNew.enroll(c4cNew.getDebitId(), "{\n" +
                    "    \"external_id\": \"" + Uuid_helper.generate_uuid() + "\",\n" +
                    "    \"operation_id\": \"" + c4cNew.getDebitId() + "\",\n" +
                    "    \"amount\": 100,\n" +
                    "    \"receiver\": {\n" +
                    "        \"source\": \"PAN\",\n" +
                    "        \"value\": \"" + Cards_data.getData(receiver, Card_param.pan) + "\"\n" +
                    "    }," +
                    "    \"identification\": {\n" +
                    "        \"requirements\": {\n" +
                    "            \"sender\": {\n" +
                    "                \"first_name\": \"firstname\",\n" +
                    "                \"last_name\": \"last\",\n" +
                    "                \"tax_id\": \"1234567890\",\n" +
                    "                 \"account_number\": \"UA953348510000026201112609803\"\n" +
                    "            },\n" +
                    "            \"recipient\": {\n" +
                    "                \"first_name\": \"test\",\n" +
                    "                \"last_name\": \"test\",\n" +
                    "                \"account_number\": \"UA953348510000026201112609803\"\n" +
                    "            },\n" +
                    "            \"details\": {\n" +
                    "            \n" +
                    "                \"submerchant_url\": \"https://jira.fuib.com/projects/PAYH/issues/PAYH-41057\",\n" +
                    "                \"independent_sales_organization_id\": \"3016715233\",\n" +
                    "                \"additional_message\": \"test\"\n" +
                    "            }\n" +
                    "        }\n" +
                    "    }\n" +
                    "}");
            waiter(30);
            if (i == 2) {
                Assert.assertTrue(c4cNew.getResponse().contains("Перевищено кількість зарахувань."));
            }else {
                c4cNew.statusEnroll(c4cNew.getDebitId(), c4cNew.getEnrollId());
            }
        }
        c4cNew.setExpectedStatus(200);
        c4cNew.refund(100);
        waiter(30);
        c4cNew.status();
        Assert.assertTrue(c4cNew.getResponse().contains("\"available_amount\":700"));
        Assert.assertTrue(c4cNew.getResponse().contains("\"total_refunded_amount\":100"));
        Assert.assertTrue(c4cNew.getResponse().contains("\"status\":\"ACTIVE\""));
    }

    public void negative_amountMore(){
        c4cNew.setExpectedStatus(200);
        c4cNew.initTransfers("{\n" +
                "    \"external_id\": \""+new Random().nextInt() +"\",\n" +
                "    \"amount\": 1000,\n" +
                "    \"description\": \"test description\",\n" +
                "    \"destination\": \"test destination\",\n" +
                "    \"payer\": {\n" +
                "        \"source\": \"PAN\",\n" +
                "        \"value\": \""+ Cards_data.getData(sender, Card_param.pan) +"\",\n" +
                "        \"expire\": \""+ Cards_data.getData(sender, Card_param.expire)+"\"\n" +
                "    },\n" +
                "    \"receiver\": {\n" +
                "        \"source\": \"PAN\",\n" +
                "        \"value\": \""+ Cards_data.getData(receiver, Card_param.pan)+"\"\n" +
                "    },"+
                "    \"identification\": {\n" +
                "        \"requirements\": {\n" +
                "            \"sender\": {\n" +
                "                \"first_name\": \"firstname\",\n" +
                "                \"last_name\": \"last\",\n" +
                "                \"tax_id\": \"1234567890\",\n" +
                "                 \"account_number\": \"UA953348510000026201112609803\"\n" +
                "            },\n" +
                "            \"recipient\": {\n" +
                "                \"first_name\": \"test\",\n" +
                "                \"last_name\": \"test\",\n" +
                "                \"account_number\": \"UA953348510000026201112609803\"\n" +
                "            },\n" +
                "            \"details\": {\n" +
                "                \"source\": \"07\",\n" +
                "                \"submerchant_url\": \"https://jira.fuib.com/projects/PAYH/issues/PAYH-41057\",\n" +
                "                \"independent_sales_organization_id\": \"3016715233\",\n" +
                "                \"additional_message\": \"test\"\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "}");
        waiter(30);
        c4cNew.status();
        Assert.assertTrue(c4cNew.getResponse().contains("\"available_amount\":1000"));
        Assert.assertTrue(c4cNew.getResponse().contains("\"status\":\"ACTIVE\""));

        c4cNew.setExpectedStatus(400);
        c4cNew.enroll(c4cNew.getDebitId(), "{\n" +
                "    \"external_id\": \""+ Uuid_helper.generate_uuid() +"\",\n" +
                "    \"operation_id\": \""+c4cNew.getDebitId()+"\",\n" +
                "    \"amount\": 1500,\n" +
                " \"identification\": {\n" +
                "        \"requirements\": {\n" +
                "            \"sender\": {\n" +
                "                \"first_name\": \"firstname\",\n" +
                "                \"last_name\": \"last\",\n" +
                "                \"tax_id\": \"1234567890\",\n" +
                "                 \"account_number\": \"UA953348510000026201112609803\"\n" +
                "            },\n" +
                "            \"recipient\": {\n" +
                "                \"first_name\": \"test\",\n" +
                "                \"last_name\": \"test\",\n" +
                "                \"account_number\": \"UA953348510000026201112609803\"\n" +
                "            },\n" +
                "            \"details\": {\n" +
                "            \n" +
                "                \"submerchant_url\": \"https://jira.fuib.com/projects/PAYH/issues/PAYH-41057\",\n" +
                "                \"independent_sales_organization_id\": \"3016715233\",\n" +
                "                \"additional_message\": \"test\"\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "}");
        Assert.assertTrue(c4cNew.getResponse().contains("\"code\":\"LIMIT_EXCEEDED\""));
        c4cNew.setExpectedStatus(200);
        c4cNew.status();

        for (int i = 0; i < 2; i++) {
            if (i == 1){
                c4cNew.setExpectedStatus(400);
            }
            c4cNew.enroll(c4cNew.getDebitId(), "{\n" +
                    "    \"external_id\": \"" + Uuid_helper.generate_uuid() + "\",\n" +
                    "    \"operation_id\": \"" + c4cNew.getDebitId() + "\",\n" +
                    "    \"amount\": 800,\n" +
                    "    \"receiver\": {\n" +
                    "        \"source\": \"PAN\",\n" +
                    "        \"value\": \"" + Cards_data.getData(receiver, Card_param.pan) + "\"\n" +
                    "    }," +
                    "    \"identification\": {\n" +
                    "        \"requirements\": {\n" +
                    "            \"sender\": {\n" +
                    "                \"first_name\": \"firstname\",\n" +
                    "                \"last_name\": \"last\",\n" +
                    "                \"tax_id\": \"1234567890\",\n" +
                    "                 \"account_number\": \"UA953348510000026201112609803\"\n" +
                    "            },\n" +
                    "            \"recipient\": {\n" +
                    "                \"first_name\": \"test\",\n" +
                    "                \"last_name\": \"test\",\n" +
                    "                \"account_number\": \"UA953348510000026201112609803\"\n" +
                    "            },\n" +
                    "            \"details\": {\n" +
                    "            \n" +
                    "                \"submerchant_url\": \"https://jira.fuib.com/projects/PAYH/issues/PAYH-41057\",\n" +
                    "                \"independent_sales_organization_id\": \"3016715233\",\n" +
                    "                \"additional_message\": \"test\"\n" +
                    "            }\n" +
                    "        }\n" +
                    "    }\n" +
                    "}");
            if (i == 1) {
                Assert.assertTrue(c4cNew.getResponse().contains("\"code\":\"LIMIT_EXCEEDED\""));
            }else {
                c4cNew.statusEnroll(c4cNew.getDebitId(), c4cNew.getEnrollId());
            }
        }
    }

    public void negative_withoutReceiver(){
        c4cNew.setExpectedStatus(200);
        c4cNew.initTransfers("{\n" +
                "    \"external_id\": \""+new Random().nextInt() +"\",\n" +
                "    \"amount\": 1000,\n" +
                "    \"commission\": 10,\n" +
                "    \"description\": \"test description\",\n" +
                "    \"destination\": \"test destination\",\n" +
                "    \"payer\": {\n" +
                "        \"source\": \"PAN\",\n" +
                "        \"value\": \""+ Cards_data.getData(sender, Card_param.pan) +"\",\n" +
                "        \"expire\": \""+ Cards_data.getData(sender, Card_param.expire)+"\"\n" +
                "    },\n" +
                "    \"identification\": {\n" +
                "        \"requirements\": {\n" +
                "            \"sender\": {\n" +
                "                \"first_name\": \"firstname\",\n" +
                "                \"last_name\": \"last\",\n" +
                "                \"tax_id\": \"1234567890\",\n" +
                "                 \"account_number\": \"UA953348510000026201112609803\"\n" +
                "            },\n" +
                "            \"recipient\": {\n" +
                "                \"first_name\": \"test\",\n" +
                "                \"last_name\": \"test\",\n" +
                "                \"account_number\": \"UA953348510000026201112609803\"\n" +
                "            },\n" +
                "            \"details\": {\n" +
                "                \"source\": \"07\",\n" +
                "                \"submerchant_url\": \"https://jira.fuib.com/projects/PAYH/issues/PAYH-41057\",\n" +
                "                \"independent_sales_organization_id\": \"3016715233\",\n" +
                "                \"additional_message\": \"test\"\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "}");
        waiter(20);
        c4cNew.status();
        Assert.assertTrue(c4cNew.getResponse().contains("\"available_amount\":1000"));
        Assert.assertTrue(c4cNew.getResponse().contains("\"status\":\"ACTIVE\""));

        c4cNew.enroll(c4cNew.getDebitId(), "{\n" +
                "    \"external_id\": \""+ Uuid_helper.generate_uuid() +"\",\n" +
                "    \"operation_id\": \""+c4cNew.getDebitId()+"\",\n" +
                "    \"amount\": 500,\n" +
                " \"identification\": {\n" +
                "        \"requirements\": {\n" +
                "            \"sender\": {\n" +
                "                \"first_name\": \"firstname\",\n" +
                "                \"last_name\": \"last\",\n" +
                "                \"tax_id\": \"1234567890\",\n" +
                "                 \"account_number\": \"UA953348510000026201112609803\"\n" +
                "            },\n" +
                "            \"recipient\": {\n" +
                "                \"first_name\": \"test\",\n" +
                "                \"last_name\": \"test\",\n" +
                "                \"account_number\": \"UA953348510000026201112609803\"\n" +
                "            },\n" +
                "            \"details\": {\n" +
                "            \n" +
                "                \"submerchant_url\": \"https://jira.fuib.com/projects/PAYH/issues/PAYH-41057\",\n" +
                "                \"independent_sales_organization_id\": \"3016715233\",\n" +
                "                \"additional_message\": \"test\"\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "}");
        waiter(20);
        c4cNew.statusEnroll(c4cNew.getDebitId(), c4cNew.getEnrollId());
        Assert.assertTrue(c4cNew.getResponse().contains("\"status\":\"REJECTED\""));
        c4cNew.refund(100);
        waiter(20);
        c4cNew.statusRefund();
        Assert.assertTrue(c4cNew.getResponse().contains("\"status\":\"PROCESSED\""));

        c4cNew.status();
        Assert.assertTrue(c4cNew.getResponse().contains("\"available_amount\":400"));
        Assert.assertTrue(c4cNew.getResponse().contains("\"total_refunded_amount\":100"));
        Assert.assertTrue(c4cNew.getResponse().contains("\"status\":\"ACTIVE\""));
    }

    public void negative_withoutSender(){

        c4cNew.setExpectedStatus(400);
        c4cNew.initTransfers("{\n" +
                "    \"external_id\": \""+new Random().nextInt() +"\",\n" +
                "    \"amount\": 1000,\n" +
                "    \"commission\": 10,\n" +
                "    \"description\": \"test description\",\n" +
                "    \"destination\": \"test destination\",\n" +
                "    \"receiver\": {\n" +
                "        \"source\": \"PAN\",\n" +
                "        \"value\": \""+ Cards_data.getData(receiver, Card_param.pan)+"\"\n" +
                "    },"+
                "    \"identification\": {\n" +
                "        \"requirements\": {\n" +
                "            \"sender\": {\n" +
                "                \"first_name\": \"firstname\",\n" +
                "                \"last_name\": \"last\",\n" +
                "                \"tax_id\": \"1234567890\",\n" +
                "                 \"account_number\": \"UA953348510000026201112609803\"\n" +
                "            },\n" +
                "            \"recipient\": {\n" +
                "                \"first_name\": \"test\",\n" +
                "                \"last_name\": \"test\",\n" +
                "                \"account_number\": \"UA953348510000026201112609803\"\n" +
                "            },\n" +
                "            \"details\": {\n" +
                "                \"source\": \"07\",\n" +
                "                \"submerchant_url\": \"https://jira.fuib.com/projects/PAYH/issues/PAYH-41057\",\n" +
                "                \"independent_sales_organization_id\": \"3016715233\",\n" +
                "                \"additional_message\": \"test\"\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "}");
        waiter(20);
        c4cNew.status();
        Assert.assertTrue(c4cNew.getResponse().contains("\"available_amount\":1000"));
        Assert.assertTrue(c4cNew.getResponse().contains("\"status\":\"ACTIVE\""));
    }

    public void positive_with3ds(){

        c4cNew.setExpectedStatus(200);
        c4cNew.initTransfers("{\n" +
                "    \"external_id\": \""+new Random().nextInt() +"\",\n" +
                "    \"amount\": 1000,\n" +
                "    \"commission\": 10,\n" +
                "    \"description\": \"test description\",\n" +
                "    \"destination\": \"test destination\",\n" +
                "    \"payer\": {\n" +
                "        \"source\": \"PAN\",\n" +
                "        \"value\": \""+ Cards_data.getData(sender, Card_param.pan) +"\",\n" +
                "        \"expire\": \""+ Cards_data.getData(sender, Card_param.expire)+"\"\n" +
                "    },\n" +
                "    \"receiver\": {\n" +
                "        \"source\": \"PAN\",\n" +
                "        \"value\": \""+ Cards_data.getData(sender, Card_param.pan)+"\"\n" +
                "    },"+
                "    \"identification\": {\n" +
                "        \"requirements\": {\n" +
                "            \"sender\": {\n" +
                "                \"first_name\": \"firstname\",\n" +
                "                \"last_name\": \"last\",\n" +
                "                \"tax_id\": \"1234567890\",\n" +
                "                 \"account_number\": \"UA953348510000026201112609803\"\n" +
                "            },\n" +
                "            \"recipient\": {\n" +
                "                \"first_name\": \"test\",\n" +
                "                \"last_name\": \"test\",\n" +
                "                \"account_number\": \"UA953348510000026201112609803\"\n" +
                "            },\n" +
                "            \"details\": {\n" +
                "                \"source\": \"07\",\n" +
                "                \"submerchant_url\": \"https://jira.fuib.com/projects/PAYH/issues/PAYH-41057\",\n" +
                "                \"independent_sales_organization_id\": \"3016715233\",\n" +
                "                \"additional_message\": \"test\"\n" +
                "            }\n" +
                "        }\n" +
                "    },\n" +
                ThreeDS.threeDS_2_2_0+
                "}");
        waiter(20);
        c4cNew.status();
        Assert.assertTrue(c4cNew.getResponse().contains("\"status\":\"PENDING\""));
        c4cNew.makeThreeDS();

        waiter(30);
        c4cNew.status();
        Assert.assertTrue(c4cNew.getResponse().contains("\"status\":\"ACTIVE\""));

        c4cNew.enroll(c4cNew.getDebitId(), "{\n" +
                "    \"external_id\": \""+ Uuid_helper.generate_uuid() +"\",\n" +
                "    \"operation_id\": \""+c4cNew.getDebitId()+"\",\n" +
                "    \"amount\": 500,\n" +
                " \"identification\": {\n" +
                "        \"requirements\": {\n" +
                "            \"sender\": {\n" +
                "                \"first_name\": \"firstname\",\n" +
                "                \"last_name\": \"last\",\n" +
                "                \"tax_id\": \"1234567890\",\n" +
                "                 \"account_number\": \"UA953348510000026201112609803\"\n" +
                "            },\n" +
                "            \"recipient\": {\n" +
                "                \"first_name\": \"test\",\n" +
                "                \"last_name\": \"test\",\n" +
                "                \"account_number\": \"UA953348510000026201112609803\"\n" +
                "            },\n" +
                "            \"details\": {\n" +
                "            \n" +
                "                \"submerchant_url\": \"https://jira.fuib.com/projects/PAYH/issues/PAYH-41057\",\n" +
                "                \"independent_sales_organization_id\": \"3016715233\",\n" +
                "                \"additional_message\": \"test\"\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "}");
        waiter(20);
        c4cNew.statusEnroll(c4cNew.getDebitId(), c4cNew.getEnrollId());
    }

    public void negative_refundAmountMore(){
        c4cNew.setExpectedStatus(200);
        c4cNew.initTransfers("{\n" +
                "    \"external_id\": \""+new Random().nextInt() +"\",\n" +
                "    \"amount\": 1000,\n" +
                "    \"commission\": 10,\n" +
                "    \"description\": \"test description\",\n" +
                "    \"destination\": \"test destination\",\n" +
                "    \"payer\": {\n" +
                "        \"source\": \"PAN\",\n" +
                "        \"value\": \""+ Cards_data.getData(sender, Card_param.pan) +"\",\n" +
                "        \"expire\": \""+ Cards_data.getData(sender, Card_param.expire)+"\"\n" +
                "    },\n" +
                "    \"receiver\": {\n" +
                "        \"source\": \"PAN\",\n" +
                "        \"value\": \""+ Cards_data.getData(sender, Card_param.pan)+"\"\n" +
                "    },"+
                "    \"identification\": {\n" +
                "        \"requirements\": {\n" +
                "            \"sender\": {\n" +
                "                \"first_name\": \"firstname\",\n" +
                "                \"last_name\": \"last\",\n" +
                "                \"tax_id\": \"1234567890\",\n" +
                "                 \"account_number\": \"UA953348510000026201112609803\"\n" +
                "            },\n" +
                "            \"recipient\": {\n" +
                "                \"first_name\": \"test\",\n" +
                "                \"last_name\": \"test\",\n" +
                "                \"account_number\": \"UA953348510000026201112609803\"\n" +
                "            },\n" +
                "            \"details\": {\n" +
                "                \"source\": \"07\",\n" +
                "                \"submerchant_url\": \"https://jira.fuib.com/projects/PAYH/issues/PAYH-41057\",\n" +
                "                \"independent_sales_organization_id\": \"3016715233\",\n" +
                "                \"additional_message\": \"test\"\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "}");
        waiter(30);
        c4cNew.status();
        Assert.assertTrue(c4cNew.getResponse().contains("\"available_amount\":1000"));
        Assert.assertTrue(c4cNew.getResponse().contains("\"status\":\"ACTIVE\""));

        c4cNew.enroll(c4cNew.getDebitId(), "{\n" +
                "    \"external_id\": \""+ Uuid_helper.generate_uuid() +"\",\n" +
                "    \"operation_id\": \""+c4cNew.getDebitId()+"\",\n" +
                "    \"amount\": 500,\n" +
                " \"identification\": {\n" +
                "        \"requirements\": {\n" +
                "            \"sender\": {\n" +
                "                \"first_name\": \"firstname\",\n" +
                "                \"last_name\": \"last\",\n" +
                "                \"tax_id\": \"1234567890\",\n" +
                "                 \"account_number\": \"UA953348510000026201112609803\"\n" +
                "            },\n" +
                "            \"recipient\": {\n" +
                "                \"first_name\": \"test\",\n" +
                "                \"last_name\": \"test\",\n" +
                "                \"account_number\": \"UA953348510000026201112609803\"\n" +
                "            },\n" +
                "            \"details\": {\n" +
                "            \n" +
                "                \"submerchant_url\": \"https://jira.fuib.com/projects/PAYH/issues/PAYH-41057\",\n" +
                "                \"independent_sales_organization_id\": \"3016715233\",\n" +
                "                \"additional_message\": \"test\"\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "}");
        waiter(30);
        c4cNew.statusEnroll(c4cNew.getDebitId(), c4cNew.getEnrollId());
        Assert.assertTrue(c4cNew.getResponse().contains("\"status\":\"ENROLLED\""));

        c4cNew.setExpectedStatus(400);
        c4cNew.refund(501);
        Assert.assertTrue(c4cNew.getResponse().contains("\"code\":\"LIMIT_EXCEEDED\""));

        c4cNew.setExpectedStatus(200);
        c4cNew.status();
        Assert.assertTrue(c4cNew.getResponse().contains("\"available_amount\":500"));
        Assert.assertTrue(c4cNew.getResponse().contains("\"total_refunded_amount\":0"));
        Assert.assertTrue(c4cNew.getResponse().contains("\"status\":\"ACTIVE\""));
    }

    public void negative_initNotActive_and_thanEnroll(){

        c4cNew.setExpectedStatus(200);
        c4cNew.initTransfers("{\n" +
                "    \"external_id\": \""+new Random().nextInt() +"\",\n" +
                "    \"amount\": 1000,\n" +
                "    \"commission\": 10,\n" +
                "    \"description\": \"test description\",\n" +
                "    \"destination\": \"test destination\",\n" +
                "    \"payer\": {\n" +
                "        \"source\": \"PAN\",\n" +
                "        \"value\": \""+ Cards_data.getData(sender, Card_param.pan) +"\",\n" +
                "        \"expire\": \""+ Cards_data.getData(sender, Card_param.expire)+"\"\n" +
                "    },\n" +
                "    \"receiver\": {\n" +
                "        \"source\": \"PAN\",\n" +
                "        \"value\": \""+ Cards_data.getData(receiver, Card_param.pan)+"\"\n" +
                "    },"+
                "    \"identification\": {\n" +
                "        \"requirements\": {\n" +
                "            \"sender\": {\n" +
                "                \"first_name\": \"firstname\",\n" +
                "                \"last_name\": \"last\",\n" +
                "                \"tax_id\": \"1234567890\",\n" +
                "                 \"account_number\": \"UA953348510000026201112609803\"\n" +
                "            },\n" +
                "            \"recipient\": {\n" +
                "                \"first_name\": \"test\",\n" +
                "                \"last_name\": \"test\",\n" +
                "                \"account_number\": \"UA953348510000026201112609803\"\n" +
                "            },\n" +
                "            \"details\": {\n" +
                "                \"source\": \"07\",\n" +
                "                \"submerchant_url\": \"https://jira.fuib.com/projects/PAYH/issues/PAYH-41057\",\n" +
                "                \"independent_sales_organization_id\": \"3016715233\",\n" +
                "                \"additional_message\": \"test\"\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "}");
        c4cNew.status();

        c4cNew.setExpectedStatus(400);
        c4cNew.enroll(c4cNew.getDebitId(), "{\n" +
                "    \"external_id\": \""+ Uuid_helper.generate_uuid() +"\",\n" +
                "    \"operation_id\": \""+c4cNew.getDebitId()+"\",\n" +
                "    \"amount\": 500,\n" +
                " \"identification\": {\n" +
                "        \"requirements\": {\n" +
                "            \"sender\": {\n" +
                "                \"first_name\": \"firstname\",\n" +
                "                \"last_name\": \"last\",\n" +
                "                \"tax_id\": \"1234567890\",\n" +
                "                 \"account_number\": \"UA953348510000026201112609803\"\n" +
                "            },\n" +
                "            \"recipient\": {\n" +
                "                \"first_name\": \"test\",\n" +
                "                \"last_name\": \"test\",\n" +
                "                \"account_number\": \"UA953348510000026201112609803\"\n" +
                "            },\n" +
                "            \"details\": {\n" +
                "            \n" +
                "                \"submerchant_url\": \"https://jira.fuib.com/projects/PAYH/issues/PAYH-41057\",\n" +
                "                \"independent_sales_organization_id\": \"3016715233\",\n" +
                "                \"additional_message\": \"test\"\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "}");
        Assert.assertTrue(c4cNew.getResponse().contains("\"message\":\"Переказ неактивний.\""));

    }

    private void waiter(int sec){
        try {
            Thread.sleep(sec*1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void erw(){
        c4cNew.statusEnroll("5c93adf3-cedb-4bf6-927d-2fde8d7d09de", "d16c23a7-c140-422c-a516-31d7b7b1b581");
    }
}
