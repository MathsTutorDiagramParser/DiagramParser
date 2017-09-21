package com.tutor.evaluator.model.markingStructure;

/**
 * Created by Madhavi Ruwandika on 8/25/2017.
 */
public class Mark {

    private String condition;
    private int value;
    private String feedBack = "";

    public Mark(String condition, int value) {
        this.condition = condition;
        this.value = value;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
