package tp.comand;

import java.io.*;

import tp.excepciones.ArrayOutException;
import tp.excepciones.FileContentsException;
import tp.excepciones.IllegalObjectPosition;
import tp.excepciones.NotAGameObjectException;
import tp.excepciones.ParseException;
import tp.excepciones.SamePosicionException;
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
	public Command parse(String argumentos) {
		
		String[] args = argumentos.split(" ");
		
		if(args.length != 2) 
			return null;
		
		if (!args[0].equalsIgnoreCase("l") && !args[0].equalsIgnoreCase("load"))
			return null;
		
		return new Load(args[1]);	
	}

	@Override
	public boolean execute(Game g) throws IOException, FileContentsException, ArrayOutException, SamePosicionException, NotAGameObjectException, IllegalObjectPosition, ParseException {
		File file = new File(fichero + extension);
		String linea;
		
		BufferedReader br = new BufferedReader(new FileReader(file));
		linea = br.readLine();
		if (!linea.equals(this.cabecera)) {
			br.close();
			throw new FileContentsException("El archivo no es un savegame de Plants Vs Zombies v3.0");		
		}
		g.load(br);
		br.close();
		return true;
	}

	@Override
	public String info() {
		return "[L]oad <nombre archivo>: carga la partida guardada en el archivo dado.";
	}
}
