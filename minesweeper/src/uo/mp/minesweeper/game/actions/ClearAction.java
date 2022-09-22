package uo.mp.minesweeper.game.actions;

import java.util.List;

import uo.mp.minesweeper.game.Action;
import uo.mp.minesweeper.game.Square;
import uo.mp.minesweeper.util.checks.ArgumentCheck;

public class ClearAction implements Action {
	
	private List<Square> neightbouringSquares;

	/**
	 * Constructor
	 * * @param neightbouringSquares Casillas adyacentes a la casilla
	 */
	public ClearAction (List<Square> neightbouringSquares) {
		setNeightbouringSquares(neightbouringSquares);
	}
	
	/**
	 * Establece la lista de objetos Square que se corresponden con aquellas
	 * casillas adyacentes a la casilla a la que la acción está asociada
	 * @param neightbouringSquares Casillas adyacentes a la casilla
	 */
	private void setNeightbouringSquares(List<Square> neightbouringSquares){
		ArgumentCheck.checkNotNull(neightbouringSquares, "Lista no valida -NULA-");
		this.neightbouringSquares = neightbouringSquares;
	}
	
	public void activate() {
		for(Square s: neightbouringSquares)
			s.stepOn();
	}


}
