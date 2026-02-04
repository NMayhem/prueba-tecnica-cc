package com.aidaml.cc.demo.security;

import jakarta.annotation.PostConstruct;

import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

import org.springframework.stereotype.Component;

@Component
public class AESEncryptionComponent {

    private static final String CBC_ALGO = "AES/CBC/PKCS5Padding";
    private static final String ECB_ALGO = "AES/ECB/PKCS5Padding";
    private static final String KEYGEN_ALGO = "AES";

    private SecretKey key;

    // Generates AES-256 key after loading Spring Context, then assigns it to our "key" variable.
    @PostConstruct
    private void generateKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(KEYGEN_ALGO);
        keyGenerator.init(256);
        SecretKey key = keyGenerator.generateKey();
        
        this.key = key;
    }

    // CBC Mode. A bit stronger than ECB (Encrypting the same string two times results in two different outputs).
    public String cbcEncrypt(String plainText) throws NoSuchPaddingException, NoSuchAlgorithmException,
    InvalidAlgorithmParameterException, InvalidKeyException,
    BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance(CBC_ALGO);
        byte[] iv = new byte[cipher.getBlockSize()];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(iv);

        cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(iv));
        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
        byte[] encryptedBytesWithIV = new byte[iv.length + encryptedBytes.length];

        System.arraycopy(iv, 0, encryptedBytesWithIV, 0, iv.length);
        System.arraycopy(encryptedBytes, 0, encryptedBytesWithIV, iv.length, encryptedBytes.length);

        return Base64.getEncoder().encodeToString(encryptedBytesWithIV);
    }

    public String cbcDecrypt(String encryptedText) throws NoSuchPaddingException, NoSuchAlgorithmException,
    InvalidAlgorithmParameterException, InvalidKeyException,
    BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance(CBC_ALGO);
        byte[] decodedBytes = Base64.getDecoder().decode(encryptedText);
        byte[] iv = new byte[cipher.getBlockSize()];
        byte[] encryptedBytes = new byte[decodedBytes.length - iv.length];

        System.arraycopy(decodedBytes, 0, iv, 0, iv.length);
        System.arraycopy(decodedBytes, iv.length, encryptedBytes, 0, encryptedBytes.length);
        cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(iv));
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
        
        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }

    // ECB mode. Very weak.
    public String ecbEncrypt(String plainText) throws NoSuchPaddingException, NoSuchAlgorithmException,
    InvalidAlgorithmParameterException, InvalidKeyException,
    BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance(ECB_ALGO);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] cipherText = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
        
        return Base64.getEncoder().encodeToString(cipherText);
    }

}
