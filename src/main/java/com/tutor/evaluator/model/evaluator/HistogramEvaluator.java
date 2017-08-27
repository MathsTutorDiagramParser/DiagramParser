package com.tutor.evaluator.model.evaluator;

import com.tutor.evaluator.model.constants.HistogramCondition;
import com.tutor.evaluator.model.constants.HistogramEvaluatorConstant;
import com.tutor.evaluator.model.constants.MarkingCondition;
import com.tutor.evaluator.model.markingStructure.Mark;
import com.tutor.evaluator.model.markingStructure.MarkSheet;
import com.tutor.evaluator.model.markingStructure.SubMarkSheet;
import com.tutor.evaluator.model.rubicRulesPOJOObjects.Condition;
import com.tutor.evaluator.model.rubicRulesPOJOObjects.RubricRules;
import com.tutor.evaluator.model.rubicRulesPOJOObjects.SubQuestion;
import com.tutor.parser.model.feedback.FeedBack;
import com.tutor.parser.model.graphParser.DiagramStructure.AbstractDiagramStructure;
import com.tutor.parser.model.graphParser.DiagramStructure.Histogram.AbstractHistogramStructure;
import com.tutor.parser.model.graphParser.DiagramStructure.NumberLine.AbstractNumberLineStructure;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Madhavi Ruwandika on 8/25/2017.
 */
public class HistogramEvaluator extends Evaluator {
    Mark[] marks = null;
    AbstractDiagramStructure studentStructure;
    AbstractDiagramStructure teacherStructure;
    String subQfeedback;
    int totalSubQ = 0;

    public MarkSheet evaluate(AbstractDiagramStructure studentStructure,
                              AbstractDiagramStructure teacherStructure, RubricRules rubricRules, List<FeedBack> feedBacks) {
        MarkSheet markSheet = new MarkSheet();

        this.studentStructure = studentStructure;
        this.teacherStructure = teacherStructure;
        ArrayList<SubMarkSheet> subMarkSheets = new ArrayList<>();

        for (SubQuestion subQuestion : rubricRules.getSubQuestions()) {

            List<Condition> conditions = subQuestion.getConditions();
            totalSubQ = 0;
            subQfeedback = "";

            SubMarkSheet subQmarkSheet = new SubMarkSheet();

            for (int i = 0; i < conditions.size(); i++) {
                marks = new Mark[conditions.size()];
                Condition condition = conditions.get(i);
                if (condition.getName().equals(HistogramCondition.HISTOGRAM_MARKING_THE_AXIS)) {
                    marks[i] = getMarksForAxis(condition);
                } else if (condition.getName().equals(HistogramCondition.HISTOGRAM_BAR_VALUES)) {
                    marks[i] = getMarksForBars(condition);
                }
                subQmarkSheet = new SubMarkSheet(totalSubQ, marks, subQfeedback);
            }
            subMarkSheets.add(subQmarkSheet);
            markSheet.setTotalMark(subMarkSheets.get(0).getTotalMark());
            markSheet.setSubMarkSheets(subMarkSheets);
        }

        return markSheet;
    }

    public Mark getMarksForAxis(Condition condition) {
        Mark mark = new Mark();
        String xS = ((AbstractHistogramStructure) studentStructure).getxLegend();
        String xT = ((AbstractHistogramStructure) teacherStructure).getxLegend();
        String yS = ((AbstractHistogramStructure) studentStructure).getyLegend();
        String yT = ((AbstractHistogramStructure) teacherStructure).getyLegend();
        String feedBackAxis = "";
        int check = 0;
                if (xS.equals(xT) && yS.equals(yT)) {
                    feedBackAxis = HistogramEvaluatorConstant.CORRECT_HISTOGRAM_BARS;
                    totalSubQ +=condition.getMarkingMethods().get(0).getGainedMarks();
                    subQfeedback = subQfeedback+feedBackAxis+" and";
                    mark.setFeedBack(feedBackAxis);
                    mark.setValue(condition.getMarkingMethods().get(0).getGainedMarks());
                    mark.setCondition(condition.getName());
                    return mark;
                }else if(xS.equals(xT) &&  !yS.equals(yT)){
                    feedBackAxis = HistogramEvaluatorConstant.INCORRECT_HISTOGRAM_YAXIS;
                    totalSubQ +=1;
                    subQfeedback = subQfeedback+feedBackAxis+" and";
                    mark.setValue(1);
                    mark.setFeedBack(feedBackAxis);
                    mark.setCondition(condition.getName());
                    return mark;
                }else if(!xS.equals(xT) &&  yS.equals(yT)){
                    feedBackAxis = HistogramEvaluatorConstant.INCORRECT_HISTOGRAM_XAXIS;
                    totalSubQ +=1;
                    subQfeedback = subQfeedback+feedBackAxis+" and";
                    mark.setValue(1);
                    mark.setFeedBack(feedBackAxis);
                    mark.setCondition(condition.getName());
                    return mark;
                }else{
                    totalSubQ +=0;
                    feedBackAxis = HistogramEvaluatorConstant.INCORRECT_HISTOGRAM_BARS;
                    subQfeedback = subQfeedback+feedBackAxis+" and";
                    mark.setValue(0);
                    mark.setFeedBack(feedBackAxis);
                    mark.setCondition(condition.getName());
                    return mark;
                }

    }

    public Mark getMarksForBars(Condition condition) {
        Mark mark = new Mark();
        String feedbackBars = "";
        int barCountS = ((AbstractHistogramStructure) studentStructure).getBar().size();
        int barCountT = ((AbstractHistogramStructure) teacherStructure).getBar().size();
        if (barCountS != barCountT) {
            if (barCountS < barCountT) {
                feedbackBars = HistogramEvaluatorConstant.INCOMPLETE_BARS + Integer.toString(barCountT - barCountS) + " Bars Less";
            } else {
                feedbackBars = HistogramEvaluatorConstant.INCOMPLETE_BARS + Integer.toString(barCountT - barCountS) + " Bars Additionally ";
            }

            mark.setValue(0);
            mark.setFeedBack(feedbackBars);
            totalSubQ +=0;
            subQfeedback = subQfeedback+feedbackBars;
            mark.setCondition(condition.getName());
            return  mark;

        } else {
            int count = 0;
           Double ratioS= ((AbstractHistogramStructure) studentStructure).getyAxisratio();
           Double ratioT= ((AbstractHistogramStructure) teacherStructure).getyAxisratio();
            for (int i = 0; i < barCountS; i++) {
                Double yS1=((AbstractHistogramStructure) studentStructure).getBar().get(i).getRectangle().getY()*ratioS+1.0;
                Double yS2=((AbstractHistogramStructure) studentStructure).getBar().get(i).getRectangle().getY()*ratioS-1.0;
                Double yT=((AbstractHistogramStructure) teacherStructure).getBar().get(i).getRectangle().getY()*ratioT;
                if(yS1>=yT && yT>=yS2){
                    count++;
                }

            }
            if (count==barCountS){
                feedbackBars=HistogramEvaluatorConstant.ALL_BARS;
                mark.setFeedBack(feedbackBars);
                mark.setValue(condition.getMarkingMethods().get(0).getGainedMarks());
                totalSubQ +=condition.getMarkingMethods().get(0).getGainedMarks();
                subQfeedback = subQfeedback+feedbackBars;
                mark.setCondition(condition.getName());
                return mark;

            }
        }

        return mark;
    }
}






