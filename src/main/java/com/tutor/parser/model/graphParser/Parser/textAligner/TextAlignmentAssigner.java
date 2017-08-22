package com.tutor.parser.model.graphParser.Parser.textAligner;

import com.tutor.parser.model.graphParser.DiagramStructure.AbstractDiagramStructure;

import com.tutor.parser.model.graphParser.DiagramStructure.Histogram.AbstractHistogramStructure;
import com.tutor.parser.model.graphParser.DiagramStructure.NumberLine.AbstractNumberLineStructure;
import com.tutor.parser.model.graphParser.DiagramStructure.TreeDiagram.AbstractTreeDiagramStructure;
import com.tutor.parser.model.graphicalPOJOObject.GraphicalImageComponent;
import com.tutor.parser.model.graphParser.DiagramStructure.Trignometry.AbstractTrignometryStructure;
import com.tutor.parser.model.util.DiagramType;

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

            case TREEDIAGRAM:
                TreeDiagramTextAligner treeDiagramTextAligner = new TreeDiagramTextAligner();
                return treeDiagramTextAligner.alignTextToTreeDiagram((AbstractTreeDiagramStructure) abstractDiagramStructure, textList);

                default:
                return null;
        }

    }
}
