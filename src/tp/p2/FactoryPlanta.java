package tp.p2;
import tp.plantas.*;

public class FactoryPlanta {
	Planta[] Factory = {
			new Sunflower(),
			new Peashooter(),
			new CherryBomb(),
			new WallNut()
	};

	public Planta parse(String planta, int x, int y, Game game) {
		Planta objetivo = null;
		int i = 0;
		while(objetivo == null && i < Factory.length) {
			objetivo = Factory[i].getPlanta(planta, x, y, game);
			i++;
		}
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
