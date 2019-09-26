
public class Cell {
	Seed content;
	int row, col;
	
	public Cell(int row, int col) {
		this.row = row;
		this.col = col;
		clear(); //overrode method
	}

	public void clear() {
		// TODO Auto-generated method stub
		content = Seed.EMPTY;
	}
	
	public char show() {
		switch (content) {
		case X: 
			return 'X';
		case O: 
			return 'O';
		case EMPTY: 
			return ' ';
		}
		return 0;
	}
}
