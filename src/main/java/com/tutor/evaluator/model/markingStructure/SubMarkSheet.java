package com.tutor.evaluator.model.markingStructure;

/**
 * Created by Wiranji Dinelka on 8/25/2017.
 */
public class SubMarkSheet {


    private int QNo = 1 ;
    private int gainedMark;
    private int totalMark = 0;
    private Mark[] partitialMark;
    int checkedConditionCount = 0;
    private String feedBack="";


    public SubMarkSheet() {
    }

    public SubMarkSheet(int totalMark, Mark[] partitialMark, String feedBack,int gainedMark,int QNo) {
        this.gainedMark = gainedMark;
        this.QNo  = QNo;
        this.totalMark = totalMark;
        this.partitialMark = partitialMark;
        this.feedBack = feedBack;
    }


    public int getQNo() {
        return QNo;
    }

    public void setQNo(int QNo) {
        this.QNo = QNo;
    }

    public int getGainedMark() {
        return gainedMark;
    }

    public void setGainedMark(int gainedMark) {
        this.gainedMark = gainedMark;
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
