package com.tutor.evaluator.model.rubricRulesReaderObject;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created by Madhavi Ruwandika on 8/26/2017.
 */
public class XMLMarkingMethodType {

    private String id;
    private String name;

    public XMLMarkingMethodType() {
    }

    public XMLMarkingMethodType(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @XmlElement(name="id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @XmlElement(name="name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
