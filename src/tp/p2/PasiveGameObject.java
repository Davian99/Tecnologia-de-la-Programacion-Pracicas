package tp.p2;

public abstract class PasiveGameObject extends GameObject{
	
	public PasiveGameObject(int x, int y, Game game) {
		super(x, y, game);
	}
	
	public PasiveGameObject() {
		
	}
	
	public abstract String toStringFull();
}
