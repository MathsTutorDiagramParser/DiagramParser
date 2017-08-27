package com.tutor.parser.model.preProcessor;

import com.tutor.parser.model.graphicalPOJOObject.GraphicalImageComponent;
import com.tutor.parser.model.graphicalSVGObject.SVGImage;

import java.util.ArrayList;

/**
 * Created by Madhavi Ruwandika on 6/4/2017.
 */
public class SVGtoPOJOMapper {

    private SVGImage svgImage;
    ArrayList<GraphicalImageComponent> graphicalImageComponents ;
    ArrayList<GraphicalImageComponent> texts;

    public SVGtoPOJOMapper(SVGImage svgImage) {
        this.svgImage = svgImage;
        this.graphicalImageComponents = new ArrayList<>();
        this.texts = new ArrayList<>();
    }

    public SVGImage getSvgImage() {
        return svgImage;
    }

    public void setSvgImage(SVGImage svgImage) {
        this.svgImage = svgImage;
    }

    public ArrayList<GraphicalImageComponent> getGraphicalImageComponents() {
        return graphicalImageComponents;
    }

    public void setGraphicalImageComponents(ArrayList<GraphicalImageComponent> graphicalImageComponents) {
        this.graphicalImageComponents = graphicalImageComponents;
    }

    public ArrayList<GraphicalImageComponent> getTexts() {
        return texts;
    }

    public void setTexts(ArrayList<GraphicalImageComponent> texts) {
        this.texts = texts;
    }
}
