package com.geekbrains.Thread;

public class Main {
    private char currentLetter = 'A';
    //private Object mon = new Object();

    public static void main(String[] args) {

    Main obj = new Main();
	new Thread(obj::printA).start();
	new Thread(obj::printB).start();
	new Thread(obj::printC).start();
    }

    synchronized void printA() {
        for (int i = 0; i < 5; i++) {
            while (currentLetter != 'A') {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(currentLetter);
            currentLetter = 'B';
            notifyAll();
        }
    }

    synchronized void printB()  {
        for(int i = 0; i<5;i++) {
            while (currentLetter != 'B') {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(currentLetter);
            currentLetter = 'C';
            notifyAll();
        }
    }
    synchronized void printC() {
        for (int i = 0; i < 5; i++) {
            while (currentLetter != 'C') {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(currentLetter);
            currentLetter = 'A';
            notifyAll();
        }
    }
}
