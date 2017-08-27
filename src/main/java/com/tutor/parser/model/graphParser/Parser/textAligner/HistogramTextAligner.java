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


    int pos;

    public AbstractHistogramStructure alignTextToHistogram(AbstractHistogramStructure abstractHistogramStructure, List<GraphicalImageComponent> textList) {
        this.abstractHistogramStructure = abstractHistogramStructure;
        this.textList=textList;
        for(int i=0;i<textList.size();i++){
            staticX=textList.get(2).getX();
            if(!staticX.equals(textList.get(i).getX())){
                pos=i;
                staticY=textList.get(i).getX();
                break;
            }

        }
        y1=textList.get(2).getY();
        y2=textList.get(3).getY();
        Text text=(Text)textList.get(2);
        valY1=Double.parseDouble(text.getText());
        text=(Text)textList.get(3);
        valY2=Double.parseDouble(text.getText());
        yAxisRatio=Math.abs(valY1-valY2)/Math.abs(y1-y2);

        x1=textList.get(pos+2).getX();
        x2=textList.get(pos+3).getX();
        text=(Text)textList.get(pos+2);
        valX1=Double.parseDouble(text.getText());
        text=(Text)textList.get(pos+3);
        valX2=Double.parseDouble(text.getText());
        xAxisRatio=Math.abs(valX1-valX2)/Math.abs(x1-x2);
        abstractHistogramStructure.setxAxisratio(xAxisRatio);
        abstractHistogramStructure.setyAxisratio(yAxisRatio);
        int top =0;
        int bottom=0;
        Double topVal=textList.get(0).getY();
        Double bottomVal=textList.get(0).getY();

        for(int i=0;i<textList.size();i++){
            if(textList.get(i).getY()<topVal){
                topVal=textList.get(i).getY();
                top=i;
            }else if(textList.get(i).getY()>bottomVal){
                bottomVal=textList.get(i).getY();
                bottom=i;
            }
        }
        Text textTop=(Text)textList.get(top);
        Text textBottom=(Text)textList.get(bottom);
        abstractHistogramStructure.setyLegend(textTop.getText());
        abstractHistogramStructure.setxLegend(textBottom.getText());


        return abstractHistogramStructure;
    }
}
