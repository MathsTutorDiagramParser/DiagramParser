package com.tutor.model.graphParser.GraphGrammarGenerator.graphGrammarObject;

import java.util.ArrayList;

/**
 * Created by Madhavi Ruwandika on 8/4/2017.
 */
public class ProductionRule {

    private Graph leftGraph;
    private Graph rightGraph;
    private ArrayList<RuleOperations> ruleOperations;

    public ProductionRule(Graph leftGraph, Graph rightGraph, ArrayList<RuleOperations> ruleOperations) {
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

    public ArrayList<RuleOperations> getRuleOperations() {
        return ruleOperations;
    }

    public void setRuleOperations(ArrayList<RuleOperations> ruleOperations) {
        this.ruleOperations = ruleOperations;
    }
}
