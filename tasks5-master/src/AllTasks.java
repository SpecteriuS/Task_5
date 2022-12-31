import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;

import java.security.MessageDigest;
import java.util.Base64;

public class AllTasks {

    public static void main(String[] args) throws NoSuchAlgorithmException {

        // Task 1 ------------------------------------------------------------------------------------------------------

        PrintHelper.printTask(1);
        PrintHelper.blank();

        int[] answer1_1 = encrypt("Hi there!");
        for (int i = 0; i < answer1_1.length; i++) {
            System.out.print(answer1_1[i] + " ");
        }
        PrintHelper.blank();

        String answer1_2 = decrypt(answer1_1);
        System.out.println(answer1_2);

        PrintHelper.blank();

        // Task 2 ------------------------------------------------------------------------------------------------------

        PrintHelper.printTask(2);
        PrintHelper.blank();

        boolean answer2 = canMove("Bishop", "A7", "G1");
        System.out.println(answer2);

        PrintHelper.blank();


        // Task 3 ------------------------------------------------------------------------------------------------------

        PrintHelper.printTask(3);
        PrintHelper.blank();

        boolean answer3 = canComplete("butl", "beautiful");
        System.out.println(answer3);

        PrintHelper.blank();


        // Task 4 ------------------------------------------------------------------------------------------------------

        PrintHelper.printTask(4);
        PrintHelper.blank();

        int answer4 = sumDigProd(1, 2, 3, 4, 5, 6);
        System.out.println(answer4);

        PrintHelper.blank();


        // Task 5 ------------------------------------------------------------------------------------------------------

        PrintHelper.printTask(5);
        PrintHelper.blank();

        ArrayList<String> answer5 = sameVowelGroup(new String[]{"hoops", "chuff", "bot", "bottom"});
        for (int i = 0; i < answer5.size(); i++) {
            System.out.print(answer5.get(i) + " ");
        }

        PrintHelper.blank();
        PrintHelper.blank();

        // Task 6 ------------------------------------------------------------------------------------------------------

        PrintHelper.printTask(6);
        PrintHelper.blank();

        boolean answer6 = validateCard(1234567890123452L);
        System.out.println(answer6);

        PrintHelper.blank();


        // Task 7 ------------------------------------------------------------------------------------------------------

        PrintHelper.printTask(7);
        PrintHelper.blank();

        String answer7 = numToEng(919);
        System.out.println(answer7);

        PrintHelper.blank();


        // Task 8 ------------------------------------------------------------------------------------------------------

        PrintHelper.printTask(8);
        PrintHelper.blank();

        String answer8 = getSHA256Hash("password123");
        System.out.println(answer8);

        PrintHelper.blank();


        // Task 9 ------------------------------------------------------------------------------------------------------

        PrintHelper.printTask(9);
        PrintHelper.blank();

        String answer9 = correctTitle("TYRION LANNISTER, HAND OF THE QUEEN.");
        System.out.println(answer9);

        PrintHelper.blank();


        // Task 10 -----------------------------------------------------------------------------------------------------

        PrintHelper.printTask(10);
        PrintHelper.blank();

        String answer10 = hexLattice(61);
        System.out.println(answer10);

        PrintHelper.blank();

    }

    // Task 1 ----------------------------------------------------------------------------------------------------------

    // Returns the encoded message
    public static int[] encrypt(String str) {
        char [] str_char = str.toCharArray();

        int[] arr = new int[str.length()];
        arr[0] = str_char[0];

        for (int i = 1; i < str_char.length; i++) {
            arr[i] = str_char[i] - str_char[i-1];
        }
        return arr;
    }


    // Returns the decoded message
    public static String decrypt(int[] arr) {
        String str = "";
        int[] arr_char = new int[arr.length];
        arr_char[0] = arr[0];

        for (int i = 1; i < arr.length; i++) {
            arr_char[i] = arr[i] + arr_char[i-1];
        }

        for (int i = 0; i < arr_char.length; i++){
            str += (char)arr_char[i];
        }

        return str;
    }


    // Task 2 ----------------------------------------------------------------------------------------------------------

    // Returns true if the chess piece can make such a move
    public static boolean canMove(String figure, String start, String end) {
        int y_start = Integer.parseInt(start.substring(1));
        int y_end = Integer.parseInt(end.substring(1));

        String letters = "-ABCDEFGH";

        int x_start = letters.indexOf(start.charAt(0));
        int x_end = letters.indexOf(end.charAt(0));

        int delta_x = Math.abs(x_end - x_start);
        int delta_y = Math.abs(y_end - y_start);

        if (figure.toLowerCase().equals("pawn")) {
            if (delta_x == 0 & delta_y < 3) {
                return true;
            }
        }

        if (figure.toLowerCase().equals("rook")) {
            if (delta_x == 0 | delta_y == 0) {
                return true;
            }
        }

        if (figure.toLowerCase().equals("knight")) {
            if ((delta_x + delta_y == 3) & (delta_x == 1 | delta_y == 1)) {
                return true;
            }
        }

        if (figure.toLowerCase().equals("bishop")) {
            if (delta_x == delta_y) {
                return true;
            }
        }

        if (figure.toLowerCase().equals("queen")) {
            if (delta_x == delta_y | delta_x == 0 | delta_y == 0) {
                return true;
            }
        }

        if (figure.toLowerCase().equals("king")) {
            if (delta_x < 2 & delta_y < 2) {
                return true;
            }
        }

        return false;
    }

    // Task 3 ----------------------------------------------------------------------------------------------------------

    // Returns true if it is possible to complete the word by adding characters
    public static boolean canComplete(String word, String full_word){

        char[] word_char = word.toCharArray();
        char[] full_word_char = full_word.toCharArray();

        int previous_index = -1;
        int counter = 0;

        for (int i = 0; i < word_char.length; i++) {
            boolean is_found = false;
            for (int j = previous_index+1; j < full_word_char.length; j++) {
                if ((word_char[i] == full_word_char[j]) & (!is_found)) {

                    counter += 1;

                    previous_index = j;
                    is_found = true;
                }
            }
        }

        return word_char.length == counter;
    }

    // Task 4 ----------------------------------------------------------------------------------------------------------

    // Adds all passed numbers
    // Multiplies result digits until one digit remains
    // Returns it
    public static int sumDigProd(int ... args) {

        int sum = 0;
        for (int i = 0; i < args.length; i++) {
            sum += args[i];
        }

        String number_str = Integer.toString(sum);

        while (number_str.length() > 1) {
            char[] chars = number_str.toCharArray();
            int[] digits = new int[chars.length];

            for (int i = 0; i < chars.length; i++) {
                digits[i] = Integer.parseInt(String.valueOf(chars[i]));
            }

            sum = 1;

            for (int i = 0; i < digits.length; i++) {
                sum = sum * digits[i];
            }

            number_str = Integer.toString(sum);
        }

        return sum;
    }

    // Task 5 ----------------------------------------------------------------------------------------------------------

    // Returns an array of words containing all the vowels from the first word
    public static ArrayList<String> sameVowelGroup(String[] arr) {

        ArrayList<Character> control_vowels = unique_vowels(arr[0]);

        ArrayList<String> answer = new ArrayList<>();

        for (int i = 0; i < arr.length; i++) {
            ArrayList<Character> current_vowels = unique_vowels(arr[i]);
            boolean is_bad = false;
            for (int j = 0; j < control_vowels.size(); j++) {
                if (!current_vowels.contains(control_vowels.get(j))) {
                    is_bad = true;
                }
            }
            if (!is_bad) {
                answer.add(arr[i]);
            }
        }

        return answer;
    }

    // Returns array of unique vowels of string
    public static ArrayList<Character> unique_vowels(String str){
        ArrayList<Character> vowels = new ArrayList<Character>();
        vowels.add('e');
        vowels.add('u');
        vowels.add('i');
        vowels.add('o');
        vowels.add('a');
        vowels.add('y');

        ArrayList<Character> answer = new ArrayList<Character>();

        for (int i = 0; i < str.length(); i++) {
            if ((vowels.contains(str.charAt(i))) & (!answer.contains(str.charAt(i)))) {
                answer.add(str.charAt(i));
            }
        }

        return answer;
    }

    // Task 6 ----------------------------------------------------------------------------------------------------------

    // Returns true if number is a valid credit card number
    public static boolean validateCard(long number) {
        char [] number_char = Long.toString(number).toCharArray();

        int[] digit_arr = new int[number_char.length];
        for (int i = 0; i < digit_arr.length-1; i++) {
            digit_arr[i] = Integer.parseInt(String.valueOf(number_char[i]));
        }

        int control_digit = Integer.parseInt(String.valueOf(number_char[number_char.length-1]));

        if ((digit_arr.length < 13) | (digit_arr.length > 18)) {
            return false;
        }

        for (int i = 0; i < digit_arr.length; i++) {
            if (i % 2 == digit_arr.length % 2) {
                digit_arr[i] = weird_x2(digit_arr[i]);
            }
        }

        int sum = 0;
        for (int i = 0; i < digit_arr.length; i++) {
            sum += digit_arr[i];
        }

        int last_of_sum = Integer.parseInt(String.valueOf(String.valueOf(sum).charAt(String.valueOf(sum).length()-1)));

        return 10-last_of_sum == control_digit;
    }

    public static int weird_x2(int digit) {
        int answer = digit * 2;

        if (answer > 9) {
            answer = 2 * digit - 9;
        }

        return answer;
    }

    // Task 7 ----------------------------------------------------------------------------------------------------------

    // Converts a number to its word representation
    public static String numToEng(int number) {
        int units = number % 10;
        int dozens = (number % 100 - units) / 10;
        int hundreds = (number - dozens - units) / 100;

        String answer = "";

        String[] digit_words = new String[]{"-", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
        String[] dozens_words = new String[]{"-", "ten", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"};
        String[] dozens_before_20 = new String[]{"ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"};

        if (hundreds > 0) {
            answer += digit_words[hundreds];
            answer += " hundred ";
        }

        if (dozens > 1) {
            answer += dozens_words[dozens] + " ";
            if (units > 0) {
                answer += digit_words[units];
            }
        } else if (dozens > 0) {
            answer += dozens_before_20[units];
        } else {
            answer += digit_words[units];
        }

        return answer;
    }

    // Task 8 ----------------------------------------------------------------------------------------------------------

    // Returns SHA256 hash of string
    public static String getSHA256Hash(String str) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(str.getBytes(StandardCharsets.UTF_8));
        final StringBuilder hexString = new StringBuilder();
        for (int i = 0; i < hash.length; i++) {
            final String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1)
                hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }

    // Task 9 ----------------------------------------------------------------------------------------------------------

    // Returns a string with proper case for character titles in Game of Thrones
    public static String correctTitle(String str) {
        str = str.toLowerCase();
        String[] words = str.split(" ");

        for (int i = 0; i < words.length; i++) {
            if ((!words[i].equals("and")) && (!words[i].equals("the")) && (!words[i].equals("of")) && (!words[i].equals("in"))) {
                words[i] = words[i].substring(0, 1).toUpperCase() + words[i].substring(1);
            }
        }

        String answer = "";
         for (int i = 0; i < words.length; i++) {
             answer += words[i] + " ";
         }

        return answer;
    }

    // Task 10 ---------------------------------------------------------------------------------------------------------

    // Draws a hexagonal grid
    public static String hexLattice(int number) {
        if (number == 1) {
            return "o";
        }

        boolean is_good_number = false;

        int i = 0;
        int value = 1;

        while (true) {
            value += 6 + i * 6;

            if (value == number) {
                is_good_number = true;
                break;
            }

            if (value > number) {
                break;
            }

            i++;
        }

        if (!is_good_number) {
            return "Invalid";
        }

        int max = 2 * i + 3;
        int min = i + 2;

        int string_length = max * 2 - 1;

        StringBuilder answer = new StringBuilder();

        for (int j = min; j <= max; j++) {
            StringBuilder blank = new StringBuilder();
            for (int k = 1; k <= (string_length - (2 * j - 1))/2; k++) {
                blank.append(" ");
            }

            answer.append(blank);

            for (int k = 1; k <= j; k++) {
                answer.append("o ");
            }

            if (blank.length() > 1) {
                answer.append(blank.substring(0, blank.length() - 1));
            }

            answer.append("\n");
        }

        for (int j = max-1; j >= min; j--) {
            StringBuilder blank = new StringBuilder();
            for (int k = 1; k <= (string_length - (2 * j - 1))/2; k++) {
                blank.append(" ");
            }

            answer.append(blank);

            for (int k = 1; k <= j; k++) {
                answer.append("o ");
            }

            if (blank.length() > 1) {
                answer.append(blank.substring(0, blank.length() - 1));
            }

            answer.append("\n");
        }

        return answer.substring(0);
    }

}
