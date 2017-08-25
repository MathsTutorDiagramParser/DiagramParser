package com.tutor.evaluator.model.rubricRulesReaderObject;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.ArrayList;

public class XMLSubQuestion {
    private int id;
    private ArrayList<XMLCondition> steps;
    public XMLSubQuestion(){

    }

    public XMLSubQuestion(int id,ArrayList<XMLCondition> xmlConditions){
        this.id=id;
        this.steps =xmlConditions;
    }
    @XmlElementWrapper(name="Steps")
    @XmlElement(name="Step")
    public ArrayList<XMLCondition> getSteps() {
        return steps;
    }

    public void setSteps(ArrayList<XMLCondition> steps) {
        this.steps = steps;
    }

}
