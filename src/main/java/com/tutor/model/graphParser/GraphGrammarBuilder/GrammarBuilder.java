package com.tutor.model.graphParser.GraphGrammarBuilder;

import com.tutor.controller.GraphParser.GraphParsingHandler;
import com.tutor.model.graphParser.GraphGrammarReader.*;
import com.tutor.model.graphicalPOJOObject.GraphicalImageComponent;
import com.tutor.model.util.ObjectType;
import com.tutor.model.util.RuleOperation;
import com.tutor.model.util.SpatialRelation;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static com.tutor.model.util.DiagramType.HISTOGRAM;
import static com.tutor.model.util.DiagramType.NUMBRELINE;

/**
 * Created by Wiranji Dinelka on 8/5/2017.
 */
public class GrammarBuilder {

    private String type;

    public GrammarBuilder(){
    }

    public GraphGrammar loadBuiltGrammar(String type) throws JAXBException, FileNotFoundException {

        // Read and write grammar xml file
        GraphParsingHandler graphParsingHandler = new GraphParsingHandler();
        //graphParsingHandler.writeToXML();
        XMLGraphGrammar xmlGraphGrammar = graphParsingHandler.readFromXML();
        return convertXMLGraphGrammar(xmlGraphGrammar, type);


    }

    public GraphGrammar convertXMLGraphGrammar( XMLGraphGrammar xmlGraphGrammar, String type ) {
        GraphGrammar graphGrammar = null;
        switch (type){
            case "NumberLine":
                graphGrammar = new NumberLineGrammar();
                break;
            case "Histogram":
                graphGrammar = new HistogramGrammar();
                break;
            default:
                graphGrammar = null;
                break;
        }

        List<ProductionRule> ruleList = new ArrayList<>();
        List<XMLProductionRule> xmlRuleList = xmlGraphGrammar.getXMLProductionRules();

        for (XMLProductionRule xmlProductionRule: xmlRuleList ) {

            ProductionRule productionRule = null;

            Graph leftGraph = null;

            XMLGraph leftXMLGraph = xmlProductionRule.getLeftXMLGraph();
            leftGraph = getGraph(leftXMLGraph, xmlGraphGrammar);

            Graph rightGraph = null;
            XMLGraph rightXMLGraph = xmlProductionRule.getRightXMLGraph();
            rightGraph = getGraph(rightXMLGraph,xmlGraphGrammar);

            String operationId = xmlProductionRule.getXMLRuleOperations().get(0).getId();
            String operation = null;
            for (XMLOperations xmlOperations: xmlGraphGrammar.getOperations() ) {
                if(operationId.equals(xmlOperations.getId())) {
                    operation = xmlOperations.getName();
                }

            }

            productionRule = new ProductionRule(leftGraph,rightGraph,RuleOperation.valueOf(operation));
            ruleList.add(productionRule);
            graphGrammar.setRuleList(ruleList);
        }
        return graphGrammar;
    }

    public Graph getGraph(XMLGraph xmlGraph, XMLGraphGrammar xmlGraphGrammar) {
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
                            ArrayList<SpatialRelation> spatialRelations = new ArrayList<>();
                            spatialRelations.add(spatialRelationEnum);
                            relations[xmlRelationship.getObjectIdOne()][xmlRelationship.getObjectIdTwo()] = spatialRelations;
                            relations[xmlRelationship.getObjectIdTwo()][xmlRelationship.getObjectIdOne()] = spatialRelations;
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


