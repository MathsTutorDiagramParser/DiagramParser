package com.tutor.model.graphParser.DiagramStructure.Histogram;



public class BarList {
    private int barID;
    private Double x;
    private Double y;
    private Double height;
    private Double width;

   public BarList (int barID,Double x, Double y, Double height, Double width){
       this.barID=barID;
       this.x=x;
       this.y=y;
       this.height=height;
       this.width=width;
   }


    public int getBarID() {
        return barID;
    }

    public void setBarID(int barID) {
        this.barID = barID;
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWidth() {
        return width;
    }

    public void setWidth(Double width) {
        this.width = width;
    }
}

