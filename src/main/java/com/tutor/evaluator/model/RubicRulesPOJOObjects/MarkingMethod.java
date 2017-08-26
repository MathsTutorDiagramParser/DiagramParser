package com.tutor.evaluator.model.RubicRulesPOJOObjects;

/**
 * Created by Madhavi Ruwandika on 8/25/2017.
 */
public class MarkingMethod {

    private int id;
    private int gainedMarks;
    private String method;

    public MarkingMethod() {
    }

    public MarkingMethod(int id, int gainedMarks, String method) {
        this.id = id;
        this.gainedMarks = gainedMarks;
        this.method = method;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGainedMarks() {
        return gainedMarks;
    }

    public void setGainedMarks(int gainedMarks) {
        this.gainedMarks = gainedMarks;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
