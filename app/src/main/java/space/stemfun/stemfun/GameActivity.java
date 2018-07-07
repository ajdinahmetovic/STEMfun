package space.stemfun.stemfun;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.stem.spacev1.UnityPlayerActivity;

public class GameActivity extends AppCompatActivity {

    TinyDB localDb;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        localDb = new TinyDB(this);


        user = localDb.getObject("currentUser", User.class);

        try {
            if (user.getCurrentField().getFieldValue() == -1) {
                Intent intent = new Intent(this, RandomGenerator.class);
                intent.putExtra("whatIs", true);
                startActivity(intent);
                finish();
                throw new FieldNotSelected();
            }

            Intent i = getIntent();
            Bundle bundle = i.getExtras();


            Intent intent = new Intent(this, UnityPlayerActivity.class);
            if(user.getCurrentField().getFieldValue() == 0){
                intent.putExtra("gameName", "scienceScene");
            }if(user.getCurrentField().getFieldValue() == 1){
                intent.putExtra("gameName", "techScene");
            }if(user.getCurrentField().getFieldValue() == 2){
                intent.putExtra("gameName", "engineeringScene");
            }if(user.getCurrentField().getFieldValue() == 3){
                intent.putExtra("gameName", "mathScene");
            }
            startActivity(intent);

        }
        catch (FieldNotSelected e){

        } catch (Exception e){e.printStackTrace();}
    }
}
