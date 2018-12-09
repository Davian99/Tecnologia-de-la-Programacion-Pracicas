package tp.excepciones;

@SuppressWarnings("serial")
public class IllegalObjectPosition extends Exception{

	
	public IllegalObjectPosition(String causa){
		super(causa);
	}
}
