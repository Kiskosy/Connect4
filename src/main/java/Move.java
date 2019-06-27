
/**
 * Class is responsible for move
 *
 * @author Márton Ákos
 * */

public class Move {

    private int i;
    private int j;
    private int k;

    /**
     * Constructs a step implementation based on the parameters.
     *
     * This means, that we put the {@code k} symbol in the {@code i}-th row and {@code j}-th column.
     *
     * @param i row
     * @param j column
     * @param k symbol
     */
    public Move(int i, int j, int k){
        this.i = i;
        this.j = j;
        this.k = k;
    }

    public boolean isValid(BoardState boardState){
        if(boardState instanceof Board) {
            return ((Board) boardState).get(i, j) == 0;
        }
        return false;
    }

    public BoardState step(BoardState boardState){
        if(boardState instanceof Board){
            return new Board((Board) boardState, i, j, k);
        }
        return null;
    }

    public int getI(){
        return i;
    }

    public int getJ(){
        return j;
    }

    public int getK(){
        return k;
    }

}
