package com.tutor.evaluator.model.rubricRulesReaderObject;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

@XmlRootElement(name = "RubricRules")
public class XMLRubricRules {

    private XMLTaggedValue TaggedValue;
    private ArrayList<XMLSubQuestion> subQuestions;
    private ArrayList<XMLConditionType> conditionTypes;
    private ArrayList<XMLSubQuestionType> subQuestionTypes;
    private ArrayList<XMLMarkingMethodType> xmlMarkingMethodTypes;

    public XMLRubricRules(XMLTaggedValue taggedValue, ArrayList<XMLSubQuestion> subQuestions, ArrayList<XMLConditionType> conditionTypes, ArrayList<XMLSubQuestionType> subQuestionTypes, ArrayList<XMLMarkingMethodType> xmlMarkMethods) {
        TaggedValue = taggedValue;
        this.subQuestions = subQuestions;
        this.conditionTypes = conditionTypes;
        this.subQuestionTypes = subQuestionTypes;
        this.xmlMarkingMethodTypes = xmlMarkMethods;
    }

    public XMLRubricRules() {
    }

    @XmlElement(name="TaggedValue")
    public XMLTaggedValue getTaggedValue() {
        return TaggedValue;
    }

    public void setTaggedValue(XMLTaggedValue taggedValue) {
        TaggedValue = taggedValue;
    }

    @XmlElementWrapper(name="ConditionTypes")
    @XmlElement(name="ConditionType")
    public ArrayList<XMLConditionType> getConditions() {
        return conditionTypes;
    }

    public void setConditions(ArrayList<XMLConditionType> conditions) {
        this.conditionTypes = conditions;
    }


    @XmlElementWrapper(name="MarkingMethodsTypes")
    @XmlElement(name="MarkingMethodType")
    public ArrayList<XMLMarkingMethodType> getXmlMarkingMethodTypes() {
        return xmlMarkingMethodTypes;
    }

    public void setXmlMarkingMethodTypes(ArrayList<XMLMarkingMethodType> xmlMarkMethods) {
        this.xmlMarkingMethodTypes = xmlMarkMethods;
    }


    @XmlElementWrapper(name="SubQuestionTypes")
    @XmlElement(name="SubQuestionType")
    public ArrayList<XMLSubQuestionType> getSubQuestionTypes() {
        return subQuestionTypes;
    }

    public void setSubQuestionTypes(ArrayList<XMLSubQuestionType> subQuestionTypes) {
        this.subQuestionTypes = subQuestionTypes;
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
