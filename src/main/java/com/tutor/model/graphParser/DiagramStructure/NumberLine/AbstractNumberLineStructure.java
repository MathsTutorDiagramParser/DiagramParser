package com.tutor.model.graphParser.DiagramStructure.NumberLine;

import com.tutor.model.graphParser.DiagramStructure.AbstractDiagramStructure;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Madhavi Ruwandika on 8/4/2017.
 */
public class AbstractNumberLineStructure extends AbstractDiagramStructure {

    List<TickPoint> tickPointList;
    List<MarkPoint> markPointList;

    public AbstractNumberLineStructure() {
        tickPointList = new ArrayList<>();
        markPointList = new ArrayList<>();
    }

    public List<TickPoint> getTickPointList() {
        return this.tickPointList;
    }

    public void setTickPointList(List<TickPoint> tickPointList) {
        this.tickPointList = tickPointList;
    }

    public List<MarkPoint> getMarkPointList() {
        return this.markPointList;
    }

    public void setMarkPointList(List<MarkPoint> markPointList) {
        this.markPointList = markPointList;
    }

    public void updateMarkPointList(MarkPoint markPoint) {
        if(markPoint.getCircle().getFilled_colour().equals("rgb(0,0,255)")){
            markPoint.isFilled =true;
        }
        markPointList.add(markPoint);
    }

    public void updatetickPointList(TickPoint tickPoint) {
        tickPointList.add(tickPoint);
    }



    @Override
    public void updateAbstractRepresentation() {

    }

}
