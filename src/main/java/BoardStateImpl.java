
/**
 * Class implementing the {@code State} interface.
 *
 * The state includes a {@code BoardState} and the current player.
 *
 * @author Márton Ákos
 * */
public class BoardStateImpl implements State{

    private BoardState boardState;
    private int player;


    /**
     * Constructs a state based on the parameters.
     *
     * @param boardState the board state
     * @param player the player
     */
    public BoardStateImpl(BoardState boardState, int player){
        this.boardState = boardState;
        this.player = player;
    }

    /**
     * Constructs a copy of a state.
     *
     * @param from the state to copy
     */
    public BoardStateImpl(BoardStateImpl from) {
        this.boardState = new Board((Board) from.boardState);
        this.player = from.player;
    }

    @Override
    public BoardState getBoardState(){
        return boardState;
    }

    @Override
    public int getPlayer(){
        return player;
    }

    @Override
    public Integer getWinner(){
        return Operator.getPrevPlayer(this);
    }

    @Override
    public boolean isOver(){

        return boardState.checkGameOver();
    }
}
