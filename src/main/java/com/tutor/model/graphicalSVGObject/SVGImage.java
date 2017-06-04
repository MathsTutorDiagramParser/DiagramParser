package com.tutor.model.graphicalSVGObject;

import java.util.ArrayList;

/**
 * Created by Wiranji Dinelka on 6/4/2017.
 */
public class SVGImage {
    private int height;
    private int width;


    private ArrayList<SVGComponent> svgComponents;

    private ArrayList<SVGRectangle> rectangles;
    private ArrayList<SVGEllipse> ellipses;
    private ArrayList<SVGCircle> circles;
    private ArrayList<SVGLine> lines;
    private ArrayList<SVGText> texts;

    public SVGImage(){

        this.height = 0;
        this.width = 0;

        this.rectangles = new ArrayList<>();
        this.ellipses = new ArrayList<>();
        this.circles = new ArrayList<>();
        this.lines = new ArrayList<>();
        this.texts = new ArrayList<>();
        svgComponents = new ArrayList<>();
    }

    public int getHeight(){
        return this.height;
    }

    public int getWidth(){
        return this.width;
    }


    public ArrayList<SVGComponent> getSvgComponents() {
        return svgComponents;
    }

    public void setSvgComponents(ArrayList<SVGComponent> svgComponents) {
        this.svgComponents = svgComponents;
    }

    public void addRectangle(SVGRectangle rectangle){

        this.rectangles.add(rectangle);
        this.svgComponents.add(rectangle);
    }

    public void addEllipse(SVGEllipse ellipse){
        this.ellipses.add(ellipse);
        this.svgComponents.add(ellipse);

    }

    public void addCircle(SVGCircle circle){
        this.circles.add(circle);
        this.svgComponents.add(circle);

    }

    public void addLine(SVGLine line)
    {
        this.lines.add(line);
        this.svgComponents.add(line);
    }

    public void addText(SVGText text){
        this.texts.add(text);
        this.svgComponents.add(text);
    }

    public int getNumOfRectangles(){
        return this.rectangles.size();
    }

    public int getNumOfEllipses(){
        return this.ellipses.size();
    }

    public int getNumOfCircles(){
        return this.circles.size();
    }

    public int getNumOfLines(){
        return this.lines.size();
    }

    public int getNumOfTexts(){
        return this.texts.size();
    }

    public ArrayList<SVGRectangle> getRectangles(){
        return this.rectangles;
    }

    public ArrayList<SVGEllipse> getEllipses(){
        return this.ellipses;
    }

    public ArrayList<SVGCircle> getCircles(){
        return this.circles;
    }

    public ArrayList<SVGLine> getLines(){
        return this.lines;
    }

    public ArrayList<SVGText> getTexts(){
        return this.texts;
    }

}
