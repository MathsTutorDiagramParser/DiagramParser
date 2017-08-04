package com.tutor.model.graphParser;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;


/**
 * Created by Wiranji Dinelka on 8/4/2017.
 */
@XmlRootElement
public class GraphGrammar {

    private TaggedValue taggedValue;
    private ArrayList<ObjectTypes> types;
    private ArrayList<SpatialRelations> spatialRelations;
    private ArrayList<Operations> operations;
    private ArrayList<Rule> rules;

    public GraphGrammar() {  }

    public GraphGrammar(TaggedValue taggedValue, ArrayList<ObjectTypes> types, ArrayList<SpatialRelations> spatialRelations, ArrayList<Operations> operations, ArrayList<Rule> rules) {
        super();
        this.taggedValue = taggedValue;
        this.types= types;
        this.spatialRelations = spatialRelations;
        this.operations = operations;
        this.rules = rules;
    }


    @XmlElement(name="TaggedValue")
    public TaggedValue getTaggedValue() {
        return taggedValue;
    }

    public void setTaggedValue(TaggedValue taggedValue) {
        this.taggedValue = taggedValue;
    }

    @XmlElementWrapper(name="Types")
    @XmlElement(name="ObjectType")
    public ArrayList<ObjectTypes> getTypes() {
        return types;
    }

    public void setTypes(ArrayList<ObjectTypes> types) {
        this.types = types;
    }

    @XmlElementWrapper(name="Relations")
    @XmlElement(name="SpatialRelationType")
    public ArrayList<SpatialRelations> getSpatialRelations() {
        return spatialRelations;
    }

    public void setSpatialRelations(ArrayList<SpatialRelations> spatialRelations) {
        this.spatialRelations = spatialRelations;
    }

    @XmlElementWrapper(name="Operations")
    @XmlElement(name="OperationTypes")
    public ArrayList<Operations> getOperations() {
        return operations;
    }

    public void setOperations(ArrayList<Operations> operations) {
        this.operations = operations;
    }

    @XmlElementWrapper(name="Rules")
    @XmlElement(name="Rule")
    public ArrayList<Rule> getRules() {
        return rules;
    }

    public void setRules(ArrayList<Rule> rules) {
        this.rules = rules;
    }


}
