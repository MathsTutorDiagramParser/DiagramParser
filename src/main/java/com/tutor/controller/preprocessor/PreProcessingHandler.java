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


        System.out.println("size of ordered list"+orderedList.size());
        for (int j=0; j<orderedList.size(); j++){
            System.out.println("++++++++++++++++++++++++");
            System.out.println( "x: "+orderedList.get(j).getX());
            System.out.println( "y: "+orderedList.get(j).getY());
            System.out.println( "X1: "+orderedList.get(j).getX1());
            System.out.println( "Y2: "+orderedList.get(j).getY1());
            System.out.println( "X2: "+orderedList.get(j).getX2());
            System.out.println( "Y2: "+orderedList.get(j).getY2());
            System.out.println(orderedList.get(j).objectType);
            System.out.println("++++++++++++++++++++++++");

        }
        return orderedList;

    }
    public ArrayList<SpatialRelation>[][] getSpatialRelations (List<GraphicalImageComponent> orderedList) {
        ArrayList<SpatialRelation>[][] relations = spatialRelationShipGenerator.getSpatialRelationshipMatrixOfObject(orderedList);

        return relations;
    }
    public ArrayList<Text> getAssociatedTextList () {
        return this.svGtoPOJOMapper.getTexts();
    }
}
