/**
 * Interface representing the state of the game's board.
 */
public interface BoardState {

    /**
     * Returns if the state of the board is final.
     * 
     * @return {@code true} if the state of the board is considered final
     * {@code false} otherwise
     */
    public boolean checkGameOver();

    int[][] getBoard();
}
