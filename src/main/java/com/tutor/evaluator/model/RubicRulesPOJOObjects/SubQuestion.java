package com.tutor.evaluator.model.RubicRulesPOJOObjects;

import java.util.ArrayList;

/**
 * Created by Madhavi Ruwandika on 8/25/2017.
 */
public class SubQuestion {

    private int id;
    private String name;
    private ArrayList<Condition> conditions;

    public SubQuestion() {
    }

    public SubQuestion(int id, String name, ArrayList<Condition> conditions) {
        this.id = id;
        this.name = name;
        this.conditions = conditions;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Condition> getConditions() {
        return conditions;
    }

    public void setConditions(ArrayList<Condition> conditions) {
        this.conditions = conditions;
    }
}
