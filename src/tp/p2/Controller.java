package tp.p2;
import java.io.*;
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
		g = new Game(l, random, Long.parseLong(args[1]));
		scan = new Scanner(System.in);
		run(g);
	}
	
	//Metodo en el que el player da un comando si exite y lo ejecuta.
	//Tambien comprueba quien gana para acabar la partida y printa el tablero.
	public void run(Game game) {
		String entrada;
		//String[] entradaArray;
		CommandParser parseador= new CommandParser();
		Command comando = null;
		//System.out.println(this.semilla);
		while(!g.winner()) {
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
					
			} catch (ParseException e) {
				this.print = false;
				System.out.println(e.getMessage());
			
			} catch(FileContentsException e) {
				System.out.println(e.getMessage());
			
			} catch (FileNotFoundException  e) {
				this.print = false;
				System.out.println(e.getMessage());
			
			} catch (IOException e) {
				System.out.println(e.getMessage());
			
			} catch (ArrayOutException e) {
				System.out.println(e.getMessage());
			
			} catch (SamePosicionException e) {
				System.out.println(e.getMessage());
			
			} catch (NotAGameObjectException e) {
				System.out.println(e.getMessage());
			
			} catch (IllegalObjectPosition e) {
				System.out.println(e.getMessage());
			}
		}
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
	
}
