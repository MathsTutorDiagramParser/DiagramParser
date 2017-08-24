package com.tutor.model.rubricParser.RubricRulesGenerator.rubricRulesReaderObject;

import javax.xml.bind.annotation.XmlElement;

public class XMLBar {
    private Double xLow;
    private Double xHigh;
    private Double yValue;

    public XMLBar(){
    }

    public XMLBar(Double xLow, Double xHigh, Double yValue){
        this.xLow=xLow;
        this.xHigh=xHigh;
        this.yValue=yValue;
    }

    @XmlElement(name="xLow")
    public Double getXLow(){return xLow;}

    public void setXLow(Double xLow) {
        this.xLow = xLow;
    }

    @XmlElement(name="xHigh")
    public Double getXHigh(){return xHigh;}

    public void setXHigh(Double xHigh) {
        this.xHigh = xHigh;
    }
    @XmlElement(name="yValue")
    public Double getYValue(){return yValue;}

    public void setYValue(Double yValue) {
        this.yValue = yValue;
    }
}
