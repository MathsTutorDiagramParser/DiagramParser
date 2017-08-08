package com.tutor.model.graphParser.Parser.textAligner;

import com.tutor.model.graphParser.DiagramStructure.AbstractDiagramStructure;
import com.tutor.model.graphParser.DiagramStructure.Trignometry.AbstractTrignometryStructure;
import com.tutor.model.graphParser.DiagramStructure.Trignometry.LineConnection;
import com.tutor.model.graphicalPOJOObject.GraphicalImageComponent;
import com.tutor.model.graphicalPOJOObject.Text.Text;
import com.tutor.model.graphicalPOJOObject.line.Line;

import java.util.List;

/**
 * Created by Vithusha on 8/5/2017.
 */
public class TrignometryTextAligner {
// TODO: 8/7/2017 Add the regex patterns correctly

    AbstractTrignometryStructure abstractTrignometryStructure;
    List<GraphicalImageComponent> textList;
    List<GraphicalImageComponent> angleTextList = null;
    List<GraphicalImageComponent> labelTextList = null;
    List<GraphicalImageComponent> lengthTextList = null;
    List<GraphicalImageComponent> labelsInside =null;
    List<GraphicalImageComponent> angleInside =null;
    String lengthRegex = "\\d{1,3}(\\.\\d{1,3})?";
    String labelRegex =  " ";
    String angleRegex = " ";
    double vertexToleranceRadius;
    double angleToleranceRadius;

    public AbstractDiagramStructure alignTextToTrignometry(AbstractTrignometryStructure abstractTrignometryStructure, List<GraphicalImageComponent> textList) {
        this.abstractTrignometryStructure = abstractTrignometryStructure;
        this.textList = textList;


        return null;
    }

    public  List<GraphicalImageComponent> getLengthList(List<GraphicalImageComponent> textList){
        String textValue;
        for (GraphicalImageComponent text : textList){
            textValue = ((Text)text).getText().replaceAll("\\s","");
            if (textValue.matches(lengthRegex)){
                lengthTextList.add((Text) text);
            }

        }
        return lengthTextList;
    }

    public  List<GraphicalImageComponent> getLabelList(List<GraphicalImageComponent> textList){
        String textValue;
        for (GraphicalImageComponent text : textList){
            textValue = ((Text)text).getText().replaceAll("\\s","");
            if (textValue.matches(labelRegex)){
                labelTextList.add((Text)text);
            }

        }
        return lengthTextList;
    }

    public  List<GraphicalImageComponent> getAngleList(List<Text> textList){
        String textValue;
        for (Text text : textList){
            textValue = text.getText().replaceAll("\\s","");
            if (textValue.matches(angleRegex)){
                angleTextList.add(text);
            }

        }
        return lengthTextList;
    }

    public void matchLabelText(List<GraphicalImageComponent> labelTextList, LineConnection lineConnection){
        for(GraphicalImageComponent angleText: labelTextList){

            Boolean matched = isInsideCircle(lineConnection, (Text) angleText);
            if(matched){
                angleInside.add(angleText);
            }
        }

        if (labelsInside.size() ==1){
            lineConnection.setVertexLabel((Text) labelsInside.get(0));
        }
        else if(labelsInside.size() > 1) {
            Text closestText = findClosestMatching(labelsInside , lineConnection);
            lineConnection.setVertexLabel(closestText);
        }
    }

    public void matchLengthText(List<Text> lengthTextList, LineConnection lineConnection){

    }

    public void matchAngleText(List<Text> angleTextList, LineConnection lineConnection){

        for(Text angletext: angleTextList){

            Boolean matched = isInsideSector(lineConnection, angletext);
            if(matched){
                angleInside.add(angletext);
            }
        }

        if (angleInside.size() ==1){
            lineConnection.setAngleText((Text) angleInside.get(0));
        }
        else if(angleInside.size() > 1) {
            Text closestText = findClosestMatching(angleInside , lineConnection);
            lineConnection.setAngleText(closestText);
        }
    }

    public Boolean isInsideCircle(LineConnection lineConnection, Text textCoordinate){
        Text label = textCoordinate;
        double connectionPoint_X = lineConnection.getConnectionPoint_X();
        double connectionPoint_Y = lineConnection.getConnectionPoint_Y();
        double labelCordinate_X = label.getX();
        double labelCordinate_Y = label.getY();

        double closeCircle = (Math.pow((labelCordinate_X-connectionPoint_X), 2) +
                Math.pow((labelCordinate_Y-connectionPoint_Y), 2))/ vertexToleranceRadius;

        if(closeCircle <= 1){
            return true;
        }
        else{
            return false;
        }
    }

    public Boolean isInsideSector(LineConnection lineConnection, Text textCoordinate){
        Text label = textCoordinate;
        double connectionPoint_X = lineConnection.getConnectionPoint_X();
        double connectionPoint_Y = lineConnection.getConnectionPoint_Y();
        double labelCordinate_X = label.getX();
        double labelCordinate_Y = label.getY();

        double closeSector = (Math.pow((labelCordinate_X-connectionPoint_X), 2));

        if(closeSector <= 1){
            return true;
        }
        else{
            return false;
        }
    }



    public Text findClosestMatching(List<GraphicalImageComponent> labelList, LineConnection lineConnection){
        double minimumDistance = Double.POSITIVE_INFINITY;
        Text closestLabel = null;
        for (GraphicalImageComponent label :labelList){
            double distance = Math.sqrt(Math.pow((label.getX()-lineConnection.getConnectionPoint_X()),2)
                    + Math.pow((label.getY()-lineConnection.getConnectionPoint_Y()),2));
            if(distance < minimumDistance){
                minimumDistance = distance;
                closestLabel = (Text) label;
            }
        }
        return  closestLabel;
    }


    public double findLineToPointDistance(Line line, Text text){
        double endOne_X = line.getX1();
        double endTwo_X = line.getX2();
        double endOne_Y = line.getY1();
        double endTwo_Y = line.getY2();
        double textPoint_X = text.getX();
        double textPoint_Y = text.getY();

        double lengthOfLine = Math.sqrt(Math.pow((endTwo_X - endOne_X),2)+Math.pow((endTwo_Y - endTwo_X),2));
        double lengthPointToEndOne = Math.sqrt(Math.pow((endOne_X - textPoint_X),2)+Math.pow((endOne_Y - textPoint_Y),2));
        double lengthPointToEndTwo = Math.sqrt(Math.pow((endTwo_X - textPoint_X),2)+Math.pow((endTwo_Y - textPoint_Y),2));

        double distanceEndOneToMid = (Math.pow(lengthOfLine , 2) + Math.pow(lengthPointToEndOne , 2)
                -Math.pow(lengthPointToEndTwo ,2)) / (2* lengthOfLine);
        double perpendicularDistance = Math.sqrt(Math.pow(lengthPointToEndOne , 2) - Math.pow(distanceEndOneToMid , 2));

        return  perpendicularDistance;
    }

}

