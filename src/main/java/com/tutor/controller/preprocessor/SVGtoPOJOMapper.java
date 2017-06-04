package com.tutor.controller.preprocessor;

import com.tutor.model.graphicalpojoObject.Circle.Circle;
import com.tutor.model.graphicalpojoObject.Text.Text;
import com.tutor.model.graphicalpojoObject.line.AngleLine;
import com.tutor.model.graphicalpojoObject.line.HorizontalLine;
import com.tutor.model.graphicalpojoObject.line.VerticalLine;
import com.tutor.model.graphicalSVGObject.SVGComponent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Madhavi Ruwandika on 6/4/2017.
 */
public class SVGtoPOJOMapper {

    List<SVGComponent> svgComponents;

    ArrayList<VerticalLine> verticalLines;
    ArrayList<HorizontalLine> horizontalLines;
    ArrayList<Circle> circles;
    ArrayList<Text> texts;



    ArrayList<AngleLine> angleLines;


    public SVGtoPOJOMapper(List<SVGComponent> svgComponents, ArrayList<VerticalLine> verticalLines, ArrayList<HorizontalLine> horizontalLines, ArrayList<Circle> circles, ArrayList<Text> texts, ArrayList<AngleLine> angleLines) {
        this.svgComponents = svgComponents;
        this.verticalLines = verticalLines;
        this.horizontalLines = horizontalLines;
        this.circles = circles;
        this.texts = texts;
        this.angleLines = angleLines;
    }

    public List<SVGComponent> getSvgComponents() {
        return svgComponents;
    }

    public void setSvgComponents(ArrayList<SVGComponent> svgComponents) {
        this.svgComponents = svgComponents;
    }

    public ArrayList<VerticalLine> getVerticalLines() {
        return verticalLines;
    }

    public void setVerticalLines(ArrayList<VerticalLine> verticalLines) {
        this.verticalLines = verticalLines;
    }

    public ArrayList<HorizontalLine> getHorizontalLines() {
        return horizontalLines;
    }

    public void setHorizontalLines(ArrayList<HorizontalLine> horizontalLines) {
        this.horizontalLines = horizontalLines;
    }

    public ArrayList<Circle> getCircles() {
        return circles;
    }

    public void setCircles(ArrayList<Circle> circles) {
        this.circles = circles;
    }

    public List<Text> getTexts() {
        return texts;
    }

    public void setTexts(ArrayList<Text> texts) {
        this.texts = texts;
    }

    public void addSVGComponent(SVGComponent component) {
        if (svgComponents == null) {
            svgComponents = new ArrayList<SVGComponent>();
        }
        svgComponents.add(component);
    }

    public ArrayList<AngleLine> getAngleLines() {
        return angleLines;
    }

    public void setAngleLines(ArrayList<AngleLine> angleLines) {
        this.angleLines = angleLines;
    }
}
