package space.stemfun.stemfun;

public class UnderLevel {


    private Field field = Field.EMPTY;
    private int questionNum = -1;
    private State questionState = State.LOCKED;
    private State gameState = State.LOCKED;
    private State underState = State.LOCKED;

    public int getQuestionNum() {
        return questionNum;
    }
    public void setQuestionNum(int questionNum) {
        this.questionNum = questionNum;
    }
    public State getQuestionState() {
        return questionState;
    }
    public void setQuestionState(State questionState) {
        this.questionState = questionState;
    }
    public State getGameState() {
        return gameState;
    }
    public void setGameState(State gameState) {
        this.gameState = gameState;
    }
    public State getUnderState() {
        return underState;
    }
    public void setUnderState(State underState) {
        this.underState = underState;
    }
    public Field getField() {
        return field;
    }
    public void setField(Field field) {
        this.field = field;
    }
}
