package com.lab1;

public class Palindrome {
    public static void main(String[] args) {
        //создаем массив входных данных
        String[] s = {"java", "Palindrome", "madam", "racecar", "apple", "kayak", "song", "noon"};
        for (int i = 0; i < s.length; i++){
            //проверяем каждое слово из массива и выводим результат на экран
            String stringForCheck = s[i];
            System.out.println (s[i] + ": " + isPalindrome(stringForCheck));

        }

    }
    // перевернутая строка
    public static String reverseString(String s) {
        String newString = "";
        for (int i = s.length() - 1; i >= 0; i--){
            char sym = s.charAt(i);
            newString += sym;
        }
        return newString;
    }
    //проверяем, является ли палиндромом
    public static boolean isPalindrome (String s) {
        String reverse = reverseString(s);
        return reverse.equals(s);
    }
}
