package com.tutor.model.graphParser.DiagramStructure;

import com.tutor.model.graphicalPOJOObject.Text.Text;

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

    @Override
    public void updateAbstractRepresentation() {

    }

}
