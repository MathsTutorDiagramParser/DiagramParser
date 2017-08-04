package com.tutor.model.graphParser.GraphGrammarBuilder;

import com.tutor.model.graphicalPOJOObject.GraphicalImageComponent;
import com.tutor.model.util.SpatialRelation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Madhavi Ruwandika on 8/4/2017.
 */
public class Graph {

    private List<GraphicalImageComponent> graphicalImageComponents;
    private ArrayList<SpatialRelation>[][] relations;
    private boolean isInitialGraph = false;

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
}
