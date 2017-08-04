package com.tutor.model.graphParser.GraphGrammarReader;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.ArrayList;

/**
 * Created by Wiranji Dinelka on 8/4/2017.
 */
public class XMLProductionRule {

    private XMLGraph leftXMLGraph;
    private XMLGraph rightXMLGraph;
    private ArrayList<XMLRuleOperations> XMLRuleOperations = new ArrayList<>();

    public XMLProductionRule(){}

    public XMLProductionRule(XMLGraph leftXMLGraph, XMLGraph rightXMLGraph, ArrayList<XMLRuleOperations> XMLRuleOperations) {
        this.leftXMLGraph = leftXMLGraph;
        this.rightXMLGraph = rightXMLGraph;
        this.XMLRuleOperations = XMLRuleOperations;
    }

    public XMLGraph getLeftXMLGraph() {
        return leftXMLGraph;
    }

    public void setLeftXMLGraph(XMLGraph leftXMLGraph) {
        this.leftXMLGraph = leftXMLGraph;
    }

    public XMLGraph getRightXMLGraph() {
        return rightXMLGraph;
    }

    public void setRightXMLGraph(XMLGraph rightXMLGraph) {
        this.rightXMLGraph = rightXMLGraph;
    }

    @XmlElementWrapper(name="XMLRuleOperations")
    @XmlElement(name="XMLRuleOperations")
    public ArrayList<XMLRuleOperations> getXMLRuleOperations() {
        return XMLRuleOperations;
    }

    public void setXMLRuleOperations(ArrayList<XMLRuleOperations> XMLRuleOperations) {
        this.XMLRuleOperations = XMLRuleOperations;
    }
}
