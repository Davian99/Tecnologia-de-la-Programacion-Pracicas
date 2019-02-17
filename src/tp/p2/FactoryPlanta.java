package tp.p2;
import tp.excepciones.CommandParseException;
import tp.plantas.*;

public class FactoryPlanta {
	Planta[] Factory = {
			new Sunflower(),
			new Peashooter(),
			new CherryBomb(),
			new WallNut(),
			new Threepeater(),
			new SplitPea(),
			new LaserBean(),
			new Smasher(),
			new SpikeWall()
	};

	public Planta parse(String planta, int x, int y, Game game) throws CommandParseException {
		Planta objetivo = null;
		int i = 0;
		while(objetivo == null && i < Factory.length) {
			objetivo = Factory[i].getPlanta(planta, x, y, game);
			i++;
		}
		if (objetivo == null)
			throw new CommandParseException("Unknown plant name: " + planta);
		else
			return objetivo;
	}
	
	public String listAll() {
		
		StringBuilder str = new StringBuilder();
		int i = 0; 
		
		while(i < Factory.length) {
			str.append(Factory[i].infoPlanta());
			i++;
		}
		return str.toString();
	}
}
