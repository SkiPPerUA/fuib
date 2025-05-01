package test.backTests.pushes;

import org.example.qaTransactionTeam.backEnd.pushes.PushesControl;
import org.json.JSONException;
import org.testng.annotations.Test;

public class ControlPushes {
    PushesControl push;
    String env = "stage";
    String part = "001";
    String card = "012566295248";
    String firstDev = "121221";
    String secondDev = "9999999";


    {
        try {
            push = new PushesControl(env);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

   // @Test
    public void testAll() {

        testPost();
        testGet();
        testPut();
        testGet();
        testDelete();
        testGet();

    }

    @Test
    public void testPost(){
        System.out.println("Пост");
        push.postPush(part,card, "{\"device_id\":\""+firstDev+"\"}");
        System.out.println("-------------------------------------------");

    }

    @Test
    public void testPut(){
        System.out.println("Пут");
        push.putPush(part,card, "{\"device_id\":\""+secondDev+"\"}");
        System.out.println("-------------------------------------------");
    }

    @Test
    public void testGet(){
        System.out.println("Гет");
        push.getPush(part,card);
        System.out.println("-------------------------------------------");
    }

    @Test
    public void testDelete(){
        System.out.println("Делет");
        push.deletePush(part,card);
        System.out.println("-------------------------------------------");
    }
}
