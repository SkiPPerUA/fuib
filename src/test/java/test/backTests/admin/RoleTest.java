package test.backTests.admin;

import org.example.qaTransactionTeam.BaseTest;
import org.example.qaTransactionTeam.backEnd.admin.Role;
import org.example.qaTransactionTeam.backEnd.helper.Uuid_helper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Test
public class RoleTest extends BaseTest {

    Role role = new Role();
    String body;
    String client_id;
    String current_data;

    public void getGroups_positive_test(){
        role.setStatusCode(200);
        role.getGroups();
        String response = role.getResponse();
        Assert.assertEquals(new JSONArray(response).length(), 2);
        Assert.assertTrue(response.contains("\"name\":\"phacq\""));
        Assert.assertTrue(response.contains("\"name\":\"phadmp_pbl\""));
    }

    public void getRoles_positive_test(){
        role.setStatusCode(200);
        role.getRoles();
        JSONObject json = new JSONObject(role.getResponse());
        Assert.assertEquals(json.getJSONArray("acquiring").length(), 2);
        Assert.assertEquals(json.getJSONArray("smart_pay").length(), 3);
    }

    public void getAdmins_positive_test(){
        role.setStatusCode(200);
        role.getAdmins();

        Map<String, String> query = new HashMap<>();
        query.put("limit","5");
        role.getAdmins(query);
        Assert.assertEquals(new JSONArray(role.getResponse()).length(),5);

        query.put("msisdn","380502079999");
        role.getAdmins(query);
        JSONArray array = new JSONArray(role.getResponse());
        Assert.assertTrue(array.length() <= 5);
        array.toList().forEach(x -> Assert.assertTrue(x.toString().contains("msisdn=380502079999")));

        query.put("client_id","c3bf0895-8923-410c-80ce-f97a8a9b301c");
        role.getAdmins(query);
        array = new JSONArray(role.getResponse());
        Assert.assertTrue(array.length() <= 5);
        array.toList().forEach(x -> Assert.assertTrue(x.toString().contains("client_id=c3bf0895-8923-410c-80ce-f97a8a9b301c")));
    }

    public void getAdminsById_positive_test(){
        role.setStatusCode(200);
        role.getAdmins();
        JSONArray array = new JSONArray(role.getResponse());
        Assert.assertTrue(array.length() > 1);
        String id = array.getJSONObject(1).getString("id");
        role.getAdmins(id);
        Assert.assertTrue(role.getResponse().contains(id));
    }

    public void getAdminsById_negative_test(){
        role.setStatusCode(500);
        logStartTest("Id is empty"); role.getAdmins("");
        logStartTest("Id wrong format"); role.getAdmins("123");
        logStartTest("Id is null"); role.getAdmins("null");
        logStartTest("Id is default"); role.getAdmins("default");
        role.setStatusCode(400);
        logStartTest("Id is random"); role.getAdmins(Uuid_helper.generate_uuid());
    }

    public void addAdmins_positive_test_all_fields(){
        role.setStatusCode(200);
        client_id = Uuid_helper.generate_uuid();
        current_data = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"));
        role.getAdmins();
        Assert.assertFalse(role.getResponse().contains(client_id));
        body = "{\n" +
                "  \"client_id\": \""+client_id+"\",\n" +
                "  \"status\": \"ACTIVE\",\n" +
                "  \"msisdn\": \"380502079999\",\n" +
                "  \"login\": \""+current_data+"\",\n" +
                "  \"email\": \"string\",\n" +
                "  \"title\": \"string\",\n" +
                "  \"roles\": {\n" +
                "    \"acquiring\": [\n" +
                "      {\n" +
                "        \"id\": \"string\",\n" +
                "        \"name\": \"string\",\n" +
                "        \"description\": \"string\",\n" +
                "        \"assigned\": true\n" +
                "      }\n" +
                "    ],\n" +
                "    \"smart_pay\": [\n" +
                "      {\n" +
                "        \"id\": \"string\",\n" +
                "        \"name\": \"string\",\n" +
                "        \"description\": \"string\",\n" +
                "        \"assigned\": true\n" +
                "      }\n" +
                "    ]\n" +
                "  }}";
        role.addAdmins(body);
        role.getAdmins();
        Assert.assertTrue(role.getResponse().contains(client_id));
        Assert.assertTrue(role.getResponse().contains(current_data));
    }

    public void addAdmins_positive_test_only_mandatory_fields(){
        role.setStatusCode(200);
        client_id = Uuid_helper.generate_uuid();
        current_data = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"));
        role.getAdmins();
        Assert.assertFalse(role.getResponse().contains(client_id));
        body = "{\n" +
                "  \"client_id\": \""+client_id+"\",\n" +
                "  \"msisdn\": \"380502079999\",\n" +
                "  \"login\": \""+current_data+"\"\n" +
                "}";
        role.addAdmins(body);
        role.getAdmins();
        Assert.assertTrue(role.getResponse().contains(client_id));
        Assert.assertTrue(role.getResponse().contains(current_data));
    }

    public void addAdmins_negative_test(){
        role.setStatusCode(400);
        client_id = Uuid_helper.generate_uuid();
        current_data = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"));
        logStartTest("Without client_id");
        body = "{\n" +
                "  \"msisdn\": \"380502079999\",\n" +
                "  \"login\": \""+current_data+"\",\n" +
                "  \"roles\": {\n" +
                "    \"acquiring\": [\n" +
                "      {\n" +
                "        \"id\": \"string\",\n" +
                "        \"name\": \"string\",\n" +
                "        \"description\": \"string\"\n" +
                "      }\n" +
                "    ],\n" +
                "    \"smart_pay\": [\n" +
                "      {\n" +
                "        \"id\": \"string\",\n" +
                "        \"name\": \"string\",\n" +
                "        \"description\": \"string\"\n" +
                "      }\n" +
                "    ]\n" +
                "  }}";
        role.addAdmins(body);

        logStartTest("Without msisdn");
        body = "{\n" +
                "  \"client_id\": \""+client_id+"\",\n" +
                "  \"login\": \""+current_data+"\",\n" +
                "  \"roles\": {\n" +
                "    \"acquiring\": [\n" +
                "      {\n" +
                "        \"id\": \"string\",\n" +
                "        \"name\": \"string\",\n" +
                "        \"description\": \"string\"\n" +
                "      }\n" +
                "    ],\n" +
                "    \"smart_pay\": [\n" +
                "      {\n" +
                "        \"id\": \"string\",\n" +
                "        \"name\": \"string\",\n" +
                "        \"description\": \"string\"\n" +
                "      }\n" +
                "    ]\n" +
                "  }}";
        role.addAdmins(body);

        logStartTest("Without login");
        body = "{\n" +
                "  \"client_id\": \""+client_id+"\",\n" +
                "  \"msisdn\": \"380502079999\",\n" +
                "  \"roles\": {\n" +
                "    \"acquiring\": [\n" +
                "      {\n" +
                "        \"id\": \"string\",\n" +
                "        \"name\": \"string\",\n" +
                "        \"description\": \"string\"\n" +
                "      }\n" +
                "    ],\n" +
                "    \"smart_pay\": [\n" +
                "      {\n" +
                "        \"id\": \"string\",\n" +
                "        \"name\": \"string\",\n" +
                "        \"description\": \"string\"\n" +
                "      }\n" +
                "    ]\n" +
                "  }}";
        role.addAdmins(body);

        logStartTest("Phone is null");
        body = "{\n" +
                "  \"client_id\": \""+client_id+"\",\n" +
                "  \"msisdn\": \"null\",\n" +
                "  \"login\": \""+current_data+"\"\n" +
                "}";
        role.addAdmins(body);

        logStartTest("Phone is empty");
        body = "{\n" +
                "  \"client_id\": \""+client_id+"\",\n" +
                "  \"msisdn\": \"\",\n" +
                "  \"login\": \""+current_data+"\"\n" +
                "}";
        role.addAdmins(body);

        logStartTest("Phone is wrong format > 12");
        body = "{\n" +
                "  \"client_id\": \""+client_id+"\",\n" +
                "  \"msisdn\": \"3805020799999999\",\n" +
                "  \"login\": \""+current_data+"\"\n" +
                "}";
        role.addAdmins(body);

        logStartTest("Phone is wrong format < 12");
        body = "{\n" +
                "  \"client_id\": \""+client_id+"\",\n" +
                "  \"msisdn\": \"380502079\",\n" +
                "  \"login\": \""+current_data+"\"\n" +
                "}";
        role.addAdmins(body);

        logStartTest("Phone is default");
        body = "{\n" +
                "  \"client_id\": \""+client_id+"\",\n" +
                "  \"msisdn\": \"default\",\n" +
                "  \"login\": \""+current_data+"\"\n" +
                "}";
        role.addAdmins(body);

        logStartTest("Phone with +");
        body = "{\n" +
                "  \"client_id\": \""+client_id+"\",\n" +
                "  \"msisdn\": \"+380502079999\",\n" +
                "  \"login\": \""+current_data+"\"\n" +
                "}";
        role.addAdmins(body);

        logStartTest("Login is empty");
        body = "{\n" +
                "  \"client_id\": \""+client_id+"\",\n" +
                "  \"msisdn\": \"+380502079999\",\n" +
                "  \"login\": \"\"\n" +
                "}";
        role.addAdmins(body);

        logStartTest("Login wrong format");
        body = "{\n" +
                "  \"client_id\": \""+client_id+"\",\n" +
                "  \"msisdn\": \"+380502079999\",\n" +
                "  \"login\": \"3123#!@$ #@$@#re\"\n" +
                "}";
        role.addAdmins(body);

        logStartTest("Login is null");
        body = "{\n" +
                "  \"client_id\": \""+client_id+"\",\n" +
                "  \"msisdn\": \"+380502079999\",\n" +
                "  \"login\": null\n" +
                "}";
        role.addAdmins(body);

        logStartTest("Login is default");
        body = "{\n" +
                "  \"client_id\": \""+client_id+"\",\n" +
                "  \"msisdn\": \"+380502079999\",\n" +
                "  \"login\": default\n" +
                "}";
        role.addAdmins(body);

        role.setStatusCode(500);
        logStartTest("Client_id is null");
        body = "{\n" +
                "  \"client_id\": \"null\",\n" +
                "  \"msisdn\": \"380502079999\",\n" +
                "  \"login\": \""+current_data+"\"\n" +
                "}";
        role.addAdmins(body);

        role.setStatusCode(400);
        logStartTest("Client_id is wrong format");
        body = "{\n" +
                "  \"client_id\": \"123\",\n" +
                "  \"msisdn\": \"380502079999\",\n" +
                "  \"login\": \""+current_data+"\"\n" +
                "}";
        role.addAdmins(body);

        logStartTest("Client_id is default");
        body = "{\n" +
                "  \"client_id\": \"default\",\n" +
                "  \"msisdn\": \"380502079999\",\n" +
                "  \"login\": \""+current_data+"\"\n" +
                "}";
        role.addAdmins(body);

        logStartTest("Client_id is empty");
        body = "{\n" +
                "  \"client_id\": \"\",\n" +
                "  \"msisdn\": \"380502079999\",\n" +
                "  \"login\": \""+current_data+"\"\n" +
                "}";
        role.addAdmins(body);
    }

    public void updateAdmins_positive_test_all_fields(){
        role.setStatusCode(200);
        client_id = Uuid_helper.generate_uuid();
        String old_data = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"));
        role.getAdmins();
        Assert.assertFalse(role.getResponse().contains(client_id));
        body = "{\n" +
                "  \"client_id\": \""+client_id+"\",\n" +
                "  \"status\": \"ACTIVE\",\n" +
                "  \"msisdn\": \"380502079999\",\n" +
                "  \"login\": \"Login"+old_data+"\",\n" +
                "  \"email\": \"Email"+old_data+"\",\n" +
                "  \"title\": \"Title"+old_data+"\",\n" +
                "  \"roles\": {\n" +
                "    \"acquiring\": [\n" +
                "      {\n" +
                "        \"id\": \"string\",\n" +
                "        \"name\": \"string\",\n" +
                "        \"description\": \"string\",\n" +
                "        \"assigned\": true\n" +
                "      }\n" +
                "    ],\n" +
                "    \"smart_pay\": [\n" +
                "      {\n" +
                "        \"id\": \"string\",\n" +
                "        \"name\": \"string\",\n" +
                "        \"description\": \"string\",\n" +
                "        \"assigned\": true\n" +
                "      }\n" +
                "    ]\n" +
                "  }}";
        role.addAdmins(body);
        role.getAdmins();
        Assert.assertTrue(role.getResponse().contains(client_id));
        Assert.assertTrue(role.getResponse().contains("Login"+old_data));
        Assert.assertTrue(role.getResponse().contains("Email"+old_data));
        Assert.assertTrue(role.getResponse().contains("Title"+old_data));

        String new_data = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"));
        body = "{\n" +
                "  \"client_id\": \""+client_id+"\",\n" +
                "  \"status\": \"DELETED\",\n" +
                "  \"msisdn\": \"380502079998\",\n" +
                "  \"login\": \"Login"+new_data+"\",\n" +
                "  \"email\": \"Email"+new_data+"\",\n" +
                "  \"title\": \"Title"+new_data+"\",\n" +
                "  \"roles\": {\n" +
                "    \"acquiring\": [\n" +
                "      {\n" +
                "        \"id\": \"string\",\n" +
                "        \"name\": \"string\",\n" +
                "        \"description\": \"string\",\n" +
                "        \"assigned\": true\n" +
                "      }\n" +
                "    ],\n" +
                "    \"smart_pay\": [\n" +
                "      {\n" +
                "        \"id\": \"string\",\n" +
                "        \"name\": \"string\",\n" +
                "        \"description\": \"string\",\n" +
                "        \"assigned\": true\n" +
                "      }\n" +
                "    ]\n" +
                "  }}";
        role.updateAdmins(role.getId(), body);
        role.getAdmins(role.getId());
        Assert.assertTrue(role.getResponse().contains("DELETED"));
        Assert.assertTrue(role.getResponse().contains("Login"+new_data));
        Assert.assertTrue(role.getResponse().contains("Email"+new_data));
        Assert.assertTrue(role.getResponse().contains("Title"+new_data));
    }

    @Test(dependsOnMethods = "addAdmins_positive_test_only_mandatory_fields")
    public void updateAdmins_negative(){
        role.setStatusCode_put(400);

        logStartTest("Without acquiring");
        body = "{\n" +
                "  \"roles\": {\n" +
                "    \"smart_pay\": [\n" +
                "      {\n" +
                "        \"id\": \"string\",\n" +
                "        \"name\": \"string\",\n" +
                "        \"description\": \"string\"\n" +
                "      }\n" +
                "    ]\n" +
                "  }}";
        role.updateAdmins(role.getId(), body);

        logStartTest("Without smart_pay");
        body = "{\n" +
                "  \"roles\": {\n" +
                "    \"acquiring\": [\n" +
                "      {\n" +
                "        \"id\": \"string\",\n" +
                "        \"name\": \"string\",\n" +
                "        \"description\": \"string\"\n" +
                "      }\n" +
                "    ]\n" +
                "  }}";
        role.updateAdmins(role.getId(), body);
    }
}
