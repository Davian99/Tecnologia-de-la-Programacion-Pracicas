package tp.printer;

import tp.p2.ActiveGameObject;
import tp.p2.Game;
import tp.p2.GameObject;
import tp.p2.Sun;

public class ReleasePrinter extends BoardPrinter implements GamePrinter{

	public ReleasePrinter(int f, int c, int soles) {
		super(f, c, soles);
		tablero = new String[this.filas][this.columnas];
	}

	@Override
	public void encodeGame(Game game, int cicle, int soles, int zombiesLeft) {
		this.soles = soles;
		this.cicle = cicle;
		this.zombiesLeft = zombiesLeft;
		for (int i = 0; i < filas; ++i) {
			for (int j = 0; j < columnas; ++j) {
				GameObject gc = game.getter(i, j);
				if (gc != null) {
					if (game.sunEnCasilla(i, j)) {
						Sun s = game.getSun(i, j);
						tablero[i][j] = this.encodeObjectSun((ActiveGameObject) gc, s);
					}
						
					else
						tablero[i][j] = this.encodeObject((ActiveGameObject) gc);
				}
					
				else {
					if (game.sunEnCasilla(i, j)) {
						Sun s = game.getSun(i, j);
						tablero[i][j] = "   " + s.toString() + "   ";
					}
					else
						tablero[i][j] = "       ";
				}
					
			}
		}	
	}
	
	private String encodeObject(ActiveGameObject obj) {
		StringBuilder str = new StringBuilder();
		str.append(" " + obj.toString() + " [" + obj.getHP() + "] ");
		return str.toString();
	}
	
	private String encodeObjectSun(ActiveGameObject obj, Sun s) {
		StringBuilder str = new StringBuilder();
		str.append(obj.toString() + " [" + obj.getHP() + "] " + s.toString());
		return str.toString();
	}
	
	public String boardToString() {
		int cellsize = 7;
		String vDelimiter = "|";
		String hDelimiter = "-";
		
		StringBuilder str = new StringBuilder();
		str.append("\nNumber of cycles: " + this.cicle + " \n");
		str.append("Sun coins: " + this.soles + " \n");
		str.append("Remaining Zombies: " + this.zombiesLeft + " \n");
		str.append(" " + repeat(hDelimiter, cellsize * columnas + columnas-1) + " \n");
		for(int i=0; i<filas; i++) {
			str.append(vDelimiter);
			for (int j=0; j<columnas; j++) {
				str.append(tablero[i][j] + vDelimiter);
			}
			str.append("\n " + repeat(hDelimiter, cellsize * columnas + columnas-1) + " \n");
		}
		return str.toString();
	}
	
	private String repeat(String text, int len) {
		String ret = "";
		for (int i = 0; i < len; ++i)
			ret += text;
		return ret;
	}
}
