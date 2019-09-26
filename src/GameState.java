//Except game over,there are all the possible game states.
public enum GameState {
	PLAYING, STALEMATE, X_WON, O_WON
	// When the program is extended to be able to be played by 0 or 1 person, 
	// the variable X_WON and O_WON no longer stand by X and O.
	// They can be seen as "whether Player 1 won" and "whether Player 2 won".
}
