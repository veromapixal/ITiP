import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.Arrays;
import static java.lang.Integer.parseInt;

public class Task6 {
    public static void main(String[] args) {

        System.out.println("----1----");
        System.out.println(bell(1));
        System.out.println(bell(2));
        System.out.println(bell(3));

        System.out.println("----2----");
        System.out.println(translateWord("flag"));
        System.out.println(translateWord("Apple"));
        System.out.println(translateWord("button"));

        System.out.println(translateSentence("I like to eat honey waffles."));
        System.out.println(translateSentence("Do you think it is going to rain today?"));

        System.out.println("----3----");
        System.out.println(validColor("rgb(0,0,0)"));
        System.out.println(validColor("rgb(0,,0)"));
        System.out.println(validColor("rgb(255,256,255)"));
        System.out.println(validColor("rgba(0,0,0,0.123456789)"));

        System.out.println("----4----");
        System.out.println(stripUrlParams("https://edabit.com?a=1&b=2&a=2"));
        System.out.println(stripUrlParams("https://edabit.com?a=1&b=2&a=2", new String[]{"b"}));
        System.out.println(stripUrlParams("https://edabit.com", new String[]{"b"}));

        System.out.println("---5---");
        System.out.println(getHashTags("How the Avocado Became the Fruit of the Global Trade"));
        System.out.println(getHashTags("Why You Will Probably Pay More for Your Christmas Tree This Year"));
        System.out.println(getHashTags("Hey Parents, Surprise, Fruit Juice Is Not Fruit"));
        System.out.println(getHashTags("Visualizing Science"));

        System.out.println("---6---");
        System.out.println(ulam(4));
        System.out.println(ulam(9));
        System.out.println(ulam(206));

        System.out.println("---7---");
        System.out.println(longestNonrepeatingSubstring("abcabcbb"));
        System.out.println(longestNonrepeatingSubstring("aaaaaa"));
        System.out.println(longestNonrepeatingSubstring("abcde"));

        System.out.println("---8---");
        System.out.println(convertToRoman(2));
        System.out.println(convertToRoman(12));
        System.out.println(convertToRoman(16));

        System.out.println("---9---");
        System.out.println(formula("6 * 4 = 24"));
        System.out.println(formula("18 / 17 = 2"));
        System.out.println(formula("16 * 10 = 160 = 14 + 120"));

        System.out.println("---10---");
        System.out.println(palindromeDescendant(11211230));
        System.out.println(palindromeDescendant(13001120));
        System.out.println(palindromeDescendant(23336014));
        System.out.println(palindromeDescendant(11));
    }
    /**
     * 1 задание
     *
     * @return число Белла - количество способов которыми массив из n элементов может
     * быть разбит на непустые подмножества
     */
    private static int bell(int n) {
        int[][] bell = new int[n + 1][n + 1]; //двухмерный массив
        bell[0][0] = 1;//начальное значение
        for (int i = 1; i <= n; i++) {
            bell[i][0] = bell[i - 1][i - 1]; // добавление числа в массив
            for (int j = 1; j <= i; j++){
                bell[i][j] = bell[i - 1][j - 1] + bell[i][j - 1];
            }
        }
        return bell[n][0];
    }
    /**
     * 2 задание:
     *
     * @return перевод слова с английского на свинячий латинский по правилам:
     * – Если слово начинается с согласного, переместите первую букву (буквы) слова до
     * гласного до конца слова и добавьте «ay» в конец
     * - Если слово начинается с гласной, добавьте "yay" в конце слова
     */
    private static String translateWord(String word) {
        StringBuilder w = new StringBuilder(word);
        if (String.valueOf(w.charAt(0)).toLowerCase().matches("[aeiou]")) w.append("y"); // проверяем на наличие гласной
        while (!String.valueOf(w.charAt(0)).toLowerCase().matches("[aeiou]")){ // ищем следующую гласную
            w.append(Character.toLowerCase(w.charAt(0))).deleteCharAt(0); // переносим первый символ в конец
            if (Character.isUpperCase(word.charAt(0))) w.setCharAt(0, Character.toUpperCase(w.charAt(0)));
        }
        w.append("ay");
        return w.toString(); // выводим строку
    }
    /**
     * 2 задание:
     *
     * @return перевести на свинячий латинский предложения по правилам из translateWord
     */
    private static String translateSentence(String text) {
        StringBuilder t = new StringBuilder(); // Экземпляр StringBuilder
        for (String s : text.split(" ")) {
            if (s.substring(s.length() - 1).matches("[ ,.!?]")) // проверяем вхождение знаков
                t.append(translateWord(s.substring(0, s.length() - 1))).append(s.substring(s.length() - 1)).append(" "); // обновляем каждое слово прошлым методом
            else t.append(translateWord(s)).append(" "); // обновляем слово
        }
        return t.toString(); // выводим строку
    }
    /**
     * 3 задача
     *
     * @return true если строка соответствует формату:
     * rgb(r,g,b) (r,g,b принадлежат [0,255] и являются целочисленными)
     * или rgba(r,g,b,a) (a принадлежит [0,1])
     */
    private static boolean validColor(String s) {
        Integer r = null;
        Integer g = null;
        Integer b = null;
        Double a = null;
        // регулярка, которая принимает целочисленные числа и дроби
        Pattern pattern = Pattern.compile("\\d+\\.?\\d*");
        Matcher matcher = pattern.matcher(s);
        // по порядку находим значения r,g,b,a в строке
        while (matcher.find()) {
            try {
                if (r == null) {
                    r = Integer.parseInt(matcher.group());
                } else if (g == null) {
                    g = Integer.parseInt(matcher.group());
                } else if (b == null) {
                    b = Integer.parseInt(matcher.group());
                } else if (a == null) {
                    a = Double.parseDouble(matcher.group());
                }
            } catch (Exception e) {
                return false;
            }
        }
        // проверяем, что все значения соответствуют формату
        if (r == null || r > 255 || r < 0) {
            return false;
        }
        if (g == null || g > 255 || g < 0) {
            return false;
        }
        if (b == null || b > 255 || b < 0) {
            return false;
        }
        if (s.contains("rgba")) {
            return a != null && a <= 1 && a >= 0;
        } else return a == null && s.contains("rgb");
    }
    /**
     * 4 задание:
     * из url запроса убрать дублирующиеся параметры, и удалить указанные в paramsToDelete
     */
    public static String stripUrlParams(String url, String ... paramsToStrip){
        String str = "";
        if (!url.contains("?")) // проверка на наличие параметров
            return url;
        else {
            str = url.substring(url.indexOf("?") + 1);
            url = url.substring(0, url.indexOf("?") + 1);
        }
        char[] params = str.toCharArray(); // строку в массив
        StringBuilder print = new StringBuilder();
        for (char param : params) {
            if (Character.isLetter(param)) // проверка на букву
                if (!(print.toString().contains(String.valueOf(param)))) {
                    if (paramsToStrip.length > 0) {
                        for (String arg : paramsToStrip) {
                            if (!(arg.contains(String.valueOf(param))))
                                print.append(str.substring(str.lastIndexOf(param), str.lastIndexOf(param) + 3)).append("&");
                        }
                    }
                    else
                        print.append(str.substring(str.lastIndexOf(param), str.lastIndexOf(param) + 3)).append("&");
                }
        }
        return url + print.substring(0, print.length()-1);
    }
    /**
     * 5 задание
     *
     * @return 3 (или менее, если в строке меньше 3 слов) самых длинных слов из строки.
     * Если есть несколько слов одинаковой длины, берется самое первое из них, остальные игнорируются.
     */
    private static String getHashTags(String text){
        ArrayList<String> word = new ArrayList<>(); // список слов
        StringBuilder res = new StringBuilder();
        for (String s : text.split(" ")) word.add(s.replaceAll("\\W", "")); // удаление знаков
        word.sort((o1, o2) -> o2.length() - o1.length()); // сортировка слов
        for (int i = 0; i < word.size() & i < 3; i++) res.append("#").append(word.get(i)).append(" ");
        return res.toString();
    }
    /**
     * 6 задание:
     *
     * @return n-ное число в последовательности улама
     */
    private static int ulam(int num) {
        ArrayList<Integer> list = new ArrayList<>(); // задаём список
        // начальные значения
        list.add(1);
        list.add(2);
        // loop цикл для создания последовательности
        for (int i = 3; list.size() < num; i++) {
            int count = 0;
            for (int m = 0; m < list.size() - 1; m++) {
                for (int s = m + 1; s < list.size(); s++)
                    if (list.get(m) + list.get(s) == i) count++;
                if (count > 1) break;
            }
            if (count == 1) list.add(i);
        }
        return list.get(num - 1);
    }
    /**
     * 7 задание:
     *
     * @return самую длинную неповторяющуюся подстроку
     */
    static String longestNonrepeatingSubstring(String word) {
        String firstW = "";
        String lastW = "";
        for (int i = 0; i < word.length(); i++) {
            if (!firstW.contains(String.valueOf(word.charAt(i)))) {
                firstW += word.charAt(i);
                if (firstW.length() > lastW.length()) lastW = firstW;
            } else {
                word = word.substring(1); // убирает символ с перовй позиции
                firstW = "";
                i = -1;
            }
        }
        return lastW;
    }
    /**
     * 8 задание:
     *
     * @return арабскую цифру в римском представлении
     */
    private static String convertToRoman(int n) {
        /*
         * пытаемся вычесть из n числа из Roum начиная с самых больших.
         * Если вычесть удалось, значит можно добавить соответствующую последовательность
         * */
        Roum[] roums = Roum.values();
        StringBuilder builder = new StringBuilder();
        for (Roum roum : roums) {
            if (n - roum.arab >= 0) {
                builder.append(roum.name());
                n -= roum.arab;
            }
        }
        return builder.toString();
    }

    private enum Roum { // наследуем от особого класса Enum
        MMM(3000),
        MM(2000),
        M(1000),
        CM(900),
        DCCC(800),
        DCC(700),
        DC(600),
        D(500),
        CD(400),
        CCC(300),
        CC(200),
        C(100),
        XC(90),
        LXXX(80),
        LXX(70),
        LX(60),
        L(50),
        XL(40),
        XXX(30),
        XX(20),
        X(10),
        IX(9),
        VIII(8),
        VII(7),
        VI(6),
        V(5),
        IV(4),
        III(3),
        II(2),
        I(1);

        final int arab;

        Roum(int arab) {
            this.arab = arab;
        }
    }

    /**
     * 9 задание:
     *
     * @return true если данное уравнение верное
     * работает с уравениями типа A operator B = C operator D = X..., где A,B,C,D,X - константы
     * возможные операторы: +, -, *, /. Можно ставить несколько операторов подряд, например A * B * C.
     * При этом правильный порядок действия не соблюдается (в задании не сказано добавить расставку скобок)
     * Допустимо наличие отсутствие оператора, наличие нескольких частей уравнения.
     * Работает только с целыми положительными числами
     */
    private  static boolean formula(String formula) {
        formula += " = " + formula;
        String[] arr = formula.split(" ");
        int n = 0, n1 = 0, i = 0;
        while (i < 7) {
            if (arr[i + 1].equals("+")) n1 = parseInt(arr[i]) + parseInt(arr[i + 2]);
            else if (arr[i + 1].equals("-")) n1 = parseInt(arr[i]) - parseInt(arr[i + 2]);
            else if (arr[i + 1].equals("*")) n1 = parseInt(arr[i]) * parseInt(arr[i + 2]);
            else if (arr[i + 1].equals("/") & !arr[i + 2].equals("0"))
                n1 = parseInt(arr[i]) / parseInt(arr[i + 2]);
            n = n1;
            i += 6;
        }
        return parseInt(arr[4]) == n & parseInt(arr[4]) == n1;
    }
    /**
     * 10 задание:
     *
     * @return является ли данная строка палиндромом и можно ли путем сложения каждой соседней цифры создать палиндром
     */
    private static boolean palindromeDescendant(int num){
        boolean bul = false;
        StringBuffer num1 =new StringBuffer(num);
        StringBuffer num2 =new StringBuffer(num);
        if (num1.length()%2!=0)
            return false;
        else{
            while (!bul){
                if(num1 != num1.reverse()){
                    for(int i=0; i<num1.length();i+=2){
                        int a = Integer.parseInt(String.valueOf(num1.charAt(i)));
                        int b = Integer.parseInt(String.valueOf(num1.charAt(i+1)));
                        num2.append(a+b);
                    }
                }
                else
                    bul = true;
            }
        }
        return bul;
    }
}