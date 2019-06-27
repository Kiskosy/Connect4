import java.util.List;

/**
 * Class is responsible for creating the operator
 * which is ued for moving on the board
 *
 * @author Márton Ákos
 * */
public class Operator {

    private int i;
    private int j;

    /**
     * Constructs an operatir instance with the given row and column indexes
     *
     * @param i rov index
     * @param j column index
     * */

    public Operator(int i, int j){
        this.i = i;
        this.j = j;
    }

    public boolean isValid(State state){
        Move move = this.findNextMove(state);

        return move.isValid(state.getBoardState());
    }

    public State apply(State state){
        Move move = this.findNextMove(state);

        BoardState newState = move.step(state.getBoardState());
        int player = getNextPlayer(state);

        return new BoardStateImpl(newState,player);
    }

    public int getI(){
        return i;
    }

    public int getJ(){
        return j;
    }

    private Move findNextMove(State state){
        PossibleMoves moves2 = new PossibleMoves(state);
        List<Move> moves = moves2.getPossibleMoves(state).getMoves();

        for(Move move: moves){
            if(move.getI() == i && move.getJ() == j && move.getK() == state.getPlayer()){
                return move;
            }

        }

        return null;
    }


    /**
     * Returns the next following player
     *
     * @param state the current state
     * @return the number of the next player
     * */
    public static int getNextPlayer(State state) {
        return state.getPlayer() == 3 ? 1 : state.getPlayer() + 1;
    }

    /**
     * Returns the prev player
     *
     * @param state the current state
     * @return the number of the prev player
     * */
    public static int getPrevPlayer(State state) {
        return state.getPlayer() == 1 ? 3 : state.getPlayer() - 1;
    }

}
