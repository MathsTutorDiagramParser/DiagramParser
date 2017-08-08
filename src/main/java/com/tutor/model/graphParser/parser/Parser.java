package com.tutor.model.graphParser.parser;

import com.tutor.model.TextAligner.TextAlignmentAssigner;
import com.tutor.model.graphParser.DiagramStructure.AbstractDiagramStructure;
import com.tutor.model.graphParser.DiagramStructure.DiagramStructureFactory;
import com.tutor.model.graphParser.DiagramStructure.FeedBack;
import com.tutor.model.graphParser.DiagramStructure.NumberLine.AbstractNumberLineStructure;
import com.tutor.model.graphParser.DiagramStructure.NumberLine.MarkPoint;
import com.tutor.model.graphParser.GraphGrammarBuilder.Graph;
import com.tutor.model.graphicalPOJOObject.Text.Text;
import com.tutor.model.util.DiagramType;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Madhavi Ruwandika on 8/4/2017.
 */
public class Parser {

    AbstractDiagramStructure abstractDiagramStructure;
    DiagramType diagramType;

    public Parser(DiagramType diagramType) {
        this.diagramType = diagramType;
        this.abstractDiagramStructure = DiagramStructureFactory.getAbstractDiagramStructure(diagramType);
    }

    public AbstractDiagramStructure parse(Graph host, List<Text> textList) throws JAXBException, FileNotFoundException {

        System.out.println("------------------------------Creating structural parser for diagram----------------------------");
        StructuralParser structuralParser = new StructuralParser(diagramType);
        // validate the diagram through structural Parser
        structuralParser.parse(host,abstractDiagramStructure);
        System.out.println("------------------------------finish structural parsing for diagram----------------------------");

        System.out.println(">>>>>>>>>>>>>>> FeedBacks >>>>>>>>>>>>>>>>>");

        List<FeedBack> feedBacks= structuralParser.feedBacks;
        for (int i=0;i<feedBacks.size();i++){
            System.out.println(feedBacks.get(i).getDescription());
        }
        System.out.println(">>>>>>>>>>>>>>> FeedBacks >>>>>>>>>>>>>>>>>");

        System.out.println("------------------------------Started textuaral parsing for diagram----------------------------");
        // validate the diagram through text associator
        TextAlignmentAssigner assigner = new TextAlignmentAssigner();
        assigner.assignTextAligner(abstractDiagramStructure,diagramType,textList);

        if(diagramType==DiagramType.NUMBRELINE){
            System.out.println(" ==============Represented value================ ");
            List<MarkPoint> markPoints = ((AbstractNumberLineStructure)abstractDiagramStructure).getMarkPointList();
            for (int i=0;i<markPoints.size();i++){
                if(markPoints.get(i).endOFTheThickLine=="LEFT"){
                    System.out.println("LeftEnd :"+ markPoints.get(i).getText().getText()+"is Filled : "+markPoints.get(i).isFilled);
                }
                if(markPoints.get(i).endOFTheThickLine=="RIGHT"){
                    System.out.println("RightEnd :"+ markPoints.get(i).getText().getText()+"is Filled : "+markPoints.get(i).isFilled);
                }
            }
        }

        return abstractDiagramStructure;

    }


}
