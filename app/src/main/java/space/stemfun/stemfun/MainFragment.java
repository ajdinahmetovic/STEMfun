package space.stemfun.stemfun;

import android.animation.Animator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;


public class MainFragment extends Fragment {


    LinearLayout mainLayout;

    boolean isExpanded;

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

            textLayout.addView(levelText);
            viewport = new View(getContext());
            viewport.setLayoutParams(imgParams);

            textLayout.addView(viewport);

            ImageView img = new ImageView(getContext());
            //img.setForegroundGravity(Gravity.CENTER);
            img.setLayoutParams(imgParams);


            if(i==0){
                img.setImageResource(R.drawable.unlocked_icon);
            } else {
                img.setImageResource(R.drawable.locked_icon);
            }


            textLayout.addView(img);

            card.addView(textLayout);

            final LinearLayout dropGroup = new LinearLayout(getContext());
            dropGroup.setOrientation(LinearLayout.VERTICAL);
            dropGroup.setLayoutParams(dropDownParams);
            for(int j = 0;j<4;j++) {
                isExpanded = false;

                final LinearLayout dropDown = new LinearLayout(getContext());
                dropDown.setOrientation(LinearLayout.HORIZONTAL);
                dropDown.setLayoutParams(dropDownParams);

                View distancer = new View(getContext());
                distancer.setLayoutParams(distancerParams);
                dropDown.addView(distancer);

                Button game = new Button(getContext());
                game.setLayoutParams(buttonParams);
                game.setBackgroundResource(R.drawable.joystick);
                final Intent randomGameGenerator = new Intent(getContext(), RandomGenerator.class);
                game.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(randomGameGenerator);
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
                        startActivity(questionClass);
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

                        if(levelText.getText().equals("Level 1")){

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
