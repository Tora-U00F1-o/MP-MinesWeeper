package uo.mp.minesweeper.ranking;

import java.io.Serializable;
import java.util.Date;

import uo.mp.minesweeper.session.GameLevel;
import uo.mp.minesweeper.util.CodeUtil;
import uo.mp.minesweeper.util.checks.ArgumentCheck;

public class Score implements Serializable{

	private static final long serialVersionUID = 1L;
	private String userName;
	private GameLevel level;
	private long time;
	boolean hasWon;
	Date date;
	
	/**
	 * Crea un objeto Score que almacena los datos de una partida jugada
	 * @param userName Nombre de usuario del jugador
	 * @param level Nivel de dificultad
	 * @param time Duracion de la partida
	 * @param hasWon true/false si el jugador ganó/perdió la partida
	 */
	public Score(String userName, GameLevel level, long time, boolean hasWon) {
		setUserName(userName);
		setLevel(level);
		setTime(time);
		setHasWon(hasWon);
		setDate(new Date());
	}
	
	/**
	 * Crea un objeto Score que almacena los datos de una partida jugada
	 * @param userName Nombre de usuario del jugador
	 * @param level Nivel de dificultad
	 * @param time Duracion de la partida
	 * @param hasWon true/false si el jugador ganó/perdió la partida
	 * @param date fecha del score
	 */
	public Score(String userName, GameLevel level, long time, boolean hasWon,
			Date date) {
		setUserName(userName);
		setLevel(level);
		setTime(time);
		setHasWon(hasWon);
		setDate(date);
	}

	/**
	 * Devuelve el valor de userName
	 * @return Nombre de usuario del jugador
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * Establece el nombre de usuario del jugador
	 * @param userName Nombre de usuario del jugador
	 */
	private void setUserName(String userName) {
		ArgumentCheck.checkString(userName, "Nombre de usuario no valido");
		this.userName = userName;
	}

	/**
	 * Devuelve el nivel de la partida
	 * @return el nivel de la partida
	 */
	public GameLevel getLevel() {
		return level;
	}
	
	/**
	 * Establece el nivel de la partida
	 * @param level el nivel de la partida
	 */
	private void setLevel(GameLevel level) {
		ArgumentCheck.checkNotNull(level, "Nivel de la partida -nulo-");
		this.level = level;
	}

	/**
	 * Devuelve la duracion de la partida
	 * @return la duracion de la partida
	 */
	public long getTime() {
		return time;
	}

	/**
	 * Establce la duracion de la partida
	 * @param time la duracion de la partida
	 */
	private void setTime(long time) {
		ArgumentCheck.isTrue(time>=0, "Tiempo no valido");
		this.time = time;
	}

	/**
	 * Devuelve si se ganó la partida
	 * @return true/false si se ganó/no se ganó la partida
	 */
	public boolean hasWon() {
		return hasWon;
	}

	/**
	 * Estabece si se ganó la partida
	 * @param hasWon true/false si se ganó/no se ganó la partida
	 */
	private void setHasWon(boolean hasWon) {
		this.hasWon = hasWon;
	}
	
	/**
	 * Devuelve la fecha de la partida
	 * @return la fecha de la partida
	 */
	public Date getDate() {
		return date;
	}
	
	/**
	 * Establece la fecha en la que se jugo la partida
	 * @param date La fecha de la partida
	 */
	private void setDate(Date date) {
		ArgumentCheck.checkNotNull(date, "Fecha de la partida nula");
		this.date = date;
	}
	
	/**
	 * Devuelve una representación textual del contenido del objeto.
	 * return Una representación textual del contenido del objeto.
	 */
	public String toString() {
		String patron = "%s%s  %s  %s  %s  %s";
		String user = CodeUtil.fillStringWithWhites(getUserName(), 15);
		String date = CodeUtil.getProcessedDate(getDate());
		String dateTime = CodeUtil.getProcessedTime(getDate());
		String level = CodeUtil.fillStringWithWhites(getLevel().toString(), 6);
		String result = hasWon() ? "won " : "lost";
		String time = Long.toString(getTime());
		
		return String.format(patron, user, date, dateTime, level, result, time);
	}
}
