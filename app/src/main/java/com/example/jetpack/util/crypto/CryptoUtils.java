package com.example.jetpack.util.crypto;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * Created by FerJuarez on 29/12/2017.
 */

public class CryptoUtils {

    public static String getSecret(int size) {
        byte[] secret = getSecretBytes(size);
        return Base64.encodeBytes(secret);
    }

    public static byte[] getSecretBytes(int size) {
        byte[] secret = new byte[size];
        getSecureRandom().nextBytes(secret);
        return secret;
    }

    public static SecureRandom getSecureRandom() {
        try {
            return SecureRandom.getInstance("SHA1PRNG");
        } catch (NoSuchAlgorithmException e) {
            throw new AssertionError(e);
        }
    }

    public static int generateRegistrationId(boolean extendedRange) {
        try {
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            if (extendedRange) return secureRandom.nextInt(Integer.MAX_VALUE - 1) + 1;
            else               return secureRandom.nextInt(16380) + 1;
        } catch (NoSuchAlgorithmException e) {
            throw new AssertionError(e);
        }
    }
}
