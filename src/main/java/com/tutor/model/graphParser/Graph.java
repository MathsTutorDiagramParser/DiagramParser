package com.tutor.model.graphParser;

import com.tutor.model.graphicalPOJOObject.GraphicalImageComponent;

import java.util.List;

/**
 * Created by Wiranji Dinelka on 8/4/2017.
 */
public class Graph {
    private List<GraphicalImageComponent> objectList;
    private List<Relationship> relationships;

    public Graph(List<GraphicalImageComponent> objectList, List<Relationship> relationships ) {
        this.objectList = objectList;
        this.relationships = relationships;
    }

    public List<GraphicalImageComponent> getObjectList() {
        return objectList;
    }

    public void setObjectList(List<GraphicalImageComponent> objectList) {
        this.objectList = objectList;
    }

    public List<Relationship> getRelationships() {
        return relationships;
    }

    public void setRelationships(List<Relationship> relationships) {
        this.relationships = relationships;
    }
}
