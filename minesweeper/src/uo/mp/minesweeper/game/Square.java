package uo.mp.minesweeper.game;

import uo.mp.minesweeper.game.actions.NullAction;
import uo.mp.minesweeper.util.checks.ArgumentCheck;

public class Square {
	
	//State
	public enum State {CLOSED, OPEN, FLAGGED}

// Ctes
	//Value
	public final static int EMPTY = 0;
	public final static int MINE = -1;
	
	public final static char ICON_HIDE = '#';
	public final static char ICON_FLAGGED = (char) 182;
	public final static char ICON_EMPTY = ' ';
	public final static char ICON_MINE = '@';

// V.Defecto
	public final static State DEFAULT_STATE = State.CLOSED;
	public final static Action DEFAULT_ACTION = new NullAction();
	
// Atributos
	private State state; 
	private int value; // 0->Empty  1-8 ->Pista  -1->Mina
	private Action action;

	// Const
	
	/**
	 * Constructor por defecto
	 */
	public Square() {
		setState(DEFAULT_STATE);
		setValue(EMPTY);
		setAction(DEFAULT_ACTION);
	}
	
// Metodos
	
	/**
	 * establece el estado de la casilla
	 * @param state the state to set
	 */
	private void setState(State state) {
		ArgumentCheck.checkNotNull(state, "Estado Introducido no valido -NULO-");
		this.state = state;
	}
	
	/**
	 * Devuelve el estado de la casilla
	 * 0->Closed  1 ->Open  2->Flagged
	 * @return the state
	 */
	public State getState() {
		return state;
	}
	
	/**
	 * Metodo getIconStatus
	 * @return El icono asociado al Square depensiendo de su state
	 */
	public String toString() {
		return Character.toString(getStateIcon());
	}
	
	/**
	 * Devuelve el icono asociado al estado de la casilla
	 * @return el icono de estado de la casilla
	 */
	protected char getStateIcon() {
		if(getState() == State.CLOSED)
			return ICON_HIDE;
		
		else if(getState() == State.FLAGGED)
			return ICON_FLAGGED;
		
		else if(getValue() == MINE)
			return ICON_MINE;
		
		else if(getValue() == EMPTY)
			return ICON_EMPTY;
		else 
			return (char)('0'+getValue() );
	}
	
	/**
	 * Metodo setValue Establece el valor numérico de la casilla
	 * 0->Empty  1-8 ->Pista  -1->Mina
	 * @param value	el valor numérico de la casilla
	 */
	public void setValue(int value) {
		ArgumentCheck.checkNumber(value, -1, 8, "Valor no valido");
		this.value = value;
	}
	
	/**
	 * Metodo getValue
	 * Devuelve el valor numérico de la casilla.
	 * 0->Empty  1-8 ->Pista  -1->Mina
	 * @return El valor numérico de la casilla.
	 */
	public int getValue() {
		return value;
	}
	
	/**
	 * Establece la accion a la casilla
	 * @param action
	 */
	public void setAction(Action action) {
		ArgumentCheck.checkNotNull(action, "Accion introducida no valida -NULA-");
		this.action = action;
	}
	
	/**
	 * Metodo stepOn
	 * Pasa la casilla a estado OPEN si la está CLOSED.
	 */
	public void stepOn() {
		if(getState() == State.CLOSED) {
			setState(State.OPEN);
			action.activate();
		}
	}
	
	/**
	 * Metodo flag
	 * Si la casilla está cerrada, su estado pasa a FLAGGED
	 */
	public void flag() {
		if(getState() == State.CLOSED)
			setState(State.FLAGGED);
	}
	
	/**
	 * Metodo unflag
	 * Si la casilla está marcada, su estado pasa a CLOSED
	 */
	public void unflag() {
		if(hasFlag())
			setState(State.CLOSED);
	}
	
	/**
	 * Metodo hasFlag
	 * 
	 * @return true/false <--> si el estado de la casilla es FLAGGED/caso contrario
	 */
	public boolean hasFlag() { 
		return getState()==State.FLAGGED;
	}
	
	/**
	 * Metodo open
	 * Pasa la casilla a estado OPEN incondicionalmente
	 */
	public void open() {
		setState(State.OPEN);
	}
	
	/**
	 * Metodo isOpen
	 * 
	 * @return true/false <--> estado de la casilla es OPEN/caso contrario
	 */
	public boolean isOpen() {
		return getState()==State.OPEN;
	}
	
	/**
	 * Metodo putMine
	 * Coloca el valor numérico correspondiente a una mina en la casilla
	 */
	public void putMine() {
		this.setValue(MINE);
	}
	
	/**
	 * Metodo hasMine
	 * Devuelve true si la casilla contiene una mina y false en caso contrario
	 * @return true/false <--> si la casilla contiene una mina/caso contrario.
	 */
	public boolean hasMine() {
		return this.getValue()==MINE;
	}
	
	/**
	 * Metodo isEmpty
	 * Devuelve true si la casilla esta vacia(no tiene ni mina ni pista)
	 * y false en caso contrario
	 * @return true/false <--> si la casilla esta vacia/caso contrario.
	 */
	protected boolean isEmpty() {
		return getValue()==EMPTY;
	}
	
}
