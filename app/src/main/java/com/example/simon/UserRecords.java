package com.example.simon;

public class UserRecords {
    int record = 0;
    boolean win = false;
    int level = 0;
    int stage = 0;


    public UserRecords(int record, int stage, boolean win, int level) {
        this.record = record;
        this.stage = stage;
        this.win = win;
        this.level = level;
    }

    public int getRecord() {
        return record;
    }

    public void setRecord(int record) {
        this.record = record;
    }

    public int getStage() {
        return stage;
    }

    public void setStage(int stage) {
        this.stage = stage;
    }

    public boolean isWin() {
        return win;
    }

    public void setWin(boolean win) {
        this.win = win;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "Record: " + record +
                " \t-\t Stage: " + level +
                " \t-\t Win Game: " + win +
                " \t-\t Level: " + stage;
    }
}
