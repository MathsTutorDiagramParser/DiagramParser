package com.tutor.model.graphParser.DiagramStructureGenerator;

import com.tutor.model.graphParser.DiagramStructure.AbstractDiagramStructure;
import com.tutor.model.graphParser.GraphGrammarGenerator.graphGrammarObject.Graph;

/**
 * Created by Madhavi Ruwandika on 8/5/2017.
 */
public abstract class DiagramStructureGenerator {

    public abstract AbstractDiagramStructure generate(int ruleID, Graph host,AbstractDiagramStructure abstractDiagramStructure,int[] objects);

}
