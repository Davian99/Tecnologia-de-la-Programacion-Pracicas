package tp.comand;

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
			new Save()
	};	
	

	public Command parse(String instruccion){
		Command objetivo = null;
		int g = 0;
		while(objetivo == null && g < comandos.length){
			objetivo = comandos[g].parse(instruccion);
			g++;
		}
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
