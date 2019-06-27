public class Record {

    private Operator operator;
    private int[] goodness;

    public Record(Operator operator, int[] goodness){
        this.operator = operator;
        this.goodness = goodness;
    }

    public Operator getOperator(){
        return operator;
    }

    public int getGoodness(int player){
        return goodness[player -1];
    }

    public void setOperator(Operator operator){
        this.operator = operator;
    }

}
