package com.tutor.model.graphParser.GraphGrammarReader;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.ArrayList;

/**
 * Created by Wiranji Dinelka on 8/4/2017.
 */
public class Rule {

    private Graph leftGraph;
    private Graph rightGraph;
    private ArrayList<RuleOperations> ruleOperations = new ArrayList<>();

    public Rule(){}

    public Rule(Graph leftGraph, Graph rightGraph, ArrayList<RuleOperations> ruleOperations) {
        this.leftGraph = leftGraph;
        this.rightGraph = rightGraph;
        this.ruleOperations = ruleOperations;
    }

    public Graph getLeftGraph() {
        return leftGraph;
    }

    public void setLeftGraph(Graph leftGraph) {
        this.leftGraph = leftGraph;
    }

    public Graph getRightGraph() {
        return rightGraph;
    }

    public void setRightGraph(Graph rightGraph) {
        this.rightGraph = rightGraph;
    }

    @XmlElementWrapper(name="RuleOperations")
    @XmlElement(name="RuleOperations")
    public ArrayList<RuleOperations> getRuleOperations() {
        return ruleOperations;
    }

    public void setRuleOperations(ArrayList<RuleOperations> ruleOperations) {
        this.ruleOperations = ruleOperations;
    }
}
