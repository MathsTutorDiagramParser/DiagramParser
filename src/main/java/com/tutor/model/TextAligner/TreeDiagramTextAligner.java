package com.tutor.model.TextAligner;

import com.tutor.model.graphParser.DiagramStructure.AbstractDiagramStructure;
import com.tutor.model.graphParser.DiagramStructure.NumberLine.AbstractNumberLineStructure;
import com.tutor.model.graphParser.DiagramStructure.TreeDiagram.AbstractTreeDiagramStructure;
import com.tutor.model.graphicalPOJOObject.Text.Text;
import com.tutor.model.graphicalPOJOObject.line.Line;

import java.util.List;

/**
 * Created by Vithusha on 8/5/2017.
 */
public class TreeDiagramTextAligner extends TextAligner{


    public AbstractTreeDiagramStructure abstractDiagramStructure;
    public AbstractDiagramStructure alignTextToTreeDiagram(AbstractDiagramStructure abstractDiagramStructure, List<Text> texts){
        this.abstractDiagramStructure = (AbstractTreeDiagramStructure)abstractDiagramStructure;



        return abstractDiagramStructure;
    }


    public Text matchDescriptionText(Line line){



        Text text = null;


        return text;
    }

    public Text matchProbabilityText(Line line){

        Text text = null;

        return text;
    }

    public boolean minimalComplexitySolution(){


        return false;
    }

    public boolean isInside(double textCordinate_X, double textCordinate_Y, double tickCordinate_X , double tickCordinate_Y, double x_radius, double y_radius){
        double ellipseBoundary = (Math.pow((textCordinate_X - tickCordinate_X),2)/ Math.pow(x_radius,2)) +
                (Math.pow((textCordinate_Y - tickCordinate_Y),2)/ Math.pow(y_radius,2));
        if (ellipseBoundary <= 1){
            return true;
        }
        else {return false;}
    }


}
