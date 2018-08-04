package space.stemfun.stemfun;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AccountSelect extends AppCompatActivity {

    Button login;
    Button register;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_select);

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        decorView.setSystemUiVisibility(uiOptions);

        login = findViewById(R.id.login);
        register = findViewById(R.id.register);

        mAuth = FirebaseAuth.getInstance();
        final FirebaseUser user = mAuth.getCurrentUser();
        //System.out.println(user.getDisplayName());
        final Intent random = new Intent(this, MainActivity.class);
        final Intent aaa = new Intent(this, MainTest.class);

        
        //startActivity(aaa);

        if(user != null){
            //startActivity(random);
            startActivity(aaa);
            finish();
            //System.out.println(user.getDisplayName());
        }
        final Intent testIntetn = new Intent(this, MainTest.class);
        final Intent signinIntent = new Intent(this, SignInActivity.class);
        final Intent signupIntent = new Intent(this, SignUpAcitvity.class);



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(signinIntent);
//                System.out.println(user.getDisplayName());
                finish();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(signupIntent);
                finish();
            }
        });

    }

}
