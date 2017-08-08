package com.tutor.model.preProcessor;

import com.tutor.model.graphicalPOJOObject.GraphicalImageComponent;
import com.tutor.model.graphicalPOJOObject.Text.Text;
import com.tutor.model.graphicalSVGObject.SVGImage;
import org.kie.api.cdi.KSession;
import org.kie.api.runtime.KieSession;

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
