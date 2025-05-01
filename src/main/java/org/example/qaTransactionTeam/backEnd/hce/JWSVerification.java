package org.example.qaTransactionTeam.backEnd.hce;

import org.jose4j.jwa.AlgorithmConstraints;
import org.jose4j.jwk.JsonWebKey;
import org.jose4j.jwk.JsonWebKeySet;
import org.jose4j.jwk.VerificationJwkSelector;
import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.lang.JoseException;

//https://bitbucket.org/b_c/jose4j/wiki/JWS%20Examples
public class JWSVerification {

    private String payload;
    private boolean signatureVerified;

    public String getPayload() {
        return payload;
    }

    public boolean isSignatureVerified() {
        return signatureVerified;
    }

    public JWSVerification(String jsonWebKeySetJson, String compactSerialization) throws JoseException {

        JsonWebSignature jws = new JsonWebSignature();
        jws.setAlgorithmConstraints(new AlgorithmConstraints(AlgorithmConstraints.ConstraintType.PERMIT,   AlgorithmIdentifiers.ECDSA_USING_P256_CURVE_AND_SHA256));

        jws.setCompactSerialization(compactSerialization);

        JsonWebKeySet jsonWebKeySet = new JsonWebKeySet(jsonWebKeySetJson);

        VerificationJwkSelector jwkSelector = new VerificationJwkSelector();
        JsonWebKey jwk = jwkSelector.select(jws, jsonWebKeySet.getJsonWebKeys());

        jws.setKey(jwk.getKey());
        signatureVerified = jws.verifySignature();

        payload = jws.getPayload();

    }
}
