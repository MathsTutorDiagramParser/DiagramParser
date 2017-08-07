package com.tutor;


import com.tutor.controller.GraphParser.GraphParsingHandler;
import com.tutor.model.graphParser.GraphGrammarBuilder.*;
import com.tutor.model.graphParser.parser.Parser;
import com.tutor.model.preProcessor.SVGtoPOJOMapper;
import com.tutor.model.util.DiagramType;
import com.tutor.model.util.SpatialRelation;
import com.tutor.model.graphicalPOJOObject.GraphicalImageComponent;
import com.tutor.service.preProcessorService.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Madhavi Ruwandika on 8/6/2017.
 */
public class main {

    private static Logger logger = LoggerFactory.getLogger(main.class);

    public static void main(String[] args) throws JAXBException, FileNotFoundException {
        SVGObjectTokenizationService svgObjectTokenizationService = new SVGObjectTokenizationServiceImpl();
        ObjectSequenceGeneratorService objectSequenceGeneratorService = new ObjectSequenceGeneratorServiceImpl();
        SpatialRelationshipGeneratorService spatialRelationShipGenerator = new SpatialRelationshipGeneratorServiceImpl();


        SVGtoPOJOMapper svGtoPOJOMapper = svgObjectTokenizationService.tokenize();
        logger.info("================done catogerization======================= ");

        objectSequenceGeneratorService.order(svGtoPOJOMapper.getGraphicalImageComponents());
        List<GraphicalImageComponent> orderedList = objectSequenceGeneratorService.getOrderedList();

        System.out.println("//////////////////////////////////done ordering//////////////////////////////////");

        ArrayList<SpatialRelation>[][] relations =
                spatialRelationShipGenerator.getSpatialRelationshipMatrixOfObject(orderedList);

        System.out.println("//////////////////////////////////done relationship identification//////////////////////////////////");


        Graph host  = new Graph();
        host.setGraphicalImageComponents(orderedList);
        host.setRelations(relations);

        Parser parser = new Parser(DiagramType.NUMBRELINE);
        parser.parse(host,svGtoPOJOMapper.getTexts());
    }
}
