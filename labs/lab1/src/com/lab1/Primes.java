package com.lab1;

public class Primes {

    public static void main(String[] args) {
	    for (int i = 2; i < 101; i++) {
            System.out.println(i + " " + isPrime(i));


        }
    }
//является ли число простым
    public static boolean isPrime(int n){
        for (int i = 2; i < n; i++) {
            if (n % i == 0){
                return false;
            }
        }
        return true;
    }
}
