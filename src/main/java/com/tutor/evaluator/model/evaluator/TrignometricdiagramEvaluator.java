package com.tutor.evaluator.model.evaluator;

import com.tutor.evaluator.model.constants.StepConstant;
import com.tutor.evaluator.model.markingStructure.Mark;
import com.tutor.evaluator.model.markingStructure.MarkSheet;
import com.tutor.evaluator.model.rubicRulesPOJOObjects.Condition;
import com.tutor.evaluator.model.rubicRulesPOJOObjects.RubricRules;
import com.tutor.evaluator.model.rubicRulesPOJOObjects.SubQuestion;
import com.tutor.parser.model.feedback.FeedBack;
import com.tutor.parser.model.graphParser.DiagramStructure.AbstractDiagramStructure;
import com.tutor.parser.model.graphParser.DiagramStructure.Trignometry.AbstractTrignometryStructure;
import com.tutor.parser.model.graphParser.DiagramStructure.Trignometry.LineConnection;
import com.tutor.parser.model.graphParser.DiagramStructure.Trignometry.LineStructure;
import com.tutor.parser.model.graphicalPOJOObject.GraphicalImageComponent;
import com.tutor.parser.model.graphicalPOJOObject.Text.Text;
import com.tutor.parser.model.graphicalPOJOObject.line.Line;

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

    @Override
    public MarkSheet[] evaluate(AbstractDiagramStructure studentStructure, AbstractDiagramStructure teacherStructure, RubricRules rubricRules, List<FeedBack> feedBacks) {
        MarkSheet[] markSheets = new MarkSheet[rubricRules.getSubQuestions().size()];

        this.studentStructure = studentStructure;
        this.teacherStructure = teacherStructure;


        for (SubQuestion subQuestion : rubricRules.getSubQuestions()){

            List<Condition> conditions = subQuestion.getConditions();
            totalSubQ = 0;
            subQfeedback = "";
            MarkSheet subQmarkSheet = new MarkSheet();

            for (int i = 0; i < conditions.size(); i++) {
                marks = new Mark[conditions.size()];
                Condition condition = conditions.get(i);

                if (condition.getName().equals(StepConstant.TRIGNOMETRY_MARK_LENGTH)) {
                    marks[i] = checkMarkedLength(condition);
                } else if (condition.getName().equals(StepConstant.TRIGNOMETRY_MARK_ANGLE)) {
                    marks[i] = checkMarkedAngles(condition);
                }
                subQmarkSheet = new MarkSheet(totalSubQ, marks, subQfeedback);
            }
            markSheets[subQuestion.getId()] =  subQmarkSheet;
        }
        return markSheets;
    }

    private Mark checkMarkedLength(Condition condition){
        List<LineStructure> studentLine = ((AbstractTrignometryStructure)studentStructure).getLineList();
        List<LineStructure> teacherLine = ((AbstractTrignometryStructure)teacherStructure).getLineList();
        int lengthCount = 0;
        int teacherLengthCount = 0;

        if(studentLine != null && studentLine.size()!=0 ) {
            for(LineStructure lineofTeacher: teacherLine){
                for(LineStructure lineOfStudent: studentLine){
                    if (lineOfStudent == lineofTeacher){
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

        if(teacherLengthCount == lengthCount){
            int partMark = condition.getMarkingMethods().get(0).getGainedMarks();
            totalSubQ += partMark;
            return new Mark(condition.getName(), partMark);
        }
        else{
            return new Mark(condition.getName(), 0);
        }

    }
    private Mark checkMarkedAngles(Condition condition){
        List<LineConnection> studentConnection = ((AbstractTrignometryStructure)studentStructure).getConnectionList();
        List<LineConnection> teacherConnection = ((AbstractTrignometryStructure)teacherStructure).getConnectionList();
        int angleCount = 0;
        int teacherAngleCount = 0;

        if(studentConnection != null && studentConnection.size()!=0 ) {
            for(LineConnection connOfTeacher: teacherConnection){
                for(LineConnection connOfStudent: studentConnection){
                    if (connOfStudent == connOfTeacher){
                        if((connOfStudent.getAngleText() == connOfTeacher.getAngleText())&&(connOfTeacher.getAngleText() != null)){
                            angleCount++;
                        }
                    }
                }
            }
        }
        for(LineConnection teacherConn :((AbstractTrignometryStructure) teacherStructure).getConnectionList()){
            if (teacherConn.getAngleText()!=null){
                teacherAngleCount +=1;
            }
        }
        if(teacherAngleCount == angleCount){
            int partMark = condition.getMarkingMethods().get(0).getGainedMarks();
            totalSubQ += partMark;
            return new Mark(condition.getName(), partMark);
        }
        else{
            return new Mark(condition.getName(), 0);
        }
    }
}
