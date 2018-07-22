package space.stemfun.stemfun;

import java.util.ArrayList;

public class Level {

    private ArrayList<UnderLevel> underLevels = new ArrayList<>();
    private State levelState ;



    public State getLevelState() {
        return levelState;
    }

    public void setLevelState(State levelState) {
        this.levelState = levelState;
    }


    public Level() {
        levelState = State.LOCKED;
        for(int i = 0;i<7;i++){
            underLevels.add(new UnderLevel());
        }

        underLevels.get(1).setUnderState(State.UNLOCKED);

    }

    public ArrayList<UnderLevel> getUnderLevels() {

        return underLevels;
    }

    public void setUnderLevels(ArrayList<UnderLevel> underLevels) {
        this.underLevels = underLevels;
    }



}
