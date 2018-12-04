package tp.printer;

import tp.p2.*;

public class DebugPrinter extends BoardPrinter implements GamePrinter{

	private int num_obj;

	public DebugPrinter(int f, int c, int soles) {
		super(f, c, soles);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void encodeGame(Game game, int cicle, int soles, int zombiesLeft) {
		this.soles = soles;
		this.cicle = cicle;
		this.zombiesLeft = zombiesLeft;
		GameObject gc;
		int n = game.objetosLista();
		tablero = new String[1][n];
		for(num_obj = 0; num_obj < game.objetosLista(); ++num_obj) {
			gc = game.getObject(num_obj);
			if (game.sunEnCasilla(gc.getX(), gc.getY())) {
				Sun s = game.getSun(gc.getX(), gc.getY());
				tablero[0][num_obj] = this.encodeObjectSun((ActiveGameObject)gc, s);
			}
			else		
				tablero[0][num_obj] = this.encodeObject((ActiveGameObject)gc);
		}
	}
	
	private String encodeObject(ActiveGameObject obj) {
		StringBuilder str = new StringBuilder();
		str.append("|" + obj.toString() + " [");
		str.append("l:" + obj.getHP() + ",");
		str.append("x:" + obj.getX() + ",");
		str.append("y:" + obj.getY() + ",");
		str.append("t:" + obj.ciclesToExecute() + "]|");
		return str.toString();
	}
	
	private String encodeObjectSun(ActiveGameObject obj, Sun s) {
		StringBuilder str = new StringBuilder();
		str.append("|" + obj.toString() + s.toString() + "[");
		str.append("l:" + obj.getHP() + ",");
		str.append("x:" + obj.getX() + ",");
		str.append("y:" + obj.getY() + ",");
		str.append("t:" + obj.ciclesToExecute() + "]|");
		return str.toString();
	}
	
	@Override
	public String boardToString() {
		StringBuilder str = new StringBuilder();
		str.append("\nNumber of cycles: " + this.cicle + " \n");
		str.append("Sun coins: " + this.soles + " \n");
		str.append("Remaining Zombies: " + this.zombiesLeft + " \n");
		str.append(this.repeat("-", num_obj * 21) + "\n");
		for (int i = 0; i < this.num_obj; ++i) {
			str.append(this.tablero[0][i]);
		}
		str.append("\n" + this.repeat("-", num_obj * 21) + "\n");
		
		return str.toString();
	}
	
	private String repeat(String text, int len) {
		String ret = "";
		for (int i = 0; i < len; ++i)
			ret += text;
		return ret;
	}
}
