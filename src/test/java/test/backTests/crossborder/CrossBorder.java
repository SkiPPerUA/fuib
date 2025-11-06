package test.backTests.crossborder;

import io.restassured.http.ContentType;
import org.example.qaTransactionTeam.BaseTest;
import org.example.qaTransactionTeam.backEnd.dazzler.Crossborder;
import org.example.qaTransactionTeam.backEnd.utils.*;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

@Test
public class CrossBorder extends BaseTest {

    Crossborder crossborder = new Crossborder(); //022773408997     021895374890 ФУНТ

    public void test_crossborder(){
        String body = "{\n" +
                "    \"amount\": 120,\n" +
                "    \"ekb_id\": 2290175,\n" + //8531524  2189387  544881  2290175
                "    \"expire\": \"2507\",\n" +
                "    \"authentication\":{\n" +
                "      \"device_id\":\"device_idVladTest\",\n" +
                "      \"session_id\":\"VladTest\",\n" +
                "      \"ip\":\"79.110.129.18\",\n" +
                "      \"event_type\":\"APP_A2C\"\n" +
                "   }," +
                //"    \"sender_card_id_uah\": \"021129845704\",\n" +      // 2901 мастер евро        2506  мастер евро       2507   виза дол         2608 виза евро     2902 мастер дол  2210 мастер злот  2511 злот
                "    \"sender_card_id\": \"028417738202\",\n" +       //022773443990            017813552327            018063400990            021016415798       023079553334     012956728491      018553337602
                "    \"receiver_card_number\": \"5575191548185686\"\n" +//5575191548185686      4596610012333736    //США 4400667784881736 5312570067325344   4569332829625799
                "}\n";
        for (int i = 0; i < 1; i++){
            crossborder.makeTrans(body);
        }
//        try {
//            BDpostgre.BDpostgre("ph-ms-db.test-fuib.com:5000/","crossborder_manager", "dev","password");
//            BDpostgre.updateSQL("update crossborder.transactions set created_at = '2025-04-08 10:58:17.081 +0300' where created_at > '2025-04-10 10:58:16.081 +0300'");
//        } catch (Throwable e) {
//            throw new RuntimeException(e);
//        } finally {
//            try {
//                BDpostgre.closeConn();
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//        }
    }

    public void test_new_structure_ITM(){
        crossborder.makeTrans("{\n" +
                "    \"amount\": 200,\n" +
                "    \"ekb_id\": 13718887,\n" +
                "    \"expire\": \"2507\",\n" +
                "    \"sender_card_id\": \"018063400990\",\n" +
                "    \"recipient\": {\n" +
                "          \"source\":\"ITM\",\n" +
                "          \"value\":\"?C98E71KYFMYC589\"\n" +
                "          }" +
                "}\n");
    }

    public void test_new_structure_PAN(){
        crossborder.makeTrans("{\n" +
                "    \"amount\": 200,\n" +
                "    \"ekb_id\": 13718887,\n" +
                "    \"expire\": \"2507\",\n" +
                "    \"sender_card_id\": \"018063400990\",\n" +
                "    \"recipient\": {\n" +
                "          \"source\":\"PAN\",\n" +
                "          \"value\":\"5575191548185686\"\n" +
                "          }" +
                "}\n");
    }

    public void regress_all_currency(){
        List<List<String>> cases = List.of(List.of("usd","2507","018063400990"),List.of("euro","2901","022773443990"),List.of("zlot","2511","018553337602"));
        for (List<String> list : cases){
            String body = "{\n" +
                    "    \"amount\":200,\n" +
                    "    \"ekb_id\": 2290175,\n" +
                    "    \"expire\": \""+list.get(1)+"\",\n" +
                    "    \"sender_card_id\": \""+list.get(2)+"\",\n" +
                    "    \"receiver_card_number\": \"5575191548185686\"\n" +
                    "}\n";
            logger.info(list.get(0));
            crossborder.makeTrans(body);
        }
    }

    public void test_checkCardCountries(){
        crossborder.checkCardCountries("5545931006939729");
        crossborder.checkCardCountries(Cards_data.getData(Card.MONO_MC, Card_param.pan));
    }

    public void cros(){
        List.of(3373717,
                13044079,
                6766663,
                2290175,
                2290189,
                8531524,
                9617499,
                2866149,
                2290176,
                7651730,
                12322606,
                3281742,
                9163270,
                11120648,
                6011742,
                74369,
                4416667,
                5928572).forEach(x-> {String body1 = "{\n" +
                "    \"amount\": 100,\n" +
                "    \"ekb_id\": "+x+",\n" +
                "    \"expire\": \"2507\",\n" +
                "    \"sender_card_id\": \"022773443990\",\n" +
                "    \"receiver_card_number\": \"5575191548185686\"\n" +
                "}\n";
        crossborder.makeTrans(body1);
                    System.out.println(x+" "+ crossborder.getTransId());}
        );
    }

    public void cross_rpc(){
        RabbitMQ_http rabbitMQHttp = new RabbitMQ_http("CreateCrossborderSettlement","C2CRestRPC.input");
        rabbitMQHttp.sendHttp("{" +
                "           \"sender_card_id\": \"018148569931\"," + // 028417738202
                "           \"ekb_id\": 9163270," +                  // 2290175
                "           \"amount\": 100," +
                "           \"recipient\": {" +
                "               \"source\": \"PAN\"," +
                "               \"value\": \"5575191548185686\"" +
                "       }," +
                "           \"device_id\": \"62dfcb03ae5b63192b88578e\"," +
                "           \"session_id\": \"1747122813\"," +
                "           \"ip\": \"31.131.120.41\"," +
                "           \"receiver_name\": \"fsdfs\"," +
                "           \"receiver_last_name\": \"fdfdsdsa\"," +
                "           \"jwt\": \"eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJMU1dxSEYzWUF0UDkzMmNXdm5ESXBzOHhqaHRQeWxiN0JOV2prVWhqcVhrIn0.eyJleHAiOjE3MjA3OTIxMzksImlhdCI6MTcyMDc5MTIzOSwianRpIjoiZjI4ZTc0YjktMDg4Ny00N2YyLWE3YjUtYmE1ZjEzYTg2MGQzIiwiaXNzIjoiaHR0cHM6Ly9hdXRoLnRlc3QtZnVpYi5jb20vYXV0aC9yZWFsbXMvcHVtYiIsImF1ZCI6WyJ0cmFuc2FjdGVyIiwiUEhUUk4iLCJPREIiLCJhY2NvdW50Il0sInN1YiI6ImZjZDViODMzLTUzMmMtNDg5Ny1hOGVjLWMzNTdlZGE4YzdlMiIsInR5cCI6IkJlYXJlciIsImF6cCI6InRyYW5zYWN0ZXIiLCJzZXNzaW9uX3N0YXRlIjoiNzg2ZWM4NjEtM2Y5My00ZmMxLTkzMzYtMWU1ZDg4ZTI4MzViIiwicmVhbG1fYWNjZXNzIjp7InJvbGVzIjpbIm9mZmxpbmVfYWNjZXNzIiwidW1hX2F1dGhvcml6YXRpb24iXX0sInJlc291cmNlX2FjY2VzcyI6eyJQSFRSTiI6eyJyb2xlcyI6WyJwaHVudHIiLCJwaGNvbSIsInBoX3NlIiwicGhfY2Jfb3AiLCJwaF9jdV9leCIsInBoX3BuX3RrbiIsInBoX3N3X3JlcSJdfSwidHJhbnNhY3RlciI6eyJyb2xlcyI6WyJwaF9wcF9hZG0iLCJwaF9kaW5mIiwicGhfcHBfb3AiXX0sIk9EQiI6eyJyb2xlcyI6WyJhY2NfbG10X3IiLCJwYXlfZG9jX2NydCIsInBheV9kb2NfZGVsIl19LCJhY2NvdW50Ijp7InJvbGVzIjpbIm1hbmFnZS1hY2NvdW50IiwibWFuYWdlLWFjY291bnQtbGlua3MiLCJ2aWV3LXByb2ZpbGUiXX19LCJzY29wZSI6IiIsInNpZCI6Ijc4NmVjODYxLTNmOTMtNGZjMS05MzM2LTFlNWQ4OGUyODM1YiIsIm1lcmNoYW50X2lkIjoiMTA1NDYxOTctMGQyZi00MDU5LWI5YTItZDAxY2I5N2ViYTYxIiwicHJlZmVycmVkX3VzZXJuYW1lIjoic3ZjX3RwdW9fcGgifQ.W5PNYosl9l0FVrwS-jglfCYKQmHYLWKR-kV46Taeojf1wURekLGkbXlyQA0NYNLy76mWi9bzjonSTMY5qOfiWnS6U7v23YadH_s9A0tQ2axN927Q-_I1ldY7K6MdKWwFkHTOvco6qlXvC2IwQp9otZNgRI_TDNDXwF47QpE2jqBEF3hZR2fRB9HsvD_qxHZ9MiwhA0acq63pwjPApGsnVzDtCRexs3q3_JnHo_TQOxT1TLUl9Zz1II1bFuyt9aqy0gR2rl9DxSkGk7l6ldByUexEElY3JyIQ-epFW8RlkcxPnrcZZU6IqUdS9GJc4HvwXYMf_brPeclLRDGyRjTZNw\"" +
                "}");
    }

    public void finish(){
        RabbitMQ_http rabbitMQHttp = new RabbitMQ_http("CrossborderSettlementPay","C2CRestRPC.input");
        rabbitMQHttp.sendHttp("{" +
                "\"inner_transfer_id\":\"a252ad35-2205-4fdb-9a25-eb334b73d740\"," +
                "\"otp\":\"1111\"," +
                "\"jwt\":\"eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJMU1dxSEYzWUF0UDkzMmNXdm5ESXBzOHhqaHRQeWxiN0JOV2prVWhqcVhrIn0.eyJleHAiOjE3MjA3OTIxMzksImlhdCI6MTcyMDc5MTIzOSwianRpIjoiZjI4ZTc0YjktMDg4Ny00N2YyLWE3YjUtYmE1ZjEzYTg2MGQzIiwiaXNzIjoiaHR0cHM6Ly9hdXRoLnRlc3QtZnVpYi5jb20vYXV0aC9yZWFsbXMvcHVtYiIsImF1ZCI6WyJ0cmFuc2FjdGVyIiwiUEhUUk4iLCJPREIiLCJhY2NvdW50Il0sInN1YiI6ImZjZDViODMzLTUzMmMtNDg5Ny1hOGVjLWMzNTdlZGE4YzdlMiIsInR5cCI6IkJlYXJlciIsImF6cCI6InRyYW5zYWN0ZXIiLCJzZXNzaW9uX3N0YXRlIjoiNzg2ZWM4NjEtM2Y5My00ZmMxLTkzMzYtMWU1ZDg4ZTI4MzViIiwicmVhbG1fYWNjZXNzIjp7InJvbGVzIjpbIm9mZmxpbmVfYWNjZXNzIiwidW1hX2F1dGhvcml6YXRpb24iXX0sInJlc291cmNlX2FjY2VzcyI6eyJQSFRSTiI6eyJyb2xlcyI6WyJwaHVudHIiLCJwaGNvbSIsInBoX3NlIiwicGhfY2Jfb3AiLCJwaF9jdV9leCIsInBoX3BuX3RrbiIsInBoX3N3X3JlcSJdfSwidHJhbnNhY3RlciI6eyJyb2xlcyI6WyJwaF9wcF9hZG0iLCJwaF9kaW5mIiwicGhfcHBfb3AiXX0sIk9EQiI6eyJyb2xlcyI6WyJhY2NfbG10X3IiLCJwYXlfZG9jX2NydCIsInBheV9kb2NfZGVsIl19LCJhY2NvdW50Ijp7InJvbGVzIjpbIm1hbmFnZS1hY2NvdW50IiwibWFuYWdlLWFjY291bnQtbGlua3MiLCJ2aWV3LXByb2ZpbGUiXX19LCJzY29wZSI6IiIsInNpZCI6Ijc4NmVjODYxLTNmOTMtNGZjMS05MzM2LTFlNWQ4OGUyODM1YiIsIm1lcmNoYW50X2lkIjoiMTA1NDYxOTctMGQyZi00MDU5LWI5YTItZDAxY2I5N2ViYTYxIiwicHJlZmVycmVkX3VzZXJuYW1lIjoic3ZjX3RwdW9fcGgifQ.W5PNYosl9l0FVrwS-jglfCYKQmHYLWKR-kV46Taeojf1wURekLGkbXlyQA0NYNLy76mWi9bzjonSTMY5qOfiWnS6U7v23YadH_s9A0tQ2axN927Q-_I1ldY7K6MdKWwFkHTOvco6qlXvC2IwQp9otZNgRI_TDNDXwF47QpE2jqBEF3hZR2fRB9HsvD_qxHZ9MiwhA0acq63pwjPApGsnVzDtCRexs3q3_JnHo_TQOxT1TLUl9Zz1II1bFuyt9aqy0gR2rl9DxSkGk7l6ldByUexEElY3JyIQ-epFW8RlkcxPnrcZZU6IqUdS9GJc4HvwXYMf_brPeclLRDGyRjTZNw\"," +
                "\"client_ip\":\"31.131.120.41\"" +
                "}" +
                "    ");
        }

    public void getReceiverCardCountry(){
        RabbitMQ_http rabbitMQHttp = new RabbitMQ_http("GetReceiverCardCountry","C2CRestRPC.input");
        rabbitMQHttp.sendHttp("{" +
                "\"receiver_card_number\":\""+Cards_data.getData(Card.FUIB_VISA,Card_param.pan)+"\"" +
                "}");
    }

    public void getTransStatusRPC(){
        RabbitMQ_http rabbitMQHttp = new RabbitMQ_http("GetTransactionDetails","C2CRestRPC.input");
        rabbitMQHttp.sendHttp("{" +
                "\"transfer_id\":\"2db71dad-811c-473c-9eb0-6861b06ea767\"" +
                "}");
    }

    public void getComm(){
        crossborder.getLimits();
    }
}
