package tp.comand;

import java.io.*;

import tp.excepciones.CommandExecuteException;
import tp.excepciones.CommandParseException;
import tp.p2.*;

public class Save extends Command{
	private String fichero;
	private final String extension = ".dat";
	private final String cabecera = "Plants Vs Zombies v3.0";
	
	public Save(){
		
	}
	
	public Save(String fichero){
		this.fichero = fichero;
	}
		
	@Override
	public Command parse(String argumentos) throws CommandParseException {
		
		String[] args = argumentos.split(" ");
		
		if (!args[0].equalsIgnoreCase("s") && !args[0].equalsIgnoreCase("save"))
			return null;
		
		if(args.length != 2) 
			throw new CommandParseException("Incorrect number of arguments for Save command: [S]ave <nombre archivo>");
		
		return new Save(args[1]);	
	}

	@Override
	public boolean execute(Game g) throws CommandExecuteException {
		File file = new File(fichero + extension);
		try {
			file.createNewFile();
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			bw.write(cabecera + "\n");
			g.store(bw);
			bw.write("\n");
			bw.close();
			System.out.println("Game successfully saved in file " + fichero + ".dat");
		} catch (IOException e) {
			throw new CommandExecuteException ("Invalid filename: the filename contains invalid characters.");
		}
		return false;
	}
	

	@Override
	public String info() {
		return "[S]ave <nombre archivo>: guarda tu partida en el archivo dado.\n";
	}
}
