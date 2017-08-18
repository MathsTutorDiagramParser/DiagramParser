package com.tutor.controller.main;

import com.tutor.controller.preprocessor.PreProcessingHandler;
import com.tutor.model.graphParser.GraphGrammarGenerator.graphGrammarObject.Graph;
import com.tutor.model.graphParser.Parser.Parser;
import com.tutor.model.graphicalPOJOObject.GraphicalImageComponent;
import com.tutor.model.util.DiagramType;
import com.tutor.model.util.SpatialRelation;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wiranji Dinelka on 8/7/2017.
 */
public class MainController {

    public void processSVGInput(DiagramType diagramType) throws JAXBException, FileNotFoundException {

        PreProcessingHandler preProcessingHandler = new PreProcessingHandler();

        List<GraphicalImageComponent> orderedList = preProcessingHandler.getOrderedList(diagramType);

        System.out.println("//////////////////////////////////Done ordering//////////////////////////////////");

        ArrayList<SpatialRelation>[][] relations = preProcessingHandler.getSpatialRelations(orderedList);

        System.out.println("//////////////////////////////////Done relationship identification//////////////////////////////////");

        ArrayList<GraphicalImageComponent> textArrayList = preProcessingHandler.getAssociatedTextList();


        Graph host  = new Graph();
        host.setGraphicalImageComponents(orderedList);
        host.setRelations(relations);


        Parser parser = new Parser(diagramType);
        parser.parse(host,textArrayList);
    }
}
