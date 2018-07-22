package space.stemfun.stemfun;

public class UnderLevel {


    private Field field ;
    private int questionNum;


    private Question questionData;
    private State questionState ;
    private State gameState ;
    private State underState ;

    UnderLevel (){

          field = Field.EMPTY;
          questionNum = -1;
          questionState = State.LOCKED;
          gameState = State.LOCKED;
          underState = State.LOCKED;

    }



    public Question getQuestionData() {
        return questionData;
    }

    public void setQuestionData(Question questionData) {
        this.questionData = questionData;
    }

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
