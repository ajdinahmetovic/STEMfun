package space.stemfun.stemfun;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;


public class NoClassroom extends Fragment {



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_no_classroom, container, false);





        final SearchView search = view.findViewById(R.id.classroomSearch);

        search.setQueryHint("Search for classroom");


        search.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                System.out.println(search.getQuery());

            }
        });


        return view;
    }


}
