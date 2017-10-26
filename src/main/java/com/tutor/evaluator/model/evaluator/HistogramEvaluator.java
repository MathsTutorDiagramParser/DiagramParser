package com.tutor.evaluator.model.evaluator;

import com.tutor.evaluator.model.constants.HistogramEvaluatorConstant;
import com.tutor.evaluator.model.constants.MarkingCondition;
import com.tutor.evaluator.model.constants.NumberLineEvaluatorConstant;
import com.tutor.evaluator.model.markingStructure.Mark;
import com.tutor.evaluator.model.markingStructure.MarkSheet;
import com.tutor.evaluator.model.markingStructure.SubMarkSheet;
import com.tutor.evaluator.model.rubicRulesPOJOObjects.Condition;
import com.tutor.evaluator.model.rubicRulesPOJOObjects.RubricRules;
import com.tutor.evaluator.model.rubicRulesPOJOObjects.SubQuestion;
import com.tutor.parser.model.feedback.FeedBack;
import com.tutor.parser.model.graphParser.DiagramStructure.AbstractDiagramStructure;
import com.tutor.parser.model.graphParser.DiagramStructure.Histogram.AbstractHistogramStructure;
import com.tutor.parser.model.graphParser.DiagramStructure.Histogram.Bar;
import com.tutor.parser.model.graphParser.DiagramStructure.NumberLine.AbstractNumberLineStructure;
import com.tutor.parser.model.graphParser.DiagramStructure.NumberLine.MarkPoint;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Madhavi Ruwandika on 8/25/2017.
 */
public class HistogramEvaluator extends Evaluator {

    Mark[] marks = null;
    AbstractDiagramStructure studentStructure;
    AbstractDiagramStructure teacherStructure;
    String subQfeedback ;
    int totalSubQ = 0;
    int totalQMark = 0;
    int totalQGainMark = 0;
    int totalSubQGainMark = 0;

    public MarkSheet evaluate(AbstractDiagramStructure studentStructure,
                              AbstractDiagramStructure teacherStructure, RubricRules rubricRules, String feedBacks) {
        MarkSheet markSheet = new MarkSheet();

        this.studentStructure = studentStructure;
        this.teacherStructure = teacherStructure;

        ArrayList<SubMarkSheet> subMarkSheets = new ArrayList<>();
        int itr = 0;
        for (SubQuestion subQuestion : rubricRules.getSubQuestions()){

            List<Condition> conditions = subQuestion.getConditions();
            totalSubQGainMark = 0;
            subQfeedback = "";
            SubMarkSheet subQmarkSheet = new SubMarkSheet();

            for (int i = 0; i < conditions.size(); i++) {
                totalSubQGainMark += conditions.get(i).getTotalMarks();
                marks = new Mark[conditions.size()];
                Condition condition = conditions.get(i);
                if (condition.getName().equals(MarkingCondition.BAR_VALUES)) {
                    marks[i] = barCheck(condition);
                } else if (condition.getName().equals(MarkingCondition.MARKING_AXIS)) {
                    marks[i] = axisCheck(condition);

                }
            }
            subQmarkSheet = new SubMarkSheet(totalSubQ, marks, subQfeedback,totalSubQGainMark,itr);
            itr++;
            subMarkSheets.add(subQmarkSheet);
        }

        markSheet.setSubMarkSheets(subMarkSheets);
        return markSheet;
    }


    public Mark barCheck(Condition condition){


        List<Bar> studentBars = ((AbstractHistogramStructure)studentStructure).getBar();
        List<Bar> teacherBars = ((AbstractHistogramStructure)teacherStructure).getBar();

        int k= studentBars.size();
        int l= studentBars.size();

        if(k!=l){
            subQfeedback= HistogramEvaluatorConstant.BAR_INEQUAL;
            return new Mark(condition.getName(), 0);
        }else{
            Boolean check= true;
            int index=200;
            for(int a=0;a<k;a++){
               if(studentBars.get(a).getY()<teacherBars.get(a).getY()+5 && studentBars.get(a).getY()>teacherBars.get(a).getY()-5 ){

               } else{
                   index=a;
                   check=false;
               }
            }
            if(check){
                subQfeedback=HistogramEvaluatorConstant.ALL_MARKED_CORRECTLY;
                for(int x = 0; x< condition.getMarkingMethods().size(); x++) {
                    if (condition.getMarkingMethods().get(x).getMethod().equals("ALL")) {
                        return new Mark(condition.getName(), condition.getMarkingMethods().get(x).getGainedMarks());
                    }
                }

            }else{
                subQfeedback=HistogramEvaluatorConstant.NOT_ALL_MARKED_CORRECTLY+ index +HistogramEvaluatorConstant.BAR;
                return new Mark(condition.getName(), 0);

            }



        }

        return null;
    }
    public Mark axisCheck(Condition condition){


        String xSlegend = ((AbstractHistogramStructure)studentStructure).xLegend;
        String xTlegend = ((AbstractHistogramStructure)teacherStructure).xLegend;
        String ySLegend= ((AbstractHistogramStructure)studentStructure).yLegend;
        String yTLegend= ((AbstractHistogramStructure)teacherStructure).yLegend;

        if(xSlegend.equals(xTlegend)&& ySLegend.equals(yTLegend)){
            subQfeedback=HistogramEvaluatorConstant.ALL_LEGENDS;
            for(int x = 0; x< condition.getMarkingMethods().size(); x++) {
                if (condition.getMarkingMethods().get(x).getMethod().equals("ALL")) {
                    return new Mark(condition.getName(), condition.getMarkingMethods().get(x).getGainedMarks());
                }
            }


        }else{
            if(!(xSlegend.equals(xTlegend))&&!(ySLegend.equals(yTLegend))){
                subQfeedback=HistogramEvaluatorConstant.NOT_ALL_LEGENDS;
            }else if(!ySLegend.equals(yTLegend)){
                subQfeedback=HistogramEvaluatorConstant.NOT_Y_LEGEND;
            }else{
                subQfeedback=HistogramEvaluatorConstant.NOT_X_LEGEND;
            }
            return new Mark(condition.getName(), 0);
        }


        return null;
    }

}
