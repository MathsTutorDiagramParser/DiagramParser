package com.tutor.parser.model.graphParser.DiagramStructure.TreeDiagram;

import com.tutor.parser.model.graphicalPOJOObject.GraphicalImageComponent;
import com.tutor.parser.model.graphicalPOJOObject.line.AngleLine;
import com.tutor.parser.model.graphicalPOJOObject.Text.Text;

import java.util.ArrayList;

/**
 * Created by Wiranji Dinelka on 8/5/2017.
 */
public class TreeBranch extends GraphicalImageComponent {
    private AngleLine angleLine;
    private ArrayList<Text> outCome;
    private Text probability;

    public TreeBranch(AngleLine angleLine) {
        this.angleLine = angleLine;
    }

    public TreeBranch(AngleLine angleLine, ArrayList<Text> outCome, Text probability) {
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

    public ArrayList<Text> getOutCome() {
        return outCome;
    }

    public void setOutCome(ArrayList<Text> outCome) {
        this.outCome = outCome;
    }

    public Text getProbability() {
        return probability;
    }

    public void setProbability(Text probability) {
        this.probability = probability;
    }
}
