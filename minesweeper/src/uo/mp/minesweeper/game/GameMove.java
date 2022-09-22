package uo.mp.minesweeper.game;

public class GameMove {

	private char operation;
	private int row, column;
	
	public GameMove(char operation, int row, int column) {
		setOperation(operation);
		setRow(row);
		setColumn(column);
	}

	/**
	 * Establece la operacion
	 * @param operation
	 */
	public void setOperation(char operation) {
		this.operation = operation;
	}

	/**
	 * Establece la coordenada de la fila
	 * @param row coordenada de la fila
	 */
	public void setRow(int row) {
		this.row = row;
	}

	/**
	 * Establece la coordenada de la columna
	 * @param column coordenada de la columna
	 */
	public void setColumn(int column) {
		this.column = column;
	}

	/**
	 * Devuelve el carácter que representa el comando de usuario.
	 * @return el comando
	 */
	public char getOperation() {
		return operation;
	}

	/**
	 * Devuelve la coordenada de fila
	 * @return la coord de la fila
	 */
	public int getRow() {
		return row;
	}
	
	/**
	 * Devuelve la coordenada de columna
	 * @return la coord de la columna
	 */
	public int getColumn() {
		return column;
	}
	
	/**
	 * Devuelve una representación textual del comando completo con el siguiente
	 * formato: GameMove [operation=s, row=1, column=1 ]
	 */
	public String toString() {
		String patron = "GameMove [operation=%s, row=%d, column=%d ]";
		return String.format(patron, getOperation(), getRow(), getColumn());
	}

	
}
