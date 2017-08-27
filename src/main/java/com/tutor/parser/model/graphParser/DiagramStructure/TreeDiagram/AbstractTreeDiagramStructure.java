package com.tutor.parser.model.graphParser.DiagramStructure.TreeDiagram;

import com.tutor.parser.model.graphParser.DiagramStructure.AbstractDiagramStructure;
import com.tutor.parser.model.graphicalPOJOObject.GraphicalImageComponent;
import com.tutor.parser.model.graphicalPOJOObject.Text.Text;

import java.util.ArrayList;

/**
 * Created by Wiranji Dinelka on 8/5/2017.
 */
public class AbstractTreeDiagramStructure extends AbstractDiagramStructure {

    private ArrayList<TreeGraph> treeGraphArrayList;
    private Text levelOneDes;
    private Text levelTwoDes;

    public AbstractTreeDiagramStructure() {
        this.treeGraphArrayList = treeGraphArrayList;
    }

    public ArrayList<TreeGraph> getTreeGraphArrayList() {
        return treeGraphArrayList;
    }

    public void setTreeGraphArrayList(ArrayList<TreeGraph> treeGraphArrayList) {
        this.treeGraphArrayList = treeGraphArrayList;
    }

    public Text getLevelOneDes() {
        return levelOneDes;
    }

    public void setLevelOneDes(Text levelOneDes) {
        this.levelOneDes = levelOneDes;
    }

    public Text getLevelTwoDes() {
        return levelTwoDes;
    }

    public void setLevelTwoDes(Text levelTwoDes) {
        this.levelTwoDes = levelTwoDes;
    }

    @Override
    public void updateAbstractRepresentation(GraphicalImageComponent obj) {

    }
}
