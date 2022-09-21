package uo.mp.minesweeper.util.files;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import uo.mp.minesweeper.util.checks.ArgumentCheck;
import uo.mp.minesweeper.util.exceptions.ApplicationException;


public class FileUtil {
	//TODO al final del proyecto comprobar que no hay metodos que no se usan
	/**
	 * Lee y un archivo devuelve una lista de 
	 * cadenas con cada linea del archivo
	 * @param fileName Nombre del fichero
	 * @return una lista de cadenas con cada linea del archivo
	 * @throws ApplicationException
	 */
	public List<String> loadLines(String fileName) 
										throws ApplicationException {
		ArgumentCheck.checkString(fileName, "Nombre de fichero a "
				+ "modificar no valido");
		
		BufferedReader bf = null;
		List<String> lines = new LinkedList<>();
		
		try {
			try {
				bf = new BufferedReader(
								new FileReader((fileName)));
				while(bf.ready())
					lines.add(bf.readLine());
				
			} finally {
				if(bf!=null)
					bf.close();
			}
		} catch(FileNotFoundException e) {
			throw new ApplicationException("Archivo: \""+
						fileName+"\" no encontrado");
		
		} catch (IOException e){
			throw new RuntimeException("No se ha podido importar el "
					+ "archivo, intentelo más tarde.");
		}
		
		return lines;
	}
	
	/**
	 * Añade o sobreescribe al fichero -parametro- con  la linea -parametro-
	 * en funcion del boolean append -parametro-
	 */
	public void writeLine(String line, String fileName, boolean append) {
		ArgumentCheck.checkString(line, "Linea a escribir no valida");
		
		List<String> lines= new LinkedList<String>();
		lines.add(line);
		
		writeLines(lines, fileName, append);
		
	}
	
	/**
	 * Sobreescribe el fichero indicado -parametro- 
	 * con  la lista de cadenas -parametro-
	 */
	public void writeLines(List<String> lines, String fileName) {
		writeLines(lines, fileName, false);
	}
	
	/**
	 * Añade o sobreescribe al fichero -parametro- con  la linea -parametro-
	 * en funcion del boolean append -parametro-
	 */
	public void writeLines(List<String> lines, String fileName,
							boolean append) {
		
		ArgumentCheck.checkNotNull(lines, "Lista a escribir nula");		
		ArgumentCheck.checkString(fileName, "Nombre de fichero a "
				+ "modificar no valido");
		
		BufferedWriter writer = null;
		try {
			try {
				writer = new BufferedWriter(new FileWriter(fileName, append));
				for(String line: lines)
					writer.write(line+"\n");
				
			} finally {
				if(writer!=null)
					writer.close();
			}
			
		}catch (IOException e){
			throw new RuntimeException("No se ha podido exportar el "
					+ "archivo, intentelo más tarde.");
		}
	}
}
