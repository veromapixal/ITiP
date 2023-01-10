import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;
public class Task3 {
    public static void main(String[] args) {
        //task1

        //System.out.println(solutions(1, 0, -1));// ➞ 2
        //System.out.println(solutions(1, 0, 0));// ➞ 1
        //System.out.println(solutions(1, 0, 1));// ➞ 0

        //task2

        //System.out.println(findZip("all zip files are zipped"));// ➞ 18
        //System.out.println(findZip("all zip files are compressed"));// ➞ -1

        //task3

        //System.out.println(checkPerfect(6));// ➞ true
        //System.out.println(checkPerfect(28));// ➞ true
        //System.out.println(checkPerfect(496));// ➞ true
        //System.out.println(checkPerfect(12));// ➞ false
        //System.out.println(checkPerfect(97));// ➞ false

        //task4

        //System.out.println(flipEndChars("Cat, dog, and mouse.")); //➞ ".at, dog, and mouseC"
        //System.out.println(flipEndChars("ada")); //➞ "Two's a pair."
        //System.out.println(flipEndChars("Ada")); //➞ "adA"
        //System.out.println(flipEndChars("z")); //➞ "Incompatible."

        //task5

        //System.out.println(isValidHexCode("#CD5C5C"));// ➞ true
        //System.out.println(isValidHexCode("#EAECEE"));// ➞ true
        //System.out.println(isValidHexCode("#eaecee"));// ➞ true
        //System.out.println(isValidHexCode("#CD5C58C"));// ➞ false
        //System.out.println(isValidHexCode("#CD5C5Z"));// ➞ false
        //System.out.println(isValidHexCode("#CD5C&C"));// ➞ false
        //System.out.println(isValidHexCode("CD5C5C"));// ➞ false

        //task6

        //System.out.println(same(new int[]{1, 3, 4, 4, 4}, new int[]{2, 5, 7}));//➞ true;
        //System.out.println(same(new int[]{9, 8, 7, 6}, new int[]{4, 4, 3, 1}));//➞ false;
        //System.out.println(same(new int[]{2}, new int[]{3, 3, 3, 3, 3}));//➞ true;

        //task7

        //System.out.println(isKaprekar(3));//➞ false;
        //System.out.println(isKaprekar(5));//➞ false;
        //System.out.println(isKaprekar(297));//➞ true;

        //test 8
        //System.out.println(longestZero("01100001011000")); // -> 0000
        //System.out.println(longestZero("100100100")); // -> 00
        //System.out.println(longestZero("11111")); // -> ""

        //test 9
        //System.out.println(nextPrime(12)); // -> 13
        //System.out.println(nextPrime(24)); // -> 29
        //System.out.println(nextPrime(11)); // -> 11

        // test 10
        //System.out.println(rightTriangle(3, 4, 5)); // ➞ true
        //System.out.println(rightTriangle(145, 105, 100)); // ➞ true
        //System.out.println(rightTriangle(70, 130, 110)); // ➞ false
        //System.out.println(rightTriangle(1, 1, 100)); // ➞ false
    }

    //1. Квадратное уравнение ax2 + bx + c = 0 имеет либо 0, либо 1, либо 2 различных
    //решения для действительных значений x. учитывая a, b и c, вы должны вернуть
    //число решений в уравнение.

    public static int solutions(double a, double b, double c){
        double d = (b * b) - 4 * a * c;
        if (d > 0){
            return 2;
        }
        else if (d == 0){
            return 1;
        }
        return 0;
    }

    //2. Напишите функцию, которая возвращает позицию второго вхождения " zip " в
    //строку, или -1, если оно не происходит по крайней мере дважды. Ваш код должен
    //быть достаточно общим, чтобы передать все возможные случаи, когда "zip" может
    //произойти в строке
    //Примечание:
    //Верхний регистр " Zip "- это не то же самое, что нижний регистр "zip".
    public static int findZip(String s){
        String newString = s.replaceFirst("zip", "---");
        //System.out.println(newString);
        return newString.indexOf("zip");
    }

    //3. Создайте функцию, которая проверяет, является ли целое число совершенным
    //числом или нет. Совершенное число - это число, которое можно записать как
    //сумму его множителей, исключая само число.
    //Например, 6-это идеальное число, так как 1 + 2 + 3 = 6, где 1, 2 и 3-Все коэффициенты 6.
    //Точно так же 28-это совершенное число, так как 1 + 2 + 4 + 7 + 14 = 28.
    public static boolean checkPerfect(int x){
        int sum = 0;
        for(int i = 1; i < x; i++){
            if(x % i == 0){
                sum += i;
            }
        }
        return (sum == x);
    }

    //4. Создайте функцию, которая принимает строку и возвращает новую строку с
    //заменой ее первого и последнего символов, за исключением трех условий:
    //– Если длина строки меньше двух, верните "несовместимо".".
    //– Если первый и последний символы совпадают, верните "два-это пара.".
    public static String flipEndChars(String str){
        String newStr = str;
        if (str.length() < 2){
            return "Incompatible.";
        }
        else if (str.charAt(0) == str.charAt(str.length()-1)){
            return "Two's a pair.";
        }
        else{
            char firstSymb = str.charAt(0);
            char lastSymb = str.charAt(str.length() - 1);

            StringBuilder stringBuilder = new StringBuilder(newStr);
            stringBuilder.setCharAt(0, lastSymb);
            stringBuilder.setCharAt(stringBuilder.length() - 1, firstSymb);
            return stringBuilder.toString();
        }
    }

    //5. Создайте функцию, которая определяет, является ли строка допустимым
    //шестнадцатеричным кодом.
    //Шестнадцатеричный код должен начинаться с фунтового ключа # и иметь длину ровно 6
    //символов. Каждый символ должен быть цифрой от 0-9 или буквенным символом от A-F.
    //все буквенные символы могут быть прописными или строчными.
    public static boolean isValidHexCode(String hexCode){
        if (hexCode.length() != 7){
            return false;
        }
        if (!hexCode.startsWith("#")){
            return false;
        }
        String str = hexCode.substring(1);
        //regular expression, where ^ - begin of str, $ - the end of str
        //[] - диапазон, a-fA-F0-9 - слияние диапазонов
        return str.matches("^[a-fA-F0-9]+$");
    }
    //6. Напишите функцию, которая возвращает true, если два массива имеют одинаковое
    //количество уникальных элементов, и false в противном случае.
    public static boolean same(int[] arr1, int[] arr2){
        //ArrayList -  список\динамический массив
        //Set - множества, LinkedHashSet- множество с элементами,
        // сохраненными в порядке добавления
        ArrayList<Integer> arrayList1 = new ArrayList<>(arr1.length);
        for (int i : arr1){
            arrayList1.add(i);
        }
        Set<Integer> set1 = new LinkedHashSet<>(arrayList1);

        ArrayList<Integer> arrayList2 = new ArrayList<>(arr2.length);
        for (int i : arr2){
            arrayList2.add(i);
        }
        Set<Integer> set2 = new LinkedHashSet<>(arrayList2);

        return set1.size() == set2.size();
    }

    //7. Число Капрекара-это положительное целое число, которое после возведения в
    //квадрат и разбиения на две лексикографические части равно сумме двух
    //полученных новых чисел:
    //  – Если количество цифр квадратного числа четное, то левая и правая части будут иметь
    //одинаковую длину.
    //  – Если количество цифр квадратного числа нечетно, то правая часть будет самой длинной
    //половиной, а левая-самой маленькой или равной нулю, если количество цифр равно 1.
    //  – Учитывая положительное целое число n, реализуйте функцию, которая возвращает true,
    //если это число Капрекара, и false, если это не так.
    public static boolean isKaprekar(long n){
        if (n == 0 || n == 1){
            return true;
        }
        if (n == 2 || n == 3){
            return false;
        }
        long newNum = n * n;
        System.out.println(newNum);
        System.out.println(String.valueOf(newNum));
        int len = String.valueOf(newNum).length();
        if (len % 2 == 0) {
            len /= 2;
        } else {
            len = (len / 2) + 1;
        }

        StringBuilder del = new StringBuilder("1");
        for (int i = 0; i < len; i++) {
            del.append("0");
        }
        int delim = Integer.parseInt(String.valueOf(del));

        long left = newNum / delim;
        long right = newNum % delim;
        // вторая часть != 0
        if (right == 0) {
            return false;
        }
        return left + right == n;
    }
    //8. Напишите функцию, которая возвращает самую длинную последовательность
//    последовательных нулей в двоичной строке.
    public static String longestZero(String number) {
        StringBuilder nulls = new StringBuilder();

        int length = 0;
        int maxLength = 0;
        char[] array = number.toCharArray();

        for (int i = 0; i < array.length; i++) {
            String current = String.valueOf(array[i]);
            if (current.equals("0")) {
                length++;
            }

            if (!current.equals("0")) {
                length = 0;
            }
            maxLength = Math.max(maxLength, length);
        }

        return "0".repeat(maxLength);

        //просплитить по 1
    }


    //9. Если задано целое число, создайте функцию, которая возвращает следующее
    //простое число. Если число простое, верните само число.
    public static int nextPrime(int number) {
        if (isSimple(number)) {
            return number;
        }
        while (!isSimple(number)) {
            number++;
        }
        return number;
    }

    private static boolean isSimple(int number) {
        if (number < 2)
            return false;
        double s = Math.sqrt(number);
        for (int i = 2; i <= s; i++) {
            if (number % i == 0)
                return false;
        }
        return true;
    }

    //10. Учитывая три числа, x, y и z, определите, являются ли они ребрами
    //прямоугольного треугольника.
    public static boolean rightTriangle(int x, int y, int z) {
        if (!(x + y > z) || !(x + z > y) || !(y + z > x)) {
            return false;
        }
        x = x * x;
        y = y * y;
        z = z * z;
        return x + y == z || x + z == y || y + z == x;
    }
}
