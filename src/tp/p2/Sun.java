package tp.p2;

import java.io.BufferedWriter;
import java.io.IOException;

public class Sun extends PasiveGameObject{
	
	public Sun (int x, int y, Game game) {
		super(x, y, game);
	}
	
	public Sun getPosition(int x, int y) {
		if (this.x == x && this.y == y)
			return this;
		return null;
	}
	
	public String toString() {
		return("*");
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

	public void store(BufferedWriter bw) {
		try {
			bw.write(this.toString() + ":" + this.x + ":" + this.y);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
