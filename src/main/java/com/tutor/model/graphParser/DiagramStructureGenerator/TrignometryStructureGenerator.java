package com.tutor.model.graphParser.DiagramStructureGenerator;
import com.tutor.model.graphParser.DiagramStructure.Trignometry.FigureStructure;

import com.tutor.parser.model.graphParser.DiagramStructure.AbstractDiagramStructure;
import com.tutor.parser.model.graphParser.DiagramStructure.Trignometry.AbstractTrignometryStructure;
import com.tutor.parser.model.graphParser.DiagramStructure.Trignometry.LineConnection;
import com.tutor.parser.model.graphParser.DiagramStructure.Trignometry.LineStructure;
import com.tutor.parser.model.graphParser.DiagramStructureGenerator.DiagramStructureGenerator;
import com.tutor.parser.model.graphParser.GraphGrammarGenerator.graphGrammarObject.Graph;
import com.tutor.parser.model.graphicalPOJOObject.line.Line;
import com.tutor.parser.model.preProcessor.SpatialRelationShipGenerator;
import com.tutor.parser.model.util.SpatialRelation;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Vithusha on 8/16/2017.
 */
public class TrignometryStructureGenerator extends DiagramStructureGenerator {
    AbstractTrignometryStructure abstractTrignometryStructure = new AbstractTrignometryStructure();
    List<LineConnection> allConnections = abstractTrignometryStructure.getConnectionList();

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
            ArrayList<LineConnection> connectionList = generateConnectionList(connectionOne,connectionTwo);
            FigureStructure figure = new FigureStructure(connectionList);
            abstractTrignometryStructure.updateFigureList(figure);
        }

        if(ruleID == 2){

            LineConnection connection = (LineConnection) host.getGraphicalImageComponents().get(objects[0]);
            LineStructure line = new LineStructure((Line) host.getGraphicalImageComponents().get(objects[1]));
            ArrayList<LineConnection> connectionList = generateConnectionList(connection,line);
            FigureStructure figure = new FigureStructure(connectionList);
            abstractTrignometryStructure.updateFigureList(figure);
        }

        if(ruleID == 3){
            FigureStructure figure = (FigureStructure) host.getGraphicalImageComponents().get(objects[0]);
            FigureStructure oldFigure = figure;
            LineStructure line = new LineStructure((Line) host.getGraphicalImageComponents().get(objects[1]));
            figure.updateConnectionList(generateConnectionList(figure,line));
            abstractTrignometryStructure.changeFigureStructure(oldFigure, figure);

        }
        return abstractTrignometryStructure;
    }

    private ArrayList<LineConnection> generateConnectionList(LineConnection connectionOne, LineConnection connectionTwo) {
        ArrayList<LineConnection> connectionList = new ArrayList<>();
        SpatialRelationShipGenerator relation = new SpatialRelationShipGenerator();
        connectionList.add(connectionOne);
        connectionList.add(connectionTwo);
        Line connectionOneLineOne = connectionOne.getLineOne().getLine();
        Line connectionOneLineTwo = connectionOne.getLineTwo().getLine() ;
        Line connectionTwoLineOne = connectionTwo.getLineOne().getLine();
        Line connectionTwoLineTwo = connectionTwo.getLineTwo().getLine();
        if(relation.isAnyLineTouch(connectionOneLineOne,connectionTwoLineOne)||relation.isLineTouch(connectionOneLineOne,connectionTwoLineOne)||relation.isEndPointTouch(connectionOneLineOne,connectionTwoLineOne)){
            LineConnection connection_1 = new LineConnection(connectionOne.getLineOne(),connectionTwo.getLineOne());
            connectionList.add(connection_1);
            addToConnectionList(connection_1);
        }
        if(relation.isAnyLineTouch(connectionOneLineTwo,connectionTwoLineOne)||relation.isLineTouch(connectionOneLineTwo,connectionTwoLineOne)||relation.isEndPointTouch(connectionOneLineTwo,connectionTwoLineOne)){
            LineConnection connection_2 = new LineConnection(connectionOne.getLineTwo(),connectionTwo.getLineOne());
            connectionList.add(connection_2);
            addToConnectionList(connection_2);
        }
        if(relation.isAnyLineTouch(connectionOneLineOne,connectionTwoLineTwo)||relation.isLineTouch(connectionOneLineOne,connectionTwoLineTwo)||relation.isEndPointTouch(connectionOneLineOne,connectionTwoLineTwo)){
            LineConnection connection_3 = new LineConnection(connectionOne.getLineOne(),connectionTwo.getLineTwo());
            connectionList.add(connection_3);
            addToConnectionList(connection_3);
        }
        if(relation.isAnyLineTouch(connectionOneLineTwo,connectionTwoLineTwo)||relation.isLineTouch(connectionOneLineTwo,connectionTwoLineTwo)||relation.isEndPointTouch(connectionOneLineTwo,connectionTwoLineTwo)){
            LineConnection connection_4 = new LineConnection(connectionOne.getLineTwo(),connectionTwo.getLineTwo());
            connectionList.add(connection_4);
            addToConnectionList(connection_4);
        }

    return connectionList;
    }
    private ArrayList<LineConnection> generateConnectionList(LineConnection connectionOne, LineStructure lineStructure) {
        ArrayList<LineConnection> connectionList = new ArrayList<>();
        SpatialRelationShipGenerator relation = new SpatialRelationShipGenerator();
        connectionList.add(connectionOne);
        Line connectionOneLineOne = connectionOne.getLineOne().getLine();
        Line connectionOneLineTwo = connectionOne.getLineTwo().getLine() ;
        Line line = lineStructure.getLine();
        if(relation.isAnyLineTouch(connectionOneLineOne,line)||relation.isLineTouch(connectionOneLineOne,line)||relation.isEndPointTouch(connectionOneLineOne,line)){
            LineConnection connection_1 = new LineConnection(connectionOne.getLineOne(),lineStructure);
            connectionList.add(connection_1);
            addToConnectionList(connection_1);
        }
        if(relation.isAnyLineTouch(connectionOneLineTwo,line)||relation.isLineTouch(connectionOneLineTwo,line)||relation.isEndPointTouch(connectionOneLineTwo,line)){
            LineConnection connection_2 = new LineConnection(connectionOne.getLineTwo(),lineStructure);
            connectionList.add(connection_2);
            addToConnectionList(connection_2);
        }

        return connectionList;
    }

    private ArrayList<LineConnection> generateConnectionList(FigureStructure figureStructure, LineStructure lineStructure){
        ArrayList<LineConnection> connectionList = figureStructure.getConnectionList();
        Line line = lineStructure.getLine();
        SpatialRelationShipGenerator relation = null;
        for(LineConnection connection: connectionList){
            Line LineOne = connection.getLineOne().getLine();
            Line LineTwo = connection.getLineTwo().getLine() ;
            if(relation.isAnyLineTouch(LineOne,line)||relation.isLineTouch(LineOne,line)||relation.isEndPointTouch(LineOne,line)){
                LineConnection connection_1 = new LineConnection(connection.getLineOne(),lineStructure);
                if (!connectionList.contains(connection_1)) {

                    connectionList.add(connection_1);
                    addToConnectionList(connection_1);
                }
            }
            if(relation.isAnyLineTouch(LineTwo,line)||relation.isLineTouch(LineTwo,line)||relation.isEndPointTouch(LineTwo,line)){
                LineConnection connection_2 = new LineConnection(connection.getLineTwo(),lineStructure);
                if (!connectionList.contains(connection_2)) {
                    connectionList.add(connection_2);
                    addToConnectionList(connection_2);
                }
            }
        }
        return connectionList;
    }
    public void addToConnectionList( LineConnection connection){
        if (!allConnections.contains(connection))
        {
            abstractTrignometryStructure.updateConnectionList(connection);

        }
    }
}
