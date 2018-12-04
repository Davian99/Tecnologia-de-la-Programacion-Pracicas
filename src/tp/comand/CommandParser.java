package tp.comand;

import tp.excepciones.ParseException;

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
			new Load()
	};	
	

	public Command parse(String instruccion) throws ParseException{
		Command objetivo = null;
		int g = 0;
		while(objetivo == null && g < comandos.length){
			objetivo = comandos[g].parse(instruccion);
			g++;
		}
		if (objetivo == null)
			throw new ParseException("No se ha encontrado el comando: " + instruccion);
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
