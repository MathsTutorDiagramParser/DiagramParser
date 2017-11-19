package com.tutor.parser.model.graphParser.Parser.textAligner;
import com.tutor.parser.model.graphParser.DiagramStructure.Histogram.AbstractHistogramStructure;
import com.tutor.parser.model.graphParser.DiagramStructure.Histogram.Bar;
import com.tutor.parser.model.graphicalPOJOObject.GraphicalImageComponent;
import com.tutor.parser.model.graphicalPOJOObject.Text.Text;

import java.util.List;

/**
 * Created by Vithusha on 8/5/2017.
 */
public class HistogramTextAligner {
    AbstractHistogramStructure abstractHistogramStructure;
    List<Bar> barList;
    List<GraphicalImageComponent> textList;
    Double xAxisRatio;
    Double yAxisRatio;
    Double staticY;
    Double staticX;
    Double x1;
    Double x2;
    Double valX1;
    Double valX2;
    Double y1;
    Double y2;
    Double valY1;
    Double valY2;
    Double scaleYValue;
    Double scaleXValue;
    String xLegend;
    int yIndexMin;
    int yIndexMax;
    Double yMin;
    Double yMax;
    String yLegend;


    int pos;

    public AbstractHistogramStructure alignTextToHistogram(AbstractHistogramStructure abstractHistogramStructure, List<GraphicalImageComponent> textList) {
        this.abstractHistogramStructure = abstractHistogramStructure;
        this.textList=textList;
        pos=4;
        for(int i=4;i<textList.size();i++){
            staticX=textList.get(2).getX();
            if(!staticX.equals(textList.get(i).getX())){
                pos=i;
                staticY=textList.get(i).getX();
                break;
            }

        }
        yMin=textList.get(0).getY();
        yIndexMin=0;
        yMax=textList.get(0).getY();
        yIndexMax=0;
        for(int i=0;i<textList.size();i++){
            if(yMin>textList.get(i).getY()){
                yMin=textList.get(i).getY();
                yIndexMin=i;
            }

            if(yMax<textList.get(i).getY()){
                yMax=textList.get(i).getY();
                yIndexMax=i;
            }
        }
        Text textMi=(Text)textList.get(yIndexMin);
        Text textMa=(Text)textList.get(yIndexMax);
        System.out.println("Y min label is "+ textMi.getText());
        System.out.println("Y max label is "+ textMa.getText());
        y1=textList.get(2).getY();
        y2=textList.get(3).getY();
        Text text=(Text)textList.get(2);
        valY1=Double.parseDouble(text.getText());
        text=(Text)textList.get(3);
        valY2=Double.parseDouble(text.getText());
        scaleYValue=Math.abs(valY1-valY2);
        yAxisRatio=Math.abs(valY1-valY2)/Math.abs(y1-y2);

        x1=textList.get(pos+2).getX();
        x2=textList.get(pos+3).getX();
        text=(Text)textList.get(pos+2);
        valX1=Double.parseDouble(text.getText());
        text=(Text)textList.get(pos+3);
        valX2=Double.parseDouble(text.getText());
        xAxisRatio=Math.abs(valX1-valX2)/Math.abs(x1-x2);
        scaleXValue=Math.abs(valX1-valX2);
        abstractHistogramStructure.setxAxisratio(xAxisRatio);
        abstractHistogramStructure.setyAxisratio(yAxisRatio);

        // Set the axis legends here
        abstractHistogramStructure.setxLegend(textMa.getText());
        abstractHistogramStructure.setyLegend(textMi.getText());
        abstractHistogramStructure.setScaleYValue(scaleYValue);
        abstractHistogramStructure.setScaleXValue(scaleXValue);



        return abstractHistogramStructure;
    }
}
