package com.tutor.parser.model.graphParser.Parser.textAligner;

import com.tutor.parser.model.graphParser.DiagramStructure.AbstractDiagramStructure;

import com.tutor.parser.model.graphParser.DiagramStructure.TreeDiagram.AbstractTreeDiagramStructure;
import com.tutor.parser.model.graphParser.DiagramStructure.TreeDiagram.TreeBranch;
import com.tutor.parser.model.graphParser.DiagramStructure.TreeDiagram.TreeGraph;
import com.tutor.parser.model.graphParser.DiagramStructure.TreeDiagram.TreeNode;
import com.tutor.parser.model.graphicalPOJOObject.GraphicalImageComponent;
import com.tutor.parser.model.graphicalPOJOObject.Text.Text;
import com.tutor.parser.model.graphicalPOJOObject.line.Line;

import java.util.List;

/**
 * Created by Vithusha on 8/5/2017.
 */
public class TreeDiagramTextAligner extends TextAligner{


    public AbstractTreeDiagramStructure abstractTreeDiagramStructure;
    public List<GraphicalImageComponent> textList;
    TextAligner aligner = new TextAligner();
    List<TreeGraph> graphList;
    Double distance ;
    Text levelOneDesc;
    Text levelTwoDesc;
    boolean isLevel1DesSet;
    boolean isLevel2DesSet;

    public TreeDiagramTextAligner() {
        this.distance = 0.0;
        this.isLevel1DesSet = false;
        this.isLevel2DesSet = false;
    }

    public AbstractDiagramStructure alignTextToTreeDiagram(AbstractTreeDiagramStructure abstractTreeDiagramStructure, List<GraphicalImageComponent> textList){
        this.abstractTreeDiagramStructure = abstractTreeDiagramStructure;
        this.textList = textList;
        this.graphList = abstractTreeDiagramStructure.getTreeGraphArrayList();

        if(graphList != null) {
            for(int i=0; i<graphList.size(); i++) {
                TreeGraph graph = graphList.get(i);
                if(graph.getNode() == null) {
                    this.distance = Math.abs(graph.getNodeOne().getX2() - graph.getNodeTwo().getX1());
                    matchDescriptionText(graph.getNodeOne().getLeftTreeBranch());
                    matchDescriptionText(graph.getNodeOne().getRightTreeBranch());
                    matchProbabilityText(graph.getNodeOne().getLeftTreeBranch());
                    matchProbabilityText(graph.getNodeOne().getRightTreeBranch());
                    matchDescriptionText(graph.getNodeOne().getLeftTreeBranch());
                    matchDescriptionText(graph.getNodeOne().getRightTreeBranch());

                    matchProbabilityText(graph.getNodeTwo().getLeftTreeBranch());
                    matchProbabilityText(graph.getNodeTwo().getRightTreeBranch());
                    matchDescriptionText(graph.getNodeTwo().getLeftTreeBranch());
                    matchDescriptionText(graph.getNodeTwo().getRightTreeBranch());

                } else {
                    if (distance.equals(0.0)) {
                        this.distance = Math.abs(graph.getNode().getX2() - graph.getNode().getX1());
                    }
                    matchProbabilityText(graph.getNode().getLeftTreeBranch());
                    matchProbabilityText(graph.getNode().getRightTreeBranch());
                    matchDescriptionText(graph.getNode().getLeftTreeBranch());
                    matchDescriptionText(graph.getNode().getRightTreeBranch());
                }
            }
            for(int i=0; i<graphList.size(); i++) {
                TreeGraph graph = graphList.get(i);
                if(graph.getNode() == null) {
                    this.distance = Math.abs(graph.getNodeOne().getX2() - graph.getNodeTwo().getX1());
                    if(!isLevel1DesSet) {
                        isLevel1DesSet = true;
                        levelOneDesc = matchLevelDescription(graph.getNodeOne());
                        abstractTreeDiagramStructure.setLevelOneDes(levelOneDesc);
                    }
                    if(!isLevel2DesSet) {
                        isLevel2DesSet = true;
                        levelTwoDesc = matchLevelDescription(graph.getNodeTwo());
                        abstractTreeDiagramStructure.setLevelTwoDes(levelTwoDesc);
                    }

                } else {
                    if (distance.equals(0.0)) {
                        this.distance = Math.abs(graph.getNode().getX2() - graph.getNode().getX1());
                    }
                    if(!isLevel1DesSet) {
                        isLevel1DesSet = true;
                        levelOneDesc = matchLevelDescription(graph.getNode());
                        abstractTreeDiagramStructure.setLevelOneDes(levelOneDesc);
                    }
                }
            }

        }

        return abstractTreeDiagramStructure;
    }


    public void matchProbabilityText(TreeBranch branch){
        Line line = branch.getAngleLine();
        Text text = null;
        Double minDistance = Double.POSITIVE_INFINITY;
        for(GraphicalImageComponent textComponent: textList){
            Text probText = (Text) textComponent;
            if(!probText.isAttached() && probText.getText().length() > 0) {
                boolean isInside = aligner.isInsideLengthTolerance(line, probText);
                if (isInside) {
                    if (minDistance > findDistanceToProbText(line, probText)) {
                        minDistance = findDistanceToProbText(line, probText);
                        text = probText;
                    }
                }
            }
        }

        if(text != null) {
            if (minDistance != Double.POSITIVE_INFINITY & text.isAttached() == false) {
                branch.setProbability(text);
                text.setAttached(true);
            }
        }
    }

    public void matchDescriptionText(TreeBranch branch){
        Line line = branch.getAngleLine();
        Text text = null;
        Double minDistance = Double.POSITIVE_INFINITY;
        for (GraphicalImageComponent textComponent :textList){
            Text descText = (Text) textComponent;
            if(!descText.isAttached() && descText.getText().length() > 0){
                if(isDescriptionMatch(line,descText)){
                    if(minDistance > findDistanceToDescText(line , descText)){
                        minDistance = findDistanceToDescText(line , descText);
                        text = descText;
                    }
                }
            }
        }

        if(text != null) {
            if (minDistance != Double.POSITIVE_INFINITY & text.isAttached() == false) {
                branch.setOutCome(text);
                text.setAttached(true);
            }
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

    public boolean isDescriptionMatch(Line line ,Text text){
        double end_X;
        double end_Y;
        double textPoint_X = text.getX();
        double textPoint_Y = text.getY();
        double radius_X;
        double radius_Y;
        double center_X;
        double center_Y;

        end_X = getEndOfLine_X(line);
        end_Y = getEndOfLine_Y(line);

        radius_X = distance/2;
        radius_Y = distance/2;

        center_X = end_X+distance/2;
        center_Y = end_Y;

        return isInsideEllipse(textPoint_X, textPoint_Y, center_X, center_Y,radius_X,radius_Y);

    }

    public Text matchLevelDescription( TreeNode treeNode) {
        Text text = null;
        for (GraphicalImageComponent textComponent :textList){
            Text levelDescText = (Text) textComponent;
            if(!levelDescText.isAttached()){
                if(isLevelDescMatch(treeNode,levelDescText)) {
                    text = levelDescText;
                    text.setAttached(true);
                }
            }
        }
        return text;
    }

    public boolean isLevelDescMatch(TreeNode treeNode , Text text){
        double start_X;
        double end_X;
        double textPoint_X ;


        start_X = treeNode.getX1();
        end_X = treeNode.getX2();
        textPoint_X = text.getX();

        if(start_X<textPoint_X && textPoint_X<end_X+distance){
            return true;
        } else return false;

    }

    public  double findDistanceToProbText(Line line, Text text){

        double textPoint_X = text.getX();
        double textPoint_Y = text.getY();
        double center_X = (line.getX1()+line.getX2())/2;
        double center_Y = (line.getY1()+line.getY2())/2;

        return findPointToPointDistance(center_X,center_Y,textPoint_X,textPoint_Y);

    }
    public  double findDistanceToDescText(Line line, Text text){

        double textPoint_Y = text.getY();
        double end_Y = getEndOfLine_Y(line);

        return Math.sqrt(Math.pow(textPoint_Y - end_Y , 2));

    }

    public double findPointToPointDistance(double pointOne_X,double pointOne_Y,double pointTwo_X,double pointTwo_Y ){
        double distance = Math.sqrt(Math.pow(pointTwo_X - pointOne_X , 2)+ Math.pow(pointTwo_Y-pointOne_Y,2));
        return  distance;
    }

}
