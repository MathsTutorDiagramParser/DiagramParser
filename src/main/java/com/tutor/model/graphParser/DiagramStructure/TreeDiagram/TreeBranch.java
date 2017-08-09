package com.tutor.model.graphParser.DiagramStructure.TreeDiagram;

import com.tutor.model.graphicalPOJOObject.GraphicalImageComponent;
import com.tutor.model.graphicalPOJOObject.line.AngleLine;
import com.tutor.model.graphicalPOJOObject.Text.Text;

/**
 * Created by Wiranji Dinelka on 8/5/2017.
 */
public class TreeBranch extends GraphicalImageComponent {
    private AngleLine angleLine;
    private Text outCome;
    private Text probability;

    public TreeBranch(AngleLine angleLine) {
        this.angleLine = angleLine;
    }

    public TreeBranch(AngleLine angleLine, Text outCome, Text probability) {
        this.angleLine = angleLine;
        this.outCome = outCome;
        this.probability = probability;
    }

    public AngleLine getAngleLine() {
        return angleLine;
    }

    public void setAngleLine(AngleLine angleLine) {
        this.angleLine = angleLine;
    }

    public Text getOutCome() {
        return outCome;
    }

    public void setOutCome(Text outCome) {
        this.outCome = outCome;
    }

    public Text getProbability() {
        return probability;
    }

    public void setProbability(Text probability) {
        this.probability = probability;
    }
}
