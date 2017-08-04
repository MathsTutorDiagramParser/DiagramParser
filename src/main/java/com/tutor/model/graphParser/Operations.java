package com.tutor.model.graphParser;

/**
 * Created by Wiranji Dinelka on 8/4/2017.
 */
public class Operations {

    private String id;
    private String name;

    public Operations(){}

    public Operations(String id, String name) {
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
