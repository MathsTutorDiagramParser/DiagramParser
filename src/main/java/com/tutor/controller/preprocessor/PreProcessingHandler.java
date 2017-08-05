package com.tutor.controller.preprocessor;

import com.tutor.controller.GraphParser.GraphParsingHandler;
import com.tutor.model.graphParser.GraphGrammarBuilder.GrammarBuilder;
import com.tutor.model.graphParser.GraphGrammarBuilder.Graph;
import com.tutor.model.graphParser.GraphGrammarBuilder.NumberLineGrammar;
import com.tutor.model.graphParser.GraphGrammarBuilder.ProductionRule;
import com.tutor.model.graphParser.parser.Parser;
import com.tutor.model.preProcessor.SVGtoPOJOMapper;
import com.tutor.model.util.DiagramType;
import com.tutor.model.util.SpatialRelation;
import com.tutor.model.graphicalPOJOObject.GraphicalImageComponent;
import com.tutor.service.preProcessorService.*;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Madhavi Ruwandika on 7/22/2017.
 */
public class PreProcessingHandler {

    public static void main(String[] args) throws JAXBException, FileNotFoundException {


        SVGObjectTokenizationService svgObjectTokenizationService = new SVGObjectTokenizationServiceImpl();
        ObjectSequenceGeneratorService objectSequenceGeneratorService = new ObjectSequenceGeneratorServiceImpl();
        SpatialRelationshipGeneratorService spatialRelationShipGenerator =
                new SpatialRelationshipGeneratorServiceImpl();


        SVGtoPOJOMapper svGtoPOJOMapper = svgObjectTokenizationService.tokenize();
        System.out.println("//////////////////////////////////done seperation//////////////////////////////////");

        objectSequenceGeneratorService.order(svGtoPOJOMapper.getGraphicalImageComponents());
        List<GraphicalImageComponent> orderedList = objectSequenceGeneratorService.getOrderedList();

        // print ordered object list
        System.out.println("size of ordered list"+orderedList.size());
        for (int i=0;i<orderedList.size();i++){
            System.out.println("++++++++++++++++++++++++");
            System.out.println( "x: "+orderedList.get(i).getX());
            System.out.println( "y: "+orderedList.get(i).getY());
            System.out.println( "X1: "+orderedList.get(i).getX1());
            System.out.println( "Y2: "+orderedList.get(i).getY1());
            System.out.println( "X2: "+orderedList.get(i).getX2());
            System.out.println( "Y2: "+orderedList.get(i).getY2());
            System.out.println("++++++++++++++++++++++++");
        }



        ArrayList<SpatialRelation>[][] relations =
                spatialRelationShipGenerator.getSpatialRelationshipMatrixOfObject(orderedList);

        // print Spatial relationship
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


        //For test the grammar rulelist generation
        GrammarBuilder grammarBuilder = new GrammarBuilder();
        NumberLineGrammar numberLineGrammar = (NumberLineGrammar) grammarBuilder.loadBuiltGrammar("NumberLine");

        for (ProductionRule rule : numberLineGrammar.getRuleList()) {
            System.out.println(rule.getLeftGraph().getGraphicalImageComponents().get(0).objectType);
        }


//        GraphParsingHandler graphParsingHandler = new GraphParsingHandler();
//        graphParsingHandler.writeToXML();
//        graphParsingHandler.readFromXML();
//
//        Graph host  = new Graph();
//        host.setGraphicalImageComponents(orderedList);
//        host.setRelations(relations);
//
//        Parser parser = new Parser(DiagramType.NUMBRELINE);
//        parser.parse(host,svGtoPOJOMapper.getTexts());

    }
}
