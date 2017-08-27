package com.tutor.model.graphParser.DiagramStructure.Trignometry;

import com.tutor.model.graphicalPOJOObject.GraphicalImageComponent;
import com.tutor.model.graphicalPOJOObject.Text.Text;
import com.tutor.model.graphicalPOJOObject.line.Line;
import com.tutor.model.util.ObjectType;

/**
 * Created by Vithusha on 8/6/2017.
 */
public class LineStructure  extends GraphicalImageComponent {

    private Line line;
    private Text lengthText;
    public ObjectType objectType;


    public LineStructure(Line line){
        this.line = line;
        this.objectType = ObjectType.LINE;
    }
    public Line getLine() {
        return line;
    }

    public void setLengthText(Text lengthText) {
        this.lengthText = lengthText;
    }

    public Text getLengthText() {
        return lengthText;
    }


}
