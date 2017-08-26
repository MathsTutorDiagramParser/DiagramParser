package com.tutor.web.controller.GraphParser;

import com.tutor.parser.model.graphParser.GraphGrammarGenerator.GraphGrammarReaderObject.*;
import com.tutor.parser.model.util.DiagramType;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

/**
 * Created by Madhavi Ruwandika on 8/4/2017.
 */
public class GraphParsingHandler {
    public GraphParsingHandler() {
    }

//    public void writeToXML() throws JAXBException, FileNotFoundException {
//
//        JAXBContext contextObj = JAXBContext.newInstance(XMLGraphGrammar.class);
//
//        Marshaller marshallerObj = contextObj.createMarshaller();
//        marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//
//        XMLTaggedValue xmlTaggedValue = new XMLTaggedValue("GraphType", "TREE_DIAGRAM");
//
//        ArrayList<XMLObjectTypes> objectTypeList = new ArrayList<>();
//        XMLObjectTypes type1 = new XMLObjectTypes("I0","ANGLE_LINE");
////        XMLObjectTypes type2 = new XMLObjectTypes("I1","Vertical_Line");
////        XMLObjectTypes type3 = new XMLObjectTypes("I2","Angle_Line");
////        XMLObjectTypes type4 = new XMLObjectTypes("I3","Circle");
////        XMLObjectTypes type5 = new XMLObjectTypes("I4","Initial_Number_Line");
//        objectTypeList.add(type1);
////        objectTypeList.add(type2);
////        objectTypeList.add(type3);
////        objectTypeList.add(type4);
////        objectTypeList.add(type5);
//
//        ArrayList<XMLSpatialRelations> xmlSpatialRelationsList = new ArrayList<>();
//        XMLSpatialRelations xmlSpatialRelations1 = new XMLSpatialRelations("S0","TOUCH");
//        XMLSpatialRelations xmlSpatialRelations2 = new XMLSpatialRelations("S1","UP");
//        XMLSpatialRelations xmlSpatialRelations3 = new XMLSpatialRelations("S2","DOWN");
//        xmlSpatialRelationsList.add(xmlSpatialRelations1);
//        xmlSpatialRelationsList.add(xmlSpatialRelations2);
//        xmlSpatialRelationsList.add(xmlSpatialRelations3);
//
//
//        ArrayList<XMLObject> objectIdList = new ArrayList<>();
//        XMLObject xmlObject1 = new XMLObject(0,"I1");
//        XMLObject xmlObject2 = new XMLObject(1,"I1");
//        objectIdList.add(xmlObject1);
//        objectIdList.add(xmlObject2);
//
//        ArrayList<XMLRelationship> xmlRelationshipList = new ArrayList<>();
//        XMLRelationship xmlRelationship1 = new XMLRelationship(0,1,"I10");
//        XMLRelationship xmlRelationship2 = new XMLRelationship(1,2,"I11");
//        xmlRelationshipList.add(xmlRelationship1);
//        xmlRelationshipList.add(xmlRelationship2);
//
//        ArrayList<XMLOperations> xmlOperationsList = new ArrayList<>();
//        XMLOperations xmlOperations1 = new XMLOperations("o_p1","COPY_OBJECT_ALL_ATTRIBUTE");
//        XMLOperations xmlOperations2 = new XMLOperations("o_p2","COPY_OBJECT_AVERAGE_ATTRIBUTE");
//        xmlOperationsList.add(xmlOperations1);
//        xmlOperationsList.add(xmlOperations2);
//
//        XMLGraph xmlGraph1 = new XMLGraph(objectIdList, xmlRelationshipList);
//        XMLGraph xmlGraph2 = new XMLGraph(objectIdList, xmlRelationshipList);
//
//        ArrayList<XMLRuleOperations> xmlRuleOperationsList = new ArrayList<>();
//        ArrayList<Integer> intFromList = new ArrayList<>();
//        intFromList.add(0);
//        intFromList.add(1);
//        XMLRuleOperations xmlRuleOperations1 = new XMLRuleOperations(xmlOperations1.getId(),intFromList);
//        intFromList.add(3);
//        XMLRuleOperations xmlRuleOperations2 = new XMLRuleOperations(xmlOperations2.getId(), intFromList);
//        xmlRuleOperationsList.add(xmlRuleOperations1);
//        xmlRuleOperationsList.add(xmlRuleOperations2);
//
//        ArrayList<XMLProductionRule> xmlProductionRuleList = new ArrayList<>();
//        XMLProductionRule xmlProductionRule1 = new XMLProductionRule(xmlGraph1, xmlGraph2, xmlRuleOperationsList);
//        XMLProductionRule xmlProductionRule2 = new XMLProductionRule(xmlGraph1, xmlGraph2, xmlRuleOperationsList);
//        xmlProductionRuleList.add(xmlProductionRule1);
//        xmlProductionRuleList.add(xmlProductionRule2);
//
//        XMLGraphGrammar xmlGraphGrammar = new XMLGraphGrammar(xmlTaggedValue,objectTypeList, xmlSpatialRelationsList, xmlOperationsList, xmlProductionRuleList);
//
//        marshallerObj.marshal(xmlGraphGrammar, new FileOutputStream("D:\\Projects\\FYP\\project\\MathsTutor\\src\\main\\resources\\com\\graphGrammar\\treeDiagram.xml"));
//    }

    public XMLGraphGrammar readFromXML(DiagramType diagramType){
        XMLGraphGrammar graphGrammar = new XMLGraphGrammar();
        String fileName = "";

        switch (diagramType) {
            case NUMBRELINE:
                fileName = "numberLine.xml";
                break;
            case HISTOGRAM:
                fileName = "histogram.xml";
                break;
            case TREEDIAGRAM:
                fileName = "treeDiagram.xml";
                break;
            case TRIGNOMETRICDIAGRAM:
                fileName = "trigDiagram.xml";
            default:
                fileName = "numberLine.xml";
                break;
        }
        try {
            File file = new File("D:\\Projects\\FYP\\project\\MathsTutor\\src\\main\\resources\\com\\graphGrammar\\"+fileName);

            JAXBContext jaxbContext = JAXBContext.newInstance(XMLGraphGrammar.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            graphGrammar =(XMLGraphGrammar) jaxbUnmarshaller.unmarshal(file);
        } catch (JAXBException e) {e.printStackTrace(); }

        return graphGrammar;
    }



}
