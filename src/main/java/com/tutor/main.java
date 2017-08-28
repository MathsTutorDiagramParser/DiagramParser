package com.tutor;

import com.tutor.common.FileReaderSupportService;
import com.tutor.common.FileReaderSupportServiceImpl;
import com.tutor.model.preProcessor.DiagramSpecificOrderGenerator;

import com.tutor.parser.model.graphParser.GraphGrammarGenerator.graphGrammarObject.Graph;
import com.tutor.parser.model.graphParser.Parser.Parser;
import com.tutor.parser.model.graphicalPOJOObject.GraphicalImageComponent;
import com.tutor.parser.model.preProcessor.SVGtoPOJOMapper;
import com.tutor.parser.model.util.DiagramType;
import com.tutor.parser.model.util.SpatialRelation;
import com.tutor.parser.service.preProcessorService.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 * Created by Madhavi Ruwandika on 8/6/2017.
 */
public class main {

    private static Logger logger = LoggerFactory.getLogger(main.class);
    public static void main(String[] args) throws JAXBException, FileNotFoundException {

        DiagramType diagramType = null;
        // create a scanner so we can read the command-line input
        Scanner scanner = new Scanner(System.in);
        //  prompt for the user's name
        System.out.println("Enter Diagram type : (1- NumberLine, 2- Histogram ,3- Tree diagram ,4- trignometric diagram) : ");

        // get their input as a String
        int type = Integer.parseInt(scanner.next());

        switch (type){
            case 1:
                diagramType = DiagramType.NUMBRELINE;
                break;
            case 2:
                diagramType = DiagramType.HISTOGRAM;
                break;
            case 3:
                diagramType = DiagramType.TREEDIAGRAM;
                break;
            case 4:
                diagramType = DiagramType.TRIGNOMETRICDIAGRAM;
                break;
        }


        SVGObjectTokenizationService svgObjectTokenizationService = new SVGObjectTokenizationServiceImpl();
        ObjectSequenceGeneratorService objectSequenceGeneratorService = new ObjectSequenceGeneratorServiceImpl();
        SpatialRelationshipGeneratorService spatialRelationShipGenerator = new SpatialRelationshipGeneratorServiceImpl();
        DiagramSpecificOrderGenerator diagramSpecificOrderGenerator = new DiagramSpecificOrderGenerator();

        FileReaderSupportService fileReaderSupportService = new FileReaderSupportServiceImpl();
        SVGtoPOJOMapper svGtoPOJOMapper = svgObjectTokenizationService.tokenize(fileReaderSupportService.readStudetAnswer(diagramType));
        logger.info("//////////////////////////////////done seperation//////////////////////////////////");


        List<GraphicalImageComponent> orderedList = objectSequenceGeneratorService.getOrderedList(svGtoPOJOMapper.getGraphicalImageComponents());
//        objectSequenceGeneratorService.order(svGtoPOJOMapper.getTexts());
        orderedList = diagramSpecificOrderGenerator.getDiagramSpecificOrderedList(orderedList, diagramType);
        List<GraphicalImageComponent> textList = objectSequenceGeneratorService.getOrderedList(svGtoPOJOMapper.getTexts());


        ArrayList<SpatialRelation>[][] relations =
                spatialRelationShipGenerator.getSpatialRelationshipMatrixOfObject(orderedList);

        logger.info("//////////////////////////////////done relationship identification//////////////////////////////////");


        Graph host  = new Graph();
        host.setGraphicalImageComponents(orderedList);
        host.setRelations(relations);

        Parser parser = new Parser(diagramType);
        parser.parse(host,textList);
    }
}
