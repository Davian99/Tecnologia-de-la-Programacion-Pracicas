package tp.comand;
import tp.excepciones.CommandParseException;
import tp.p2.*;

public class Help extends Command{
		
	public Help(){
		
	}
		
		
	@Override
	public Command parse(String argumentos) throws CommandParseException {	
		
		String[] args = argumentos.split(" ");
		
		if (!args[0].equalsIgnoreCase("h") && !args[0].equalsIgnoreCase("help"))
			return null;
		
		if(args.length != 1)
			throw new CommandParseException("Incorrect number of arguments for help command: [H]elp");
		
		return new Help();
	}

	@Override
	public boolean execute(Game game) {
		this.printInfo();
		return false;
	}
	
	public String info(){
		return "[H]elp: ¿Tu que crees que hace don obvio?\n";
	}
	
	public void printInfo() {
		CommandParser parseador = new CommandParser();
		String str = parseador.helpAll();
		System.out.println(str);
	}
}