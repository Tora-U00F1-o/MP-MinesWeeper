package uo.mp.minesweeper.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import uo.mp.minesweeper.util.checks.ArgumentCheck;

public class CodeUtil {

	/**
	 * Devuelve un string con la fecha del Date -parametro-
	 * en el formato: dd/mm/yy 
	 * @param date
	 * @return
	 */
	public static String getProcessedDate(Date date) {
		ArgumentCheck.checkNotNull(date, "Fecha a procesar no valida");
		return new SimpleDateFormat("dd/MM/yyyy").format( date );
	}
	
	/**
	 * Devuelve un string con la hora del Date -parametro-
	 * en el formato: HH:mm:ss
	 * @param date
	 * @return
	 */
	public static String getProcessedTime(Date date) {
		ArgumentCheck.checkNotNull(date, "Fecha a procesar no valida");
		return new SimpleDateFormat("HH:mm:ss").format( date );
	}
	
	/**
	 * Devuelve un string de el numero introducido con un añadido:
	 * si tiene una cifra le pone un 0 delante, si tiene mas 
	 * de una cifra no hace nada
	 * @param n	numero a analizar
	 * @return string de el numero introducido
	 */
	public static String makeNumberToStringPretty(int n) {
		return n/10==0 ? "0"+n : ""+n;
	}
	
	/**
	 * Devuelve el string -parametro- pero x espacios en blanco
	 * hasta que la longitud del string total sea la indicada 
	 * en el entero -parametro-
	 * @param s La cadena a tratar
	 * @param n la longitud final de la cadena
	 * @return la cadena tratada
	 */
	public static String fillStringWithWhites(String s, int n) {
		ArgumentCheck.checkNotNull(s, "String a tratar no valido");
		return s +stringMultiplicator(" ", n-s.length());
	}
	
	/**
	 * Devuelve el String -parametro- multiplicado por n -parametro-
	 * @param s Cadena a tratar
	 * @param n Numero de veces a repetir
	 * @return El String -parametro- multiplicado por n -parametro-
	 */
	public static String stringMultiplicator(String s, int n) {
		ArgumentCheck.checkNotNull(s, "String a tratar no valido");
		
		String result = "";
		for(int i=0; i<n; i++)
			result += s;
		return result;
	}
	
	/**
	 * Ordena la lista introducida -parametro- en funcion a los criterios del
	 * comparator -parametro-
	 * @param <T>
	 * @param list
	 */
	public static <T> void sort(List<T> list, Comparator<T> comparator) {
		ArgumentCheck.checkNotNull(list, "Lista a ordenar nula");
		ArgumentCheck.checkNotNull(comparator, "Comparador nulo");
		Collections.sort(list, comparator);
	}
	
	/**
	 * Crea y devuelve una fecha sobre el string y el formato
	 * introducidos -parametros-
	 * @param format formato del string
	 * @param dateString fecha string introducida
	 * @returnuna fecha sobre el string y el formato introducidos
	 * @throws ParseException
	 */
	public static Date parseDate(String format, String dateString)
			throws ParseException {
		ArgumentCheck.checkString(format, "Formato de fecha no valido");
		ArgumentCheck.checkString(dateString, "Cadena fecha no valida");
		
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date date = sdf.parse(dateString);
		return date;
	}
	
	/**
	 * Parsea un string a boolean, comparando dos opciones, si coincide
	 * con la primera devuelve true, si coincide con el segundo string false,
	 * y si no coincide con ninguno lanza una ParseException
	 * @param bool
	 * @param isTrue
	 * @param isFalse
	 * @return
	 * @throws ParseException
	 */
	public static boolean parseBoolean(String bool, String isTrue, String isFalse)
			throws ParseException {
		
		ArgumentCheck.checkNotNull(bool, "Boolean a parsear no valido");
		ArgumentCheck.checkNotNull(isFalse, "Opcion de parseo falsa no valida");
		ArgumentCheck.checkNotNull(isTrue, "Opcion de parseo verdadera no valida");
		
		if(bool.equals(isTrue))
			return true;
		else if(bool.equals(isFalse))
			return false;
		else 
			throw new ParseException(bool+" No coincide con sus opciones: verdadera("
					+isTrue +") ni falsa("+isFalse+")", 3);
	}
	
	
	
	public static <T> void updateDatabase(T object, String fileName) {
		ObjectOutputStream file = null;
		try {
			try {
				file = new ObjectOutputStream(
						new BufferedOutputStream( new FileOutputStream(fileName)));
				
				file.writeObject(object); 
				
			} finally {
				if(file!=null)
					file.close();
			}
		} catch (IOException e) {
			throw new RuntimeException("A ocurrido un error durante la "
					+ "escritura de la base de datos");
		}
	}
	
	/**
	 * 
	 * @param <T>
	 * @param fileName
	 * @return
	 * @throws FileNotFoundException
	 */
	@SuppressWarnings("unchecked")
	public static <T> T readDatabase(String fileName) throws FileNotFoundException {
		T obj = null;
		ObjectInputStream file = null;
		try {
			try {
				 file = new ObjectInputStream( 
						new BufferedInputStream( new FileInputStream(fileName)));
				
				 obj = (T) file.readObject();
			} finally {	
				if(file!=null)
					file.close();
			}
		} catch (FileNotFoundException e) {
			throw e;
			
		} catch (IOException | ClassNotFoundException e) {
			throw new RuntimeException("Error al cargar la base de datos");
		}
		
		return obj;
	}
}