package tp.plantas;

import tp.p2.Game;

//Dispara guisantes a los zombies causándoles daño.

public class Smasher extends Planta{
	
	private int damage;
	
	//Constructora de Smasher
	public Smasher(int x, int y, Game game) {
		super(x, y, 4, game, "SS");
		this.damage = 8;
		this.coste = 50;
	}
	
	public Smasher() {
		super();
	}
	
	//Metodo que hace el update de Smasher.
	public void update(){
		//Si hay un zombie y se muere con el ataque
		if (this.y < this.game.getTamY()-2 && this.game.atacarZombiePos(this.x, this.y+1, this.damage))
			this.y++;
		this.turno++;
	}
	
	//Metodo toString que printa el Smasher.	
	public String toString() {
		return "SS";
	}

	@Override
	public int ciclesToExecute() {
		return 0;
	}
	
	public Smasher getPlanta(String name, int x, int y, Game g) {
		if (name.equalsIgnoreCase("ss") || name.equalsIgnoreCase("smasher")) {
			return new Smasher(x, y, g);	
		}
		return null;
	}
	
	public String infoPlanta(){
		return "[S]ma[S]her: Cost: 50 suncoins Harm: 8 Life: 4\n";
	}

	@Override
	public String toStringFull() {
		// TODO Auto-generated method stub
		return "Smasher";
	}
}
