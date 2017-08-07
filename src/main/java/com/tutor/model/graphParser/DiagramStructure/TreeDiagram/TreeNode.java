package com.tutor.model.graphParser.DiagramStructure.TreeDiagram;

import com.tutor.model.graphicalPOJOObject.GraphicalImageComponent;

/**
 * Created by Wiranji Dinelka on 8/5/2017.
 */
public class TreeNode extends GraphicalImageComponent {
    private TreeBranch leftTreeBranch;
    private TreeBranch rightTreeBranch;
    private String level;

    public TreeNode() {
    }

    public TreeNode(TreeBranch leftTreeBranch, TreeBranch rightTreeBranch, String level) {
        super();
        this.leftTreeBranch = leftTreeBranch;
        this.rightTreeBranch = rightTreeBranch;
        this.level = level;
    }

    public TreeBranch getLeftTreeBranch() {
        return leftTreeBranch;
    }

    public void setLeftTreeBranch(TreeBranch leftTreeBranch) {
        this.leftTreeBranch = leftTreeBranch;
    }

    public TreeBranch getRightTreeBranch() {
        return rightTreeBranch;
    }

    public void setRightTreeBranch(TreeBranch rightTreeBranch) {
        this.rightTreeBranch = rightTreeBranch;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
