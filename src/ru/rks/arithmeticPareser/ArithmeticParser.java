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
        checkForValidate(
                deleteSpaces(
                        inputString()));
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
     * @param string Переменная строкового типа, содержащая арифметическое выражение.
     * @return результат сопоставления арифметического выражения с регулярным.
     */
    private static boolean checkForConformity(String string) {

        return string.matches("^((-?\\(*-?)*(((\\d+(\\.\\d+)?)|(\\.\\d+))|[A-Za-z]+)\\)*)" +
                "([-+*%\\/]=?((-?\\(*-?)*(((\\d+(\\.\\d+)?)|(\\.\\d+))|[A-Za-z]+)\\)*))+$");
    }

    /**
     * Метод выводит результаты проверки строки на соответствие регулярному выражению
     * и соотвествие открывающих и закывающих скобок в консоль.
     *
     * @param expression Строка, содержащая регулярное выражение.
     */
    static void checkForValidate(String expression) {
        if (checkForConformity(expression)) {
            if(!checkForBrackets(expression)){
                System.out.println("Несоответствие открывающих и закрывающих скобок");
                return;
            }
            if(checkForDoubleSignOperations(expression)){
                System.out.println("Два идущих подряд знака операции");
                return;
            }
            System.out.println("Выражение введено верно");
        }
        else{
            System.out.println("Неверно введено арифметическое выражение");
        }

    }

    /**
     * Метод подсчитывает кол-во открывающих и закрывающих скобок в выражении и возвращает
     * булевое значение в зависимости от равенства их кол-ва.
     *
     * @param expression Переменная строкового типа, содержащая арифметическое выражение.
     * @return булевое значение в зависимости от равенства открывающих скобок: true - если равны,
     * false - если нет, соответственно.
     */
    private static boolean checkForBrackets(String expression) {

        int countClosingBrackets = 0;
        int countOpeningBrackets = 0;

        Pattern patternForOpening = Pattern.compile("\\(");
        Pattern patternForClosing = Pattern.compile("\\)");

        Matcher matcherForClosing = patternForClosing.matcher(expression);
        Matcher matcherForOpening = patternForOpening.matcher(expression);

        while (matcherForClosing.find()) {
            countClosingBrackets++;
        }

        while (matcherForOpening.find()) {
            countOpeningBrackets++;
        }

        return countClosingBrackets == countOpeningBrackets;

    }


    private static String deleteSpaces(String expression) {

        return expression.replaceAll(" ", "");

    }
    private static boolean checkForDoubleSignOperations(String expression){
        Pattern pattern = Pattern.compile("([-+*\\/%]=?){2,}");
        Matcher matcher = pattern.matcher(expression);
        return matcher.find();
    }
}
