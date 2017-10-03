package com.tutor.evaluator.service;

import com.tutor.parser.model.graphParser.DiagramStructure.AbstractDiagramStructure;
import com.tutor.parser.model.graphParser.GraphGrammarGenerator.graphGrammarObject.Graph;
import com.tutor.parser.model.graphParser.Parser.Parser;
import com.tutor.parser.model.graphicalPOJOObject.GraphicalImageComponent;
import com.tutor.parser.model.preProcessor.SVGtoPOJOMapper;
import com.tutor.parser.model.util.DiagramType;
import com.tutor.parser.model.util.SpatialRelation;
import com.tutor.parser.service.preProcessorService.*;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Madhavi Ruwandika on 8/26/2017.
 */
public class ModelAnswerServiceImpl implements ModelAnswerService{
    @Override
    public AbstractDiagramStructure getModelAnswer(String filename,DiagramType diagramType, int QuestionNumber) throws JAXBException, FileNotFoundException {


        SVGObjectTokenizationService svgObjectTokenizationService = new SVGObjectTokenizationServiceImpl();
        ObjectSequenceGeneratorService objectSequenceGeneratorService = new ObjectSequenceGeneratorServiceImpl();
        SpatialRelationshipGeneratorService spatialRelationShipGenerator = new SpatialRelationshipGeneratorServiceImpl();
        SVGtoPOJOMapper svGtoPOJOMapperT = svgObjectTokenizationService.tokenize("D:/Projects/FYP/project/MathsTutor/src/main/resources/test/"+filename);

        List<GraphicalImageComponent> orderedListT = objectSequenceGeneratorService.getOrderedList(svGtoPOJOMapperT.getGraphicalImageComponents());
        List<GraphicalImageComponent> textListT = objectSequenceGeneratorService.getOrderedList(svGtoPOJOMapperT.getTexts());
        ArrayList<SpatialRelation>[][] relationsT =
                spatialRelationShipGenerator.getSpatialRelationshipMatrixOfObject(orderedListT);

        Graph hostT  = new Graph();
        hostT.setGraphicalImageComponents(orderedListT);
        hostT.setRelations(relationsT);

        Parser parserT = new Parser(diagramType);
        AbstractDiagramStructure abstractDiagramStructureT= parserT.parse(hostT,textListT);

        return abstractDiagramStructureT;

    }
}
