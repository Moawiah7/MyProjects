/**

 * This class represents the Caesar cipher algorithm, which is a type of monoalphabetic substitution

 * cipher. It shifts each letter in the plaintext by a fixed number of positions down the alphabet.

 * The number of positions shifted is determined by a key, which is an integer value. If the key is

 * positive, the letters are shifted to the right (i.e., the cipher is encrypting), and if the key is

 * negative, the letters are shifted to the left (i.e., the cipher is decrypting).

 * This class extends the MonoAlphaSubstitution class, which provides the basic functionality for

 * monoalphabetic substitution ciphers.
 */

import java.util.*;
import java.lang.*;

public class Caesar extends MonoAlphaSubstitution {
/** The number of positions each letter is shifted by. */
    private int shift;
    private String key;
/**
 * Creates a new Caesar cipher with a shift of 0.
 */

    public Caesar() {
        this(0);
    }

/**
 * Creates a new Caesar cipher with the specified shift.
 * @param shift the number of positions each letter should be shifted by
 */

    public Caesar(int shift) {
        this.shift = shift % 26;
        this.key = generateKey();
    }

/**
 * Generates the substitution key for this Caesar cipher.
 * @return the substitution key
 */

    @Override
    public char encrypt(char c){
        if(Character.isUpperCase(c)){
            return(key.charAt(c-'A'));
        }else if(Character.isLowerCase(c)){
            return(key.charAt(c-'a'+26));
        }
        return(c);
    }

private String generateKey() {
    StringBuilder keyBuilder = new StringBuilder();
    while(shift<0){
        shift += 26;
    }
    for (int i = 0; i < 26; i++) {
        int shifted = Math.abs((i + shift) % 26);
        char shiftedChar = (char) ('A' + shifted);
        keyBuilder.append(shiftedChar);
    }
    for (int i = 0; i < 26; i++) {
        int shifted = Math.abs((i + shift) % 26);
        char shiftedChar = (char) ('a' + shifted);
        keyBuilder.append(shiftedChar);
    }
    return keyBuilder.toString();
}

@Override
public char decrypt(char c){
    int s = -shift;
    if (s < 0) {
        s += 26;
    }
    if(Character.isUpperCase(c)){
        return(key.charAt((c-'A'+s) % 26));
    }else if(Character.isLowerCase(c)){
        return(key.charAt((c-'a'+26+s) % 26 + 26));
    }
    return(c);
}

/**
 * The main method for the Caesar class. Takes in command-line arguments and performs encryption or decryption as specified.
 * @param args an array of command-line arguments: <code>{"encrypt/decrypt", shift, "text"}</code>
 */

    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("Too few parameters!");
            System.out.println("Usage: java Caesar encrypt n \"cipher text\"");
            return;
        } else if(args.length > 3){
            System.out.println("Too many parameters!");
            System.out.println("Usage: java Caesar encrypt n \"cipher text\"");
            return;
        }
        String text = args[2];
        int x = Integer.parseInt(args[1]);
        if(args[0].equals("encrypt")) {
                Caesar caesar = new Caesar(x);
                System.out.println(caesar.encrypt(text));
                }
        else if (args[0].equals("decrypt")){
                Caesar caesar = new Caesar(-x);
                System.out.println(caesar.encrypt(text));
                }
        else{
                System.out.println("Invalid command.");
        }
    }
}
