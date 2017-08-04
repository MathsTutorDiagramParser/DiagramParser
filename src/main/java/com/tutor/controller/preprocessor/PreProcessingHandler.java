package com.tutor.controller.preprocessor;

import com.tutor.model.preProcessor.SVGtoPOJOMapper;
import com.tutor.model.util.SpatialRelation;
import com.tutor.model.SpatialRelation;
import com.tutor.model.graphParser.ObjectType;
import com.tutor.model.graphParser.ProductionRule;
import com.tutor.model.graphicalPOJOObject.Circle.Circle;
import com.tutor.model.graphicalPOJOObject.GraphicalImageComponent;
import com.tutor.service.preProcessorService.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

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

        JAXBContext contextObj = JAXBContext.newInstance(ProductionRule.class);

        Marshaller marshallerObj = contextObj.createMarshaller();
        marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        ObjectType o1 = new ObjectType("aaa","a111");
        ObjectType o2 = new ObjectType("aaab","a111b");
        ArrayList<ObjectType> list = new ArrayList<>();
        list.add(o1);
        list.add(o2);

        ProductionRule emp1=new ProductionRule(1,"Vimal Jaiswal",50000, list);

        marshallerObj.marshal(emp1, new FileOutputStream("employee.xml"));

    }
}
