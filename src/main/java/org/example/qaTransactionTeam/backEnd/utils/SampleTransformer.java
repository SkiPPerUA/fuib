package org.example.qaTransactionTeam.backEnd.utils;

import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.common.FileSource;
import com.github.tomakehurst.wiremock.extension.Parameters;
import com.github.tomakehurst.wiremock.extension.ResponseDefinitionTransformer;
import com.github.tomakehurst.wiremock.http.*;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class SampleTransformer extends ResponseDefinitionTransformer {

    public static String body;
    @Override
    public ResponseDefinition transform(
            Request request,
            ResponseDefinition responseDefinition,
            FileSource files,
            Parameters parameters) {
        byte[] headers = request.getBody();
        body = new String(headers,StandardCharsets.UTF_8);
        return new ResponseDefinitionBuilder()
                .like(responseDefinition)
                .build();
    }

    @Override
    public String getName() {
        return "requestparestoresponse";
    }
}
