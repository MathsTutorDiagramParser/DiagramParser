package com.tutor.parser.model.graphParser.GraphGrammar;

import com.tutor.web.controller.GraphParser.GraphParsingHandler;
import com.tutor.parser.model.graphParser.GraphGrammarGenerator.GraphGrammarReaderObject.*;
import com.tutor.parser.model.graphParser.GraphGrammarGenerator.graphGrammarObject.Graph;
import com.tutor.parser.model.graphParser.GraphGrammarGenerator.graphGrammarObject.ProductionRule;
import com.tutor.parser.model.graphParser.GraphGrammarGenerator.graphGrammarObject.RuleOperations;
import com.tutor.parser.model.graphicalPOJOObject.GraphicalImageComponent;
import com.tutor.parser.model.util.DiagramType;
import com.tutor.parser.model.util.ObjectType;
import com.tutor.parser.model.util.RuleOperation;
import com.tutor.parser.model.util.SpatialRelation;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wiranji Dinelka on 8/5/2017.
 */
public class GrammarGrammarFactory {

    public GrammarGrammarFactory(){
    }

    public static GraphGrammar loadBuiltGrammar(DiagramType type) throws JAXBException, FileNotFoundException {

        // Read and write grammar xml file
        GraphParsingHandler graphParsingHandler = new GraphParsingHandler();
        //graphParsingHandler.writeToXML();
        XMLGraphGrammar xmlGraphGrammar = graphParsingHandler.readFromXML(type);
        return convertXMLGraphGrammar(xmlGraphGrammar, type);

    }

    private static GraphGrammar convertXMLGraphGrammar( XMLGraphGrammar xmlGraphGrammar, DiagramType type ) {

        GraphGrammar graphGrammar = null;
        switch (type){
            case NUMBRELINE:
                graphGrammar = new NumberLineGrammar();
                break;
            case HISTOGRAM:
                graphGrammar = new HistogramGrammar();
                break;
            case TREEDIAGRAM:
                graphGrammar = new TreeDiagramGrammar();
                break;
            case TRIGNOMETRICDIAGRAM:
                graphGrammar = new TrignometricDGrammar();
            default:
                graphGrammar = null;
                break;
        }

        List<ProductionRule> ruleList = new ArrayList<>();
        List<XMLProductionRule> xmlRuleList = xmlGraphGrammar.getXMLProductionRules();

        for (XMLProductionRule xmlProductionRule: xmlRuleList ) {

            ProductionRule productionRule = null;
            int ruleId = xmlProductionRule.getRuleId();

            Graph leftGraph = null;

            XMLGraph leftXMLGraph = xmlProductionRule.getLeftXMLGraph();
            leftGraph = getGraph(leftXMLGraph, xmlGraphGrammar);

            Graph rightGraph = null;

            XMLGraph rightXMLGraph = xmlProductionRule.getRightXMLGraph();
            rightGraph = getGraph(rightXMLGraph,xmlGraphGrammar);

            ArrayList<XMLRuleOperations> xmlRuleOperationsList = xmlProductionRule.getXMLRuleOperations();
            String operation = null;
            ArrayList<Integer> existenceList = new ArrayList<>();
            ArrayList<RuleOperations> ruleOperationsArrayList = new ArrayList<>();
            RuleOperations ruleOperations;
            for (XMLRuleOperations xmlRuleOperation: xmlRuleOperationsList) {
                for (XMLOperations xmlOperations : xmlGraphGrammar.getOperations()) {
                    if (xmlRuleOperation.getId().equals(xmlOperations.getId())) {
                        operation = xmlOperations.getName();
                    }

                }
                for (int object_Id:xmlRuleOperation.getFrom_id_List()) {
                    existenceList.add(object_Id);
                }

                ruleOperations = new RuleOperations(RuleOperation.valueOf(operation),existenceList);
                ruleOperationsArrayList.add(ruleOperations);
            }

            productionRule = new ProductionRule(ruleId,leftGraph,rightGraph, ruleOperationsArrayList);
            ruleList.add(productionRule);
            graphGrammar.setRuleList(ruleList);
        }
        return graphGrammar;
    }

    private static Graph getGraph(XMLGraph xmlGraph, XMLGraphGrammar xmlGraphGrammar) {
        Graph graph = null;
        List<XMLObject> xmlObjectList = xmlGraph.getObjectIdList();
        List<GraphicalImageComponent> graphicalImageComponentList = new ArrayList<>();
        for (XMLObject xmlObject : xmlObjectList ) {
            for (XMLObjectTypes xmlObjectTypes: xmlGraphGrammar.getTypes()) {
                if(xmlObjectTypes.getId().equals(xmlObject.getType())) {
                    GraphicalImageComponent graphicalImageComponent = new GraphicalImageComponent(ObjectType.valueOf(xmlObjectTypes.getName()));
                    graphicalImageComponentList.add(graphicalImageComponent);
                }
            }
        }

        List<XMLRelationship> xmlRelationships = xmlGraph.getXMLRelationships();

        ArrayList<SpatialRelation>[][] relations = null;
        if(xmlRelationships!= null) {
            relations = new ArrayList[graphicalImageComponentList.size()][graphicalImageComponentList.size()];

            for (XMLRelationship xmlRelationship : xmlRelationships) {

                for (XMLSpatialRelations xmlSpatialRelations : xmlGraphGrammar.getXMLSpatialRelations()) {

                    if (xmlSpatialRelations.getId().equals(xmlRelationship.getSpatialRelationId())) {

                        SpatialRelation spatialRelationEnum = SpatialRelation.valueOf(xmlSpatialRelations.getName());

                        if (relations[xmlRelationship.getObjectIdOne()][xmlRelationship.getObjectIdTwo()] == null) {
                            relations[xmlRelationship.getObjectIdOne()][xmlRelationship.getObjectIdTwo()] = new ArrayList<>();
                            relations[xmlRelationship.getObjectIdOne()][xmlRelationship.getObjectIdTwo()].add(spatialRelationEnum);
                            relations[xmlRelationship.getObjectIdTwo()][xmlRelationship.getObjectIdOne()] = new ArrayList<>();
                            relations[xmlRelationship.getObjectIdTwo()][xmlRelationship.getObjectIdOne()].add(spatialRelationEnum);
                        } else {
                            relations[xmlRelationship.getObjectIdOne()][xmlRelationship.getObjectIdTwo()].add(spatialRelationEnum);
                            relations[xmlRelationship.getObjectIdTwo()][xmlRelationship.getObjectIdOne()].add(spatialRelationEnum);
                        }
                    }
                }
            }

        }
        graph = new Graph(graphicalImageComponentList, relations);
        return graph;
    }
}


