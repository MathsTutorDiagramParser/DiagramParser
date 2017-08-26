package com.tutor.evaluator.model.evaluator;

import com.tutor.evaluator.model.markingStructure.MarkSheet;
import com.tutor.evaluator.model.rubicRulesPOJOObjects.RubricRules;
import com.tutor.parser.model.feedback.FeedBack;
import com.tutor.parser.model.graphParser.DiagramStructure.AbstractDiagramStructure;

import java.util.List;

/**
 * Created by Madhavi Ruwandika on 8/25/2017.
 */
public  class Evaluator {

    public MarkSheet evaluate(MarkSheet markSheet, AbstractDiagramStructure studentStructure,
                              AbstractDiagramStructure teacherStructure, RubricRules rubricRules, List<FeedBack> feedBacks){
        return markSheet;
    }




}
