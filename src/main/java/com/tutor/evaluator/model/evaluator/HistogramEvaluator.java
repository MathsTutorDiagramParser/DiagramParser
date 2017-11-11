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
    int totalMarks=0;
    int outOfMarks=0;

    public MarkSheet evaluate(AbstractDiagramStructure studentStructure,
                              AbstractDiagramStructure teacherStructure, RubricRules rubricRules, String structureFeedBack){
        MarkSheet markSheet = new MarkSheet();
        this.studentStructure = studentStructure;
        this.teacherStructure = teacherStructure;

        ArrayList<SubMarkSheet> subMarkSheets = new ArrayList<>();


        for (SubQuestion subQuestion : rubricRules.getSubQuestions()){

            List<Condition> conditions = subQuestion.getConditions();
            totalSubQ = 0;
            subQfeedback = "";
            totalMarks=0;
            outOfMarks=0;


            SubMarkSheet subQmarkSheet = new SubMarkSheet();

            for (int i = 0; i < conditions.size(); i++) {
                marks = new Mark[conditions.size()];
                Condition condition = conditions.get(i);
                if (condition.getName().equals(MarkingCondition.BAR_VALUES)) {
                    outOfMarks+=condition.getTotalMarks();
                    marks[i] = barCheck(condition);
                } else if (condition.getName().equals(MarkingCondition.MARKING_AXIS)) {
                    outOfMarks+=condition.getTotalMarks();
                    marks[i] = axisCheck(condition);

                }


            }
            subQmarkSheet = new SubMarkSheet(totalSubQ, marks, subQfeedback);
            subQmarkSheet.setTotalMark(totalMarks);
            subMarkSheets.add(subQmarkSheet);

        }
        markSheet.setTotalMark(totalMarks);
        markSheet.setTotalMark_gainMark(outOfMarks);
        markSheet.setFeedback(structureFeedBack+  subQfeedback);
        markSheet.setSubMarkSheets(subMarkSheets);
        return markSheet;
    }


    public Mark barCheck(Condition condition){


        List<Bar> studentBars = ((AbstractHistogramStructure)studentStructure).getBar();
        List<Bar> teacherBars = ((AbstractHistogramStructure)teacherStructure).getBar();

        int k= studentBars.size();
        int l= teacherBars.size();

        if(k!=l){
            subQfeedback+= HistogramEvaluatorConstant.BAR_INEQUAL;
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
                subQfeedback+=HistogramEvaluatorConstant.ALL_MARKED_CORRECTLY;
                for(int x = 0; x< condition.getMarkingMethods().size(); x++) {
                    if (condition.getMarkingMethods().get(x).getMethod().equals("ALL")) {
                        totalMarks+=condition.getMarkingMethods().get(x).getGainedMarks();
                        return new Mark(condition.getName(), condition.getMarkingMethods().get(x).getGainedMarks());
                    }
                }

            }else{
                String indexName;
                if(index==0){
                    indexName="first ";
                }else if(index==1){
                    indexName="second ";

                }else if(index==2){
                    indexName="third ";
                }else{
                    indexName="a ";
                }
                subQfeedback+=HistogramEvaluatorConstant.NOT_ALL_MARKED_CORRECTLY+ indexName +HistogramEvaluatorConstant.BAR;
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
            subQfeedback+=HistogramEvaluatorConstant.ALL_LEGENDS;
            for(int x = 0; x< condition.getMarkingMethods().size(); x++) {
                if (condition.getMarkingMethods().get(x).getMethod().equals("ALL")) {
                    totalMarks+=condition.getMarkingMethods().get(x).getGainedMarks();
                    return new Mark(condition.getName(), condition.getMarkingMethods().get(x).getGainedMarks());
                }
            }


        }else{
            if(!(xSlegend.equals(xTlegend))&&!(ySLegend.equals(yTLegend))){
                subQfeedback+=HistogramEvaluatorConstant.NOT_ALL_LEGENDS;
            }else if(!ySLegend.equals(yTLegend)){
                subQfeedback+=HistogramEvaluatorConstant.NOT_Y_LEGEND;
            }else{
                subQfeedback+=HistogramEvaluatorConstant.NOT_X_LEGEND;
            }
            return new Mark(condition.getName(), 0);
        }


        return null;
    }

}
