package com.tutor.model.graphParser.GraphGrammarBuilder;

import com.tutor.model.util.RuleOperation;

/**
 * Created by Madhavi Ruwandika on 8/4/2017.
 */
public class ProductionRule {

    private Graph leftGraph;
    private Graph rightGraph;
    private RuleOperation ruleOperation;


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

    public RuleOperation getRuleOperation() {
        return ruleOperation;
    }

    public void setRuleOperation(RuleOperation ruleOperation) {
        this.ruleOperation = ruleOperation;
    }
}
