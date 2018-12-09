package tp.excepciones;

@SuppressWarnings("serial")
public class CommandParseException extends Exception{
	
	public CommandParseException(String causa){
		
		super(causa);
	}
	
}
