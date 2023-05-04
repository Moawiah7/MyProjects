/**
 * This class represents a Vigenere cipher, which is a type of substitution cipher.
 * It inherits from the Substitution class.
*/

import java.util.Arrays;

public class Vigenere extends Substitution {

// The array of shifts used in the encryption and decryption

    private final int[] shifts;

// The current position in the array of shifts

    private int position;

/**
 * Constructs a Vigenere object with an empty key.
 */

    public Vigenere() {
        super();
        shifts = new int[0];
        position = 0;
    }

/**
 * Constructs a Vigenere object with the specified key.
 *
 * @param key the key to use for encryption and decryption
 */

    public Vigenere(String key) {
        super();
        int keyLength = key.length();
        shifts = new int[keyLength];
        for (int i = 0; i < keyLength; i++) {
            shifts[i] = key.charAt(i) - 'A';
        }
        position = 0;
    }

/**
 * Encrypts a character using the Vigenere cipher.
 *
 * @param c the character to encrypt
 * @return the encrypted character
 */

    @Override
    public char encrypt(char c) {
        if (!Character.isLetter(c)) {
            return c;
        }
        int shift = shifts[position];
        position = (position + 1) % shifts.length;
        return (char) (((c - 'A' + shift) % 26) + 'A');
    }

/**
 * Decrypts a character using the Vigenere cipher.
 *
 * @param c the character to decrypt
 * @return the decrypted character
 */

    @Override
    public char decrypt(char c) {
        if (!Character.isLetter(c)) {
            return c;
        }
        int shift = shifts[position];
        position = (position + 1) % shifts.length;
        return (char) (((c - 'A' - shift + 26) % 26) + 'A');
    }

/**
 * The main method of the Vigenere class. It takes command line arguments
 * and either encrypts or decrypts the specified text using the specified key.
 *
 * @param args the command line arguments
 */

    public static void main(String[] args) {
        if (args.length < 2) {
            System.err.println("Too few parameters!");
            System.err.println("Usage: java Vigenere encrypt key \"cipher text\"");
            System.err.println("       java Vigenere decrypt key \"plain text\"");
            return;
        }
        String command = args[0];
        String key = args[1];
        String text = Arrays.stream(args).skip(2).reduce((s1, s2) -> s1 + " " + s2).orElse("");
        Vigenere cipher = new Vigenere(key);
        String result;
        switch (command) {
            case "encrypt":
                result = cipher.encrypt(text);
                break;
            case "decrypt":
                result = cipher.decrypt(text);
                break;
            default:
                System.err.println("The first parameter must be \"encrypt\" or \"decrypt\"!");
                System.err.println("Usage: java Vigenere encrypt key \"cipher text\"");
                System.err.println("       java Vigenere decrypt key \"plain text\"");
                return;
        }
        System.out.println(result);
    }

}
