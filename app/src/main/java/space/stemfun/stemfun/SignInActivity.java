package space.stemfun.stemfun;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignInActivity extends AppCompatActivity {

    Button signin;
    EditText username, password;
    String usernameVal, passwordVal;

    ProgressDialog dialog;
    FirebaseAuth mAuth;
    FirebaseUser firebaseUser;

    DatabaseReference reference;

    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        mAuth = FirebaseAuth.getInstance();

        user = new User();

        reference = FirebaseDatabase.getInstance().getReference();
        reference.child("stemfun-54bfc");

        //setContentView(R.layout.activity_account_select);
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        decorView.setSystemUiVisibility(uiOptions);

        username = findViewById(R.id.username);
        signin = findViewById(R.id.signinButton);

        password = findViewById(R.id.password);

        dialog = new ProgressDialog(this);
        final Intent intent = new Intent(this, MainActivity.class);

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.setMessage("Attempting to log you in ... ");
                dialog.show();

                usernameVal = username.getText().toString().trim();
                passwordVal = password.getText().toString().trim();
                try {
                    if (usernameVal.isEmpty()) {
                        dialog.dismiss();
                        throw new EmptyUsername();
                    } else if (passwordVal.isEmpty()) {
                        dialog.dismiss();
                        throw new EmptyPassword();
                    }
                    mAuth.signInWithEmailAndPassword(usernameVal + "@stemfun.space", passwordVal).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {


                            if (task.isSuccessful()) {

                                firebaseUser = mAuth.getCurrentUser();
                                System.out.println("UUIID" + firebaseUser.getUid());



                                reference.child("users").child(firebaseUser.getUid()).addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        user = dataSnapshot.getValue(User.class);


                                        TinyDB localDb = new TinyDB(getApplicationContext());
                                        localDb.putObject("currentUser", user);

                                        Toast.makeText(getApplicationContext(), "LoggedIn", Toast.LENGTH_SHORT).show();
                                        startActivity(intent);
                                        finish();
                                        dialog.dismiss();
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });
                            } else if (!task.isSuccessful()) {
                                Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                                try {
                                    throw task.getException();
                                } catch (FirebaseAuthInvalidUserException e) {
                                    username.setError("Username or password is wrong");
                                    username.requestFocus();
                                } catch (FirebaseAuthInvalidCredentialsException e){
                                    password.setError("Username or password is wrong");
                                    password.requestFocus();
                                } catch (Exception e) {
                                }
                            }

                        }
                    });
                } catch (EmptyUsername e){
                    username.setError("Username cant be empty");
                    username.requestFocus();
                } catch (EmptyPassword e){
                    password.setError("Password cant be empty");
                    password.requestFocus();
                } catch (Exception e){
                    e.printStackTrace();
                }

            }
        });
    }

}
