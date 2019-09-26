// It is used to define all possible situations while playing.
// For example, to distinguish whether there is a winner, to re-initialize the game board when players decide to start again.
// From my point of view, a judge is not only in charge of judging the result of match, he/she also needs to take care of the board.
import java.util.Scanner;

public class Judge {
	private Board board;
	Cell[][] cells;
	GameState currentState;
	Seed currentPlayer;
	private int scale;
	private int[] rows;
    private int[] cols;
    private static Scanner in = new Scanner (System.in);
    private int diag;
    private int xdiag;
    public int X_wins = 0, O_wins = 0;
	
	public Judge(int scale) {
		this.scale = scale;
	}
	public void initGame(Player player1, Player player2, String choice, GameState currentState) {
		board = new Board(scale);
		board.init();
		player1.mark = (choice.equals("X") || choice.equals("x"))? (Seed.X):(Seed.O);
		player2.mark = (choice.equals("X") || choice.equals("x"))? (Seed.O):(Seed.X);
		currentPlayer = choice == player1.mark.toString() ? (Seed.X):(Seed.O);
		currentState = GameState.PLAYING;
	}
	
	public boolean updateGame(Seed seed, int row, int col, boolean endGame, GameState currentState) {
		if (isWon(seed,row, col) == Seed.X) {
			currentState = GameState.X_WON;
			System.out.println("Player X Won!");
			X_wins++;
		}
		else if (isWon(seed, row, col) == Seed.O) {
			currentState = GameState.O_WON;
			System.out.println("Player O Won!");
			O_wins++;
		}
		else if (isDraw()) {
			currentState = GameState.STALEMATE;
			System.out.println("Nobody Won!");
		}
		else return endGame;
		System.out.println("Want another round?(Yes/No)");
		String answer = in.next();
		boolean valid_input = false;
		while (!valid_input) {
			if (answer.equals("Yes") || answer.equals("yes") || answer.equals("No") || answer.equals("no")) {
				valid_input = true;
			}
			else {
				System.out.print("Invalid input! Please type again!");
			}
		}
		if (answer.equals("Yes") || answer.equals("yes")) {
			return endGame;	
		}
		else if (answer.equals("No") || answer.equals("no")){
			endGame = true;
		}
		return endGame;
	}
	
	Seed isWon(Seed seed, int row, int col) {
		rows = new int[scale];
        cols = new int[scale];
        diag = 0;
        xdiag = 0;
        
        int count = seed == Seed.X ? 1 : -1;
        rows[row] += count;
        cols[col] += count;
         
        if (row == col) {
            diag += count;
        }
             
        // X-diagonal
        if (row + col == scale - 1) {
            xdiag += count;
        }
        
     // If any of them equals to n, return 1
        if (Math.abs(rows[row]) == scale || 
            Math.abs(cols[col]) == scale || 
            Math.abs(diag) == scale || 
            Math.abs(xdiag) == scale) {
            return count > 0? Seed.X : Seed.O;
        }
         
        return Seed.EMPTY;
	}
	
	public boolean isDraw() {
		for (int row = 0; row < scale; ++row) {
			for (int col = 0; col < scale; ++col) {
				if (board.cells[row][col].content == Seed.EMPTY) {
					return false;
				}
			}
		}
		return true;
	}
	
	public int get_X_wins() {
		return X_wins;
	}
	
	public int get_O_wins() {
		return O_wins;
	}
}
