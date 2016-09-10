package exception;

public class RestServiceException extends Exception {
	
	public RestServiceException(String message){
		super(message);
	}
	
	public RestServiceException(Exception e){
		super(e);
	}
	
	public RestServiceException(String message, Exception e){
		super(message,e);
	}

}
