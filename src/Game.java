import java.util.Scanner;
import java.util.ArrayList;

public class Game {
	private Board board;
	private GameState currentState;
	private Seed currentMarker;
	private Player currentPlayer = new Player();
	private static Scanner in = new Scanner (System.in);
	private int round = 1; // Game always starts from the 1st round
	private boolean validinput = false;
	private int scale;
	Player[] p = new Player[2];  //Temporary storage for the data of the 2 players at the present round
	private ArrayList<String> name_list = new ArrayList<String>();
	private ArrayList<Player> users = new ArrayList<Player>(); //Store the data of players. ArrayList allows the number bigger than 2
	
	public Game() {
		boolean endGame = false; // When endGame is true, it means players no longer wanna play
		System.out.println("Welcome to Tic Tac Toe!");
		while(!endGame) {
			System.out.println("--------Round " + String.valueOf(round) + "--------");
			System.out.print("Select the scale of board you want: "); // Players can customize the size of gameboard
			boolean valid_number = false;
			while (!valid_number) {
				String scale_script;
				//Make sure the input here is integer
				scale_script = in.next();
				if (scale_script.matches("[0-9]+") ) {
					valid_number = true;
					this.scale = Integer.valueOf(scale_script);
				}
				else {
					System.out.println("Invalid number input! Please type again!");
				}
			}
			System.out.print("Please type in Player 1's name: ");
			String name1 = in.next();
			p[0] = new Player(name1);
			if (name_list.toString().indexOf(name1.toString()) == -1) { // Make sure only the new-coming players are allowed to be recorded in the data list.
				users.add(p[0]);
			}
			System.out.print("Please type in Player 2's name: ");
			String name2 = in.next();
			p[1] = new Player(name2);
			if (name_list.toString().indexOf(name2.toString()) == -1) {
				users.add(p[1]);
			}
			System.out.println("Who goes first, " + p[0].get_name() + " or " + p[1].get_name() + "?"); 
			System.out.println("1: " + p[0].get_name());
			System.out.println("2: " + p[1].get_name());
			String option = in.next();
			valid_number = false;
			while (!valid_number) {
				if (option.equals("1") || option.equals("2") || option.equals(p[0].get_name()) || option.equals(p[1].get_name())) {
					valid_number = true;
				}
				else {
					System.out.println("Invalid number input! Please type again!");
				}
			}
			System.out.print("--------" + p[0].get_name() + " " + String.valueOf(p[0].getWins()));
			System.out.println(" : " + String.valueOf(p[1].getWins()) + " " + p[1].get_name() + "--------");
			validinput = false;
			while (!validinput) {
				if (option.equals("1") || option.equals(p[0].get_name())) {
					System.out.print("Which marker do you want, " + p[0].get_name() + "? (X/O)");
					validinput = true;
				}
				else if (option.equals("2") || option.equals(p[1].get_name())){
					System.out.print("Which marker do you want, " + p[1].get_name() + "? (X/O)"); 
					validinput = true;
				}
				else {
					System.out.print("Invalid input! Please type again!"); 
				}
			}
			validinput = false;
			String Starter = in.next();
			while (!validinput) {
				if (Starter.equals("X") || Starter.equals("O")||Starter.equals("x") || Starter.equals("o")) {
					validinput = true;
				}
				else {
					System.out.print("Invalid input! Please type again!");
				}
			}
			board = new Board(scale);
			currentPlayer = option.equals("1") ? p[0] : p[1];
			initGame(Starter);
			board.show();
			do {
				int[] move = new int[2];
				move = currentPlayer.playerMove(currentMarker, board);
				board.show();
				updateGame(move[0],move[1]);
				endGame = checkResult(currentState, endGame, currentPlayer, p);
				currentMarker = (currentMarker == Seed.O) ? Seed.X : Seed.O;
				currentPlayer = (currentPlayer == p[0]) ? p[1] : p[0];
			}while (currentState == GameState.PLAYING);
		}
	}
	
	public void initGame(String input) {
		board.init();
		
		currentMarker = (input.equals("X") || input.equals("x"))? (Seed.X):(Seed.O);
		currentState = GameState.PLAYING;
	}
	
	public void updateGame(int row, int col) {
		if (board.isWon(row, col, scale, currentPlayer, p)) {
			currentState = (currentMarker == Seed.X)?GameState.X_WON:GameState.O_WON;
		}
		else if (board.isDrawn()) {
			currentState = GameState.STALEMATE;
		}
	}
	
	public boolean checkResult(GameState currentState, boolean endGame, Player currentPlayer, Player[] p) {
		if (currentState == GameState.X_WON) {
			if (currentPlayer.equals(p[0])) {
				System.out.println(p[0].get_name() + " won!");
				p[0].setWins();
				p[1].setLosses();
			}
			else if (currentPlayer.equals(p[1])) {
				System.out.println(p[1].get_name() + " won!");
				p[1].setWins();
				p[0].setLosses();
			}
		}
		else if (currentState == GameState.O_WON) {
			if (currentPlayer.equals(p[0])) {
				System.out.println(p[0].get_name() + " won!");
				p[0].setWins();
				p[1].setLosses();
			}
			else if (currentPlayer.equals(p[1])) {
				System.out.println(p[1].get_name() + " won!");
				p[1].setWins();
				p[0].setLosses();
			}
		}
		else if (currentState == GameState.STALEMATE) {
			System.out.println("Nobody Won!");
			p[0].setDraws();
			p[1].setDraws();
		}
		else return endGame; // A round doesn't end yet.
		
		// Ask players whether or not they want another game.
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
			round++;
			return endGame;	
		}
		else if (answer.equals("No") || answer.equals("no")){
			System.out.println("Thanks for playing! See you next time!");
			System.out.println("----------------------");
			System.out.println("|    Game Results    |");
			System.out.println("----------------------");
			System.out.println("|Players | W | D | L |");
			System.out.println("----------------------");
			for (int i = 0; i < users.size(); i++) {
				System.out.println("| " + users.get(i).get_name() + " | " + String.valueOf(users.get(i).getWins()) + " | " 
						+ String.valueOf(users.get(i).getDraws()) + " | " + String.valueOf(users.get(i).getLosses()) + " |");
			}
			System.out.println("----------------------");
			endGame = true;
		}
		return endGame;
	}
}
