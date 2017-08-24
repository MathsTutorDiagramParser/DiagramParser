package com.tutor.parser.model.graphParser.GraphGrammarGenerator.GraphGrammarReaderObject;

/**
 * Created by Wiranji Dinelka on 8/5/2017.
 */
public class XMLObject {
    private int id;
    private String type;

    public XMLObject() {

    }
    public XMLObject(int id, String type) {
        this.id = id;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
