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

                dialog.setMessage("Processing...");
                dialog.show();


                System.out.println("Click");

                newUser = new User();

                newUser.setUsername(username.getText().toString());
                newUser.setTrophies(user.getTrophies());
                newUser.setPassword(currentPassword.getText().toString());
                newUser.setEmail(newUser.getUsername()+"@stemfun.space");
                newUser.setAccountType(user.getAccountType());
                newUser.setName(name.getText().toString());


                String jUser = new Gson().toJson(user);
                String jnewUser = new Gson().toJson(newUser);

                if(!jUser.equals(jnewUser)){
                    System.out.println("CHANGED");
                    try {
                        if(!confirmPassword.getText().toString().equals(user.getPassword())){
                            dialog.dismiss();
                            throw new PasswordsNotSame();
                        }

                        if(!newUser.getName().equals(user.getName())){
                            databaseReference.child("users").child(firebaseUser.getUid()).child("name").setValue(newUser.getName()).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        System.out.println("Ok1");
                                    }
                                }
                            });
                        }
                        if(!newUser.getUsername().equals(user.getUsername())){
                            firebaseUser.updateEmail(newUser.getEmail()).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        System.out.println("Ok2");
                                        databaseReference.child("users").child(firebaseUser.getUid()).setValue(newUser).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if(task.isSuccessful()){
                                                    System.out.println("Ok2InRef");
                                                    dialog.dismiss();
                                                }
                                            }
                                        });

                                    } else {
                                        try{
                                            dialog.dismiss();
                                            throw task.getException();
                                        } catch (FirebaseAuthUserCollisionException e){
                                            username.setError("Username taken");
                                            username.requestFocus();
                                        } catch (Exception e){

                                        }
                                    }
                                }
                            });
                        }
                        if(!newUser.getPassword().equals(user.getPassword())){
                            firebaseUser.updatePassword(newUser.getPassword()).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        System.out.println("Ok3");

                                        databaseReference.child("users").child(firebaseUser.getUid()).child("password").setValue(newUser.getPassword()).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if(task.isSuccessful()){
                                                    System.out.println("Ok3InRef");
                                                    dialog.dismiss();
                                                }
                                            }
                                        });
                                    } else {
                                        try{
                                            dialog.dismiss();
                                            throw task.getException();
                                        } catch (FirebaseAuthWeakPasswordException e){
                                            currentPassword.requestFocus();
                                            currentPassword.setError("Weak password");
                                        } catch (Exception e) {
                                        }
                                    }
                                }
                            });
                        }

                        user = newUser;
                        localDb.putObject("currentUser", user);
                        dialog.dismiss();
                    } catch (PasswordsNotSame e){
                        confirmPassword.setError("Please confirm your password");
                        confirmPassword.requestFocus();
                    }

                } else {
                    dialog.dismiss();
                    System.out.println("No changes");
                }

            }
        });
        return view;
    }

}
