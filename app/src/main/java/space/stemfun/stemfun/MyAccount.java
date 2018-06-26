package space.stemfun.stemfun;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;


public class MyAccount extends Fragment {

    EditText name, username, newPassword, currentPassword;
    Button apply;

    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;
    private FirebaseUser firebaseUser;
    User user;
    TinyDB localDb;

    public MyAccount() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_my_account, container, false);

        name = view.findViewById(R.id.name);
        username = view.findViewById(R.id.username);
        currentPassword = view.findViewById(R.id.password);

        localDb = new TinyDB(getContext());

        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        firebaseUser = mAuth.getCurrentUser();


        user = localDb.getObject("currentUser", User.class);



        name.setText(user.getName());
        username.setText(user.getUsername());
        currentPassword.setText(user.getPassword());


        return view;
    }

}
