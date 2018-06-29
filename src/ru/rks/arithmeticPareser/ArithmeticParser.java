package ru.rks.arithmeticPareser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Класс предназначен для синтаксического анализа арифметических выражений.
 */

public class ArithmeticParser {

    public static void main(String[] args) {

        String str = inputString();
        str = deleteSpaces(str);
        System.out.println(str);
        checkForValidate(str);


    }

    /**
     * Метод для ввода арифметического выражения с клавиатуры.
     *
     * @return строка, содержащее арифметическое выражение.
     */
    static String inputString() {

        String string = null;

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println("Введите арифметическое выражение: ");
            string = bufferedReader.readLine();

        } catch (IOException e) {
            e.printStackTrace();

        }

        return string;
    }

    /**
     * Метод возвращает результат сопоставления полученного на вход арифметического выражения с регулярным.
     *
     * @param string
     * @return результат сопоставления арифметического выражения с регулярным.
     */
    private static boolean checkForConformity(String string) {

        return string.matches("^(-?\\(*-?)*(\\d+(?:\\.\\d+)?|\\w+)+(\\s*\\)*\\s*" +
                "[-+*^/%=]\\s*(-?\\(*-?)*(\\d+(?:\\.\\d+)?|\\w)+\\)*)+$");
    }

    /**
     * Метод выводит результаты проверки строки на соответствие регулярному выражению
     * и соотвествие открывающих и закывающих скобок в консоль.
     *
     * @param string
     */
    static void checkForValidate(String string) {

        if (checkForConformity(string)) {

            if (checkForBrackets(string)) {

                System.out.println("Выражение введено верно");

            } else {

                System.out.println("Несоотвествие открывающих и закрывающих скобок");

            }

        } else {

            System.out.println("Выражение введено некорректно");

        }
    }

    /**
     * Метод подсчитывает кол-во открывающих и закрывающих скобок в выражении и возвращает
     * булевое значение в зависимости от равенства их кол-ва.
     *
     * @param string Переменная строкового типа, содержащая арифметическое выражение.
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

}
