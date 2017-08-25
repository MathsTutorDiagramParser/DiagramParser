package com.tutor.parser.model.graphParser.DiagramStructure.Trignometry;

import com.tutor.parser.model.graphicalPOJOObject.Text.Text;
import com.tutor.parser.model.graphicalPOJOObject.line.Line;

/**
 * Created by Vithusha on 8/6/2017.
 */
public class LineStructure  {

    private Line line;
    private Text lengthText;


    public LineStructure(Line line){
        this.line = line;
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
