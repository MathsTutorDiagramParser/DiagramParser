package com.tutor.model.graphParser.DiagramStructureGenerator;

import com.tutor.model.graphParser.DiagramStructure.AbstractDiagramStructure;
import com.tutor.model.graphParser.DiagramStructure.NumberLine.AbstractNumberLineStructure;
import com.tutor.model.graphParser.DiagramStructure.NumberLine.MarkPoint;
import com.tutor.model.graphParser.DiagramStructure.NumberLine.TickPoint;
import com.tutor.model.graphParser.GraphGrammarGenerator.graphGrammarObject.Graph;
import com.tutor.model.graphicalPOJOObject.Circle.Circle;
import com.tutor.model.graphicalPOJOObject.line.VerticalLine;
import com.tutor.model.util.ObjectType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Madhavi Ruwandika on 8/5/2017.
 */
public class NumberLineStructureGenerator extends DiagramStructureGenerator{
    public NumberLineStructureGenerator() {
    }

    @Override
    public AbstractDiagramStructure generate(int ruleID, Graph host,AbstractDiagramStructure abstractDiagramStructure, int[] objects) {

        AbstractNumberLineStructure diagramStructure = (AbstractNumberLineStructure)abstractDiagramStructure;
        if(ruleID==0 || ruleID==1){
            TickPoint tickPoint = new TickPoint((VerticalLine) host.getGraphicalImageComponents().get(objects[1]));
            diagramStructure.updatetickPointList(tickPoint);
        }
        else if (ruleID == 2){
            MarkPoint markPoint1 = new MarkPoint((Circle)host.getGraphicalImageComponents().get(objects[0]),"LEFT",false);
            diagramStructure.updateMarkPointList(markPoint1);
            MarkPoint markPoint2 = new MarkPoint((Circle)host.getGraphicalImageComponents().get(objects[2]),"RIGHT",false);
            diagramStructure.updateMarkPointList(markPoint2);
        }

        else if(ruleID == 3){

            MarkPoint markPoint1 = new MarkPoint((Circle) host.getGraphicalImageComponents().get(objects[0]), "LEFT", false);
            diagramStructure.updateMarkPointList(markPoint1);
            MarkPoint markPoint2 = new MarkPoint(new Circle(Double.POSITIVE_INFINITY, host.getGraphicalImageComponents().get(objects[1]).getY1(), "rgb(255,255,255)"), "RIGHT", true);
            diagramStructure.updateMarkPointList(markPoint2);

        }
        else if (ruleID == 4){

            if(host.getGraphicalImageComponents().get(objects[0]).getLowerestXCoordinate() >= (host.getGraphicalImageComponents().get(objects[1]).getX()-10)){
                MarkPoint markPoint1 = new MarkPoint((Circle)host.getGraphicalImageComponents().get(objects[1]),"LEFT",false);
                diagramStructure.updateMarkPointList(markPoint1);
                MarkPoint markPoint2 = new MarkPoint(new Circle(Double.POSITIVE_INFINITY,host.getGraphicalImageComponents().get(objects[0]).getY1(),"rgb(255,255,255)"),"RIGHT",true);
                diagramStructure.updateMarkPointList(markPoint2);
            }
            else {
                MarkPoint markPoint2 = new MarkPoint(new Circle(Double.NEGATIVE_INFINITY,host.getGraphicalImageComponents().get(objects[0]).getY1(),"rgb(255,255,255)"),"LEFT",true);
                diagramStructure.updateMarkPointList(markPoint2);
                MarkPoint markPoint1 = new MarkPoint((Circle)host.getGraphicalImageComponents().get(objects[1]),"RIGHT",false);
                diagramStructure.updateMarkPointList(markPoint1);
            }

        }

        if(ruleID==5){
            MarkPoint markPoint1 = new MarkPoint((Circle)host.getGraphicalImageComponents().get(objects[1]),"LEFT",false);
            diagramStructure.updateMarkPointList(markPoint1);
            MarkPoint markPoint2 = new MarkPoint((Circle)host.getGraphicalImageComponents().get(objects[2]),"RIGHT",false);
            diagramStructure.updateMarkPointList(markPoint2);
        }

        return diagramStructure;
    }
}
