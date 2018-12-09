package tp.excepciones;

@SuppressWarnings("serial")
public class SamePosicionException extends Exception{
	public SamePosicionException(String causa){
			
			super(causa);
		}
}
