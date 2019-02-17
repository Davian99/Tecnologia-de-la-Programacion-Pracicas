package tp.zombies;

import tp.p2.Game;
import tp.p2.GameObject;
import tp.plantas.Planta;


public class Acrobat extends Zombie{
	
	//Constructora de common zombie.
	public Acrobat(int x, int y, Game game) {
		super(x, y, 5, game, 2, "A");
	}
	
	public Acrobat() {
		// TODO Auto-generated constructor stub
	}
	
	public void update(){
		this.turno++;	
		if(this.turno % this.getAvanzar() == 0 && this.turno != 0) {//Si le toca avanzar avanza.
			if(!this.avanza()) {
				GameObject gc = this.game.getter(this.x, this.y-1);
				if (gc instanceof Planta && this.y-2 >= 0 && this.game.casillaVacia(this.x, this.y-2)) {
					this.y -= 2;
				}
			}		
		}
	}

	//Metodo toString que printa el zombie.
	public String toString() {
		return "A";
	}
	
	public Zombie getZombie(String name, int x, int y, Game g){
		if (name.equalsIgnoreCase("a"))	
			return new Acrobat(x, y, g);
		return null;
	}
	
	public String infoZombie(){
		return "[A]crobat: Speed: 2 Harm: 0 Life: 5 Can jump\n";
	}
}

