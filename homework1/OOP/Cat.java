package homework1.OOP;

public class Cat implements Actions {
    private boolean win;
    private final int max_distance, max_height;
    private final String name;
    Cat(String name, int max_distance, int max_height)
    {
        this.name = name;
        this.max_height=max_height;
        this.max_distance=max_distance;
    }

    @Override
    public final void Run(Obstacles trace) {
        win = trace.get() <= max_distance;
    }

    @Override
    public final void Jump(Obstacles wall) {
        win = wall.get() <= max_height;
    }
    @Override
    public final void getInfo(){
        System.out.println(name + ": Distance = " + max_distance + ", Height = " + max_height);
    }
    @Override
    public final boolean checkWin(){
        return win;
    }

}
