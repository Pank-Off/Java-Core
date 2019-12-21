package Exception;

class MyArraySizeException extends Exception {
    private static final int size = 4;

    static int getSize(){return size;}
}
