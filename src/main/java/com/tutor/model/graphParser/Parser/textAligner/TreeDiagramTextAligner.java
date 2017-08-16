package com.tutor.model.graphParser.Parser.textAligner;

import com.tutor.model.graphParser.DiagramStructure.AbstractDiagramStructure;

import com.tutor.model.graphParser.DiagramStructure.TreeDiagram.AbstractTreeDiagramStructure;
import com.tutor.model.graphParser.DiagramStructure.TreeDiagram.TreeBranch;
import com.tutor.model.graphParser.DiagramStructure.TreeDiagram.TreeGraph;
import com.tutor.model.graphParser.Parser.textAligner.TextAligner;
import com.tutor.model.graphicalPOJOObject.GraphicalImageComponent;
import com.tutor.model.graphicalPOJOObject.Text.Text;
import com.tutor.model.graphicalPOJOObject.line.Line;

import java.util.List;

/**
 * Created by Vithusha on 8/5/2017.
 */
public class TreeDiagramTextAligner extends TextAligner{


    public AbstractTreeDiagramStructure abstractTreeDiagramStructure;
    public List<GraphicalImageComponent> textList;
    TextAligner aligner = new TextAligner();
    List<TreeGraph> graphList;

    public AbstractDiagramStructure alignTextToTreeDiagram(AbstractTreeDiagramStructure abstractTreeDiagramStructure, List<GraphicalImageComponent> textList){
        this.abstractTreeDiagramStructure = abstractTreeDiagramStructure;
        this.textList = textList;
        this.graphList = abstractTreeDiagramStructure.getTreeGraphArrayList();
        for(TreeGraph graph: graphList) {
            matchDescriptionText(graph.getNode().getLeftTreeBranch());
            matchProbabilityText(graph.getNode().getLeftTreeBranch());
            matchDescriptionText(graph.getNode().getRightTreeBranch());
            matchProbabilityText(graph.getNode().getRightTreeBranch());
        }
        return abstractTreeDiagramStructure;
    }


    public void matchDescriptionText(TreeBranch branch){
        Line line = branch.getAngleLine();
        Text text = null;
        Double minDistance = Double.POSITIVE_INFINITY;
        for(GraphicalImageComponent textComponent: textList){
            Text desText = (Text) textComponent;
            boolean isInside = isInsideLengthTolerance(line, desText);
            if(isInside){
                if(minDistance > findLineToPointDistance(line, desText)){
                    minDistance = findLineToPointDistance(line, desText);
                    text = desText;
                }
            }
        }

        if (minDistance != Double.POSITIVE_INFINITY & text.isAttached() ==false ){
           branch.setOutCome(text);
            text.setAttached(true);
        }


    }

    public void matchProbabilityText(TreeBranch branch){
        Line line = branch.getAngleLine();
        Text text = null;
        Double minDistance = Double.POSITIVE_INFINITY;
        for (GraphicalImageComponent textComponent :textList){
            Text probText = (Text) textComponent;
            if(!probText.isAttached()){
                if(isProbabilityMatch(line,probText)){
                    if(minDistance > findProbabilityPointDistance(line , probText)){
                        minDistance = findProbabilityPointDistance(line , probText);
                        text = probText;
                    }
                }
            }
        }

        if (minDistance != Double.POSITIVE_INFINITY & text.isAttached() ==false ){
            branch.setProbability(text);
            text.setAttached(true);
        }
    }

    public boolean minimalComplexitySolution(){
        return false;
    }

    public boolean isInsideEllipse(double textCordinate_X, double textCordinate_Y, double center_X , double center_Y,
                                   double x_radius, double y_radius){
        double ellipseBoundary = (Math.pow((textCordinate_X - center_X),2)/ Math.pow(x_radius,2)) +
                (Math.pow((textCordinate_Y - center_Y),2)/ Math.pow(y_radius,2));
        if (ellipseBoundary <= 1){
            return true;
        }
        else {return false;}

    }







}