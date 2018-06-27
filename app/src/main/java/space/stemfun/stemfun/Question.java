package space.stemfun.stemfun;

import java.util.ArrayList;

public class Question {

    private String question;

    private String[] answers = new String[4];

    private String description;

    private String correctAnswer;

    void setAnswer (int index, String choice){
        answers[index] = choice;
    }

    String getAnswer (int index){
        return answers[index];
    }

    void shufleAnswers (){

    }

}
