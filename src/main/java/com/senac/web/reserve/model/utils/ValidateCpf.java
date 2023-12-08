package com.senac.web.reserve.model.utils;
import java.util.ArrayList;
import java.util.List;

public class ValidateCpf {
    /*
     * String no Java começa no indice 0 logo a posição 10 é o indice 9 e
     * a posicao 11 é o indice 10
     */
    private static final int VALIDATE_FIRST_DIGIT = 10;
    private static final int VALIDATE_SECOND_DIGIT = 11;

    public static boolean validarCpf(String cpf) {
        String cpfValidator = cpf.substring(0,9);
        int firstDigit = VALIDATE_FIRST_DIGIT;
        int secondDigit = VALIDATE_SECOND_DIGIT;
        int calculateFirstDigit = 0;
        int calculateSecondDigit = 0;

        for(int i = 0; i < 9; i++) {
            calculateFirstDigit += Character.getNumericValue(cpf.charAt(i)) * firstDigit;
            firstDigit--;
        }
        int firstDigitVerified = 11 - (calculateFirstDigit % 11);
        firstDigitVerified = firstDigitVerified >= 10 ? 0 : firstDigitVerified;
        cpfValidator += firstDigitVerified;

        for(int i = 0; i < 10; i++) {
            calculateSecondDigit += Character.getNumericValue(cpf.charAt(i)) * secondDigit;
            secondDigit--;
        }
        int secondDigitVerified = 11 - (calculateSecondDigit % 11);
        secondDigitVerified = secondDigitVerified >= 10 ? 0 : secondDigitVerified;
        cpfValidator += secondDigitVerified;
        return !cpfValidator.equals(cpf) || invalidCpfs(cpfValidator);
    }

    private static boolean invalidCpfs(String cpf) {
        List<String> cpfs = new ArrayList<String>();
        cpfs.add("11111111111");
        cpfs.add("22222222222");
        cpfs.add("33333333333");
        cpfs.add("44444444444");
        cpfs.add("55555555555");
        cpfs.add("66666666666");
        cpfs.add("77777777777");
        cpfs.add("88888888888");
        cpfs.add("99999999999");
        return cpfs.contains(cpf);
    }
}