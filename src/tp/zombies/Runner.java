package tp.zombies;

import tp.p2.Game;

public class Runner extends Zombie{
	
	//Constructora de runner zombie.
	public Runner (int x, int y, Game game) {
		super(x, y, 2, game, 1, "R");
	}
	
	public Runner() {
		// TODO Auto-generated constructor stub
	}

	//Metodo toString que printa el zombie.	
	public String toString() {
		return "R";
	}
	
	public Zombie getZombie(String name, int x, int y, Game g){
		if (name.equalsIgnoreCase("r"))
			return new Runner(x, y, g);
		return null;
	}
	
	public String infoZombie(){
		return "[R]unner: Speed: 1 Harm: 1 Life: 2\n";
	}
}


