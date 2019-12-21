package homework1.OOP;

class Team {
    final Actions[] players = new Actions[4];

    Team(Human h1, Human h2, Cat c1, Robot r1)
    {
        players[0] = h1;
        players[1] = h2;
        players[2] = c1;
        players[3] = r1;
    }

    void showAll()
    {
        for (Actions player : players) player.getInfo();
    }

    void showResults()
    {
        System.out.println("Препятствия прошли: ");
        for (Actions player : players) {
            if (player.checkWin())
                player.getInfo();
        }
    }
}
