package com.tutor.evaluator.model.rubricRulesReaderObject;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.ArrayList;

public class XMLSubQuestion {
    private int id;
    private String name;
    private ArrayList<XMLCondition> conditions;

    public XMLSubQuestion(){

    }

    public XMLSubQuestion(int id, String name, ArrayList<XMLCondition> conditions) {
        this.id = id;
        this.name = name;
        this.conditions = conditions;
    }

    @XmlElement(name="id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @XmlElement(name="name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElementWrapper(name="Conditions")
    @XmlElement(name="Condition")
    public ArrayList<XMLCondition> getConditions() {
        return conditions;
    }

    public void setConditions(ArrayList<XMLCondition> conditions) {
        this.conditions = conditions;
    }

}
