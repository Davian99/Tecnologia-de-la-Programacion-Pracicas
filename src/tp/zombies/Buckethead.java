package tp.zombies;

import tp.p2.Game;

public class Buckethead extends Zombie{
	
	//Constructora de bucket zombie.
	public Buckethead (int x, int y, Game game) {
		super(x, y, 8, game, 4, "B");
	}
	
	public Buckethead() {
		// TODO Auto-generated constructor stub
	}

	//Metodo toString que printa el zombie.
	public String toString() {
		return "B";
	}
	
	public Zombie getZombie(String name, int x, int y, Game g){
		if (name.equalsIgnoreCase("b")) 
			return new Buckethead(x, y, g);
		return null;
	}
	
	public String infoZombie(){
		return "[B]ucketHead: Speed: 4 Harm: 1 Life: 8\n";
	}
}

