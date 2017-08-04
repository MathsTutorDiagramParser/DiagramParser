package com.tutor.model.graphParser.DiagramStructure;

import com.tutor.model.graphicalPOJOObject.Text.Text;

import java.util.List;

/**
 * Created by Madhavi Ruwandika on 8/4/2017.
 */
public class AbstractDiagramStructure {

    List<Text> textList;

    public AbstractDiagramStructure(List<Text> textList) {
        this.textList = textList;
    }

}
