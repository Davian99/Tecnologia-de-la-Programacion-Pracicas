package tp.zombies;

import tp.p2.Game;
import tp.p2.GameObject;
import tp.plantas.Planta;


public class Cannibal extends Zombie{
	
	//Constructora de common zombie.
	public Cannibal(int x, int y, Game game) {
		super(x, y, 10, game, 3, "C");
	}
	
	public Cannibal() {
		// TODO Auto-generated constructor stub
	}
	
	public void update(){
		GameObject gc;
		this.turno++;	
		if(this.turno % this.getAvanzar() == 0 && this.turno != 0) {//Si le toca avanzar avanza.
			if(!this.avanza()) {
				gc = this.game.getter(this.x, this.y-1);
				if (gc instanceof Zombie) {
					this.vida += ((Zombie) gc).getHP() / 2;
					this.game.eliminarObjeto(x, y-1);
					this.avanza();
				}else if (gc instanceof Planta) {
					if (gc.muere(2)) {
						this.game.eliminarObjeto(x, y-1);
					}
				}
			}		
		} else {
			gc = this.game.getter(this.x, this.y-1);
			if (gc instanceof Planta) {
				if (gc.muere(2)) {
					this.game.eliminarObjeto(x, y-1);
				}
			}
		}
	}

	//Metodo toString que printa el zombie.
	public String toString() {
		return "C";
	}
	
	public Zombie getZombie(String name, int x, int y, Game g){
		if (name.equalsIgnoreCase("c"))	
			return new Cannibal(x, y, g);
		return null;
	}
	
	public String infoZombie(){
		return "[C]annibal: Speed: 3 Harm: 2 Life: 10 Can eats other zombies and regain health\n";
	}
}

