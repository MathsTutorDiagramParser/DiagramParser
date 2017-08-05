package com.tutor.model.graphParser.parser;
import com.tutor.model.TextAligner.TextAlignmentAssigner;
import com.tutor.model.graphParser.DiagramStructure.AbstractDiagramStructure;
import com.tutor.model.graphicalPOJOObject.Text.Text;
import com.tutor.model.util.DiagramType;

import java.util.List;


/**
 * Created by Madhavi Ruwandika on 8/4/2017.
 */
public class TextAssociator {
    AbstractDiagramStructure abstractDiagramStructure;
    DiagramType diagramType;
    List<Text> textList;
    TextAlignmentAssigner textAligner;


    public void associateText(AbstractDiagramStructure abstractDiagramStructure, DiagramType diagramType, List<Text> textList){
        this.abstractDiagramStructure = abstractDiagramStructure;
        this.diagramType = diagramType;
        this.textList = textList;
        textAligner.assignTextAligner(abstractDiagramStructure, diagramType, textList );
    }
}
