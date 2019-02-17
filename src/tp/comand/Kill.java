package tp.comand;
import tp.excepciones.CommandExecuteException;
import tp.excepciones.CommandParseException;
import tp.p2.*;

public class Kill extends Command{
	private int x;
	private int y;
		
	public Kill(){
		
	}
	
	public Kill(int x, int y){
		this.x = x;
		this.y = y;
	}
		
	@Override
	public Command parse(String argumentos) throws NumberFormatException, CommandParseException {	
		
	// cambio a por X texto
		String[] args = argumentos.split(" ");
		
		if (!args[0].equalsIgnoreCase("k") && !args[0].equalsIgnoreCase("kill"))
			return null;
		
		if(args.length != 3) 
			throw new CommandParseException("Incorrect number of arguments for add command: [K]ill <x><y>");
		
		try{
			return new Kill(Integer.valueOf(args[1]), Integer.valueOf(args[2]));
		}catch(NumberFormatException e){
			throw new NumberFormatException("Invalid argument for kill command, number expected, introduced: "+args[1]+ " " +args[2]);
		}
	}

	@Override
	public boolean execute(Game g) throws CommandExecuteException {
		if (!g.casillaVacia(x, y)) {
			g.aniadirSunGame(g.eliminarObjeto(x, y));
			return true;
		}
		
		return false;
			
	}

	public String info(){
		return "[K]ill <x><y>: Elimina el objeto de la posicion dada\n";
	}
}
