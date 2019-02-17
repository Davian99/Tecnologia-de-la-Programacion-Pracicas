package tp.comand;

import tp.excepciones.CommandParseException;

public class CommandParser {
	
	Command [] comandos={
			new Add(),
			new None(),
			new Reset(),
			new Help(),
			new Exit(),
			new List(),
			new Print(),
			new ZombieList(),
			new Catch(),
			new Save(),
			new Load(),
			new AddSuncoins(),
			new AddZombie(),
			new GoTo(),
			new Kill()
	};	
	

	public Command parse(String instruccion) throws CommandParseException{
		Command objetivo = null;
		int g = 0;
		String[] args = instruccion.split(" ");
		if(args.length == 0) 
			return new None();
		
		while(objetivo == null && g < comandos.length){
			objetivo = comandos[g].parse(instruccion);
			g++;
		}
		if (objetivo == null)
			throw new CommandParseException("Unknown command: " + instruccion + ", Use 'help' to see the available commands");
		else
			return objetivo;
	}
	
	
	public String helpAll() {
		
		StringBuilder str = new StringBuilder();
		int i = 0;
		
		while(i < comandos.length){
			str.append(comandos[i].info());
			i++;
		}
		return str.toString();
	}
}
