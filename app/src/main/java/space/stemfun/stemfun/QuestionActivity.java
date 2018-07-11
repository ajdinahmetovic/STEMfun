package space.stemfun.stemfun;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static android.widget.ListPopupWindow.WRAP_CONTENT;

public class QuestionActivity extends AppCompatActivity {



    private TinyDB localDb;
    private DatabaseReference databaseReference;

    public String nums [] = new String[50];
    private ArrayList<String> num = new ArrayList<>();
    private String answerClicked;

    User user;

    Intent intent;

    Button questionText, choice1, choice2, choice3, choice4;

    Question question;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        decorView.setSystemUiVisibility(uiOptions);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("stemfun-54bfc");

        questionText = findViewById(R.id.question);
        choice1 = findViewById(R.id.choice1);
        choice2 = findViewById(R.id.choice2);
        choice3 = findViewById(R.id.choice3);
        choice4 = findViewById(R.id.choice4);

        intent = new Intent(this, MainActivity.class);

        question = new Question();

        localDb = new TinyDB(this);
        user = localDb.getObject("currentUser", User.class);


    try {
        if (user.getCurrentField().getFieldValue() == -1) {
            System.out.println("NOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO");

            Intent intent = new Intent(this, RandomGenerator.class);
            intent.putExtra("whatIs", false);
            startActivity(intent);
            finish();
            throw new FieldNotSelected();
        }

        //databaseReference.child("stemfun-54bfc").child("questions).child("junior").child("science").child("11");
        //Query myQuery = databaseReference.limitToFirst(100);


        if(user.getLevels().get(user.getLevel()).getUnderLevels().get(user.getUnderLevel()).getQuestionNum() == -1){

            databaseReference.child("questions").child((user.getUserAge().getValue())).child(user.getCurrentField().toString().toLowerCase()).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    System.out.println(dataSnapshot.getChildrenCount());
                    user.getLevels().get(user.getLevel()).getUnderLevels().get(user.getUnderLevel()).setQuestionNum((int) (Math.random()* (int) dataSnapshot.getChildrenCount() + 1) - 1 );
                    localDb.putObject("currentUser", user);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });


        } else {


        }

        databaseReference.child("questions").child(user.getUserAge().getValue()).child(user.getCurrentField().toString().toLowerCase()).child((user.getQuestionProgress().get(user.getCurrentField().getFieldValue()).toString())).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                question = dataSnapshot.getValue(Question.class);
                List<Integer> list = user.getQuestionProgress();
                list.set(user.getCurrentField().getFieldValue(), list.get(user.getCurrentField().getFieldValue()) + 1);
                user.setQuestionProgress(list);
                localDb.putObject("currentUser", user);

                questionText.setText(question.getQuestion());
                choice1.setText(question.getChoice1());
                choice2.setText(question.getChoice2());
                choice3.setText(question.getChoice3());
                choice4.setText(question.getChoice4());

                user.getLevels().get(user.getLevel()).getUnderLevels().get(user.getUnderLevel()).setGameState(State.UNLOCKED);
                System.out.println(question.getAnswer());

                choice1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (choice1.getText().toString().equals(question.getAnswer())) {
                            //Toast.makeText(getApplicationContext(), "Your answer is correct !!", Toast.LENGTH_LONG).show();
                            //startActivity(intent);
                            user.setQuesGame(user.getQuesGame()+1);
                            user.getLevels().get(user.getLevel()).getUnderLevels().get(user.getUnderLevel()).setQuestionState(State.UNLOCKED);
                            ViewDialog dialog = new ViewDialog();
                            localDb.putObject("currentUser", user);
                            dialog.showDialog(QuestionActivity.this, PopupType.MEDAL);
                        } else {
                            //Toast.makeText(getApplicationContext(), "Your answer is wrong please try again", Toast.LENGTH_LONG).show();
                            ViewDialog dialog = new ViewDialog();
                            dialog.setExplanation(question.getDescription());
                            dialog.showDialog(QuestionActivity.this, PopupType.WRONG_ANSWER);
                            //startActivity(intent);
                        }
                    }
                });

                choice2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (choice2.getText().toString().equals(question.getAnswer())) {
                            //Toast.makeText(getApplicationContext(), "Your answer is correct !!", Toast.LENGTH_LONG).show();
                            final ViewDialog dialog = new ViewDialog();
                            user.setQuesGame(user.getQuesGame()+1);
                            user.getLevels().get(user.getLevel()).getUnderLevels().get(user.getUnderLevel()).setQuestionState(State.UNLOCKED);
                            localDb.putObject("currentUser", user);
                            View view = getLayoutInflater().inflate(R.layout.popup_medal, null);
                            dialog.showDialog(QuestionActivity.this, PopupType.MEDAL);
                        } else {
                            //Toast.makeText(getApplicationContext(), "Your answer is wrong please try again", Toast.LENGTH_LONG).show();
                            ViewDialog dialog = new ViewDialog();
                            dialog.setExplanation(question.getDescription());
                            dialog.showDialog(QuestionActivity.this, PopupType.WRONG_ANSWER);
                        }
                    }
                });

                choice3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (choice3.getText().toString().equals(question.getAnswer())) {
                            //Toast.makeText(getApplicationContext(), "Your answer is correct !!", Toast.LENGTH_LONG).show();
                            ViewDialog dialog = new ViewDialog();
                            user.setQuesGame(user.getQuesGame()+1);
                            user.getLevels().get(user.getLevel()).getUnderLevels().get(user.getUnderLevel()).setQuestionState(State.UNLOCKED);
                            localDb.putObject("currentUser", user);
                            dialog.showDialog(QuestionActivity.this, PopupType.MEDAL);
                        } else {
                            //Toast.makeText(getApplicationContext(), "Your answer is wrong please try again", Toast.LENGTH_LONG).show();
                            ViewDialog dialog = new ViewDialog();
                            System.out.println("xxxxxxx" + question.getDescription());
                            dialog.setExplanation(question.getDescription());
                            dialog.showDialog(QuestionActivity.this, PopupType.WRONG_ANSWER);
                        }
                    }
                });

                choice4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (choice4.getText().toString().equals(question.getAnswer())) {
                            //Toast.makeText(getApplicationContext(), "Your answer is correct !!", Toast.LENGTH_LONG).show();
                            ViewDialog dialog = new ViewDialog();
                            user.setQuesGame(user.getQuesGame()+1);
                            user.getLevels().get(user.getLevel()).getUnderLevels().get(user.getUnderLevel()).setQuestionState(State.UNLOCKED);
                            localDb.putObject("currentUser", user);
                            dialog.showDialog(QuestionActivity.this, PopupType.MEDAL);
                        } else {
                            //Toast.makeText(getApplicationContext(), "Your answer is wrong please try again", Toast.LENGTH_LONG).show();
                            ViewDialog dialog = new ViewDialog();
                            dialog.setExplanation(question.getDescription());
                            dialog.showDialog(QuestionActivity.this, PopupType.WRONG_ANSWER);
                        }
                    }
                });


                //localDb.putObject("question", question);
                //question = dataSnapshot.getValue(Question.class);
                //System.out.println(dataSnapshot.getKey());
                //System.out.println(dataSnapshot.getValue());
                //System.out.println(question.getQuestion());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    catch (FieldNotSelected e){

    }

    catch (Exception e){
        e.printStackTrace();
    }


    }
    
}
