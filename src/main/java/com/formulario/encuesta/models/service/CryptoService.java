package com.formulario.encuesta.models.service;

import javax.crypto.SecretKey;

public interface CryptoService {
    
    String encrypt(String text , SecretKey key);
    String decrypt(String encryptText , SecretKey key);
    SecretKey generateSecretKey();
}
