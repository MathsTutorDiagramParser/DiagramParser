package com.tutor.model.rubricParser.RubricRulesGenerator.rubricRulesReaderObject;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.ArrayList;

public class XMLData {

    private String xAxis;
    private String yAxis;
    private ArrayList<XMLBar>  bars = new ArrayList<>();

    public XMLData(){

    }
    public  XMLData(String xAxis, String yAxis,ArrayList<XMLBar>  bars){
        this.xAxis=xAxis;
        this.yAxis=yAxis;
        this.bars=bars;
    }
    @XmlElement(name="xAxis")
    public String getxAxis(){return xAxis;}

    public void setxAxis(String xAxis) {
        this.xAxis = xAxis;
    }

    @XmlElement(name="yAxis")
    public String getyAxis(){return yAxis;}

    public void setyAxis(String yAxis) {
        this.yAxis = yAxis;
    }

    @XmlElementWrapper(name="BARS")
    @XmlElement(name="BAR")
    public ArrayList<XMLBar> getBars(){return bars;}

    public void setBars(ArrayList<XMLBar> bars) {
        this.bars = bars;
    }
}
