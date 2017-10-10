package com.tutor.evaluator.model.constants.TreeDiagramWordPool;

import com.tutor.parser.model.graphicalPOJOObject.Text.Text;

import java.util.ArrayList;

/**
 * Created by Wiranji Dinelka on 9/28/2017.
 */
public class Question3Statement implements QuestionStatement{




    public Question3Statement() {
            }

    @Override
    public ArrayList<Text> getRootUpperOutcomeList() {
        //setLabelModel.add("");
        return null;
    }

    @Override
    public ArrayList<Text> getRootLowerOutcomeList() {
        //setLabelModel.add("");
        return null;
    }

    @Override
    public ArrayList<Text> getChildUpperOutcomeList() {
        Text t1 = new Text(0,0,"වැළදීම");
        Text t2 = new Text(0,0,"වැළදී");
        Text t3 = new Text(0,0,"වැළදි");
        Text t4 = new Text(0,0,"අැති");
        ArrayList<Text> setLabelModel = new ArrayList<>();
        setLabelModel.add(t1);
        setLabelModel.add(t2);
        setLabelModel.add(t3);
        setLabelModel.add(t4);
        return setLabelModel;
    }

    @Override
    public ArrayList<Text> getChildLowerOutcomeList() {
        Text t1 = new Text(0,0,"නොවැළදීම");
        Text t2 = new Text(0,0,"නොවැළදි");
        Text t3 = new Text(0,0,"නොවැළදී");
        Text t4 = new Text(0,0,"නැති");
        Text t5 = new Text(0,0,"නොමැති");
        Text t6 = new Text(0,0,"නිවරදි");

        ArrayList<Text> setLabelModel = new ArrayList<>();
        setLabelModel.add(t1);
        setLabelModel.add(t2);
        setLabelModel.add(t3);
        setLabelModel.add(t4);
        setLabelModel.add(t5);
        setLabelModel.add(t6);
        return setLabelModel;
    }
}
