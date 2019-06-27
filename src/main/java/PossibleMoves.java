import java.util.ArrayList;
import java.util.List;

/**
 * Class containing the list of all possible moves and operators.
 *
 * @author Márton Ákos
 */
public class PossibleMoves {

    public PossibleMoves possibleMoves;

    private List<Move> moves;
    private List<Operator> operators;
    private int[][] board;

    public PossibleMoves(State state){
        this.moves = new ArrayList<>();
        this.operators = new ArrayList<>();

        //BoardStateImpl board = (BoardStateImpl) state.getBoardState();
        BoardState boardState = state.getBoardState();
        this.board = boardState.getBoard();


        for(int i = 0; i < Board.ROW_SIZE; i++){
            for(int j = 0; j < Board.COLUMN_SIZE; j++){
                if(getRowPosition(j) == i) {
                    this.operators.add(new Operator(i, j));
                }
                for(int k = 0; k < Board.PLAYERS.length; k++){
                    this.moves.add((new Move(i,j, Board.PLAYERS[k])));
                }
            }
        }
    }

    /**
     * Returns the bottom empty value in based on provided Column
     *
     * @param col the provided column
     * */
    public int getRowPosition(int col) {
        int rowPosition = -1;
        for (int row=0; row<6; row++) {
            if (board[row][col] == 0) {
                rowPosition = row;
            }
        }
        return rowPosition;
    }


    /**
     * Returns the instance of this class.
     *
     * @return the instance of this class
     */
    public PossibleMoves getPossibleMoves(State state){
        if(possibleMoves == null){
            possibleMoves = new PossibleMoves(state);
        }
        return possibleMoves;
    }

    public List<Move> getMoves(){
        return this.moves;
    }

    public List<Operator> getOperators(){
        return this.operators;
    }
}
