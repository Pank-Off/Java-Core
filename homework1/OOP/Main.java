package homework1.OOP;

public class Main {

    public static void main(String[] args) {

    	Course c = new Course(new Wall(4), new Trace(400));
		Team team = new Team(new Human("Kirill", 1000, 2), new Human("Anton", 2000, 1), new Cat("Barsik", 500, 5), new Robot("R2D2", 10000, 10));
		team.showAll();
		c.doIt(team);
		team.showResults();
	}
}
