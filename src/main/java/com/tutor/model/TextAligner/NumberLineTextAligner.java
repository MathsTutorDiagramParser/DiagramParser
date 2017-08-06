package com.tutor.model.TextAligner;

import com.tutor.model.graphParser.DiagramStructure.AbstractNumberLineStructure;
import com.tutor.model.graphParser.DiagramStructure.TickPoint;
import com.tutor.model.graphicalPOJOObject.Text.Text;

import java.util.List;

/**
 * Created by Vithusha on 8/5/2017.
 */
public class NumberLineTextAligner {

    AbstractNumberLineStructure abstractNumberLineStructure;
    List<TickPoint> tickPointList;
    List<Text> textList;
    double stepDistance;
    int textCount = 0;
    int tickCount = 0;

    public AbstractNumberLineStructure alignTextToNumberLine(AbstractNumberLineStructure abstractNumberLineStructure,List<Text> textList) {
        this.abstractNumberLineStructure = abstractNumberLineStructure;
        tickPointList = abstractNumberLineStructure.getTickPointList();
        this.textList = textList;


        stepDistance = (tickPointList.get(1).getVerticalLine().getX1() - tickPointList.get(0).getVerticalLine().getX1());
        //for (TickPoint tick : tickPointList) {
        while(tickCount < tickPointList.size()){
            TickPoint tick = tickPointList.get(tickCount);
            Text matchedText = matchText(tick);
            tick.setText(matchedText);

        }

        return  abstractNumberLineStructure;
    }

    public Text matchText(TickPoint tick){
        double tickCordinate = tick.getVerticalLine().getX1();
        Text text = textList.get(tickCount);
        double tickCordinate_X = tick.getVerticalLine().getX1();
        double tickCordinate_Y = Math.max(tick.getVerticalLine().getY1(), tick.getVerticalLine().getY1());
        double textCordinate_X = text.getX();
        double textCordinate_Y = text.getY();
        double x_radius = 0.5 * stepDistance;
        double y_radius = stepDistance;
        Text matchedText = null;

        boolean isInside = isInside(textCordinate_X,textCordinate_Y,tickCordinate_X,tickCordinate_Y,x_radius,y_radius);
        if(isInside){
            textCount++;
            tickCount++;
            matchedText = text;
        }
        else if(textCordinate_X < tickCordinate_X){
            textCount++;

        }
        else if(textCordinate_X > tickCordinate_X){
            tickCount++;

        }
        return matchedText;
    }

    public boolean isInside(double textCordinate_X, double textCordinate_Y, double tickCordinate_X , double tickCordinate_Y, double x_radius, double y_radius){
        double ellipseBoundary = (Math.pow((textCordinate_X - tickCordinate_X),2)/ Math.pow(x_radius,2)) +
                (Math.pow((textCordinate_Y - tickCordinate_Y),2)/ Math.pow(y_radius,2));
        if (ellipseBoundary <= 1){
            return true;
        }
        else {return false;}
    }

}
