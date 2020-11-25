package com.bsbo_07_19turchenkov;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class Test {
    private static final int LENGTH = 5;

    public static class Example1 {
        public static char[] allSymbols = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r',
                's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K',
                'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3',
                '4', '5', '6', '7', '8', '9'};
        public static char[] allSymbols1 = {'a', 'b', 'c', 'd'};
        public static char[] p = new char[LENGTH];
        public static char[] chars = new char[LENGTH];
        public static int length = allSymbols1.length - 1;
    }

    public static void Tesdt1() {
        Example1.p[0] = 0;
        Example1.p[1] = 0;
        Example1.p[2] = 0;
        Example1.p[3] = 0;
        do {
            if (Example1.p[3] <= Example1.length) {
                cout1();
                Example1.p[3]++;
                continue;
            } else {
                Example1.p[2]++;
                Example1.p[3] = 0;
            }

            if (Example1.p[2] <= Example1.length) {
                continue;
            } else {
                Example1.p[1]++;
                Example1.p[2] = 0;
            }
            if (Example1.p[1] <= Example1.length) {
                continue;
            } else {
                Example1.p[0]++;
                Example1.p[1] = 0;
            }

        } while (Example1.p[0] <= Example1.length);
        System.out.println("123");
    }

    public static void cout1() {
        System.out.println(Example1.allSymbols1[Example1.p[0]] + " " + Example1.allSymbols1[Example1.p[1]] + " " +
                Example1.allSymbols1[Example1.p[2]] + " " +  Example1.allSymbols1[Example1.p[3]]);
    }
    public static void ToSha() throws NoSuchAlgorithmException {
        String password = "123456";
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hashInBytes = md.digest(password.getBytes(StandardCharsets.UTF_8));
        //bytes to hex
        System.out.println(Arrays.toString(hashInBytes));
        StringBuilder sb = new StringBuilder();
        for (byte b : hashInBytes) {
            sb.append(String.format("%02x", b));
        }
        System.out.println(sb.toString());

    }
}