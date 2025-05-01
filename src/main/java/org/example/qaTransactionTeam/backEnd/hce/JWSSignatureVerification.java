package org.example.qaTransactionTeam.backEnd.hce;

import org.jose4j.jwa.AlgorithmConstraints;
import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.lang.JoseException;

import java.security.PublicKey;

//https://bitbucket.org/b_c/jose4j/wiki/JWS%20Examples
public class JWSSignatureVerification {

    private boolean signatureVerified;
    private String payload;

    public boolean isSignatureVerified() {
        return signatureVerified;
    }

    public String getPayload() {
        return payload;
    }

    public JWSSignatureVerification(String compactSerialization) throws JoseException {

        JsonWebSignature jws = new JsonWebSignature();

        // Set the algorithm constraints based on what is agreed upon or expected from the sender
        jws.setAlgorithmConstraints(new AlgorithmConstraints(AlgorithmConstraints.ConstraintType.PERMIT, AlgorithmIdentifiers.ECDSA_USING_P256_CURVE_AND_SHA256));

        // Set the compact serialization on the JWS
        jws.setCompactSerialization(compactSerialization);

        // Set the verification key
        // Note that your application will need to determine where/how to get the key
        // Here we use an example from the JWS spec
        PublicKey publicKey = ExampleEcKeysFromJws.PUBLIC_256;
        jws.setKey(publicKey);

        // Check the signature
        signatureVerified = jws.verifySignature();

        // Get the payload, or signed content, from the JWS
        payload = jws.getPayload();

    }
}
