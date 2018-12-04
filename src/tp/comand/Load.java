package tp.comand;

import java.io.*;
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
	public boolean execute(Game g) throws IOException{
		File file = new File(fichero + extension);
		String linea;
		
		BufferedReader br = new BufferedReader(new FileReader(file));
		if (br == null)
			throw new FileNotFoundException("El archivo introducido no existe.");
		linea = br.readLine();
		if (linea.equals(this.cabecera)) {
			try {
				g.load(br);
				return true;
			} catch (IOException e){
				//throw new IOException ("El archivo es corrupto.");
				e.printStackTrace();
			}
		}
		return false;
		
	}

	@Override
	public String info() {
		// TODO Auto-generated method stub
		return null;
	}
}
