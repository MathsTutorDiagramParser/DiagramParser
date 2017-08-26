package com.tutor.evaluator.model.RubicRulesPOJOObjects;


import com.tutor.parser.model.util.DiagramType;

import java.util.List;

public class RubricRules {

    List<SubQuestion> subQuestions;

    public RubricRules() {
    }

    public RubricRules(List<SubQuestion> subQuestions) {
        this.subQuestions = subQuestions;
    }

    public List<SubQuestion> getSubQuestions() {
        return subQuestions;
    }

    public void setSubQuestions(List<SubQuestion> subQuestions) {
        this.subQuestions = subQuestions;
    }

}
