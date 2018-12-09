package tp.plantas;

import tp.p2.*;

//Genera soles cada 2 turnos

public class Sunflower extends Planta{
	
	//Constructora de Sunflower.	
	public Sunflower(int x, int y, Game game) {
		super(x, y, 1, game, "S");
		this.coste = 20;
	}

	public Sunflower() {
		// TODO Auto-generated constructor stub
	}

	//Metodo que hace el update de Sunflower. Comprueba si puede o no generar soles.
	public void update() {
		if (this.turno % 2 == 0 && this.turno != 0)
			this.game.addSun(this.x, this.y);
		this.turno++;
	}
	
	//Metodo toString que printa el Sunflower.
	public String toString() {
		return "S";
	}

	@Override
	public int ciclesToExecute() {
		return this.turno % 2;
	}
	
	public Sunflower getPlanta(String name, int x, int y, Game g) {
		if ((name.equalsIgnoreCase("s") || name.equalsIgnoreCase("sunflower")) && g.suficientesSuncoins(this.coste)) 
				return new Sunflower(x, y, g);
		return null;
	}
	
	public String infoPlanta(){
		return "[S]unflower: Cost: 20 suncoins Harm: 0 Life: 1\n";
	}

	@Override
	public String toStringFull() {
		// TODO Auto-generated method stub
		return "Sunflower";
	}
}
