
package uo.mp.minesweeper.util;

import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.Scanner;

import uo.mp.minesweeper.util.checks.ArgumentCheck;


public class Console {
	
	private static PrintStream out = System.out;
	private static Scanner keyboard = new Scanner( System.in );

	/**
	 * Imprime el mensaje -parametro- 
	 * y lee por consola y devuelve una cadena 
	 * @param msg Mensaje a mostrar por consola
	 * @return	La cadena introducida por consola
	 */
	public static String readString(String msg) {
		ArgumentCheck.checkNotNull(msg, "Mensaje introducido nulo");
		
		while(true) {
			println(msg + ": ");
			
			keyboard.useDelimiter( System.lineSeparator() );
			String res = keyboard.next();
			keyboard.reset();
			
			if(ArgumentCheck.stringIsValid(res))
				return res;
			
			println("\tLinea introducida("+res+") no valida");
		} 
	}
	
	/**
	 * Imprime el mensaje -parametro- 
	 * y lee por consola y devuelve un carácter
	 * @param msg Mensaje a mostrar por consola
	 * @return El carácter introducido por consola
	 */
	public static char readCharacter(String msg) {
		ArgumentCheck.checkNotNull(msg, "Mensaje introducido nulo");
		
		while(true) {
			println(msg + ": ");
			char res = ' ';
			try {
				res = keyboard.next(".").charAt(0);
				if(Character.isWhitespace(res))
					continue;
				
				return res;
			} catch(InputMismatchException e) {
				println("\tCaracter introducido no valido\n");
				resetScanner();
			}
			if(Character.isWhitespace(res))
				return res;
			
			println("\tLinea introducida("+res+") no valida");
		} 
	}

	/**
	 * Imprime el mensaje -parametro- 
	 * y lee por consola y devuelve un entero 
	 * @param msg Mensaje a mostrar por consola
	 * @return El entero introducido por consola
	 */
	public static int readInteger(String msg) {
		ArgumentCheck.checkNotNull(msg, "Mensaje introducido nulo");
		
		while(true) {
			println( msg + ": ");
			try {
				return keyboard.nextInt();
				
			} catch (InputMismatchException e) {
				println("\tCaracter introducido no es un numero\n");
				resetScanner();
			}
		}
	}

	/**
	 * Muestra por consola la cadena introducoda
	 * @param msg la cadena a mostrar
	 */
	public static void println(String msg) {
		ArgumentCheck.checkNotNull(msg, "Mensaje introducido nulo");
		
		out.println( msg );
	}
	
	/**
	 * Resetea el scanner
	 */
	private static void resetScanner() {
		 keyboard = new Scanner( System.in );
	}

}
