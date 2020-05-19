package com.example.timetable_app;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.timetable_app.model.ActivityTag;
import com.example.timetable_app.model.TimetableActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;


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
    // TODO: Rename and change types and number of parameters
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
                Calendar.getInstance().getTime(), new HashSet<ActivityTag>(
                Collections.singleton(ActivityTag.MEAL))
        ));
        List<String> list = new ArrayList<>();
        for(TimetableActivity activity : activities)
            list.add(activity.toString());

        //add activities to view
        final ListView listview = (ListView) fragmentDailyActivityListLayout.findViewById(R.id.day_activity_list);
        final StableArrayAdapter adapter = new StableArrayAdapter(this.getContext(),
                android.R.layout.simple_list_item_1, list);
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

    private class StableArrayAdapter extends ArrayAdapter<String> {
        HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

        public StableArrayAdapter(Context context, int textViewResourceId,
                                  List<String> objects) {
            super(context, textViewResourceId, objects);
            for (int i = 0; i < objects.size(); ++i) {
                mIdMap.put(objects.get(i), i);
            }
        }

        @Override
        public long getItemId(int position) {
            String item = getItem(position);
            return mIdMap.get(item);
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

    }
}
