package com.tutor.evaluator.model.rubricRulesReaderObject;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created by Madhavi Ruwandika on 8/26/2017.
 */
public class XMLSubQuestionType {

    private String Id;
    private String name;

    public XMLSubQuestionType(String id, String name) {
        Id = id;
        this.name = name;
    }

    @XmlElement(name="id")
    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    @XmlElement(name="name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
