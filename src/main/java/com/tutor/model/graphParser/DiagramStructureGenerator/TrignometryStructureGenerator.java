package com.tutor.model.graphParser.DiagramStructureGenerator;


import com.tutor.model.graphParser.DiagramStructure.Trignometry.FigureStructure;

import com.tutor.parser.model.graphParser.DiagramStructure.AbstractDiagramStructure;
import com.tutor.parser.model.graphParser.DiagramStructure.Trignometry.AbstractTrignometryStructure;
import com.tutor.parser.model.graphParser.DiagramStructure.Trignometry.LineConnection;
import com.tutor.parser.model.graphParser.DiagramStructure.Trignometry.LineStructure;
import com.tutor.parser.model.graphParser.DiagramStructureGenerator.DiagramStructureGenerator;
import com.tutor.parser.model.graphParser.GraphGrammarGenerator.graphGrammarObject.Graph;
import com.tutor.parser.model.graphicalPOJOObject.line.Line;


/**
 * Created by Vithusha on 8/16/2017.
 */
public class TrignometryStructureGenerator extends DiagramStructureGenerator {
    public TrignometryStructureGenerator(){

    }
    @Override
    public AbstractDiagramStructure generate(int ruleID, Graph host, AbstractDiagramStructure abstractDiagramStructure, int[] objects) {
        AbstractTrignometryStructure abstractTrignometryStructure = (AbstractTrignometryStructure) abstractDiagramStructure;
        if(ruleID==0){

            LineStructure lineOne = new LineStructure((Line) host.getGraphicalImageComponents().get(objects[0]));
            abstractTrignometryStructure.updateLineList(lineOne);
            LineStructure lineTwo = new LineStructure((Line) host.getGraphicalImageComponents().get(objects[1]));
            abstractTrignometryStructure.updateLineList(lineTwo);

            LineConnection connection = new LineConnection(lineOne,lineTwo);
            abstractTrignometryStructure.updateConnectionList(connection);

        }

        if(ruleID == 1){
            LineConnection connectionOne = (LineConnection) host.getGraphicalImageComponents().get(objects[0]);
            LineConnection connectionTwo = (LineConnection) host.getGraphicalImageComponents().get(objects[1]);
            FigureStructure figure = new FigureStructure(connectionOne , connectionTwo);
            abstractTrignometryStructure.updateFigureList(figure);
        }

        if(ruleID == 2){

            LineConnection connection = (LineConnection) host.getGraphicalImageComponents().get(objects[0]);
            LineStructure line = new LineStructure((Line) host.getGraphicalImageComponents().get(objects[1]));
            FigureStructure figure = new FigureStructure(connection , line);
            abstractTrignometryStructure.updateFigureList(figure);
        }

        if(ruleID == 3){
            FigureStructure figure = (FigureStructure) host.getGraphicalImageComponents().get(objects[0]);
            LineStructure line = new LineStructure((Line) host.getGraphicalImageComponents().get(objects[1]));
            FigureStructure newFigure = new FigureStructure(figure , line);
            abstractTrignometryStructure.updateFigureList(newFigure);
        }
        return abstractTrignometryStructure;
    }
}
