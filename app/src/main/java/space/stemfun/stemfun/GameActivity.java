package space.stemfun.stemfun;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.stem.spacev1.UnityPlayerActivity;
import com.unity3d.player.UnityPlayer;

import kotlin.Unit;

public class GameActivity extends UnityPlayerActivity {

    TinyDB localDb;
    User user;
    Intent i;
    String passed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        localDb = new TinyDB(this);

        Intent s = getIntent();
        if(s.hasExtra("isPassed")){
            isPassed(s.getStringExtra("isPassed"));
        }

        user = localDb.getObject("currentUser", User.class);
        try {
            if (user.getCurrentField().getFieldValue() == -1) {
                Intent intent = new Intent(this, RandomGenerator.class);
                intent.putExtra("whatIs", true);
                startActivity(intent);
                //finish();
                throw new FieldNotSelected();
            }

            i = new Intent(this, MainActivity.class);




            Intent intent = new Intent(this, UnityPlayerActivity.class);

            intent.putExtra("level", user.getLevel());
            intent.putExtra("underLevel", user.getUnderLevel());

            if(user.getCurrentField().getFieldValue() == 0){

                intent.putExtra("gameName", "scienceScene");

                if(localDb.getBoolean("science_first")){
                    localDb.putBoolean("science_first", false);
                    ViewDialog dialog = new ViewDialog();
                    dialog.showDialog(GameActivity.this, PopupType.SCIENCE_GAME);
                } else {
                    startActivity(intent);
                }


            }if(user.getCurrentField().getFieldValue() == 1){
                intent.putExtra("gameName", "techScene");

                if(localDb.getBoolean("tech_first")){
                    localDb.putBoolean("tech_first", false);
                    ViewDialog dialog = new ViewDialog();
                    dialog.showDialog(GameActivity.this, PopupType.TECH_GAME);
                } else {
                    startActivity(intent);
                }
            }if(user.getCurrentField().getFieldValue() == 2){
                intent.putExtra("gameName", "engineeringScene");

                if(localDb.getBoolean("engineering_first")){
                    localDb.putBoolean("engineering_first", false);
                    ViewDialog dialog = new ViewDialog();
                    dialog.showDialog(GameActivity.this, PopupType.ENGINEERING_GAME);
                } else {
                    startActivity(intent);
                }
            }if(user.getCurrentField().getFieldValue() == 3){
                intent.putExtra("gameName", "mathScene");

                if(localDb.getBoolean("math_first")){
                    localDb.putBoolean("math_first", false);
                    ViewDialog dialog = new ViewDialog();
                    dialog.showDialog(GameActivity.this, PopupType.MATH_GAME);
                } else {
                    startActivity(intent);
                }
            }
            //startActivity(intent);


        }
        catch (FieldNotSelected e){

        } catch (Exception e){e.printStackTrace();}

    }


    public void isPassed (String val){

//        i = new Intent(getApplication().getApplicationContext(), MainActivity.class);


        //passed = val;

        System.out.println("IN FUNCCCCCCCCCCCCCCCasdasdsadasdsadajkdsjnkasasdasdasdadsadadsajkbsdasdasdasdajksdjksadjkasajkdad");
/*
        if(val.equals("true")){
            i.putExtra("isPassed", "true");
        } else if (val.equals("false")) {
            i.putExtra("isPassed", "false");
        }

        startActivity(i);
*/
        TinyDB localDb = new TinyDB(UnityPlayer.currentActivity.getApplicationContext());
        User user = localDb.getObject("currentUser", User.class);
        user.getLevels().get(user.getLevel()).getUnderLevels().get(user.getUnderLevel()).setGameState(State.UNLOCKED);
        ViewDialog dialog = new ViewDialog();
        dialog.showDialog(UnityPlayer.currentActivity, PopupType.MEDAL);
        localDb.putObject("currentUser", user);

    }
}
