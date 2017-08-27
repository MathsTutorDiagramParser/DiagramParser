package com.tutor.model.graphParser.DiagramStructure.Trignometry;


import com.tutor.model.graphicalPOJOObject.GraphicalImageComponent;
import com.tutor.model.graphicalPOJOObject.line.Line;
import com.tutor.model.graphicalPOJOObject.Text.Text;
import com.tutor.model.util.ObjectType;

/**
 * Created by Vithusha on 8/6/2017.
 */
public class LineConnection  extends GraphicalImageComponent{

    private LineStructure lineOne;
    private LineStructure lineTwo;
    private Text angleText;
    private  Text vertexLabel;
    double connectionPoint_X;
    double connectionPoint_Y;


    public LineConnection(LineStructure lineOne , LineStructure lineTwo) {
        this.lineOne = lineOne;
        this.lineTwo = lineTwo;
        this.objectType = ObjectType.CONNECTION;
    }


    public void setAngleText(Text angleText) {
        this.angleText = angleText;
    }

    public LineStructure getLineOne() {
        return lineOne;
    }

    public LineStructure getLineTwo() {
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

        if((lineOne.getLine().getX1()) == lineTwo.getLine().getX1() | (lineOne.getLine().getX1()) == lineTwo.getLine().getX2()){
            connectionPoint_X = lineOne.getLine().getX1();
        }

        else{
            connectionPoint_X = lineOne.getLine().getX2();
        }
        return connectionPoint_X;
    }

    public double getConnectionPoint_Y() {

        if((lineOne.getLine().getY1()) == lineTwo.getLine().getY1() | (lineOne.getLine().getY1()) == lineTwo.getLine().getY2()){
            connectionPoint_Y = lineOne.getLine().getY1();
        }
        else{
            connectionPoint_Y = lineOne.getLine().getY2();
        }
        return connectionPoint_Y;
    }
    public double getLowerEnd_X(){
        return Double.parseDouble(null);
    }
    public double getLowerEnd_Y(){
        return Double.parseDouble(null);
    }
    public double getHigherEnd_X(){
        return Double.parseDouble(null);
    }
    public double getHigherEnd_Y(){
        return Double.parseDouble(null);
    }

}
