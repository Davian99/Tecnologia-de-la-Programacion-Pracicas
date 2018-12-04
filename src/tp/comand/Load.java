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
	public void execute(Controller control, Game g) {
		File file = new File(fichero + extension);
		String linea;
		//Save s = new Save(fichero + extension);
		//s.execute(control, g);
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			linea = br.readLine();
			if (linea.equals(this.cabecera)) {
				g.load(br);
				
			} else {
				System.out.println("El archivo no es parte de un savegame.");
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("El archivo introducido no existe.");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public String info() {
		// TODO Auto-generated method stub
		return null;
	}
}
