package com.tutor.evaluator.service;

import com.tutor.evaluator.model.evaluator.Evaluator;
import com.tutor.evaluator.model.evaluator.EvaluatorFactory;
import com.tutor.evaluator.model.markingStructure.MarkSheet;
import com.tutor.evaluator.model.rubicRulesPOJOObjects.RubricRules;
import com.tutor.evaluator.model.rubicRulesPOJOObjects.RubricRulesFactory;
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
    Evaluator evaluator;

    public EvaluatorServiceImpl(DiagramType diagramType) throws JAXBException, FileNotFoundException {
        this.diagramType=diagramType;
        this.evaluator = EvaluatorFactory.getEvaluator(diagramType);
    }

    public MarkSheet[] evaluate(AbstractDiagramStructure abstractDiagramStructureStudent, AbstractDiagramStructure abstractDiagramStructureTeacher, List<FeedBack> feedBacks)throws JAXBException, FileNotFoundException{
        this.abstractDiagramStructureStudent=abstractDiagramStructureStudent;
        this.abstractDiagramStructureTeacher=abstractDiagramStructureTeacher;
        //this.markingStructure=new MarkSheet();
        this.rubricRules= RubricRulesFactory.loadBuiltRubricRules(diagramType);

        MarkSheet[] markSheets = evaluator.evaluate(abstractDiagramStructureStudent,abstractDiagramStructureTeacher,rubricRules,feedBacks);

        return markSheets;
    }

}
