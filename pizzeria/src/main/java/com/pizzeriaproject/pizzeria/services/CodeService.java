package com.pizzeriaproject.pizzeria.services;

import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class CodeService {
    private final ConcurrentHashMap<String, Long> codes = new ConcurrentHashMap<>();
    private final Random random = new Random();
    private static final long EXPIRATION_TIME = 300000;

    // TODO Check if code is in Memory
    public String generateCode() {
        String code = String.format("%06d", random.nextInt(999999));
        codes.put(code, System.currentTimeMillis() + EXPIRATION_TIME);
        return code;
    }

    public boolean isCodeValid(String code) {
        Long expirationTime = codes.getOrDefault(code, null);
        if (expirationTime == null || System.currentTimeMillis() > expirationTime) {
            codes.remove(code);
            return false;
        }
        return true;
    }

    public void cleanupExpiredCodes() {
        long currentTime = System.currentTimeMillis();
        codes.entrySet().removeIf(entry -> entry.getValue() < currentTime);
    }
}
