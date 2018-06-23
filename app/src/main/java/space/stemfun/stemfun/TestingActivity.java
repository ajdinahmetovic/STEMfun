package space.stemfun.stemfun;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class TestingActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing);

        img = findViewById(R.id.image);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();


        UserProfileChangeRequest update = new UserProfileChangeRequest.Builder().setDisplayName("KUGLA").setPhotoUri(Uri.parse("https://scontent-vie1-1.xx.fbcdn.net/v/t1.0-9/32836122_450370002079993_4769055806291181568_n.jpg?_nc_cat=0&oh=bf2270f141ebc49309dd7552b4de12ad&oe=5BB1F7FF")).build();

        img.setImageURI(user.getPhotoUrl());

        /*
        user.updateProfile(update).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    System.out.println("Gaood");
                }
            }
        });
*/


    }
}
