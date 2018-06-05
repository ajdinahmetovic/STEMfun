package space.stemfun.stemfun;

import android.app.ProgressDialog;
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
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

public class SignUpAcitvity extends AppCompatActivity {

    EditText name, username, password, confirmPassword;
    Button signup;
    String nameVal, usernameVal, passwordVal, confirmPasswordVal;
    private FirebaseAuth mAuth;
    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_acitvity);

        EditText fields [];
        fields = new EditText[]{name, username, password, confirmPassword};

        mAuth = FirebaseAuth.getInstance();

        name = findViewById(R.id.name);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        confirmPassword = findViewById(R.id.confirmPassword);
        signup = findViewById(R.id.signupButton);
        dialog = new ProgressDialog(this);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nameVal = name.getText().toString().trim();
                usernameVal = username.getText().toString().trim();
                passwordVal = password.getText().toString().trim();
                confirmPasswordVal = confirmPassword.getText().toString().trim();

                if(!(passwordVal.equals(confirmPasswordVal))){
                    confirmPassword.setError("Passwords are different");
                    confirmPassword.requestFocus();
                }

                dialog.setMessage("Registering");
                dialog.show();
                usernameVal = usernameVal+"@stemfun.space";
                mAuth.createUserWithEmailAndPassword(usernameVal, passwordVal).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(getApplicationContext(), "You have been registered", Toast.LENGTH_SHORT);
                            System.out.println("Registered");
                            dialog.dismiss();

                        } else if (!task.isSuccessful()) {
                            dialog.dismiss();
                            try{
                                throw task.getException();
                            } catch (FirebaseAuthWeakPasswordException e){
                                password.setError("Weak password");
                                password.requestFocus();
                            } catch (FirebaseAuthUserCollisionException e){
                                username.setError("Username taken");
                                username.requestFocus();
                            } catch (FirebaseAuthInvalidCredentialsException e){
                                username.setError("Username is invalid");
                                username.requestFocus();
                            }catch (Exception e) {
                                e.printStackTrace();
                            }


                        }
                    }
                });

            }
        });










    }
}
