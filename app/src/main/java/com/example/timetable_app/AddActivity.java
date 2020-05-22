package com.example.timetable_app;

import android.app.TimePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import com.example.timetable_app.model.ActivityTag;

import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddActivity#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddActivity extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private HashMap<CheckBox, ActivityTag> tags;

    public AddActivity() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment add_activity.
     */
    // TODO: Rename and change types and number of parameters
    public static AddActivity newInstance(String param1, String param2) {
        AddActivity fragment = new AddActivity();
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
        return inflater.inflate(R.layout.fragment_add_activity, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //add a checkbox for each tag
        LinearLayout tagArea = view.findViewById(R.id.add_actiity_tag_area);
        tags = new HashMap<>();
        for(ActivityTag tag : ActivityTag.values()){
            CheckBox cb = new CheckBox(getContext());
            cb.setText(tag.getDescription());
            tagArea.addView(cb);
            tags.put(cb, tag);
        }

        //setup popups for start and end times
        View.OnClickListener timePickerPopup = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog tpd = new TimePickerDialog(getContext(), null, 0, 0, false);
                tpd.show();
            }
        };
        view.findViewById(R.id.add_activity_set_start_time).setOnClickListener(timePickerPopup);
        view.findViewById(R.id.add_activity_set_end_time).setOnClickListener(timePickerPopup);

    }
}
