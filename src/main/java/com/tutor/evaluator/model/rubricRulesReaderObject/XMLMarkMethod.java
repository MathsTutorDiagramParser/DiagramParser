package com.tutor.evaluator.model.rubricRulesReaderObject;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created by Wiranji Dinelka on 8/26/2017.
 */
public class XMLMarkMethod {
    private int  id;
    private int gainedMarks;
    private String method;

    public XMLMarkMethod(){

    }
    public XMLMarkMethod(int  id, int gainedMarks, String method){
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
