package tp.comand;
import tp.excepciones.CommandParseException;
import tp.p2.*;

public class List extends Command{

	public List(){
		
	}
	
	
	public Command parse(String argumentos) throws CommandParseException {	
		
		String[] args = argumentos.split(" ");
		
		if (!args[0].equalsIgnoreCase("l") && !args[0].equalsIgnoreCase("list"))
			return null;
		
		if(args.length != 1)
			throw new CommandParseException("Incorrect number of arguments for list command: [L]ist");
		
		return new List();
	}
	
	public boolean execute(Game game) {
		
		System.out.println(game.listInfoPlanta());
		return false;
	}
	
	public String info(){
		return "[L]ist: Muestra el nombre de las plantas disponibles y su coste\n";
	}
	
	
}
