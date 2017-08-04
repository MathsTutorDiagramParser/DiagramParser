package com.tutor.controller.GraphParser;

import com.tutor.model.graphParser.GraphGrammarReader.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

/**
 * Created by Madhavi Ruwandika on 8/4/2017.
 */
public class GraphParsingHandler {

    public void writeToXML() throws JAXBException, FileNotFoundException {

        JAXBContext contextObj = JAXBContext.newInstance(GraphGrammar.class);

        Marshaller marshallerObj = contextObj.createMarshaller();
        marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);


        TaggedValue taggedValue = new TaggedValue("GraphType", "NUMBER_LINE");

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

        ArrayList<Operations> operationsList = new ArrayList<>();
        Operations operations1 = new Operations("o_p1","COPY_OBJECT_ALL_ATTRIBUTE");
        Operations operations2 = new Operations("o_p2","COPY_OBJECT_ALL_ATTRIBUTE");
        operationsList.add(operations1);
        operationsList.add(operations2);

        Graph graph1 = new Graph(objectIdList,relationshipList);
        Graph graph2 = new Graph(objectIdList,relationshipList);

        ArrayList<RuleOperations> ruleOperationsList = new ArrayList<>();
        RuleOperations ruleOperations1 = new RuleOperations(operations1.getId(), type1.getId(),type2.getId());
        RuleOperations ruleOperations2 = new RuleOperations(operations2.getId(), type1.getId(),type2.getId());
        ruleOperationsList.add(ruleOperations1);
        ruleOperationsList.add(ruleOperations2);


        Operations operationType = new Operations("o_p","COPY_OBJECT_ALL_ATTRIBUTE");
        ArrayList<Rule> ruleList = new ArrayList<>();
        Rule rule1 = new Rule(graph1,graph2,ruleOperationsList);
        Rule rule2 = new Rule(graph1,graph2,ruleOperationsList);
        ruleList.add(rule1);
        ruleList.add(rule2);

        GraphGrammar graphGrammar = new GraphGrammar(taggedValue,objectTypeList,spatialRelationsList,operationsList,ruleList);

        marshallerObj.marshal(graphGrammar, new FileOutputStream("E:\\FYP\\implementation\\parser1\\DiagramParser\\src\\main\\resources\\com\\graphGrammar\\rule.xml"));
    }

    public void readFromXML(){
        try {
            File file = new File("E:\\FYP\\implementation\\parser1\\DiagramParser\\src\\main\\resources\\com\\graphGrammar\\rule.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(GraphGrammar.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            GraphGrammar graphGrammar=(GraphGrammar) jaxbUnmarshaller.unmarshal(file);
            System.out.println(graphGrammar.getTaggedValue().getTag()+" "+graphGrammar.getTypes().get(0).getName());

        } catch (JAXBException e) {e.printStackTrace(); }
    }
}
