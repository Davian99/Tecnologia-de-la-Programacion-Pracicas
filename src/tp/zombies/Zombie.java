package tp.zombies;
import tp.p2.*;
import tp.pasives.Water;

abstract public class Zombie extends ActiveGameObject{
	
	protected int damage;
	private int avanzar;
	
	//Constructora de Zombie
	public Zombie(int x, int y, int vida, Game game, int turnos, String symbol) {
		super(x, y, game, vida, 0, symbol);
		this.damage = 1;
		this.avanzar = turnos; 
	}
	
	public Zombie() {
		super();
	}

	//Metodo que hace el update de todos los zombies
	public void update(){
		this.turno++;	
		if(this.turno % this.getAvanzar() == 0 && this.turno != 0) {//Si le toca avanzar avanza.
			if(!this.avanza()) {
				this.vida -= game.atacarPlanta(this.x, this.y-1, this.damage);
				if(this.vida <= 0) {
					this.game.eliminarObjeto(this.x, this.y);
				}
			}		
		}
		else {
			this.vida -= game.atacarPlanta(this.x, this.y-1, this.damage);
			if(this.vida <= 0) {
				this.game.eliminarObjeto(this.x, this.y);
			}
		}
	}
	
	//Metodo auxiliar que comprueba si la casilla de la izquierda del zombie esta vacia para avanzar.
	public boolean avanza() {
		if (game.casillaVacia(this.x, this.y-1) && !(game.getPGO(this.x, this.y-1) instanceof Water)) {
			this.y--;
			return true;
		}
		return false;
	}
	
	public int ciclesToExecute() {
		return this.getAvanzar() - (this.turno % this.getAvanzar());
	}
	
	public abstract Zombie getZombie(String name, int x, int y, Game g);

	public abstract String infoZombie();

	public int getAvanzar() {
		return avanzar;
	}
	
	public int valor() {
		return 0;
	}
}
