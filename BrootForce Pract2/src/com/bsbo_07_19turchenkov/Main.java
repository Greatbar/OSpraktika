package com.bsbo_07_19turchenkov;

import org.apache.commons.codec.digest.DigestUtils;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Main {
    public static void main(String[] args) {

        String[] hashes = {"74e1bb62f8dabb8125a58852b63bdf6eaef667cb56ac7f7cdba6d7305c50a22f",
                "3a7bd3e2360a3d29eea436fcfb7e44c735d117c42d1c1835420b6b9942dd4f1b",
                "1115dd800feaacefdf481f1f9070374a2a81e27880f187396db67958b207cbad"};
        ExecutorService executorService = Executors.newFixedThreadPool(8);
        for (String hash : hashes) {
            executorService.submit(new Cracker(hash));

            System.out.println("Выполнение потоков началось");
        }
        System.out.println("Выполнение потоков продолжается");
        executorService.shutdown();
    }

    static class Cracker implements Runnable {
        public String crackInString;
        public byte[] crackInByte;

        public Cracker(String crackInByte) {
            this.crackInString = crackInByte;
            this.crackInByte = crackInByte.getBytes();
        }

        @Override
        public void run() {
            char[] p = new char[5];
            String match = "";
            p[0] = 'a';
            p[1] = 'a';
            p[2] = 'a';
            p[3] = 'a';
            p[4] = 'a';
            do {
                if (p[4] <= 'z') {

                    String notPassword = new String(p);
                    String notPasswordSTR = DigestUtils.sha256Hex(notPassword);
                    byte[] notPasswordByte = notPasswordSTR.getBytes();
                    if (Arrays.equals(crackInByte, notPasswordByte)) {
                        match = notPassword;
                    }
                    p[4]++;
                    continue;
                } else {
                    p[3]++;
                    p[4] = 'a';
                }
                if (p[3] <= 'z') {
                    continue;
                } else {
                    p[2]++;
                    p[3] = 'a';
                }
                if (p[2] <= 'z') {
                    continue;
                } else {
                    p[1]++;
                    p[2] = 'a';
                }
                if (p[1] <= 'z') {
                    continue;
                } else {
                    p[0]++;
                    p[1] = 'a';
                }
            } while (p[0] <= 'z');
            System.out.printf("Данный хеш %s имеет совпадение с: %s%n", crackInString, match);
        }
    }
}