package com.homework5.Thread;

import java.util.Arrays;

public class Main {

    static final int SIZE = 10000000;
    static final int HALF = SIZE/2;

    public static void main(String[] args) {
		float[] array1 = new float[SIZE];
        float[] array2 = new float[SIZE];
		for(int i=0;i<SIZE;i++)
			array1[i] = 1;
		System.arraycopy(array1,0,array2,0,SIZE);
        notThread(array1);
		yesThread(array2);
        System.out.println(Arrays.equals(array1, array2));
    }

	private static void notThread(float[] arr){
		long a = System.currentTimeMillis();
		for(int i=0;i<SIZE;i++)
			arr[i]=(float)(arr[i]*Math.sin(0.2f+i/5)*Math.cos(0.2f+i/5)*Math.cos(0.4f+i/2));
		System.out.print("notThread: ");
		System.out.println(System.currentTimeMillis()-a);
	}

	private static void yesThread(float[] arr) {
		long a = System.currentTimeMillis();
    	float[] a1 = new float[HALF];
		float[] a2 = new float[HALF];
		System.arraycopy(arr,0,a1,0,HALF);
		System.arraycopy(arr,HALF,a2,0,HALF);
		Thread t1 = new Thread(()-> {
			for(int i=0;i<HALF;i++)
				a1[i]=(float)(a1[i]*Math.sin(0.2f+i/5)*Math.cos(0.2f+i/5)*Math.cos(0.4f+i/2));
            System.out.println("Я все!(1)");
		});
        t1.start();

		Thread t2 = new Thread(() -> {
			for(int i=0;i<HALF;i++)
				a2[i]=(float)(a2[i]*Math.sin(0.2f+(i+HALF)/5)*Math.cos(0.2f+(i+HALF)/5)*Math.cos(0.4f+(i+HALF)/2));
            System.out.println("Я все!(2)");
			});
        t2.start();

		try{
		    t1.join();
            System.out.println("Первый поток закончил");
		    t2.join();
            System.out.println("Второй поток закончил");
        }catch (InterruptedException e){
		    e.printStackTrace();
        }
		System.arraycopy(a1,0,arr,0,HALF);
		System.arraycopy(a2,0,arr,HALF,HALF);
		System.out.print("yesThread: ");
		System.out.println(System.currentTimeMillis()-a);
	}
}
