package com.tutor.model.graphParser.DiagramStructure;

import com.tutor.model.graphicalPOJOObject.Text.Text;

import java.util.List;

/**
 * Created by Madhavi Ruwandika on 8/4/2017.
 */
public class AbstractNumberLineStructure extends AbstractDiagramStructure {

    private List<TickPoint> tickPointList;
    private List<MarkPoint> markPointList;

    public AbstractNumberLineStructure(List<Text> textList) {
        super(textList);
    }

    public List<TickPoint> getTickPointList() {
        return tickPointList;
    }

    public void setTickPointList(List<TickPoint> tickPointList) {
        this.tickPointList = tickPointList;
    }

    public List<MarkPoint> getMarkPointList() {
        return markPointList;
    }

    public void setMarkPointList(List<MarkPoint> markPointList) {
        this.markPointList = markPointList;
    }


}
