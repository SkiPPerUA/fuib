package test.backTests.counters;

import org.example.qaTransactionTeam.BaseTest;
import org.example.qaTransactionTeam.backEnd.counters.Counters;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class CountersTest extends BaseTest {

    Counters counters = new Counters();

    public void positive_test(){
        counters.setExpectedResponseCode(200);
        counters.getCounter("12345","nbu_p2p");
        Assert.assertEquals(counters.getResponse(), "{\"period\":\"202410\",\"sum\":12300}");
    }

    public void negative_test(){
        //not found
        counters.getCounter("1234555","nbu_p2p");
        Assert.assertEquals(counters.getResponse(), "");


        counters.setExpectedResponseCode(400);
        //without client
        counters.getCounter("","nbu_p2p");

        //without kind
        counters.getCounter("12345","");

        //kind not correct
        counters.getCounter("12345","nnn");

        //client with letters
        counters.getCounter("12345dd","nbu_p2p");
    }

}
