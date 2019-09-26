
public class Board {
	//One board is expected to be combined with 9 cells.
	public static final int ROWS = 3;
	public static final int COLS = 3;
	
	Cell[][] cells;
	int currentRow, currentCol;
	
	public Board() {
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
	
	public boolean isDraw() {
		for (int row = 0; row < ROWS; ++row) {
			for (int col = 0; col < COLS; ++col) {
				if (cells[row][col].content == Seed.EMPTY) {
					return false;
				}
			}
		}
		return true;
	}
	public boolean isWon(Seed seed) {
		//3 in a row
		boolean row_check = cells[currentRow][0].content == seed && cells[currentRow][1].content == seed 
				&& cells[currentRow][2].content == seed;
		//3 in a column
		boolean col_check = cells[0][currentCol].content == seed && cells[1][currentCol].content == seed 
				&& cells[2][currentCol].content == seed;
		// 3 in the main diagonal line
		boolean diagonal_check_1 = currentRow == currentCol && cells[0][0].content == seed 
				&& cells[1][1].content == seed && cells[2][2].content == seed;
		// 3 in the opposite diagonal line
		boolean diagonal_check_2 = currentRow + currentCol == 2 && cells[0][2].content == seed 
				&& cells[1][1].content == seed && cells[2][0].content == seed;
		return (row_check || col_check || diagonal_check_1 || diagonal_check_2);
	}
	
	public void show() {
		System.out.println("-------------");
		for (int row = 0; row < ROWS; ++row) {
				System.out.print("| " + cells[row][0].show() + " | " + cells[row][1].show() + " | " + cells[row][2].show() + " |");
				System.out.println();
				System.out.println("-------------");
			}
	}
}
