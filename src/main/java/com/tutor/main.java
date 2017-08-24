package com.tutor;


import com.tutor.parser.model.graphParser.GraphGrammarGenerator.graphGrammarObject.Graph;
import com.tutor.parser.model.graphParser.Parser.Parser;
import com.tutor.parser.model.preProcessor.SVGtoPOJOMapper;
import com.tutor.parser.model.util.DiagramType;
import com.tutor.parser.model.util.SpatialRelation;
import com.tutor.parser.model.graphicalPOJOObject.GraphicalImageComponent;
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


        SVGtoPOJOMapper svGtoPOJOMapperS = svgObjectTokenizationService.tokenize(diagramType);
        SVGtoPOJOMapper svGtoPOJOMapperT = svgObjectTokenizationService.tokenize(diagramType);
        logger.info("//////////////////////////////////done seperation//////////////////////////////////");


        List<GraphicalImageComponent> orderedList = objectSequenceGeneratorService.getOrderedList(svGtoPOJOMapper.getGraphicalImageComponents());

        System.out.println("size of ordered list"+orderedList.size());
        for (int j=0; j<orderedList.size(); j++){
            System.out.println("++++++++++++++++++++++++");
            System.out.println( "x: "+orderedList.get(j).getX());
            System.out.println( "y: "+orderedList.get(j).getY());
            System.out.println( "X1: "+orderedList.get(j).getX1());
            System.out.println( "Y2: "+orderedList.get(j).getY1());
            System.out.println( "X2: "+orderedList.get(j).getX2());
            System.out.println( "Y2: "+orderedList.get(j).getY2());
            System.out.println(orderedList.get(j).objectType);
            System.out.println("++++++++++++++++++++++++");

        }

        List<GraphicalImageComponent> textList = objectSequenceGeneratorService.getOrderedList(svGtoPOJOMapper.getTexts());
        ArrayList<SpatialRelation>[][] relations =
                spatialRelationShipGenerator.getSpatialRelationshipMatrixOfObject(orderedList);
        //   print Spatial relationship
             for (int i=0; i< orderedList.size();i++){
                   System.out.println("======"+i+"=====");
                   for (int j=0;j<orderedList.size();j++){
                    System.out.print( "j="+j+ "=>");
                    for(int k=0;k< relations[i][j].size();k++){
                        System.out.print(relations[i][j].get(k)+"   ");
                    }
                    System.out.println("\n");
             }
        }
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

        Graph host  = new Graph();
        host.setGraphicalImageComponents(orderedList);
        host.setRelations(relations);

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
