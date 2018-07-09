package space.stemfun.stemfun;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.util.Size;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

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
