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

    private String [] colors = {"#91a6ff", "#ef626c", "#faff7f"};

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

        View view = inflater.inflate(R.layout.fragment_main, container, false);
        //isExpanded = false;








        localDb = new TinyDB(getContext());
        user = localDb.getObject("currentUser", User.class);

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
            int num = (int) (Math.random() * 100 + 1);
            dialogs[i].setProgress(list.get(i));
            progressText[i].setText(list.get(i)+"%");
        }


        Typeface font  = Typeface.createFromAsset(getActivity().getAssets(), "bold.ttf");

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            font = getResources().getFont(R.font.bold);
        }
        mainLayout = view.findViewById(R.id.mainLinearLayout);

        final float scale = view.getContext().getResources().getDisplayMetrics().density;

        LinearLayout.LayoutParams cardParams = new LinearLayout.LayoutParams((int) (300*scale+0.5f), ViewGroup.LayoutParams.WRAP_CONTENT);
        cardParams.topMargin =(int) (50*scale+0.5f);
        LinearLayout.LayoutParams textParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        LinearLayout.LayoutParams dropDownParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dropDownParams.topMargin =(int) (50*scale+0.5f);
        LinearLayout.LayoutParams distancerParams = new LinearLayout.LayoutParams((int) (70*scale+0.5f), (int) (10*scale+0.5f));
        LinearLayout.LayoutParams distancer2Params = new LinearLayout.LayoutParams((int) (30*scale+0.5f), (int) (10*scale+0.5f));

        LinearLayout.LayoutParams imgParams = new LinearLayout.LayoutParams((int) (40*scale+0.5f), (int) (40*scale+0.5f));

        LinearLayout.LayoutParams buttonParams = new LinearLayout.LayoutParams((int) (70*scale+0.5f), (int) (70*scale+0.5f));

        ScrollView scrollView = new ScrollView(getContext());

        int random;

        if(user.getQuesGame()%2==0 && user.getQuesGame()!=0){
            //ViewDialog dialog = new ViewDialog();
            //dialog.showDialog(getActivity(), PopupType.MEDAL);
            user.setCurrentField(Field.EMPTY);
            user.setUnderLevel(user.getUnderLevel()+1);
            user.setQuesGame(user.getQuesGame()+1);
            localDb.putObject("currentUser", user);
        }

        DisplayMetrics metrics = new DisplayMetrics();
        ((Activity) getContext()).getWindowManager()
                .getDefaultDisplay()
                .getMetrics(metrics);
        int height = metrics.heightPixels;
        int width = metrics.widthPixels;
        /////////////////////
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
                //.stream(300, 5000L);
        ///////////////////////

        if(user.getUnderLevel() == 4){
/*
            ViewDialog dialog = new ViewDialog();
            dialog.showDialog(getActivity(), PopupType.TROPHY);
  */
            user.setTrophies(user.getTrophies()+1);
            user.setUnderLevel(1);
            user.setQuesGame(0);
            user.setLevel(user.getLevel()+1);
            localDb.putObject("currentUser",user);
        }

        for(int i=0;i<5;i++){

            random = (int) (Math.random() * 3);
            System.out.println("Rand"+random);
            CardView card = new CardView(getContext());
            card.setRadius(50);
            card.setLayoutParams(cardParams);
            //card.setCardElevation((int) (5*scale+0.5f));


            //card.setBackgroundColor(Color.parseColor(colors[random]));
            //card.setRadius((int) (50*scale+0.5f));

            if(random==0){
                //card.setBackgroundResource(R.drawable.shape_cardview_blue);
                card.setBackgroundResource(R.drawable.shape_blue);
            } else if(random==1) {
                card.setBackgroundResource(R.drawable.shape_cardview_yellow);
            } else if(random==2) {
                card.setBackgroundResource(R.drawable.shape_cardview_pink);
            }

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

            if(i<user.getLevel()){
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


            if(i<user.getLevel()){

                //img.setImageResource(R.drawable.unlocked_icon);
            } else {
                img.setImageResource(R.drawable.locked_icon);
                textLayout.addView(img);
                //textLayout.addView(levelText);
                card.addView(textLayout);
            }

            textLayout.setGravity(Gravity.CENTER);



            final LinearLayout dropGroup = new LinearLayout(getContext());
            dropGroup.setOrientation(LinearLayout.VERTICAL);
            dropGroup.setLayoutParams(dropDownParams);
            for(int j = 0;j<3;j++) {
                isExpanded = false;


                final LinearLayout dropDown = new LinearLayout(getContext());

               if(j<user.getUnderLevel()){
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
                game.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(b<user.getUnderLevel()){
                            startActivity(gameActivity);
                        } else {
                            Toast.makeText(getContext(),"Locked",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                dropDown.addView(game);

                View distancer2 = new View(getContext());
                distancer2.setLayoutParams(distancer2Params);
                dropDown.addView(distancer2);

                final Intent questionClass = new Intent(getContext(), QuestionActivity.class);
                Button question = new Button(getContext());
                question.setLayoutParams(buttonParams);
                question.setBackgroundResource(R.drawable.questionmark);
                question.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(b<user.getUnderLevel()){
                            startActivity(questionClass);
                        } else {
                            Toast.makeText(getContext(),"Locked",Toast.LENGTH_SHORT).show();
                        }

                    }
                });
                dropDown.addView(question);

                dropGroup.addView(dropDown);
                }
                card.addView(dropGroup);

                dropGroup.setVisibility(View.GONE);

                card.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        if(levelText.getText().toString().substring(levelText.getText().toString().length()-1, levelText.getText().toString().length()).equals(String.valueOf(user.getLevel()))){

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
