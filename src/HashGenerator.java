import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class HashGenerator {

    static String generateHashMD(String input, String algorithm) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance(algorithm);
        byte[] hashBytes = digest.digest(input.getBytes());
        return bytesToHex(hashBytes);
    }

    static String generateHashSecureRandom(String algorithm) throws NoSuchAlgorithmException {
        SecureRandom secureRandom = SecureRandom.getInstance(algorithm);
        byte[] randomBytes = new byte[16];
        secureRandom.nextBytes(randomBytes);
        return bytesToHex(randomBytes);
    }

    static String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }


}
