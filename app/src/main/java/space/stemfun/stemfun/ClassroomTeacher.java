package space.stemfun.stemfun;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import space.stemfun.stemfun.Classrooms.ClassroomData;


public class ClassroomTeacher extends Fragment {

     FirebaseDatabase database;
     private DatabaseReference databaseReference;
     private ClassroomData classroomData;
     TinyDB localDb;

    public ClassroomTeacher() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_classroom_teacher, container, false);

        classroomData = new ClassroomData("MyCoolClass");
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();
        Button create = view.findViewById(R.id.create);
        localDb =new TinyDB(getContext());
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        final FirebaseUser firebaseUser = mAuth.getCurrentUser();

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                databaseReference.child("classrooms").child(databaseReference.child("classrooms").push().getKey()).setValue(classroomData).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        Toast.makeText(getContext(), "Sucessfully created classsroom", Toast.LENGTH_LONG).show();
                        //localDb.putBoolean("classroomCreated", true);

                        localDb.putObject("classData", ClassroomData.class);

                        final User user = localDb.getObject("currentUser", User.class);
                        user.setHasClassroom(true);

                        databaseReference.child("users").child(firebaseUser.getUid()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                localDb.putObject("currentUser", user);
                                FragmentManager manager = getActivity().getSupportFragmentManager();
                                FragmentTransaction transaction = manager.beginTransaction();
                                transaction.replace(R.id.mainLayout, new ClassroomPanel()).commit();
                            }
                        });



                    }
                });

            }
        });



        return view;
    }

}
