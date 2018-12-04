package tp.p2;
import tp.zombies.*;

public class FactoryZombie {
	Zombie[] Factory = {
			new Runner(),
			new CommonZombie(),
			new Buckethead()
	};

	public Zombie parse(String planta, int x, int y, Game game) {
		Zombie objetivo = null;
		int i = 0;
		while(objetivo == null && i < Factory.length) {
			objetivo = Factory[i].getZombie(planta, x, y, game);
			i++;
		}
		return objetivo;
	}
	
	public String listAll() {
		
		StringBuilder str = new StringBuilder();
		int i = 0; 
		
		while(i < Factory.length) {
			str.append(Factory[i].infoZombie());
			i++;
		}
		return str.toString();
	}

}