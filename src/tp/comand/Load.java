package tp.comand;

import java.io.*;

import tp.excepciones.CommandExecuteException;
import tp.excepciones.FileContentsException;
import tp.excepciones.CommandParseException;
import tp.p2.*;

public class Load extends Command{
	private String fichero;
	private final String extension = ".dat";
	private final String cabecera = "Plants Vs Zombies v3.0";
	
	public Load(){
		
	}
	
	public Load(String fichero){
		this.fichero = fichero;
	}
		
	@Override
	public Command parse(String argumentos) throws CommandParseException {
		
		String[] args = argumentos.split(" ");
		
		if (!args[0].equalsIgnoreCase("l") && !args[0].equalsIgnoreCase("load"))
			return null;
		
		if(args.length != 2) 
			throw new CommandParseException("Incorrect number of arguments for load command: [L]oad <nombre archivo>");
		
		return new Load(args[1]);	
	}

	@SuppressWarnings("resource")
	@Override
	public boolean execute(Game g) throws CommandExecuteException {
		File file = new File(fichero + extension);
		String linea;
		
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			throw new CommandExecuteException("File not found");
		}
		try {
			linea = br.readLine();
		} catch (IOException e) {
			throw new CommandExecuteException("Error reading file: " + this.fichero + this.extension);
		}
		try {
			if (!linea.equals(this.cabecera)) {
				br.close();
				throw new CommandExecuteException("The file is not a save game of Plants Vs Zombies v3.0");	
			}
					
			} catch (IOException | NullPointerException e) {
				throw new CommandExecuteException("Load failed: invalid file contents.");
			}
			
		try {
			g.load(br);
		} catch (FileContentsException e) {
			throw new CommandExecuteException(e.getMessage());
		}
		try {
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Game successfully loaded from file: " + this.fichero + this.extension);
		return true;
	}

	@Override
	public String info() {
		return "[L]oad <nombre archivo>: carga la partida guardada en el archivo dado.\n";
	}
}
