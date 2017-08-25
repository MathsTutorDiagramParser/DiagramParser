package com.tutor.evaluator.model.rubricRulesReaderObject;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

@XmlRootElement(name = "rubricRules")
public class XMLRubricRules {

    private XMLTaggedValue TaggedValue;
    private ArrayList<XMLSubQuestion> subQuestions;

    public XMLRubricRules() {  }

    public XMLRubricRules(XMLTaggedValue TaggedValue, ArrayList<XMLSubQuestion> xmlSubQuestions) {
        super();
        this.TaggedValue = TaggedValue;
        this.subQuestions = xmlSubQuestions;
    }

    @XmlElement(name="TaggedValue")
    public XMLTaggedValue getXMLTaggedValue() {
        return TaggedValue;
    }

    public void setXMLTaggedValue(XMLTaggedValue xmlTaggedValue) {
        this.TaggedValue = xmlTaggedValue;
    }

    @XmlElementWrapper(name="Question")
    @XmlElement(name="SubQuestion")
    public ArrayList<XMLSubQuestion> getSubQuestions() {
        return subQuestions;
    }

    public void setSubQuestions(ArrayList<XMLSubQuestion> subQuestions) {
        this.subQuestions = subQuestions;
    }

}
