package com.tutor.model.graphicalPOJOObject.Text;

import com.tutor.model.graphicalPOJOObject.GraphicalImageComponent;

/**
 * Created by Madhavi Ruwandika on 6/4/2017.
 */
public class Text extends GraphicalImageComponent {

    String text;

    public Text(double x, double y, String text) {
        super(x,y);
        this.text = text;
    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
