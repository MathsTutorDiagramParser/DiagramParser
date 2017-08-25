package com.tutor.evaluator.rubricParser.RubricRulesGenerator.rubricRulesReaderObject;

import javax.xml.bind.annotation.XmlElement;

public class XMLMarkMethods {

    private int id;
    private int gainedMarks;
    private String method;

    public XMLMarkMethods(){

    }
    public XMLMarkMethods(int id, int gainedMarks, String method,XMLData data){
        this.id=id;
        this.gainedMarks=gainedMarks;
        this.method=method;
    }

    @XmlElement(name="id")
    public int getId(){return id;}

    public void setId(int id) {
        this.id = id;
    }

    @XmlElement(name="gainedMarks")
    public int getGainedMarks(){return gainedMarks;}

    public void setGainedMarks(int gainedMarks) {
        this.gainedMarks = gainedMarks;
    }

    @XmlElement(name="method")
    public String getMethod(){return method;}

    public void setMethod(String method) {
        this.method = method;
    }

}
