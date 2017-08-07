package com.tutor.model.graphParser.DiagramStructure.TreeDiagram;

import com.tutor.model.graphicalPOJOObject.GraphicalImageComponent;

/**
 * Created by Wiranji Dinelka on 8/7/2017.
 */
public class TreeGraph extends GraphicalImageComponent{
    private TreeNode nodeOne;
    private TreeNode nodeTwo;
    private TreeNode node;
    private int index;

    public TreeGraph() {
    }

    public TreeGraph(TreeNode nodeOne, TreeNode nodeTwo) {
        this.nodeOne = nodeOne;
        this.nodeTwo = nodeTwo;
    }

    public TreeGraph(TreeNode node, int index) {
        this.node = node;
        this.index = index;
    }

    public TreeNode getNodeOne() {
        return nodeOne;
    }

    public void setNodeOne(TreeNode nodeOne) {
        this.nodeOne = nodeOne;
    }

    public TreeNode getNodeTwo() {
        return nodeTwo;
    }

    public void setNodeTwo(TreeNode nodeTwo) {
        this.nodeTwo = nodeTwo;
    }

    public TreeNode getNode() {
        return node;
    }

    public void setNode(TreeNode node) {
        this.node = node;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}

