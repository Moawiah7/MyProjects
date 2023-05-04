import java.util.Arrays;

/**
 * A given array of the expected grequency of English letters in alphabetical order.
 */
public class Brutus {

    public static final double[] english = {
        0.0855, 0.0160, 0.0316, 0.0387, 0.1210, 0.0218, 0.0209, 0.0496, 0.0733,
        0.0022, 0.0081, 0.0421, 0.0253, 0.0717, 0.0747, 0.0207, 0.0010, 0.0633,
        0.0673, 0.0894, 0.0268, 0.0106, 0.0183, 0.0019, 0.0172, 0.0011
    };
/**
 * Returns an integer array of the occurrence count for each letter in the given string.
 * 
 * @param str the string to count occurrences of each letter
 * @return an array of the occurrence count for each letter in the given string
 */
    public static int[] count(String str) {
        int[] charCounts = new int[26];
        Arrays.fill(charCounts, 0);

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (Character.isLetter(c)) {
                charCounts[Character.toLowerCase(c) - 'a']++;
            }
        }

        return charCounts;
    }
/**
 * Returns an array of the frequency for each letter in the given string.
 * 
 * @param str the string to calculate the frequency of each letter
 * @return an array of the frequency for each letter in the given string
 */
    public static double[] frequency(String str) {
        int[] charCounts = count(str);
        double[] charFreqs = new double[26];

        int totalChars = 0;
        for (int i = 0; i < charCounts.length; i++) {

            totalChars += charCounts[i];
        }

        for (int i = 0; i < charFreqs.length; i++) {
            charFreqs[i] = (double) charCounts[i] / totalChars;
        }

        return charFreqs;
    }
/**
 * Computes the chi-squared value between two frequency arrays.
 * @param freq1 the first frequency array
 * @param freq2 the second frequency array
 * @return the chi-squared value between the two frequency arrays
 */
    public static double chiSquared(double[] freq1, double[] freq2) {
        double sum = 0.0;
        for (int i = 0; i < freq1.length; i++) {
            double diff = freq1[i] - freq2[i];
            sum += (diff * diff) / freq2[i];
        }
        return sum;
    }
/**
 * Reads a single encrypted text string as a command line argument, performs a thourough check on the text.
 * @param args an array of command-line arguments, where we use args to get the user input. Args[1] is the string to be decrypted.
 */
    public static void main(String[] args) {
        if (args.length > 1) {
            System.out.println("Too many parameters!");
            System.out.println("Usage: java Brutus \"cipher text\"");
        }else if(args.length < 1){
            System.out.println("Too few parameters!");
            System.out.println("Usage: java Brutus \"cipher text\"");
        }else{
            
        

        String ciphertext = args[0];
        double minChiSquared = Double.MAX_VALUE;
        int bestShift = 0;

        for (int shift = 0; shift < 26; shift++) {
            String plaintext = Caesar.rotate(shift, ciphertext);
            double[] plaintextFreqs = frequency(plaintext);
            double chiSquared = chiSquared(plaintextFreqs, english);
            if (chiSquared < minChiSquared) {
                minChiSquared = chiSquared;
                bestShift = shift;
            }
        }

        System.out.println(Caesar.rotate(bestShift, ciphertext));
    }
    }
}
