package com.tutor.evaluator.model.rubicRulesPOJOObjects;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created by Madhavi Ruwandika on 8/25/2017.
 */
public class MarkingMethod {

    private int id;
    private int gainedMarks;
    private String method;


    @XmlElement(name="id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @XmlElement(name="gainedMarks")
    public int getGainedMarks() {
        return gainedMarks;
    }

    public void setGainedMarks(int gainedMarks) {
        this.gainedMarks = gainedMarks;
    }

    @XmlElement(name="name")
    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
