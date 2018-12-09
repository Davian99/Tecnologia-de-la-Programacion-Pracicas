package tp.comand;
import tp.excepciones.CommandParseException;
import tp.p2.*;

public class Exit extends Command{

	public Exit(){
		
	}
		
	@Override
	public Command parse(String argumentos) throws CommandParseException {	
		
		String[] args = argumentos.split(" ");
		
		if (!args[0].equalsIgnoreCase("e") && !args[0].equalsIgnoreCase("exit"))
			return null;
		
		if(args.length != 1) 
			throw new CommandParseException("Incorrect number of arguments for exit command: [E]xit");
		
		
		return new Exit();
	}

	@Override
	public boolean execute(Game g) {
		g.terminar();
		return false;
	}
	
	public String info(){
		return "[E]xit: Terminas la partida.\n";
	}
}