package com.tutor.parser.model.graphParser.Parser;


import com.tutor.parser.model.graphParser.DiagramStructure.DiagramStructureFactory;
import com.tutor.parser.model.graphParser.DiagramStructure.Histogram.AbstractHistogramStructure;
import com.tutor.parser.model.graphParser.DiagramStructure.Histogram.Bar;
import com.tutor.parser.model.graphParser.DiagramStructure.TreeDiagram.AbstractTreeDiagramStructure;
import com.tutor.parser.model.graphParser.DiagramStructure.TreeDiagram.TreeGraph;
import com.tutor.parser.model.graphParser.Parser.textAligner.TextAlignmentAssigner;
import com.tutor.parser.model.graphParser.DiagramStructure.AbstractDiagramStructure;

import com.tutor.parser.model.graphParser.GraphGrammarGenerator.graphGrammarObject.Graph;
import com.tutor.parser.model.graphicalPOJOObject.GraphicalImageComponent;
import com.tutor.parser.model.util.DiagramType;
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

    public AbstractDiagramStructure parse(Graph host, List<GraphicalImageComponent> textList) throws JAXBException, FileNotFoundException, NullPointerException {


        logger.info("------------------------------Creating structural Parser for diagram----------------------------");
        StructuralParser structuralParser = new StructuralParser(diagramType);
        // validate the diagram through structural Parser
        structuralParser.





























                parse(host,abstractDiagramStructure);
        logger.info("------------------------------finish structural parsing for diagram----------------------------");

        logger.info("------------------------------Started textuaral parsing for diagram----------------------------");
        // validate the diagram through text.svg associator
        TextAlignmentAssigner assigner = new TextAlignmentAssigner();
        assigner.assignTextAligner(abstractDiagramStructure,diagramType,textList);

        logger.info("------------------------------Finished textuaral parsing for diagram----------------------------");

        if(diagramType==DiagramType.NUMBRELINE){

//            logger.info(" ==============Represented value================ ");
//            List<MarkPoint> markPoints = ((AbstractNumberLineStructure)abstractDiagramStructure).getMarkPointList();
//            List<TickPoint> tickPoints = ((AbstractNumberLineStructure)abstractDiagramStructure).getTickPointList();
//            List<MarkPoint> extra = ((AbstractNumberLineStructure)abstractDiagramStructure).getExtraPointList();
//            logger.info("tick points");
//            for (int i=0;i<tickPoints.size();i++){
//                logger.info("tick "+i+" :"+tickPoints.get(i).getText().getText());
//            }
//
//            logger.info("mark points");
//            for (int j=0;j<markPoints.size();j++){
//
//                logger.info("mark point "+j+" :"+markPoints.get(j).getCircle().getX()+" "+markPoints.get(j).getCircle().getY());
//
//                if(markPoints.get(j).endOFTheThickLine=="LEFT"){
//                    logger.info("LeftEnd :"+ markPoints.get(j).getText().getText()+" -> is_Filled : "+markPoints.get(j).isFilled);
//                }
//                if(markPoints.get(j).endOFTheThickLine=="RIGHT"){
//                    logger.info("RightEnd :"+ markPoints.get(j).getText().getText()+"-> is_Filled : "+markPoints.get(j).isFilled);
//                }
//            }
//            logger.info("extra mark points");
//            if(extra!=null){
//                for (int k=0 ; k<extra.size();k++){
//                    if(extra.get(k).getText()!=null){
//                        logger.info("point :"+ extra.get(k).getText().getText()+" -> is_Filled : "+extra.get(k).isFilled);
//                    }
//                }
//            }
        }
        else if (diagramType == DiagramType.TREEDIAGRAM) {
            try {
                AbstractTreeDiagramStructure abstractTreeDiagramStructure = (AbstractTreeDiagramStructure) abstractDiagramStructure;

                ArrayList<TreeGraph> graphList = abstractTreeDiagramStructure.getTreeGraphArrayList();
                if (graphList != null) {
                    for (int i = 0; i < graphList.size(); i++) {
                        TreeGraph graph = graphList.get(i);
                        if (graph.getNode() == null) {
                            logger.info("Level One");
                            if (abstractTreeDiagramStructure.getLevelOneDes() != null) {
                                logger.info("Level String : " + abstractTreeDiagramStructure.getLevelOneDes().getText());
                            }
                            logger.info("Node level :" + graph.getNodeOne().getLevel());
                            logger.info("Upper branch");
                            for(int k = 0; k<graph.getNodeOne().getLeftTreeBranch().getOutCome().size();k++) {
                                logger.info("Outcome : " + graph.getNodeOne().getLeftTreeBranch().getOutCome().get(k).getText());

                            }
                            logger.info("Probability : " + graph.getNodeOne().getLeftTreeBranch().getProbability().getText());
                            logger.info("Down branch");
                            for(int k = 0; k<graph.getNodeOne().getRightTreeBranch().getOutCome().size();k++) {
                                logger.info("Outcome : " + graph.getNodeOne().getRightTreeBranch().getOutCome().get(k).getText());
                            }
                            logger.info("Probability : " + graph.getNodeOne().getRightTreeBranch().getProbability().getText());

                            logger.info("Level Two");
                            if (abstractTreeDiagramStructure.getLevelTwoDes() != null) {
                                logger.info("Level String : " + abstractTreeDiagramStructure.getLevelTwoDes().getText());
                            }
                            logger.info("Node level :" + graph.getNodeTwo().getLevel());
                            logger.info("Upper branch");

                            for(int k = 0; k<graph.getNodeTwo().getLeftTreeBranch().getOutCome().size();k++) {
                                logger.info("Outcome : " + graph.getNodeTwo().getLeftTreeBranch().getOutCome().get(k).getText());
                            }
                            logger.info("Probability : " + graph.getNodeTwo().getLeftTreeBranch().getProbability().getText());
                            logger.info("Down branch");
                            for(int k = 0; k<graph.getNodeTwo().getRightTreeBranch().getOutCome().size();k++) {
                                logger.info("Outcome : " + graph.getNodeTwo().getRightTreeBranch().getOutCome().get(k).getText());
                            }
                            logger.info("Probability : " + graph.getNodeTwo().getRightTreeBranch().getProbability().getText());
                        } else {

                            if (i == 0) {
                                logger.info("Level One");
                                if (abstractTreeDiagramStructure.getLevelOneDes() != null) {
                                    logger.info("Level String : " + abstractTreeDiagramStructure.getLevelOneDes().getText());
                                }
                                logger.info("Node level :" + graph.getNode().getLevel());
                                logger.info("Upper branch");
                                for(int k = 0; k<graph.getNode().getLeftTreeBranch().getOutCome().size();k++) {
                                    logger.info("Outcome : " + graph.getNode().getLeftTreeBranch().getOutCome().get(k).getText());
                                }
                                logger.info("Probability : " + graph.getNode().getLeftTreeBranch().getProbability().getText());
                                logger.info("Down branch");
                                for(int k = 0; k<graph.getNode().getRightTreeBranch().getOutCome().size();k++) {
                                    logger.info("Outcome : " + graph.getNode().getRightTreeBranch().getOutCome().get(k).getText());
                                }
                                logger.info("Probability : " + graph.getNode().getRightTreeBranch().getProbability().getText());

                            } else {
                                logger.info("Node level :" + graph.getNode().getLevel());
                                logger.info("Upper branch");
                                for(int k = 0; k<graph.getNode().getLeftTreeBranch().getOutCome().size();k++) {
                                    logger.info("Outcome : " + graph.getNode().getLeftTreeBranch().getOutCome().get(k).getText());
                                }
                                logger.info("Probability : " + graph.getNode().getLeftTreeBranch().getProbability().getText());
                                logger.info("Down branch");
                                for(int k = 0; k<graph.getNode().getRightTreeBranch().getOutCome().size(); k++) {
                                    logger.info("Outcome : " + graph.getNode().getRightTreeBranch().getOutCome().get(k).getText());
                                }
                                logger.info("Probability : " + graph.getNode().getRightTreeBranch().getProbability().getText());

                            }
                        }
                    }
                }
            } catch (NullPointerException e) {
                logger.info("Null Point Exception");
            }
        }else if(diagramType == DiagramType.HISTOGRAM){
            List<Bar> bar=((AbstractHistogramStructure) this.abstractDiagramStructure).getBar();
            int k=((AbstractHistogramStructure) this.abstractDiagramStructure).getBar().size();
            Double yRatio=((AbstractHistogramStructure) this.abstractDiagramStructure).getyAxisratio();
            Double xRatio=((AbstractHistogramStructure) this.abstractDiagramStructure).getxAxisratio();
            logger.info("Found "+k+" Bars ");

            for(int l=0;l<k;l++) {
                bar.get(l).setY(bar.get(l).rectangle.getHeight()*yRatio);
                bar.get(l).setXlow(bar.get(l).rectangle.getX()*xRatio);
                bar.get(l).setXhigh((bar.get(l).rectangle.getX()+bar.get(l).rectangle.getWidth())*xRatio);
                logger.info(l+"th Bar -> Y Value is : "+bar.get(l).rectangle.getHeight()*yRatio);

            }

        }

        return abstractDiagramStructure;

    }



}
