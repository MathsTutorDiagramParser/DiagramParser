package com.tutor.model.graphParser.DiagramStructureGenerator;

import com.tutor.model.graphParser.DiagramStructure.AbstractDiagramStructure;
import com.tutor.model.graphParser.DiagramStructure.Histogram.AbstractHistogramStructure;
import com.tutor.model.graphParser.DiagramStructure.Histogram.Bar;
import com.tutor.model.graphParser.DiagramStructure.NumberLine.AbstractNumberLineStructure;
import com.tutor.model.graphParser.DiagramStructure.NumberLine.MarkPoint;
import com.tutor.model.graphParser.DiagramStructure.NumberLine.TickPoint;
import com.tutor.model.graphParser.GraphGrammarGenerator.graphGrammarObject.Graph;
import com.tutor.model.graphParser.Parser.StructuralParser;
import com.tutor.model.graphicalPOJOObject.Circle.Circle;
import com.tutor.model.graphicalPOJOObject.Rectangle.Rectangle;
import com.tutor.model.graphicalPOJOObject.line.VerticalLine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Madhavi Ruwandika on 8/5/2017.
 */
public class HistogramStructureGenerator extends DiagramStructureGenerator{
    private Logger logger = LoggerFactory.getLogger(StructuralParser.class);
    @Override
    public AbstractDiagramStructure generate(int ruleID, Graph host, AbstractDiagramStructure abstractDiagramStructure, int[] objects) {

        AbstractHistogramStructure diagramStructure = (AbstractHistogramStructure)abstractDiagramStructure;
        if (ruleID == 4){
            Bar bar = new Bar((Rectangle) host.getGraphicalImageComponents().get(objects[1]));
            logger.info("Found Bar : height: "+bar.rectangle.getHeight()+" width: "+bar.rectangle.getWidth());
            diagramStructure.updateBarList(bar);

        }else if(ruleID == 5){
            Bar bar = new Bar((Rectangle) host.getGraphicalImageComponents().get(objects[1]));
            logger.info("Found Bar : height: "+bar.rectangle.getHeight()+" width: "+bar.rectangle.getWidth());
            diagramStructure.updateBarList(bar);
        }
        return diagramStructure;
    }
    }

