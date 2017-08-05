package com.tutor.controller.GraphParser;

import com.tutor.model.graphParser.GraphGrammarReader.*;
import com.tutor.model.graphParser.parser.Parser;
import com.tutor.model.util.DiagramType;

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
    public GraphParsingHandler() {
    }

    public void writeToXML() throws JAXBException, FileNotFoundException {

        JAXBContext contextObj = JAXBContext.newInstance(XMLGraphGrammar.class);

        Marshaller marshallerObj = contextObj.createMarshaller();
        marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        XMLTaggedValue XMLTaggedValue = new XMLTaggedValue("GraphType", "NUMBER_LINE");

        ArrayList<XMLObjectTypes> objectTypeList = new ArrayList<>();
        XMLObjectTypes type1 = new XMLObjectTypes("1","Horizontal_Line");
        XMLObjectTypes type2 = new XMLObjectTypes("2","Vertical_Line");
        objectTypeList.add(type1);
        objectTypeList.add(type2);

        ArrayList<XMLSpatialRelations> XMLSpatialRelationsList = new ArrayList<>();
        XMLSpatialRelations XMLSpatialRelations1 = new XMLSpatialRelations("I10","Cross");
        XMLSpatialRelations XMLSpatialRelations2 = new XMLSpatialRelations("I11","Touch");
        XMLSpatialRelationsList.add(XMLSpatialRelations1);
        XMLSpatialRelationsList.add(XMLSpatialRelations2);


        ArrayList<XMLObject> objectIdList = new ArrayList<>();
        XMLObject xmlObject1 = new XMLObject(0,"I1");
        XMLObject xmlObject2 = new XMLObject(1,"I1");
        objectIdList.add(xmlObject1);
        objectIdList.add(xmlObject2);

        ArrayList<XMLRelationship> XMLRelationshipList = new ArrayList<>();
        XMLRelationship XMLRelationship1 = new XMLRelationship(0,1,"I10");
        XMLRelationship XMLRelationship2 = new XMLRelationship(1,2,"I11");
        XMLRelationshipList.add(XMLRelationship1);
        XMLRelationshipList.add(XMLRelationship2);

        ArrayList<XMLOperations> XMLOperationsList = new ArrayList<>();
        XMLOperations XMLOperations1 = new XMLOperations("o_p1","COPY_OBJECT_ALL_ATTRIBUTE");
        XMLOperations XMLOperations2 = new XMLOperations("o_p2","COPY_OBJECT_ALL_ATTRIBUTE");
        XMLOperationsList.add(XMLOperations1);
        XMLOperationsList.add(XMLOperations2);

        XMLGraph XMLGraph1 = new XMLGraph(objectIdList, XMLRelationshipList);
        XMLGraph XMLGraph2 = new XMLGraph(objectIdList, XMLRelationshipList);

        ArrayList<XMLRuleOperations> XMLRuleOperationsList = new ArrayList<>();
        XMLRuleOperations XMLRuleOperations1 = new XMLRuleOperations(XMLOperations1.getId(), type1.getId(),type2.getId());
        XMLRuleOperations XMLRuleOperations2 = new XMLRuleOperations(XMLOperations2.getId(), type1.getId(),type2.getId());
        XMLRuleOperationsList.add(XMLRuleOperations1);
        XMLRuleOperationsList.add(XMLRuleOperations2);


        XMLOperations operationType = new XMLOperations("o_p","COPY_OBJECT_ALL_ATTRIBUTE");
        ArrayList<XMLProductionRule> XMLProductionRuleList = new ArrayList<>();
        XMLProductionRule XMLProductionRule1 = new XMLProductionRule(XMLGraph1, XMLGraph2, XMLRuleOperationsList);
        XMLProductionRule XMLProductionRule2 = new XMLProductionRule(XMLGraph1, XMLGraph2, XMLRuleOperationsList);
        XMLProductionRuleList.add(XMLProductionRule1);
        XMLProductionRuleList.add(XMLProductionRule2);

        XMLGraphGrammar XMLGraphGrammar = new XMLGraphGrammar(XMLTaggedValue,objectTypeList, XMLSpatialRelationsList, XMLOperationsList, XMLProductionRuleList);

        marshallerObj.marshal(XMLGraphGrammar, new FileOutputStream("E:\\FYP\\implementation\\parser1\\DiagramParser\\src\\main\\resources\\com\\graphGrammar\\rule.xml"));
    }

    public XMLGraphGrammar readFromXML(){
        XMLGraphGrammar graphGrammar = new XMLGraphGrammar();
        try {
            File file = new File("E:\\FYP\\implementation\\parser1\\DiagramParser\\src\\main\\resources\\com\\graphGrammar\\rule.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(XMLGraphGrammar.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            graphGrammar =(XMLGraphGrammar) jaxbUnmarshaller.unmarshal(file);
            System.out.println(graphGrammar.getXMLTaggedValue().getTag()+" "+ graphGrammar.getTypes().get(0).getName());

        } catch (JAXBException e) {e.printStackTrace(); }

        return graphGrammar;
    }

}
