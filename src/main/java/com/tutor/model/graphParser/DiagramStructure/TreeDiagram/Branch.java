package com.tutor.model.graphParser.DiagramStructure.TreeDiagram;

import com.tutor.model.graphicalPOJOObject.line.AngleLine;

/**
 * Created by Wiranji Dinelka on 8/5/2017.
 */
public class Branch {
    private AngleLine angleLine;
    private String outCome;
    private String probability;

    public Branch(AngleLine angleLine, String outCome, String probability) {
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

    public String getOutCome() {
        return outCome;
    }

    public void setOutCome(String outCome) {
        this.outCome = outCome;
    }

    public String getProbability() {
        return probability;
    }

    public void setProbability(String probability) {
        this.probability = probability;
    }
}
