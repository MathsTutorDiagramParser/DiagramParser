package com.tutor.model.graphParser.GraphGrammarGenerator.GraphGrammarReaderObject;

/**
 * Created by Wiranji Dinelka on 8/4/2017.
 */
public class XMLTaggedValue {

    private String tag;
    private String tagValue;

    public XMLTaggedValue(){}

    public XMLTaggedValue(String tag, String tagValue) {
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
