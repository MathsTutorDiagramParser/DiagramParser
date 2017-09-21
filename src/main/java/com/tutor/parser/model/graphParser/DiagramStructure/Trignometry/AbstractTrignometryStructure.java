package com.tutor.parser.model.graphParser.DiagramStructure.Trignometry;

import com.tutor.parser.model.graphParser.DiagramStructure.AbstractDiagramStructure;
import com.tutor.parser.model.graphicalPOJOObject.GraphicalImageComponent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vithusha on 8/6/2017.
 */
public class AbstractTrignometryStructure extends AbstractDiagramStructure {

    List<LineConnection> connectionList;
    List<LineStructure> lineList;
    List<FigureStructure> figureList;

    public AbstractTrignometryStructure() {
        connectionList = new ArrayList<>();
        lineList = new ArrayList<>();
        figureList = new ArrayList<>();
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
    public void updateAbstractRepresentation(GraphicalImageComponent obj) {

    }

    public void updateLineList(LineStructure line){
        lineList.add(line);
    }
    public void updateConnectionList(LineConnection connection){
        connectionList.add(connection);
    }

    public void updateFigureList(FigureStructure figure){

        figureList.add(figure);
    }
    public  void changeFigureStructure(FigureStructure oldFigure,FigureStructure newFigure){
        figureList.remove(oldFigure);
        figureList.add(newFigure);
    }

}
