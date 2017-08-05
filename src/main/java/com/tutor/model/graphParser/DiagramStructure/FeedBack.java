package com.tutor.model.graphParser.DiagramStructure;

/**
 * Created by Madhavi Ruwandika on 8/4/2017.
 */
public class FeedBack {

    private String Id;
    private String description;

    public FeedBack(String id) {
        Id = id;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
