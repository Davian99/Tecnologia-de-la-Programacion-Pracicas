package tp.p2;

import java.util.Random;

public class ZombieManager {

	private int zombiesLeft;
	private float prob;

	//Constructora de ZombieManager. 
	public ZombieManager(Level level){
		switch(level){// Segun el nivel proporcionado tendra un numero de zombies y una frecuencia de aparicion. 
			case EASY: 
			this.zombiesLeft = 3;
			this.prob = 0.1f;
			break;
			case HARD: 
			this.zombiesLeft = 5;
			this.prob = 0.2f;
			break;
			default:   
			this.zombiesLeft = 10;
			this.prob = 0.3f;
		}
	}
	
	public void setZombies(int c) {
		this.zombiesLeft = c;
	}

	//Metodo que devuelve los zombies que faltan por salir.
	public int numZombies(){
		return this.zombiesLeft;
	}

	//Metodo que decrementa le numero de zombies que quedan por salir.
	
	//Metodo que comprueba si quedna zombies por salir y si la frecuencia lo permite.
	public boolean isZombieAdded(Random random){
		if (zombiesLeft > 0) {
			float valor = random.nextFloat();
			if(valor<= prob && numZombies() != 0){
				this.zombiesLeft--;
				return true;
			}
			else return false;
		}
		
		else return false;
	}
	
	
	
}