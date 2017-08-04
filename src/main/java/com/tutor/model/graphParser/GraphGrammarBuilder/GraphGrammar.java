package com.tutor.model.graphParser.GraphGrammarBuilder;

import com.tutor.model.graphParser.GraphGrammarReader.Rule;

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
}
