package com.example.timetable_app.model;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;


public class TimetableActivity {
    //a name describing the activity
    private String name;
    //an optional description of the activity
    private String notes;
    //the time the activity begins
    private Date startTime;
    //the duration of the activity
    private long durationMinutes;
    //a timestamp of when the activity was last updated
    private Date timestamp;
    //a collection of tags that describe the activity
    private HashSet<String> tags;

    public TimetableActivity(String name, String notes, Date startTime,
                             long durationMinutes, Date timestamp, HashSet<String> tags) {
        this.name = name;
        this.notes = notes;
        this.startTime = startTime;
        this.durationMinutes = durationMinutes;
        this.timestamp = timestamp;
        this.tags = tags;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        this.setTimestamp();
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
        this.setTimestamp();
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
        this.setTimestamp();
    }

    public long getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(long durationMinutes) {
        this.durationMinutes = durationMinutes;
        this.setTimestamp();
    }

    public Date getTimestamp() {
        return timestamp;
    }

    private void setTimestamp() {
        this.timestamp = Calendar.getInstance().getTime();
    }

    public HashSet getTags() {
        return tags;
    }

    public void setTags(HashSet<String> tags) {
        this.tags = tags;
        this.setTimestamp();
    }

    public void addTag(String tag) {
        this.tags.add(tag);
    }

    public void removeTag(String tag) {
        this.tags.remove(tag);
    }

    @Override
    public String toString() {
        final int NUM_MILLISECONDS_IN_ONE_MINUTE = 60000;

        StringBuilder s = new StringBuilder();
        s.append(name).append('\n')
                .append("Notes:\n").append(notes).append('\n')
                .append("Start Time:").append(startTime).append('\n');

        if(durationMinutes > 0)
            s.append("End Time:").append(new Date(startTime.getTime() + (durationMinutes * NUM_MILLISECONDS_IN_ONE_MINUTE))).append('\n');

        s.append("Tags:").append(tags).append('\n')
                .append("last edited:").append(timestamp);

       return s.toString();
    }
}
