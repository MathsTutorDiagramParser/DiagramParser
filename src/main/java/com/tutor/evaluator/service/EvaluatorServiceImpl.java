package com.tutor.evaluator.service;

import com.tutor.evaluator.model.RubicRulesPOJOObjects.RubricRules;
import com.tutor.evaluator.model.evaluator.Evaluator;
import com.tutor.evaluator.model.evaluator.EvaluatorFactory;
import com.tutor.evaluator.model.markingStructure.MarkSheet;
import com.tutor.evaluator.model.RubicRulesPOJOObjects.RubricRulesFactory;
import com.tutor.evaluator.model.rubricRulesReaderObject.XMLRubricRules;
import com.tutor.parser.model.feedback.FeedBack;
import com.tutor.parser.model.graphParser.DiagramStructure.AbstractDiagramStructure;
import com.tutor.parser.model.util.DiagramType;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.util.List;

public class EvaluatorServiceImpl implements EvaluatorService {

    RubricRules rubricRules;
    AbstractDiagramStructure abstractDiagramStructureStudent;
    AbstractDiagramStructure abstractDiagramStructureTeacher;
    DiagramType diagramType;
    MarkSheet markSheet;
    Evaluator evaluator;
    List<FeedBack> feedBacks;

    public EvaluatorServiceImpl(DiagramType diagramType) throws JAXBException, FileNotFoundException {
        this.diagramType=diagramType;
        this.evaluator = EvaluatorFactory.getEvaluator(diagramType);
        this.markSheet = new MarkSheet();
    }

    public MarkSheet parse(AbstractDiagramStructure abstractDiagramStructureStudent, AbstractDiagramStructure abstractDiagramStructureTeacher)throws JAXBException, FileNotFoundException{
        this.abstractDiagramStructureStudent = abstractDiagramStructureStudent;
        this.abstractDiagramStructureTeacher = abstractDiagramStructureTeacher;
        //this.markingStructure=new MarkSheet();
        this.rubricRules = RubricRulesFactory.loadBuiltRubricRules(diagramType);

        this.markSheet = evaluator.evaluate(markSheet,abstractDiagramStructureStudent, abstractDiagramStructureTeacher, rubricRules);

        if(diagramType == DiagramType.TREEDIAGRAM) {
            for (int i = 0; i < markSheet.getSubMarkSheets().size(); i++) {
                System.out.println("Sub question : " + i);
                System.out.println("Total Mark : " + markSheet.getSubMarkSheets().get(i).getTotalMark());

                for (int k = 0; k < markSheet.getSubMarkSheets().get(i).getPartitialMark().length; k++) {
                    System.out.println("Condition : " + k);
                    System.out.println("Mark is : " + markSheet.getSubMarkSheets().get(i).getPartitialMark()[k].getValue());
                    System.out.println("feedback is : " + markSheet.getSubMarkSheets().get(i).getPartitialMark()[k].getFeedBack());
                }
                System.out.println();
            }
        }

        return markSheet;
    }

}
