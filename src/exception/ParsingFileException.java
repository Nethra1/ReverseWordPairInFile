package exception;

/**
 * @author nethravijayadas
 *
 */
public class ParsingFileException extends Exception{

	/**
	 *  Custom exception to throw parse file exception
	 */
	private static final long serialVersionUID = 2458337984561177761L;
	
	/**
	 * @param msg meaningful message of what happened
	 * @param e exception details
	 */
	public ParsingFileException(String msg, Throwable e) {
		super(msg, e);
	}

}
