package com.tutor.evaluator.model.rubicRulesPOJOObjects;


import java.util.ArrayList;
import java.util.List;

public class RubricRules {

    List<SubQuestion> subQuestions;

    public RubricRules() {
        this.subQuestions = new ArrayList<>();
    }

    public List<SubQuestion> getSubQuestions() {
        return subQuestions;
    }
    public void setSubQuestions(List<SubQuestion> subQuestions) {
        this.subQuestions = subQuestions;
    }

}
