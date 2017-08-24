package com.tutor.evaluator.rubricParser.RubricRulesGenerator.rubricRulesReaderObject;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

@XmlRootElement(name = "RubricRules")
public class XMLRubricRules {

    private XMLTaggedValue xmlTaggedValue;
    private ArrayList<XMLCondition> xmlConditions;

    public XMLRubricRules() {  }

    public XMLRubricRules(XMLTaggedValue xmlTaggedValue, ArrayList<XMLCondition> xmlConditions) {
        super();
        this.xmlTaggedValue = xmlTaggedValue;
        this.xmlConditions= xmlConditions;
    }


    @XmlElement(name="TaggedValue")
    public XMLTaggedValue getXMLTaggedValue() {
        return xmlTaggedValue;
    }

    public void setXMLTaggedValue(XMLTaggedValue xmlTaggedValue) {
        this.xmlTaggedValue = xmlTaggedValue;
    }

    @XmlElementWrapper(name="Conditions")
    @XmlElement(name="Condition")
    public ArrayList<XMLCondition> getXmlConditions() {
        return xmlConditions;
    }

    public void setXmlConditions(ArrayList<XMLCondition> xmlConditions) {
        this.xmlConditions = xmlConditions;
    }

}
