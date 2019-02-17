package tp.plantas;

import tp.p2.Game;

//Ofrece alta proteccion

public class SpikeWall extends Planta {
	
	//Constructora de SpikeWall
	public SpikeWall(int x, int y, Game game) {
		super(x, y, 8, game, "SW");
		this.coste = 75;
	}
	
	public SpikeWall() {
		// TODO Auto-generated constructor stub
	}
	

	//Update de SpikeWall
	public void update() {
		//No hacer nada
	}
	
	//Metodo toString que printa el SpikeWall.
	public String toString() {
		return "SW";
	}

	@Override
	public int ciclesToExecute() {
		return 0;
	}
	
	public SpikeWall getPlanta(String name, int x, int y, Game g) {
		if ((name.equalsIgnoreCase("sw") || name.equalsIgnoreCase("SpikeWall")))
				return new SpikeWall(x, y, g);
		return null;
	}
	
	public String infoPlanta(){
		return "[S]pike[W]all: Cost: 75 suncoins Harm: 1 Life: 8\n";
	}

	@Override
	public String toStringFull() {
		// TODO Auto-generated method stub
		return "SpikeWall";
	}
}
