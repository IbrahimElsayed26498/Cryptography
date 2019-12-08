/*
In the name of Allah, the Gracious, the Merciful
 */
package Cryptography;

/**
 *
 * @author Ibrahim
 */
public class PlayFair {
    
    // the array witch will store the key.
    static char[][] keyArray = new char[5][5];
    
    /**
     * function to generate a key
     * @param key 
     */
    public static void keyGenerator(String key) {
        
        key = key.toLowerCase(); // convert all input to lowercase
        key = key.replace("j", ""); // remove 'j' character
        key = key.replaceAll(" ",""); // remove spaces
        key += "abcdefghiklmnopqrstuvwxyz"; // store the alphapets after the key
        int charCounter = 0; // counter to go throw the key paramenter
        
        char chs[] = new char[25]; // it'll store the key (temperorary)
                                   // to make it easy to deal with.
        
        // store the key in the array without dublication
        for (int i = 0; i < chs.length; i++) {
            if (containsChar(chs, key.charAt(charCounter))) {
                // if the char is in the key array, skip it.
                i--; 
            } else {
                // if not, add it to the array.
                chs[i] = key.charAt(charCounter);
            }
            
            charCounter++;
        }
        
        keyArray = convertTo2D(chs); // convert the 1 D into 2 D 
                                     // and store it in keyArray.
    }
    
    
    /**
     * this function checks if the char is on the array or not
     * @param arr the array you want to search in
     * @param ch the character to search for
     * @return true if the ch in arr, else returns false
     */
    private static boolean containsChar(char arr[], char ch) {
        // traverse in the array.
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == ch) { // if the char on the array
                return true;
            }
        }

        return false;
    }
    /**
     * function to convert the 1 D array
     * into 2 D array.
     * @param arr array in 1 D of length 25.
     * @return array input put in 2 D of [5][5].
     */
    private static char[][] convertTo2D(char[] arr) {
        char chs[][] = new char[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                chs[i][j] = arr[i * 5 + j];
            }
        }
        return chs;
    }
    
    /**
     * this function converts the 2D array to a String
     * @param arrss the 2 D array
     * @return 2 D array in String format
     */
    public static String convertArrayToString(char[][] arrss) {
        String array = "";
        for (char arrs[] : arrss) {
            for (char arr : arrs) {
                array += (arr + " ");
            }
            array += ("\b" + "\n");
        }
        return array;
    }

    /**
     * function to encrypt the message with play fair algorithm
     * @param message the message you want to encrypt
     * @param key the key which the message will be encrypted with
     * @return String encrypted message
     */
    public static String encrypt(String message, String key) {
        
        keyGenerator(key); // generate the key
        
        message = message.toLowerCase(); // convet all message to lower case
        message = message.replaceAll("j", "i"); // replace every 'j' with 'i'
        message = message.replaceAll(" ",""); // remove spaces
        
        // prepare the message for encryption
        
        String newMessage = message.charAt(0) + ""; //newMessage will store the
                                                    //message after preparation
        String encrypted = ""; // will store the encrypted message
        
        for (int i = 1; i < message.length(); i++) {
            if (newMessage.length() % 2 != 0) {        //length is odd
                // he lx lo
                // hz nl lo
                if (message.charAt(i) == 
                        newMessage.charAt(newMessage.length() - 1))
                    newMessage += ('x' + "");
                
                    // if the pair char are idntical
                    // spreate them with 'x' char.
                newMessage += (message.charAt(i) + "");
            }
            else{
                newMessage += (message.charAt(i) + "");
            }
        }
        // if the message is odd, add 'x' at the end
        if(newMessage.length() %2 != 0){
            newMessage += ('x' + "");
        }
        
        // they will store row and column for every char
        Index a1 =  new Index();
        Index a2 = new Index();
        
        // The actual Enctryption
        for(int i = 0; i < newMessage.length(); i += 2){
            
            // find the index of the chars in the key array
            a1 = findIndex(newMessage.charAt(i), keyArray);
            a2 = findIndex(newMessage.charAt(i + 1), keyArray);
            
            // if the two chars are in the same row
            if(a1.i == a2.i){
                
               // the char will take the next right char (circuly)
               encrypted += ("" + keyArray[a1.i][(a1.j + 1) % 5]);
               encrypted += ("" + keyArray[a2.i ] [(a2.j + 1) % 5]);
               
            // if the are at the same column
            }else if (a1.j == a2.j){
                // the char will take the next down (circuly)
               encrypted += ("" + keyArray[(a1.i + 1) % 5 ][a1.j ]);
               encrypted += ("" + keyArray[(a2.i + 1) % 5 ][a2.j]);
            }
            
            else{
                
                encrypted += ("" + keyArray[a1.i][a2.j]);
                encrypted += ("" + keyArray[a2.i][a1.j]);
            }
            
        }
        
        
        return encrypted;
    }
    
    /**
     * function to decrypt the encrypted message
     * @param message encrypted message
     * @param key key encryption
     * @return the real message
     */
    public static String decryption(String message, String key){
        keyGenerator(key); // genreate the key
        String decrypted = ""; // it'll store the encrypted message
        // to store the row and column for each char
        Index a1 =  new Index(); 
        Index a2 = new Index();
        
        for(int i = 0; i < message.length(); i += 2){
            // find the index of the chars in the key array
            a1 = findIndex(message.charAt(i), keyArray);
            a2 = findIndex(message.charAt(i + 1), keyArray);
            
            // if they are at the same row
            if(a1.i == a2.i){
               // move the chars to left (circuly)
               decrypted += ("" + keyArray[a1.i][((a1.j - 1) + 5) % 5]);
               decrypted += ("" + keyArray[a2.i][((a2.j - 1) + 5) % 5]);
            
            // if they are at the same column
            }else if (a1.j == a2.j){
                // move the chars to up (circuly)
               decrypted += ("" + keyArray[((a1.i - 1) + 5 )% 5 ][a1.j ]);
               decrypted += ("" + keyArray[((a2.i - 1) + 5 )% 5][a2.j]);
            }
            else{
                decrypted += ("" + keyArray[a1.i][a2.j]);
                decrypted += ("" + keyArray[a2.i][a1.j]);
            }
            
        }
        
        return decrypted;
    }
    
    /**
     * function to find index of char in 2 D array
     * @param ch the char you want to find it's index
     * @param arr the array in which you will search 
     * @return object of Index class
     */
    private static Index findIndex(char ch, char[][] arr){
        Index a = new Index();
        for(int i = 0; i < arr.length; i++){
            for(int j = 0; j < arr[0].length; j++){
                if(ch == arr[i][j]){
                    a.i = i;
                    a.j = j;
                    break;
                }
            }
        }
        return a;
    }
    
}
/**
 * class to store index of an element in 2 D array
 * @author Ibrahim
 */
class Index{
        public int i = 0;
        public int j = 0;
}