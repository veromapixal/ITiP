import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Task5 {
    public static void main(String[] args) {

        //task1
        System.out.println(encrypt("Hello"));
        System.out.println(decrypt(72, 33, -73, 84, -12, -3, 13, -13, -68));
        System.out.println(encrypt("Sunshine"));

        //task2
        System.out.println(canMove("Rook", "A8", "H8"));
        System.out.println(canMove("Bishop", "A7", "G1"));
        System.out.println(canMove("Queen", "C4", "D6"));

        //task3
        System.out.println(canComplete("butl", "beautiful"));
        System.out.println(canComplete("butlz", "beautiful"));
        System.out.println(canComplete("tulb", "beautiful"));
        System.out.println(canComplete("bbutl", "beautiful"));

        //task4
        System.out.println(sumDigProd(16, 28));
        System.out.println(sumDigProd(0));
        System.out.println(sumDigProd(1, 2, 3, 4, 5, 6));

        //task5
        System.out.println(sameVowelGroup("toe", "ocelot", "maniac"));
        System.out.println(sameVowelGroup("many", "carriage", "emit", "apricot", "animal"));
        System.out.println(sameVowelGroup("hoops", "chuff", "bot", "bottom"));

        //task6
        System.out.println(validateCard(1234567890123456L));
        System.out.println(validateCard(1234567890123452L));
        System.out.println();

        //task7
        System.out.println(numToEng(0));
        System.out.println(numToEng(18));
        System.out.println(numToEng(126));
        System.out.println(numToEng(909));
        System.out.println(numToRus(0));
        System.out.println(numToRus(18));
        System.out.println(numToRus(126));
        System.out.println(numToRus(909));
        System.out.println();

        //task8
        System.out.println(getSha256Hash("password123"));
        System.out.println(getSha256Hash("Fluffy@home"));
        System.out.println(getSha256Hash("Hey dude!"));
        System.out.println();

        //task9
        System.out.println(correctTitle("jOn SnoW, kINg IN thE noRth."));
        System.out.println(correctTitle("sansa stark, lady of winterfell."));
        System.out.println(correctTitle("TYRION LANNISTER, HAND OF THE QUEEN."));
        System.out.println();

        //task10
        hexLattice(1);
        System.out.println();
        hexLattice(7);
        System.out.println();
        hexLattice(19);
        System.out.println();
        hexLattice(21);
        System.out.println();




    }

    //1. Создайте две функции, которые принимают строку и массив и возвращают
    //закодированное или декодированное сообщение.
    //Первая буква строки или первый элемент массива представляет собой символьный код
    //этой буквы. Следующие элементы-это различия между символами: например, A +3 --> C
    //или z -1 --> y.
    public static ArrayList encrypt(String message){
        ArrayList decodeMessage = new ArrayList();
        decodeMessage.add((int) message.charAt(0));
        for (int i = 0; (i+1) < message.length(); i++){
            int difference = 0;
            difference = message.charAt(i+1) - message.charAt(i);
            decodeMessage.add(difference);
        }
        return decodeMessage;
    }


    public static StringBuilder decrypt(int... code){
        StringBuilder decodeMessage = new StringBuilder(code.length);
        int symbol = code[0];
        decodeMessage.append((char) symbol);
        for (int i = 1; i < code.length; i++){
            //чтобы находить последующие, суммируем значения разности индексов символов,
            // добавляя по индексу символы в строку
            symbol += code[i];
            decodeMessage.append((char) symbol);
        }
        return decodeMessage;
    }

    //Создайте функцию, которая принимает имя шахматной фигуры, ее положение и
    //целевую позицию. Функция должна возвращать true, если фигура может двигаться
    //к цели, и false, если она не может этого сделать.
    //Возможные входные данные -
    //"пешка"-Pawn, "конь"-Knight, "слон"-Bishop, "Ладья"-Rook, "Ферзь"-Queen, "король"-King.
    public static boolean canMove(String figure, String start, String finish){
        boolean flag = false;
        if(figure == "Pawn") {
            //ест по диагонали на 1 вперед в обе стороны или ходит на 1 вперед
            if ((((int) start.charAt(0) + 1 == (int) finish.charAt(0)) && ((int) start.charAt(1) + 1 == (int) finish.charAt(1)))
                    || (((int) start.charAt(0) - 1 == (int) finish.charAt(0)) && ((int) start.charAt(1) + 1 == (int) finish.charAt(1)))
                    || (((int) start.charAt(0) == (int) finish.charAt(0)) && ((int) start.charAt(1) + 1 == (int) finish.charAt(0)))) {
                flag = true;
            }
        }

        if (figure == "Knight") {
            if ((Math.abs((int) start.charAt(0) - (int) finish.charAt(0)) == 1 && Math.abs((int) start.charAt(1) - (int) finish.charAt(1)) == 2)
                    || (Math.abs((int) start.charAt(0) - (int) finish.charAt(0)) == 2 && Math.abs((int) start.charAt(1) - (int) finish.charAt(1)) == 1)) {
                flag = true;
            }
        }
        if (figure == "Bishop") {
            //находится по диагонали от исходной клетки -> модули разности координат ==
            if (Math.abs((int) start.charAt(0) - (int) finish.charAt(0)) == Math.abs((int) start.charAt(1) - (int) finish.charAt(1))) {
                flag = true;
            }
        }
        if (figure == "Rook") {
            if (((int) start.charAt(0) == (int) finish.charAt(0)) || ((int) start.charAt(1) == (int) finish.charAt(1))) {
                flag = true;
            }
        }
        if (figure == "Queen") {
            if ((Math.abs((int) start.charAt(0) - (int) finish.charAt(0)) == Math.abs((int) start.charAt(1) - (int) finish.charAt(1)))
                    || (((int) start.charAt(0) == (int) finish.charAt(0)) || ((int) start.charAt(1) == (int) finish.charAt(1)))) {
                flag = true;
            }
        }
        if (figure == "King") {
            if ((Math.abs((int) start.charAt(0) - (int) finish.charAt(0)) == 1)
                    || (Math.abs((int) start.charAt(1) - (int) finish.charAt(1)) == 1)) {
                flag = true;
            }
        }
        return flag;
    }

    //3. Входная строка может быть завершена, если можно добавить дополнительные
    //буквы, и никакие буквы не должны быть удалены, чтобы соответствовать слову.
    //Кроме того, порядок букв во входной строке должен быть таким же, как и порядок
    //букв в последнем слове.
    //Создайте функцию, которая, учитывая входную строку, определяет, может ли слово быть
    //завершено.
    public static boolean canComplete(String subline, String line) {
        // Идём по строке, сравнивая поочерёдно с каждым символом субстроки.
        // Если длина субстроки равна количеству совпадений - true
        int count = 0;
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == subline.charAt(count)) {
                count++;
                if (subline.length() == count) {
                    return true;
                }
            }
        }
        return false;
    }

    //4. Создайте функцию, которая принимает числа в качестве аргументов, складывает их
    //вместе и возвращает произведение цифр до тех пор, пока ответ не станет длиной
    //всего в 1 цифру
    public static int sumDigProd(int ... numbers){
        int number = 0;
        for (int i : numbers) {
            number += i;
        }
        while (number > 9){
            int temp = 1;
            while (number > 0){
                temp *= number % 10;
                number /= 10;
            }
            number = temp;
        }
        return number;

    }

    //5. Напишите функцию, которая выбирает все слова, имеющие все те же гласные (в
    //любом порядке и / или количестве), что и первое слово, включая первое слово.
    public static ArrayList<String> sameVowelGroup(String ... words){
        //создаем список для ответа и множество для уникальных элементов
        ArrayList<String> answerList = new ArrayList<>();
        Set<Character> set = new HashSet<>();
        //работа с первым словом: добавляем в список с ответом,сохраняем все его гласные в set
        answerList.add(words[0]);
        String word1 = words[0].replaceAll("[^euioay]", "");
        for (int i = 0; i < word1.length(); i++){
            set.add(word1.charAt(i));
        }
        //также через регулярку убираем все согласные в словах и сравниваем с теми гласными, которые есть в сет
        for (int i = 1; i < words.length; i++){
            String word2 = words[i].replaceAll("[^euioay]", "");
            boolean flag = true;
            for (char letter: word2.toCharArray()){
                if (!(set.contains(letter))){
                    flag = false;
                    break;
                }
            }
            if (flag) {
                answerList.add(words[i]);
            }
        }
        return answerList;
    }

    //6. Создайте функцию, которая принимает число в качестве аргумента и возвращает
    //true, если это число является действительным номером кредитной карты, а в
    //противном случае-false.
    //Номера кредитных карт должны быть длиной от 14 до 19 цифр и проходить тест Луна,
    //описанный ниже:
    //– Удалите последнюю цифру (это"контрольная цифра").
    //– Переверните число.
    //– Удвойте значение каждой цифры в нечетных позициях. Если удвоенное значение имеет
    //более 1 цифры, сложите цифры вместе (например, 8 x 2 = 16 ➞ 1 + 6 = 7).
    //– Добавьте все цифры.
    //– Вычтите последнюю цифру суммы (из шага 4) из 10. Результат должен быть равен
    //контрольной цифре из Шага 1.
    public static boolean validateCard(long cardNumber) {
        // проверяем длину числа
        if (Math.pow(10, 14) > cardNumber && Math.pow(10, 20) <= cardNumber) {
            return false;
        }
        // выводим контрольную цифру из числа
        int checkDigit = (int) cardNumber % 10;
        cardNumber /= 10;
        int sum = 0;
        int temp;
        //идём по числу соблюдая условия.
        for (int i = 1; Math.pow(10, i) <= cardNumber; i++) {
            //Удвойте значение каждой цифры в нечетных позициях. Если удвоенное значение имеет более 1 цифры, сложите цифры вместе
            temp = (int) (cardNumber % Math.pow(10, i) / Math.pow(10, i - 1) * (i % 2 + 1));
            sum += temp % 10 + temp / 10;
        }
        //Вычесть последнюю цифру суммы и сравнить её с контрольной
        return 10 - sum % 10 == checkDigit;
    }

    //7. Напишите функцию, которая принимает положительное целое число от 0 до 999
    //включительно и возвращает строковое представление этого целого числа,
    //написанное на английском языке.
    public static StringBuilder numToEng(int number) {
        StringBuilder result = new StringBuilder();
        // задаём единицы и десятки
        String[] zeroToNineteen = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten",
                "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"};
        String[] decades = {"twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"};
        if (number / 100 > 0){
            result.append(String.format("%s hundred ", zeroToNineteen[number / 100]));
        }
        if (number % 100 >= 20) {
            result.append(String.format("%s ", decades[number % 100 / 10]));
        }
        if (number % 10 > 0 || number == 0) {
            result.append(zeroToNineteen[number % 20]);
        }
        return result;
    }

    public static StringBuilder numToRus(int number) {
        StringBuilder result = new StringBuilder();
        // задаём единицы и десятки
        String[] zeroToNineteenRus = {"ноль", "один", "два", "три", "четыре", "пять", "шесть", "семь", "восемь", "девять",
                "десять", "одинадцать", "двенадцать", "тринадцать", "четырнадцать", "пятнадцать", "шестнадцать",
                "семнадцать", "восемнадцать", "девятнадцать"};
        String[] decadesRus = {"двадцать", "тридцать", "сорок", "пятьдесят",
                "шестьдесят", "семьдесят", "восемьдесят", "девяносто"};
        String[] hundredsRus = {"сто", "двесте", "триста", "четыреста",
                "пятьсот", "шестьсот", "семьсот", "восемьсот", "девятьсот"};
        if (number / 100 > 0) {
            result.append(String.format("%s ", hundredsRus[number / 100 - 1]));
        }
        if (number % 100 >= 20) {
            result.append(String.format("%s ", decadesRus[number % 100 / 10 - 2]));
        }
        if (number % 10 > 0 || number == 0) {
            result.append(zeroToNineteenRus[number % 20]);
        }
        return result;
    }

    //8 Хеш-алгоритмы легко сделать одним способом, но по существу невозможно
    //сделать наоборот. Например, если вы хешируете что-то простое, например,
    //password123, это даст вам длинный код, уникальный для этого слова или фразы. В
    //идеале, нет способа сделать это в обратном порядке. Вы не можете взять хеш-код и
    //вернуться к слову или фразе, с которых вы начали.
    //Создайте функцию, которая возвращает безопасный хеш SHA-256 для данной строки.
    //Хеш должен быть отформатирован в виде шестнадцатеричной цифры.
    public static String getSha256Hash(String Line) {
        try {
            //Класс Java MessageDigest представляет криптографическую хеш-функцию
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            // создаём массив битов состоящих из хэшированного представления бита числа
            byte[] hash = digest.digest(Line.getBytes());
            StringBuilder hexString = new StringBuilder();
            // проходимся по массиву
            for (int i : hash) {
                //Собираем строковое представление целочисленного аргумента в виде целого числа
                String hex = Integer.toHexString(0xff & i);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    //9. Напишите функцию, которая принимает строку и возвращает строку с правильным
    //регистром для заголовков символов в серии "Игра престолов".
    //Слова and, the, of и in должны быть строчными. Все остальные слова должны иметь
    //первый символ в верхнем регистре, а остальные-в Нижнем.
    public static String correctTitle(String text) {
        StringBuilder answer = new StringBuilder(text.toLowerCase());
        //выставляем первый символ заглавным, если это буква
        answer.setCharAt(0, Character.toUpperCase(text.charAt(0)));
        //Меняем буквы, перед которыми пробел на заглавные и проверяем
        //через регулярку, не является ли предлогом\артиклем

        for (int i = 1; i < text.length(); i++)
            if (Character.isSpaceChar(text.charAt(i - 1))
                    && !answer.substring(i, i + 3).matches("of |in |the|and")) {
                answer.setCharAt(i, Character.toUpperCase(text.charAt(i)));
            }
            return answer.toString();
    }

    //10. Как указано в онлайн-энциклопедии целочисленных последовательностей:
    //Гексагональная решетка - это привычная двумерная решетка, в которой каждая точка
    //имеет 6 соседей.
    //Центрированное шестиугольное число - это центрированное фигурное число,
    //представляющее шестиугольник с точкой в центре и всеми другими точками,
    //окружающими центральную точку в шестиугольной решетке.
    //Напишите функцию, которая принимает целое число n и возвращает "недопустимое", если
    //n не является центрированным шестиугольным числом или его иллюстрацией в виде
    //многострочной прямоугольной строки в противном случае.
    public static void hexLattice(int number) {
        //вычисляем модификатор, присваиваем его number
        for (int i = 1; number >= 1; i++) {
            if (number == 1) {
                number = i;
                break;
            }
            number -= i * 6;
        }
        //если недопустимое число
        if (number < 0) {
            System.out.println("Invalid");
        }
        // вывод иллюстрации шестиугольника с учётом свободных полей
        int space = 1 + (number - 1) * 2;
        for (int i = space; i > 0; i--) {
            System.out.println(" ".repeat(space + 1 - number) + "o ".repeat(number));
            //дошли ли до макс строки
            if (number <= i) {
                number++;
            }
            else {
                number--;
            }
        }
    }
}