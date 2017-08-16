package com.tutor.model.graphParser.Parser.textAligner;

import com.tutor.model.graphParser.DiagramStructure.Trignometry.LineStructure;
import com.tutor.model.graphicalPOJOObject.Text.Text;
import com.tutor.model.graphicalPOJOObject.line.Line;

import java.util.List;

/**
 * Created by Madhavi Ruwandika on 8/7/2017.
 */
public class TextAligner {

    public boolean isInsideEllipse(double textCordinate_X, double textCordinate_Y, double center_X, double center_Y, double x_radius, double y_radius) {
        double ellipseBoundary = (Math.pow((textCordinate_X - center_X), 2) / Math.pow(x_radius, 2)) +
                (Math.pow((textCordinate_Y - center_Y), 2) / Math.pow(y_radius, 2));
        if (ellipseBoundary <= 1) {
            return true;
        } else {
            return false;
        }
    }

    //Get all the texts in the textList that are unmatched with any graphical component
    public List<Text> getUnmatchedTextList(List<Text> textList) {
        List<Text> unmatchedTextlist = null;
        for (Text text : textList) {
            if (!text.isAttached()) {
                unmatchedTextlist.add(text);
            }
        }
        return unmatchedTextlist;
    }


    public boolean isInsideLengthTolerance(Line line, Text lengthLabel) {
        Line lengthLine = line;
        double endOne_X = lengthLine.getX1();
        double endOne_Y = lengthLine.getY1();
        double endTwo_X = lengthLine.getX2();
        double endTwo_Y = lengthLine.getY2();

        double label_X = lengthLabel.getX();
        double label_Y = lengthLabel.getY();

        double radius =  Math.sqrt(Math.pow((endTwo_X - endOne_X),2)+Math.pow((endTwo_Y - endTwo_Y),2)) / 2;
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

    public double findLineToPointDistance(Line line, Text text){
        double endOne_X = line.getX1();
        double endTwo_X = line.getX2();
        double endOne_Y = line.getY1();
        double endTwo_Y = line.getY2();
        double textPoint_X = text.getX();
        double textPoint_Y = text.getY();

        double lengthOfLine = Math.sqrt(Math.pow((endTwo_X - endOne_X),2)+Math.pow((endTwo_Y - endTwo_Y),2));
        double lengthPointToEndOne = Math.sqrt(Math.pow((endOne_X - textPoint_X),2)+Math.pow((endOne_Y - textPoint_Y),2));
        double lengthPointToEndTwo = Math.sqrt(Math.pow((endTwo_X - textPoint_X),2)+Math.pow((endTwo_Y - textPoint_Y),2));

        double distanceEndOneToMid = (Math.pow(lengthOfLine , 2) + Math.pow(lengthPointToEndOne , 2)
                -Math.pow(lengthPointToEndTwo ,2)) / (2* lengthOfLine);
        double perpendicularDistance = Math.sqrt(Math.pow(lengthPointToEndOne , 2) - Math.pow(distanceEndOneToMid , 2));

        return  perpendicularDistance;
    }

    public boolean isInsideCircle(double textCordinate_X, double textCordinate_Y, double center_X, double center_Y, double radius) {
        double circleBoundary = (Math.pow((textCordinate_X-center_X), 2) +
                Math.pow((textCordinate_Y-center_Y), 2))/ radius;
        if (circleBoundary <= 1) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isProbabilityMatch(Line line ,Text text){
        double end_X;
        double end_Y;
        double textPoint_X = text.getX();
        double textPoint_Y = text.getY();
        double lengthOfLine;
        double radius;
        double center_X;
        double center_Y;

        end_X = getEndOfLine_X(line);
        end_Y = getEndOfLine_X(line);

        lengthOfLine = findPointToPointDistance(end_X,end_Y,textPoint_X,textPoint_Y);
        radius = lengthOfLine / 3;
        center_X = end_X +radius;
        center_Y = end_Y;

        return isInsideCircle(textPoint_X, textPoint_Y, center_X, center_Y,radius);




    }

    public double findPointToPointDistance(double pointOne_X,double pointOne_Y,double pointTwo_X,double pointTwo_Y ){
        double distance = Math.sqrt(Math.pow(pointTwo_X - pointOne_X , 2)+ Math.pow(pointTwo_Y-pointOne_Y,2));
        return  distance;
    }

    public  double findProbabilityPointDistance(Line line, Text text){

        double textPoint_X = text.getX();
        double textPoint_Y = text.getY();
        double end_X = getEndOfLine_X(line);
        double end_Y = getEndOfLine_Y(line);

        return findPointToPointDistance(end_X,end_Y,textPoint_X,textPoint_Y);

    }

    public double getEndOfLine_X(Line line){
        double end_X;
        if(line.getX1() > line.getX2() ){
            end_X = line.getX1();

        }
        else {
            end_X = line.getX2();

        }
        return end_X;
    }

    public double getEndOfLine_Y(Line line){
        double end_Y;
        if(line.getX1() > line.getX2() ){
            end_Y =  line.getY1();
        }
        else {
            end_Y = line.getY2();
        }
        return end_Y;
    }
}
