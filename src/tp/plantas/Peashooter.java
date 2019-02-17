package tp.plantas;

import tp.p2.Game;

//Dispara guisantes a los zombies causándoles daño.

public class Peashooter extends Planta{
	
	private int damage;
	
	//Constructora de Peashooter
	public Peashooter(int x, int y, Game game) {
		super(x, y, 5, game, "P");
		this.damage = 1;
		this.coste = 50;
	}
	
	public Peashooter() {
		super();
	}
	
	//Metodo que hace el update de peashooter.
	public void update(){
		this.game.atacarZombies(this.x, this.y, this.damage, 1, false);
		this.turno++;
	}
	
	//Metodo toString que printa el peashooter.	
	public String toString() {
		return "P";
	}

	@Override
	public int ciclesToExecute() {
		return 0;
	}
	
	public Peashooter getPlanta(String name, int x, int y, Game g) {
		if (name.equalsIgnoreCase("p") || name.equalsIgnoreCase("peashooter")) {
			return new Peashooter(x, y, g);	
		}
		return null;
	}
	
	public String infoPlanta(){
		return "[P]eashooter: Cost: 50 suncoins Harm: 1 Life: 5\n";
	}

	@Override
	public String toStringFull() {
		// TODO Auto-generated method stub
		return "Peashooter";
	}
}
