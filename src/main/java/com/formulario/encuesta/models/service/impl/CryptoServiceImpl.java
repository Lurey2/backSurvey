package com.formulario.encuesta.models.service.impl;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.springframework.stereotype.Service;

import com.formulario.encuesta.models.service.CryptoService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CryptoServiceImpl implements CryptoService {
    
    @Override
    public String encrypt(String text, SecretKey key) {
        try {

            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encryptedBytes = cipher.doFinal(text.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(encryptedBytes);
            
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public String decrypt(String encryptText, SecretKey key) {
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptText));
            return new String(decryptedBytes, StandardCharsets.UTF_8);
        } catch (Exception e) {
            
            return null;
        }
        
    }

    @Override
    public SecretKey generateSecretKey() {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(128);
            return keyGenerator.generateKey();
        } catch (Exception e) {
            return null;// TODO: handle exception
        }
    }
    
}
