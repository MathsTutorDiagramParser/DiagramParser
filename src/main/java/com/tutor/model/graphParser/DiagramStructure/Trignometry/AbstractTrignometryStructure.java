package com.tutor.model.graphParser.DiagramStructure.Trignometry;

import com.tutor.model.graphParser.DiagramStructure.AbstractDiagramStructure;
import com.tutor.model.graphParser.DiagramStructure.MarkPoint;
import com.tutor.model.graphParser.DiagramStructure.TickPoint;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vithusha on 8/6/2017.
 */
public class AbstractTrignometryStructure extends AbstractDiagramStructure {

    List<LineConnection> connectionList;
    List<LineStructure> lineList;

    public AbstractTrignometryStructure() {
        connectionList = new ArrayList<>();
        lineList = new ArrayList<>();
    }

    public void setConnectionList(List<LineConnection> connectionList) {
        this.connectionList = connectionList;
    }

    public List<LineConnection> getConnectionList() {
        return connectionList;
    }

    public void setLineList(List<LineStructure> lineList) {
        this.lineList = lineList;
    }

    public List<LineStructure> getLineList() {
        return lineList;
    }

    @Override
    public void updateAbstractRepresentation() {

    }


}
