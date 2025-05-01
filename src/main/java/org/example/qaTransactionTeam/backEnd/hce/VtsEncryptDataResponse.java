package org.example.qaTransactionTeam.backEnd.hce;

import com.bettercloud.vault.VaultException;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.RSAEncrypter;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.jwk.RSAKey;

import java.security.GeneralSecurityException;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.time.Instant;

public class VtsEncryptDataResponse {

    public static String encryptOutputData(String encryptData) throws VaultException {


        String result = "";
        try {
            String jweKid = (HCEConfigsSertificats.findVault("visa","jweKid"));
            String jwsKid = (HCEConfigsSertificats.findVault("visa","jwsKid"));
            X509Certificate publicVisaKey = KeyTools.convertToX509Cert(HCEConfigsSertificats.findVault("visa","encryption"));
            PrivateKey privateVisaKey = KeyTools.getPrivateKeyFromString(HCEConfigsSertificats.findVault("inbound","private"));

            String dataForEncryption = encryptData;
            EncryptionMethod enc = EncryptionMethod.A256GCM;
            JWEAlgorithm alg = JWEAlgorithm.RSA_OAEP_256;
            JWEEncrypter jweEncrypter = new RSAEncrypter(RSAKey.parse(publicVisaKey).toRSAPublicKey());
            JWEHeader jweHeader = new JWEHeader
                    .Builder(alg, enc)
                    .type(JOSEObjectType.JOSE)
                    .keyID(jweKid)
                    .customParam("iat", Instant.now().toEpochMilli())
                    .build();
            Payload jwePayload = new Payload(dataForEncryption);
            JWEObject jweObject = new JWEObject(jweHeader, jwePayload);
            jweObject.encrypt(jweEncrypter);
            String jweString = jweObject.serialize();
            JWSSigner jwsSigner = new RSASSASigner(privateVisaKey);
            JWSHeader jwsHeader = new JWSHeader.Builder(JWSAlgorithm.PS256)
                    .keyID(jwsKid)
                    .contentType("JWE")
                    .build();
            Payload jwsPayload = new Payload(jweString);
            JWSObject jwsObject = new JWSObject(jwsHeader, jwsPayload);
            jwsObject.sign(jwsSigner);
            result =  jwsObject.serialize();
        } catch (JOSEException | GeneralSecurityException e) {
            e.printStackTrace();
        }
        return result;
    }
}
