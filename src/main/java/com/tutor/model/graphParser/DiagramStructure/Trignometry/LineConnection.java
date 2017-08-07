package com.tutor.model.graphParser.DiagramStructure.Trignometry;

import com.tutor.model.graphicalPOJOObject.GraphicalImageComponent;
import com.tutor.model.graphicalPOJOObject.line.Line;
import com.tutor.model.graphicalPOJOObject.Text.Text;
/**
 * Created by Vithusha on 8/6/2017.
 */
public class LineConnection  {

    private Line lineOne;
    private Line lineTwo;
    private Text angleText;

    public void setLines(Line lineOne , Line lineTwo) {
        this.lineOne = lineOne;
        this.lineTwo = lineTwo;
    }

    public void setAngleText(Text angleText) {
        this.angleText = angleText;
    }

    public Line getLineOne() {
        return lineOne;
    }

    public Line getLineTwo() {
        return lineTwo;
    }

    public Text getAngleText() {
        return angleText;
    }
}
