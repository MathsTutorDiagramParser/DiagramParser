package com.tutor.model;

/**
 * Created by Wiranji Dinelka on 6/4/2017.
 */
public class SVGText {
    private double x;
    private double y;
    private String text;
    private int fontSize;
    private boolean isNumeric;
    private Double numericValue;

    public SVGText(double x, double y, String text, int fontSize){
        this.x = x;
        this.y = y;
        this.text = text;
        //this.text = StringEscapeUtils.unescapeHtml4(text);
        //System.out.println("Text :"+text+" Converted: "+this.text);
        this.fontSize = fontSize;

        if(_isNumeric()){
            this.isNumeric = true;
            this.numericValue = Double.parseDouble(this.text);
        }
        else{
            this.isNumeric = false;
            this.numericValue = null;
        }
    }

    public double getX(){
        //Average length of a character is a 13 with font size 24 with "Helvetica" font family

        double correction = fontSize*text.length()*0.54/2;
        return this.x + correction;
    }

    public double getY(){
        //Average correction is a 11.5/24 with font size 24 with "Helvetica" font family
        double correction = fontSize*0.48;
        return this.y - correction;
    }

    public double getHeight(){
        return fontSize/0.48;
    }

    public double getWitdh(){
        return fontSize*text.length()*0.54;
    }

    public String getText(){
        return this.text;
    }

    public boolean isNumeric(){
        return this.isNumeric;
    }

    public Double getNumericValue(){
        return this.numericValue;
    }

    private final boolean _isNumeric(){
        try
        {
            Double.parseDouble(this.text);
        }
        catch(NumberFormatException nfex)
        {
            return false;
        }
        return true;
    }

}

