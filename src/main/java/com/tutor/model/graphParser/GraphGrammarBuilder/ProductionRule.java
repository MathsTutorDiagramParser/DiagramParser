package com.tutor.model.graphParser.GraphGrammarBuilder;

import com.tutor.model.util.RuleOperation;

import java.util.List;

/**
 * Created by Madhavi Ruwandika on 8/4/2017.
 */
public class ProductionRule {

    private Graph leftGraph;
    private Graph rightGraph;
    private List<RuleOperation> ruleOperations;

    public ProductionRule(Graph leftGraph, Graph rightGraph, List<RuleOperation> ruleOperations) {
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

    public List<RuleOperation> getRuleOperation() {
        return ruleOperations;
    }

    public void setRuleOperation(List<RuleOperation> ruleOperations) {
        this.ruleOperations = ruleOperations;
    }
}
