package tp.zombies;

import java.util.Random;

import tp.p2.Game;

public class HijoePuta extends Zombie{
	
	//Constructora de common zombie.
	public HijoePuta(int x, int y, Game game) {
		super(x, y, 5, game, 2, "A");
	}
	
	public HijoePuta() {
		// TODO Auto-generated constructor stub
	}
	
	public void update(){
		this.turno++;	
		if(this.turno % this.getAvanzar() == 0 && this.turno != 0) {//Si le toca avanzar avanza.
			if(!this.avanza()) {
				Random k = this.game.getGenerador();
				int i = k.nextInt(2);
				if (i == 0) {
					if(this.game.casillaVacia(this.x-1, this.y)) {
						this.x--;
					} else {
						if(this.game.casillaVacia(this.x+1, this.y)) {
							this.x++;
						}
					}
				} else {
					if(this.game.casillaVacia(this.x+1, this.y)) {
						this.x++;
					} else {
						if(this.game.casillaVacia(this.x-1, this.y)) {
							this.x--;
						}
					}
				}
			}		
		}
	}

	//Metodo toString que printa el zombie.
	public String toString() {
		return "HP";
	}
	
	public Zombie getZombie(String name, int x, int y, Game g){
		if (name.equalsIgnoreCase("hp"))	
			return new HijoePuta(x, y, g);
		return null;
	}
	
	public String infoZombie(){
		return "[H]ijoe[P]uta: Speed: 2 Harm: 1 Life: 4 Change row if possible\n";
	}
}

