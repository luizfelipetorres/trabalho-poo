package util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hash {

    public static String generateHash(String input) {
        MessageDigest algorithm;
        StringBuilder output = null;

        try {
            algorithm = MessageDigest.getInstance("SHA-256");
            byte hash[] = algorithm.digest(input.getBytes("UTF-8"));

            output = new StringBuilder();
            for (byte b : hash) {
                output.append(String.format("%02X", 0xFF & b));
            }
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            System.err.println(e);
        }

        return output.toString();
    }

}
