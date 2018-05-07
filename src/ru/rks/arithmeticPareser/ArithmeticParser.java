package ru.rks.arithmeticPareser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ArithmeticParser {

    public static void main(String[] args) {

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {

            String str;
            System.out.println("Введите арифметическое выражение: ");
            str = bufferedReader.readLine();
            checkForValidate(str);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Метод возвращает результат сопоставление полученного на вход арифметического выражения с регулярным.
     * @param string
     * @return
     */
    private static boolean checkForConformity(String string) {

        return string.matches("^(-?\\(*-?)*(\\d+(?:\\.\\d+)?|\\w+)+(\\s*\\)*\\s*" +
                "[-+*^\\/]\\s*(-?\\(*-?)*(\\d+(?:\\.\\d+)?|\\w)+\\)*)+$");
    }

    /**
     * Метод выводит результаты проверки строки на соответствие регулярному выражению
     * и соотвествие открывающих и закывающих скобок в консоль.
     * @param string
     */
    static void checkForValidate(String string){

        if(checkForConformity(string)){

            if( checkForBrackets(string)) {

                System.out.println("Выражение введено верно");

            }

                else {

                    System.out.println("Несоотвествие открывающих и закрывающих скобок");

                }

            }

            else{

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


    /*private static String deleteSpaces(String string) {

        return string.replaceAll(" ", "");

    }*/

}
