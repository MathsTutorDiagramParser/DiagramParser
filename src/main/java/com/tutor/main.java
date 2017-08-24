package com.tutor;


import com.tutor.model.graphParser.DiagramStructure.AbstractDiagramStructure;
import com.tutor.model.graphParser.GraphGrammarGenerator.graphGrammarObject.Graph;
import com.tutor.model.graphParser.Parser.GradeParser;
import com.tutor.model.graphParser.Parser.Parser;
import com.tutor.model.preProcessor.SVGtoPOJOMapper;
import com.tutor.model.rubricParser.MarkingStructure.MarkingStructure;
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


        SVGtoPOJOMapper svGtoPOJOMapperS = svgObjectTokenizationService.tokenize(diagramType);
        SVGtoPOJOMapper svGtoPOJOMapperT = svgObjectTokenizationService.tokenize(diagramType);
        logger.info("//////////////////////////////////done seperation//////////////////////////////////");


        List<GraphicalImageComponent> orderedListS = objectSequenceGeneratorService.getOrderedList(svGtoPOJOMapperS.getGraphicalImageComponents());
        List<GraphicalImageComponent> orderedListT = objectSequenceGeneratorService.getOrderedList(svGtoPOJOMapperT.getGraphicalImageComponents());
//        objectSequenceGeneratorService.order(svGtoPOJOMapper.getTexts());
        List<GraphicalImageComponent> textListS = objectSequenceGeneratorService.getOrderedList(svGtoPOJOMapperS.getTexts());
        List<GraphicalImageComponent> textListT = objectSequenceGeneratorService.getOrderedList(svGtoPOJOMapperT.getTexts());


        ArrayList<SpatialRelation>[][] relationsS =
                spatialRelationShipGenerator.getSpatialRelationshipMatrixOfObject(orderedListS);

        ArrayList<SpatialRelation>[][] relationsT =
                spatialRelationShipGenerator.getSpatialRelationshipMatrixOfObject(orderedListS);

        logger.info("//////////////////////////////////done relationship identification//////////////////////////////////");


        Graph hostS  = new Graph();
        hostS.setGraphicalImageComponents(orderedListS);
        hostS.setRelations(relationsS);

        Graph hostT  = new Graph();
        hostT.setGraphicalImageComponents(orderedListT);
        hostT.setRelations(relationsT);


        Parser parserS = new Parser(diagramType);
        Parser parserT = new Parser(diagramType);
        AbstractDiagramStructure abstractDiagramStructureS=parserS.parse(hostS,textListS);
        AbstractDiagramStructure abstractDiagramStructureT=parserT.parse(hostT,textListT);

        logger.info("//////////////////////////////////Starting Grading Module//////////////////////////////////");

        GradeParser gradeParser=new GradeParser(diagramType);
        MarkingStructure markingStructure= gradeParser.parse(abstractDiagramStructureS,abstractDiagramStructureT);
        System.out.println("Set A debugger here and go up.. do the implemenation there");

    }
}
