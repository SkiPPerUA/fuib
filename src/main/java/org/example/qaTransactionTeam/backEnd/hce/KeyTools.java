package org.example.qaTransactionTeam.backEnd.hce;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.io.ByteArrayInputStream;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;


public class KeyTools {

    private static final String SECRET_KEY_ALGORITHM = "AES";
    /**
     *
     */
    private static final String PRIVATE_KEY_ALGORITHM = "RSA";
    /**
     *
     */
    private static final String ALGORITHM_MODE_PADDING = "RSA/ECB/OAEPWITHSHA256ANDMGF1PADDING";

    /**
     * Generate an AES key using KeyGenerator Initialize the
     * key size:
     * - 128 bits (16 bytes) mdes
     * - 256 bits (32 bytes) Apple Pay
     */
    public static String generateKeyId(Integer keySize) throws NoSuchAlgorithmException {

        KeyGenerator keyGen = KeyGenerator.getInstance(SECRET_KEY_ALGORITHM);
        keyGen.init(keySize);//128 for mdes
        //
        return DatatypeConverter.printHexBinary(keyGen.generateKey().getEncoded());
    }

    /**
     * Wrap random key key
     */
    public static String wrapKey(String key, String wrappingKey) throws GeneralSecurityException {
        //
        providerInit();
        //Create cert from string
        X509Certificate certificate = convertToX509Cert(wrappingKey);
        RSAPublicKey wrappingRSAPublicKey = (RSAPublicKey) certificate.getPublicKey();

        Cipher rsaWrapper = Cipher.getInstance(ALGORITHM_MODE_PADDING, new BouncyCastleProvider());
        rsaWrapper.init(Cipher.WRAP_MODE, wrappingRSAPublicKey);

        //Get key from string
        SecretKey originalKey = stringKeyToSecretKey(key, SECRET_KEY_ALGORITHM);
        //
        return DatatypeConverter.printHexBinary(rsaWrapper.wrap(originalKey));
    }

    /**
     * Unwrap random key key
     */
    public static String unWrapKey(String encryptedKey, String privateKey) throws GeneralSecurityException, IllegalArgumentException {
        //
        providerInit();
        //Get private key from string
        PrivateKey originalPrivateKey = getPrivateKeyFromString(privateKey);

        Cipher rsaUnWrapper = Cipher
                .getInstance(ALGORITHM_MODE_PADDING, new BouncyCastleProvider());
        rsaUnWrapper.init(Cipher.UNWRAP_MODE, originalPrivateKey);

        //Get byte from string
        byte[] originalKey = DatatypeConverter.parseHexBinary(encryptedKey);
        //
        return DatatypeConverter.printHexBinary(
                rsaUnWrapper.unwrap(originalKey, SECRET_KEY_ALGORITHM, Cipher.SECRET_KEY).getEncoded());
    }

    /**
     * Create certificate from String (only public key)
     */
    public static X509Certificate convertToX509Cert(String certificateString) throws CertificateException {

        CertificateFactory cf = CertificateFactory.getInstance("X509");

        certificateString = certificateString.replaceAll("-----(BEGIN|END) CERTIFICATE-----", "")
                .replaceAll("\n", "");
        byte[] certificateData = DatatypeConverter.parseBase64Binary(certificateString);
        //
        return (X509Certificate) cf.generateCertificate(new ByteArrayInputStream(certificateData));

    }

    /**
     * Create private key from string
     */
    public static PrivateKey getPrivateKeyFromString(String key) throws GeneralSecurityException {
        Security.addProvider(
                new org.bouncycastle.jce.provider.BouncyCastleProvider()
        );
        //replace all symbol
        key = key.replaceAll("-----(BEGIN|END) PRIVATE KEY-----\n", "").replaceAll("\n", "");
        byte[] keyData = DatatypeConverter.parseBase64Binary(key);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyData);
        KeyFactory kf = KeyFactory.getInstance(PRIVATE_KEY_ALGORITHM);
        //
        return kf.generatePrivate(keySpec);
    }

    /**
     * Get SecretKey from string
     *
     * @param key String key
     * @return Secret key
     */
    public static SecretKey stringKeyToSecretKey(String key, String algorithm) {
        byte[] originalKeyByte = DatatypeConverter.parseHexBinary(key);
        return new SecretKeySpec(originalKeyByte, algorithm);
    }

    /**
     * Initialize provider
     */
    private static void providerInit() {
        Security.getProvider("BC");//???
        Security.addProvider(new BouncyCastleProvider());
    }

}
