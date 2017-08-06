package com.tutor.model.graphParser.parser;

import com.tutor.model.graphParser.DiagramStructure.AbstractDiagramStructure;
import com.tutor.model.graphParser.DiagramStructure.DiagramStructureFactory;
import com.tutor.model.graphParser.GraphGrammarBuilder.Graph;
import com.tutor.model.graphicalPOJOObject.Text.Text;
import com.tutor.model.util.DiagramType;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.util.List;

/**
 * Created by Madhavi Ruwandika on 8/4/2017.
 */
public class Parser {

    AbstractDiagramStructure abstractDiagramStructure;
    DiagramType diagramType;

    public Parser(DiagramType diagramType) {
        this.diagramType = diagramType;
        this.abstractDiagramStructure = DiagramStructureFactory.getAbstractDiagramStructure(diagramType);
    }

    public AbstractDiagramStructure parse(Graph host, List<Text> textList) throws JAXBException, FileNotFoundException {

        System.out.println("------------------------------Creating structural parser for diagram----------------------------");
        StructuralParser structuralParser = new StructuralParser(diagramType);
        // validate the diagram through structural Parser
        structuralParser.parse(host,abstractDiagramStructure);
        System.out.println("------------------------------finish structural parsing for diagram----------------------------");

        TextAssociator textAssociator = new TextAssociator();
        // validate the diagram through text associator
        System.out.println("------------------------------Started textuaral parsing for diagram----------------------------");
        textAssociator.associateText(abstractDiagramStructure,diagramType,textList);

        return abstractDiagramStructure;

    }


}
