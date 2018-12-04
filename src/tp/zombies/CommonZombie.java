package tp.zombies;

import tp.p2.Game;

public class CommonZombie extends Zombie{
	
	//Constructora de common zombie.
	public CommonZombie(int x, int y, Game game) {
		super(x, y, 5, game, 2, "Z");
	}
	
	public CommonZombie() {
		// TODO Auto-generated constructor stub
	}

	//Metodo toString que printa el zombie.
	public String toString() {
		return "Z";
	}
	
	public Zombie getZombie(String name, int x, int y, Game g){
		if (name.equalsIgnoreCase("z"))	
			return new CommonZombie(x, y, g);
		return null;
	}
	
	public String infoZombie(){
		return "[Z]ombie: Speed: 2 Harm: 1 Life: 5\n";
	}
}

