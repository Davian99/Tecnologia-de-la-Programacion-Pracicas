package tp.plantas;

import tp.p2.Game;

//Dispara guisantes a los zombies causándoles daño.

public class LaserBean extends Planta{
	
	private int damage;
	
	//Constructora de LaserBean
	public LaserBean(int x, int y, Game game) {
		super(x, y, 3, game, "LB");
		this.damage = 1;
		this.coste = 75;
	}
	
	public LaserBean() {
		super();
	}
	
	//Metodo que hace el update de LaserBean.
	public void update(){
		this.game.atacarZombies(this.x, this.y, this.damage, 1, true);
		this.turno++;
	}
	
	//Metodo toString que printa el LaserBean.	
	public String toString() {
		return "LB";
	}

	@Override
	public int ciclesToExecute() {
		return 0;
	}
	
	public LaserBean getPlanta(String name, int x, int y, Game g) {
		if (name.equalsIgnoreCase("lb") || name.equalsIgnoreCase("laserbean")) {
			return new LaserBean(x, y, g);	
		}
		return null;
	}
	
	public String infoPlanta(){
		return "[L]aser[B]ean: Cost: 75 suncoins Harm: 1 to the entire row Life: 3\n";
	}

	@Override
	public String toStringFull() {
		// TODO Auto-generated method stub
		return "LaserBean";
	}
}
