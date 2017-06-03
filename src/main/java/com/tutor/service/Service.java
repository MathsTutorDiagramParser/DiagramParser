package com.tutor.service;

import com.tutor.model.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wiranji Dinelka on 6/3/2017.
 */
public class Service {
    public List<Model> getAllModels() {
        Model m1 = new Model(1L, "Hello World!", "Koushik");
        Model m2 = new Model(2L, "Hello Jersey!", "Koushik");
        List<Model> list = new ArrayList<>();
        list.add(m1);
        list.add(m2);
        return list;
    }
}
