package com.tutor.model.graphParser.Parser.textAligner;

import com.tutor.model.graphParser.DiagramStructure.NumberLine.AbstractNumberLineStructure;
import com.tutor.model.graphParser.DiagramStructure.NumberLine.MarkPoint;
import com.tutor.model.graphParser.DiagramStructure.NumberLine.TickPoint;
import com.tutor.model.graphicalPOJOObject.GraphicalImageComponent;
import com.tutor.model.graphicalPOJOObject.Text.Text;

import java.util.List;

/**
 * Created by Vithusha on 8/5/2017.
 */
public class NumberLineTextAligner {

    AbstractNumberLineStructure abstractNumberLineStructure;
    List<TickPoint> tickPointList;
    List<MarkPoint> markPointList;
    List<MarkPoint> extraPointList;
    List<GraphicalImageComponent> textList;
    double stepDistance;
    int textCount = 0;
    int tickCount = 0;
    int markPointCount = 0;
    int extraCount = 0;

    public AbstractNumberLineStructure alignTextToNumberLine(AbstractNumberLineStructure abstractNumberLineStructure,List<GraphicalImageComponent> textList) {

        this.abstractNumberLineStructure = abstractNumberLineStructure;
        tickPointList = abstractNumberLineStructure.getTickPointList();
        markPointList = abstractNumberLineStructure.getMarkPointList();
        extraPointList = abstractNumberLineStructure.getExtraPointList();

        this.textList = textList;
        stepDistance = (tickPointList.get(1).getVerticalLine().getX1() - tickPointList.get(0).getVerticalLine().getX1());
        //for (TickPoint tick : tickPointList) {
        while(tickCount < tickPointList.size()){
            TickPoint tick = tickPointList.get(tickCount);
            Text matchedText = matchText(tick);
            tick.setText(matchedText);

        }

        textCount = 0;
        //for (TickPoint tick : tickPointList) {
        while (markPointCount<markPointList.size()){
            MarkPoint markPoint = markPointList.get(markPointCount);
            Text matchedText = matchText(markPoint);
            markPoint.setText(matchedText);
        }

        markPointCount=0;
        if(extraPointList!=null){
            while (markPointCount < extraPointList.size()){
                MarkPoint markPoint = extraPointList.get(markPointCount);
                Text matchedText = matchText(markPoint);
                markPoint.setText(matchedText);
            }
        }

        return  abstractNumberLineStructure;
    }

    public Text matchText(TickPoint tick){
        double tickCordinate = tick.getVerticalLine().getX1();
        Text text = (Text) textList.get(textCount);
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

    public Text matchText(MarkPoint point){

        Text matchedText = null;
        if(point.isInfinity() && point.endOFTheThickLine=="LEFT"){
            matchedText = new Text(Double.NEGATIVE_INFINITY,point.circle.getY(),"NEGETIVE_INFINITY");
            markPointCount++;
        }else if (point.isInfinity() && point.endOFTheThickLine=="RIGHT") {
            matchedText = new Text(Double.POSITIVE_INFINITY,point.circle.getY(),"POSITIVE_INFINITY");
            markPointCount++;
        }else {

            double point_X = point.circle.getX();
            double point_Y = point.getCircle().getY();
            double textCordinate_X = textList.get(textCount).getX();
            double textCordinate_Y = textList.get(textCount).getY();
            double x_radius = 0.5 * stepDistance;
            double y_radius = stepDistance;

            boolean isInside = isInside(textCordinate_X,textCordinate_Y,point_X,point_Y,x_radius,y_radius);
            if(isInside){
                matchedText = (Text) textList.get(textCount);
                textCount++;
                markPointCount++;
            }
            else if(textCordinate_X < point_X){
                textCount++;

            }
            else if(textCordinate_Y > point_Y){
                markPointCount++;

            }
            return matchedText;
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
