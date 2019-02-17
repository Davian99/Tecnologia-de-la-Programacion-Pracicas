package tp.pasives;

import tp.p2.Game;
import tp.p2.PasiveGameObject;

public class Water extends PasiveGameObject{
	
	public Water (int x, int y, Game game) {
		super(x, y, game);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Boolean muere(int dmg) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String toStringFull() {
		return "~~~~~~~";
	}
	
	public String toString() {
		return "~";
	}

}
