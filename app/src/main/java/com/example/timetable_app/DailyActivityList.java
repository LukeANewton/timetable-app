package com.example.timetable_app;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.timetable_app.adapters.TimetableActivityToListViewAdapter;
import com.example.timetable_app.model.ActivityTag;
import com.example.timetable_app.model.TimetableActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DailyActivityList#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DailyActivityList extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DailyActivityList() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DailyActivityList.
     */
    public static DailyActivityList newInstance(String param1, String param2) {
        DailyActivityList fragment = new DailyActivityList();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentDailyActivityListLayout = inflater.inflate(R.layout.fragment_daily_activity_list, container, false);

        //get the activities for the day
        List<TimetableActivity> activities = new ArrayList<>();
        activities.add(new TimetableActivity("Lunch",
                "", Calendar.getInstance().getTime(), 60,
                Calendar.getInstance().getTime(), new HashSet<>(
                Collections.singleton(ActivityTag.MEAL))
        ));
        activities.add(new TimetableActivity("TV",
                "", Calendar.getInstance().getTime(), 90,
                Calendar.getInstance().getTime(), new HashSet<>(
                Collections.singleton(ActivityTag.RECREATION))
        ));

        //add activities to view
        final ListView listview = fragmentDailyActivityListLayout.findViewById(R.id.day_activity_list);
        final TimetableActivityToListViewAdapter adapter = new TimetableActivityToListViewAdapter(this.getContext(),
                android.R.layout.simple_list_item_1, activities);
        listview.setAdapter(adapter);


        return fragmentDailyActivityListLayout;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.add_activity_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast myToast = Toast.makeText(getActivity(), "Add", Toast.LENGTH_SHORT);
                myToast.show();
            }
        });
    }
}
