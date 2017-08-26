package com.tutor.evaluator.model.RubicRulesPOJOObjects;

import com.tutor.evaluator.model.rubricRulesReaderObject.XMLCondition;

import java.util.ArrayList;

/**
 * Created by Madhavi Ruwandika on 8/25/2017.
 */
public class SubQuestion {

    private int id;
    private ArrayList<Step> steps;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Step> getSteps() {
        return steps;
    }

    public void setSteps(ArrayList<Step> steps) {
        this.steps = steps;
    }
}
