package com.tutor.model.graphParser.GraphGrammarGenerator.GraphGrammarReaderObject;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.ArrayList;

/**
 * Created by Wiranji Dinelka on 8/4/2017.
 */
public class XMLProductionRule {

    private int ruleId;
    private XMLGraph leftXMLGraph;
    private XMLGraph rightXMLGraph;
    private ArrayList<XMLRuleOperations> xmlRuleOperations = new ArrayList<>();

    public XMLProductionRule(){}

    public XMLProductionRule(int ruleId, XMLGraph leftXMLGraph, XMLGraph rightXMLGraph, ArrayList<XMLRuleOperations> xmlRuleOperations) {
        this.ruleId = ruleId;
        this.leftXMLGraph = leftXMLGraph;
        this.rightXMLGraph = rightXMLGraph;
        this.xmlRuleOperations = xmlRuleOperations;
    }

    @XmlElement(name="RuleId")
    public int getRuleId() {
        return ruleId;
    }

    public void setRuleId(int ruleId) {
        this.ruleId = ruleId;
    }

    @XmlElement(name="LeftGraph")
    public XMLGraph getLeftXMLGraph() {
        return leftXMLGraph;
    }

    public void setLeftXMLGraph(XMLGraph leftXMLGraph) {
        this.leftXMLGraph = leftXMLGraph;
    }

    @XmlElement(name="RightGraph")
    public XMLGraph getRightXMLGraph() {
        return rightXMLGraph;
    }

    public void setRightXMLGraph(XMLGraph rightXMLGraph) {
        this.rightXMLGraph = rightXMLGraph;
    }

    @XmlElementWrapper(name="RuleOperations")
    @XmlElement(name="RuleOperation")
    public ArrayList<XMLRuleOperations> getXMLRuleOperations() {
        return xmlRuleOperations;
    }

    public void setXMLRuleOperations(ArrayList<XMLRuleOperations> xmlRuleOperations) {
        this.xmlRuleOperations = xmlRuleOperations;
    }
}
