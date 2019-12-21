package Exception;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        String[][] array = new String[][]{{"1", "3", "6","5"}, {"6","5","8","9"}, {"4","7","4","5"},{"6","8","5","5"}};
        try {
            System.out.println("Sum = " + doSomething(array));
            print(array);
        }catch(MyArraySizeException e)
        {
            System.err.println(Arrays.toString(e.getStackTrace()));
            System.err.println("Некорректный размер массива!");
        }catch(MyArrayDataException e1)
        {
            System.err.println("ERROR!");
        }
        finally {
            System.out.println("Программа закончена");
        }
        }

    private static int doSomething(String[][] array) throws MyArraySizeException, MyArrayDataException {
        if(array.length!=MyArraySizeException.getSize())
            throw new MyArraySizeException();
        int[][] array2 = new int[array.length][array.length];
        int sum = 0;
        for(int i=0;i<array.length;i++)
        {
            for(int j=0;j<array.length;j++)
            {
                try {
                    array2[i][j]=0;
                    array2[i][j] = Integer.parseInt(array[i][j]);
                    sum+=array2[i][j];
                }catch(NumberFormatException e){
                    throw new MyArrayDataException(array,i,j);
                }
            }
        }
        return sum;
    }

    private static void print(String[][] array)
    {
        for (String[] strings : array) {
            for (int j = 0; j < array.length; j++) {
                System.out.print(strings[j] + " ");
            }
            System.out.println();
        }
    }
}
