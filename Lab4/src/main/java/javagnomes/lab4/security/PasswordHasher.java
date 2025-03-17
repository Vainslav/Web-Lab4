package javagnomes.lab4.security;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.tuple.Pair;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class PasswordHasher {
    public static Pair<String, String> hash(String password, String salt) {
        try{
            if (salt == null){
                salt = RandomStringUtils.randomAlphabetic(10);
            }
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            return Pair.of(Base64.getEncoder().encodeToString(md.digest((password + salt).getBytes(StandardCharsets.UTF_8))),salt);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Hashing failed", e);
        }
    }

    public static boolean verify(String password, String hash, String salt) {
        return !hash(password, salt).getLeft().equals(hash);
    }
}
