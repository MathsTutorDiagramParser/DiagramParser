package com.tutor.evaluator.model;

import com.tutor.evaluator.rubricParser.MarkingStructure.MarkingStructure;
import com.tutor.evaluator.rubricParser.RubricRules.RubricRulesFactory;
import com.tutor.evaluator.rubricParser.RubricRulesGenerator.rubricRulesReaderObject.XMLRubricRules;
import com.tutor.parser.model.graphParser.DiagramStructure.AbstractDiagramStructure;
import com.tutor.parser.model.util.DiagramType;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;

public class GradeParser {

    XMLRubricRules rubricRules;
    AbstractDiagramStructure abstractDiagramStructureStudent;
    AbstractDiagramStructure abstractDiagramStructureTeacher;
    DiagramType diagramType;
    MarkingStructure markingStructure;

    public GradeParser(DiagramType diagramType) throws JAXBException, FileNotFoundException {
        this.diagramType=diagramType;
    }

    public MarkingStructure parse(AbstractDiagramStructure abstractDiagramStructureStudent,AbstractDiagramStructure abstractDiagramStructureTeacher)throws JAXBException, FileNotFoundException{
        this.abstractDiagramStructureStudent=abstractDiagramStructureStudent;
        this.abstractDiagramStructureTeacher=abstractDiagramStructureTeacher;
        this.markingStructure=new MarkingStructure();
        this.rubricRules= RubricRulesFactory.loadBuiltRubricRules(diagramType);
        System.out.println(rubricRules);
        return markingStructure;
    }

}
