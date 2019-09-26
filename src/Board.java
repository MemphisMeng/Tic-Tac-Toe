
public class Board {
	//One board is expected to be combined with 9 cells.
	public int ROWS;
	public int COLS;
	int[][] rows;
	int[][] cols;
	int[] diag = new int[2];
	int[] xdiag = new int[2];
	
	Cell[][] cells;
	int currentRow, currentCol;
	
	public Board(int scale) {
		this.ROWS = this.COLS = scale;
		rows = new int[2][scale];
		cols = new int[2][scale];
		cells = new Cell[ROWS][COLS];
		for (int row = 0; row < ROWS; ++row) {
			for (int col = 0; col < COLS; ++col) {
				cells[row][col] = new Cell(row, col);
			}
		}
	}
	
	public void init() {
		for (int row = 0; row < ROWS; ++row) {
			for (int col = 0; col < COLS; ++col) {
				cells[row][col].clear();
			}
		}
	}
	
	public boolean isDrawn() {
		for (int row = 0; row < ROWS; ++row) {
			for (int col = 0; col < COLS; ++col) {
				if (cells[row][col].content == Seed.EMPTY) {
					return false;
				}
			}
		}
		return true;
	}
	
	public boolean isWon(int row, int col, int scale, Player currentPlayer, Player[] p) {  // The winning principle
		int P = currentPlayer.equals(p[0]) ? 0 : 1;
		
		rows[P][row] ++;
		cols[P][col] ++;
		
		if (row == col) {
			diag[P] ++;
		}
		
		// The diagonal line of the other side
		if (row + col == scale - 1) {
			xdiag[P] ++;
		}
		
		if (rows[P][row] == scale || 
			cols[P][col] == scale || 
			diag[P] == scale || 
			xdiag[P] == scale ) {
			return true;
		}
		
		return false;
	}
	
	public void show() {
		System.out.print("----");
		for (int i = 0; i < COLS; i++) {
			System.out.print("----");
		}
		System.out.println();
		for (int row = 0; row < ROWS; ++row) {
			System.out.print("| ");
			for (int col = 0; col < COLS; ++col) {
				System.out.print(cells[row][col].show() + " | ");
			}
			System.out.println();
			System.out.print("----");
			for (int j = 0; j < COLS; j++) {
				System.out.print("----");
			}
			System.out.println();
		}
	}
	
	public int get_row() {
		return ROWS;
	}
	
	public int get_col() {
		return COLS;
	}
}
