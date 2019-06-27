import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;



/*
 * Main class for the game
 *
 * @author Márton Ákos
 * */
public class Main {

    /**
     * Bound value used for computer player 1.
     */
    public static final int BOUND_1 = 3;
    /**
     * Bound value used for computer player 1.
     */
    public static final int BOUND_2 = 3;
    /**
     * Number of players
     */
    public static final int NUMBER_OF_PLAYERS = 3;

    /**
     * Starts the three-player 6x7 connect-four.
     *
     * There are two computer players playing the game. It uses the maxN algorithm
     * to recommend steps.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {

        try {

            DecisionMaker dm = new DecisionMaker(NUMBER_OF_PLAYERS);

            Heuristics heuristics = new Heuristics();

            State state = new BoardStateImpl(new Board(), Board.PLAYERS[0]);

            System.out.println("Start...\n");
            System.out.println(state.getBoardState());

            while (!state.isOver()) {
                System.out.println(Board.getPlayerSymbol(state.getPlayer()) + "tesz\n");

                List<Operator> applicable = getPossibleMoves(state);

                if (state.getPlayer() > 1) {
                    state = aiTurn(dm, heuristics, state, applicable);
                } else {
                    state = playerTurn(state, applicable);
                }

                System.out.println(state.getBoardState());
            }
            boolean isDraw = state.getWinner() == 0;

            if (isDraw) {
                System.out.println("döntetlen");
            } else {
                System.out.println(Board.getPlayerSymbol(state.getWinner()) + " nyert");
            }

        } catch (Exception ex){
            ;
        }



    }


    /**
     * Returns the new state with a computer player's step applied.
     *
     * The step (operator) is chosen with the maxN algorithm.
     *
     * @param dm a decision maker object, that has some public methods returning an operator
     * @param heuristics a {@code Heuristics} object
     * @param state the current state
     * @param applicable a list of operators that can be applied to the state
     * @return the new state with the computer's step applied
     */
    public static State aiTurn(DecisionMaker dm, Heuristics heuristics, State state, List<Operator> applicable) {
        int bound = state.getPlayer() == 2 ? BOUND_1 : BOUND_2;
        Operator next = dm.maxN(new BoardStateImpl((BoardStateImpl) state) , applicable, bound, heuristics);
        state = next.apply(state);
        return state;
    }

    /**
     * Returns the new state with the player's step applied.
     *
     * @param state the current state
     * @param applicable a list of operators that can be applied to the state
     * @return the new state with the player's step applied
     *
     * @throws IOException if there are some errors with reading from the standard input
     */
    public static State playerTurn(State state, List<Operator> applicable) throws IOException {

        Scanner in = new Scanner(System.in);
        int moveOfPlayer = in.nextInt();

        Operator chosen = applicable.stream().filter(o -> {
            int oj = ((Operator) o).getJ();

            return oj == moveOfPlayer-1;
        }).findFirst().get();

        return chosen.apply(state);
    }


    /**
     * Returns the list of the possible operators to the current state.
     *
     * @param state the current state
     * @return the list of the possible operators
     */
    public static List<Operator> getPossibleMoves(State state) {
        PossibleMoves moves = new PossibleMoves(state);
        return moves.getPossibleMoves(state).getOperators().stream()
                .filter(o -> o.isValid(state))
                .collect(Collectors.toList());
    }
}
