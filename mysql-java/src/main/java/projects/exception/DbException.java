package projects.exception;

@SuppressWarnings("serial")
public class DbException extends RuntimeException{ //extends the RuntimeException superclass from java.lang 
	//each method allows us to call superclass methods and override their constructors 
	public DbException(String message) {
		super(message);
	}

	public DbException(Throwable cause) {
		super(cause);
	}

	public DbException(String message, Throwable cause) {
		super(message,cause);
	}

}
