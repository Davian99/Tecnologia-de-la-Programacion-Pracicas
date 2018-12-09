package tp.comand;

import tp.p2.Game;
import tp.excepciones.*;

public class Catch extends Command{
	private int x, y;
	
	public Catch() {
		
	}
	
	public Catch (int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public Command parse(String argumentos) throws NumberFormatException, CommandParseException {	
		
		// cambio a por X texto
			String[] args = argumentos.split(" ");
			
			if (!args[0].equalsIgnoreCase("c") && !args[0].equalsIgnoreCase("catch"))
				return null;
			
			if(args.length != 3) 
				throw new CommandParseException("Incorrect number of arguments for add command: [C]atch <x><y>");
			
			try{
				return new Catch(Integer.valueOf(args[1]), Integer.valueOf(args[2]));
			}catch(NumberFormatException e){
				throw new NumberFormatException("Invalid argument for catch command, number expected, , introduced: "+args[1]+ " " +args[2]);
			}
		}

	@Override
	public boolean execute(Game g) {
		if (!g.catchSunEstate()) {
			g.cogerSun(this.x, this.y);
			return true;
		}
		else
			System.out.println("Ya has cogido un Sol este turno!\n");
			return false;
	}
	
	public String info(){
		return "[C]atch <x><y>: Recoge un sol de la posicion dada\n";
	}
}
