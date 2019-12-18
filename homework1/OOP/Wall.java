package homework1.OOP;

class Wall extends Obstacles {
    private int h;

    Wall(int h)
    {
        if(h<0)
            System.out.println("Incorrect");
        else
            this.h = h;
    }

    @Override
    int get() {
        return h;
    }
}
