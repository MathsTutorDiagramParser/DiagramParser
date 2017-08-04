package com.tutor.model.graphParser.GraphGrammarReader;

/**
 * Created by Wiranji Dinelka on 8/4/2017.
 */
public class XMLRelationship {
    private String objectIdOne;
    private String objectIdTwo;
    private String spatialRelationId;

    public XMLRelationship(){}

    public XMLRelationship(String objectIdOne, String objectIdTwo, String spatialRelationId) {
        this.objectIdOne = objectIdOne;
        this.objectIdTwo = objectIdTwo;
        this.spatialRelationId = spatialRelationId;
    }

    public String getObjectIdOne() {
        return objectIdOne;
    }

    public void setObjectIdOne(String objectIdOne) {
        this.objectIdOne = objectIdOne;
    }

    public String getObjectIdTwo() {
        return objectIdTwo;
    }

    public void setObjectIdTwo(String objectIdTwo) {
        this.objectIdTwo = objectIdTwo;
    }

    public String getSpatialRelationId() {
        return spatialRelationId;
    }

    public void setSpatialRelationId(String spatialRelationId) {
        this.spatialRelationId = spatialRelationId;
    }
}
