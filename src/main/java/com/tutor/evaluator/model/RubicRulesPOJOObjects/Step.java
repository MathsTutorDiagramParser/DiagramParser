package com.tutor.evaluator.model.RubicRulesPOJOObjects;

import com.tutor.evaluator.model.rubricRulesReaderObject.XMLMarkMethods;

import java.util.ArrayList;

/**
 * Created by Madhavi Ruwandika on 8/25/2017.
 */
public class Step {

    private String name;
    private int totalMarks;
    private ArrayList<MarkingMethod> markingMethods = new ArrayList<>();
}
