import java.util.*;

public class Task4 {

    public static void main(String[] args) {
//        test1
        textEditor(10, 7, "hello my name is Bessie and this is my essay");


//        test 2
        System.out.println(split("((()))"));
        System.out.println(split("((()))(())()()(()())"));
        System.out.println(split("((())())(()(()()))"));


//        test 3
        System.out.println(toCamelCase("hello_edabit"));
        System.out.println(toCamelCase("is_modal_open"));
        System.out.println(toSnakeCase("helloEdabit"));
        System.out.println(toSnakeCase("toSnakeCase"));


//        test 4
        System.out.println(overTime(9, 17, 30, 1.5F));
        System.out.println(overTime(16, 18, 30, 1.8F));
        System.out.println(overTime(13.25F, 15, 30, 1.5F));
        System.out.println(overTime(18, 20, 30, 2));


//        test 5
        System.out.println(BMI("205 pounds", "73 inches"));
        System.out.println(BMI("55 kilos", "1.65 meters"));
        System.out.println(BMI("154 pounds", "2 meters"));


//        test 6
        System.out.println(bugger(39));
        System.out.println(bugger(999));
        System.out.println(bugger(4));


//        test 7
        System.out.println(toStarShorthand("abbvvccc"));
        System.out.println(toStarShorthand("77777geff"));
        System.out.println(toStarShorthand("abc"));
        System.out.println(toStarShorthand(""));
        System.out.println(toStarShorthand("sadsaabbcc"));


//        test 8
        System.out.println(doesRhyme("Sam I am!", "Green eggs and ham."));
        System.out.println(doesRhyme("Sam I am!", "Green eggs and HAM."));
        System.out.println(doesRhyme("You are off to the races", "a splendid day."));
        System.out.println(doesRhyme("and frequently do?", "you gotta move."));
        System.out.println(doesRhyme("and frequently dooooe?", "you gotta move."));


//        test 9
        System.out.println(trouble(451999277, 41177722899L));
        System.out.println(trouble(1222345, 12345));
        System.out.println(trouble(666789, 12345667));
        System.out.println(trouble(33789, 12345337));
        System.out.println(trouble(1234555, 1231319191955L));


//        test 10
        System.out.println(countUniqueBooks("AZYWABBCATTTA", 'A'));
        System.out.println(countUniqueBooks("$AA$BBCATT$C$$B$", '$'));
        System.out.println(countUniqueBooks("ZZABCDEF", 'Z'));



    }

//    1. Бесси работает над сочинением для своего класса писателей. Поскольку ее почерк
//    довольно плох, она решает напечатать эссе с помощью текстового процессора.
//    Эссе содержит N слов (1≤N≤100), разделенных пробелами. Каждое слово имеет
//    длину от 1 до 15 символов включительно и состоит только из прописных или
//    строчных букв. Согласно инструкции к заданию, эссе должно быть
//    отформатировано очень специфическим образом: каждая строка должна содержать
//    не более K (1≤K≤80) символов, не считая пробелов. К счастью, текстовый
//    процессор Бесси может справиться с этим требованием, используя следующую
//    стратегию:
//            – Если Бесси набирает Слово, и это слово может поместиться в текущей строке, поместите
//    его в эту строку. В противном случае поместите слово на следующую строку и
//    продолжайте добавлять к этой строке. Конечно, последовательные слова в одной строке
//    все равно должны быть разделены одним пробелом. В конце любой строки не должно
//    быть места.
//            – К сожалению, текстовый процессор Бесси только что сломался. Пожалуйста,
//    помогите ей правильно оформить свое эссе!
//    Вам будут даны n, k и строка

    public static void  textEditor(int n, int k, String line) {
        String[] arrayWords = line.split(" ");
        int i = 0;
        String finalString = "";
        String forCheck = "";
        while (i < n) {
            while (i < n && forCheck.length() + arrayWords[i].length() <= k) {
                finalString += arrayWords[i] + " ";
                forCheck += arrayWords[i];

                i += 1;
            }
            System.out.println(finalString);
            finalString = "";
            forCheck = "";
        }
    }

    //Напишите функцию, которая группирует строку в кластер скобок.
    // Каждый кластер должен быть сбалансирован.
    public static ArrayList<String> split(String line) {
        int opened = 0;
        String buffer = "";
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 0; i < line.length(); i++) {
            char currentCharacter = line.charAt(i);
            if (currentCharacter == '(') {
                opened++;
            } else if (currentCharacter == ')') {
                opened--;
            }
            buffer = buffer.concat(Character.toString(currentCharacter));
            if (opened == 0 && currentCharacter == ')') {
                arrayList.add(buffer);
                buffer = "";
            }
        }
        return arrayList;
    }

    // 3. Создайте две функции toCamelCase () и toSnakeCase (), каждая из которых берет
    // одну строку и преобразует ее либо в camelCase, либо в snake_case.
    public static String toCamelCase(String line) {
        String[] concatLine = line.split("_");
        StringBuilder finaLine = new StringBuilder(concatLine[0]);

        for (int i = 1; i < concatLine.length; i++) {
            String currentWord = concatLine[i].toLowerCase();

            StringBuilder addWord = new StringBuilder();
            addWord.append(currentWord.substring(0, 1).toUpperCase());
            addWord.append(currentWord.substring(1));

            finaLine.append(addWord);
        }
        return String.valueOf(finaLine);
    }


    public static String toSnakeCase(String line) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < line.length(); i++) {

            char ch = line.charAt(i);

            if (Character.isUpperCase(ch)) {
                result.append('_');
                result.append(Character.toLowerCase(ch));
            } else {
                result.append(ch);
            }
        }
        return result.toString();
    }

//4. Напишите функцию, которая вычисляет сверхурочную работу и оплату, связанную
//    с сверхурочной работой.
//    Работа с 9 до 5: обычные часы работы
//    После 5 вечера это сверхурочная работа
//    Ваша функция получает массив с 4 значениями:
//            – Начало рабочего дня, в десятичном формате, (24-часовая дневная нотация)
//            – Конец рабочего дня. (Тот же формат)
//            – Почасовая ставка
//– Множитель сверхурочных работ
//    Ваша функция должна возвращать:
//    $ + заработанные в тот день (округлены до ближайшей сотой)
    public static String overTime(float...arr) {
        float startDay = arr[0];
        float endDay = arr[1];
        float hourlyRate = arr[2];
        float overtimeCoefficient = arr[3];
        float salary = 0;
        StringBuilder finalLine = new StringBuilder();

        if (startDay - (int) startDay != 0) {
            salary += ((int) startDay - startDay) * hourlyRate;
//            startDay++;
        }

        for (float i = startDay; i < endDay; i++) {
            if (i >= 17) {
                salary += hourlyRate * overtimeCoefficient;
            } else {
                salary += hourlyRate;
            }
        }
        String finalResult = String.format("%.2f", salary);
        finalLine.append("$").append(finalResult);
        return finalLine.toString();
    }


//    Индекс массы тела (ИМТ) определяется путем измерения вашего веса в
//    килограммах и деления на квадрат вашего роста в метрах. Категории ИМТ таковы:
//    Недостаточный вес: <18,5
//    Нормальный вес: 18.5-24.9
//    Избыточный вес: 25 и более
//    Создайте функцию, которая будет принимать вес и рост (в килограммах, фунтах,
//             метрах или дюймах) и возвращать ИМТ и связанную с ним категорию. Округлите
//    ИМТ до ближайшей десятой.
    public static String BMI(String weight, String height) {
        String[] weightArray = weight.split(" ");
        String[] heightArray = height.split(" ");
        float weightNumber;
        float heightNumber;
        float BMI;
        if (weightArray[1].equals("pounds")) {
            weightNumber = Float.parseFloat(weightArray[0]) * 0.453592F;
        } else {
            weightNumber = Float.parseFloat(weightArray[0]);
        }

        if (heightArray[1].equals("inches")) {
            heightNumber = Float.parseFloat(heightArray[0]) * 0.0254F;
        } else {
            heightNumber = Float.parseFloat(heightArray[0]);
        }

        BMI = weightNumber / (heightNumber * heightNumber);
        String BMIString = String.format("%.1f", BMI);


        if (BMI < 18.5) {
            return BMIString + " Underweight";
        } else if (BMI >= 18.5 && BMI < 25) {
            return BMIString + " Normal weight";
        }
        return BMIString + " Overweight";
    }

//    6. Создайте функцию, которая принимает число и возвращает его мультипликативное
//    постоянство, которое представляет собой количество раз, которое вы должны
//    умножать цифры в num, пока не достигнете одной цифры.
    public static int bugger(int number) {
        int newNumber = number;
        int count = 0;
        while (newNumber > 9) {

            int tempNumber = 1;
            while (newNumber > 0) {
                tempNumber *= newNumber % 10;
                newNumber /= 10;
            }
            newNumber = tempNumber;
            count++;
        }
        return count;
    }


// 7.   Напишите функцию, которая преобразует строку в звездную стенографию. Если
//    символ повторяется n раз, преобразуйте его в символ*n.
    public static String toStarShorthand(String line) {
        if (line.isEmpty()) {
            return "";
        }
        String changeLine = line;
        char prevEl = line.charAt(0);
        boolean flag = false;
        int count = 1;
        char var = prevEl;
        for (int i = 0; i < line.length() - 1; i++) {
            if (line.charAt(i) == line.charAt(i + 1)) {
                count++;
                var = line.charAt(i);
            //проверка(заходило когда не надо)
            } else if (count >= 2) {
                flag = true;
            }
            // проверка когда сходятся последние символы
            if (i == line.length() - 2 && count >= 2) {
                flag = true;
            }
            if (count >= 2 && flag) {
                String forRepeat = String.valueOf(var);
                String someCharacters = forRepeat.repeat(count);
                String magicLine = forRepeat + "*" + count;
                changeLine = changeLine.replaceFirst(someCharacters, magicLine);
                count = 1;
                flag = false;
            }
        }
        return changeLine;
    }

//    8. Создайте функцию, которая возвращает true, если две строки рифмуются, и false в
//    противном случае. Для целей этого упражнения две строки рифмуются, если
//    последнее слово из каждого предложения содержит одни и те же гласные.

    public static boolean doesRhyme(String firstLine, String secondLine) {

        firstLine = firstLine;
        secondLine = secondLine;
        String[] mas1 = firstLine.split(" ");
        String[] mas2 = secondLine.split(" ");
        HashSet<Character> hashSet1 = new HashSet<>();
        HashSet<Character> hashSet2 = new HashSet<>();

        String lastWord1 = mas1[mas1.length - 1].toLowerCase();
        String lastWord2 = mas2[mas2.length - 1].toLowerCase();

        for (char letter : lastWord1.toCharArray()) {
            if ("qeyioa".indexOf(letter) != -1) {
                hashSet1.add(letter);
            }
        }

        for (char letter : lastWord2.toCharArray()) {
            if ("qeyioa".indexOf(letter) != -1) {
                hashSet2.add(letter);
            }
        }
        return hashSet1.containsAll(hashSet2);
    }


//    9. Создайте функцию, которая принимает два целых числа и возвращает true, если
//    число повторяется три раза подряд в любом месте в num1 и то же самое число
//    повторяется два раза подряд в num2.
    public static boolean trouble(long firstNumber, long secondNumber) {
        HashMap<Character, Integer> hashMap1 = new HashMap<>();
        HashMap<Character, Integer> hashMap2 = new HashMap<>();

        char[] arrayFirstNumber = Long.toString(firstNumber).toCharArray();
        char[] arraySecondNumber = Long.toString(secondNumber).toCharArray();


        for (char digit : arrayFirstNumber) {
            // читаем текущее значение счетчика
            Integer count = hashMap1.get(digit);
            // если слово раньше не встречалось, то его нет в словаре
            if (count == null)
                count = 0;
            // увеличиваем значение счетчика
            count++;
            // записываем новое значение
            hashMap1.put(digit, count);
        }

        for (char digit : arraySecondNumber) {
            Integer count = hashMap2.get(digit);
            if (count == null)
                count = 0;
            count++;
            hashMap2.put(digit, count);
        }

        Character key = 0;
        for (Map.Entry<Character, Integer> characterIntegerEntry : hashMap1.entrySet()) {
            if (characterIntegerEntry.getValue() == 3) {
                key = characterIntegerEntry.getKey();
            }
        }

        return key == 0 ? false : hashMap2.get(key) == 2;
    }

//    10. Предположим, что пара одинаковых символов служит концами книги для всех
//    символов между ними. Напишите функцию, которая возвращает общее количество
//    уникальных символов (книг, так сказать) между всеми парами концов книги.
//    Эта функция будет выглядеть следующим образом:
//    countUniqueBooks("stringSequence", "bookEnd")
//    Пример:
//    countUniqueBooks("AZYWABBCATTTA", 'A') ➞ 4
//     1st bookend group: "AZYWA" : 3 unique books: "Z", "Y", "W"
//     2nd bookend group: "ATTTA": 1 unique book: "T"
//    countUniqueBooks("$AA$BBCATT$C$$B$", '$') ➞ 3
//    countUniqueBooks("ZZABCDEF", 'Z') ➞ 0

    public static int countUniqueBooks(String line, char marker) {
        List<Character> list = new ArrayList<>();
        boolean flag = false;
        for (int i = 0; i < line.length(); i++) {
            char currentCharacter = line.charAt(i);

            if (currentCharacter == marker) {
                if (flag) {
                    flag = false;
                } else {
                    flag = true;
                }
            } else if (flag) {
                if (!list.contains(currentCharacter)) {
                    list.add(currentCharacter);
                }
            }
        }
        return list.size();
    }

}
