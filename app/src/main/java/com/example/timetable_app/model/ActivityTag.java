package com.example.timetable_app.model;

public enum ActivityTag {
    RECREATION("recreation"),
    CHORES("chores"),
    HYGIENE("hygiene"),
    MEAL("MEAL"),
    PROJECTS("self projects"),
    SCHOOL("school-related"),
    WORK("work-related");

    private final String description;

    ActivityTag(String description){
        this.description = description;
    }

    String getDescription(){
        return this.description;
    }
}