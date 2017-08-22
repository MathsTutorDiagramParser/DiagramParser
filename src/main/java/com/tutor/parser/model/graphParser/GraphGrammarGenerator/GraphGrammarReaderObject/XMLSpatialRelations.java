package com.tutor.parser.model.graphParser.GraphGrammarGenerator.GraphGrammarReaderObject;

/**
 * Created by Wiranji Dinelka on 8/4/2017.
 */
public class XMLSpatialRelations {

    private String id;
    private String name;

    public XMLSpatialRelations() {}

    public XMLSpatialRelations(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
