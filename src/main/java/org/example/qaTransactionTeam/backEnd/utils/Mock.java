package org.example.qaTransactionTeam.backEnd.utils;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;

public class Mock {

    private static WireMockServer mock;

    public static WireMockServer getMock() {
        return mock;
    }

    public static void Mock (){

        mock = new WireMockServer();
        mock.start();

    }

    public static void Mock (int port){

        mock = new WireMockServer(port);
        mock.start();

    }

    public static void MockWithConfig (int port){

        mock = new WireMockServer(
                new WireMockConfiguration().port(port).extensions(SampleTransformer.class));
        mock.start();

    }

    public static void stopMock(){
        mock.stop();
    }

}
