package com.tutor.evaluator.model.markingStructure;

/**
 * Created by Wiranji Dinelka on 8/25/2017.
 */
public class SubMarkSheet {
    private int totalMark = 0;
    private Mark[] partitialMark;
    int checkedConditionCount = 0;
    private String feedBack="";


    public SubMarkSheet() {
    }

    public SubMarkSheet(int totalMark, Mark[] partitialMark, String feedBack) {
        this.totalMark = totalMark;
        this.partitialMark = partitialMark;
        this.feedBack = feedBack;
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

    public String getFeedBack() {
        return feedBack;
    }

    public void setFeedBack(String feedBack) {
        this.feedBack = feedBack;
    }

}
