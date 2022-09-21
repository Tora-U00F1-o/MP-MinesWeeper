package uo.mp.minesweeper.util.exceptions;

public class LineFormatException extends Exception {

	private static final long serialVersionUID = 1L;
	private static int lineNumber;

	public LineFormatException() {
		super();
	}

	public LineFormatException(String msg) {
		super(msg);
	}
	
	@SuppressWarnings("static-access")
	public LineFormatException(int lineNumber, String msg) {
		super("Error linea: "+getLineNumber()+" --> "+msg);
		this.lineNumber = lineNumber;
	}

	public LineFormatException(Throwable cause) {
		super(cause);
	}

	public LineFormatException(String message, Throwable cause) {
		super(message, cause);
	}

	public LineFormatException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
	

	public static int getLineNumber() {
		return lineNumber;
	}

}
