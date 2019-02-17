package tp.plantas;

import tp.p2.Game;

//Dispara guisantes a los zombies causándoles daño.

public class SplitPea extends Planta{
	
	private int damage;
	
	//Constructora de SplitPea
	public SplitPea(int x, int y, Game game) {
		super(x, y, 5, game, "SP");
		this.damage = 1;
		this.coste = 75;
	}
	
	public SplitPea() {
		super();
	}
	
	//Metodo que hace el update de SplitPea.
	public void update(){
		this.game.atacarZombies(this.x, this.y, this.damage, 1, false); //Ataca adelante
		this.game.atacarZombies(this.x, this.y, this.damage, -1, false); //Ataca adelante
		this.turno++;
	}
	
	//Metodo toString que printa el SplitPea.	
	public String toString() {
		return "SP";
	}

	@Override
	public int ciclesToExecute() {
		return 0;
	}
	
	public SplitPea getPlanta(String name, int x, int y, Game g) {
		if (name.equalsIgnoreCase("sp") || name.equalsIgnoreCase("splitpea")) {
			return new SplitPea(x, y, g);	
		}
		return null;
	}
	
	public String infoPlanta(){
		return "[S]plit[P]ea: Cost: 75 suncoins Harm: 1 in both directions Life: 5\n";
	}

	@Override
	public String toStringFull() {
		// TODO Auto-generated method stub
		return "SplitPea";
	}
}
