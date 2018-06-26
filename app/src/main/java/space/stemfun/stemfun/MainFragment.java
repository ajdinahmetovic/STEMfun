package space.stemfun.stemfun;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;


public class MainFragment extends Fragment {


    LinearLayout mainLayout;

    boolean isExpanded;

    private String [] colors = {"#91a6ff", "#ef626c", "#faff7f"};


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
        isExpanded = false;

//        Typeface font  = Typeface.createFromAsset(getActivity().getAssets(), "res/font/bold.ttf");
        Typeface font = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            font = getResources().getFont(R.font.bold);
        }
        mainLayout = view.findViewById(R.id.mainLinearLayout);

        final float scale = view.getContext().getResources().getDisplayMetrics().density;

        LinearLayout.LayoutParams cardParams = new LinearLayout.LayoutParams((int) (300*scale+0.5f), ViewGroup.LayoutParams.WRAP_CONTENT);
        cardParams.topMargin =(int) (50*scale+0.5f);
        LinearLayout.LayoutParams textParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        LinearLayout.LayoutParams dropDownParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dropDownParams.topMargin =(int) (50*scale+0.5f);
        LinearLayout.LayoutParams distancerParams = new LinearLayout.LayoutParams((int) (70*scale+0.5f), (int) (10*scale+0.5f));
        LinearLayout.LayoutParams distancer2Params = new LinearLayout.LayoutParams((int) (30*scale+0.5f), (int) (10*scale+0.5f));

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


            card.setBackgroundColor(Color.parseColor(colors[random]));
            //card.setRadius((int) (50*scale+0.5f));

            TextView levelText = new TextView(getContext());
            levelText.setText("Level "+(i+1));
            levelText.setTypeface(font);
            levelText.setTextSize(40);
            levelText.setGravity(Gravity.CENTER);
            levelText.setTextColor(Color.WHITE);
            levelText.setLayoutParams(textParams);
            card.addView(levelText);

            final LinearLayout dropGroup = new LinearLayout(getContext());
            dropGroup.setOrientation(LinearLayout.VERTICAL);
            dropGroup.setLayoutParams(dropDownParams);
            for(int j = 0;j<5;j++) {


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

                Button question = new Button(getContext());
                question.setLayoutParams(buttonParams);
                question.setBackgroundResource(R.drawable.questionmark);
                dropDown.addView(question);

                dropGroup.addView(dropDown);
                }
                card.addView(dropGroup);

                dropGroup.setVisibility(View.GONE);

                card.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if (isExpanded) {
                            dropGroup.setVisibility(View.GONE);
                            isExpanded = false;
                        } else {
                            dropGroup.setVisibility(View.VISIBLE);
                            isExpanded = true;
                        }

                    }
                });
               // card.setRadius((float) 255);

            mainLayout.addView(card);

            //card.setForegroundGravity(Gravity.CENTER_HORIZONTAL);
        }


        return view;
    }

}
