package homework1.OOP;

class Trace extends Obstacles {
    private int distance;

    Trace(int distance)
    {
        if(distance<0)
            System.out.println("Incorrect");
        else
            this.distance=distance;
    }

    @Override
    int get() {
        return distance;
    }
}
