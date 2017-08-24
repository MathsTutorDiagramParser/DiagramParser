package com.tutor.model.graphParser.Parser;

import com.tutor.model.graphParser.DiagramStructure.AbstractDiagramStructure;
import com.tutor.model.rubricParser.MarkingStructure.MarkingStructure;
import com.tutor.model.rubricParser.RubricRules.RubricRulesFactory;
import com.tutor.model.rubricParser.RubricRulesGenerator.rubricRulesReaderObject.XMLRubricRules;
import com.tutor.model.util.DiagramType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;

public class GradeParser {

    private Logger logger = LoggerFactory.getLogger(GradeParser.class);
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
