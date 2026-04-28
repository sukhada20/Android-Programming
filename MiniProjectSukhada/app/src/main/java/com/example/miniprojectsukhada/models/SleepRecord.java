package com.example.miniprojectsukhada.models;

import java.io.Serializable;

public class SleepRecord implements Serializable {
    public String date;
    public long duration; // in milliseconds
    public String quality;

    public SleepRecord() {}

    public SleepRecord(String date, long duration, String quality) {
        this.date = date;
        this.duration = duration;
        this.quality = quality;
    }

    public String getFormattedDuration() {
        long hours = duration / (1000 * 60 * 60);
        long minutes = (duration % (1000 * 60 * 60)) / (1000 * 60);
        return String.format("%d hrs %d min", hours, minutes);
    }
}