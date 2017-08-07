package com.tutor.controller.preprocessor;

import com.tutor.controller.GraphParser.GraphParsingHandler;
import com.tutor.model.graphParser.GraphGrammarBuilder.*;
import com.tutor.model.graphParser.parser.Parser;
import com.tutor.model.preProcessor.SVGtoPOJOMapper;
import com.tutor.model.util.DiagramType;
import com.tutor.model.util.SpatialRelation;
import com.tutor.model.graphicalPOJOObject.GraphicalImageComponent;
import com.tutor.service.preProcessorService.*;
import com.tutor.model.graphicalPOJOObject.Text.Text;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Madhavi Ruwandika on 7/22/2017.
 */
public class PreProcessingHandler {

    SVGObjectTokenizationService svgObjectTokenizationService;
    ObjectSequenceGeneratorService objectSequenceGeneratorService;
    SpatialRelationshipGeneratorService spatialRelationShipGenerator;
    SVGtoPOJOMapper svGtoPOJOMapper;

    public PreProcessingHandler() {
        this.svgObjectTokenizationService = new SVGObjectTokenizationServiceImpl();
        this.objectSequenceGeneratorService = new ObjectSequenceGeneratorServiceImpl();
        this.spatialRelationShipGenerator = new SpatialRelationshipGeneratorServiceImpl();
    }

    public List<GraphicalImageComponent> getOrderedList (DiagramType diagramType) throws JAXBException, FileNotFoundException {

        this.svGtoPOJOMapper = svgObjectTokenizationService.tokenize(diagramType);
        objectSequenceGeneratorService.order(svGtoPOJOMapper.getGraphicalImageComponents());

        List<GraphicalImageComponent> orderedList = objectSequenceGeneratorService.getOrderedList();
        return orderedList;

        //        System.out.println("size of ordered list"+orderedList.size());
//        for (int i=0;i<orderedList.size();i++){
//            System.out.println("++++++++++++++++++++++++");
//            System.out.println( "x: "+orderedList.get(i).getX());
//            System.out.println( "y: "+orderedList.get(i).getY());
//            System.out.println( "X1: "+orderedList.get(i).getX1());
//            System.out.println( "Y2: "+orderedList.get(i).getY1());
//            System.out.println( "X2: "+orderedList.get(i).getX2());
//            System.out.println( "Y2: "+orderedList.get(i).getY2());
//            System.out.println(orderedList.get(i).objectType);
//            System.out.println("++++++++++++++++++++++++");
//
//        }

    }
    public ArrayList<SpatialRelation>[][] getSpatialRelations (List<GraphicalImageComponent> orderedList) {
        ArrayList<SpatialRelation>[][] relations = spatialRelationShipGenerator.getSpatialRelationshipMatrixOfObject(orderedList);
        // print Spatial relationship
//        for (int i=0; i< orderedList.size();i++){
//            System.out.println("======"+i+"=====");
//            for (int j=0;j<orderedList.size();j++){
//                System.out.print( "j="+j+ "=>");
//                for(int k=0;k< relations[i][j].size();k++){
//                    System.out.print(relations[i][j].get(k)+"   ");
//                }
//                System.out.println("\n");
//            }
//
//        }
        return relations;
    }
    public ArrayList<Text> getAssociatedTextList () {
        return this.svGtoPOJOMapper.getTexts();
    }
}
