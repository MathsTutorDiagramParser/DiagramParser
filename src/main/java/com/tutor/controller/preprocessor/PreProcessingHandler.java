package com.tutor.controller.preprocessor;

import com.tutor.controller.GraphParser.GraphParsingHandler;
import com.tutor.model.graphParser.GraphGrammarBuilder.*;
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

        System.out.println("//////////////////////////////////done ordering//////////////////////////////////");

       //  print ordered object list
        System.out.println("size of ordered list"+orderedList.size());
        for (int i=0;i<orderedList.size();i++){
            System.out.println("++++++++++++++++++++++++");
            System.out.println( "x: "+orderedList.get(i).getX());
            System.out.println( "y: "+orderedList.get(i).getY());
            System.out.println( "X1: "+orderedList.get(i).getX1());
            System.out.println( "Y1: "+orderedList.get(i).getY1());
            System.out.println( "X2: "+orderedList.get(i).getX2());
            System.out.println( "Y2: "+orderedList.get(i).getY2());
            System.out.println(orderedList.get(i).objectType);
            System.out.println("++++++++++++++++++++++++");

        }

        ArrayList<SpatialRelation>[][] relations =
                spatialRelationShipGenerator.getSpatialRelationshipMatrixOfObject(orderedList);

        System.out.println("//////////////////////////////////done relationship identification//////////////////////////////////");

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
    }
}
