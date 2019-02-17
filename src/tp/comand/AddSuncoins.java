package tp.comand;
import tp.excepciones.CommandParseException;
import tp.p2.*;

public class AddSuncoins extends Command{
	
	private int suncoins;

	public AddSuncoins(){
		
	}
		
	public AddSuncoins(int s) {
		this.suncoins = s;
	}
		
	@Override
	public Command parse(String argumentos) throws CommandParseException {		
	
		String[] args = argumentos.split(" ");
		
		if (!args[0].equalsIgnoreCase("as") && !args[0].equalsIgnoreCase("addsuncoins"))
			return null;
		
		if(args.length != 2) 
			throw new CommandParseException("Incorrect number of arguments for reset command: [A]dd[S]uncoins");
		
		try{
			return new AddSuncoins(Integer.valueOf(args[1]));
		}catch(NumberFormatException e){
			throw new NumberFormatException("Invalid argument for addsuncoins command, number expected, introduced: "+args[1]);
		}
	}

	@Override
	public boolean execute(Game game) {
		game.aniadirSunGame(this.suncoins);
		return true;
	}
	
	public String info(){
		return "[A]dd[S]uncoins <x>: Suma x suncoins\n";
	}
}