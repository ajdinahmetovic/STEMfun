package space.stemfun.stemfun;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    User user;
    TinyDB localDb;
    BottomNavigationView navigation;
    int k=0;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            //transaction.setCustomAnimations(R.anim.pop_enter, R.anim.pop_exit, R.anim.enter, R.anim.exit);
            switch (item.getItemId()) {
                case R.id.space:
                        transaction.replace(R.id.mainLayout, new MainFragment());
                        transaction.commit();
                        return true;
                case R.id.account:

                        transaction.replace(R.id.mainLayout, new MyAccount());
                        transaction.commit();
                        return true;

                case R.id.classroom:
                    if(user.getAccountType() == UserType.STUDENT) {
                        transaction.replace(R.id.mainLayout, new Classroom());
                    } else if (user.getAccountType() == UserType.TEACHER) {
                        if(user.isHasClassroom()){
                            transaction.replace(R.id.mainLayout, new ClassroomPanel());
                        }else {
                            transaction.replace(R.id.mainLayout, new ClassroomTeacher());
                        }

                    }
                    transaction.commit();
                    return true;
            }
            return false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        localDb = new TinyDB(this);
        user = localDb.getObject("currentUser",User.class);


        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        decorView.setSystemUiVisibility(uiOptions);

         navigation = (BottomNavigationView) findViewById(R.id.bottomNavigation);
         navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        transaction.replace(R.id.mainLayout, new MainFragment()).commit();


    }
}
