package space.stemfun.stemfun;

public class Question {

    private String choice1;
    private String choice2;
    private String choice3;
    private String choice4;
    private String description;

    private String answer;

    private String question;

    public String getChoice1() {
        return choice1;
    }

    public void setChoice1(String choice1) {
        this.choice1 = choice1;
    }

    public String getChoice2() {
        return choice2;
    }

    public void setChoice2(String choice2) {
        this.choice2 = choice2;
    }

    public String getChoice3() {
        return choice3;
    }

    public void setChoice3(String choice3) {
        this.choice3 = choice3;
    }

    public String getChoice4() {
        return choice4;
    }

    public void setChoice4(String choice4) {
        this.choice4 = choice4;
    }

    //private String[] answers = new String[4];
/*
    void setAnswer (int index, String choice){
        answers[index] = choice;
    }

    String getAnswer (int index){
        return answers[index];
    }
*/
    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    void shufleAnswers (){

    }


    public Question(String question,String description, String choice1, String choice2, String choice3, String choice4, String answer) {
        this.question = question;
        this.choice1 = choice1;
        this.choice2 = choice2;
        this.choice3 = choice3;
        this.choice4 = choice4;
        this.answer = answer;
        this.description = description;
        //   this.field = field;
    }

    public  Question (){

    }

}
