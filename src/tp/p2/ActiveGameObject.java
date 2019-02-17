package tp.p2;

import java.io.BufferedWriter;
import java.io.IOException;

public abstract class ActiveGameObject extends GameObject{
	protected int vida;
	protected int turno;
	protected String Symbol;
	
	public ActiveGameObject(int x, int y, Game game, int vida, int turno, String symbol) {
		super(x, y, game);
		this.vida = vida;
		this.turno = turno;
		this.Symbol = symbol;
	}
	
	public ActiveGameObject () {
		
	}
	
	public Boolean muere(int dmg) {
		this.vida -= dmg;
		return this.vida <= 0;
	}
	
	public int getHP() {
		return this.vida;
	}
	
	public void setTurno(int t) {
		this.turno = t;
	}
	
	abstract public void update();
	abstract public int ciclesToExecute();

	public void store(BufferedWriter bw) {
		try {
			bw.write(this.Symbol + ":" + this.vida + ":" + this.x + ":" + this.y + ":" + this.ciclesToExecute());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	abstract public int valor();
}
