package uo.mp.minesweeper.console;

import java.util.List;

import uo.mp.minesweeper.ranking.Score;
import uo.mp.minesweeper.session.GameLevel;
import uo.mp.minesweeper.session.SessionInteractor;
import uo.mp.minesweeper.util.Console;

public class ConsoleSessionInteractor implements SessionInteractor{
	
	public final static String rankHeadBoard = "User           "
			+ "Date        Hour      Level   Res   time";

	/**
	 * Solicita al usuario un nivel de dificultad y devuelve la respuesta con un GameLevel.
	 * @return el nivel de dificultad elegido
	 */
	public GameLevel askGameLevel() {
		while(true) {
			char level = Console.readCharacter("Level? (e)asy, "
												+ "(m)edium, (h)igh");
			switch(Character.toLowerCase(level)) {
			case 'e':
				return GameLevel.EASY;
			case 'm':
				return GameLevel.MEDIUM;
			case 'h':
				return GameLevel.HARD;
			default:
				Console.println("\tOption invalid\n");
			}
		}
	}

	/**
	 * Solicita al usuario su nombre y devuelve un String con la respuesta. El nombre que 
	 * devuelve no puede ser vacío.
	 * @return El nombre introducido
	 */
	public String askUserName() {
		return Console.readString("Player name ?");
	}
	
	/**
	 * Solicita al usuario que introduzca una opción del menú de sesión. Devuelve un entero que
	 * representa la opción escogida de entre las posibles. Un valor mayor que cero representará 
	 * alguna de las acciones disponibles. El valor cero representará siempre la opción salir.
	 */
	public int askNextOption() {
		showMenu();		
		return Console.readInteger("Option? ");
	}
	
	/**
	 * Muestra por consola el menú
	 */
	private void showMenu() {
		String[] menuOptions = {"  1- Play a new game", "  2- Show my results", 
				"  3- Show all results",  "  4- Export results", 
				"  5- Import results", "  0- Exit"} ;
		
		Console.println("Available options:");
		for(String option: menuOptions)
			Console.println(option);
	}

	/**
	 * Al finalizar una partida, pregunta al usuario si quiere guardar su puntuación. 
	 * Devuelve true si la respuesta es afirmativa, y false en caso contrario.
	 * @return true/false si el usuario quiere guardar o no 
	 */
	public boolean doYouWantToRegisterYourScore() {
		
		while(true) {
			char option = Console.readCharacter("Do you want to store your "
									+ "score? (y)es, (n)o ");
			if(option=='y' || option=='n')
				return Character.toLowerCase(option) == 'y';
			
			Console.println("\tOption invalid\n");
		} 
	}

	/**
	 * Recibe una lista de objetos Score representando todas las puntuaciones registradas en el 
	 * sistema. Las muestra todas con su información completa (formato tabular, una línea por 
	 * cada score).
	 * @param ranking La lista a mostrar
	 */
	public void showRanking(List<Score> ranking) {
		
		Console.println(rankHeadBoard);
		for(Score score: ranking)
			Console.println(score.toString());

		Console.println("\n");
	}

	/**
	 * Recibe una lista de objetos Score representando todas las puntuaciones registradas en el 
	 * sistema. Las muestra todas con su información completa (formato tabular, una línea por 
	 * cada score). Omite la información de quién es el usuario asociado a cada partida (se 
	 * sobreentiende que es el usuario almacenado en la sesión).
	 * @param ranking
	 */
	public void showPersonalRanking(List<Score> ranking) {
		Console.println(rankHeadBoard.substring(15));
		for(Score score: ranking)
			Console.println(score.toString().substring(15));

		Console.println("\n");
		
	}

	/**
	 * Muestra al usuario un mensaje de despedida cuando escoge finalizar la sesión.
	 */
	public void showGoodBye() {
		Console.println("Thanks for your session, Sayonara baby!");
		
	}

	/**
	 * Comunica un mensaje de error al usuario. El mensaje a mostrar se recibe como parámetro.
	 * @param message
	 */
	public void showErrorMessage(String message) {
		Console.println("Error leve: "+message);
	}

	/**
	 * Comunica mensajes de error graves al usuario. Este método ha de usarse para informar de
	 * que el error no puede ser solucionado y el programa finalizará su ejecución de forma
	 * inmediata.
	 * @param message
	 */
	public void showFatalErrorMessage(String message) {
		Console.println("Fatal Error- "+message 
						+"\nDo not panic, contact Toraño!");
		
	}

	/**
	 * Pide al usuario el nombre de un archivo. Devuelve el nombre mediante un String
	 * @return el nombre del archivo
	 */
	public String askFileName() {
		return Console.readString("File name ?");
	}

}
