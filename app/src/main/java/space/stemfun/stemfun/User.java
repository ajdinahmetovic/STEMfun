package space.stemfun.stemfun;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class User extends UserProgress {

    private String name;
    private String username;
    private String password;
    private UserType accountType;
    private String email;
    private boolean hasClassroom;

    private String classRoom;
    private boolean requestAcepted;
    private String invitedClass;

    private ArrayList<Level> levels = new ArrayList<>();

    public ArrayList<Integer> getNumOfQuestions() {
        return numOfQuestions;
    }

    public void setNumOfQuestions(ArrayList<Integer> numOfQuestions) {
        this.numOfQuestions = numOfQuestions;
    }

    private ArrayList <Integer> numOfQuestions = new ArrayList<>();


    public ArrayList<Level> getLevels() {
        return levels;
    }

    public void setLevels(ArrayList<Level> levels) {
        this.levels = levels;
    }

    public void unlockLevel(int level){
        levels.get(level).setLevelState(State.UNLOCKED);
    }

    public void unlockUnderLevel(int level, int undetLevel){
        levels.get(level).getUnderLevels().get(undetLevel).setUnderState(State.UNLOCKED);
    }


    public UserProgress getUserProgress() {
        return userProgress;
    }

    public void setUserProgress(UserProgress userProgress) {
        this.userProgress = userProgress;
    }

    private UserProgress userProgress;


    private UserAge userAge;

    public User (){
        invitedClass = "!";
        hasClassroom = false;
        setUserAge(UserAge.junior);
        for(int i = 0;i<21;i++){
            levels.add(new Level());
        }

        levels.get(1).setLevelState(State.UNLOCKED);

    }

    public UserAge getUserAge() {
        return userAge;
    }
    public void setUserAge(UserAge userAge) {
        this.userAge = userAge;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserType getAccountType() {
        return accountType;
    }

    public void setAccountType(UserType accountType) {
        this.accountType = accountType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public boolean isHasClassroom() {
        return hasClassroom;
    }

    public void setHasClassroom(boolean hasClassroom) {
        this.hasClassroom = hasClassroom;
    }
}
