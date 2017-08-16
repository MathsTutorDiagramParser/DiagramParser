package com.tutor;


import com.tutor.model.graphParser.GraphGrammarGenerator.graphGrammarObject.Graph;
import com.tutor.model.graphParser.Parser.Parser;
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

        SVGtoPOJOMapper svGtoPOJOMapper = svgObjectTokenizationService.tokenize(DiagramType.NUMBRELINE);
        logger.info("//////////////////////////////////done seperation//////////////////////////////////");


        List<GraphicalImageComponent> orderedList = objectSequenceGeneratorService.getOrderedList(svGtoPOJOMapper.getGraphicalImageComponents());
//        objectSequenceGeneratorService.order(svGtoPOJOMapper.getTexts());
        List<GraphicalImageComponent> textList = objectSequenceGeneratorService.getOrderedList(svGtoPOJOMapper.getTexts());

//        System.out.println("size of ordered list"+orderedList.size());
//        for (int j=0; j<orderedList.size(); j++){
//            System.out.println("++++++++++++++++++++++++");
//            System.out.println( "x: "+orderedList.get(j).getX());
//            System.out.println( "y: "+orderedList.get(j).getY());
//            System.out.println( "X1: "+orderedList.get(j).getX1());
//            System.out.println( "Y2: "+orderedList.get(j).getY1());
//            System.out.println( "X2: "+orderedList.get(j).getX2());
//            System.out.println( "Y2: "+orderedList.get(j).getY2());
//            System.out.println(orderedList.get(j).objectType);
//            System.out.println("++++++++++++++++++++++++");
//
//        }
//        System.out.println("//////////////////////////////////done ordering//////////////////////////////////");

        ArrayList<SpatialRelation>[][] relations =
                spatialRelationShipGenerator.getSpatialRelationshipMatrixOfObject(orderedList);
        //   print Spatial relationship
        for (int i=11; i< 12;i++){
            System.out.println("======"+i+" "+orderedList.get(i).getX1()+"===== "+orderedList.get(i).getX2());
           for (int j=0;j<orderedList.size();j++){
                System.out.print( "j="+j+ " "+orderedList.get(i).objectType+"=>"+orderedList.get(j).objectType+" are ");

                for(int k=0;k< relations[i][j].size();k++){
                   System.out.print(relations[i][j].get(k)+"   ");
                }
                System.out.println("\n");
           }

        }

        logger.info("//////////////////////////////////done relationship identification//////////////////////////////////");


        Graph host  = new Graph();
        host.setGraphicalImageComponents(orderedList);
        host.setRelations(relations);

        Parser parser = new Parser(DiagramType.HISTOGRAM);
        parser.parse(host,textList);
    }
}
