package space.stemfun.stemfun;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class MainTest extends AppCompatActivity {

    private TextView mTextMessage;
/*
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                    FragmentManager manager = getSupportFragmentManager();
                    FragmentTransaction transaction = manager.beginTransaction();
                    switch (item.getItemId()) {
                        case R.id.navigation_home:
                            return true;
                        case R.id.navigation_dashboard:
                            return true;
                        case R.id.my_account:
                            transaction.replace(R.id.frame, new MyAccount()).commit();
                            return true;
                    }
                    return false;
                }
            };
        */
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main_test);

           // BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
           // navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        }

    }

