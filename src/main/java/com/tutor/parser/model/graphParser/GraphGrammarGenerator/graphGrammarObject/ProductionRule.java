package com.tutor.parser.model.graphParser.GraphGrammarGenerator.graphGrammarObject;

import java.util.ArrayList;

/**
 * Created by Madhavi Ruwandika on 8/4/2017.
 */
public class ProductionRule {

    private int ruleId;
    private Graph leftGraph;
    private Graph rightGraph;
    private ArrayList<RuleOperations> ruleOperations;

    public ProductionRule(int ruleId, Graph leftGraph, Graph rightGraph, ArrayList<RuleOperations> ruleOperations) {
        this.ruleId = ruleId;
        this.leftGraph = leftGraph;
        this.rightGraph = rightGraph;
        this.ruleOperations = ruleOperations;
    }

    public int getRuleId() {
        return ruleId;
    }

    public void setRuleId(int ruleId) {
        this.ruleId = ruleId;
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
