package tp.pasives;

import tp.p2.Game;
import tp.p2.PasiveGameObject;

public class GraveStone extends PasiveGameObject{
	
	int hp;
	
	public GraveStone (int x, int y, Game game, int vida) {
		super(x, y, game);
		this.hp = vida;
	}

	@Override
	public void update() {
		// Nothing
		
	}

	@Override
	public Boolean muere(int dmg) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public int getHP() {
		return this.hp;
	}

	@Override
	public String toStringFull() {
		return "R.I.P";
	}
	
	public String toString() {
		return "†";
	}

}
