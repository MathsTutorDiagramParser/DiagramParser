package com.tutor.model.graphicalPOJOObject.line;

import com.tutor.model.util.ObjectType;

/**
 * Created by Madhavi Ruwandika on 6/4/2017.
 */
public class VerticalLine extends Line {
    public VerticalLine(double x1, double y1, double x2, double y2, int stroke_width) {
        super(x1, y1, x2, y2, stroke_width);
        this.objectType = ObjectType.VERTICAL_LINE;
        this.superObjectType = ObjectType.LINE;
    }
}
