package com.tutor.parser.model.preProcessor;

import com.tutor.parser.model.graphParser.GraphGrammarGenerator.graphGrammarObject.Graph;
import com.tutor.parser.model.graphParser.GraphGrammarGenerator.graphGrammarObject.ProductionRule;
import com.tutor.parser.model.graphParser.GraphGrammarGenerator.graphGrammarObject.RuleOperations;
import com.tutor.parser.model.graphicalPOJOObject.line.HorizontalLine;
import com.tutor.parser.model.util.DiagramType;
import com.tutor.parser.model.util.SpatialRelation;
import com.tutor.parser.model.graphicalPOJOObject.GraphicalImageComponent;
import com.tutor.parser.model.util.ObjectType;
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
                        // only applied for numberline.this is used to identify marked line.
                        checkThickLine(relation,objectList.get(i));
                        relations[i][j] = relation;
                    }
                    else {
                        // only applied for numberline.this is used to identify marked line.
                        checkThickLine(relations[i][j],objectList.get(i));
                        relations[i][j].add(SpatialRelation.SAME);
                    }
                } else {
                    // get the existing spatial relationship between 2 objects
                    ArrayList<SpatialRelation> relation =   identifySpatialRelation(objectList.get(i),objectList.get(j));

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
                    //



                    //Check the relations array contains UP or DOWN relations
                    if(relation.contains(SpatialRelation.UP) || relation.contains(SpatialRelation.DOWN) ) {
                        relations[i][j] = relation;

                        if(relation.contains(SpatialRelation.UP)) {
                            if(relations[j][i] == null) {
                                relations[j][i] = new ArrayList<>();
                            }
                            for (SpatialRelation s: relation ) {
                                if(s!= SpatialRelation.UP) {
                                    relations[j][i].add(s);
                                } else {
                                    relations[j][i].add(SpatialRelation.DOWN);
                                }
                            }
                        } else {
                            if(relations[j][i] == null) {
                                relations[j][i] = new ArrayList<>();
                            }
                            for (SpatialRelation s: relation ) {
                                if(s!= SpatialRelation.DOWN) {
                                    relations[j][i].add(s);
                                } else {
                                    relations[j][i].add(SpatialRelation.UP);
                                }
                            }
                        }
                    }
                    else {
                        relations[i][j] = relation;
                        relations[j][i] = relation;
                    }
                }
            }
        }
        return relations;
    }


    public void checkThickLine(ArrayList<SpatialRelation> relations,GraphicalImageComponent obj){
        if(obj.objectType==ObjectType.HORIZONTAL_LINE){
            if(((HorizontalLine)obj).getStroke_width()>=5){
                relations.add(SpatialRelation.THICK_LINE);
            }else {
                relations.add(SpatialRelation.NOT_THICK_LINE);
            }
        }
    }

    public  ArrayList<SpatialRelation> identifySpatialRelation(GraphicalImageComponent o1,GraphicalImageComponent o2){

        ArrayList<SpatialRelation> relations = new ArrayList<>();
        //identify touch
        if(isEndPointTouch(o1,o2)){
            relations.add(SpatialRelation.TOUCH);
        }
        else if(isLineTouch(o1,o2)) {
            relations.add(SpatialRelation.TOUCH);
        }
        else if(isAnyLineTouch(o1,o2)){
            relations.add(SpatialRelation.TOUCH);
        }
        // For Rectangle
        if(isOnTheLine(o1,o2)){
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
        if(isLong(o1,o2)){
            relations.add(SpatialRelation.SHORT);
        }
        if(isPerpendicular(o1,o2)){
            relations.add(SpatialRelation.PERPENDICULAR);
        }

        if(isUp(o1,o2)){
            relations.add(SpatialRelation.UP);
        }else {
            relations.add(SpatialRelation.DOWN);
        }


        return relations;
    }

    public boolean isEndPointTouch(GraphicalImageComponent o1,GraphicalImageComponent o2){

            if (!(o1.objectType == ObjectType.RECTANGLE || o2.objectType == ObjectType.RECTANGLE)&&((o1.superObjectType != ObjectType.LINE && o2.superObjectType != ObjectType.LINE && (isCloseToTouch(o1.getX(), o2.getX()) && isCloseToTouch(o1.getY(), o2.getY())))
                    || (o1.objectType != ObjectType.CIRCLE && o2.objectType != ObjectType.CIRCLE && (isCloseToTouch(o1.getX1(), o2.getX1()) && isCloseToTouch(o1.getY1(), o2.getY1())))
                    || (o1.objectType != ObjectType.CIRCLE && o2.objectType != ObjectType.CIRCLE && (isCloseToTouch(o1.getX2(), o2.getX2()) && isCloseToTouch(o1.getY2(), o2.getY2())))
                    || (o1.objectType != ObjectType.CIRCLE && o2.objectType != ObjectType.CIRCLE && (isCloseToTouch(o1.getX1(), o2.getX2()) && isCloseToTouch(o1.getY1(), o2.getY2())))
                    || (o1.objectType != ObjectType.CIRCLE && o2.objectType != ObjectType.CIRCLE && (isCloseToTouch(o1.getX2(), o2.getX1()) && isCloseToTouch(o1.getY2(), o2.getY1())))
                    || ( (o1.superObjectType != ObjectType.LINE && o2.objectType != ObjectType.CIRCLE) && ( (isCloseToTouch(o1.getX(), o2.getX1()) && isCloseToTouch(o1.getY(), o2.getY1())) || (isCloseToTouch(o1.getX(), o2.getX2()) && isCloseToTouch(o1.getY(), o2.getY2()))))
                    || (o1.objectType != ObjectType.CIRCLE && o2.superObjectType != ObjectType.LINE && ((isCloseToTouch(o1.getX1(), o2.getX()) && isCloseToTouch(o1.getY1(), o2.getY())) || (isCloseToTouch(o1.getX2(), o2.getX()) && isCloseToTouch(o1.getY2(), o2.getY())))))
                    ) {
                return true;
            }


        return false;
    }
    public boolean isPerpendicular(GraphicalImageComponent o1,GraphicalImageComponent o2){
        if(o1.objectType== ObjectType.HORIZONTAL_LINE && o2.objectType==ObjectType.VERTICAL_LINE){
            double y1=o1.getY1();
            double y2=o1.getY2();
            double x1=o2.getX1();
            double x2=o2.getX2();
            if(y1==y2 && x1==x2){
                return true;
            }


        }else if(o1.objectType== ObjectType.VERTICAL_LINE&& o2.objectType==ObjectType.HORIZONTAL_LINE){
            double x1=o1.getX1();
            double x2=o1.getX2();
            double y1=o2.getY1();
            double y2=o2.getY2();
            if(x1==x2 && y1==y2){
                return true;
            }

        }
        return false;
    }


    public boolean isLong(GraphicalImageComponent o1,GraphicalImageComponent o2){
        if(o1.objectType== ObjectType.HORIZONTAL_LINE && o2.objectType==ObjectType.VERTICAL_LINE){
            double x1=o1.getX1();
            double x2=o1.getX2();
            double y1=o2.getY1();
            double y2=o2.getY2();
            double t1=Math.abs(x2-x1);
            double t2=Math.abs(y2-y1);
            if((t1>t2) && ((t1/t2)> 8 )){
                return true;
            }else if((t1< t2) && ((t2/t1)> 8 )){
                return true;
            }
        }else if(o1.objectType== ObjectType.VERTICAL_LINE&& o2.objectType==ObjectType.HORIZONTAL_LINE){
            double x1=o2.getX1();
            double x2=o2.getX2();
            double y1=o1.getY1();
            double y2=o1.getY2();
            double t1=Math.abs(x2-x1);
            double t2=Math.abs(y2-y1);

            if((t1>t2) && ((t1/t2)> 8 )){
                return true;
            }else if((t1< t2) && ((t2/t1)> 8 )){
                return true;
            }
        }
          return false;
    }

    public boolean isLineTouch(GraphicalImageComponent o1,GraphicalImageComponent o2){

        if(o1.objectType== ObjectType.HORIZONTAL_LINE && o2.objectType==ObjectType.VERTICAL_LINE){
            double x1=o1.getX1();
            double x2=o1.getX2();
            double x=o2.getX1();

            if(x1>x2){
                if(((x-3) <= x1 ) && (x1<= x+3) ){
                    return true;
                }
                return false;
            }
            else {
                if(((x-3) <= x2 ) && (x2<= x+3) ){
                    return true;
                }
                return false;
            }

        }
        return false;
    }

    public boolean isAnyLineTouch(GraphicalImageComponent o1,GraphicalImageComponent o2){
    boolean value = false;
        if(o1.objectType== ObjectType.LINE && o2.objectType==ObjectType.LINE) {
            double x11 = o1.getX1();
            double x12 = o1.getX2();
            double x21 = o2.getX1();
            double x22 = o2.getX2();
            double y11 = o1.getY1();
            double y12 = o1.getY2();
            double y21 = o2.getY1();
            double y22 = o2.getY2();

            if ((findMagnitude(x11, x12, x21, y11, y12, y21) <= 0.5) || (findMagnitude(x11, x12, x22, y11, y12, y22) <= 0.5) ||
                    (findMagnitude(x11, x21, x22, y11, y21, y22) <= 0.5) || (findMagnitude(x12, x21, x22, y12, y21, y21) <= 0.5)) {
                value = true;
            }
        }

        return value;

    }

    private double findMagnitude(double x1, double x2, double x3,double y1, double y2, double y3) {
        double magnitude= Math.abs(((y2-y1)/(x2-x1))-((y3-y1)/(x3-x1)));
        return  magnitude;
    }


    public boolean isOnTheLine(GraphicalImageComponent o1,GraphicalImageComponent o2){

        if(o1.objectType== ObjectType.HORIZONTAL_LINE && o2.objectType==ObjectType.RECTANGLE ){
            double x1=o1.getX1();
            double y1=o1.getY1();
            double x2=o1.getX2();
            double x3=o2.getX();
            double y3=o2.getY();
            double h=o2.getH();
            double t =y3+h;
            // Check whether the bars are inside the horizontal line
            if(((x1<x3 && x3<x2)||(x2<x3 && x3<x1))&&(isCloseToTouchRectangle(y1,t))){
                return true;

            }
        }else if(o2.objectType== ObjectType.HORIZONTAL_LINE && o1.objectType==ObjectType.RECTANGLE){
            double x1=o2.getX1();
            double y1=o2.getY1();
            double x2=o2.getX2();
            double x3=o1.getX();
            double y3=o1.getY();
            double h=o1.getH();
            double t =y3+h;
            // Check whether the bars are inside the horizontal line
            if(((x1<x3 && x3<x2)||(x2<x3 && x3<x1))&&(isCloseToTouchRectangle(y1,t))){
                return true;

            }

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
                    if((o2_lowest-o1_lowest)<=15){
                        return true;
                    }

                }
                else if(o1_lowest >= o2_lowest){
                    if( (o1_lowest- o2_lowest)<=15){
                        return true;
                    }
                }
                if (o1_highest>=o2_highest){
                    if( (o1_highest-o2_highest)<=15){
                        return true;
                    }

                }
                else if(o1_highest <=o2_highest){
                    if( (o2_highest-o1_highest)<=15){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean isUp(GraphicalImageComponent o1,GraphicalImageComponent o2){
        if( (o1.superObjectType!= ObjectType.LINE && o2.superObjectType!=ObjectType.LINE && ( o1.getY() >= o2.getY()))
                || (o1.superObjectType == ObjectType.LINE && o2.superObjectType == ObjectType.LINE  && (getAverageValue(o1.getY1(),o1.getY2()) >= getAverageValue(o2.getY1(),o2.getY2()))) )   {
            return true;
        }
        return false;
    }

    public boolean pointOnLineSegment(double x1,double x2,double y1,double y2, double x,double y){

        double m1 = ((y1-y)/(x1-x));
        double m2 = ((y1-y2)/(x1-x2));

        if(m1==m2){
            if(((x1<=x )&& (x <= x2))||((x1>=x)&&(x2<=x))){
                if(((y1<=y) && (y <= y2))||((y1>=y)&&(y2<=y))){
                    return true;
                }
            }
        }else {
             if(y1==y2) {
                if(((y1+5)>=y ) && ((y1-5)<=y)){
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
        //need to consider rule operation
        ArrayList<SpatialRelation>[][] old = host.getRelations();
        ArrayList<SpatialRelation>[] change = old[redex[0]];
        ArrayList<SpatialRelation>[][] newRelations =
                new ArrayList[host.getGraphicalImageComponents().size()][host.getGraphicalImageComponents().size()];


        switch (diagramType){

            case NUMBRELINE:

                if(rule.getRuleId() == 3){
                    int newItr1 =0;
                    for(int oldItr = 0;oldItr < change.length; oldItr++){
                        ArrayList<SpatialRelation>[] substitute;
                        if(oldItr==redex[0]){
                            substitute = old[redex[1]];
                        }
                        else {
                            substitute = old[oldItr];
                        }
                        if(!isInArray(Arrays.copyOfRange(redex,1,redex.length),oldItr)){
                            newRelations[newItr1] = buildSubstituteArray(substitute,redex);
                            newItr1++;
                        }
                    }
                }
                else {
                    int newItr =0;
                    for(int oldItr = 0;oldItr < change.length; oldItr++){
                        ArrayList<SpatialRelation>[] substitute = old[oldItr];
                        if(!isInArray(Arrays.copyOfRange(redex,1,redex.length),oldItr)){
                            newRelations[newItr] = buildSubstituteArray(substitute,redex);
                            newItr++;
                        }
                    }

                }
                host.setRelations(newRelations);
                break;

            case HISTOGRAM:

                if(rule.getRuleId()==0){
                   int newItr1 =0;
                   for(int oldItr = 0;oldItr < change.length; oldItr++){
                       ArrayList<SpatialRelation>[] substitute;
                       if(oldItr==0){
                          substitute = old[redex[1]];
                       }
                       else {
                          substitute = old[oldItr];
                       }
                       if(!isInArray(Arrays.copyOfRange(redex,1,redex.length),oldItr)){
                           newRelations[newItr1] = buildSubstituteArray(substitute,redex);
                           newItr1++;
                       }
                   }
                }
                else {
                    int newItr1 =0;
                    for(int oldItr = 0;oldItr < change.length; oldItr++){
                        ArrayList<SpatialRelation>[] substitute = old[oldItr];
                        if(!isInArray(Arrays.copyOfRange(redex,1,redex.length),oldItr)){
                            newRelations[newItr1] = buildSubstituteArray(substitute,redex);
                            newItr1++;
                        }
                    }
                }

                host.setRelations(newRelations);
                break;

            case TREEDIAGRAM:
                host.setRelations(getNewTreeRelArray(newRelations, host, change,old, redex));
                break;

            case TRIGNOMETRICDIAGRAM:
                int newItrTrig =0;
                for(int oldItr = 0;oldItr < change.length; oldItr++){
                    ArrayList<SpatialRelation>[] substitute = old[oldItr];

                    if(!isInArray(Arrays.copyOfRange(redex,1,redex.length),oldItr)){
                        if(oldItr == redex[0]){
                            if(redex.length!=1) {
                                for (int j = 0; j < substitute.length; j++) {
                                    for (ArrayList<SpatialRelation> relationList : old[redex[1]]) {
                                        for (SpatialRelation relation : relationList) {
                                            substitute[j].add(relation);
                                        }
                                    }
                                }
                            }

                        }
                        newRelations[newItrTrig] = buildSubstituteArrayTrig(substitute,redex);
                        newItrTrig++;
                    }

                }
                host.setRelations(newRelations);
                break;
        }

    }

    private static ArrayList<SpatialRelation>[] buildSubstituteArrayTrig(ArrayList<SpatialRelation>[] substitute, int[] redex) {
        int size = substitute.length - redex.length + 1;

        if (size == 0) {
            return substitute;
        }

        ArrayList<SpatialRelation>[] newSubstitute = new ArrayList[size];
        int itrNew = 0;
        for (int i = 0; i < substitute.length; i++) {

            if(!isInArray(Arrays.copyOfRange(redex,1,redex.length),i)) {
                if (i == redex[0]) {

                    ArrayList<SpatialRelation> substituteRelation = null;
                    substituteRelation = substitute[i];

                    if (redex.length != 1) {
                        if (substitute[redex[1]].contains(SpatialRelation.TOUCH)) {
                            substituteRelation.add(SpatialRelation.TOUCH);
                        }
                        if (substitute[redex[1]].contains(SpatialRelation.OVERLAP)) {
                            substituteRelation.add(SpatialRelation.OVERLAP);
                        }
                        if (substitute[redex[1]].contains(SpatialRelation.SAME)) {
                            substituteRelation.add(SpatialRelation.SAME);
                        }
                    }
                    newSubstitute[itrNew] = substituteRelation;
                    itrNew++;
                } else if ((!isInArray(redex, i))) {
                    newSubstitute[itrNew] = substitute[i];
                    itrNew++;
                }
            }

        }
        return newSubstitute;
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

    public boolean isCloseToTouch(double p,double q){
        if((p<= q + 20 ) && (p>(q - 20))){
            return true;
        }
        return false;
    }
    public boolean isCloseToTouchRectangle(double y1,double t){
        if((y1<= t + 50 ) && (y1>(t- 20))){
            return true;
        }
        return false;
    }


    public double getAverageValue(double y1, double y2) {
        return (y1+y2)/2;
    }

    public static SpatialRelation identifySpecificSpatialRelation(GraphicalImageComponent o1,GraphicalImageComponent o2){
        if(specificCheckUp(o1,o2)){
            return SpatialRelation.UP;
        }else {
            return SpatialRelation.DOWN;
        }
    }

    public static boolean specificCheckUp(GraphicalImageComponent o1,GraphicalImageComponent o2){
        if((o1.getY1()+o1.getY2())/2 >= (o2.getY1()+o2.getY2())/2) {
            return true;
        }
        return false;
    }


    public static ArrayList<SpatialRelation>[][] getNewTreeRelArray(ArrayList<SpatialRelation>[][] newRelations, Graph host,
                                                                    ArrayList<SpatialRelation>[] change, ArrayList<SpatialRelation>[][] old,
                                                                    int[] redex) {

        //Iterate through old host graph
        int hostGraphComponentSize = host.getGraphicalImageComponents().size();
        int row = 0;
        for (int itr = 0; itr < change.length; itr++) {
            if (itr == redex[0]) {
                GraphicalImageComponent graphicalImageComponent = host.getGraphicalImageComponents().get(itr);
                // Set Up or Down relationship with newly added graph element with existing elements
                for (int j = 0; j < hostGraphComponentSize; j++) {

                    if (j == itr) {
                        newRelations[itr][itr] = new ArrayList<>();
                        newRelations[itr][itr].add(SpatialRelation.SAME);
                    } else {
                        newRelations[itr][j] = new ArrayList<>();
                        SpatialRelation spatialRelationNew = identifySpecificSpatialRelation(graphicalImageComponent, host.getGraphicalImageComponents().get(j));
                        newRelations[itr][j].add(spatialRelationNew);


                        if (spatialRelationNew.equals(SpatialRelation.UP)) {
                            if (newRelations[j][itr] == null) {
                                newRelations[j][itr] = new ArrayList<>();
                            }
                            newRelations[j][itr].add(SpatialRelation.DOWN);

                        } else {
                            if (newRelations[j][itr] == null) {
                                newRelations[j][itr] = new ArrayList<>();
                            }
                            newRelations[j][itr].add(SpatialRelation.UP);
                        }
                    }

                }
                row++;
            } else if (redex.length != 1) {
                if (itr != redex[1]) {
                    //Get old relationship list
                    ArrayList<SpatialRelation>[] oldRelationList = old[itr];
                    //To keep the new host relation iteration
                    int count = 0;
                    //To assign each relation with other objects according to the old relation list
                    for (int k = 0; k < oldRelationList.length; k++) {
                        // Skip when relation is already define during adding new relation between newly added element and other elements
                        if (k != redex[0]) {
                            //Skip relation if the object is removed when reducing host graph
                            if (redex.length != 1){
                                if (k != redex[1]) {
                                newRelations[row][count] = oldRelationList[k];
                                count++;
                            }
                            }
                        } else {
                            count++;
                        }
                    }
                    row++;
                }
            } else {
                //Get old relationship list
                ArrayList<SpatialRelation>[] oldRelationList = old[itr];
                //To keep the new host relation iteration
                int count = 0;
                //To assign each relation with other objects according to the old relation list
                for (int k = 0; k < oldRelationList.length; k++) {
                    // Skip when relation is already define during adding new relation between newly added element and other elements
                    if (k != redex[0]) {
                        newRelations[row][count] = oldRelationList[k];
                        count++;

                    } else {
                        count++;
                    }
                }
                row++;
            }
        }

        return newRelations;
    }

}
