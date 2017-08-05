package com.tutor.model.graphParser.DiagramStructure.TreeDiagram;

import com.tutor.model.graphicalPOJOObject.GraphicalImageComponent;

/**
 * Created by Wiranji Dinelka on 8/5/2017.
 */
public class Node extends GraphicalImageComponent {
    private Branch leftBranch;
    private Branch rightBranch;
    private String level;

    public Node(Branch leftBranch, Branch rightBranch, String level) {
        super();
        this.leftBranch = leftBranch;
        this.rightBranch = rightBranch;
        this.level = level;
    }

    public Branch getLeftBranch() {
        return leftBranch;
    }

    public void setLeftBranch(Branch leftBranch) {
        this.leftBranch = leftBranch;
    }

    public Branch getRightBranch() {
        return rightBranch;
    }

    public void setRightBranch(Branch rightBranch) {
        this.rightBranch = rightBranch;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
