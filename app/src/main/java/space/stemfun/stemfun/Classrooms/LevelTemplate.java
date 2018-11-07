package space.stemfun.stemfun.Classrooms;

import space.stemfun.stemfun.Field;
import space.stemfun.stemfun.Question;
import space.stemfun.stemfun.State;

public class LevelTemplate {

    private Field field ;

    private Question questionData;
    private State questionState ;
    private State gameState ;
    private State underState ;

    LevelTemplate (){

        field = Field.EMPTY;
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
