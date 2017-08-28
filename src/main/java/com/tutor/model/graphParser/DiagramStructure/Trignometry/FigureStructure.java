package com.tutor.model.graphParser.DiagramStructure.Trignometry;


import com.tutor.parser.model.graphParser.DiagramStructure.Trignometry.LineConnection;
import com.tutor.parser.model.graphParser.DiagramStructure.Trignometry.LineStructure;
import com.tutor.parser.model.graphicalPOJOObject.GraphicalImageComponent;
import com.tutor.parser.model.util.ObjectType;

/**
 * Created by Vithusha on 8/16/2017.
 */
public class FigureStructure extends GraphicalImageComponent {
    private  FigureStructure figureStructure;
    private LineConnection connectionOne;
    private LineConnection connectionTwo;
    private LineStructure line;


    public FigureStructure(LineConnection connectionOne,LineConnection connectionTwo){
        this.connectionOne = connectionOne;
        this.connectionTwo = connectionTwo;
        this.objectType = ObjectType.FIGURE;

    }
    public FigureStructure(FigureStructure figureStructure,LineStructure line){
        this.figureStructure = figureStructure;
        this.line = line;
    }
    public FigureStructure(LineConnection connectionOne,LineStructure line){
        this.connectionOne = connectionOne;
        this.line = line;
    }
    public LineConnection getConnectionOne() {
        return connectionOne;
    }

    public LineConnection getConnectionTwo() {
        return connectionTwo;
    }

    public LineStructure getLine() {
        return line;
    }


}

