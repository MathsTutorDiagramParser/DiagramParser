package com.tutor.parser.model.graphParser.Parser.textAligner;


import com.tutor.parser.model.graphParser.DiagramStructure.AbstractDiagramStructure;
import com.tutor.parser.model.graphParser.DiagramStructure.Trignometry.AbstractTrignometryStructure;
import com.tutor.parser.model.graphParser.DiagramStructure.Trignometry.LineConnection;
import com.tutor.parser.model.graphParser.DiagramStructure.Trignometry.LineStructure;
import com.tutor.parser.model.graphParser.Parser.textAligner.TextAligner;
import com.tutor.parser.model.graphicalPOJOObject.GraphicalImageComponent;
import com.tutor.parser.model.graphicalPOJOObject.Text.Text;
import com.tutor.parser.model.graphicalPOJOObject.line.Line;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vithusha on 8/5/2017.
 */
public class TrignometryTextAligner extends TextAligner {
// TODO: 8/7/2017 Add the regex patterns correctly

    AbstractTrignometryStructure abstractTrignometryStructure;
    List<LineConnection> connectionList;
    List<LineStructure> lineList;
    List<GraphicalImageComponent> textList;
    List<Text> angleTextList = null;
    List<Text> labelTextList = null;
    List<Text> lengthTextList = null;
    List<Text> labelsInside =null;
    List<Text> angleInside = new ArrayList<>();
    String lengthRegex = "\\d{1,3}(\\.\\d{1,3})?";
    String labelRegex =  "^[a-zA-Z]$";
    String angleRegex = " ";
    double vertexToleranceRadius = 900;
    double angleToleranceRadius;

    public AbstractDiagramStructure alignTextToTrignometry(AbstractTrignometryStructure abstractTrignometryStructure, List<GraphicalImageComponent> textList) {
        this.abstractTrignometryStructure = abstractTrignometryStructure;
        this.textList = textList;


        connectionList = abstractTrignometryStructure.getConnectionList();
        lineList = abstractTrignometryStructure.getLineList();
        angleTextList = getSubList(textList, angleRegex);
        labelTextList = getSubList(textList, labelRegex);
        lengthTextList = getSubList(textList, lengthRegex);

        for(LineConnection connection : connectionList){
            matchLabelText(labelTextList ,connection);
            matchAngleText(angleTextList, connection);
        }

        if(lengthTextList!=null) {
            for (Text lengthText : lengthTextList) {
                matchLengthText(lengthText, lineList);
            }
        }

        return abstractTrignometryStructure;
    }


    //Get the list of angles from the text list using regex patterns
    public  List<Text> getSubList(List<GraphicalImageComponent> textList, String regex){
        String textValue;
        List<Text> subTextList = new ArrayList<>();
        for (GraphicalImageComponent textComponent : textList){
            Text text = (Text) textComponent;
            textValue = text.getText().trim();
            if (textValue.matches(regex)){
                subTextList.add(text);
            }

        }
        return subTextList;
    }
    //Match the correct label texts with the line connection and sets the label to the connection
    public void matchLabelText(List<Text> labelTextList, LineConnection lineConnection){
        if(labelTextList != null) {
            labelsInside = new ArrayList<>();

            for (Text labelText : labelTextList) {
                if (!labelText.isAttached()) {
                    Boolean matched = isInsideCircle(lineConnection, labelText);
                    if (matched) {
                        labelsInside.add(labelText);
                    }
                }
            }


            if ((labelsInside!=null)&&(labelsInside.size() == 1)) {
                lineConnection.setVertexLabel(labelsInside.get(0));
            } else if ((labelsInside!=null)&&(labelsInside.size() > 1) ){
                Text closestText = findClosestMatching(labelsInside, lineConnection);
                lineConnection.setVertexLabel(closestText);
                closestText.isAttached();
            }
        }
    }

    //  Matches each text in the lengthList to the closest Line
    // TODO: 8/8/2017 Have to make it so that, first take the points which belong to the ELLIPSE that lies over the line
    public void matchLengthText(Text lengthLabel, List<LineStructure> lineStructure){
        LineStructure closestLine = null;
        double minDistance = Double.POSITIVE_INFINITY;
        for(LineStructure line :lineStructure){

            if(isInsideLengthTolerance(line, lengthLabel)){
                if(minDistance > findLineToPointDistance(line.getLine(), lengthLabel)){
                    minDistance = findLineToPointDistance(line.getLine(),lengthLabel);
                    closestLine = line;
                }
            }
        }
        if(closestLine !=null){
            closestLine.setLengthText(lengthLabel);
            lengthLabel.setAttached(true);
        }

    }

    //Checks whether the Text lies inside the tolerance circle boundary

    private boolean isInsideLengthTolerance(LineStructure line, Text lengthLabel) {
        Line lengthLine = line.getLine();
        double endOne_X = lengthLine.getX1();
        double endOne_Y = lengthLine.getY1();
        double endTwo_X = lengthLine.getX2();
        double endTwo_Y = lengthLine.getY2();

        double label_X = lengthLabel.getX();
        double label_Y = lengthLabel.getY();

        double radius =  Math.sqrt(Math.pow((endTwo_X - endOne_X),2)+Math.pow((endTwo_Y - endTwo_Y),2));
        double center_X = (endOne_X + endTwo_X) / 2;
        double center_Y = (endOne_Y + endTwo_Y) / 2;

        double closeCircle = (Math.pow((label_X-center_X), 2) +
                Math.pow((label_Y-center_Y), 2))/ radius;

        if(closeCircle <= 1){
            return  true;
        }
        else {
            return false;
        }
    }


    //Matches a line connection with the closest possible angle text.
    public void matchAngleText(List<Text> angleTextList, LineConnection lineConnection){
        if(angleTextList!=null) {
            for (Text angletext : angleTextList) {
                if (!angletext.isAttached()) {
                    Boolean matched = isInsideSector(lineConnection, angletext);
                    if (matched) {
                        angleInside.add(angletext);
                    }
                }
            }

            if ((angleInside!=null)&&(angleInside.size() == 1) ){
                lineConnection.setAngleText(angleInside.get(0));
            } else if ((labelsInside!=null)&&(angleInside.size() > 1)) {
                Text closestText = findClosestMatching(angleInside, lineConnection);
                lineConnection.setAngleText(closestText);
                closestText.setAttached(true);
            }
        }
    }
    //Checks whether a text coordinate lies inside the circle
    public Boolean isInsideCircle(LineConnection lineConnection, Text textCoordinate){
        Text label = textCoordinate;
        double connectionPoint_X = lineConnection.getConnectionPoint_X();
        double connectionPoint_Y = lineConnection.getConnectionPoint_Y();
        double labelCordinate_X = label.getX();
        double labelCordinate_Y = label.getY();

        double circleBoundary = (Math.pow((labelCordinate_X-connectionPoint_X), 2) +
                Math.pow((labelCordinate_Y-connectionPoint_Y), 2))/ vertexToleranceRadius;

        if(circleBoundary <= 1){
            return true;
        }
        else{
            return false;
        }
    }

    // TODO: 8/8/2017 Find a proper formula and implement the method correctly
    //Return whether a point lies within a sector
    public Boolean isInsideSector(LineConnection lineConnection, Text angleText){
        Text label = angleText;
        double connectionPoint_X = lineConnection.getConnectionPoint_X();
        double connectionPoint_Y = lineConnection.getConnectionPoint_Y();
        double labelCordinate_X = label.getX();
        double labelCordinate_Y = label.getY();
        double lineOneSmallAngle;
        double lineTwoSmallAngle;
        double vectorOne_X = 0;
        double vectorTwo_X = 0;
        double vectorOne_Y = 0;
        double vectorTwo_Y = 0;
        double labelVector_X = labelCordinate_X - connectionPoint_X;
        double labelVector_Y = labelCordinate_Y - connectionPoint_Y;
        boolean isInside;
        Line lineOne = lineConnection.getLineOne().getLine();
        Line lineTwo = lineConnection.getLineTwo().getLine();
        double gradientLineOne = Double.parseDouble(null);
        double gradientLineTwo = Double.parseDouble(null);



        if(lineOne.getX1() == connectionPoint_X){
            gradientLineOne = (lineOne.getX2()-lineOne.getX1())/(lineOne.getY2()-lineOne.getY1());
            vectorOne_X = lineOne.getX2()-lineOne.getX1();
            vectorOne_Y = lineOne.getY2() - lineOne.getY1();

        }
        else if(lineOne.getX2() == connectionPoint_X){
            gradientLineOne = (lineOne.getX1()-lineOne.getX2())/(lineOne.getY1()-lineOne.getY2());
            vectorOne_X = lineOne.getX1()-lineOne.getX2();
            vectorOne_Y = lineOne.getY1() - lineOne.getY2();
        }

        if(lineTwo.getX1() == connectionPoint_X){
            gradientLineTwo = (lineTwo.getX2()-lineTwo.getX1())/(lineTwo.getY2()-lineTwo.getY1());
            vectorTwo_X = lineTwo.getX2()-lineTwo.getX1();
            vectorTwo_Y = lineTwo.getY2() - lineTwo.getY1();
        }
        else if(lineTwo.getX2() == connectionPoint_X){
            gradientLineTwo = (lineTwo.getX1()-lineTwo.getX2())/(lineTwo.getY1()-lineTwo.getY2());
            vectorTwo_X = lineTwo.getX1()-lineTwo.getX2();
            vectorTwo_Y = lineTwo.getY1() - lineTwo.getY2();
        }

         double totalDegree = findAngleBetweenLines(vectorOne_X,vectorOne_Y,vectorTwo_X,vectorTwo_Y);
         double lineOneLabelDegree = findAngleBetweenLines(vectorOne_X,vectorOne_Y,labelVector_X,labelVector_Y);
         double lineTwoLabelDegree = findAngleBetweenLines(vectorTwo_X,vectorTwo_Y,labelVector_X,labelVector_Y);

         if ((totalDegree <= (lineOneLabelDegree + lineTwoLabelDegree+ angleToleranceRadius))&&(totalDegree >= (lineOneLabelDegree + lineTwoLabelDegree - angleToleranceRadius)))
        {
             isInside = true;
         }
         else{
             isInside= false;
        }
            return isInside;
    }

    // Finds the label that is very close to the connection point and returns

    public Text findClosestMatching(List<Text> labelList, LineConnection lineConnection){
        double minimumDistance = Double.POSITIVE_INFINITY;
        Text closestLabel = null;
        for (Text label :labelList){
            double distance = Math.sqrt(Math.pow((label.getX()-lineConnection.getConnectionPoint_X()),2)
                    + Math.pow((label.getY()-lineConnection.getConnectionPoint_Y()),2));
            if(distance < minimumDistance){
                minimumDistance = distance;
                closestLabel = label;
            }
        }
        return  closestLabel;
    }


    //Set the radius of the circle to check the vertex label boundary
    public void setVertexToleranceRadius(double vertexToleranceRadius){
        this.vertexToleranceRadius = vertexToleranceRadius;
    }

    //Set the radius of the circle to check the angle boundary
    public void setAngleToleranceRadius(double angleToleranceRadius){
        this.angleToleranceRadius = angleToleranceRadius ;
    }
    public double findAngleBetweenLines(double one_x , double one_y, double two_x, double two_y){

        double cosValue = ((one_x*two_x) + (one_y*two_y))/(Math.sqrt((one_x*one_x)+(one_y*one_y))*(Math.sqrt((two_x*two_x)+two_y*two_y)));

        double degree = Math.toDegrees(Math.acos(cosValue));
        return degree;
    }
}

