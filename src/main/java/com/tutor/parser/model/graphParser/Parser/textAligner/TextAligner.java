package com.tutor.parser.model.graphParser.Parser.textAligner;

import com.tutor.parser.model.graphicalPOJOObject.Text.Text;
import com.tutor.parser.model.graphicalPOJOObject.line.Line;

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

        double a = (endTwo_X - endOne_X);
        double b = (endTwo_Y - endOne_Y);

        double radius =  Math.sqrt(Math.pow(a,2)+Math.pow(b,2))*3/4;
        double center_X = (endOne_X + endTwo_X) / 2;
        double center_Y = (endOne_Y + endTwo_Y) / 2;

        double closeCircle = (Math.pow((label_X-center_X), 2) +
                Math.pow((label_Y-center_Y), 2))/Math.pow(radius,2);

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
                Math.pow((textCordinate_Y-center_Y), 2))/ Math.pow(radius,2);
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
        end_Y = getEndOfLine_Y(line);

        lengthOfLine = findPointToPointDistance(end_X,end_Y,textPoint_X,textPoint_Y);
        radius = lengthOfLine;
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


    public void insideSectorClosed(Line line1,Line line2, int x, int y){

        boolean sameEnd = false;
        boolean is_inside_Connection=false;
        boolean is_outside_Connection=false;
        double center_x = 0;
        double center_y = 0;
        double radious = 0;
        boolean l1_higher = false ;
        boolean l2_higher =false;


        if((line1.getX1()==line2.getX1())&& line1.getY1()==line2.getY1()){
            sameEnd = true;
            center_x = line1.getX1();
            center_y = line2.getY1();
            radious = Math.sqrt(Math.pow((line1.getX1()-line1.getX2()),2)+Math.pow((line1.getY1()-line1.getY2()),2));

        }else if((line1.getX2()==line2.getX2())&& (line1.getY2()==line2.getY2())){
            sameEnd = true;
            center_x = line1.getX1();
            center_y = line2.getY1();
            radious = Math.sqrt(Math.pow((line1.getX1()-line1.getX2()),2)+Math.pow((line1.getY1()-line1.getY2()),2));
        }
        // assume line one is the left aligned of first found line in ordering
        // inside_connection - within small sector
        // outside_connection - within large sector
        if(sameEnd){
            if(isInsideCircle(x,y,center_x,center_y,radious)){

            }
        }

    }




}
