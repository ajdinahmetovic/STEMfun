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

import static android.widget.ListPopupWindow.WRAP_CONTENT;

public class QuestionActivity extends AppCompatActivity {



    private TinyDB localDb;
    private DatabaseReference databaseReference;

    public String nums [] = new String[50];
    private ArrayList<String> num = new ArrayList<>();
    private String answerClicked;

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

        //databaseReference.child("stemfun-54bfc").child("questions").child("junior").child("science").child("11");

        //Query myQuery = databaseReference.limitToFirst(100);

        databaseReference.child("questions").child("junior").child("science").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int i = 0;
                boolean hasQuestion = false;
                while(!hasQuestion){
                    if(dataSnapshot.hasChild(String.valueOf(i))){
                        question = dataSnapshot.child(String.valueOf(i)).getValue(Question.class);
                        hasQuestion=true;
                    }
                    i++;
                }

/*
                LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                PopupWindow pw = new PopupWindow(inflater.inflate(R.layout.popup_medal, null, false),WRAP_CONTENT,WRAP_CONTENT, true);
                pw.setElevation(1000);
                pw.showAtLocation(findViewById(R.id.question_cons), Gravity.CENTER, 0, 0);
*/


                Button back = findViewById(R.id.backButton);
/*
                ViewDialog dialog = new ViewDialog();
                dialog.showDialog(QuestionActivity.this, PopupType.WRONG_ANSWER,backb);
*/


                questionText.setText(question.getQuestion());
                choice1.setText(question.getChoice1());
                choice2.setText(question.getChoice2());
                choice3.setText(question.getChoice3());
                choice4.setText(question.getChoice4());

                System.out.println(question.getAnswer());


                choice1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(choice1.getText().toString().equals(question.getAnswer())){
                            Toast.makeText(getApplicationContext(), "Your answer is correct !!", Toast.LENGTH_LONG).show();
                            startActivity(intent);
                            ViewDialog dialog = new ViewDialog();
                            dialog.showDialog(QuestionActivity.this, PopupType.MEDAL);
                        } else {
                            Toast.makeText(getApplicationContext(), "Your answer is wrong please try again", Toast.LENGTH_LONG).show();
                            ViewDialog dialog = new ViewDialog();
                            dialog.showDialog(QuestionActivity.this, PopupType.WRONG_ANSWER);

                            //startActivity(intent);
                        }
                    }
                });

                choice2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(choice2.getText().toString().equals(question.getAnswer())){
                            Toast.makeText(getApplicationContext(), "Your answer is correct !!", Toast.LENGTH_LONG).show();
                            ViewDialog dialog = new ViewDialog();
                            dialog.showDialog(QuestionActivity.this, PopupType.MEDAL);
                        } else {
                            Toast.makeText(getApplicationContext(), "Your answer is wrong please try again", Toast.LENGTH_LONG).show();
                            ViewDialog dialog = new ViewDialog();
                            dialog.showDialog(QuestionActivity.this, PopupType.WRONG_ANSWER);
                        }
                    }
                });

                choice3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(choice3.getText().toString().equals(question.getAnswer())){
                            Toast.makeText(getApplicationContext(), "Your answer is correct !!", Toast.LENGTH_LONG).show();
                            ViewDialog dialog = new ViewDialog();
                            dialog.showDialog(QuestionActivity.this, PopupType.MEDAL);
                        } else {
                            Toast.makeText(getApplicationContext(), "Your answer is wrong please try again", Toast.LENGTH_LONG).show();
                            ViewDialog dialog = new ViewDialog();
                            dialog.showDialog(QuestionActivity.this, PopupType.WRONG_ANSWER);
                        }
                    }
                });

                choice4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(choice4.getText().toString().equals(question.getAnswer())){
                            Toast.makeText(getApplicationContext(), "Your answer is correct !!", Toast.LENGTH_LONG).show();
                            ViewDialog dialog = new ViewDialog();
                            dialog.showDialog(QuestionActivity.this, PopupType.MEDAL);
                        } else {
                            Toast.makeText(getApplicationContext(), "Your answer is wrong please try again", Toast.LENGTH_LONG).show();
                            ViewDialog dialog = new ViewDialog();
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
}
