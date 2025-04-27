package com.henrique.login.springbooot.util;

import java.security.SecureRandom;

public class GenerateMfaNumber {

    private static final SecureRandom random = new SecureRandom();

    public static int generateMfaCode() {
        int code = 10000 + random.nextInt(90000); // Gera um número entre 10000 e 99999
        return code;
    }
}
