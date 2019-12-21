package homework1.OOP;

class Course {
    private final Obstacles[] O = new Obstacles[2];

    Course(Wall wall, Trace trace)
    {
        O[0] = wall;
        O[1] = trace;
    }

    void doIt(Team team)
    {
        for(int i = 0;i<team.players.length;i++)
        {
            team.players[i].Jump(O[0]);
            if(!team.players[i].checkWin())
                continue;
            team.players[i].Run(O[1]);
        }
    }
}
