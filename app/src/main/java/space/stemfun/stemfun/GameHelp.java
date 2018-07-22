package space.stemfun.stemfun;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import com.stem.spacev1.UnityPlayerActivity;

public class GameHelp extends UnityPlayerActivity {

    TinyDB localdb;


    public void isPassed (String data){

        localdb = new TinyDB(getApplicationContext());

        //Log.i("TAG", "The data was "+data);

        System.out.println("KUGLAAAAAA");


        User user = localdb.getObject("currentUser", User.class);

        Activity activity = (Activity) getApplicationContext();

                user.getLevels().get(user.getLevel()).getUnderLevels().get(user.getUnderLevel()).setGameState(State.UNLOCKED);
                ViewDialog dialog = new ViewDialog();
                dialog.showDialog(activity, PopupType.MEDAL);
                localdb.putObject("currentUser", user);


        }

    }



