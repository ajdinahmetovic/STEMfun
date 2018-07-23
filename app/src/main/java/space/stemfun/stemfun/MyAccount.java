package space.stemfun.stemfun;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;

import org.w3c.dom.Text;


public class MyAccount extends Fragment {

    EditText name, username, confirmPassword, currentPassword;
    Button apply;
    TextView trophies, medals;

    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;
    private FirebaseUser firebaseUser;
    ProgressDialog dialog;
    User user;
    TinyDB localDb;

    User newUser;

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

        dialog = new ProgressDialog(getContext());

        View view = inflater.inflate(R.layout.fragment_my_account, container, false);

        name = view.findViewById(R.id.name);
        username = view.findViewById(R.id.username);
        currentPassword = view.findViewById(R.id.password);
        confirmPassword = view.findViewById(R.id.confirmPassword);
        apply = view.findViewById(R.id.apply);
        trophies = view.findViewById(R.id.trophies);
        medals = view.findViewById(R.id.medals);

        localDb = new TinyDB(getContext());

        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        firebaseUser = mAuth.getCurrentUser();
        databaseReference.child("stemfun-54bfc");


        user = localDb.getObject("currentUser", User.class);


        name.setText(user.getName());
        username.setText(user.getUsername());
        currentPassword.setText(user.getPassword());
        name.setText(user.getName());
        username.setText(user.getUsername());
        currentPassword.setText(user.getPassword());
        trophies.setText(String.valueOf(user.getTrophies()));
        medals.setText(String.valueOf(user.getMedals()));


        apply.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                final ProgressDialog progressDialog = new ProgressDialog(getContext());
                progressDialog.setTitle("Processing...");
                progressDialog.show();

                user.setUsername(username.getText().toString());
                user.setName(name.getText().toString());
                user.setPassword(currentPassword.getText().toString());
                user.setEmail(username.getText().toString()+"@stemfun.space");


                if(currentPassword.getText().toString().equals(confirmPassword.getText().toString())){


                    databaseReference.child("users").child(firebaseUser.getUid()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if(task.isSuccessful()){

                                Toast.makeText(getContext(), "Changes saved", Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                                localDb.putObject("currentUser", user);
                            } else if(!task.isSuccessful()){

                                progressDialog.dismiss();
                                try {
                                   throw  task.getException();
                                } catch (FirebaseAuthUserCollisionException e){
                                    username.requestFocus();
                                    username.setError("Username taken");
                                }
                                catch (Exception e){
                                    e.printStackTrace();
                                }
                            }

                        }
                    });


                } else {
                    currentPassword.requestFocus();
                    currentPassword.setError("Passwords not same");
                    progressDialog.dismiss();
                }


            }
        });
        return view;
    }

}
