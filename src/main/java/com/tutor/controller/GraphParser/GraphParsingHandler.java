package com.tutor.controller.GraphParser;

import com.tutor.model.graphParser.*;
import com.tutor.model.graphicalPOJOObject.GraphicalImageComponent;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

/**
 * Created by Madhavi Ruwandika on 8/4/2017.
 */
public class GraphParsingHandler {

    public void writeToXML() throws JAXBException, FileNotFoundException {

        JAXBContext contextObj = JAXBContext.newInstance(ProductionRule.class);

        Marshaller marshallerObj = contextObj.createMarshaller();
        marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);


        TargetValue targetValue = new TargetValue("GraphType", "NUMBER_LINE");

        ArrayList<ObjectTypes> objectTypeList = new ArrayList<>();
        ObjectTypes type1 = new ObjectTypes("1","Horizontal_Line");
        ObjectTypes type2 = new ObjectTypes("2","Vertical_Line");
        objectTypeList.add(type1);
        objectTypeList.add(type2);

        ArrayList<SpatialRelations> spatialRelationsList = new ArrayList<>();
        SpatialRelations spatialRelations1 = new SpatialRelations("I10","Cross");
        SpatialRelations spatialRelations2 = new SpatialRelations("I11","Touch");
        spatialRelationsList.add(spatialRelations1);
        spatialRelationsList.add(spatialRelations2);


        ArrayList<String> objectIdList = new ArrayList<>();
        objectIdList.add("1");
        objectIdList.add("2");

        ArrayList<Relationship> relationshipList = new ArrayList<>();
        Relationship relationship1 = new Relationship("1","2","I10");
        Relationship relationship2 = new Relationship("1","2","I11");
        relationshipList.add(relationship1);
        relationshipList.add(relationship2);


        Graph graph1 = new Graph(objectIdList,relationshipList);
        Graph graph2 = new Graph(objectIdList,relationshipList);

        ArrayList<Rule> ruleList = new ArrayList<>();
        Rule rule1 = new Rule(graph1,graph2,"Application");
        Rule rule2 = new Rule(graph1,graph2,"Application2");
        ruleList.add(rule1);
        ruleList.add(rule2);

        ProductionRule productionRule = new ProductionRule(targetValue,objectTypeList,spatialRelationsList,ruleList);

        marshallerObj.marshal(productionRule, new FileOutputStream("rule.xml"));
    }
}
