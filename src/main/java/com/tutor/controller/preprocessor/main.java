package com.tutor.controller.preprocessor;

import com.tutor.model.SpatialRelation;
import com.tutor.model.graphicalPOJOObject.Circle.Circle;
import com.tutor.model.graphicalPOJOObject.GraphicalImageComponent;
import com.tutor.model.graphicalPOJOObject.line.HorizontalLine;
import com.tutor.model.graphicalPOJOObject.line.VerticalLine;
import com.tutor.model.graphicalSVGObject.SVGImage;
import com.tutor.service.SVGReadPlatformService;
import com.tutor.service.SVGReadPlatformServiceImpl;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Madhavi Ruwandika on 7/22/2017.
 */
public class main {

    public static void main(String[] args) {

        SVGtoPOJOMapper svGtoPOJOMapper = SVGObjectTokenizer.tokenize();

        System.out.println("//////////////////////////////////done seperation//////////////////////////////////");

        ObjectSequenceOrderGenerator objectSequenceOrderGenerator = new ObjectSequenceOrderGenerator();
        objectSequenceOrderGenerator.order(svGtoPOJOMapper.getGraphicalImageComponents());
        List<GraphicalImageComponent> orderedList = objectSequenceOrderGenerator.getOrderedList();

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

        SpatialRelationShipGenerator spatialRelationShipGenerator = new SpatialRelationShipGenerator();

        ArrayList<SpatialRelation>[][] relations = spatialRelationShipGenerator.buildSpatialRelationShipMatrix(orderedList);


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
