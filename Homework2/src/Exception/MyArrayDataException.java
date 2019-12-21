package Exception;

class MyArrayDataException extends Exception {

    MyArrayDataException(String[][] array, int i, int j)
    {
        System.err.println("Неверные данные в ячейке [" + i + "," + j + "]: " + array[i][j]);
    }
}
