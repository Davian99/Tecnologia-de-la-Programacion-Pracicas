package tp.p2;

public class GameprinterObs {
	private int columnas;
	private int filas;
	private int soles;
	private int cicle;
	private int zombiesLeft;
	private String[][] tablero;
	final String space = " ";
	
	//Constructora del gamePrinter.
	public GameprinterObs(int f, int c, int soles) {
		this.columnas = c;
		this.filas = f;
		this.soles = soles;
	}

	//Metodo que rellena el tablero con lo que hay en cada casilla.
	public void crearTablero(Game game, int cicle, int soles, int zombiesLeft) {
		this.soles = soles;
		this.cicle = cicle;
		this.zombiesLeft = zombiesLeft;
		tablero = new String[filas][columnas];
		for (int i = 0; i < filas; ++i) {
			for (int j = 0; j < columnas; ++j) {
				tablero[i][j] = game.printCasilla(i, j);
			}
		}
	}
	
	//Metodo que hace el tablero.
	public String toString() {
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
	
	public String repeat(String text, int len) {
		String ret = "";
		for (int i = 0; i < len; ++i)
			ret += text;
		return ret;
	}
	
}
