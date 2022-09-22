package uo.mp.minesweeper.game;

import uo.mp.minesweeper.util.Timer;
import uo.mp.minesweeper.util.checks.ArgumentCheck;

public class Game {

// Atrib
	private Board board;
	private GameInteractor interactor;
	private Timer timer;

//Const
	public Game(Board board) {
		setBoard(board);
		timer = new Timer();
	}

// Metodos

	/**
	 * Establece el tablero introducido como parametro como el tablero del juego
	 * 
	 * @param board el tablero donde jugar
	 */
	private void setBoard(Board board) {
		this.board = board;
	}

	/**
	 * Asigna a Game el objeto GameInteractor recibido como parámetro
	 * 
	 * @param interactor
	 */
	public void setInteractor(GameInteractor interactor) {
		ArgumentCheck.checkNotNull(interactor, "Interactor no valido -NULO-");
		this.interactor = interactor;
	}

	/**
	 * Inicia el juego
	 */
	public void play() {
		board.uncoverWelcomeArea();
		timer.config();
		interactor.showReadyToStart();
		do {
			interactor.showGame(timer.getTimeElapsed(), board);
			GameMove gm = interactor.askMove(board.rows(), board.cols());

			int x = gm.getColumn();
			int y = gm.getRow();
			switch (gm.getOperation()) {
			case 's':
			case 'S':
				board.stepOn(x, y);
				break;
			case 'f':
			case 'F':
				board.flag(x, y);
				break;
			case 'u':
			case 'U':
				board.unflag(x, y);
				break;
			}
			if (board.getMinesLeft() == 0) {
				board.chouTableWin();
				break;
			}

		} while (!board.isExploded());

		timer.stop();
		interactor.showGame(getTime(), board); // enseña el tablero final

		if (board.getMinesLeft() == 0)
			interactor.showCongratulations(timer.getTimeElapsed());
		else {
			interactor.showBooommm();
			interactor.showGameFinished();
		}
	}
	
	public long getTime() {
		return timer.getFinalTime();
	}
}
