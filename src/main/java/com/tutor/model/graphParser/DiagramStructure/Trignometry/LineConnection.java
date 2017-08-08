package com.tutor.model.graphParser.DiagramStructure.Trignometry;


import com.tutor.model.graphicalPOJOObject.line.Line;
import com.tutor.model.graphicalPOJOObject.Text.Text;
/**
 * Created by Vithusha on 8/6/2017.
 */
public class LineConnection  {

    private Line lineOne;
    private Line lineTwo;
    private Text angleText;
    private  Text vertexLabel;
    double connectionPoint_X;
    double connectionPoint_Y;


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

    public void setVertexLabel(Text vertexLabel) {
        this.vertexLabel = vertexLabel;
    }

    public Text getVertexLabel() {
        return vertexLabel;
    }

    public double getConnectionPoint_X() {

        if((lineOne.getX1()) == lineTwo.getX1() | (lineOne.getX1()) == lineTwo.getX2()){
            connectionPoint_X = lineOne.getX1();
        }

        else{
            connectionPoint_X = lineOne.getX2();
        }
        return connectionPoint_X;
    }

    public double getConnectionPoint_Y() {

        if((lineOne.getY1()) == lineTwo.getY1() | (lineOne.getY1()) == lineTwo.getY2()){
            connectionPoint_Y = lineOne.getY1();
        }
        else{
            connectionPoint_Y = lineOne.getY2();
        }
        return connectionPoint_Y;
    }
}
