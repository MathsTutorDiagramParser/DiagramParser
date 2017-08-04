package com.tutor.model.graphParser;

import com.tutor.model.graphicalPOJOObject.GraphicalImageComponent;

import java.util.List;

/**
 * Created by Wiranji Dinelka on 8/4/2017.
 */
public class Graph {
    private List<String> objectIdList;
    private List<Relationship> relationships;

    public Graph() {

    }

    public Graph(List<String> objectIdList, List<Relationship> relationships ) {
        this.objectIdList = objectIdList;
        this.relationships = relationships;
    }

    public List<String> getObjectIdList() {
        return objectIdList;
    }

    public void setObjectIdList(List<String> objectIdList) {
        this.objectIdList = objectIdList;
    }

    public List<Relationship> getRelationships() {
        return relationships;
    }

    public void setRelationships(List<Relationship> relationships) {
        this.relationships = relationships;
    }
}
