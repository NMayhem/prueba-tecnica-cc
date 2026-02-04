package com.aidaml.cc.demo.security;

import jakarta.annotation.PostConstruct;

import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

@Service
public class AESEncryptionService {

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

    // AES-256, ECB mode. Very weak. TODO other mode. 
    public String encrypt(String plainText) throws NoSuchPaddingException, NoSuchAlgorithmException,
    InvalidAlgorithmParameterException, InvalidKeyException,
    BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance(ECB_ALGO);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] cipherText = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
        
        return Base64.getEncoder().encodeToString(cipherText);
    }

}
