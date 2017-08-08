package com.tutor.model.graphParser.GraphGrammarGenerator.graphGrammarObject;

import com.tutor.model.util.RuleOperation;

import java.util.ArrayList;

/**
 * Created by Wiranji Dinelka on 8/5/2017.
 */
public class RuleOperations {
    private RuleOperation ruleOperation;
    private ArrayList<Integer> isExists;

    public RuleOperations( RuleOperation ruleOperation, ArrayList<Integer> isExists) {
        this.ruleOperation = ruleOperation;
        this.isExists = isExists;
    }

    public RuleOperation getRuleOperation() {
        return ruleOperation;
    }

    public void setRuleOperation(RuleOperation ruleOperation) {
        this.ruleOperation = ruleOperation;
    }

    public ArrayList<Integer> getIsExists() {
        return isExists;
    }

    public void setIsExists(ArrayList<Integer> isExists) {
        this.isExists = isExists;
    }
}
