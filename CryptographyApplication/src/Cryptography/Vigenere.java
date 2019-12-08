package Cryptography;

public class Vigenere {

    static String alpha = "abcdefghigklmnopqrstuvwxyz";

    public static String encrypt(String plain, String key) {
        String cipher = "";
        for (int i = 0; i < plain.length(); i++) {
            char x = plain.charAt(i);
            int index = alpha.indexOf(x);
            cipher += alpha.charAt((index + 
                    (alpha.indexOf(key.charAt(i % key.length())))) % 26);
        }
        return cipher;

    }

    public static String decrypt(String cipher, String key) {
        String plain = "";
        for (int i = 0; i < cipher.length(); i++) {
            char x = cipher.charAt(i);
            int index = alpha.indexOf(x);
            plain += alpha.charAt(((index - alpha.indexOf
        (key.charAt(i % key.length()))) + 26) % 26);
        }
        return plain;

    }

}
