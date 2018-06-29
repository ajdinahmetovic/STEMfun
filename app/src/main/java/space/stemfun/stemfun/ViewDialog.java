package space.stemfun.stemfun;

import android.app.Activity;
import android.app.Dialog;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.widget.Button;

public class ViewDialog {
    public void showDialog (final Activity activity, PopupType popupType){

        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);

        if(popupType == PopupType.MEDAL){
            dialog.setContentView(R.layout.popup_medal);
        } else if (popupType == PopupType.TROPHY) {
            dialog.setContentView(R.layout.popup_trophy);
        } else if(popupType == PopupType.WRONG_ANSWER) {
            dialog.setContentView(R.layout.popup_wrong_answer);
        }

        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
/*
        ((ViewGroup)dialog.getWindow().getDecorView())
                .getChildAt(0).startAnimation(AnimationUtils.loadAnimation(
                activity,android.R.anim.slide_in_left));
                */
/*
        buttons [0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ViewGroup)dialog.getWindow().getDecorView())
                        .getChildAt(0).startAnimation(AnimationUtils.loadAnimation(
                        activity,android.R.anim.slide_out_right));
                dialog.dismiss();
            }
        });
*/
        dialog.show();

    }
}
