package tp.comand;

import tp.excepciones.CommandParseException;
import tp.p2.*;

public class None extends Command{

		public None(){
			
		}
		
		
	@Override
	public Command parse(String argumentos) throws CommandParseException {	
		
		String[] args = argumentos.split(" ");
		
		if (!args[0].equalsIgnoreCase("n") && !args[0].equalsIgnoreCase("none") && !args[0].equalsIgnoreCase(""))
			return null;
		
		if(args.length != 1)
			throw new CommandParseException("Incorrect number of arguments for none command: [N]one");
		
		return new None();
	}

	@Override
	public boolean execute(Game game) {
		try {
			game.update();
		} catch (CommandParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	
	public String info(){
		return "[N]one: Pasar el turno\n";
	}
}