package tp.p2;
import tp.excepciones.CommandParseException;
import tp.zombies.*;

public class FactoryZombie {
	Zombie[] Factory = {
			new Runner(),
			new CommonZombie(),
			new Buckethead(),
			new Acrobat(),
			new HijoePuta(),
			new Mage(),
			new Cannibal()
	};

	public Zombie parse(String zombie, int x, int y, Game game) throws CommandParseException {
		Zombie objetivo = null;
		int i = 0;
		while(objetivo == null && i < Factory.length) {
			objetivo = Factory[i].getZombie(zombie, x, y, game);
			i++;
		}
		if (objetivo == null)
			throw new CommandParseException("No se ha encontrado el zombie: " + zombie);
		else
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