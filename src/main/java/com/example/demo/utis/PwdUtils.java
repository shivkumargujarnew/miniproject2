package com.example.demo.utis;
import java.security.SecureRandom;

import org.springframework.stereotype.Component;
@Component
public class PwdUtils {


	    private static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
	    private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	    private static final String DIGITS = "0123456789";
	    private static final String SPECIAL_CHARACTERS = "!@#$%^&*()-_=+<>?/";

	    // Combine all characters to form a pool
	    private static final String ALL_CHARACTERS = LOWERCASE + UPPERCASE + DIGITS + SPECIAL_CHARACTERS;

	    public static String generatePassword(int length) {
	        SecureRandom random = new SecureRandom();
	        StringBuilder password = new StringBuilder();

	        // Ensure the password has at least one lowercase, one uppercase, one digit, and one special character
	        password.append(LOWERCASE.charAt(random.nextInt(LOWERCASE.length())));
	        password.append(UPPERCASE.charAt(random.nextInt(UPPERCASE.length())));
	        password.append(DIGITS.charAt(random.nextInt(DIGITS.length())));
	        password.append(SPECIAL_CHARACTERS.charAt(random.nextInt(SPECIAL_CHARACTERS.length())));

	        // Fill the rest of the password with random characters from the pool
	        for (int i = password.length(); i < length; i++) {
	            password.append(ALL_CHARACTERS.charAt(random.nextInt(ALL_CHARACTERS.length())));
	        }

	        // Shuffle the password to ensure the random characters are not predictable
	        StringBuilder shuffledPassword = new StringBuilder(password.toString());
	        for (int i = 0; i < shuffledPassword.length(); i++) {
	            int j = random.nextInt(shuffledPassword.length());
	            char temp = shuffledPassword.charAt(i);
	            shuffledPassword.setCharAt(i, shuffledPassword.charAt(j));
	            shuffledPassword.setCharAt(j, temp);
	        }

	        return shuffledPassword.toString();
	    }

	   
	}


