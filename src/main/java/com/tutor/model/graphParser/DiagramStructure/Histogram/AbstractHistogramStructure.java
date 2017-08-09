package com.tutor.model.graphParser.DiagramStructure.Histogram;

import com.tutor.model.graphParser.DiagramStructure.AbstractDiagramStructure;
import com.tutor.model.graphParser.DiagramStructure.NumberLine.TickPoint;
import com.tutor.model.graphicalPOJOObject.GraphicalImageComponent;
import com.tutor.model.graphicalPOJOObject.Text.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Madhavi Ruwandika on 8/4/2017.
 */
public class AbstractHistogramStructure extends AbstractDiagramStructure {
    List<BarList> barList;
    public String xLegend;
    public String yLegend;
    public AbstractHistogramStructure() {
        barList=new ArrayList<>();
        xLegend="";
        yLegend="";
    }
    public void updateBarList(BarList barItem){
        barList.add(barItem);
    }

    public String getxLegend() {
        return xLegend;
    }

    public void setxLegend(String xLegend) {
        this.xLegend = xLegend;
    }

    public String getyLegend() {
        return yLegend;
    }

    public void setyLegend(String yLegend) {
        this.yLegend = yLegend;
    }

    @Override
    public void updateAbstractRepresentation(GraphicalImageComponent graphicalImageComponent) {

    }

}
