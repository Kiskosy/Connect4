/**
 * Interface for the state.
 */
public interface State {

    /**
     * Returns the current board state object.
     * 
     * @return the board of the game
     */
    BoardState getBoardState();

    /**
     * Returns the player's symbol in the state.
     * 
     * @return the player's symbol, an integer value
     */
    int getPlayer();

    /**
     * Returns if the state's board state indicates that the game is over.
     * 
     * @return {@code true} if the board state indicates that the game is over, {@code false}
     * if not
     */
    boolean isOver();

    /**
     * Returns the winner player's symbol.
     * 
     * @return the winner player's symbol
     */
    Integer getWinner();

}
