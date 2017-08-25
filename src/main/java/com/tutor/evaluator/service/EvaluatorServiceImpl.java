package com.tutor.evaluator.service;

import com.tutor.evaluator.model.markingStructure.MarkSheet;
import com.tutor.evaluator.model.rubricRules.RubricRulesFactory;
import com.tutor.evaluator.model.rubricRulesReaderObject.XMLRubricRules;
import com.tutor.parser.model.graphParser.DiagramStructure.AbstractDiagramStructure;
import com.tutor.parser.model.util.DiagramType;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;

public class EvaluatorServiceImpl implements EvaluatorService {

    XMLRubricRules rubricRules;
    AbstractDiagramStructure abstractDiagramStructureStudent;
    AbstractDiagramStructure abstractDiagramStructureTeacher;
    DiagramType diagramType;
    MarkSheet markSheet;

    public EvaluatorServiceImpl(DiagramType diagramType) throws JAXBException, FileNotFoundException {
        this.diagramType=diagramType;
    }

    public MarkSheet parse(AbstractDiagramStructure abstractDiagramStructureStudent, AbstractDiagramStructure abstractDiagramStructureTeacher)throws JAXBException, FileNotFoundException{
        this.abstractDiagramStructureStudent=abstractDiagramStructureStudent;
        this.abstractDiagramStructureTeacher=abstractDiagramStructureTeacher;
        //this.markingStructure=new MarkSheet();
        this.rubricRules= RubricRulesFactory.loadBuiltRubricRules(diagramType);
        System.out.println(rubricRules);
        return markSheet;
    }

}
