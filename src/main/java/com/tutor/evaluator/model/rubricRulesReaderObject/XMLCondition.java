package com.tutor.evaluator.model.rubricRulesReaderObject;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.ArrayList;

public class XMLCondition {

    private String conditionName;
    private int totalMarks;
    private ArrayList<XMLMarkMethod> markingMethods = new ArrayList<>();

    public XMLCondition(){
    }

    public XMLCondition(String id, String name, int totalMarks, ArrayList<XMLMarkMethod> xmlMarkSet){
        this.conditionName=id;
        this.totalMarks=totalMarks;
        this.markingMethods =xmlMarkSet;
    }

    @XmlElement(name="conditionName")
    public String getId() {
        return conditionName;
    }

    public void setId(String id) {
        this.conditionName= id;
    }

    @XmlElement(name="totalMarks")
    public int getTotalMarks(){return totalMarks;}

    public void setTotalMarks(int totalMarks) {
        this.totalMarks = totalMarks;
    }

    @XmlElementWrapper(name="MarkMethods")
    @XmlElement(name="MarkMethod")
    public ArrayList<XMLMarkMethod> getMarkingMethods(){return markingMethods;}

    public void setMarkingMethods(ArrayList<XMLMarkMethod> markingMethods) {
        this.markingMethods = markingMethods;
    }
}
