package space.stemfun.stemfun;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;

public class SignUpAcitvity extends AppCompatActivity {

    EditText name, username, password, confirmPassword;
    Button signup;
    String nameVal, usernameVal, passwordVal, confirmPasswordVal;
    private FirebaseAuth mAuth;
    ProgressDialog dialog;
    private DatabaseReference databaseReference;
    private FirebaseUser firebaseUser;
    RadioButton student, teacher;
    RadioGroup accountType;
    Intent main;
    TinyDB localDb;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        decorView.setSystemUiVisibility(uiOptions);

        user = new User();
        mAuth = FirebaseAuth.getInstance();
        name = findViewById(R.id.name);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        confirmPassword = findViewById(R.id.confirmPassword);
        signup = findViewById(R.id.signupButton);
        localDb = new TinyDB(this);

        main = new Intent(this, MainActivity.class);

        student = findViewById(R.id.student);
        teacher = findViewById(R.id.teacher);
        accountType = findViewById(R.id.accountType);

        databaseReference = FirebaseDatabase.getInstance().getReference();


        dialog = new ProgressDialog(this);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    //nameVal = name.getText().toString().trim();
                    user.setName(name.getText().toString().trim());
                    //usernameVal = username.getText().toString().trim();
                    user.setUsername(username.getText().toString().trim());
                    //passwordVal = password.getText().toString().trim();
                    user.setPassword(password.getText().toString().trim());
                    confirmPasswordVal = confirmPassword.getText().toString().trim();
                    if(student.isChecked()){
                        user.setAccountType(AccountType.STUDENT);
                    } else if (teacher.isChecked()) {
                        user.setAccountType(AccountType.TEACHER);
                    }

                try {
                    if (user.getName().isEmpty()){
                        throw new EmptyName();
                    } else if(user.getUsername().isEmpty()){
                        throw new EmptyUsername();
                    } else if (!(user.getPassword().equals(confirmPasswordVal))) {
                        throw new PasswordsNotSame();
                    } else if (user.getPassword().isEmpty()){
                        throw new EmptyPassword();
                    } else if (!student.isChecked() && !teacher.isChecked()){
                        throw new TypeNotSelected();
                    }
                    dialog.setMessage("Attemting to log you in");
                    dialog.show();
                    user.setEmail(user.getUsername()+"@stemfun.space");
                    mAuth.createUserWithEmailAndPassword(user.getEmail(), user.getPassword()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                //Toast.makeText(getApplicationContext(), "You have been registered", Toast.LENGTH_SHORT);
                                System.out.println("Registered");
                                dialog.dismiss();
                                firebaseUser = mAuth.getCurrentUser();

                                databaseReference.child("users").child(firebaseUser.getUid()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful()){

                                            localDb.putObject("currentUser", user);
                                            startActivity(main);
                                        }
                                    }
                                });

                            } else if (!task.isSuccessful()) {
                                dialog.dismiss();
                                try {
                                    throw task.getException();
                                } catch (FirebaseAuthWeakPasswordException e) {
                                    password.setError("Password is weak");
                                    password.requestFocus();
                                } catch (FirebaseAuthUserCollisionException e) {
                                    username.setError("Username already taken");
                                    username.requestFocus();
                                } catch (FirebaseAuthInvalidCredentialsException e) {
                                    username.setError("Username is invalid");
                                    username.requestFocus();
                                }  catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    });
                } catch (EmptyName e){
                    name.setError("Name cant be empty");
                    name.requestFocus();
                } catch (EmptyUsername e){
                    username.setError("Username cant be empty");
                    username.requestFocus();
                } catch (PasswordsNotSame e){
                    password.setError("Password not same");
                    password.requestFocus();
                } catch (TypeNotSelected e){
                    student.setError("Please select Account Type");
                    teacher.setError("Please Select one of these");
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
