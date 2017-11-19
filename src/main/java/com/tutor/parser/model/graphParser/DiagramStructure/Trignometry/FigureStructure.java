package com.tutor.parser.model.graphParser.DiagramStructure.Trignometry;


import com.tutor.parser.model.graphicalPOJOObject.GraphicalImageComponent;
import com.tutor.parser.model.graphicalPOJOObject.line.Line;
import com.tutor.parser.model.util.ObjectType;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Vithusha on 8/16/2017.
 */
public class FigureStructure extends GraphicalImageComponent {
    private FigureStructure figureStructure;
    private LineConnection connectionOne;
    private LineConnection connectionTwo;
    private LineConnection lineConnection;
    private LineStructure line;
    private ArrayList<LineConnection> connectionList;
    private int figureType;


    public FigureStructure(LineConnection connectionOne, LineConnection connectionTwo) {
        this.connectionOne = connectionOne;
        this.connectionTwo = connectionTwo;
        this.objectType = ObjectType.FIGURE;
        figureType = 1;

    }

    public FigureStructure(FigureStructure figureStructure, LineStructure line) {
        this.figureStructure = figureStructure;
        this.line = line;
        this.objectType = ObjectType.FIGURE;
        figureType = 2;
    }

    public FigureStructure(LineConnection lineConnection, LineStructure line) {
        this.connectionOne = lineConnection;
        this.line = line;
        this.objectType = ObjectType.FIGURE;
        figureType = 3;
    }

    public FigureStructure(FigureStructure figureStructure, LineConnection lineConnection) {
        this.connectionOne = lineConnection;
        this.figureStructure = figureStructure;
        this.objectType = ObjectType.FIGURE;
        figureType = 4;
    }

    public LineConnection getConnectionOne() {
        return connectionOne;
    }

    public LineConnection getConnectionTwo() {
        return connectionTwo;
    }

    public LineConnection getLineConnection() {
        return lineConnection;
    }

    public FigureStructure getFigure() {
        return figureStructure;
    }

    public int getFigureType() {
        return figureType;
    }

    public LineStructure getLine() {
        return line;
    }

    public FigureStructure(ArrayList<LineConnection> connections) {
        this.connectionList = connections;
        this.objectType = ObjectType.FIGURE;

    }

    public void updateConnectionList(ArrayList<LineConnection> connections) {

            /*for(LineConnection connection : connections) {
                if (connectionList != null) {
                    connectionList.add(connection);



            }*/

        if (connectionList == null) {
            connectionList = connections;
        } else {
            Iterator<LineConnection> iter = connections.iterator();

            while (iter.hasNext()) {
                LineConnection connection = iter.next();

                if (connection != null) {

                    connectionList.add(connection);

                }
            }

        }
    }


        public ArrayList<LineConnection> getConnectionList () {
            return connectionList;
        }

}

