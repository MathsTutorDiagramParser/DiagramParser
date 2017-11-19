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
    String subQfeedback ="  " ;
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
        int l= teacherBars.size();
        boolean indexCheckM;
        boolean indexCheckL;
        String indexName="";
        String barBars="";
        int barWCount=0;
        int barCCount=0;
        Double scaleSY =(((AbstractHistogramStructure)studentStructure).getScaleYValue())*0.2;
        Double scaleSX =(((AbstractHistogramStructure)studentStructure).getScaleXValue())*0.4;


        if(k!=l){
            if(k>l){
                subQfeedback+=HistogramEvaluatorConstant.BAR_INEQUAL_MORE;
                barCCount=0;
                for(int i=0;i<k;i++) {
                    for (int j = 0; j < l; j++) {
                        if((studentBars.get(i).getXlow()<teacherBars.get(j).getXlow()+scaleSX && studentBars.get(i).getXlow()>teacherBars.get(j).getXlow()-scaleSX)&&(studentBars.get(i).getXhigh()<teacherBars.get(j).getXhigh()+scaleSX && studentBars.get(i).getXhigh()>teacherBars.get(j).getXhigh()-scaleSX)) {
                            if (studentBars.get(i).getY() < teacherBars.get(j).getY() + scaleSY && studentBars.get(i).getY() > teacherBars.get(j).getY() - scaleSY) {
                                barCCount++;
                            }
                        }
                    }
                }
                if(barCCount==k){
                    subQfeedback+="You have correctly marked the required bars but cannot give marks";
                }else{
                    if((k-barCCount)==1){
                        barBars=" bar";
                    }else{
                        barBars=" bars";
                    }
                    subQfeedback+="You have incorrectly marked "+(k-barCCount)+barBars;
                }

            }else{
                subQfeedback+=HistogramEvaluatorConstant.BAR_INEQUAL_LESS;

                for(int i=0;i<k;i++) {
                    for (int j = 0; j < l; j++) {
                        if((studentBars.get(i).getXlow()<teacherBars.get(j).getXlow()+scaleSX && studentBars.get(i).getXlow()>teacherBars.get(j).getXlow()-scaleSX)&&(studentBars.get(i).getXhigh()<teacherBars.get(j).getXhigh()+scaleSX && studentBars.get(i).getXhigh()>teacherBars.get(j).getXhigh()-scaleSX)) {
                            if (studentBars.get(i).getY() < teacherBars.get(j).getY() + scaleSY && studentBars.get(i).getY() > teacherBars.get(j).getY() - scaleSY) {
                                barCCount++;
                            }
                        }
                    }
                }
                if((k-barCCount)==1){
                    barBars=" bar";
                }else{
                    barBars=" bars";
                }
                subQfeedback+="You have incorrectly marked "+(k-barCCount)+barBars;




            }
            return new Mark(condition.getName(), 0);

        }else if (k==l){
            subQfeedback+=HistogramEvaluatorConstant.CORRECT_NUMBER_OF_BARS;
            barCCount=0;
            for(int i=0;i<k;i++) {
                for (int j = 0; j < l; j++) {
                    if((studentBars.get(i).getXlow()<teacherBars.get(j).getXlow()+scaleSX && studentBars.get(i).getXlow()>teacherBars.get(j).getXlow()-scaleSX)&&(studentBars.get(i).getXhigh()<teacherBars.get(j).getXhigh()+scaleSX && studentBars.get(i).getXhigh()>teacherBars.get(j).getXhigh()-scaleSX)) {
                        if (studentBars.get(i).getY() < teacherBars.get(j).getY() + scaleSY && studentBars.get(i).getY() > teacherBars.get(j).getY() - scaleSY) {
                            barCCount++;
                        }
                    }
                }
            }

            if(barCCount==k){
                subQfeedback+=HistogramEvaluatorConstant.ALL_MARKED_CORRECTLY;
                for(int x = 0; x< condition.getMarkingMethods().size(); x++) {
                    if (condition.getMarkingMethods().get(x).getMethod().equals("ALL")) {
                        totalSubQGainMark+=condition.getMarkingMethods().get(x).getGainedMarks();
                        return new Mark(condition.getName(), condition.getMarkingMethods().get(x).getGainedMarks());
                    }
                }

            }else{
                if((k-barCCount)==1){
                    barBars=" bar";
                }else{
                    barBars=" bars";
                }
                subQfeedback+="You have incorrectly marked "+(k-barCCount)+barBars;
                return new Mark(condition.getName(), 0);

            }



        }

        return null;
    }

    public static boolean isNumeric(String str)
    {
        try
        {
            double d = Double.parseDouble(str);
        }
        catch(NumberFormatException nfe)
        {
            return false;
        }
        return true;
    }
    public Mark axisCheck(Condition condition){


        String xSlegend = ((AbstractHistogramStructure)studentStructure).getxLegend();
        String xTlegend = ((AbstractHistogramStructure)teacherStructure).getxLegend();
        String ySLegend= ((AbstractHistogramStructure)studentStructure).getyLegend();
        String yTLegend= ((AbstractHistogramStructure)teacherStructure).getyLegend();
        if(isNumeric(xSlegend) && isNumeric(ySLegend) ){
            subQfeedback+=" No X and Y axises labels";


        }else if(isNumeric(xSlegend)){
            subQfeedback+="  No X Axis label ";
            if(!ySLegend.equals(yTLegend)){
                subQfeedback+=" You have not labeled Y axis correctly";
            }

        }else if (isNumeric(ySLegend)){
            subQfeedback+="  No Y Axis label";
            if(!xSlegend.equals(xTlegend)){
                subQfeedback+=" You have not labeled X axis correctly";
            }
        }else{
            if(xSlegend.equals(xTlegend)&& ySLegend.equals(yTLegend)){
                subQfeedback+=HistogramEvaluatorConstant.ALL_LEGENDS;
                for(int x = 0; x< condition.getMarkingMethods().size(); x++) {
                    if (condition.getMarkingMethods().get(x).getMethod().equals("ALL")) {
                        totalSubQGainMark+=condition.getMarkingMethods().get(x).getGainedMarks();
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


        }



        return null;
    }

}
