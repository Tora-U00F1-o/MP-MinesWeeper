package uo.mp.minesweeper.game.actions;

import uo.mp.minesweeper.game.Action;
import uo.mp.minesweeper.game.Board;
import uo.mp.minesweeper.util.checks.ArgumentCheck;

public class BlowUpAction implements Action{
	
	private Board board;
	
	/**
	 * Constructor con un parametro board
	 * @param board El tablero en el que está la casilla
	 */
	public BlowUpAction(Board board) {
		setBoard(board);
	}
	
	/**
	 * Establece el tablero al que apunta la casilla
	 * @param board El tablero en el que está la casilla
	 */
	private void setBoard(Board board) {
		ArgumentCheck.checkNotNull(board, "El tablero introducido no existe -NULO-");
		this.board = board;
	}
	
	/**
	 * Provoca que el tablero se marque como explotado
	 */
	public void activate() {
		board.markAsExploded();
		board.unveil();
	}
}
