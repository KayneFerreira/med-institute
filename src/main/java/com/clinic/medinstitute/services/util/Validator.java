package com.clinic.medinstitute.services.util;

public class Validator {
    
    public static boolean validateCpf(String cpf) {
        /*
         * Validate first digit
         */
        int firstDigitSum = 0;
        for (int i=0; i<cpf.length()-2; i++) {
            int digit = cpf.charAt(i) - '0';
            firstDigitSum += digit*(10-i);
        }
        int firstDigitValidator = (firstDigitSum*10)%11;
        int firstDigit = cpf.charAt(9) - '0';
        if (firstDigitValidator != firstDigit) {
            return false;
        }

        /*
         * Validate second digit
         */
        int secondDigitSum = 0;
        for (int i=0; i<cpf.length()-1; i++) {
            int digit = cpf.charAt(i) - '0';
            secondDigitSum += digit*(11-i);
        }
        int secondDigitValidator = (secondDigitSum*10)%11;
        int secondDigit = cpf.charAt(10) - '0';
        if (secondDigitValidator != secondDigit) {
            return false;
        }
        return true;
    }
}
