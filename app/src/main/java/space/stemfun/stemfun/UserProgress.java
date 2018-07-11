package space.stemfun.stemfun;

import java.util.Arrays;
import java.util.List;

public class UserProgress {

    private int trophies;
    private int medals;
    private List<Integer> questionProgress = Arrays.asList(0,0,0,0);
    private Field currentField = Field.EMPTY;
    private int level = 1;
    private int underLevel = 1;
    private int quesGame = 0;


    public int getQuesGame() {
        return quesGame;
    }

    public void setQuesGame(int quesGame) {
        this.quesGame = quesGame;
    }

    public int getTrophies() {
        return trophies;
    }

    public void setTrophies(int trophies) {
        this.trophies = trophies;
    }

    public int getMedals() {
        return medals;
    }

    public void setMedals(int medals) {
        this.medals = medals;
    }

    public List<Integer> getQuestionProgress() {
        return questionProgress;
    }

    public void setQuestionProgress(List<Integer> questionProgress) {
        this.questionProgress = questionProgress;
    }

    public Field getCurrentField() {
        return currentField;
    }

    public void setCurrentField(Field currentField) {
        this.currentField = currentField;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getUnderLevel() {
        return underLevel;
    }

    public void setUnderLevel(int underLevel) {
        this.underLevel = underLevel;
    }
}
