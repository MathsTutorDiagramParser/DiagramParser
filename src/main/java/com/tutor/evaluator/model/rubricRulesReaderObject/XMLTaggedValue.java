package com.tutor.evaluator.model.rubricRulesReaderObject;

import javax.xml.bind.annotation.XmlElement;

public class XMLTaggedValue {
    private String tag;
    private String tagValue;

    public XMLTaggedValue(){}

    public XMLTaggedValue(String tag, String tagValue) {
        this.tag = tag;
        this.tagValue = tagValue;
    }

    @XmlElement(name="tag")
    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @XmlElement(name="tagValue")
    public String getTagValue() {
        return tagValue;
    }

    public void setTagValue(String tagValue) {
        this.tagValue = tagValue;
    }
}
