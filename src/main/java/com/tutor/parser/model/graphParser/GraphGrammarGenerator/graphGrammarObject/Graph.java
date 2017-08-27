package com.tutor.parser.model.graphParser.GraphGrammarGenerator.graphGrammarObject;

import com.tutor.parser.model.graphicalPOJOObject.GraphicalImageComponent;
import com.tutor.parser.model.util.SpatialRelation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Madhavi Ruwandika on 8/4/2017.
 */
public class Graph {

    private List<GraphicalImageComponent> graphicalImageComponents;
    private ArrayList<SpatialRelation>[][] relations;
    private boolean isInitialGraph = false;

    public Graph(List<GraphicalImageComponent> graphicalImageComponents, ArrayList<SpatialRelation>[][] relations) {
        this.graphicalImageComponents = graphicalImageComponents;
        this.relations = relations;
    }

    public Graph() {
    }

    public List<GraphicalImageComponent> getGraphicalImageComponents() {
        return graphicalImageComponents;
    }

    public void setGraphicalImageComponents(List<GraphicalImageComponent> graphicalImageComponents) {
        this.graphicalImageComponents = graphicalImageComponents;
    }

    public ArrayList<SpatialRelation>[][] getRelations() {
        return relations;
    }

    public void setRelations(ArrayList<SpatialRelation>[][] relations) {
        this.relations = relations;
    }

    public boolean isInitialGraph() {
        return isInitialGraph;
    }

    public void setInitialGraph(boolean initialGraph) {
        isInitialGraph = initialGraph;
    }

    public ArrayList<SpatialRelation> getSpatialRelations(int index1,int index2 ){
        return relations[index1][index2];
    }
}
