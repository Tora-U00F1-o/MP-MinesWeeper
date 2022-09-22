package uo.mp.minesweeper.game;

import uo.mp.minesweeper.game.actions.BlowUpAction;
import uo.mp.minesweeper.game.actions.ClearAction;
import uo.mp.minesweeper.game.actions.NullAction;
import uo.mp.minesweeper.util.checks.ArgumentCheck;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Board {

	// Ctes
		public final static int MIN_BOARD_SIZE = 1;
		public final static int MAX_BOARD_SIZE = 100;
		
	// Atrib
		private Square[][] board;
		private int numberOfMines;
		private boolean state; // true/false <--> el tablero esta bien/ ha explotado algo
		
		
	// Const
		/**
		 * Contructor 1
		 * @param width Anchura tablero
		 * @param height Altura tablero
		 * @param percentage Porcentaje de minas
		 */
		public Board(int width, int height, int percentage) {
			ArgumentCheck.checkNumber(percentage, 0, 100,
					"El porcentaje debe estar en el rango [0,100]");
			
			setBoard(new Square[height][width]);
			fillBoard();
			
			int nMines = (int) (width*height*percentage/100+0.5);
			setNumberOfMines(nMines);
			putMines();
			setUp();
		}
		
		/**
		 * Contructor 2
		 * @param mines Numero de minas en el tablero
		 * @param squares el tablero a jugar
		 */
		public Board(int mines, Square[][] squares) {
			setBoard(squares);
			setNumberOfMines(mines);
			setUp();
		}


		
	// Metodos
		
		/**
		 * Metodo setBoard
		 * Establece el tablero del juego
		 * @param board Tablero de juego
		 */
		private void setBoard(Square[][] board) {
			ArgumentCheck.checkNotNull(board, "Tablero introducido no valido -NULO-");
			
			ArgumentCheck.checkNumber(board[0].length, MIN_BOARD_SIZE, MAX_BOARD_SIZE,
							"La anchura del tablero debe ser >0 y <=" +MAX_BOARD_SIZE);
			ArgumentCheck.checkNumber(board.length, MIN_BOARD_SIZE, MAX_BOARD_SIZE, 
							"La altura del tablero debe ser >0 y <=" +MAX_BOARD_SIZE);
			       
			ArgumentCheck.checkArrayDim(board, "Tablero introducido no valido -IRREGULAR-");
			this.board = board;
		}
		
		/**
		 * Termina de preparar el tablero
		 * Coloca los valores a las casillas, las acciones
		 * y establece el estado del tablero no explotado
		 * 
		 */
		private void setUp() {
			setValue();
			setActions();
			setState(true);
		}
		
		/**
		 * Devuelve el numero de filas del tablero
		 * @return numero de filas
		 */
		public int rows() {
			return board.length;
		}
		
		/**
		 * Devuelve el numero de columnas del tablero
		 * @return numero de columnas
		 */
		public int cols() {
			return board[0].length;
		}
		
		/**
		 * Metodo setState 
		 * Establece el estado del tablero
		 * @param state  true/false <--> no ha habido explosiones/reventó algo
		 */
		private void setState(boolean state) {
			this.state = state;
		}
		
		/**
		 * Rellena el tablero de casillas vacias
		 */
		private void fillBoard() {
			for(int i=0; i<rows(); i++)
				for(int j=0; j<cols() ; j++)
					board[i][j] = new Square();
		}
		
		/**
		 * Establece el numero de minas del tablero
		 * @param numberOfMines
		 */
		private void setNumberOfMines(int numberOfMines) {
			int maxLim = cols()*rows();
			ArgumentCheck.checkNumber(numberOfMines, 0, maxLim, "Numero de minas no valido");
			this.numberOfMines = numberOfMines;
		}
		
		/**
		 * Devuelve el numero de minas del tablero
		 * @return el numero de minas
		 */
		public int getNumberOfMines() {
			return numberOfMines;
		}

		/**
		 * Establece la accion principal para cada casilla del tablero
		 */
		private void setActions() {
			for(int i=0; i<rows(); i++)
				for(int j=0; j<cols() ; j++) {
					if(board[i][j].hasMine())
						board[i][j].setAction(new BlowUpAction(this));
					else if(board[i][j].isEmpty())
						board[i][j].setAction(new ClearAction(getNeightbouringSquares(j, i)));
					else 
						board[i][j].setAction(new NullAction());
				}
		}
		
		/**
		 *  Metodo getState 
		 * @return true/false <--> no ha habido explosiones/reventó algo
		 */
		public boolean getState() {
			return state;
		}
		
		/**
		 * Metodo isExploted
		 * @return true/false <--> si ha explotado alguna mina/caso contrario
		 */
		public boolean isExploded() {
			return !state;
		}
		
		/**
		 * Sitúa al estado interno del tablero como explotado
		 */
		public void markAsExploded() {
			setState(false);
		}

		/**
		 * Si no está descubierta, descubre la casilla de coordenadas (x, y)
		 * @param x	Coordenada x del tablero
		 * @param y Coordenada y del tablero
		 */
		public void stepOn(int x, int y) {
			ArgumentCheck.checkCoords(x, y, 0, cols() -1, rows()-1);
			board[y][x].stepOn();
		}
		
		/**
		 * Si no hay ya una bandera y la casilla no está descubierta, coloca
		 * una bandera en lacasilla de coordenadas (x, y)
		 * @param x Coordenada x del tablero
		 * @param y Coordenada y del tablero
		 */
		public void flag(int x, int y) {
			ArgumentCheck.checkCoords(x, y, 0, cols() -1, rows()-1);
			if(getFlagsLeft()>0)
				board[y][x].flag();
		}

		/**
		 * Si hay una bandera, retira una bandera en la casilla de coordenadas (x, y).
		 * @param x Coordenada x del tablero
		 * @param y Coordenada y del tablero
		 */
		public void unflag(int x, int y) {
			ArgumentCheck.checkCoords(x, y, 0, cols() -1, rows()-1);
			board[y][x].unflag();
		}

		/**
		 * Descubre todas las casillas del tablero.
		 */
		public void unveil() {
			for(Square[] row: board)
				for(Square s: row)
					s.open();
		}
		
		/**
		 * Devuelve el Numero de casillas actual con banderas 
		 * @return el Numero de casillas actual con banderas
		 */
		private int getNumberOfFlagsNow() {
			int number = 0;
			for(Square[] row: board)
				for(Square s: row)
					if(s.hasFlag())
						number++;
			return number;
		}
		
		/**
		 * Devuelve el número de banderas que aún no han sido colocadas.
		 * @return
		 */
		public int getFlagsLeft() {
			return getNumberOfMines()-getNumberOfFlagsNow();
		}
		
		/**
		 * Devuelve el numero de minas que tienen bandera
		 * @return el numero de minas que tienen bandera
		 */
		private int getMinesFlagged() {
			int number = 0;
			for(Square[] row: board)
				for(Square s: row)
					if(s.hasFlag() && s.hasMine())
						number++;
			return number;
		}
		
		/**
		 * Devuelve el número de minas que aún no han sido cubiertas con una bandera
		 * @return el número de minas sin marcar
		 */
		public int getMinesLeft() {
			return getNumberOfMines()-getMinesFlagged();
		}

		/**
		 * Devuelve una casilla aleatoria que no contenga mina
		 * @return una casilla aleatoria que no sea mina
		 */
		private Square getEmptyRandomSquare() {
			Random r = new Random();
			Square s = null;
			do {
				s = (Square) board[r.nextInt(rows())][r.nextInt(cols())];
			} while (s.hasMine());
			
			return s;
			
		}
		
		/**
		 * Establece aleatoriamente las minas
		 * @param nMines Numero de minas a plantar
		 */
		private void putMines() {
			for(int i=0; i<getNumberOfMines(); i++)
				getEmptyRandomSquare().putMine();
		}
		
		/**
		 * Devuelve las casillas vecinas, de la indicada por x e y, en el tablero
		 * @param x Coordenada x de la casilla
		 * @param y Coordenada y de la casilla
		 */
		private List<Square> getNeightbouringSquares(int x, int y) {
			ArgumentCheck.checkCoords(x, y, 0, cols() -1, rows()-1);
			
			List<Square> neigthbours = new ArrayList<Square>();
			for(int i=y-1; i<=y+1; i++) {
	            for(int j=x-1; j<=x+1; j++) {
	                    if(i==y && j==x)
	                        continue;
	                    if(i>=0 && i<rows() && j>=0 && j<cols() ) 
	                        neigthbours.add(board[i][j]);
	            }
			}
			return neigthbours;
		}
		
		/**
		 * Devuelve el numero de minas alrededor de la casilla indicada por (x, y)
		 * @param x Coordenada x de la casilla
		 * @param y Coordenada y de la casilla
		 * @return numero de minas alrededor
		 */
		private int searchMinesNear(int x, int y) {
			int minesNear = 0;
			for(Square s: getNeightbouringSquares(x, y))
				if(s.getValue() == Square.MINE)
					minesNear++;
			return minesNear;
		}
		
		/**
		 * Establece los valores de las casillas (las pistas) de
		 * todas las casillas del tablero
		 * - ES NECESARIO QUE LAS MINAS YA HAYAN SIDO COLOCADAS -
		 */
		private void setValue() {
			for(int i=0; i<rows(); i++) {
	            for(int j=0; j<cols(); j++) {
	            	if(!board[i][j].hasMine()) 
	            		board[i][j].setValue(searchMinesNear(j, i));
	            }
			}
		}
		
		/**
		 * Devuelve un array de caracteres que representa el estado del tablero de juego
		 * @return un array de caracteres
		 */
		public char[][] getStatus() {
			char[][] statusBoard = new char[rows()][cols()];
			for(int i=0; i<rows(); i++)
				for(int j=0; j<cols(); j++)
					statusBoard[i][j] = board[i][j].getStateIcon();
			return statusBoard;
		}
		
		/**
		 * Devuelve una referencia al array de Square manejado internamente
		 * @return
		 */
		public Square[][] getSquaresForTest() {
			return board;
		}
		
		/**
		 * Busca una casilla vacía al azar y ejecuta stepOn sobre ella 
		 * para descubrir una isla. La casilla escogida al azar no
		 * podrá ser una esquina del tablero.
		 */
		public void uncoverWelcomeArea() {
			if(canOpenWelcomeArea()) {
				int x, y = 0;
				do {
					x = new Random().nextInt(cols());
					y = new Random().nextInt(rows());
					
				} while(board[y][x].getState()!=Square.State.CLOSED ||
								isCorner(x, y) || !board[y][x].isEmpty());
				board[y][x].stepOn();
			}
		}
		
		/**
		 * Comprueba si en el tabero hay alguna casilla vacia y cerrada y que no es esquina
		 * @return true/false <--> hay al menos una/si no hay
		 */
		private boolean canOpenWelcomeArea() {
			for(int i=0; i<rows(); i++)
				for(int j=0; j<cols(); j++) 
					if(board[i][j].getState()==Square.State.CLOSED)
						if(!isCorner(j, i) && board[i][j].isEmpty())
							return true;
			return false;
				
		}

		/**
		 * Indica si la posicion introducida por parametros x y es una esquina
		 * del tablero
		 * @param x Coord x del tablero
		 * @param y Coord y del tablero
		 * @return true/false <--> es esquina/no lo es
		 */
		private boolean isCorner(int x, int y) {
			ArgumentCheck.checkCoords(x, y, 0, cols()-1, rows()-1);
			if(x==0 && y==0)
				return true;
			else if(x==0 && y == rows()-1)
				return true;
			else if(x==cols()-1 && y == 0)
				return true;
			else if(x==cols()-1 && y == rows()-1)
				return true;
			return false;
			
		}
		
		/**
		 * Descubre todas las casillas del tablero respetando las banderas ya colocadas
		 */
		protected void chouTableWin() {
			for(Square[] row: board)
				for(Square s: row)
					if(s.getState() == Square.State.CLOSED)
						s.open();
		}
		
	}

