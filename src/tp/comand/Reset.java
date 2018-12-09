package tp.comand;
import tp.excepciones.CommandParseException;
import tp.p2.*;

public class Reset extends Command{

		public Reset(){
			
		}
		
		
	@Override
	public Command parse(String argumentos) throws CommandParseException {		
	
		String[] args = argumentos.split(" ");
		
		if (!args[0].equalsIgnoreCase("r") && !args[0].equalsIgnoreCase("reset"))
			return null;
		
		if(args.length != 1) 
			throw new CommandParseException("Incorrect number of arguments for reset command: [R]eset");
		
		return new Reset();
	}

	@Override
	public boolean execute(Game game) {
		game.reset();
		return true;
	}
	
	public String info(){
		return "[R]eset: Reinicia la partida\n";
	}
}