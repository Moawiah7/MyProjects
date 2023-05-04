/**
 * MonoAlphaSubstitution represents a monoalphabetic substitution cipher, which is a simple
   substitution cipher where each letter in the plaintext is replaced with another letter
   consistently throughout the message.
 */


import java.util.HashMap;
import java.util.Map;

public class MonoAlphaSubstitution extends Substitution {

    private Map<Character, Character> encodingTable;

/**
 * Constructs a MonoAlphaSubstitution object with default encoding table, which maps
 * each letter to itself.
 */

    public MonoAlphaSubstitution() {
        encodingTable = new HashMap<>();
        for (char c = 'a'; c <= 'z'; c++) {
            encodingTable.put(c, c);
        }
    }

/**
 * Constructs a MonoAlphaSubstitution object with the given encoding key.
 *
 * @param key the encoding key, which is a string of letter pairs indicating the
 *            substitution relationship.
 */

    public MonoAlphaSubstitution(String key) {
        encodingTable = new HashMap<>();
        for (int i = 0; i < key.length(); i += 2) {
            char c1 = key.charAt(i);
            char c2 = key.charAt(i + 1);
            encodingTable.put(c1, c2);
            //encodingTable.put(c2, c1);
        }
    }

/**
 * Encrypts the given character using the encoding table.
 *
 * @param c the character to be encrypted.
 * @return the encrypted character.
 */

   @Override
    public char encrypt(char c) {
        return encodingTable.getOrDefault(c, c);
    }

/**
 * Decrypts the given character using the encoding table.
 *
 * @param c the character to be decrypted.
 * @return the decrypted character.
 */

    @Override
    public char decrypt(char c) {
        for (Map.Entry<Character, Character> entry : encodingTable.entrySet()) {
            if (entry.getValue() == c) {
                return entry.getKey();
            }
        }
        return c;
    }

/**
 * Main method to run the MonoAlphaSubstitution cipher. Takes in three command line
 * arguments: the direction of the cipher ("encrypt" or "decrypt"), the encoding key,
 * and the plaintext/ciphertext to be processed.
 *
 * @param args the command line arguments.
 */

    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("Too few parameters!");
            System.out.println("Usage: java MonoAlphaSubstitution encrypt key \"cipher text\"");
            return;
        } else if(args.length > 3){
            System.out.println("Too many parameters!");
            System.out.println("Usage: java MonoAlphaSubstitution encrypt key \"cipher text\"");
            return;
        }
        String direction = args[0];
        String key = args[1];
        String text = args[2];
        MonoAlphaSubstitution cipher = new MonoAlphaSubstitution(key);
        StringBuilder result = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (direction.equals("encrypt")) {
                result.append(cipher.encrypt(c));
            } else if (direction.equals("decrypt")) {
                result.append(cipher.decrypt(c));
            } else {
                System.out.println("The first parameter must be \"encrypt\" or \"decrypt\"!");
                System.out.println("Usage: java MonoAlphaSubstitution encrypt key \"cipher text\"");
                return;
            }
        }
        System.out.println(result.toString());
    }

}
