package space.stemfun.stemfun;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.util.Size;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.unity3d.player.UnityPlayer;

import java.util.ArrayList;
import java.util.Collections;

import nl.dionsegijn.konfetti.KonfettiView;
import nl.dionsegijn.konfetti.models.Shape;

public class ViewDialog {



    private String explanation;

    public void showDialog (final Activity activity, PopupType popupType){



        final Dialog dialog = new Dialog(activity);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);

        if(popupType == PopupType.MEDAL){
            dialog.setContentView(R.layout.popup_medal);
            Button collect = dialog.findViewById(R.id.collectButton);

            KonfettiView confetti = dialog.findViewById(R.id.confetti);

            collect.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //KonfettiView confetti = dialog.findViewById(R.id.confetti);

                    TinyDB localDb = new TinyDB(activity.getApplicationContext());
                    User user = localDb.getObject("currentUser", User.class);
                    user.setMedals(user.getMedals()+1);
                    localDb.putObject("currentUser", user);
                    Intent intent = new Intent(activity.getApplicationContext(),MainActivity.class);
                    activity.startActivity(intent);

                }
            });

        } else if (popupType == PopupType.TROPHY) {
            dialog.setContentView(R.layout.popup_trophy);

            Button collect = dialog.findViewById(R.id.collectButton);

            collect.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println("Collected 1 trophy");
                    TinyDB localDb = new TinyDB(activity.getApplicationContext());
                    User user = localDb.getObject("currentUser", User.class);
                    user.setTrophies(user.getTrophies()+1);
                    localDb.putObject("currentUser", user);
                    Intent intent = new Intent(activity.getApplicationContext(),MainActivity.class);
                    dialog.dismiss();
                }
            });


        } else if(popupType == PopupType.WRONG_ANSWER) {
            dialog.setContentView(R.layout.popup_wrong_answer);
            Button back = dialog.findViewById(R.id.backButton);
            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(activity.getApplicationContext(),MainActivity.class);
                    activity.startActivity(intent);
                }
            });

            Button expalantion = dialog.findViewById(R.id.explanationButton);
            expalantion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ViewDialog dialog1 = new ViewDialog();
                    dialog1.setExplanation(explanation);
                    System.out.println(explanation);
                    dialog1.showDialog(activity, PopupType.EXPLANATION);
                }
            });

        } else if(popupType == PopupType.EXPLANATION){
            dialog.setContentView(R.layout.popup_explanation);
            TextView explanationView = dialog.findViewById(R.id.explanationText);
            explanationView.setText(explanation);

            Button back = dialog.findViewById(R.id.backButton);

            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(activity.getApplicationContext(), MainActivity.class);
                    activity.startActivity(intent);

                }
            });

        } else if(popupType == PopupType.SCIENCE_GAME){
            dialog.setContentView(R.layout.popup_science_game);
            Button back = dialog.findViewById(R.id.backButton);
            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(activity.getApplicationContext(), GameActivity.class);
                    //TinyDB localDb = new TinyDB(activity.getApplicationContext());
                    activity.startActivity(intent);

                }
            });

        } else if(popupType == PopupType.TECH_GAME){
            dialog.setContentView(R.layout.popup_tech_game);
            Button back = dialog.findViewById(R.id.backButton);
            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(activity.getApplicationContext(), GameActivity.class);
                    //TinyDB localDb = new TinyDB(activity.getApplicationContext());
                    activity.startActivity(intent);

                }
            });

        } else if(popupType == PopupType.ENGINEERING_GAME){
            dialog.setContentView(R.layout.popup_engineering_game);
            Button back = dialog.findViewById(R.id.backButton);
            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(activity.getApplicationContext(), GameActivity.class);
                    //TinyDB localDb = new TinyDB(activity.getApplicationContext());
                    activity.startActivity(intent);

                }
            });

        } else if(popupType == PopupType.MATH_GAME){
            dialog.setContentView(R.layout.popup_math_game);
            Button back = dialog.findViewById(R.id.backButton);
            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(activity.getApplicationContext(), GameActivity.class);
                    //TinyDB localDb = new TinyDB(activity.getApplicationContext());
                    activity.startActivity(intent);

                }
            });

        } else if(popupType == PopupType.MEDAL_LANDSCAPE){
            dialog.setContentView(R.layout.popup_metal_landscape);
            Button collect = dialog.findViewById(R.id.collectButton);

            KonfettiView confetti = dialog.findViewById(R.id.confetti);

            collect.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //KonfettiView confetti = dialog.findViewById(R.id.confetti);

                    TinyDB localDb = new TinyDB(activity.getApplicationContext());
                    User user = localDb.getObject("currentUser", User.class);
                    user.setMedals(user.getMedals()+1);
                    localDb.putObject("currentUser", user);
                    Intent intent = new Intent(activity.getApplicationContext(),MainActivity.class);
                    activity.startActivity(intent);

                }
            });

        } else if(popupType == PopupType.GAME_FAILED){

            dialog.setContentView(R.layout.popup_game_failed);
            Button back = dialog.findViewById(R.id.backButton);
            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(activity.getApplicationContext(),MainActivity.class);
                    activity.startActivity(intent);
                }
            });

            Button expalantion = dialog.findViewById(R.id.explanationButton);

            final TinyDB localDb = new TinyDB(activity.getApplicationContext());
            final User user = localDb.getObject("currentUser", User.class);

            expalantion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ViewDialog dialog1 = new ViewDialog();
                    dialog1.setExplanation(explanation);
                    System.out.println(explanation);

                    Intent intent = new Intent(activity.getApplicationContext(), GameActivity.class);

                    if(user.getCurrentField().getFieldValue() == 0){
                        localDb.putBoolean("science_first", true);


                    } else if(user.getCurrentField().getFieldValue() == 1){

                        localDb.putBoolean("tech_first", true);
                    } else if(user.getCurrentField().getFieldValue() == 2){
                        localDb.putBoolean("engineering_first", true);
                        activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

                    } else if(user.getCurrentField().getFieldValue() == 3) {
                        ViewDialog dialog = new ViewDialog();
                        dialog.showDialog(activity, PopupType.MATH_GAME);
                    }
                    //dialog1.showDialog(activity, PopupType.EXPLANATION);
                    activity.startActivity(intent);
                }
            });


        } else if(popupType == PopupType.GAME_FAILED_LANDSCAPE){

            dialog.setContentView(R.layout.popup_game_failed_landscape);
            Button back = dialog.findViewById(R.id.backButton);
            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(activity.getApplicationContext(),MainActivity.class);
                    activity.startActivity(intent);
                }
            });

            Button expalantion = dialog.findViewById(R.id.explanationButton);

            final TinyDB localDb = new TinyDB(activity.getApplicationContext());
            final User user = localDb.getObject("currentUser", User.class);

            expalantion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ViewDialog dialog1 = new ViewDialog();
                    dialog1.setExplanation(explanation);
                    System.out.println(explanation);

                    Intent intent = new Intent(activity.getApplicationContext(), GameActivity.class);

                    if(user.getCurrentField().getFieldValue() == 0){
                        localDb.putBoolean("science_first", true);

                    } else if(user.getCurrentField().getFieldValue() == 1){

                        localDb.putBoolean("tech_first", true);
                    } else if(user.getCurrentField().getFieldValue() == 2){
                        localDb.putBoolean("engineering_first", true);

                    } else if(user.getCurrentField().getFieldValue() == 3) {
                        ViewDialog dialog = new ViewDialog();
                        dialog.showDialog(activity, PopupType.MATH_GAME);
                    }
                    //dialog1.showDialog(activity, PopupType.EXPLANATION);
                    activity.startActivity(intent);
                }
            });


        } else if (popupType == PopupType.LEADERBOARD){
            dialog.setContentView(R.layout.popup_leaderboard);

            Button close = dialog.findViewById(R.id.close);
            close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });

            LinearLayout container = dialog.findViewById(R.id.container);


            LinearLayout rank = new LinearLayout(activity);
            LinearLayout.LayoutParams rankParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

            rank.setLayoutParams(rankParams);
/*
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
            Query query = reference.child("users").orderByChild("medals");

            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    ArrayList<User> list = new ArrayList<>();
                    for(DataSnapshot data : dataSnapshot.getChildren()){
                        list.add(data.getValue(User.class));
                        //System.out.println(data.getValue());
                    }


                   // System.out.println(dataSnapshot.getValue());
                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

*/






        } else if (popupType == PopupType.NO_INTERNET){
            dialog.setContentView(R.layout.popup_no_internet);


        }


        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.show();

    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

}
