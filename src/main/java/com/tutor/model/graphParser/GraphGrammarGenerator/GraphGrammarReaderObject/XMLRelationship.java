package com.tutor.model.graphParser.GraphGrammarGenerator.GraphGrammarReaderObject;

/**
 * Created by Wiranji Dinelka on 8/4/2017.
 */
public class XMLRelationship {
    private int objectIdOne;
    private int objectIdTwo;
    private String spatialRelationId;

    public XMLRelationship(){}

    public XMLRelationship(int objectIdOne, int objectIdTwo, String spatialRelationId) {
        this.objectIdOne = objectIdOne;
        this.objectIdTwo = objectIdTwo;
        this.spatialRelationId = spatialRelationId;
    }

    public int getObjectIdOne() {
        return objectIdOne;
    }

    public void setObjectIdOne(int objectIdOne) {
        this.objectIdOne = objectIdOne;
    }

    public int getObjectIdTwo() {
        return objectIdTwo;
    }

    public void setObjectIdTwo(int objectIdTwo) {
        this.objectIdTwo = objectIdTwo;
    }

    public String getSpatialRelationId() {
        return spatialRelationId;
    }

    public void setSpatialRelationId(String spatialRelationId) {
        this.spatialRelationId = spatialRelationId;
    }
}
