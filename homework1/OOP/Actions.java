package homework1.OOP;

public interface Actions {
    void Run(Obstacles trace);
    void Jump(Obstacles wall);
    void getInfo();
    boolean checkWin();
}
