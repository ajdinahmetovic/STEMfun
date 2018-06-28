package space.stemfun.stemfun;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.stem.spacev1.UnityPlayerActivity;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Intent i = getIntent();

        Bundle bundle  = i.getExtras();

        Intent intent = new Intent(this, UnityPlayerActivity.class);
        intent.putExtra("gameName", bundle.getString("gameName"));
        startActivity(intent);
    }
}
