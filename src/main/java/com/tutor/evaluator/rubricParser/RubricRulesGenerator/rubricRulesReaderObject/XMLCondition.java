package com.tutor.evaluator.rubricParser.RubricRulesGenerator.rubricRulesReaderObject;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.ArrayList;

public class XMLCondition {

    private int id;
    private String name;
    private int totalMarks;
    private ArrayList<XMLMarkMethods> xmlMarkSet = new ArrayList<>();

    public XMLCondition(){


    }

    public XMLCondition(int id, String name, int totalMarks, ArrayList<XMLMarkMethods> xmlMarkSet){
        this.id=id;
        this.name=name;
        this.totalMarks=totalMarks;
        this.xmlMarkSet=xmlMarkSet;
    }
    @XmlElement(name="id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @XmlElement(name="conditionName")
    public String getName(){ return name;}

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement(name="totalMarks")
    public int getTotalMarks(){return totalMarks;}

    public void setTotalMarks(int totalMarks) {
        this.totalMarks = totalMarks;
    }
    @XmlElementWrapper(name="MarkSet")
    @XmlElement(name="MarkMethod")
    public ArrayList<XMLMarkMethods> getXmlMarkSet(){return xmlMarkSet;}

    public void setXmlMarkSet(ArrayList<XMLMarkMethods> xmlMarkSet) {
        this.xmlMarkSet = xmlMarkSet;
    }
}
