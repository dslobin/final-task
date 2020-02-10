package by.epam.autoshow.util;

import org.apache.commons.codec.digest.DigestUtils;

public class Sha256PasswordEncoder {

    private Sha256PasswordEncoder() {
    }

    public static String encode(String password) {
        String sha256Hex = DigestUtils.sha256Hex(password);
        return sha256Hex;
    }
}