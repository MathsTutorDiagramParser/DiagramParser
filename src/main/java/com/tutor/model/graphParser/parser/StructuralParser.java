package com.tutor.model.graphParser.parser;

import com.tutor.model.graphParser.DiagramStructure.AbstractDiagramStructure;
import com.tutor.model.graphParser.DiagramStructure.DiagramStructureFactory;
import com.tutor.model.graphParser.GraphGrammarBuilder.Graph;
import com.tutor.model.graphParser.GraphGrammarBuilder.GraphGrammar;
import com.tutor.model.graphParser.GraphGrammarBuilder.GraphGrammarFactory;
import com.tutor.model.graphParser.GraphGrammarBuilder.ProductionRule;
import com.tutor.model.graphicalPOJOObject.Text.Text;
import com.tutor.model.util.DiagramType;

/**
 * Created by Madhavi Ruwandika on 8/4/2017.
 */
public class StructuralParser {

    GraphGrammar graphGrammar;
    boolean matched = false;

    public StructuralParser(DiagramType diagramType) {
        this.graphGrammar = GraphGrammarFactory.getGrammar(diagramType);
    }

    public  void parse(Graph host,AbstractDiagramStructure abstractDiagramStructure){

        while (!host.isInitialGraph()){
            matched= false;
            for (int i=0;i<graphGrammar.getRuleList().size();i++){
                ProductionRule productionRule = graphGrammar.getRuleList().get(i);
                int[] redex =  findRedexForRApplication(host,productionRule);
                while (redex != null){
                    rApplication(host,productionRule,redex);
                    matched = true;
                    updateAbstractRepresentation(productionRule,abstractDiagramStructure,redex);
                    redex = findRedexForRApplication(host,productionRule);
                }
            }
        }
    }

    // need to implement
    public void updateAbstractRepresentation(ProductionRule productionRule,
                                             AbstractDiagramStructure abstractDiagramStructure,int[] redex){

    }

    // need to implement
    public int[] findRedexForRApplication(Graph host,ProductionRule p){
        int[] redex = null;

        return redex;
    }

    // need to implement
    public void rApplication(Graph host,ProductionRule productionRule,int[] redex){

    }

}
