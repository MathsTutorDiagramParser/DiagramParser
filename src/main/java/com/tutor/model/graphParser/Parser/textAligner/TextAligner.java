package com.tutor.model.graphParser.Parser.textAligner;

/**
 * Created by Madhavi Ruwandika on 8/7/2017.
 */
public class TextAligner {

    public boolean isInside(double textCordinate_X, double textCordinate_Y, double tickCordinate_X , double tickCordinate_Y, double x_radius, double y_radius){
        double ellipseBoundary = (Math.pow((textCordinate_X - tickCordinate_X),2)/ Math.pow(x_radius,2)) +
                (Math.pow((textCordinate_Y - tickCordinate_Y),2)/ Math.pow(y_radius,2));
        if (ellipseBoundary <= 1){
            return true;
        }
        else {return false;}
    }
}
