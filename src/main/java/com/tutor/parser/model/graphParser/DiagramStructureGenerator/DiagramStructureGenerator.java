package com.tutor.parser.model.graphParser.DiagramStructureGenerator;

import com.tutor.parser.model.graphParser.DiagramStructure.AbstractDiagramStructure;
import com.tutor.parser.model.graphParser.GraphGrammarGenerator.graphGrammarObject.Graph;

/**
 * Created by Madhavi Ruwandika on 8/5/2017.
 */
public abstract class DiagramStructureGenerator {

    public abstract AbstractDiagramStructure generate(int ruleID, Graph host,AbstractDiagramStructure abstractDiagramStructure,int[] objects);

}
