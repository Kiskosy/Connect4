
/**
 * Class implementing the {@code BoardState} interface
 * The board of the game is a 6*7 matrix of integer values from 0, to 3.
 * The number 0 means that the cell of the board is empty, the number 1 means player, while
 * the number 2 and 3 means AI
 *
 * @author Márton Ákos
 * */
public class Board implements BoardState{

    /**
     * The row size of the Board
     * */
    public static final int ROW_SIZE = 6;
    /**
     * The column size of the Board
     * */
    public static final int COLUMN_SIZE = 7;

    /**
     * Symbol of the players
     * */
    public static final int[] PLAYERS = {1, 2, 3};

    public int[][] board;

    /**
     * Constructs an empty board
     * */
    public Board(){
        this.board = new int[ROW_SIZE][COLUMN_SIZE];
    }

    /**
     * Constructs a board state based on another one. Only one cell is different
     * @param old the another board state
     * @param i the row coordinate of the different cell
     * @param j the column coordinate of the differenct cell
     * @param k the new symbol in the different cell
     * */
    public Board(Board old, int i, int j, int k){
        this(old);
        this.board[i][j] = k;
    }

    /**
     * Constructs a copy of the provided board state
     *
     * @param from the board which gets copied
     * */
    public Board(Board from){
        this.board = new int[from.board.length][from.board.length];
        for(int i = 0; i < this.board.length; i++){
            this.board[i] = from.board[i].clone();
        }
    }

    /**
     * Returns the value of a cell based on provided row and column coordinates.
     *
     * @param i row coordinate
     * @param j column coordinate
     * @return the value of the cell, or -1 if out of bound
     * */
    public int get(int i, int j){
        try {
            return board[i][j];
        } catch (ArrayIndexOutOfBoundsException ex){
            return -1;
        }
    }

    /**
     * Check if theres any 4 same cells in vertically, horizontally, átlósan
     *
     * @return true if game ended
     * */

    public boolean checkWinState() {

        // Check for 4 consecutive checkers in a row, horizontally.
        for (int i=5; i>=0; i--) {
            for (int j=0; j<4; j++) {
                if (board[i][j] == board[i][j+1]
                        && board[i][j] == board[i][j+2]
                        && board[i][j] == board[i][j+3]
                        && board[i][j] != 0) {

                    return true;
                }
            }
        }

        // Check for 4 consecutive checkers in a row, vertically.
        for (int i=5; i>=3; i--) {
            for (int j=0; j<7; j++) {
                if (board[i][j] == board[i-1][j]
                        && board[i][j] == board[i-2][j]
                        && board[i][j] == board[i-3][j]
                        && board[i][j] != 0) {

                    return true;
                }
            }
        }

        // Check for 4 consecutive checkers in a row, in descending diagonals.
        for (int i=0; i<3; i++) {
            for (int j=0; j<4; j++) {
                if (board[i][j] == board[i+1][j+1]
                        && board[i][j] == board[i+2][j+2]
                        && board[i][j] == board[i+3][j+3]
                        && board[i][j] != 0) {

                    return true;
                }
            }
        }

        // Check for 4 consecutive checkers in a row, in ascending diagonals.
        for (int i=0; i<6; i++) {
            for (int j=0; j<7; j++) {
                if (canMove(i-3,j+3)) {
                    if (board[i][j] == board[i-1][j+1]
                            && board[i][j] == board[i-2][j+2]
                            && board[i][j] == board[i-3][j+3]
                            && board[i][j] != 0) {

                        return true;
                    }
                }
            }
        }

        return false;

    }

    @Override
    public int[][] getBoard(){
        return board;
    }

    /**
     * Returns if the current board state is draw
     * Draw is if there is no 0 cell on the board
     *
     * @return true if draw
     * */

    @Override
    public boolean checkGameOver() {

        if (checkWinState()) {
            return true;
        }

        for(int row=0; row<6; row++) {
            for(int col=0; col<7; col++) {
                if(board[row][col] == 0) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Check if out of bound a row or cell
     *
     * @return true if out of bound, else false
     * */
    public boolean canMove(int row, int col) {
        if ((row <= -1) || (col <= -1) || (row > 5) || (col > 6)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {

        String returnString = "";
        returnString = ("| 1 | 2 | 3 | 4 | 5 | 6 | 7 |");
        returnString += "\n";
        for (int i=0; i<6; i++) {
            for (int j=0; j<7; j++) {
                if (j!=6) {
                    if (board[i][j] == 1) {
                        returnString += ("| " + "X" + " ");
                    } else if (board[i][j] == 2) {
                        returnString += ("| " + "O" + " ");
                    } else if (board[i][j] == 3){
                        returnString += ("| " + "H" + " ");
                    } else {
                        returnString += ("| " + "-" + " ");
                    }
                } else {
                    if (board[i][j] == 1) {
                        returnString += ("| " + "X" + " |");
                        returnString += "\n";
                    } else if (board[i][j] == 2) {
                        returnString += ("| " + "O" + " |");
                        returnString += "\n";
                    } else if (board[i][j] == 3){
                        returnString += ("| " + "H" + " |");
                        returnString += "\n";
                    } else {
                        returnString += ("| " + "-" + " |");
                        returnString += "\n";
                    }
                }
            }
        }

        return returnString;
    }

    public static String getPlayerSymbol(int number) {
        switch (number) {
            case 0:
                return "- ";
            case 1:
                return "X ";
            case 2:
                return "O ";
            case 3:
                return "H ";
        }
        return "  ";
    }
}
