package com.example.timetable_app.adapters;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.example.timetable_app.model.TimetableActivity;

import java.util.HashMap;
import java.util.List;

public class TimetableActivityToListViewAdapter extends ArrayAdapter<TimetableActivity> {
        private HashMap<TimetableActivity, Integer> mIdMap;

        public TimetableActivityToListViewAdapter(Context context, int textViewResourceId,
                                  List<TimetableActivity> objects) {
            super(context, textViewResourceId, objects);

            mIdMap = new HashMap<>();

            for (int i = 0; i < objects.size(); ++i) {
                mIdMap.put(objects.get(i), i);
            }
        }

        @Override
        public long getItemId(int position) {
            TimetableActivity item = getItem(position);
            return mIdMap.get(item);
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }
}
