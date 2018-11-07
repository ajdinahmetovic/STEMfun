package space.stemfun.stemfun;

import android.animation.Animator;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import nl.dionsegijn.konfetti.Confetti;
import nl.dionsegijn.konfetti.KonfettiView;
import nl.dionsegijn.konfetti.models.Shape;
import nl.dionsegijn.konfetti.models.Size;


public class MainFragment extends Fragment {


    LinearLayout mainLayout;

    boolean isExpanded;




    User user;
    TinyDB localDb;
    ProgressBar dialogs [] = new ProgressBar[4];
    TextView progressText [] = new TextView[4];

    private String [] colors = {"#91a6ff", "#faff7f", "#ef626c"};

   // private int[] backs = {R.drawable.shape_cardview_blue, R.drawable.shape_cardview_pink, R.drawable.shape_cardview_yellow};

    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Drawable [] icons = {
                getResources().getDrawable(R.drawable.science),
                getResources().getDrawable(R.drawable.tech_icon),
                getResources().getDrawable(R.drawable.engineering),
                getResources().getDrawable(R.drawable.math)};

        View view = inflater.inflate(R.layout.fragment_main, container, false);
        //isExpanded = false;

        localDb = new TinyDB(getContext());
        user = localDb.getObject("currentUser", User.class);

        ImageView imgs [] = new ImageView[4];

        imgs[0] = view.findViewById(R.id.scienceImg);
        imgs[1] = view.findViewById(R.id.techImg);
        imgs[2] = view.findViewById(R.id.engineeringImg);
        imgs[3] = view.findViewById(R.id.mathImg);

        for(int i = 0;i<4;i++){
            imgs[i].setImageDrawable(icons[i]);
        }


        dialogs[0] = view.findViewById(R.id.scineceProgress);
        dialogs[1] = view.findViewById(R.id.techProgress);
        dialogs[2] = view.findViewById(R.id.engineeringProgress);
        dialogs[3] = view.findViewById(R.id.mathProgress);

        progressText[0] = view.findViewById(R.id.scienceProgressText);
        progressText[1] = view.findViewById(R.id.techProgressText);
        progressText[2] = view.findViewById(R.id.engineeringProgressText);
        progressText[3] = view.findViewById(R.id.mathProgressText);

        List<Integer> list = user.getQuestionProgress();

        for(int i = 0;i<4;i++){

            //dialogs[i].setProgress(100);
            dialogs[i].setProgress(list.get(i));
            progressText[i].setText(" ");
            progressText[i].setTextColor(getResources().getColor(R.color.colorPrimary));

        }

        Typeface font  = Typeface.createFromAsset(getActivity().getAssets(), "bold.ttf");
/*
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            font = getResources().getFont(R.font.bold);
        }
        */
        mainLayout = view.findViewById(R.id.mainLinearLayout);

        final float scale = view.getContext().getResources().getDisplayMetrics().density;

        LinearLayout.LayoutParams cardParams = new LinearLayout.LayoutParams((int) (300*scale+0.5f), ViewGroup.LayoutParams.WRAP_CONTENT);
        cardParams.topMargin =(int) (50*scale+0.5f);
        LinearLayout.LayoutParams textParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        LinearLayout.LayoutParams dropDownParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dropDownParams.topMargin =(int) (50*scale+0.5f);
        LinearLayout.LayoutParams distancerParams = new LinearLayout.LayoutParams((int) (55*scale+0.5f), (int) (10*scale+0.5f));
        LinearLayout.LayoutParams distancer2Params = new LinearLayout.LayoutParams((int) (30*scale+0.5f), (int) (10*scale+0.5f));

        LinearLayout.LayoutParams imgParams = new LinearLayout.LayoutParams((int) (40*scale+0.5f), (int) (40*scale+0.5f));

        LinearLayout.LayoutParams buttonParams = new LinearLayout.LayoutParams((int) (90*scale+0.5f), (int) (70*scale+0.5f));
        LinearLayout.LayoutParams button2Params = new LinearLayout.LayoutParams((int) (70*scale+0.5f), (int) (70*scale+0.5f));

        LinearLayout.LayoutParams endpointParams = new LinearLayout.LayoutParams(2, (int) (50*scale+0.5f));


        int random;


        if(user.getLevels().get(user.getLevel()).getUnderLevels().get(user.getUnderLevel()).getGameState()==State.UNLOCKED && user.getLevels().get(user.getLevel()).getUnderLevels().get(user.getUnderLevel()).getQuestionState()==State.UNLOCKED){


            System.out.println("IN UNDERLEVEL UNLOCK");
            user.setCurrentField(Field.EMPTY);
            user.setUnderLevel(user.getUnderLevel()+1);
            user.unlockUnderLevel(user.getLevel(), user.getUnderLevel());
            user.setQuesGame(user.getQuesGame()+1);
            localDb.putObject("currentUser", user);

            new Thread(new Runnable() {
                @Override
                public void run() {
                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
                    databaseReference.child("stemfun-54bfc");
                    FirebaseAuth mAuth = mAuth = FirebaseAuth.getInstance();
                    FirebaseUser firebaseUser = mAuth.getCurrentUser();

                    databaseReference.child("users").child(firebaseUser.getUid()).setValue(localDb.getObject("currentUser", User.class)).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()) {
                                System.out.println("Changes saved");

                            } else {
                                System.out.println("Failed");
                            }
                        }
                    });
                }
            }).start();

        }
        //ZA KONFETE
        DisplayMetrics metrics = new DisplayMetrics();
        ((Activity) getContext()).getWindowManager()
                .getDefaultDisplay()
                .getMetrics(metrics);
        int height = metrics.heightPixels;
        int width = metrics.widthPixels;
        /////////////////////

                //.stream(300, 5000L);
        ///////////////////////

        System.out.println(user.getLevel());

        if(user.getUnderLevel() == 6){
/*
            ViewDialog dialog = new ViewDialog();
            dialog.showDialog(getActivity(), PopupType.TROPHY);
  */
            ViewDialog dialog = new ViewDialog();
            dialog.showDialog(getActivity(), PopupType.TROPHY);
            KonfettiView confetti = view.findViewById(R.id.confetti);
            confetti.build().addColors(Color.parseColor("#BCED09"), Color.parseColor("#2F52E0"), Color.parseColor("#C84C09"))
                    .setDirection(0.0, 359.0)
                    .setSpeed(3f, 7f)
                    .setFadeOutEnabled(true)
                    .setTimeToLive(20000L)
                    .addShapes(Shape.RECT, Shape.CIRCLE)
                    .addSizes(new nl.dionsegijn.konfetti.models.Size(10, 25))
                    .setPosition(width/2, height/2)
                    .burst(1000);

            user.setTrophies(user.getTrophies()+1);
            user.setUnderLevel(1);
            user.setQuesGame(0);
            user.setLevel(user.getLevel()+1);
            user.getLevels().get(user.getLevel()).setLevelState(State.UNLOCKED);
            localDb.putObject("currentUser",user);
        }

        for(int i=0;i<20;i++){

            random = (int) (Math.random() * 3);
            //System.out.println("Rand"+random);
            CardView card = new CardView(getContext());
            card.setRadius(50);
            card.setLayoutParams(cardParams);
            //card.setCardElevation((int) (5*scale+0.5f));


            //card.setBackgroundColor(Color.parseColor(colors[random]));
            //card.setRadius((int) (50*scale+0.5f));
/*
            if(random==0){
                //card.setBackgroundResource(R.drawable.shape_cardview_blue);
                card.setBackgroundResource(R.drawable.shape_blue);
            } else if(random==1) {
                card.setBackgroundResource(R.drawable.shape_cardview_yellow);
            } else if(random==2) {
                card.setBackgroundResource(R.drawable.shape_cardview_pink);
            }
*/
            LinearLayout textLayout = new LinearLayout(getContext());
            textLayout.setOrientation(LinearLayout.HORIZONTAL);
            textLayout.setGravity(Gravity.CENTER);
            textLayout.setLayoutParams(textParams);
            View viewport = new View(getContext());
            viewport.setLayoutParams(imgParams);

            //ViewDialog dialog = new ViewDialog();
            //dialog.showDialog(getActivity(), PopupType.TROPHY);


            final TextView levelText = new TextView(getContext());
            levelText.setText("Level "+(i+1));
            levelText.setTypeface(font);
            levelText.setTextSize(60);
            levelText.setId(i);
            levelText.setPadding((int) (15*scale+0.5f),0,0,0);
            levelText.setGravity(Gravity.CENTER);
            //levelText.setTextColor(Color.parseColor("#FFFFFF"));
            //if(random==1){
                levelText.setTextColor(Color.parseColor("#010b19"));
            //}
            //#010b19
            levelText.setLayoutParams(textParams);

            if(user.getLevels().get(i+1).getLevelState()==State.UNLOCKED){
                card.addView(levelText);
                //img.setImageResource(R.drawable.unlocked_icon);
            } else {
                textLayout.addView(levelText);
            }

            viewport = new View(getContext());
            viewport.setLayoutParams(imgParams);

            textLayout.addView(viewport);

            ImageView img = new ImageView(getContext());
            //img.setForegroundGravity(Gravity.CENTER);
            img.setLayoutParams(imgParams);


            if(user.getLevels().get(i+1).getLevelState()==State.UNLOCKED){

                card.setBackgroundResource(R.drawable.shape_cardview_pink);

                //img.setImageResource(R.drawable.unlocked_icon);
            } else {
                img.setImageResource(R.drawable.locked_icon);
                textLayout.addView(img);
                card.setBackgroundResource(R.drawable.shape_blue);

                //textLayout.addView(levelText);
                card.addView(textLayout);
            }

            textLayout.setGravity(Gravity.CENTER);



            final LinearLayout dropGroup = new LinearLayout(getContext());
            dropGroup.setOrientation(LinearLayout.VERTICAL);
            dropGroup.setLayoutParams(dropDownParams);
            for(int j = 0;j<5;j++) {
                isExpanded = false;


                final LinearLayout dropDown = new LinearLayout(getContext());

               if(user.getLevels().get(i+1).getUnderLevels().get(j+1).getUnderState()==State.UNLOCKED){
                   dropDown.setBackgroundColor(Color.parseColor(colors[random]));
               } else {
                   dropDown.setBackgroundColor(Color.parseColor("#595959"));
               }

                //dropDown.setBackgroundColor(Color.parseColor(colors[random]));
                dropDown.setOrientation(LinearLayout.HORIZONTAL);
                dropDown.setLayoutParams(dropDownParams);

                View distancer = new View(getContext());
                distancer.setLayoutParams(distancerParams);
                dropDown.addView(distancer);

                final int b = j;

                final Button game = new Button(getContext());
                game.setLayoutParams(buttonParams);
                game.setBackgroundResource(R.drawable.joystick);
                final Intent gameActivity = new Intent(getContext(), GameActivity.class);
                final int finalJ = j;
                final int finalI = i;
                game.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(user.getLevels().get(finalI+1).getUnderLevels().get(finalJ+1).getUnderState()==State.UNLOCKED){
                            localDb.putInt("clickedLevel", finalI+1);
                            localDb.putInt("clickedUnderLevel", finalJ+1);

                            startActivity(gameActivity);
                        } else {
                            Toast.makeText(getContext(),"Locked",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                dropDown.addView(game);
/*
                View distancer2 = new View(getContext());
                distancer2.setLayoutParams(distancer2Params);
*/

                ImageView distancer2  = new ImageView(getContext());
                distancer2.setLayoutParams(distancer2Params);

                if(user.getLevels().get(i+1).getUnderLevels().get(j+1).getField() == Field.ENGINEERING){
                    //distancer2.setImageResource(R.drawable.engineering);
                    distancer2.setImageDrawable(icons[2]);
                } else if(user.getLevels().get(i+1).getUnderLevels().get(j+1).getField() == Field.MATH){
                    //distancer2.setImageResource(R.drawable.math);
                    distancer2.setImageDrawable(icons[3]);
                } else if(user.getLevels().get(i+1).getUnderLevels().get(j+1).getField() == Field.TECHNOLOGY){
                    //distancer2.setImageResource(R.drawable.tech_icon);
                    distancer2.setImageDrawable(icons[1]);
                } else if(user.getLevels().get(i+1).getUnderLevels().get(j+1).getField() == Field.SCIENCE){
                    //distancer2.setImageResource(R.drawable.science);
                    distancer2.setImageDrawable(icons[0]);
                }


                dropDown.addView(distancer2);

                final Intent questionClass = new Intent(getContext(), QuestionActivity.class);
                final Button question = new Button(getContext());
                question.setLayoutParams(button2Params);
                question.setBackgroundResource(R.drawable.question);
                question.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(user.getLevels().get(finalI+1).getUnderLevels().get(finalJ+1).getUnderState()==State.UNLOCKED){
                            questionClass.putExtra("level", finalI+1);
                            questionClass.putExtra("underLevel", finalJ+1);
                            startActivity(questionClass);

                        } else {
                            Toast.makeText(getContext(),"Locked",Toast.LENGTH_SHORT).show();
                        }

                    }
                });
                dropDown.addView(question);

                dropGroup.addView(dropDown);
                }

            View endpoint = new View(getContext());
            endpoint.setLayoutParams(endpointParams);

            //card.addView(endpoint);
                dropGroup.addView(endpoint);
                card.addView(dropGroup);

                dropGroup.setVisibility(View.GONE);





                if(i == user.getLevel()-1){

                    dropGroup.setVisibility(View.VISIBLE);
                    isExpanded = true;
                }

                card.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(Integer.parseInt(levelText.getText().toString().substring(levelText.getText().toString().length()-1, levelText.getText().toString().length()))<=user.getLevel()){

                            if (isExpanded) {
                                dropGroup.setVisibility(View.GONE);
                                dropGroup.animate().alpha(0.0f);
                                isExpanded = false;
                            } else {
                                dropGroup.setVisibility(View.VISIBLE);
                                dropGroup.animate().alpha(1.0f);
                                isExpanded = true;
                            }
                        } else {
                            Toast.makeText(getContext(), "This level is locked", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
               // card.setRadius((float) 255);

            mainLayout.addView(card);
            //card.setForegroundGravity(Gravity.CENTER_HORIZONTAL);
        }

        View viewport = new View(getContext());
        LinearLayout.LayoutParams viewportparams = new LinearLayout.LayoutParams((int) (80*scale+0.5f),(int) (80*scale+0.5f));
        viewport.setLayoutParams(viewportparams);
        mainLayout.addView(viewport);

        return view;
    }


}
