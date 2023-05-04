/**
 * An abstract class that implements the Cipher interface by providing concrete
 * implementations of the encrypt and decrypt methods based on abstract methods
 * for encrypting and decrypting individual characters.
 */
public abstract class Substitution implements Cipher {
    /**
     * Encrypts the given plaintext string by calling the abstract encrypt
     * method on each character in the string and concatenating the results.
     *
     * @param plaintext the plaintext string to encrypt
     * @return the encrypted ciphertext string
     */
    public String encrypt(String plaintext) {
        StringBuilder ciphertext = new StringBuilder();
        for (char c : plaintext.toCharArray()) {
            ciphertext.append(encrypt(c));
        }
        return ciphertext.toString();
    }

    /**
     * Decrypts the given ciphertext string by calling the abstract decrypt
     * method on each character in the string and concatenating the results.
     *
     * @param cryptotext the ciphertext string to decrypt
     * @return the decrypted plaintext string
     */
    public String decrypt(String cryptotext) {
        StringBuilder plaintext = new StringBuilder();
        for (char c : cryptotext.toCharArray()) {
            plaintext.append(decrypt(c));
        }
        return plaintext.toString();
    }

    /**
     * Encrypts a single character by substituting it for another character.
     * This method is left abstract and must be implemented by a concrete
     * subclass.
     *
     * @param c the character to encrypt
     * @return the encrypted character
     */
    public abstract char encrypt(char c);

    /**
     * Decrypts a single character by substituting it for another character.
     * This method is left abstract and must be implemented by a concrete
     * subclass.
     *
     * @param c the character to decrypt
     * @return the decrypted character
     */
    public abstract char decrypt(char c);
}

