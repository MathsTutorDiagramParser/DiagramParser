package com.tutor.model.graphParser.DiagramStructure.TreeDiagram;

import com.tutor.model.graphParser.DiagramStructure.AbstractDiagramStructure;

import java.util.ArrayList;

/**
 * Created by Wiranji Dinelka on 8/5/2017.
 */
public class AbstractTreeDiagramStructure extends AbstractDiagramStructure {

    private ArrayList<Node> nodeArrayList;

    public AbstractTreeDiagramStructure() {
    }

    public ArrayList<Node> getNodeArrayList() {
        return nodeArrayList;
    }

    public void setNodeArrayList(ArrayList<Node> nodeArrayList) {
        this.nodeArrayList = nodeArrayList;
    }

    @Override
    public void updateAbstractRepresentation() {

    }
}
