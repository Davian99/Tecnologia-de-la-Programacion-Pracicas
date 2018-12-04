package tp.comand;

import tp.p2.*;

public class None extends Command{

		public None(){
			
		}
		
		
	@Override
	public Command parse(String argumentos) {	
		
		String[] args = argumentos.split(" ");
		
		if(args.length != 1)
			return null;
		
		if (!args[0].equalsIgnoreCase("n") && !args[0].equalsIgnoreCase("none") && !args[0].equalsIgnoreCase(""))
			return null;
		
		return new None();
	}

	@Override
	public boolean execute(Game game) {
		game.update();
		return true;
	}
	
	public String info(){
		return "[N]one: Pasar el turno\n";
	}
}