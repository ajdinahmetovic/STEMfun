package space.stemfun.stemfun;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class Notes extends Fragment {


    public Notes() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_notes, container, false);

        LinearLayout mainContsainer = view.findViewById(R.id.container);

        final float scale = view.getContext().getResources().getDisplayMetrics().density;

        LinearLayout.LayoutParams cardParams = new LinearLayout.LayoutParams((int) (230*scale+0.5f), (int) (230*scale+0.5f));
        cardParams.topMargin =(int) (50*scale+0.5f);


        LinearLayout.LayoutParams imgParams = new LinearLayout.LayoutParams((int) (50*scale+0.5f), (int) (50*scale+0.5f));
        cardParams.topMargin =(int) (50*scale+0.5f);


        for(int  i = 0; i<20; i++){


            CardView card = new CardView(getContext());
            card.setLayoutParams(cardParams);
            //card.setPadding(30, 30,30,30);

            card.setRadius(60);

            LinearLayout holder = new LinearLayout(getContext());
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
            message.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec vehicula pretium ornare. Interdum et malesuada fames ac ante ipsum primis in faucibus. In hac habitasse platea dictumst. Duis non interdum diam, cursus ornare massa. Aliquam placerat semper viverra. Nulla commodo molestie erat, non facilisis nisi blandit vel. Proi");


            holder.addView(message);

            card.addView(holder);
            mainContsainer.addView(card);

        }



        return view;

    }

}
