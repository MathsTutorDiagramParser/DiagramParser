package com.tutor.model.graphParser.DiagramStructure.Histogram;

import com.tutor.model.graphParser.DiagramStructure.AbstractDiagramStructure;
import com.tutor.model.graphicalPOJOObject.GraphicalImageComponent;


import java.util.ArrayList;
import java.util.List;
import com.tutor.model.graphicalPOJOObject.Rectangle.Rectangle;
import com.tutor.model.util.ObjectType;

/**
 * Created by Madhavi Ruwandika on 8/4/2017.
 */
public class AbstractHistogramStructure extends AbstractDiagramStructure {
    List<Bar> bar;
    List<Bar> abstractBar;
    public String xLegend;
    public String yLegend;
    public Double xAxisratio;
    public Double yAxisratio;

    public AbstractHistogramStructure() {
        bar =new ArrayList<>();
        xLegend=null;
        yLegend=null;
    }
    public void updateBarList(Bar barItem){
        bar.add(barItem);
    }

    public List<Bar> getAbstractBar() {
        return abstractBar;
    }

    public List<Bar> getBar() {
        return bar;
    }

    public Double getxAxisratio() {
        return xAxisratio;
    }

    public Double getyAxisratio() {
        return yAxisratio;
    }

    public void setxAxisratio(Double xAxisratio) {
        this.xAxisratio = xAxisratio;
    }

    public void setyAxisratio(Double yAxisratio) {
        this.yAxisratio = yAxisratio;
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
    public void updateAbstractRepresentation(GraphicalImageComponent obj) {
        if(obj.objectType== ObjectType.RECTANGLE){
            Bar bar1 = new Bar((Rectangle) obj);
            abstractBar.add(bar1);
        }

    }

}
