import java.util.Scanner;

public class Player {
	private String name;
	int wins, losses, draws;
	Seed mark;
	private static Scanner in = new Scanner(System.in);
	
	Player() {
	}
	
	Player(String name) {
		this.name = name;
	}

	public int[] playerMove(Seed seed, Board board) {
		int row, col;
		int[] move = new int[2];
		boolean validInput = false; //maintain every move is valid
		do {
			System.out.print(this.get_name() +  ", please enter the coordinates of your move: ");
			row = in.nextInt() - 1;
			col = in.nextInt() - 1;
			// make sure the coordinates of every move is between (1,1)-(3,3)
			if (row >= 0 && row < board.get_row() && col >= 0 && col < board.get_col() && board.cells[row][col].content.equals(Seed.EMPTY)) {
				board.cells[row][col].content = seed;
				board.currentRow = row;
				board.currentCol = col;
				validInput = true;
			}
			else {
				System.out.println("This move is invalid. Please try again.");
			}
		}while (!validInput);
		move[0] = row;
		move[1] = col;
		
		return move;
	}
	
	public String get_name() {
		return name;
	}
	
	public void setWins() {
		this.wins++;
	}
	
	public void setLosses() {
		this.losses++;
	}
	
	public void setDraws() {
		this.draws++;
	}
	
	public int getWins() {
		return wins;
	}
	
	public int getLosses() {
		return losses;
	}
	
	public int getDraws() {
		return draws;
	}
	
	@Override
    public boolean equals (Object object) {
        boolean result = false;
        if (object == null || object.getClass() != getClass()) {
            result = false;
        } else {
            Player p = (Player) object;
            if (this.name == p.get_name()) {
                result = true;
            }
        }
        return result;
	}
}