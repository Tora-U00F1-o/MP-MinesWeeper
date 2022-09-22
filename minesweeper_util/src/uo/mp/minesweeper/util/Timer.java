package uo.mp.minesweeper.util;


public class Timer {

	private long timeStart; 
	private long time;
	
	/**
	 * Configura el cronometro
	 * Establece la hora de comienzo del cronometro a la del momento
	 * en el que se invoca al método
	 */
	public void config() {
		setTimeStart(getCurrentTime());
	}
	
	/**
	 * Establece la hora de comienzo del cronometro con la hora deseada
	 * @param timeStart Hora de comienzo del crono
	 */
	private void setTimeStart(long timeStart) {
		this.timeStart = timeStart;
	}
	
	/**
	 * Devuelve la hora actual, en milisegundos
	 * @return los segundos transcurridos
	 */
	private long getCurrentTime() {
		return (long) System.currentTimeMillis();
	}
	
	/**
	 * Devuelve los minutos transcurridos desde 
	 * el momento que se configuro el crono
	 * @return Minutos transcurridos
	 */
	private long getMinutesElapsed() {
		return (getCurrentTime() - timeStart)/(1000*60) % 60;
	}
	
	/**
	 * Devuelve los segundos transcurridos desde 
	 * el momento que se configuro el crono
	 * @return Segundos transcurridos
	 */
	private long getSecondsElapsed() { // solo marca hasta 59, al siguiente s marca 0
		return (getCurrentTime() - timeStart) /1000 % 60;
	}
	
	/**
	 * Devuelve un entero con el tiempo que marca el crono en el formato ss
	 *  Cuenta hasta 3599 segundos --> 1 hora
	 * @return el tiempo transcurrido en segundos 
	 */
	public long getTimeElapsed() {
		return getMinutesElapsed()*60 + getSecondsElapsed();
	}
	
	/**
	 * Almacena los segundos transcurridos, como tiempo final
	 */
	public void stop() {
		time = getTimeElapsed();
	}
	
	/**
	 * Devuelve los segundos transcurridos desde que se inicio la cuenta hasta que se paró
	 * @return el tiempo final
	 */
	public long getFinalTime() {
		return time;
	}
	
}
