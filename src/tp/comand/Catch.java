package tp.comand;

import tp.p2.Controller;
import tp.p2.Game;

public class Catch extends Command{
	private int x, y;
	
	public Catch() {
		
	}
	
	public Catch (int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public Command parse(String argumentos) {	
		
		// cambio a por X texto
			String[] args = argumentos.split(" ");
			
			if(args.length != 3) 
				return null;
			
			if (!args[0].equalsIgnoreCase("c") && !args[0].equalsIgnoreCase("catch"))
				return null;
			
			return new Catch(Integer.valueOf(args[1]), Integer.valueOf(args[2]));
		}

	@Override
	public void execute(Controller control, Game g) {
		if (!g.catchSunEstate()) {
			g.cogerSun(this.x, this.y);
			control.changeIfPrint(true);
		}
		else
			System.out.println("Ya has cogido un Sol este turno!\n");
		//control.changeIfPrint(false);
	}
	
	public String info(){
		return "[C]atch <x><y>: Recoge un sol de la posicion dada\n";
	}
}
