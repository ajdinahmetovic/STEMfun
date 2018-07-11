package space.stemfun.stemfun;

import java.util.ArrayList;

public class Level {

    ArrayList<UnderLevel> underLevels = new ArrayList<>();
    private State levelState = State.LOCKED;



    public State getLevelState() {
        return levelState;
    }

    public void setLevelState(State levelState) {
        this.levelState = levelState;
    }


    public Level() {
        for(int i = 0;i<7;i++){
            underLevels.add(new UnderLevel());
        }
    }

    public ArrayList<UnderLevel> getUnderLevels() {

        return underLevels;
    }

    public void setUnderLevels(ArrayList<UnderLevel> underLevels) {
        this.underLevels = underLevels;
    }



}
