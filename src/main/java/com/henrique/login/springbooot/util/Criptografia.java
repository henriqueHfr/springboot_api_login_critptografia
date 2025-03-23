package com.henrique.login.springbooot.util;

public class Criptografia {
    public static String decode(String str) {
        return new String(java.util.Base64.getDecoder().decode(str));
    }
    public static String encode(String str) {
        return java.util.Base64.getEncoder().encodeToString(str.getBytes());
    }
}
