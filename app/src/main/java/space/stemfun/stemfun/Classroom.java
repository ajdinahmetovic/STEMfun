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
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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


public class Classroom extends Fragment {

    public Classroom() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_classroom, container, false);

        ImageView notes = view.findViewById(R.id.notes);

        notes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager manager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.mainLayout, new Notes()).commit() ;
            }
        });

        final float scale = view.getContext().getResources().getDisplayMetrics().density;

        LinearLayout mainContainer = view.findViewById(R.id.clasroomContainer);
        Typeface font  = Typeface.createFromAsset(getActivity().getAssets(), "bold.ttf");

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


        for (int i = 0; i< 10;i++){


            CardView card = new CardView(getContext());
            card.setRadius(60);
            card.setLayoutParams(cardParams);

            final TextView levelText = new TextView(getContext());
            levelText.setText("Level "+(i+1));
            levelText.setTextSize(60);
            levelText.setTypeface(font);
            levelText.setId(i);
            levelText.setPadding((int) (15*scale+0.5f),0,0,0);
            levelText.setGravity(Gravity.CENTER);
            //levelText.setTextColor(Color.parseColor("#FFFFFF"));
            //if(random==1){
            levelText.setTextColor(Color.parseColor("#010b19"));
            //}
            //#010b19
            levelText.setLayoutParams(textParams);

            card.addView(levelText);
            card.setBackgroundResource(R.drawable.shape_cardview_pink);



            mainContainer.addView(card);

        }


        return view;
    }
}
