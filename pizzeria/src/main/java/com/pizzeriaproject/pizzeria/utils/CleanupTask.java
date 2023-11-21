package com.pizzeriaproject.pizzeria.utils;

import com.pizzeriaproject.pizzeria.services.CodeService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CleanupTask {
    private final CodeService codeService;

    public CleanupTask(CodeService codeService) {
        this.codeService = codeService;
    }

    @Scheduled(fixedRate = 1000000)
    public void cleanupExpiredCodes() {
        codeService.cleanupExpiredCodes();
    }
}
