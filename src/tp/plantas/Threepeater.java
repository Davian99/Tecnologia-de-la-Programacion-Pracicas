package tp.plantas;

import tp.p2.Game;

//Dispara guisantes a los zombies causándoles daño.

public class Threepeater extends Planta{
	
	private int damage;
	
	//Constructora de PeashooterTriple
	public Threepeater(int x, int y, Game game) {
		super(x, y, 5, game, "T");
		this.damage = 1;
		this.coste = 100;
	}
	
	public Threepeater() {
		super();
	}
	
	//Metodo que hace el update de PeashooterTriple.
	public void update(){
		this.game.atacarZombies(this.x, this.y, this.damage, 1, false); //Misma fila
		this.game.atacarZombies(this.x-1, this.y, this.damage, 1, false); //Fila arriba
		this.game.atacarZombies(this.x+1, this.y, this.damage, 1, false); //Fila abajo
		this.turno++;
	}
	
	//Metodo toString que printa el PeashooterTriple.	
	public String toString() {
		return "T";
	}

	@Override
	public int ciclesToExecute() {
		return 0;
	}
	
	public Threepeater getPlanta(String name, int x, int y, Game g) {
		if (name.equalsIgnoreCase("t") || name.equalsIgnoreCase("Threepeater")) {
			return new Threepeater(x, y, g);	
		}
		return null;
	}
	
	public String infoPlanta(){
		return "[T]hreepeater: Cost: 100 suncoins Harm: 1 to 3 rows Life: 5\n";
	}

	@Override
	public String toStringFull() {
		return "Threepeater";
	}
}
