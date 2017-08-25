package com.tutor.evaluator.service;

import java.util.ArrayList;

/**
 * Created by Wiranji Dinelka on 8/25/2017.
 */
public class AnswerEvaluationServiceImpl {

    public void evaluate() {

        //රතු පාට බෝලයක් ගැනීම
        //නිල් පාට බෝලයක් ගැනීම
        //පලමු ගැනීම

        ArrayList<String> setLabelModel = new ArrayList<String>(){{
            add("රතු");
            add("රතු පාට");
            add("රතු පාට බෝලයක්");
            add("රතු පාට බෝලයක් ගැනීම");
            add("නිල්");
            add("නිල් පාට");
            add("");
            add("නිල් පාට බෝලයක් ගැනීම");
            add("පලමු ගැනීම");
        }};
        ArrayList<String> setLabelAnswer = new ArrayList<>();
        setLabelAnswer.add("රතු පාට බෝලයක් ගැනීම");
//    	setLabelAnswer.add("දේශීය");
//    	setLabelAnswer.add("වට්ටම් ලබා දෙන")නිල් පාට බෝලයක්;

        Double[][] similarity = new Double[setLabelModel.size()][setLabelAnswer.size()];

        for(int modelLabelIndex=0; modelLabelIndex<setLabelModel.size(); modelLabelIndex++){

            double maxRowSimilarity = 0.0;

            for(int answerLabelIndex=0; answerLabelIndex<setLabelAnswer.size(); answerLabelIndex++){

                double similarityTemp = StringMatcherServiceImpl.getSimilarity(setLabelModel.get(modelLabelIndex), setLabelAnswer.get(answerLabelIndex));
                similarity[modelLabelIndex][answerLabelIndex] = similarityTemp;
                System.out.println("Value :"+modelLabelIndex+"--"+answerLabelIndex+"=="+similarityTemp);

                if(similarityTemp>maxRowSimilarity && similarityTemp >= 0.1){
                    maxRowSimilarity = similarityTemp;
                    //System.out.println("Similar :"+modelLabelIndex+"--"+answerLabelIndex+"=="+similarityTemp);

                }
            }
        }
    }
}
