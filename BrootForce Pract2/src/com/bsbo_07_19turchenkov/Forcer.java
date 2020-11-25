package com.bsbo_07_19turchenkov;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Forcer {

    public static byte[] crackMe;
    public static String crackMeString;

    //The MessageDigest does the SHA-256 caclulation. Note that this may throw a NoSuchAlgorithmException when there
    // is no SHA-256 implementation in the local standard libraries (but that algorithm is mandatory, so this code
    // probably will never throw that Excpetion
    public static MessageDigest digest;

    static {
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public Forcer(String crackMe) throws NoSuchAlgorithmException {
        crackMeString = crackMe;
        Forcer.crackMe = digest.digest(crackMe.getBytes(StandardCharsets.UTF_8));
    }
}
