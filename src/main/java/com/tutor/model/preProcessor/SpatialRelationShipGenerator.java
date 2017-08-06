package com.tutor.model.preProcessor;

import com.tutor.model.graphParser.GraphGrammarBuilder.Graph;
import com.tutor.model.graphParser.GraphGrammarBuilder.ProductionRule;
import com.tutor.model.graphParser.GraphGrammarBuilder.RuleOperations;
import com.tutor.model.util.DiagramType;
import com.tutor.model.util.RuleOperation;
import com.tutor.model.util.SpatialRelation;
import com.tutor.model.graphicalPOJOObject.GraphicalImageComponent;
import com.tutor.model.util.ObjectType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Madhavi Ruwandika on 8/3/2017.
 */
public class SpatialRelationShipGenerator {


    private ArrayList<SpatialRelation>[][] relations ;


    public ArrayList<SpatialRelation>[][] buildSpatialRelationShipMatrix(List<GraphicalImageComponent> objectList){

        int num_of_objects = objectList.size();
        relations = new ArrayList[num_of_objects][num_of_objects];

        for(int i=0;i<num_of_objects;i++){
            for(int j=i; j<num_of_objects;j++){
                if (i==j){
                    if(relations[i][j] == null){
                        ArrayList<SpatialRelation> relation = new ArrayList<>();
                        relation.add(SpatialRelation.SAME);
                        relations[i][j] = relation;
                    }
                    else {
                        relations[i][j].add(SpatialRelation.SAME);
                    }
                } else {
                    // get the existing spatial relationship between 2 objects
                    ArrayList<SpatialRelation> relation =   identifySpatiolRelation(objectList.get(i),objectList.get(j));

                    // this is numberline specific checking condition
                    if(relation.contains(SpatialRelation.SAMEEND)){
                        if(relations[i][i]==null){
                            relations[i][i]=new ArrayList<>();
                            relations[i][i].add(SpatialRelation.SAMEEND);
                        }else {
                            relations[i][i].add(SpatialRelation.SAMEEND);
                        }
                       if(relations[j][j]==null){
                           relations[j][j] = new ArrayList<>();
                           relations[j][j].add(SpatialRelation.SAMEEND);
                       }
                       else {
                           relations[j][j].add(SpatialRelation.SAMEEND);
                       }
                       relation.remove(SpatialRelation.SAMEEND);
                    }
                    //end

                    relations[i][j]= relation;
                    relations[j][i]= relation;
                }
            }
        }
        return relations;
    }

    public ArrayList<SpatialRelation> identifySpatiolRelation(GraphicalImageComponent o1,GraphicalImageComponent o2){

        ArrayList<SpatialRelation> relations = new ArrayList<>();
        //identify tough
        if(isTough(o1,o2)){
            relations.add(SpatialRelation.TOUCH);
        }
        //identify overlap
        if (isOverLap(o1,o2)){
            relations.add(SpatialRelation.OVERLAP);
        }
        //identify cross
        if (isCross(o1,o2)){
            relations.add(SpatialRelation.CROSS);
        }

        if(isSameEnd(o1,o2)){
            relations.add(SpatialRelation.SAMEEND);
        }

        return relations;
    }

    public boolean isTough(GraphicalImageComponent o1,GraphicalImageComponent o2){
        if( (o1.superObjectType!= ObjectType.LINE && o2.superObjectType!=ObjectType.LINE && (isCloseToTough(o1.getX(), o2.getX()) && isCloseToTough(o1.getY(),o2.getY())))
                || (o1.objectType!= ObjectType.CIRCLE && o2.objectType!=ObjectType.CIRCLE  && (isCloseToTough(o1.getX1(),o2.getX1()) && isCloseToTough(o1.getY1(),o2.getY1())))
                || (o1.objectType!= ObjectType.CIRCLE && o2.objectType!=ObjectType.CIRCLE  && (isCloseToTough(o1.getX2(),o2.getX2()) && isCloseToTough(o1.getY2(),o2.getY2())))
                || (o1.objectType!= ObjectType.CIRCLE && o2.objectType!=ObjectType.CIRCLE  && (isCloseToTough(o1.getX1(),o2.getX2()) && isCloseToTough(o1.getY1(),o2.getY2())))
                || (o1.objectType!= ObjectType.CIRCLE && o2.objectType!=ObjectType.CIRCLE  && (isCloseToTough(o1.getX2(),o2.getX1()) && isCloseToTough(o1.getY2(),o2.getY1())))
                || (o1.superObjectType!= ObjectType.LINE && o2.objectType!=ObjectType.CIRCLE  && (isCloseToTough(o1.getX(),o2.getX1()) && isCloseToTough(o1.getY(),o2.getY1()))||(isCloseToTough(o1.getX(),o2.getX2()) && isCloseToTough(o1.getY(),o2.getY2())))
                || (o1.objectType!= ObjectType.CIRCLE && o2.superObjectType!=ObjectType.LINE  && (isCloseToTough(o1.getX1(),o2.getX()) && isCloseToTough(o1.getY1(),o2.getY()))||(isCloseToTough(o1.getX2(),o2.getX()) && isCloseToTough(o1.getY2(),o2.getY())))
                ){
            return true;
        }

        return false;
    }

    public boolean isCross(GraphicalImageComponent o1,GraphicalImageComponent o2){
        if(o2.superObjectType==ObjectType.LINE && o1.superObjectType==ObjectType.LINE){
            // calculate gradiant of lines
            double m1 = (o1.getY1()-o1.getY2())/(o1.getX1()-o1.getX2());
            double m2 = (o2.getY1()-o2.getY2())/(o2.getX1()-o2.getX2());

            double cross_x,cross_y;

            if(m1!= m2){

                if (m1 == Double.POSITIVE_INFINITY || m1 ==  Double.NEGATIVE_INFINITY){
                    double c2_1 = (o2.getY1()-(o2.getX1()*m2));
                    cross_x = o1.getX1();
                    cross_y = (m2*cross_x)+c2_1;
                }

                else if(m2 == Double.POSITIVE_INFINITY || m2 == Double.NEGATIVE_INFINITY){

                    double c1_1 = (o1.getY1()-(o1.getX1()*m1));
                    cross_x = o2.getX1();
                    cross_y = (m1*cross_x)+c1_1;
                }

                else {
                    //System.out.println("=="+m1+"=="+m2);
                    // calculate
                    double c1 = (o1.getY1()-(o1.getX1()*m1));
                    double c2 = (o2.getY1()-(o2.getX1()*m2));

                    cross_x = (c2-c1)/(m1-m2);
                    cross_y = ((m2*c1) - (m1*c2)) / (m2-m1);
                }



                if(pointOnLineSegment(o1.getX1(),o1.getX2(),o1.getY1(),o1.getY2(),cross_x,cross_y)){
                    return true;

                }
            }
        }

        return false;
    }
    public boolean isOverLap(GraphicalImageComponent o1,GraphicalImageComponent o2) {
        if(o1.objectType==ObjectType.CIRCLE && o2.objectType==ObjectType.CIRCLE){
            if(o1.getX()==o2.getX() && o1.getY()==o2.getY()){
                return true;
            }
        }
        else if(o1.superObjectType==ObjectType.LINE && o2.objectType==ObjectType.CIRCLE){
            if(pointOnLineSegment(o1.getX1(),o1.getX2(),o1.getY1(),o1.getY2(),o2.getX(),o2.getY())){
                return true;
            }
        }
        else if(o2.superObjectType==ObjectType.LINE && o1.objectType==ObjectType.CIRCLE){
            if(pointOnLineSegment(o2.getX1(),o2.getX2(),o2.getY1(),o2.getY2(),o1.getX(),o1.getY())){
                return true;
            }
        }
        else if(o2.superObjectType==ObjectType.LINE && o1.superObjectType==ObjectType.LINE){
            double m1 = (o1.getY1()-o1.getY2())/(o1.getX1()-o1.getX2());
            double m2 = (o2.getY1()-o2.getY2())/(o2.getX1()-o2.getX2());

            if(m1==m2){
                if(pointOnLineSegment(o1.getX1(),o1.getX2(),o1.getY1(),o1.getY2(),o2.getX1(),o2.getY1())
                        || pointOnLineSegment(o1.getX1(),o1.getX2(),o1.getY1(),o1.getY2(),o2.getX2(),o2.getY2())){
                    return true;
                }
            }
        }

        return false;
    }

    public boolean isSameEnd(GraphicalImageComponent o1,GraphicalImageComponent o2){

        if(isOverLap(o1,o2)){
            if((o1.objectType.equals(ObjectType.HORIZONTAL_LINE) && o2.objectType.equals(ObjectType.HORIZONTAL_LINE))) {
                double o1_lowest = o1.getLowerestXCoordinate();
                double o1_highest = o1.getHighestXCoordinate();

                double o2_lowest = o2.getLowerestXCoordinate();
                double o2_highest = o2.getHighestXCoordinate();

                if( o1_lowest <= o2_lowest ){
                    if( (o2_lowest-30.0) < o1_lowest){
                        return true;
                    }
                    else return false;
                }
                else if(o1_lowest >= o2_lowest){
                    if( (o1_lowest-30.0) < o2_lowest){
                        return true;
                    }
                    else return false;
                }
                else if (o1_highest>=o2_highest){
                    if( (o1_highest-30.0) < o2_highest){
                        return true;
                    }
                    else return false;
                }
                else if(o1_highest <=o2_highest){
                    if( (o2_highest-30.0) < o1_highest){
                        return true;
                    }
                    else return false;
                }
            }
        }
        return false;
    }


    public boolean pointOnLineSegment(double x1,double x2,double y1,double y2, double x,double y){

        if(((x1<=x )&& (x <= x2))||((x1>=x)&&(x2<=x))){
            if(((y1<=y) && (y <= y2))||((y1>=y)&&(y2<=y))){
                double m1 = ((y1-y)/(x1-x));
                double m2 = ((y1-y2)/(x1-x2));
                if(m1==m2){
                    return true;
                }
            }
        }
        return false;

    }

    public ArrayList<SpatialRelation>[][] getRelations() {
        return relations;
    }

    public static void updateSpatialRelationShipMatrix(Graph host, int[] redex, ProductionRule rule, DiagramType diagramType){

        ArrayList<RuleOperations> ruleOperations = rule.getRuleOperations();

        switch (diagramType){
            case NUMBRELINE:
                //need to consider rule operation
                ArrayList<SpatialRelation>[][] old = host.getRelations();
                ArrayList<SpatialRelation>[] change = old[redex[0]];

                ArrayList<SpatialRelation>[][] newRelations =
                        new ArrayList[host.getGraphicalImageComponents().size()][host.getGraphicalImageComponents().size()];

                int newItr =0;
                for(int oldItr = 0;oldItr < change.length; oldItr++){
                    ArrayList<SpatialRelation>[] substitute = old[oldItr];
                    //if(oldItr==0 || !isInArray(Arrays.copyOfRange(redex,1,redex.length),oldItr)){
                    if(!isInArray(Arrays.copyOfRange(redex,1,redex.length),oldItr)){
                        newRelations[newItr] = buildSubstituteArray(substitute,redex);
                        newItr++;
                    }
                }
                host.setRelations(newRelations);

            case HISTOGRAM:
            case TREEDIAGRAM:
            case TRIGNOMETRICDIAGRAM:

        }

    }


    public static ArrayList<SpatialRelation>[] buildSubstituteArray(ArrayList<SpatialRelation>[] substitute,int[] redex){
        int size = substitute.length-redex.length+1;

        if (size==0){
            return substitute;
        }

        ArrayList<SpatialRelation>[] newSubstitute = new  ArrayList[size];
        int itrNew = 0;
        for (int i=0;i<substitute.length;i++){

            if(i==redex[0] || !isInArray(redex,i)){
                newSubstitute[itrNew] = substitute[i];
                itrNew++;
            }
        }
        return newSubstitute;
    }

    public static boolean isInArray(int[] array, int element){
        for(int i=0;i<array.length;i++){
            if(array[i]==element){
                return true;
            }
        }
        return false;
    }

    public boolean isCloseToTough(double p,double q){
        if((p<= q+0.5) && (p>(q-0.5))){
            return true;
        }
        return false;
    }


}
