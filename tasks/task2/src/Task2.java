public class Task2 {
    public static void main(String[] args) {
//        task1
//        System.out.println(repeat("mice", 5)); // ➞ "mmmmmiiiiiccccceeeee"
//        System.out.println(repeat("hello", 3)); // ➞ "hhheeellllllooo"
//        System.out.println(repeat("stop", 1) ); // ➞ "stop"


//        task2
//        System.out.println(differenceMaxMin(new int[] {10, 4, 1, 4, -10, -50, 32, 21})); // -> 82
//        System.out.println(differenceMaxMin(new int[] {44, 32, 86, 19})); // -> 67


//        task3
//        System.out.println(isAvgWhole(new int[] {1,3})); // ➞ true
//        System.out.println(isAvgWhole(new int[] {1,2,3,4})); //  ➞ false
//        System.out.println(isAvgWhole(new int[] {1, 5, 6})); // ➞ true
//        System.out.println(isAvgWhole(new int[] {1,1,1})); // ➞ true
//        System.out.println(isAvgWhole(new int[] {9, 2, 2, 5})); //  ➞ false


//        task4
//        int[] massive1 = cumulativeSum(new int[] {1, 2 ,3}); // -> 1 3 6
//        for (int j : massive1) {
//            System.out.print(j + " ");
//        }
//        System.out.println();
//
//        int[] massive2 = cumulativeSum(new int[] {1, -2 ,3}); // -> 1 -1 2
//        for (int j : massive2) {
//            System.out.print(j + " ");
//        }
//        System.out.println();
//
//        int[] massive3 = cumulativeSum(new int[] {3, 3, -2, 408, 3, 3}); // -> 3 6 4 412 415 418
//        for (int j : massive3) {
//            System.out.print(j + " ");
//        }


//        task5
//        System.out.println(getDecimalPlaces("43.20")); // -> 2
//        System.out.println(getDecimalPlaces("400")); // 0
//        System.out.println(getDecimalPlaces("3.1002")); // 4


//        task6
        System.out.println(fibonacci(3)); // -> 2
        System.out.println(fibonacci(7)); // -> 13
        System.out.println(fibonacci(12)); // -> 144


//        task7
//        System.out.println(isValid("59001")); // ➞ true
//        System.out.println(isValid("853a7")); // -> false
//        System.out.println(isValid("73 32")); // -> false
//        System.out.println(isValid("393939")); // -> false


//        task8
//        System.out.println(isStrangePair("ratio", "orator")); // -> true
//        System.out.println(isStrangePair("sparkling", "groups")); // -> true
//        System.out.println(isStrangePair("bush", "hubris")); // false
//        System.out.println(isStrangePair("", "")); // // -> true
//        System.out.println(isStrangePair("ab", "ba")); // -> true


//        task9
//        System.out.println(isPrefix("automation", "auto-")); // -> true
//        System.out.println(isPrefix("retrospect", "sub-")); // -> false
//        System.out.println(isSuffix("arachnophobia", "-phobia")); // -> true
//        System.out.println(isSuffix("vocation", "-logy")); // -> false


//         task10
//        System.out.println(boxSeq(0)); // -> 0
//        System.out.println(boxSeq(1)); // -> 3
//        System.out.println(boxSeq(2)); // -> 2
//        System.out.println(boxSeq(5)); // -> 7
//        System.out.println(boxSeq(8)); // -> 8
//        System.out.println(boxSeq(9)); // -> 11
    }
    //   1. Создайте функцию, которая повторяет каждый символ в строке n раз.
    public static String repeat(String line, int count) {
        char[] newLine = line.toCharArray();
        String result = "";
        for (int i = 0; i < newLine.length; i++) {
            for (int j = 0; j < count; j++) {
                result += newLine[i];
            }
        }
        return result;
    }
    //    2. Создайте функцию, которая принимает массив и возвращает разницу
    // между самым большим и самыми маленькими числами
    public static int differenceMaxMin(int[] array){
        int maxValue = array[0];
        int minValue = array[0];
        for (int i = 0; i < array.length; i++) {
            if (array[i] > maxValue) {
                maxValue = array[i];
            }
            if (array[i] < minValue) {
                minValue = array[i];
            }
        }
        return maxValue - minValue;
    }
    //     3. Создайте функцию, которая принимает массив в качестве аргумента и возвращает
    //        true или false в зависимости от того, является ли среднее значение всех элементов
    //        массива целым числом или нет.
    public static boolean isAvgWhole(int[] array){
        int sum = 0;
        for (int j : array) {
            sum += j;
        }
        return sum / array.length == (double) sum / array.length;
    }
    //   4. Создайте метод, который берет массив целых чисел и возвращает массив, в
    //      котором каждое целое число является суммой самого себя + всех предыдущих
    //      чисел в массиве
    public static int[] cumulativeSum(int[] array){
        int[] newArray = new int[array.length];
        newArray[0] = array[0];
        for (int i = 1; i < newArray.length; i++){
            newArray[i] = newArray[i-1] + array[i];
        }
        return newArray;
    }
    //   5. Создайте функцию, которая возвращает число десятичных знаков, которое
    //      имеет число(заданное в виде строки). Любые нули после десятичной точки
    //      отсчитываются в сторону количества десятичных знаков.
    public static int getDecimalPlaces(String number){
        if (number.contains(".")){
            return 0;
        }
        String newNumber = number;
        int startDecimalPlace = newNumber.indexOf(".");
        String decimalPlace = newNumber.substring(startDecimalPlace, newNumber.length());
        return decimalPlace.length() - 1;
    }


    //    6. Создайте функцию, которая при заданном числе возвращает соответствующее
//    число Фибоначчи.
    public static int fibonacci(int number) {
        if (number == 0) {
            return 0;
        } else if (number == 1) {
            return 1;
        }
        return fibonacci(number - 1) + fibonacci(number - 2);
    }


//       7. Почтовые индексы состоят из 5 последовательных цифр. Учитывая строку,
//          напишите функцию, чтобы определить, является ли вход действительным
//          почтовым индексом. Действительный почтовый индекс выглядит следующим образом:
//            – Должно содержать только цифры (не допускается использование нецифровых цифр).
//            – Не должно содержать никаких пробелов.
//            – Длина не должна превышать 5 цифр.

    public static boolean isValid(String index) {
        String regex = "\\d+"; // + означает "один или несколько раз" и \d означает "цифра"
        if (index.length() <= 5) {
            if (index.matches(regex)) {
                return true;
            }
        }
        return false;
    }


    //        8. Пара строк образует странную пару, если оба из следующих условий истинны:
//            – Первая буква 1-й строки = последняя буква 2-й строки.
//            – Последняя буква 1-й строки = первая буква 2-й строки.
//            – Создайте функцию, которая возвращает true, если пара строк представляет собой
//              странную пару, и false в противном случае.
    public static boolean isStrangePair(String firstLine, String secondLine) {

        if (firstLine.isEmpty() && secondLine.isEmpty()) {
            return true;
        }

        char firstCharacterFirstLine = firstLine.charAt(0);
        char lastCharacterSecondLine = secondLine.charAt(secondLine.length() - 1);

        char lastCharacterFirstLine = firstLine.charAt(firstLine.length() - 1);
        char firstCharacterSecondLine = secondLine.charAt(0);
        return firstCharacterFirstLine == lastCharacterSecondLine && lastCharacterFirstLine == firstCharacterSecondLine;
    }


    //     9. Создайте две функции: isPrefix(word, prefix-) и isSuffix (word, -suffix).
//           – isPrefix должен возвращать true, если он начинается с префиксного аргумента.
//           – isSuffix должен возвращать true, если он заканчивается аргументом суффикса.
//–            В противном случае верните false.
    public static boolean isPrefix(String firstWord, String secondWord) {

        secondWord = secondWord.substring(0, secondWord.length() - 1);
        System.out.println(secondWord);
        return firstWord.startsWith(secondWord);
    }

    public static boolean isSuffix(String firstWord, String secondWord) {
        secondWord = secondWord.substring(1, secondWord.length());
        System.out.println(secondWord);
        return firstWord.endsWith(secondWord);
    }


    //    10. Создайте функцию, которая принимает число (шаг) в качестве аргумента и
//    возвращает количество полей на этом шаге последовательности.
//    Шаг 0: начните с 0
//    Шаг 1: Добавьте 3
//    Шаг 2: Вычтите 1
//    Повторите Шаги 1 И 2 ...
    public static int boxSeq(int number) {
        int sum = 0;
        if (number >= 1) {
            sum = 3;
        }
        for (int i = 2; i <= number; i++) {
            if (i % 2 == 1) {
                sum += 3;
            }
            if (i % 2 == 0) {
                sum -= 1;
            }
        }
        return sum;
    }

}

