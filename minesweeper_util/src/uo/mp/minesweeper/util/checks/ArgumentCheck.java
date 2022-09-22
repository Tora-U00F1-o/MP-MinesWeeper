package uo.mp.minesweeper.util.checks;


public class ArgumentCheck {

	/**
	 * Si n -parametro- no esta en el rango [minLim, maxLim] lanza una excepcion illegalArgument
	 * @param n
	 * @param minLim
	 * @param maxLim
	 * @param msg
	 */
	public static void checkNumber(int n, int minLim, int maxLim, String msg) {
		if(n<minLim || n>maxLim)
			throw new IllegalArgumentException(msg);
	}
	
	/**
	 * Comprueba que:
	 * x esté en [minLim, maxWidth]
	 * y esté en [minLim, maxHeight]
	 */
	public static void checkCoords(int x, int y, int minLim, int maxWidth, int maxHeight) {
		String patronError = "Coordenada %s (%d) debe estar en el intervalo [%d, %d]";
		ArgumentCheck.checkNumber(x, minLim, maxWidth, String.format(patronError, "x", x, minLim, maxWidth));
		ArgumentCheck.checkNumber(y, minLim, maxHeight, String.format(patronError, "y", y, minLim, maxHeight));
	}
	
	public static void checkArrayDim(Object[][] array, String msg) {
		checkNotNull(array, "Objeto introducido no valido -NULO-");
		for(Object[] row: array)
			if(row.length != array[0].length)
				throw new IllegalArgumentException(msg);
	}
	
	public static void checkNotNull(Object o, String msg) {
		if(o == null) 
			throw new IllegalArgumentException(msg);
	}
	
	public static boolean stringIsValid(String s) {
		if(s==null || s.trim().isEmpty())
			return false;
		return true;
	}
	
	public static void checkString(String s, String msg) {
		if(!stringIsValid(s))
			throw new IllegalArgumentException(msg);
			
	}
	
	public static void isTrue(boolean condition, String msg) {
		if(! condition)
			throw new IllegalArgumentException(msg);
	}
	
	public static void isTrue(boolean condition) {
		if(! condition)
			throw new IllegalArgumentException();
	}
	
	public static void checkNumDataParsed(int parts, int partsExpected) {
		if(parts != partsExpected)
			throw new IllegalArgumentException("Numero de datos incorrecto");
	}
	
}
