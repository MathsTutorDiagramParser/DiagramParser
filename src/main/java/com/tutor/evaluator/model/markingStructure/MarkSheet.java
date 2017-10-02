package com.tutor.evaluator.model.markingStructure;

import java.util.ArrayList;

public class MarkSheet {

    private double totalMark;
    ArrayList<SubMarkSheet> subMarkSheets;
    String feedback;

    public MarkSheet() {
    }

    public double getTotalMark() {
        return totalMark;
    }

    public void setTotalMark(double totalMark) {
        this.totalMark = totalMark;
    }

    public ArrayList<SubMarkSheet> getSubMarkSheets() {
        return subMarkSheets;
    }

    public void setSubMarkSheets(ArrayList<SubMarkSheet> subMarkSheets) {
        this.subMarkSheets = subMarkSheets;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
