/**
 * The Caesar class provides functions for encrypting strings using the Caesar cipher.
 * The Caesar cipher is a simple substitution cipher that replaces each letter in the plaintext with a letter for a fixed number of positions in the alphabet.
 */
public class Caesar {
 /**
 * The main function of the Caesar class is it takes in two command-line arguments: a shift value and a string to be encrypted, and prints out the encrypted string.
 * @param args an array of command-line arguments, where we use args to get the user input. Args[0] is the shift value and args[1] is the string to be encrypted.
 */
    public static void main(String[] args) {
        if(args.length == 2){
            int test = Integer.parseInt(args[0]);
            String tests = args[1];
            System.out.println(rotate(test, tests));
        }else if(args.length > 2){
            System.out.println("Too many parameters!");
            System.out.println("Usage: java Caesar n \"cipher text\"");
        }else{
            System.out.println("Too few parameters!");
            System.out.println("Usage: java Caesar n \"cipher text\"");
        }
    }
/**
 * The rotate function takes in a character and a shift value, and returns the character encrypted using the Caesar cipher with the given shift value.
 * @param shift the amount by which to shift the character in the alphabet
 * @param x the character to be encrypted
 * @return the encrypted character
 */
        public static char rotate(int shift, char x){
            shift = (shift % 26) + 26;
            //System.out.println(shift);
            if(Character.isUpperCase(x)){
                int sh = (((int) x + shift - 65) % 26 + 65);
                return (char)sh;
            } else if(Character.isLowerCase(x)){
                int sh = (((int) x + shift - 97)% 26 + 97);
                return (char)sh;
            } else {
                return x;
            }

        }
        /**
        * The rotate function takes in a shift value and a string, and returns the string encrypted using the Caesar cipher, it uses the previous rotate funcation to change each letter in the string, with the given shift value.
        * @param shift the amount by which to shift the characters in the string
        * @param line the string to be encrypted
        * @return the encrypted string
        */
        public static String rotate(int shift, String line){
            String letter = "";
            for(int i=0; i<line.length(); i++){
                letter += rotate(shift, line.charAt(i));
            }
            return letter;
        }

}
