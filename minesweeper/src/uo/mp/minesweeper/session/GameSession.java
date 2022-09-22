package uo.mp.minesweeper.session;

import java.io.FileNotFoundException;
import java.util.List;

import uo.mp.minesweeper.game.Board;
import uo.mp.minesweeper.game.Game;
import uo.mp.minesweeper.game.GameInteractor;
import uo.mp.minesweeper.ranking.GameRanking;
import uo.mp.minesweeper.ranking.Score;
import uo.mp.minesweeper.util.CodeUtil;
import uo.mp.minesweeper.util.checks.ArgumentCheck;
import uo.mp.minesweeper.util.exceptions.ApplicationException;
import uo.mp.minesweeper.util.log.SimpleLogger;

public class GameSession {

// ctes
		// options of menu
	public final static int MAX_OPTION_NUMBER = 5; 
	public final static int EXIT = 0;
	public final static int PLAY = 1;
	public final static int SHOW_PERSONAL_RANKING = 2;
	public final static int SHOW_RANKING = 3;
	public final static int EXPORT_RESULTS = 4;
	public final static int IMPORT_RESULTS = 5;
		// board sizes positions
	public final static int WIDTH = 0;
	public final static int HEIGHT = 1;
	public final static int PERCENTAGE = 2;
		// levels {width, height, percentage}
//	public final static int[] EASY_BOARD = {9, 9, 12};
//	public final static int[] MEDIUM_BOARD = {16, 16, 15};
//	public final static int[] HARD_BOARD = {30, 16, 20};
	public final static int[] EASY_BOARD = {3, 3, 12};
	public final static int[] MEDIUM_BOARD = {4, 4, 15};
	public final static int[] HARD_BOARD = {3, 3, 20};
	
//Atributos
	GameInteractor gameIn;
	SessionInteractor sessionIn;
	SimpleLogger logger;
	GameRanking ranking;
	
	/**
	 * Inicia toda la lógica principal de GameSession.
	 */
	public void run() {
		try {
			
			String user = askUserName();
			loadFromDatabase();
			askNextOption(user);
			sessionIn.showGoodBye();
			updateDatabase();
		
		} catch(Exception e) {
			handleFatalError(e);
		}
	}
	
	/**
	 * Pregunta el nombre de usuario, si es valido lo acepta y lo devuelve
	 * @return el nombre de usuario introducido, solo si es valido
	 */
	private String askUserName() {
		while(true) {
			String user = sessionIn.askUserName();
			if(ArgumentCheck.stringIsValid(user))
				return user;
			
			handleSimpleError("Usuario ("+user+") no válido, pruebe con otro");
		}
	}
	
	/**
	 * Pregunta y procesa la opcion elegida por el usuario
	 * Lleba a cabo el bucle del programa hasta que se 
	 * elija la opcion EXIT
	 * @param user
	 * @throws ApplicationException 
	 */
	private void askNextOption(String user) {
		while(true) {
			int option = sessionIn.askNextOption();
			
			if(option < EXIT || option > MAX_OPTION_NUMBER) {
				sessionIn.showErrorMessage("Número de opción no reconocida\n");
				continue;
				
			} else if(option == EXIT) {
				break;
			}
			chooseOption(user, option);
		}
	}
	
	/**
	 * Lleva a cabo la logica de la opcion elegida por el usuario
	 * @param user	Nombre de usuario que está logueado
	 * @param option Opcion elegida
	 * @return	Devuelve la opcion elegida por el usuario
	 * @throws ApplicationException 
	 */
	private void chooseOption(String user, int option) {
		switch(option) {
		case EXIT:
			break;
		case PLAY:
			Score game = playGame(user);
			if(game.hasWon() && 
					sessionIn.doYouWantToRegisterYourScore())
				registerScore(game);
			break;
			
		case SHOW_PERSONAL_RANKING:
			List<Score> personalRanking = ranking.getScoresFor(user);
			sessionIn.showPersonalRanking(personalRanking);
			break;
			
		case SHOW_RANKING:
			List<Score> generalRanking = ranking.getGeneral();
			sessionIn.showRanking(generalRanking);			
			break;
			
		case EXPORT_RESULTS:
			String fileWhereSave = askFileName();
			exportRanking(fileWhereSave);
			break;
			
		case IMPORT_RESULTS:
			String fileToLoad = askFileName();
			importRanking(fileToLoad);
			break;
			
		}
	}
	
	/**
	 * genera y comienza la partida
	 * @param level Nivel de dificultad del juego
	 * @param userName Nombre de usuario del jugador
	 * @return un score con los datos de la partida jugada
	 */
	private Score playGame(String userName) {
		GameLevel level = askGameLevel();
		Board board = generateBoard(level);
		Game game = new Game(board);
		game.setInteractor( gameIn );
		
		game.play();
		
		return generateGameReport(userName, level, game, board);
	}
	
	/**
	 * Devuelve el nivel de juego
	 * @return el nivel del juego
	 */
	private GameLevel askGameLevel() {
		GameLevel level = null;
		while(true) {
			level = sessionIn.askGameLevel();
			if(level!=null)
				return level;
			
			handleSimpleError("Nivel de juego no reconocido, "
					+ "por favor introduzca uno válido");
		}
	}
	
	/**
	 * Genera el tablero de juego en funcion de la dificultad del juego
	 * @param level dificultad de la partida
	 * @return el tablero de juego en funcion de la dificultad
	 */
	private Board generateBoard(GameLevel level) {
		int[] sizes = {};
		
		switch(level) {
		case EASY:    sizes = EASY_BOARD;   break;
		case MEDIUM:  sizes = MEDIUM_BOARD; break;
		case HARD:    sizes = HARD_BOARD;   break;
		}
		
		return new Board(sizes[WIDTH], sizes[HEIGHT], sizes[PERCENTAGE]);
	}
	
	/**
	 * Genera y devuelve un score con los datos de la partida introducida
	 * @param userName Nombre de usuario del jugador
	 * @param level Dificultad de la partida
	 * @param game la partida a hacer el score
	 * @param board el tablero de la partida
	 * @return un score con los datos de la partida jugada
	 */
	private Score generateGameReport(String userName, 
						GameLevel level, Game game, Board board) {
		
		long time = game.getTime();
		boolean hasWon = !board.isExploded();
		return new Score(userName, level, time, hasWon);
	}
	
	/**
	 * Registra la partida (Score) introducida
	 * @param game partida a registrar
	 */
	private void registerScore(Score game) {
		ranking.append(game);
		updateDatabase();
	}
	
	/**
	 * Pregunta el nombre de fichero, si es valido lo acepta y lo devuelve
	 * @return el nombre de fichero introducido, solo si es valido
	 */
	private String askFileName() {
		while(true) {
			String fileName = sessionIn.askFileName();
			if(ArgumentCheck.stringIsValid(fileName))
				return fileName;
			
			handleSimpleError("Fichero ("+fileName+") no válido"
					+ ", pruebe con otro");
		}
	}
	
	/**
	 * Exporta el ranking actual al fichero indicado
	 * @param fileName
	 */
	private void exportRanking(String fileName) {
		ranking.saveRanking(fileName);
	}
	
	/**
	 * Carga el ranking del fichero indicado
	 * @param fileName
	 */
	private void importRanking(String fileName) {
		try {
			ranking.loadRanking(fileName);
			updateDatabase();
			
		} catch (ApplicationException e) {
			handleSimpleError("No se ha podido cargar el archivo"
					+ " ("+fileName+"), pruebe con otro.");
		}
	}
	
	/**
	 * Provoca que GameSession utilice el objeto SessionInteractor recibido como parámetro.
	 * @param interactor
	 */
	public void setSessionInteractor(SessionInteractor interactor) {
		this.sessionIn = interactor;
	}
	
	/**
	 * Provoca que GameSession utilice el objeto GameInteractor recibido como parámetro.
	 * @param interactor
	 */
	public void setGameInteractor(GameInteractor interactor) {
		this.gameIn = interactor;
	}
	
	/**
	 * Provoca que GameSession utilice el objeto SimpleLogger recibido como parámetro.
	 * @param logger
	 */
	public void setLogger(SimpleLogger logger) {
		this.logger = logger;
	}
	
	/**
	 * Almacena como ranking el objeto recibido como parámetro.
	 * @param ranking
	 */
	public void setGameRanking(GameRanking ranking) {
		this.ranking = ranking;
	}
	
	/**
	 * muestra el mensaje introducido como Error leve 
	 * @param excepcion a informar
	 */
	private void handleSimpleError(String s) {
		sessionIn.showErrorMessage(s);
	}
	
	/**
	 * Opera la excepcion introducida como parametro
	 * como un error grave, registra la excepcion y 
	 * muestra un mensaje de Error Fatal
	 * @param excepcion a registrar
	 */
	private void handleFatalError(Exception e) {
		logger.log(e);
		sessionIn.showFatalErrorMessage(e.getMessage());
	}
	
	public void updateDatabase() {
		CodeUtil.updateDatabase(ranking, ranking.getFileName());
	}
	
	public void loadFromDatabase() {
		try {//TODO con los sets
			ranking = CodeUtil.readDatabase(ranking.getFileName());
		} catch (FileNotFoundException e) {
			ranking = new GameRanking(ranking.getFileName());
		}
	}
	
}
