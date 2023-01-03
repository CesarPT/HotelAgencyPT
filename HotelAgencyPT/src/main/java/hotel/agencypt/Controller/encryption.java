package hotel.agencypt.Controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class encryption {
    /**
     * Faz a encriptação da palavra-passe
     *
     * @param password Recebe a palavra-passe
     * @param encryptedpassword Recebe uma variavel vazia
     * @return Devolve a variavel 'encryptedpassword'
     */
    public static String encrypt(String password, String encryptedpassword) {
        try {
            /* MessageDigest instance for MD5. */
            MessageDigest m = MessageDigest.getInstance("MD5");

            /* Add plain-text password bytes to digest using MD5 update() method. */
            m.update(password.getBytes());

            /* Convert the hash value into bytes */
            byte[] bytes = m.digest();

            /* The bytes array has bytes in decimal form. Converting it into hexadecimal format. */
            StringBuilder s = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                s.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }

            /* Complete hashed password in hexadecimal format */
            encryptedpassword = s.toString();
        } catch (
                NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return encryptedpassword;
    }

}
