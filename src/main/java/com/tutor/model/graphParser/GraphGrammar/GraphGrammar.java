package com.tutor.model.graphParser.GraphGrammar;

import com.tutor.model.graphParser.GraphGrammarGenerator.graphGrammarObject.ProductionRule;
import com.tutor.model.util.DiagramType;

import java.util.List;

/**
 * Created by Madhavi Ruwandika on 8/4/2017.
 */
public abstract class GraphGrammar {

    private List<ProductionRule> ruleList;

    public List<ProductionRule> getRuleList() {
        return ruleList;
    }
    public void setRuleList(List<ProductionRule> ruleList) {
        this.ruleList = ruleList;
    }

    public void loadGrammar(DiagramType diagramType) {

    }

}
