package com.tutor.model.graphParser.DiagramStructure.TreeDiagram;

import com.tutor.model.graphParser.DiagramStructure.AbstractDiagramStructure;

import java.util.ArrayList;

/**
 * Created by Wiranji Dinelka on 8/5/2017.
 */
public class AbstractTreeDiagramStructure extends AbstractDiagramStructure {

    private ArrayList<TreeGraph> treeGraphArrayList;

    public AbstractTreeDiagramStructure() {
        this.treeGraphArrayList = treeGraphArrayList;
    }

    public ArrayList<TreeGraph> getTreeGraphArrayList() {
        return treeGraphArrayList;
    }

    public void setTreeGraphArrayList(ArrayList<TreeGraph> treeGraphArrayList) {
        this.treeGraphArrayList = treeGraphArrayList;
    }

    @Override
    public void updateAbstractRepresentation() {

    }
}
