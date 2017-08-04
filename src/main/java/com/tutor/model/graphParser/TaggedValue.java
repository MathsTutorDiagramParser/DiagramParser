package com.tutor.model.graphParser;

/**
 * Created by Wiranji Dinelka on 8/4/2017.
 */
public class TaggedValue {

    private String tag;
    private String tagValue;

    public TaggedValue(){}

    public TaggedValue(String tag, String tagValue) {
        this.tag = tag;
        this.tagValue = tagValue;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTagValue() {
        return tagValue;
    }

    public void setTagValue(String tagValue) {
        this.tagValue = tagValue;
    }
}
