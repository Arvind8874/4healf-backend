/*

package com.helf.security;

import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Properties;

@Component
public class MD5EncryptionWithSecretKey {

    // Encrypts a string using Base64 encoding and a secret key
    public static String encrypt(String originalString) {
        try {
            Properties props = new Properties();
            InputStream fis = MD5EncryptionWithSecretKey.class.getClassLoader().getResourceAsStream("application.properties");
            props.load(fis);
            String secretKey = props.getProperty("secretKey");
            byte[] key = secretKey.getBytes(StandardCharsets.UTF_8);
            SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");

            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);

            byte[] bytes = originalString.getBytes(StandardCharsets.UTF_8);
            byte[] encryptedBytes = cipher.doFinal(bytes);
            byte[] encodedBytes = Base64.getEncoder().encode(encryptedBytes);

            return new String(encodedBytes, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException("Error encrypting string", e);
        }
    }

    // Decrypts a string using Base64 decoding and a secret key
    public static String decrypt(String encryptedString) {
        try {
            Properties props = new Properties();
            InputStream fis = MD5EncryptionWithSecretKey.class.getClassLoader().getResourceAsStream("secret-key.properties");
            props.load(fis);
            String secretKey = props.getProperty("secretKey");
            byte[] key = secretKey.getBytes(StandardCharsets.UTF_8);
            SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");

            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);

            byte[] bytes = encryptedString.getBytes(StandardCharsets.UTF_8);
            byte[] decodedBytes = Base64.getDecoder().decode(bytes);
            byte[] decryptedBytes = cipher.doFinal(decodedBytes);

            return new String(decryptedBytes, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException("Error decrypting string", e);
        }
    }
}

*/
