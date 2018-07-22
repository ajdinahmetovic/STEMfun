package space.stemfun.stemfun;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FirebaseHelper {

    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;
    private FirebaseUser firebaseUser;
    User user;

    TinyDB localDb;



    Context context;

    FirebaseHelper(Context context){
        this.context = context;
        localDb = new TinyDB(context);
        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        firebaseUser = mAuth.getCurrentUser();
        user = localDb.getObject("currentUser", User.class);

    }


    void syncUser (){

    }
}
