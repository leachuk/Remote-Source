package au.com.entitysolutions.taskflows.login.util;

import java.security.SecureRandom;

import java.util.ArrayList;

import java.util.regex.Matcher;

import org.apache.commons.lang.ArrayUtils;

public class RandomPasswordGen {
    
    public RandomPasswordGen() {
        super();
    }
    
    public RandomPasswordGen(int passwordLength) {
        super();
        this.passwordLength = passwordLength;
    }

    //Determines the total no. of characters to use. Default 8
    private int passwordLength = 8;

    private SecureRandom random = new SecureRandom();

    private static final char[] SPECIALCHAR =
    { '!', '@', '#', '$', '%', '^', '&', '*'}; //Use only the special characters assigned to no.s 1-8

    /**
     * Alphabet consisting of the lower case letters A-Z.
     *
     * @since ostermillerutils 1.00.00
     */
    private static final char[] LOWERCASE_LETTERS_ALPHABET =
    { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
      'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', };

    private static final char[] UPPERCASE_LETTERS_ALPHABET =
    { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
      'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', };

    private static final char[] DIGIT =
    { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

    private static final char[] CHARSET =
        ArrayUtils.addAll(ArrayUtils.addAll(ArrayUtils.addAll(SPECIALCHAR,
                                                              LOWERCASE_LETTERS_ALPHABET),
                                            UPPERCASE_LETTERS_ALPHABET),
                          DIGIT);


    /**
     * Generate a random password
     * @return
     */
    public String getPass() {
        int charSetLen = CHARSET.length;

        //First generate a random 4 character sequence
        //Next, add 1 of each type to ensure compliance
        //The 1 of each type will be added to a random location
        char[] password = new char[passwordLength];

        //Generate a random character length. 
        //Length 4 less than the total required
        //The 4 extra blocks will be padded with one each of lowercase, uppercase,
        //digits and special characters 
        for (int i = 0; i < passwordLength - 4; i++) {
            char nextCharacter = CHARSET[random.nextInt(charSetLen)];
            password[i] = nextCharacter;
        }

        //Add 1 of each to ensure compliance
        //Add to random locations
        addChar(password, SPECIALCHAR[random.nextInt(SPECIALCHAR.length)]
                          ,passwordLength - 4);
        
        addChar(password, LOWERCASE_LETTERS_ALPHABET[random.nextInt(LOWERCASE_LETTERS_ALPHABET.length)]
                        ,passwordLength - 3);
      
        addChar(password, UPPERCASE_LETTERS_ALPHABET[random.nextInt(UPPERCASE_LETTERS_ALPHABET.length)]
                        ,passwordLength - 2);
      
        addChar(password, Character.forDigit(random.nextInt(9), 10)
                        ,passwordLength - 1);
      
        //TODO Possibly randomize the whole array once again
        return String.valueOf(password);
    }

    /**
     * Picks a random index in the existing array 
     * and swaps the character out to the end and inserts the new character
     * at the random index
     * @param password
     * @param character
     * @param endIndx
     */
    private void addChar(char[] password, char character, int endIndx) {
       int insertIdx = random.nextInt(passwordLength-endIndx);
       char tmp = password[insertIdx];
       password[endIndx] = tmp;
       password[insertIdx] = character;
    }

    public static void main(String[] s) {
        RandomPasswordGen gen = new RandomPasswordGen();
          String pass = gen.getPass();
          System.out.println(pass);
          String emailMsg = "Your password has been reset as requested. Please login using the temporary password assigned to you. We advise that you change the password immediately after login. Your temporary password is $TEMPPWD$";
          emailMsg = emailMsg.replaceAll("\\$TEMPPWD\\$", Matcher.quoteReplacement("4QwW*$>d"));        
          System.out.println(emailMsg);
    }

    public void setPasswordLength(int totalChars) {
        this.passwordLength = totalChars;
    }

    public int getPasswordLength() {
        return passwordLength;
    }
}
