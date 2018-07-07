package space.stemfun.stemfun;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class User extends UserProgress {

    private String name;
    private String username;
    private String password;
    private UserType accountType;
    private String email;

    public UserProgress getUserProgress() {
        return userProgress;
    }

    public void setUserProgress(UserProgress userProgress) {
        this.userProgress = userProgress;
    }

    private UserProgress userProgress;
    /*
    private int trophies;
    private int medals;                                 // S T E M
    private List<Integer> questionProgress = Arrays.asList(0,0,0,0);
    private Field currentField = Field.EMPTY;


    public Field getCurrentField() {
        return currentField;
    }

    public void setCurrentField(Field currentField) {
        this.currentField = currentField;
    }

    public List<Integer> getQuestionProgress() {
        return questionProgress;
    }

    public void setQuestionProgress(List<Integer> questionProgress) {
        this.questionProgress = questionProgress;
    }

    public int getMedals() {
        return medals;
    }

    public void setMedals(int medals) {
        this.medals = medals;
    }
        public int getTrophies() {
        return trophies;
    }

    public void setTrophies(int trophies) {
        this.trophies = trophies;
    }
*/
    private UserAge userAge;

    public User (){
        setUserAge(UserAge.junior);
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


}
