package tp.plantas;
import tp.p2.*;

public abstract class Planta extends ActiveGameObject{
	
	protected int coste;

	public Planta(int x, int y, int vida, Game game, String symbol) {
		super(x, y, game, vida, 0, symbol);
	}
	
	public Planta() {
		super();
	}

	public int getCoste() {
		return this.coste;
	}
	
	public abstract Planta getPlanta(String name, int x, int y, Game g);

	public abstract String infoPlanta();
	
	public abstract String toStringFull();
}
