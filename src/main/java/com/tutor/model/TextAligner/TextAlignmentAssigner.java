package com.tutor.model.TextAligner;

import com.tutor.model.graphParser.DiagramStructure.AbstractDiagramStructure;

import com.tutor.model.graphParser.DiagramStructure.Histogram.AbstractHistogramStructure;
import com.tutor.model.graphParser.DiagramStructure.NumberLine.AbstractNumberLineStructure;
import com.tutor.model.graphicalPOJOObject.GraphicalImageComponent;
import com.tutor.model.graphicalPOJOObject.Text.Text;
import com.tutor.model.util.DiagramType;

import java.util.List;
/**
 * Created by Vithusha on 8/5/2017.
 */
public class TextAlignmentAssigner {

    AbstractDiagramStructure abstractDiagramStructure;
    List<GraphicalImageComponent> textList;

    public AbstractDiagramStructure assignTextAligner(AbstractDiagramStructure abstractDiagramStructure, DiagramType diagramType, List<GraphicalImageComponent> textList ){
        this.abstractDiagramStructure = abstractDiagramStructure;
        this.textList = textList;
        switch (diagramType){

            case NUMBRELINE:
                NumberLineTextAligner numberLineTextAligner = new NumberLineTextAligner();
                return numberLineTextAligner.alignTextToNumberLine((AbstractNumberLineStructure) abstractDiagramStructure,textList);

            case HISTOGRAM:
                HistogramTextAligner histogramTextAligner = new HistogramTextAligner();
                return histogramTextAligner.alignTextToHistogram((AbstractHistogramStructure) abstractDiagramStructure,textList);

            default:
                return null;
        }

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
