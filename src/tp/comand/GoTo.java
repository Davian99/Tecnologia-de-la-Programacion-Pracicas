package tp.comand;
import tp.excepciones.CommandExecuteException;
import tp.excepciones.CommandParseException;
import tp.p2.*;

public class GoTo extends Command{
	private int ciclo;
		
	public GoTo(){
		
	}
	
	public GoTo(int c){
		this.ciclo = c;
	}
		
	@Override
	public Command parse(String argumentos) throws NumberFormatException, CommandParseException {	
		
	// cambio a por X texto
		String[] args = argumentos.split(" ");
		
		if (!args[0].equalsIgnoreCase("g") && !args[0].equalsIgnoreCase("goto"))
			return null;
		
		if(args.length != 2) 
			throw new CommandParseException("Incorrect number of arguments for goto command: [G]oTo <cicle>");
		
		try{
			return new GoTo(Integer.valueOf(args[1]));
		} catch(NumberFormatException e){
			throw new NumberFormatException("Invalid argument for goto command, number expected, introduced: "+args[1]);
		}
	}

	@Override
	public boolean execute(Game g) throws CommandExecuteException {
		if(this.ciclo < g.turno()) {
			g.turno(ciclo);
			return true;
		} else {
			return false;
		}
			
	}

	public String info(){
		return "[G]oTo <cicle>: vuelve al turno que desees\n";
	}
}
