package com.tutor.evaluator.model.evaluator;

import com.tutor.evaluator.model.constants.MarkingCondition;
import com.tutor.evaluator.model.constants.NumberLineEvaluatorConstant;
import com.tutor.evaluator.model.markingStructure.SubMarkSheet;
import com.tutor.evaluator.model.rubicRulesPOJOObjects.Condition;
import com.tutor.evaluator.model.rubicRulesPOJOObjects.SubQuestion;
import com.tutor.evaluator.model.markingStructure.Mark;
import com.tutor.evaluator.model.markingStructure.MarkSheet;
import com.tutor.evaluator.model.rubicRulesPOJOObjects.RubricRules;
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
    int totalQMark = 0;
    int totalQGainMark = 0;
    int totalSubQMark = 0;
    int totalSubQGainMark = 0;

    public MarkSheet evaluate(AbstractDiagramStructure studentStructure,
                              AbstractDiagramStructure teacherStructure, RubricRules rubricRules,String feedBacks) {

        MarkSheet markSheet = new MarkSheet();
        this.studentStructure = studentStructure;
        this.teacherStructure = teacherStructure;

        ArrayList<SubMarkSheet> subMarkSheets = new ArrayList<>();
        int itr=1;
        for (SubQuestion subQuestion : rubricRules.getSubQuestions()){
            List<Condition> conditions = subQuestion.getConditions();
            totalSubQMark = 0;
            totalSubQGainMark = 0;
            subQfeedback = "";
            marks = new Mark[conditions.size()];
            for (int i = 0; i < conditions.size(); i++) {
                totalSubQGainMark += conditions.get(i).getTotalMarks();
                Condition condition = conditions.get(i);
                if (condition.getName().equals(MarkingCondition.NUMBERLINE_MARK_INEQUALITY)) {
                    marks[i] = inequalityCheck(condition);
                }
            }
            SubMarkSheet subQmarkSheet= new SubMarkSheet(totalSubQMark, marks, subQfeedback,totalSubQGainMark,itr);
            itr++;
            subMarkSheets.add(subQmarkSheet);
            totalQGainMark+=totalSubQGainMark;
            totalQMark+=totalSubQMark;
        }

        markSheet.setSubMarkSheets(subMarkSheets);
        markSheet.setTotalMark_gainMark(totalQGainMark);
        markSheet.setTotalMark(totalQMark);
        markSheet.setFeedback(feedBacks);
        return markSheet;
    }


    public Mark inequalityCheck(Condition condition){
        boolean isCorrectLeftEnd = false;
        boolean isCorrectRightEnd = false;
        boolean isFoundLeftEnd = false;
        boolean isFoundRightEnd = false;

        List<MarkPoint> studentMarkPoints = ((AbstractNumberLineStructure)studentStructure).getMarkPointList();
        List<MarkPoint> teacherMarkPoints = ((AbstractNumberLineStructure)teacherStructure).getMarkPointList();

        if(studentMarkPoints != null && studentMarkPoints.size()!=0 ) {
            for (int k = 0; k < 2; k++) {
                if(!isFoundLeftEnd) {
                    if (studentMarkPoints.get(k).endOFTheThickLine.equals(NumberLineEvaluatorConstant.LEFT)) {
                        for (int j = 0; j < 2; j++) {
                            if (teacherMarkPoints.get(j).endOFTheThickLine.equals(NumberLineEvaluatorConstant.LEFT)) {
                                if ((studentMarkPoints.get(k).isInfinity() == teacherMarkPoints.get(j).isInfinity())) {
                                    if(!studentMarkPoints.get(k).isInfinity()) {
                                        try {
                                            if(studentMarkPoints.get(k).getText()!=null){
                                                double val = Double.parseDouble(studentMarkPoints.get(k).getText().getText());
                                                if (studentMarkPoints.get(k).getText().getText().equals(teacherMarkPoints.get(j).getText().getText())) {
                                                    if (studentMarkPoints.get(k).isFilled == teacherMarkPoints.get(j).isFilled) {
                                                        isCorrectLeftEnd = true;
                                                    } else {
                                                        isCorrectLeftEnd = false;
                                                        String filledValue = ((teacherMarkPoints.get(j).isFilled == true) ? NumberLineEvaluatorConstant.FILLED : NumberLineEvaluatorConstant.NON_FILLED);
                                                        if (isFoundRightEnd && isCorrectRightEnd) {
                                                            subQfeedback = NumberLineEvaluatorConstant.LEFT_VALUE_CORRECT_ISFILLED_INCORRECT + filledValue;
                                                        } else {
                                                            subQfeedback = subQfeedback + NumberLineEvaluatorConstant.LEFT_VALUE_CORRECT_ISFILLED_INCORRECT + filledValue;
                                                        }
                                                    }
                                                } else {
                                                    isCorrectLeftEnd = false;
                                                    if (isFoundRightEnd && isCorrectRightEnd) {
                                                        subQfeedback = NumberLineEvaluatorConstant.LEFT_VALUE_INCORRECT + teacherMarkPoints.get(j).getText().getText() + ".";
                                                    } else {
                                                        subQfeedback = subQfeedback + NumberLineEvaluatorConstant.LEFT_VALUE_INCORRECT + teacherMarkPoints.get(j).getText().getText() + ".";
                                                    }
                                                }
                                            }
                                            else {
                                                isCorrectLeftEnd = false;
                                                subQfeedback = "Though You have placed the dot as left end point no number or verticle line aligned with it.";
                                            }
                                        } catch (NumberFormatException e) {
                                            subQfeedback = NumberLineEvaluatorConstant.NO_STRING_VALUES;
                                        }
                                    }
                                    else {
                                        isCorrectLeftEnd = true;
                                    }
                                    isFoundLeftEnd = true;
                                } else {
                                    if (teacherMarkPoints.get(j).isInfinity()) {
                                        if(isFoundRightEnd && isCorrectRightEnd) {
                                            subQfeedback = NumberLineEvaluatorConstant.LEFT_VALUE_IS_NEGETIVE_INFINITY+" and you have marked it as ("+studentMarkPoints.get(k).getText().getText()+").";
                                        }else {
                                            subQfeedback = subQfeedback+NumberLineEvaluatorConstant.LEFT_VALUE_IS_NEGETIVE_INFINITY +"and you have marked it as ("+studentMarkPoints.get(k).getText().getText()+").";
                                        }
                                    } else {
                                        if(isFoundRightEnd && isCorrectRightEnd) {
                                            subQfeedback = NumberLineEvaluatorConstant.LEFT_VALUE_OF_THE_MARKED_LINE + teacherMarkPoints.get(j).getText().getText() + " and you have marked it as ("+studentMarkPoints.get(k).getText().getText()+").";
                                        }else {
                                            subQfeedback = subQfeedback +NumberLineEvaluatorConstant.LEFT_VALUE_OF_THE_MARKED_LINE + teacherMarkPoints.get(j).getText().getText() + " and you have marked it as (" +studentMarkPoints.get(k).getText().getText()+").";
                                        }
                                    }
                                }
                                break;
                            }
                        }
                    }
                }
                if(!isFoundRightEnd){
                    if (studentMarkPoints.get(k).endOFTheThickLine.equals(NumberLineEvaluatorConstant.RIGHT)) {
                        for (int j = 0; j < 2; j++) {
                            if (teacherMarkPoints.get(j).endOFTheThickLine.equals(NumberLineEvaluatorConstant.RIGHT)) {
                                if ((studentMarkPoints.get(k).isInfinity() == teacherMarkPoints.get(j).isInfinity())) {
                                    if(!studentMarkPoints.get(k).isInfinity()){
                                        try {
                                            if(studentMarkPoints.get(k).getText()!=null){
                                                double val = Double.parseDouble(studentMarkPoints.get(k).getText().getText());
                                                if(studentMarkPoints.get(k).getText().getText().equals(teacherMarkPoints.get(j).getText().getText())){
                                                    if(studentMarkPoints.get(k).isFilled==teacherMarkPoints.get(j).isFilled){
                                                        isCorrectRightEnd = true;
                                                    }else {
                                                        isCorrectRightEnd = false;
                                                        String filledValue = ((teacherMarkPoints.get(j).isFilled == true) ? NumberLineEvaluatorConstant.FILLED : NumberLineEvaluatorConstant.NON_FILLED);
                                                        if (isFoundLeftEnd && isCorrectLeftEnd) {
                                                            subQfeedback = NumberLineEvaluatorConstant.RIGHT_VALUE_CORRECT_ISFILLED_INCORRECT+filledValue;
                                                        } else {
                                                            subQfeedback = subQfeedback+ NumberLineEvaluatorConstant.RIGHT_VALUE_CORRECT_ISFILLED_INCORRECT + filledValue;
                                                        }
                                                    }
                                                }else {
                                                    isCorrectRightEnd = false;
                                                    if(isFoundLeftEnd && isCorrectLeftEnd){
                                                        subQfeedback = NumberLineEvaluatorConstant.RIGHT_VALUE_INCORRECT +teacherMarkPoints.get(j).getText().getText()+" and you have marked it as ("+studentMarkPoints.get(k).getText().getText()+").";
                                                    }else {
                                                        subQfeedback = subQfeedback+NumberLineEvaluatorConstant.RIGHT_VALUE_INCORRECT+teacherMarkPoints.get(j).getText().getText()+" and you have marked it as ("+studentMarkPoints.get(k).getText().getText()+").";
                                                    }
                                                }
                                            } else {
                                               isCorrectRightEnd = false;
                                               subQfeedback = "Though You have placed the dot as right end point no number or verticle line aligned with it.";
                                            }
                                        }catch (NumberFormatException e){
                                            subQfeedback = NumberLineEvaluatorConstant.NO_STRING_VALUES;
                                        }
                                    } else {
                                        isCorrectRightEnd = true;
                                    }
                                    isFoundRightEnd = true;
                                } else {
                                    if (teacherMarkPoints.get(j).isInfinity()) {
                                        if(isFoundLeftEnd && isCorrectLeftEnd){
                                            subQfeedback = NumberLineEvaluatorConstant.RIGHT_VALUE_IS_POSITIVE_INFINITY + " and you have marked it as ("+studentMarkPoints.get(k).getText().getText()+").";
                                        }else {
                                            subQfeedback = subQfeedback+NumberLineEvaluatorConstant.RIGHT_VALUE_IS_POSITIVE_INFINITY + " and you have marked it as ("+studentMarkPoints.get(k).getText().getText()+").";
                                        }

                                    } else {
                                        if(isFoundLeftEnd && isCorrectLeftEnd) {
                                            subQfeedback = NumberLineEvaluatorConstant.RIGHT_VALUE_OF_THE_MARKED_LINE + teacherMarkPoints.get(j).getText().getText() + " and you have marked it as ("+studentMarkPoints.get(k).getText().getText()+").";
                                        }else {
                                            subQfeedback = subQfeedback+ NumberLineEvaluatorConstant.RIGHT_VALUE_OF_THE_MARKED_LINE + teacherMarkPoints.get(j).getText().getText() + "and you have marked it as ("+studentMarkPoints.get(k).getText().getText()+").";
                                        }
                                    }
                                }
                                break;
                            }
                        }
                    }
                }
            }
            for(int x = 0; x< condition.getMarkingMethods().size(); x++) {
                if (condition.getMarkingMethods().get(x).getMethod().equals("ALL")) {
                    if (isCorrectLeftEnd && isCorrectRightEnd) {
                        totalSubQMark += condition.getMarkingMethods().get(x).getGainedMarks();
                        subQfeedback = NumberLineEvaluatorConstant.CORRECT_ANS;
                        return new Mark(condition.getName(), condition.getMarkingMethods().get(x).getGainedMarks());
                    } else {
                        return null;
                    }
                }
            }
        } else {
            subQfeedback = NumberLineEvaluatorConstant.INCORRECT_ANS ;
            return null;
        }
        return null;
    }

}
