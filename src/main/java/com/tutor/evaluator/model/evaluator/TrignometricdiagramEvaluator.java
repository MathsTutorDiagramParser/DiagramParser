package com.tutor.evaluator.model.evaluator;

import com.tutor.evaluator.model.constants.MarkingCondition;
import com.tutor.evaluator.model.constants.TrigonometricMarkingConstants;
import com.tutor.evaluator.model.markingStructure.Mark;
import com.tutor.evaluator.model.markingStructure.MarkSheet;
import com.tutor.evaluator.model.markingStructure.SubMarkSheet;
import com.tutor.evaluator.model.rubicRulesPOJOObjects.Condition;
import com.tutor.evaluator.model.rubicRulesPOJOObjects.RubricRules;
import com.tutor.evaluator.model.rubicRulesPOJOObjects.SubQuestion;
import com.tutor.parser.model.feedback.FeedBack;
import com.tutor.parser.model.graphParser.DiagramStructure.AbstractDiagramStructure;
import com.tutor.parser.model.graphParser.DiagramStructure.Trignometry.AbstractTrignometryStructure;
import com.tutor.parser.model.graphParser.DiagramStructure.Trignometry.LineConnection;
import com.tutor.parser.model.graphParser.DiagramStructure.Trignometry.LineStructure;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Madhavi Ruwandika on 8/25/2017.
 */
public class TrignometricdiagramEvaluator extends Evaluator {
    Mark[] marks = null;
    AbstractDiagramStructure studentStructure;
    AbstractDiagramStructure teacherStructure;
    String subQfeedback ;
    int totalSubQ = 0;
    int totalMarks = 0;
    int gainedMarks = 0;


    public MarkSheet evaluate(AbstractDiagramStructure studentStructure,
                              AbstractDiagramStructure teacherStructure, RubricRules rubricRules, String structureFeedBack) {
     MarkSheet markSheet = new MarkSheet();

        this.studentStructure = studentStructure;
        this.teacherStructure = teacherStructure;

        ArrayList<SubMarkSheet> subMarkSheets = new ArrayList<>();
        for (SubQuestion subQuestion : rubricRules.getSubQuestions()){

            List<Condition> conditions = subQuestion.getConditions();
            totalSubQ = 0;
            subQfeedback = "";
            SubMarkSheet subQmarkSheet = new SubMarkSheet();

            for (int i = 0; i < conditions.size(); i++) {
                marks = new Mark[conditions.size()];
                totalMarks += conditions.get(i).getTotalMarks();
                Condition condition = conditions.get(i);
                if (condition.getName().equals(TrigonometricMarkingConstants.MARK_LENGTH)) {
                    marks[i] = checkMarkedLength(condition);
                } else if (condition.getName().equals(TrigonometricMarkingConstants.MARK_LABEL)) {
                    marks[i] = checkMarkedLabels(condition);
                } else {
                    Mark mark = new Mark();
                    mark.setValue(0);
                    mark.setFeedBack("NO MATCHING");
                    marks[i] = mark;
                }


            }
            subQmarkSheet = new SubMarkSheet(totalSubQ, marks, subQfeedback);

            subMarkSheets.add(subQmarkSheet);


        }

        markSheet.setSubMarkSheets(subMarkSheets);
        markSheet.setTotalMark(totalSubQ);
        markSheet.setTotalMark_gainMark(totalMarks);
        markSheet.setFeedback(structureFeedBack);
        return markSheet;
    }

    private Mark checkMarkedLength(Condition condition){
        List<LineStructure> studentLine = ((AbstractTrignometryStructure)studentStructure).getLineList();
        List<LineStructure> teacherLine = ((AbstractTrignometryStructure)teacherStructure).getLineList();
        int lengthCount = 0;
        int teacherLengthCount = 0;

        if(studentLine != null && studentLine.size()!=0 ) {
            for(LineStructure lineofTeacher: teacherLine){
                for(LineStructure lineOfStudent: studentLine){
                    if ((Math.abs(lineOfStudent.getX2()-lineOfStudent.getX1())) == Math.abs(lineofTeacher.getX2()-lineOfStudent.getX1())){
                        if((lineOfStudent.getLengthText() == lineofTeacher.getLengthText())&&(lineofTeacher.getLengthText() != null)){
                            lengthCount++;
                        }
                    }
                }
            }
        }
        for(LineStructure teacherline :((AbstractTrignometryStructure) teacherStructure).getLineList()){
            if (teacherline.getLengthText()!=null){
                teacherLengthCount +=1;
            }
        }

        if((teacherLengthCount == lengthCount)&& (lengthCount!= 0 )){
            int partMark = condition.getMarkingMethods().get(0).getGainedMarks();
            totalSubQ += partMark;

            Mark mark = new Mark();
            mark.setValue(partMark);

                subQfeedback = subQfeedback + "--------------------------------- \nLength labels are marked correctly in the student's answer \n";
                mark.setFeedBack("Length labels are marked correctly in the student's answer");

            return  mark;
        }
        else{
            subQfeedback = subQfeedback  + "------------------------------------ \nLength labels are not marked in the Diagram correctly \n";
            return new Mark(condition.getName(), 0);

        }

    }
    private Mark checkMarkedLabels(Condition condition){
        List<LineConnection> studentConnection = ((AbstractTrignometryStructure)studentStructure).getConnectionList();
        List<LineConnection> teacherConnection = ((AbstractTrignometryStructure)teacherStructure).getConnectionList();
        int labelCount = 0;
        int teacherLabelCount = 0;

        if(studentConnection != null && studentConnection.size()!=0 ) {
            for(LineConnection connOfTeacher: teacherConnection){
                for(LineConnection connOfStudent: studentConnection){
                    if ((connOfStudent.getConnectionPoint_X() == connOfTeacher.getConnectionPoint_X()) && connOfStudent.getConnectionPoint_Y() == connOfTeacher.getConnectionPoint_Y()){
                        if((connOfStudent.getVertexLabel() == connOfTeacher.getAngleText())&&(connOfTeacher.getVertexLabel() != null)){
                            labelCount++;
                        }
                    }
                }
            }
        }
        for(LineConnection teacherConn :((AbstractTrignometryStructure) teacherStructure).getConnectionList()){
            if (teacherConn.getAngleText()!=null){
                teacherLabelCount +=1;
            }
        }
        if(teacherLabelCount == labelCount){
            int partMark = condition.getMarkingMethods().get(0).getGainedMarks();
            totalSubQ += partMark;
            Mark mark = new Mark();
            mark.setValue(partMark);

                subQfeedback = subQfeedback + "Vertex labels are marked correctly in the student's answer";
                mark.setFeedBack("Vertex labels are marked correctly in the student's answer");

            return  mark;
        }
        else{
            return new Mark(condition.getName(), 0);
        }
    }
}
