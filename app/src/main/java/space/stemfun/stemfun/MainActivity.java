package space.stemfun.stemfun;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button student;
    Button professor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        student = findViewById(R.id.studentButton);
        professor = findViewById(R.id.professorButton);

        final Intent intent = new Intent(this, RegistrationActivity.class);

        student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("type", AccountType.STUDENT);
                startActivity(intent);
            }
        });

        professor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("type", AccountType.PROFESSOR);
            }
        });

    }
}
