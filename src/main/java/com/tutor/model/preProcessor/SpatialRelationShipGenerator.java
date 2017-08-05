package com.tutor.model.preProcessor;

import com.tutor.model.util.SpatialRelation;
import com.tutor.model.graphicalPOJOObject.GraphicalImageComponent;
import com.tutor.model.util.ObjectType;
import java.util.ArrayList;
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
                relations[i][j] = new ArrayList<>();
                relations[j][i] = new ArrayList<>();
                if (i==j){
                    ArrayList<SpatialRelation> relation = new ArrayList<>();
                    relation.add(SpatialRelation.SAME);
                    relations[i][j] = relation;
                } else {
                    // get the existing spatial relationship between 2 objects
                    ArrayList<SpatialRelation> relation =   identifySpatiolRelation(objectList.get(i),objectList.get(j));
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
        return relations;
    }

    public boolean isTough(GraphicalImageComponent o1,GraphicalImageComponent o2){
        if( (o1.objectType!= ObjectType.Line && o2.objectType!=ObjectType.Line && o1.getX()== o2.getX() && o1.getY()== o2.getY())
                || (o1.objectType!= ObjectType.CIRCLE && o2.objectType!=ObjectType.CIRCLE  && (o1.getX1()==o2.getX1() && o1.getY1()==o2.getY1()))
                || (o1.objectType!= ObjectType.CIRCLE && o2.objectType!=ObjectType.CIRCLE  && (o1.getX2()==o2.getX2() && o1.getY2()==o2.getY2()))
                || (o1.objectType!= ObjectType.CIRCLE && o2.objectType!=ObjectType.CIRCLE  && (o1.getX1()==o2.getX2() && o1.getY1()==o2.getY2()))
                || (o1.objectType!= ObjectType.CIRCLE && o2.objectType!=ObjectType.CIRCLE  && (o1.getX2()==o2.getX1() && o1.getY2()==o2.getY1()))
                || (o1.objectType!= ObjectType.Line && o2.objectType!=ObjectType.CIRCLE  && ((o1.getX()==o2.getX1() && o1.getY()==o2.getY1())||(o1.getX()==o2.getX2() && o1.getY()==o2.getY2())))
                || (o1.objectType!= ObjectType.CIRCLE && o2.objectType!=ObjectType.Line  && ((o1.getX1()==o2.getX() && o1.getY1()==o2.getY())||(o1.getX2()==o2.getX() && o1.getY2()==o2.getY())))
                ){
            return true;
        }
        return false;
    }


    public boolean isCross(GraphicalImageComponent o1,GraphicalImageComponent o2){
        if(o2.objectType==ObjectType.Line && o1.objectType==ObjectType.Line){
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
                    System.out.println("=="+m1+"=="+m2);
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
        else if(o1.objectType==ObjectType.Line && o2.objectType==ObjectType.CIRCLE){
            if(pointOnLineSegment(o1.getX1(),o1.getX2(),o1.getY1(),o1.getY2(),o2.getX(),o2.getY())){
                return true;
            }
        }
        else if(o2.objectType==ObjectType.Line && o1.objectType==ObjectType.CIRCLE){
            if(pointOnLineSegment(o2.getX1(),o2.getX2(),o2.getY1(),o2.getY2(),o1.getX(),o1.getY())){
                return true;
            }
        }
        else if(o2.objectType==ObjectType.Line && o1.objectType==ObjectType.Line){
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

}
