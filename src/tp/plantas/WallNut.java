package tp.plantas;

import tp.p2.Game;

//Ofrece alta proteccion

public class WallNut extends Planta {
	
	//Constructora de WallNut
	public WallNut(int x, int y, Game game) {
		super(x, y, 10, game, "W");
		this.coste = 50;
	}
	
	public WallNut() {
		// TODO Auto-generated constructor stub
	}

	//Update de WallNut
	public void update() {
		//No hacer nada
	}
	
	//Metodo toString que printa el wallnut.
	public String toString() {
		return "W";
	}

	@Override
	public int ciclesToExecute() {
		return 0;
	}
	
	public WallNut getPlanta(String name, int x, int y, Game g) {
		if ((name.equalsIgnoreCase("w") || name.equalsIgnoreCase("wallnut")) && g.suficientesSuncoins(this.coste))
				return new WallNut(x, y, g);
		return null;
	}
	
	public String infoPlanta(){
		return "[W]allnut: Cost: 50 suncoins Harm: 0 Life: 10\n";
	}
}
