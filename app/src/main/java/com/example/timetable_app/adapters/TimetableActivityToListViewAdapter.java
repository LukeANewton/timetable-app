package com.example.timetable_app.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.timetable_app.R;
import com.example.timetable_app.model.ActivityTag;
import com.example.timetable_app.model.TimetableActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int NUM_MILLISECONDS_IN_ONE_MINUTE = 60000;
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

        // Get the data item for this position
        TimetableActivity activity = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_list_item, parent, false);
        }
        // Lookup view for data population
        TextView activityName = convertView.findViewById(R.id.activityName);
        TextView activityTags = convertView.findViewById(R.id.activityTags);
        TextView activityStartTime = convertView.findViewById(R.id.activityStart);
        TextView activityEndTime = convertView.findViewById(R.id.activityEnd);
        TextView activityNotes = convertView.findViewById(R.id.activityNotes);
        TextView activityLastEdited = convertView.findViewById(R.id.activityLastEdited);

        // Populate the data into the template view using the data object
        activityName.setText(activity.getName());
        for(ActivityTag tag: activity.getTags())
            activityTags.append(" " + tag.getDescription());
        activityStartTime.append(" " + sdf.format(activity.getStartTime()));
        activityEndTime.append(" " +
                sdf.format(new Date(activity.getStartTime().getTime() +
                        (activity.getDurationMinutes() * NUM_MILLISECONDS_IN_ONE_MINUTE))));
        if(activity.getNotes().isEmpty())
            activityNotes.append(" N/A");
        else
            activityNotes.append(" " + activity.getNotes());
        activityLastEdited.append(" " + activity.getTimestamp().toString());

        // Return the completed view to render on screen
        return convertView;
    }
}
