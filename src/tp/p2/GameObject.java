package tp.p2;

public abstract class GameObject {
	protected int x, y;
	protected Game game;
	
	public GameObject(int x, int y, Game game) {
		this.x = x;
		this.y = y;
		this.game = game;
	}
	
	public GameObject() {
		// TODO Auto-generated constructor stub
	}

	//Metodo geter para obtener la pos x.
	public int getX() {
		return this.x;
	}
	
	//Metodo geter para obtener la pos y.
	public int getY() {
		return this.y;
	}
	
	abstract public void update();
	abstract public Boolean muere(int dmg);
	
	public Boolean esta(int x, int y) {
		return (this.x == x && this.y == y);
	}
	
	
	
	
}
