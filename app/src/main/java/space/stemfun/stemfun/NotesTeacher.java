package space.stemfun.stemfun;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import space.stemfun.stemfun.Classrooms.ClassroomData;
import space.stemfun.stemfun.Classrooms.Note;


public class NotesTeacher extends Fragment {


    public NotesTeacher() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        TinyDB localDb = new TinyDB(getContext());

        View view = inflater.inflate(R.layout.fragment_notes_teacher, container, false);
//        ClassroomData data = localDb.getObject("classData", ClassroomData.class);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();


        LinearLayout mainContsainer = view.findViewById(R.id.container);

        final float scale = view.getContext().getResources().getDisplayMetrics().density;

        LinearLayout.LayoutParams cardParams = new LinearLayout.LayoutParams((int) (230*scale+0.5f), (int) (230*scale+0.5f));
        cardParams.topMargin =(int) (50*scale+0.5f);


        LinearLayout.LayoutParams imgParams = new LinearLayout.LayoutParams((int) (50*scale+0.5f), (int) (50*scale+0.5f));
        cardParams.topMargin =(int) (50*scale+0.5f);


       // ArrayList <Note> list = data.getNotes();



        for(int  i = 0; i<3; i++){


            CardView card = new CardView(getContext());
            card.setLayoutParams(cardParams);
            //card.setPadding(30, 30,30,30);

            card.setRadius(60);

            final LinearLayout holder = new LinearLayout(getContext());
            holder.setOrientation(LinearLayout.VERTICAL);
            holder.setPadding(30,30,30,30);


            LinearLayout title = new LinearLayout(getContext());
            title.setOrientation(LinearLayout.HORIZONTAL);
            title.setGravity(Gravity.CENTER_VERTICAL);



            ImageView img = new ImageView(getContext());
            img.setLayoutParams(imgParams);
            img.setImageResource(R.drawable.teacher);

            TextView titleTxt = new TextView(getContext());
            titleTxt.setText("Teacher 1");
            titleTxt.setTextSize(25);

            title.addView(img);
            title.addView(titleTxt);

            holder.addView(title);

            TextView message = new TextView(getContext());
            //message.setText(list.get(i).getNote());
            message.setText("AAAAAAAAA");
            message.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Button button = new Button(getContext());
                    button.setText("ADDDDDDDDDDDDDDDDDDDDDDDDDD");
                    holder.addView(button);
                }
            });

            holder.addView(message);

            card.addView(holder);
            mainContsainer.addView(card);

        }





        return view;
    }

}
