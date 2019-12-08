/*
In the name of Allah, the Gracious, the Merciful
 */
package Cryptography;

/**
 *
 * @author Ibrahim
 */
public class Shifting {
    private static String alpha = 
            "abcdefghijklmnopqrstuvwxyz";
    
    /**
     * function to encrypt the message in Caesar
     * @param message message to encrypt
     * @param key key integer to shift the words with
     * @return the encrypted message
     */
    public static String encrypt(String message, int key){
        message = message.toLowerCase();
        String encrypted = "";
        int newIndex;
        for(int i = 0; i < message.length(); i++){
            if(message.charAt(i)< 'a' || message.charAt(i) >  'z'){
                encrypted += message.charAt(i);
                continue;
            }
            newIndex = (alpha.indexOf(message.charAt(i)) + key ) % 26;
            encrypted += alpha.charAt(newIndex);
        }
        return encrypted;
    }
    
    /**
     * function to decrypt the encrypted message with Caesar
     * @param message message to decrypt
     * @param key  key integer to shift the words with
     * @return the real message
     */
    public static String decrypt(String message, int key){
        message = message.toLowerCase();
        String decrypted = "";
        int newIndex;
        for(int i = 0; i < message.length(); i++){
            if(message.charAt(i)< 'a' || message.charAt(i) >  'z'){
                decrypted += message.charAt(i);
                continue;
            }
            newIndex = (alpha.indexOf(message.charAt(i)) - key + 26) % 26;
            decrypted += alpha.charAt(newIndex);
        }
        return decrypted;
        
    }
}
