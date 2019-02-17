package tp.p2;
import java.util.Random;
import java.util.Scanner;

import tp.comand.*;
import tp.excepciones.*;

public class Controller {
	private Game g;
	private Level l;
	private Scanner scan;
	private Random random;
	private Long semilla;
	private boolean print = true;
	private Game cicles[];
	private int cicloActual = 0;
	private int max = 100;
	
	//Constructora del controller
	public Controller(String[] args) {
		
		
		if(args.length == 1 || args.length == 2){
			
			if(esLevel(args[0].toUpperCase())){
			
				this.l = Level.valueOf(args[0].toUpperCase());
				System.out.println("Level: " + this.l.toString());
				
					if (args.length == 2){
						
						if(esNumero(args[1])){
							
							System.out.println("Semilla: " + args[1]);
							this.random = new Random(Long.parseLong(args[1]));
							g = new Game(l, random, Long.parseLong(args[1]));
						}
						else throw new NumberFormatException("La semilla dada no es un numero.");
					}
					else{
						System.out.println("Semilla: 0");
						this.random = new Random(0);
						g = new Game(l, random, 0);
					}
				scan = new Scanner(System.in);
				run(g);	
			}
			else throw new IllegalArgumentException("No existe el nivel.");
		}
		else throw new IllegalArgumentException("Hay demasiados argumentos o no hay argumentos. Pruebe a poner solo el nivel y la semilla.");
	}
		
	private boolean esNumero (String numero){
		
		try{
			Long.parseLong(numero);
			return true;
		}catch(NumberFormatException e){
			return false;
		}
	}
		
	private boolean esLevel (String nivel){
			
			try{
				Level.valueOf(nivel);
				return true;
			}catch(IllegalArgumentException e){
				return false;
			}
		}
	
	//Metodo en el que el player da un comando si exite y lo ejecuta.
	//Tambien comprueba quien gana para acabar la partida y printa el tablero.
	public void run(Game game) {
		this.cicles = new Game[100];
		
		String entrada;
		
		CommandParser parseador= new CommandParser();
		Command comando = null;
		
		while(!g.winner()) {
			//Guarda el game actual
			if (this.g.turno() < this.cicloActual) {
				this.g = this.cicles[this.g.turno()];
			} 
			this.cicloActual = this.g.turno();
			if (this.cicloActual == this.max)
				resize();
			this.cicles[this.cicloActual] = new Game(this.g);
			//Imprime el tablero y el promt command
			if (print)
				g.printTablero();
			System.out.print("Command > ");
			//Entrada usuario
			entrada = scan.nextLine();
			entrada = entrada.toLowerCase();
			//entradaArray = entrada.split(" ");
			try {
				comando = parseador.parse(entrada);
				changeIfPrint(comando.execute(this.g));
				
			} catch (CommandParseException | NumberFormatException| CommandExecuteException e) {
				this.print = false;	
				System.out.println(e.getMessage());
			}
		}
	}
	
	private void resize() {
		Game[] aux = new Game[max*2];
		for (int i = 0; i < max; ++i) {
			aux[i] = this.cicles[i];
		}
		this.cicles = aux;
		this.max = max * 2;
	}

	public long getSemilla() {
		return this.semilla;
	}
	
	public void changeIfPrint(boolean a) {
		this.print = a;
	}

	public void printListZombies(){
		System.out.println(this.g.listInfoZombie());
	}

	public int getCicloActual() {
		return cicloActual;
	}

	public void setCicloActual(int cicloActual) {
		this.cicloActual = cicloActual;
	}
	
	public void setGame(Game game) {
		this.g = game;
	}
}