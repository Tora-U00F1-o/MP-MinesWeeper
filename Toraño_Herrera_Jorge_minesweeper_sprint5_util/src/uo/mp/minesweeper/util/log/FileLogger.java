package uo.mp.minesweeper.util.log;

import java.util.Date;

import uo.mp.minesweeper.util.CodeUtil;
import uo.mp.minesweeper.util.checks.ArgumentCheck;
import uo.mp.minesweeper.util.files.FileUtil;
import uo.mp.minesweeper.util.log.SimpleLogger;

public class FileLogger implements SimpleLogger {
	
	private String fileName;
	
	/**
	 * Constructor de FileLogger
	 * @param fileName el nombre del fichero de log
	 */
	public FileLogger(String fileName) {
		setFileName(fileName);
	}

	/**
	 * Devuelve el nombre del fichero de log
	 * @return el nombre del fichero de log
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * Establece el nombre del fichero de log
	 * @param fileName el nombre del fichero de log
	 */
	private void setFileName(String fileName) {
		ArgumentCheck.checkString(fileName, "Nombre introducido no valido");
		this.fileName = fileName;
	}

	/**
	 * Sends the full stack trace of the exception received to the log
	 * prefixing it with a timestamp 
	 * @param t, the exception to be logged
	 */
	public void log(Exception ex) {
		ArgumentCheck.checkNotNull(ex, "Excepcion a registrar nula");
		
		Date dateSource = new Date();
		String date = CodeUtil.getProcessedDate(dateSource);
		String time = CodeUtil.getProcessedTime(dateSource);
		
		log("["+date+" - "+time+"] : "+ex.getClass()+" " +ex.getCause()
						+": " +ex.getMessage());
	}
	
	/**
	 * Sends the string received as message to the log prefixing it with 
	 * a timestamp 
	 * @param message
	 */
	public void log(String message) {
		ArgumentCheck.checkString(message, "Mensaje a registrar no valido");

		new FileUtil().writeLine(message, getFileName(), true);			
	}
}
