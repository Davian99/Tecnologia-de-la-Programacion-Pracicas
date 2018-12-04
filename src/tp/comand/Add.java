package tp.comand;
import tp.p2.*;

public class Add extends Command{
	private int x;
	private int y;
	private String planta;
		
	public Add(){
		
	}
	
	public Add(String planta, int x, int y){
		this.x = x;
		this.y = y;
		this.planta = planta;
	}
		
	@Override
	public Command parse(String argumentos) {	
		
	// cambio a por X texto
		String[] args = argumentos.split(" ");
		
		if(args.length != 4) 
			return null;
		
		if (!args[0].equalsIgnoreCase("a") && !args[0].equalsIgnoreCase("add"))
			return null;
		
		return new Add(args[1], Integer.valueOf(args[2]), Integer.valueOf(args[3]));
	}

	@Override
	public void execute(Controller control, Game g) {
		if (g.addPlanta(planta, x, y)) {
			g.update();
			control.changeIfPrint(true);
		}
		else
			control.changeIfPrint(false);
			
	}

	public String info(){
		return "[A]dd <plant><x><y>: Añade una planta en la posicion dada\n";
	}
}
