import java.util.List;

public class DecisionMaker {

    private final int numberOfPlayers;

    public DecisionMaker(int numberOfPlayers){
        this.numberOfPlayers = numberOfPlayers;
    }

    public Operator maxN(State state, List<Operator> operators, int bound, Heuristics heuristics){
        return getMaxNRecord(state, operators, bound, heuristics).getOperator();
    }

    private Record getMaxNRecord(State state, List<Operator> operators, int bound, Heuristics heuristics){

        if(state.isOver()){
            return new Record(null, rateRecordGoodness(state, heuristics));
        }

        if(bound == 0){
            return new Record(null, rateBoundGoodness(state, heuristics));
        }

        Record max = null;
        for(Operator o : operators){
            if(o.isValid(state)){
                State newState = o.apply(state);
                Record newRecord = getMaxNRecord(newState,operators,bound -1 , heuristics);
                newRecord.setOperator(o);

                if(max == null || newRecord.getGoodness(state.getPlayer()) > max.getGoodness(state.getPlayer())){
                    max = newRecord;
                }
            }
        }

        return max;
    }

    private int[] rateRecordGoodness(State state, Heuristics heuristics){

        int[] resultRecord = new int[this.numberOfPlayers];
        if(state.getWinner() != null){
            for (int i = 0; i < this.numberOfPlayers; i++){
                if((int) state.getWinner() == i+1){
                    resultRecord[i] = heuristics.getMax();
                } else {
                    resultRecord[i] = -1 * heuristics.getMax();
                }
            }
        }
        return resultRecord;

    }

    private int[] rateBoundGoodness(State state, Heuristics heuristics){
        int[] resultRecord = new int[this.numberOfPlayers];
        for (int i = 0; i < this.numberOfPlayers; i++){
            resultRecord[i] = heuristics.getValue(state, i);
        }
        return resultRecord;
    }


}
