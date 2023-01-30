package br.com.jprojects.placesmanagement.exception;

public class PlaceNotFoundException extends Exception {

	private static final long serialVersionUID = -8633232825039453087L;
	
	
	public PlaceNotFoundException() {
		super();
	}
	
	public PlaceNotFoundException(String message) {
		super(message);
	}
	
	public PlaceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
