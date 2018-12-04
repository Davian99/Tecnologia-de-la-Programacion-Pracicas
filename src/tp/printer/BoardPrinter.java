package tp.printer;

public abstract class BoardPrinter {
	protected int columnas;
	protected int filas;
	protected int soles;
	protected int cicle;
	protected int zombiesLeft;
	protected String[][] tablero;
	final String space = " ";
	
	//Constructora del gamePrinter.
	public BoardPrinter(int f, int c, int soles) {
		this.columnas = c;
		this.filas = f;
		this.soles = soles;
	}
	
	//Metodo que hace el tablero.
	abstract public String boardToString();
}
