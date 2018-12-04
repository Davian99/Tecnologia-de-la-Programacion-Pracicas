package tp.plantas;

import tp.p2.Game;

//Explota muy fuerte

public class CherryBomb extends Planta {
	
	private int damage;

	//Constructora de CherryBomb
	public CherryBomb(int x, int y, Game game) {
		super(x, y, 2, game, "C");
		this.damage = 10;
		this.coste = 50;
	}
	
	public CherryBomb() {
		// TODO Auto-generated constructor stub
	}

	//Update de CherryBomb
	public void update() {
		if(this.turno == 2) {
			//Ataca en las cuatro direcciones
			game.atacarZombiePos(this.x-1, this.y, this.damage);
			game.atacarZombiePos(this.x+1, this.y, this.damage);
			game.atacarZombiePos(this.x-1, this.y-1, this.damage);
			game.atacarZombiePos(this.x-1, this.y+1, this.damage);
			this.game.eliminarObjeto(this.x, this.y);
		}
		this.turno++;
	}
	
	//Metodo toString que printa el cherry.
	public String toString() {
		return "C";
	}
	
	public int ciclesToExecute() {
		return 2 - this.turno;
	}
	
	public CherryBomb getPlanta(String name, int x, int y, Game g) {
		if((name.equals("c") || name.equals("cherrybomb")) && g.suficientesSuncoins(this.coste))
				return new CherryBomb(x, y, g);
		return null;
	}
	
	public String infoPlanta(){
		return "[C]herryBomb: Cost: 50 suncoins Harm: 10 Life: 2\n";
	}
}
