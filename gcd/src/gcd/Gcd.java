/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gcd;

import java.util.*;

/**
 *
 * @author timuntersberger
 */
public class Gcd {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int[][] testNumberSets = {
            {32,16},
            {672,3360},
            {145314,168468},
            {500,100},
            {500,10},
            {500,5},
            {36960,330960}
        };
        for (int i = 0; i < testNumberSets.length; i++) {
            int[] numbers = testNumberSets[i];
            System.out.printf("1. number: %d\n2. number: %d\n", numbers[0], numbers[1]);
            int primeResult = gcdPrimeFactors(numbers[0], numbers[1]);
            int eucledianResult = gcdEucledian(numbers[0], numbers[1]);
            System.out.printf("Prime: %d\nEuclidean: %d\n\n", primeResult, eucledianResult);
        }
    }

    public static int gcdEucledian(int a, int b) {
        return b == 0 || a == 0 ? a + b : gcdEucledian(b, a % b);
    }
    public static int gcdPrimeFactors(int a, int b) {
        ArrayList<Integer> commonPrimes = new ArrayList();
        ArrayList<Integer> aPrimes = getPrimes(a);
        ArrayList<Integer> bPrimes = getPrimes(b);
        for (int i = 0; i < aPrimes.size(); i++) {
            int prime = aPrimes.get(i);
            if (bPrimes.contains(prime)) {
                commonPrimes.add(prime);
                bPrimes.remove(bPrimes.indexOf(prime));
            }
        }
        return commonPrimes.stream().reduce(1, (x, y) -> x * y);
    }

    public static ArrayList<Integer> getPrimes(int n) {
        ArrayList<Integer> primes = new ArrayList();
        int prime = 2;
        while (n > 1) {
            if (n % prime == 0) {
                primes.add(prime);
                n /= prime;
            } else {
                prime = nextPrime(prime);
            }
        }
        return primes;
    }

    public static int nextPrime(int n) {
        do {
            n++;
        } while (!isPrime(n));
        return n;
    }

    public static boolean isPrime(int n) {
        if (n % 2 == 0) {
            return false;
        }
        for (int i = 3; i * i <= n; i += 2) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
