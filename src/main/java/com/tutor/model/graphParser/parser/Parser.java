package com.tutor.model.graphParser.parser;

import com.tutor.model.graphParser.DiagramStructure.AbstractDiagramStructure;
import com.tutor.model.graphParser.GraphGrammarBuilder.Graph;
import com.tutor.model.graphicalPOJOObject.Text.Text;
import com.tutor.model.util.DiagramType;

import java.util.List;

/**
 * Created by Madhavi Ruwandika on 8/4/2017.
 */
public class Parser {

    AbstractDiagramStructure abstractDiagramStructure;

    public AbstractDiagramStructure parse(DiagramType diagramType,Graph host, List<Text> textList){


        StructuralParser structuralParser = new StructuralParser(diagramType);
        // validate the diagram through structural parser
        structuralParser.parse(host,abstractDiagramStructure);

        TextAssociator textAssociator = new TextAssociator();
        // validate the diagram through text associator
        textAssociator.associateText(abstractDiagramStructure,diagramType,textList);

        return abstractDiagramStructure;

    }


}
