package tp.excepciones;

@SuppressWarnings("serial")
public class NotAGameObjectException extends Exception{

	
	public NotAGameObjectException(String causa){
		super(causa);
	}
}
