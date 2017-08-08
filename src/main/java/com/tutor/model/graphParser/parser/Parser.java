package com.tutor.model.graphParser.parser;

import com.tutor.model.TextAligner.TextAlignmentAssigner;
import com.tutor.model.graphParser.DiagramStructure.AbstractDiagramStructure;
import com.tutor.model.graphParser.DiagramStructure.DiagramStructureFactory;
import com.tutor.model.graphParser.DiagramStructure.FeedBack;
import com.tutor.model.graphParser.DiagramStructure.NumberLine.AbstractNumberLineStructure;
import com.tutor.model.graphParser.DiagramStructure.NumberLine.MarkPoint;
import com.tutor.model.graphParser.DiagramStructure.NumberLine.TickPoint;
import com.tutor.model.graphParser.GraphGrammarBuilder.Graph;
import com.tutor.model.graphicalPOJOObject.GraphicalImageComponent;
import com.tutor.model.graphicalPOJOObject.Text.Text;
import com.tutor.model.util.DiagramType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Madhavi Ruwandika on 8/4/2017.
 */
public class Parser {

    Logger logger = LoggerFactory.getLogger(Parser.class);
    AbstractDiagramStructure abstractDiagramStructure;
    DiagramType diagramType;

    public Parser(DiagramType diagramType) {
        this.diagramType = diagramType;
        this.abstractDiagramStructure = DiagramStructureFactory.getAbstractDiagramStructure(diagramType);
    }

    public AbstractDiagramStructure parse(Graph host, List<GraphicalImageComponent> textList) throws JAXBException, FileNotFoundException {

        logger.info("------------------------------Creating structural parser for diagram----------------------------");
        StructuralParser structuralParser = new StructuralParser(diagramType);
        // validate the diagram through structural Parser
        structuralParser.parse(host,abstractDiagramStructure);
        logger.info("------------------------------finish structural parsing for diagram----------------------------");

        logger.info(">>>>>>>>>>>>>>> FeedBacks >>>>>>>>>>>>>>>>>");

        List<FeedBack> feedBacks= structuralParser.feedBacks;
        for (int i=0;i<feedBacks.size();i++){
            logger.info(feedBacks.get(i).getDescription());
        }
        logger.info(">>>>>>>>>>>>>>> FeedBacks >>>>>>>>>>>>>>>>>");

        logger.info("------------------------------Started textuaral parsing for diagram----------------------------");
        // validate the diagram through text associator
        TextAlignmentAssigner assigner = new TextAlignmentAssigner();
        assigner.assignTextAligner(abstractDiagramStructure,diagramType,textList);

        if(diagramType==DiagramType.NUMBRELINE){

            logger.info(" ==============Represented value================ ");
            List<MarkPoint> markPoints = ((AbstractNumberLineStructure)abstractDiagramStructure).getMarkPointList();
            List<TickPoint> tickPoints = ((AbstractNumberLineStructure)abstractDiagramStructure).getTickPointList();
            for (int i=0;i<tickPoints.size();i++){

                if(i==5){
                    logger.info(tickPoints.get(i).getVerticalLine().getHighestXCoordinate()+" "+tickPoints.get(i).getVerticalLine().getLowerestYCoordinate());
                }
                logger.info("tick "+i+" :"+tickPoints.get(i).getText().getText());
            }

            for (int i=0;i<markPoints.size();i++){


                logger.info("mark point "+i+" :"+markPoints.get(i).getCircle().getX()+" "+markPoints.get(i).getCircle().getY());

                if(markPoints.get(i).endOFTheThickLine=="LEFT"){
                    logger.info("LeftEnd :"+ markPoints.get(i).getText().getText()+" -> is_Filled : "+markPoints.get(i).isFilled);
                }
                if(markPoints.get(i).endOFTheThickLine=="RIGHT"){
                    logger.info("RightEnd :"+ markPoints.get(i).getText().getText()+"-> is_Filled : "+markPoints.get(i).isFilled);
                }
            }
        }

        return abstractDiagramStructure;

    }


}
