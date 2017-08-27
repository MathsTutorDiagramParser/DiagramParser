package com.tutor.evaluator.model.rubicRulesPOJOObjects;

import java.util.ArrayList;

/**
 * Created by Madhavi Ruwandika on 8/25/2017.
 */
public class Condition {

    private String name;
    private int totalMarks;
    private ArrayList<MarkingMethod> markingMethods = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotalMarks() {
        return totalMarks;
    }

    public void setTotalMarks(int totalMarks) {
        this.totalMarks = totalMarks;
    }

    public ArrayList<MarkingMethod> getMarkingMethods() {
        return markingMethods;
    }

    public void setMarkingMethods(ArrayList<MarkingMethod> markingMethods) {
        this.markingMethods = markingMethods;
    }
}
