import java.util.Scanner;

public class Game {
	private Board board;
	private GameState currentState;
	private Seed currentPlayer;
	private static Scanner in_1 = new Scanner (System.in);
	private static Scanner in_2 = new Scanner (System.in);
	private static Scanner in_3 = new Scanner (System.in);
	private String Starter;
	private int round = 1, X_wins = 0, O_wins = 0;
	
	public Game() {
		boolean endGame = false;
		while(!endGame) {
			System.out.println("Welcome to Tic Tac Toe!");
			System.out.println("--------Round " + String.valueOf(round) + "--------");
			System.out.println("--------X " + String.valueOf(X_wins) + ":" + String.valueOf(O_wins) + " O--------");
			System.out.print("Who goes first, X or O?"); 
			board = new Board();
			boolean validinput = false;
			while (!validinput) {
				Starter = in_1.next();
				if (Starter.equals("X") || Starter.equals("O")||Starter.equals("x") || Starter.equals("o")) {
					validinput = true;
				}
				else {
					System.out.print("Invalid input! Please type again!");
				}
			}
			
			initGame();
			board.show();
			do {
				playerMove(currentPlayer);
				board.show();
				updateGame(currentPlayer);
				endGame = checkResult(currentState, endGame);
				currentPlayer = (currentPlayer == Seed.O)?Seed.X:Seed.O;
			}while (currentState == GameState.PLAYING);
		}
	}
	
	public void initGame() {
		board.init();
		
		currentPlayer = (Starter.equals("X") || Starter.equals("x"))? (Seed.X):(Seed.O);
		currentState = GameState.PLAYING;
	}
	
	public void playerMove(Seed seed) {
		boolean validInput = false; //maintain every move is valid
		do {
			if (seed == Seed.X) {
				System.out.print("Player X, please enter the coordinates of your move: ");
			}
			else {
				System.out.print("Player O, please enter the coordinates of your move: ");
			}
			int row = in_2.nextInt() - 1;
			int col = in_2.nextInt() - 1;
			// make sure the coordinates of every move is between (1,1)-(3,3)
			if (row >= 0 && row < Board.ROWS && col >= 0 && col < Board.COLS && board.cells[row][col].content.equals(Seed.EMPTY)) {
				board.cells[row][col].content = seed;
				board.currentRow = row;
				board.currentCol = col;
				validInput = true;
			}
			else {
				System.out.println("This move is invalid. Please try again.");
			}
		}while (!validInput);
	}
	
	public void updateGame(Seed seed) {
		if (board.isWon(seed)) {
			currentState = (seed == Seed.X)?GameState.X_WON:GameState.O_WON;
		}
		else if (board.isDraw()) {
			currentState = GameState.STALEMATE;
		}
	}
	
	public boolean checkResult(GameState currentState, boolean endGame) {
		if (currentState == GameState.X_WON) {
			System.out.println("Player X Won!");
			X_wins++;
		}
		else if (currentState == GameState.O_WON) {
			System.out.println("Player O Won!");
			O_wins++;
		}
		else if (currentState == GameState.STALEMATE) {
			System.out.println("Nobody Won!");
		}
		else return endGame; // A round doesn't end yet.
		
		// Ask players whether or not they want another game.
		System.out.println("Want another round?(Yes/No)");
		String answer = in_3.next();
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
			round++;
			return endGame;	
		}
		else if (answer.equals("No") || answer.equals("no")){
			System.out.println("Thanks for playing! See you next time!");
			System.out.println("---------------");
			System.out.println("| Game Results |");
			System.out.println("---------------");
			System.out.println("| X wins|   " + String.valueOf(X_wins) + "  |");
			System.out.println("| O wins|   " + String.valueOf(O_wins) + "  |");
			System.out.println("| Draws |   " + String.valueOf(round - X_wins - O_wins) + "  |");
			System.out.println("---------------");
			endGame = true;
		}
		return endGame;
	}
}
