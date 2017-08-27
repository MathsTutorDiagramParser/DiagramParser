package com.tutor.evaluator.model.markingStructure;

public class MarkSheet {

    private int totalMark = 0;
    private Mark[] partitialMark;
    int checkedConditionCount = 0;
    private String feebback="";

    public MarkSheet() {
    }

    public MarkSheet(int totalMark, Mark[] partitialMark, String feebback) {
        this.totalMark = totalMark;
        this.partitialMark = partitialMark;
        this.feebback = feebback;
    }

    public int getTotalMark() {
        return totalMark;
    }

    public void setTotalMark(int totalMark) {
        this.totalMark = totalMark;
    }

    public Mark[] getPartitialMark() {
        return partitialMark;
    }

    public void setPartitialMark(Mark[] partitialMark) {
        this.partitialMark = partitialMark;
    }

    public void updatePartitialMark(Mark mark){
        totalMark += mark.getValue();
        partitialMark[checkedConditionCount]= mark;
        checkedConditionCount++;
    }

    public String getFeebback() {
        return feebback;
    }

    public void setFeebback(String feebback) {
        this.feebback = feebback;
    }

}
