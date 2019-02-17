package tp.zombies;

import java.util.Random;

import tp.p2.Game;
import tp.p2.GameObject;
import tp.plantas.Planta;


public class Mage extends Zombie{
	
	//Constructora de common zombie.
	public Mage(int x, int y, Game game) {
		super(x, y, 3, game, 1, "M");
	}
	
	public Mage() {
		// TODO Auto-generated constructor stub
	}
	
	public void update(){
		if (this.game.hayPlantas()) {
			boolean attacked = false;
			GameObject gc;
			Random r = this.game.getGenerador();
			int k = r.nextInt(this.game.objetosLista());
			gc = this.game.getObject(k);
			while(!attacked) {
				if(gc instanceof Planta) {
					attacked = true;
					if (gc.muere(this.damage)){
						this.game.eliminarObjeto(gc.getX(), gc.getY());
						this.vida++;
					}
				}
				k = r.nextInt(this.game.objetosLista());
				gc = this.game.getObject(k);
			}
		}
		
	}

	//Metodo toString que printa el zombie.
	public String toString() {
		return "M";
	}
	
	public Zombie getZombie(String name, int x, int y, Game g){
		if (name.equalsIgnoreCase("m"))	
			return new Mage(x, y, g);
		return null;
	}
	
	public String infoZombie(){
		return "[M]age: Speed: 0 Harm: 1 Life: 3 Attacks a random plant each cicle\n";
	}
}

