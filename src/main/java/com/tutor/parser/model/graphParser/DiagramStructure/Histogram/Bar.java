package com.tutor.parser.model.graphParser.DiagramStructure.Histogram;
import com.tutor.parser.model.graphicalPOJOObject.Rectangle.Rectangle;
import com.tutor.parser.model.graphicalPOJOObject.Text.Text;

public class Bar {
    public Rectangle rectangle;
    public Text text;
    // this should be updated by text association
    // In a bar the low x value aligned with bar
    public double xlow;
    // In a bar the  high x value aligned with bar (I can get this value by adding the width to xlow as well)
    public double xhigh;
    // Identify two text(numeric) values in Y axis and then get the length of the line then divide as (numeric value diffrence)/line length.. In which we can get the ratio so I can multiply this value from Bar heigth and get the actual y value
    public double ratio;
    public double y;

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public Bar(Rectangle rectangle){
       this.rectangle=rectangle;
       this.text=null;
   }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public Text getText() {
        return text;
    }

    public double getRatio() {
        return ratio;
    }

    public double getXhigh() {
        return xhigh;
    }

    public double getXlow() {
        return xlow;
    }

    public void setRatio(double ratio) {
        this.ratio = ratio;
    }

    public void setXhigh(double xhigh) {
        this.xhigh = xhigh;
    }

    public void setXlow(double xlow) {
        this.xlow = xlow;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    public void setText(Text text) {
        this.text = text;
    }
}

