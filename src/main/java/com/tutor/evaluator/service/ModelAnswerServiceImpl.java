package com.tutor.evaluator.service;

import com.tutor.evaluator.model.constants.TreeDiagramWordPool.DefaultStatement;
import com.tutor.evaluator.model.constants.TreeDiagramWordPool.Question3Statement;
import com.tutor.evaluator.model.constants.TreeDiagramWordPool.QuestionStatement;
import com.tutor.parser.model.graphParser.DiagramStructure.AbstractDiagramStructure;
import com.tutor.parser.model.graphParser.DiagramStructure.TreeDiagram.AbstractTreeDiagramStructure;
import com.tutor.parser.model.graphParser.DiagramStructure.TreeDiagram.TreeGraph;
import com.tutor.parser.model.graphParser.DiagramStructure.TreeDiagram.TreeNode;
import com.tutor.parser.model.graphParser.GraphGrammarGenerator.graphGrammarObject.Graph;
import com.tutor.parser.model.graphParser.Parser.Parser;
import com.tutor.parser.model.graphicalPOJOObject.GraphicalImageComponent;
import com.tutor.parser.model.graphicalPOJOObject.Text.Text;
import com.tutor.parser.model.preProcessor.SVGtoPOJOMapper;
import com.tutor.parser.model.util.DiagramType;
import com.tutor.parser.model.util.SpatialRelation;
import com.tutor.parser.service.preProcessorService.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Madhavi Ruwandika on 8/26/2017.
 */
public class ModelAnswerServiceImpl implements ModelAnswerService{
    Logger logger = LoggerFactory.getLogger(ModelAnswerServiceImpl.class);
    int qNum = 3;
    @Override
    public AbstractDiagramStructure getModelAnswer(String filename,DiagramType diagramType, int QuestionNumber) throws JAXBException, FileNotFoundException {


        SVGObjectTokenizationService svgObjectTokenizationService = new SVGObjectTokenizationServiceImpl();
        ObjectSequenceGeneratorService objectSequenceGeneratorService = new ObjectSequenceGeneratorServiceImpl();
        SpatialRelationshipGeneratorService spatialRelationShipGenerator = new SpatialRelationshipGeneratorServiceImpl();
        SVGtoPOJOMapper svGtoPOJOMapperT = svgObjectTokenizationService.tokenize("D:/Projects/FYP/project/MathsTutor/src/main/resources/test/"+filename);

        List<GraphicalImageComponent> orderedListT = objectSequenceGeneratorService.getOrderedList(svGtoPOJOMapperT.getGraphicalImageComponents());
        List<GraphicalImageComponent> textListT = objectSequenceGeneratorService.getOrderedList(svGtoPOJOMapperT.getTexts());
        ArrayList<SpatialRelation>[][] relationsT =
                spatialRelationShipGenerator.getSpatialRelationshipMatrixOfObject(orderedListT);

        Graph hostT  = new Graph();
        hostT.setGraphicalImageComponents(orderedListT);
        hostT.setRelations(relationsT);

        Parser parserT = new Parser(diagramType);
        AbstractDiagramStructure abstractDiagramStructureT= parserT.parse(hostT,textListT);

        if(diagramType == DiagramType.TREEDIAGRAM) {
            AbstractDiagramStructure abstractDiagramStructureNew =  setKeyWordsForOutcome(abstractDiagramStructureT);
            return abstractDiagramStructureNew;
        }


        return abstractDiagramStructureT;
    }

    private AbstractTreeDiagramStructure setKeyWordsForOutcome(AbstractDiagramStructure abstractDiagramStructure) {
        AbstractTreeDiagramStructure treeDiagramStructure = (AbstractTreeDiagramStructure)abstractDiagramStructure;

        for(int i = 0; i<treeDiagramStructure.getTreeGraphArrayList().size(); i++) {
            if(treeDiagramStructure.getTreeGraphArrayList().get(i).getNode() == null) {
                TreeNode root = treeDiagramStructure.getTreeGraphArrayList().get(i).getNodeOne();
                TreeNode child = treeDiagramStructure.getTreeGraphArrayList().get(i).getNodeTwo();

                treeDiagramStructure.getTreeGraphArrayList().get(i).setNodeOne(setOutcome(root,0));
                treeDiagramStructure.getTreeGraphArrayList().get(i).setNodeTwo(setOutcome(child,1));
            } else if(treeDiagramStructure.getTreeGraphArrayList().size() == 2) {
                TreeNode child = treeDiagramStructure.getTreeGraphArrayList().get(i).getNode();
                treeDiagramStructure.getTreeGraphArrayList().get(i).setNodeTwo(setOutcome(child,1));
            } else {
                TreeNode root = treeDiagramStructure.getTreeGraphArrayList().get(i).getNode();
                treeDiagramStructure.getTreeGraphArrayList().get(i).setNodeTwo(setOutcome(root,0));
            }
        }
        return treeDiagramStructure;
    }

    private TreeNode setOutcome(TreeNode node,int level) {
        ArrayList<Text> outcomeUpList;
        ArrayList<Text> outcomeDownList;
        QuestionStatement statements;
        if(qNum == 3) {
            statements = new Question3Statement();
        } else {
            statements = new DefaultStatement();
        }
        outcomeUpList = node.getLeftTreeBranch().getOutCome();
        outcomeDownList = node.getRightTreeBranch().getOutCome();
        if(level == 0) {
            if(statements.getRootUpperOutcomeList() != null) {
                outcomeUpList.addAll(statements.getRootUpperOutcomeList());
                outcomeDownList.addAll(statements.getRootLowerOutcomeList());
            }
        } else {
            if(statements.getChildUpperOutcomeList() != null) {
                outcomeUpList.addAll(statements.getChildUpperOutcomeList());
                outcomeDownList.addAll(statements.getChildLowerOutcomeList());
            }
        }
        node.getLeftTreeBranch().setOutCome(outcomeUpList);
        node.getRightTreeBranch().setOutCome(outcomeDownList);
        return node;
    }


}
