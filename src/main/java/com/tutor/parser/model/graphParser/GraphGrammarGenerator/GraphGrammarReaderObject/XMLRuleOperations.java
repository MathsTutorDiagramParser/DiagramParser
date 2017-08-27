package com.tutor.parser.model.graphParser.GraphGrammarGenerator.GraphGrammarReaderObject;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.ArrayList;

/**
 * Created by Wiranji Dinelka on 8/4/2017.
 */
public class XMLRuleOperations {

    private String id;
    private ArrayList<Integer> from_id_List;

    public XMLRuleOperations() {}

    public XMLRuleOperations(String id, ArrayList<Integer> from_id_List ) {
        this.from_id_List = from_id_List;
        this.id = id;
    }

    @XmlElementWrapper(name="ObjectList")
    @XmlElement(name="Object")
    public ArrayList<Integer> getFrom_id_List() {
        return from_id_List;
    }

    public void setFrom_id_List(ArrayList<Integer> from_id_List) {
        this.from_id_List = from_id_List;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
