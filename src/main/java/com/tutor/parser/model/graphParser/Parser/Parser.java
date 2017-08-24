package com.tutor.parser.model.graphParser.Parser;

import com.tutor.parser.model.feedback.FeedBackGenerator;
import com.tutor.parser.model.feedback.FeedbackGeneratorFactory;
import com.tutor.parser.model.graphParser.DiagramStructure.Histogram.AbstractHistogramStructure;
import com.tutor.parser.model.graphParser.DiagramStructure.Histogram.Bar;
import com.tutor.parser.model.graphParser.DiagramStructure.TreeDiagram.AbstractTreeDiagramStructure;
import com.tutor.parser.model.graphParser.Parser.textAligner.TextAlignmentAssigner;
import com.tutor.parser.model.graphParser.DiagramStructure.AbstractDiagramStructure;
import com.tutor.parser.model.graphParser.DiagramStructure.DiagramStructureFactory;
import com.tutor.parser.model.feedback.FeedBack;
import com.tutor.parser.model.graphParser.DiagramStructure.NumberLine.AbstractNumberLineStructure;
import com.tutor.parser.model.graphParser.DiagramStructure.NumberLine.MarkPoint;
import com.tutor.parser.model.graphParser.DiagramStructure.NumberLine.TickPoint;
import com.tutor.parser.model.graphParser.GraphGrammarGenerator.graphGrammarObject.Graph;
import com.tutor.parser.model.graphicalPOJOObject.GraphicalImageComponent;
import com.tutor.parser.model.util.DiagramType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
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

        logger.info("------------------------------Creating structural Parser for diagram----------------------------");
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
            List<MarkPoint> extra = ((AbstractNumberLineStructure)abstractDiagramStructure).getExtraPointList();
            logger.info("tick points");
            for (int i=0;i<tickPoints.size();i++){
                logger.info("tick "+i+" :"+tickPoints.get(i).getText().getText());
            }

            logger.info("mark points");
            for (int j=0;j<markPoints.size();j++){

                logger.info("mark point "+j+" :"+markPoints.get(j).getCircle().getX()+" "+markPoints.get(j).getCircle().getY());

                if(markPoints.get(j).endOFTheThickLine=="LEFT"){
                    logger.info("LeftEnd :"+ markPoints.get(j).getText().getText()+" -> is_Filled : "+markPoints.get(j).isFilled);
                }
                if(markPoints.get(j).endOFTheThickLine=="RIGHT"){
                    logger.info("RightEnd :"+ markPoints.get(j).getText().getText()+"-> is_Filled : "+markPoints.get(j).isFilled);
                }
            }
            logger.info("extra mark points");
            if(extra!=null){
                for (int k=0 ; k<extra.size();k++){
                    if(extra.get(k).getText()!=null){
                        logger.info("point :"+ extra.get(k).getText().getText()+" -> is_Filled : "+extra.get(k).isFilled);
                    }
                }
            }
        }
        else if (diagramType == DiagramType.TREEDIAGRAM) {
            ((AbstractTreeDiagramStructure)abstractDiagramStructure).getTreeGraphArrayList();
        }else if(diagramType == DiagramType.HISTOGRAM){
            List<Bar> bar=((AbstractHistogramStructure) this.abstractDiagramStructure).getBar();
            int k=((AbstractHistogramStructure) this.abstractDiagramStructure).getBar().size();
            Double yRatio=((AbstractHistogramStructure) this.abstractDiagramStructure).getyAxisratio();
            logger.info("Found "+k+" Bars ");

            for(int l=0;l<k;l++) {
                logger.info(l+"th Bar -> Y Value is : "+bar.get(l).rectangle.getHeight()*yRatio);
            }



        }

        FeedBackGenerator feedBackGenerator = FeedbackGeneratorFactory.getFeedbackGenerator(diagramType);

        logger.info("*****************************************************");
    //    logger.info(feedBackGenerator.generateFinalFeedback(feedBacks,abstractDiagramStructure));
        logger.info("*****************************************************");

        return abstractDiagramStructure;

    }


}
