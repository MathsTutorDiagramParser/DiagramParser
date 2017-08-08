package com.tutor.model.TextAligner;

import com.tutor.model.graphParser.DiagramStructure.AbstractDiagramStructure;

import com.tutor.model.graphParser.DiagramStructure.Histogram.AbstractHistogramStructure;
import com.tutor.model.graphParser.DiagramStructure.NumberLine.AbstractNumberLineStructure;
import com.tutor.model.graphicalPOJOObject.GraphicalImageComponent;
import com.tutor.model.graphParser.DiagramStructure.AbstractHistogramStructure;
import com.tutor.model.graphParser.DiagramStructure.AbstractNumberLineStructure;
import com.tutor.model.graphParser.DiagramStructure.Trignometry.AbstractTrignometryStructure;
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

            case TRIGNOMETRICDIAGRAM:
                TrignometryTextAligner trignometryTextAligner=new TrignometryTextAligner();
                return trignometryTextAligner.alignTextToTrignometry((AbstractTrignometryStructure) abstractDiagramStructure,textList);

            default:
                return null;
        }

    }
}
