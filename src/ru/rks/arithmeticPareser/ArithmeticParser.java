package ru.rks.arithmeticPareser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ArithmeticParser {

    public static void main(String[] args) {

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {

            String str = bufferedReader.readLine();
            checkForBrackets(str);
            String expressionWithoutSpaces = deleteSpaces(str);
            String[] strings = splitExpression(expressionWithoutSpaces);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Метод подсчитывает кол-во открывающих и закрывающих скобок в выражении и на основке
     * @param string
     */
    static void checkForValidate(String string) {

        if (string.matches("^(-?\\(*-?)*(\\d+(?:\\.\\d+)?|\\w+)+(\\s*\\)*\\s*" +
                "[-+*^\\/]\\s*(-?\\(*-?)*(\\d+(?:\\.\\d+)?|\\w)+\\)*)+$")) {
            System.out.println("Выражение введено правильно");
        } else {
            System.out.println("Выражение введено некорректно");
        }

    }

    /**
     * Метод подсчитывает кол-во открывающих и закрывающих скобок в выражении и возвращает
     * булевое значение в зависимости от равенства их кол-ва.
     *
     * @param string
     * @return булевое значение в зависимости от равенства открывающих скобок: true - если равны,
     * false - если нет, соответственно.
     */
    private static boolean checkForBrackets(String string) {

        int countClosingBrackets = 0;
        int countOpeningBrackets = 0;

        Pattern patternForOpening = Pattern.compile("\\(");
        Pattern patternForClosing = Pattern.compile("\\)");

        Matcher matcherForClosing = patternForClosing.matcher(string);
        Matcher matcherForOpening = patternForOpening.matcher(string);

        while (matcherForClosing.find()) {
            countClosingBrackets++;
        }

        while (matcherForOpening.find()) {
            countOpeningBrackets++;
        }

        return countClosingBrackets == countOpeningBrackets;

    }



    private static String deleteSpaces(String string) {

        return string.replaceAll(" ", "");

    }

    private static String[] splitExpression(String string) {

        return string.split("[-+*/^)]");

    }

}
