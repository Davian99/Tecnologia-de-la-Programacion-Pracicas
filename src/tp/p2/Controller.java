package tp.p2;
import java.util.Random;
import java.util.Scanner;

import tp.comand.*;

public class Controller {
	private Game g;
	private Level l;
	private Scanner scan;
	private Random random;
	private Long semilla;
	private boolean finPartida = false;
	private boolean print = true;
	
	//Constructora del controller
	public Controller(String[] args) {
		this.l = Level.valueOf(args[0].toUpperCase());
		if (args.length == 2){
			System.out.println("Semilla: " + args[1]);
			this.random = new Random(Long.parseLong(args[1]));
			
		}
		else {
			System.out.println("Semilla: 0");
			this.random = new Random(0);
		}
		this.semilla = random.nextLong();
		g = new Game(l, random);
		scan = new Scanner(System.in);
		run(g);
	}
	
	//Metodo en el que el player da un comando si exite y lo ejecuta.
	//Tambien comprueba quien gana para acabar la partida y printa el tablero.
	public void run(Game game) {
		String entrada;
		//String[] entradaArray;
		CommandParser parseador= new CommandParser();
		//System.out.println(this.semilla);
		while(!finPartida) {
			//Imprime el tablero y el promt command
			if (print) 
				g.printTablero();
			System.out.print("Command > ");
			//Entrada usuario
			entrada = scan.nextLine();
			entrada = entrada.toLowerCase();
			//entradaArray = entrada.split(" ");
			Command comando = parseador.parse(entrada);
			if(comando != null)
				comando.execute(this, this.g);
			else {
				System.out.println("Commando no reconocible");
				this.print = false;
			}
			if (g.winner())
				finPartida = true;
		}
	}
	
	
	public void terminar() {
		this.finPartida = true;
	}
	
	public long getSemilla() {
		return this.semilla;
	}
	
	public void changeIfPrint(boolean a) {
		this.print = a;
	}

	public void printInfo() {
		CommandParser parseador = new CommandParser();
		String str = parseador.helpAll();
		System.out.println(str);
	}

	public void printListPlantas(){
		System.out.println(this.g.listInfoPlanta());
	}
	
	public void printListZombies(){
		System.out.println(this.g.listInfoZombie());
	}
	
}
