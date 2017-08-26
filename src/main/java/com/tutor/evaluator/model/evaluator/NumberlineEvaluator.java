package com.tutor.evaluator.model.evaluator;

import com.tutor.evaluator.model.RubicRulesPOJOObjects.Step;
import com.tutor.evaluator.model.RubicRulesPOJOObjects.SubQuestion;
import com.tutor.evaluator.model.constants.StepConstant;
import com.tutor.evaluator.model.markingStructure.Mark;
import com.tutor.evaluator.model.markingStructure.MarkSheet;
import com.tutor.evaluator.model.RubicRulesPOJOObjects.RubricRules;
import com.tutor.parser.model.feedback.FeedBack;
import com.tutor.parser.model.graphParser.DiagramStructure.AbstractDiagramStructure;
import com.tutor.parser.model.graphParser.DiagramStructure.NumberLine.AbstractNumberLineStructure;
import com.tutor.parser.model.graphParser.DiagramStructure.NumberLine.MarkPoint;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Madhavi Ruwandika on 8/25/2017.
 */
public class NumberlineEvaluator extends Evaluator {

    Mark[] marks = null;
    AbstractDiagramStructure studentStructure;
    AbstractDiagramStructure teacherStructure;
    String subQfeedback ;
    int totalSubQ = 0;

    public MarkSheet[] evaluate(MarkSheet markSheet, AbstractDiagramStructure studentStructure,
                              AbstractDiagramStructure teacherStructure, RubricRules rubricRules) {
        MarkSheet[] markSheets = new MarkSheet[rubricRules.getSubQuestions().size()];

        this.studentStructure = studentStructure;
        this.teacherStructure = teacherStructure;


        for (SubQuestion subQuestion : rubricRules.getSubQuestions()){
            List<Step> steps = subQuestion.getSteps();
            totalSubQ = 0;
            subQfeedback = "";
            MarkSheet subQmarkSheet = new MarkSheet();

            for (int i = 0; i < steps.size(); i++) {

                marks = new Mark[steps.size()];
                Step step = steps.get(i);

                if (step.getName().equals(StepConstant.NUMBERLINE_MARK_INEQUALITY)) {
                    marks[i] = inequalityCheck(step);
                } else if (step.getName().equals(StepConstant.NUMBERLINE_FINAL_ANSWER)) {

                }
                subQmarkSheet = new MarkSheet(totalSubQ, marks, subQfeedback);
            }
            markSheets[subQuestion.getId()] =  subQmarkSheet;
        }
        return markSheets;
    }


    public Mark inequalityCheck(Step step){
        boolean isCorrectLeftEnd = false;
        boolean isCorrectRightEnd = false;
        boolean isFoundLeftEnd = false;
        boolean isFoundRightEnd = false;

        List<MarkPoint> studentMarkPoints = ((AbstractNumberLineStructure)studentStructure).getMarkPointList();
        List<MarkPoint> teacherMarkPoints = ((AbstractNumberLineStructure)teacherStructure).getMarkPointList();

        if(studentMarkPoints != null) {
            for (int k = 0; k < 2; k++) {
                if(isFoundLeftEnd) {
                    if (studentMarkPoints.get(k).endOFTheThickLine.equals("LEFT")) {
                        for (int j = 0; j < 2; j++) {
                            if (teacherMarkPoints.get(j).endOFTheThickLine.equals("LEFT")) {
                                if ((studentMarkPoints.get(k).isInfinity() == teacherMarkPoints.get(j).isInfinity())) {
                                    if(studentMarkPoints.get(k).getText().equals(teacherMarkPoints.get(j).getText())){
                                        isCorrectLeftEnd = true;
                                    }else {
                                        isCorrectLeftEnd = false;
                                        subQfeedback = "Left end of the mark line should have value of "+teacherMarkPoints.get(j).getText()+".";
                                    }
                                    isFoundLeftEnd = true;
                                } else {
                                    if (teacherMarkPoints.get(j).isInfinity()) {
                                        subQfeedback = "Left end of the mark line should goes to negetive infinity.";
                                    } else {
                                        subQfeedback = "Left end point of the mark line should be " + teacherMarkPoints.get(j).getText()+".";
                                    }
                                }
                                break;
                            }
                        }
                    }
                }
                if(isFoundRightEnd){
                    if (studentMarkPoints.get(k).endOFTheThickLine.equals("RIGHT")) {
                        for (int j = 0; j < 2; j++) {
                            if (teacherMarkPoints.get(j).endOFTheThickLine.equals("RIGHT")) {
                                if ((studentMarkPoints.get(k).isInfinity() == teacherMarkPoints.get(j).isInfinity())) {
                                    if(!studentMarkPoints.get(k).isInfinity()){
                                        if(studentMarkPoints.get(k).getText().equals(teacherMarkPoints.get(j).getText())){
                                            isCorrectRightEnd = true;
                                        }else {
                                            isCorrectRightEnd = false;
                                            subQfeedback = "Right end of the mark line should have value of "+teacherMarkPoints.get(j).getText()+".";
                                        }
                                    }
                                    isFoundRightEnd = true;
                                } else {
                                    if (teacherMarkPoints.get(j).isInfinity()) {
                                        subQfeedback = "Right end of the mark line should goes to positive infinity.";
                                    } else {
                                        subQfeedback = "Right end point of the mark line should be " + teacherMarkPoints.get(j).getText()+".";
                                    }
                                }
                                break;
                            }
                        }
                    }
                }
            }
            for(int x=0;x<step.getMarkingMethods().size();x++) {
                if (step.getMarkingMethods().get(x).equals("ALL")) {
                    if (isCorrectLeftEnd && isCorrectRightEnd) {
                        totalSubQ += step.getMarkingMethods().get(x).getGainedMarks();
                        return new Mark(step.getName(), step.getMarkingMethods().get(x).getGainedMarks());
                    } else {
                        return null;
                    }
                }
            }
        } else {
            subQfeedback = "You haven't mark inequality to give mark.";
            return null;
        }

        return null;
    }

}
