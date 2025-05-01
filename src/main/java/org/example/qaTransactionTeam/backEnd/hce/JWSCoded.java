package org.example.qaTransactionTeam.backEnd.hce;

import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.lang.JoseException;

import java.security.PrivateKey;

//https://bitbucket.org/b_c/jose4j/wiki/JWS%20Examples
public class JWSCoded {

    private String jwsCompactSerialization;

    public String getJwsCompactSerialization() {
        return jwsCompactSerialization;
    }

    public JWSCoded(String payload) throws JoseException {

        JsonWebSignature jws = new JsonWebSignature();
        jws.setPayload(payload);
        jws.setAlgorithmHeaderValue(AlgorithmIdentifiers.ECDSA_USING_P256_CURVE_AND_SHA256);
        PrivateKey privateKey = ExampleEcKeysFromJws.PRIVATE_256;
        jws.setKey(privateKey);

        jwsCompactSerialization = jws.getCompactSerialization();
    }
}
