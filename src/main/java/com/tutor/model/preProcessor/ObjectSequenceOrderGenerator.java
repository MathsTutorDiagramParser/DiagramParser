package com.tutor.model.preProcessor;

import com.tutor.model.graphicalPOJOObject.GraphicalImageComponent;

import java.lang.reflect.Array;
import java.util.*;

import static org.eclipse.persistence.config.TargetDatabase.Database;

/**
 * Created by Madhavi Ruwandika on 6/11/2017.
 */
public class ObjectSequenceOrderGenerator {


    private HashMap<Double,HashMap<Double,List<GraphicalImageComponent>>> partitiallyOrderedList;
    private List<GraphicalImageComponent> orderedList ;

    public ObjectSequenceOrderGenerator() {

    }

    private  List<GraphicalImageComponent> order(List<GraphicalImageComponent> unOderedList){

        orderedList = new ArrayList<>();
        partitiallyOrderedList = new HashMap<>();
        /*
            Fill hash map structure with svg objects
         */
        for(int i=0;i<unOderedList.size();i++){

            // if x coordinate is previously added to the hash map then update the internal hash map with respect to the same key
            if(partitiallyOrderedList.containsKey(unOderedList.get(i).getLowerestXCoordinate())){
                HashMap<Double,List<GraphicalImageComponent>> map =
                        partitiallyOrderedList.get(unOderedList.get(i).getLowerestXCoordinate());
                // add element in to hash map which is represented by lowest y coordinate
                if(map.containsKey(unOderedList.get(i).getLowerestYCoordinate())){
                    List<GraphicalImageComponent> objects = map.get(unOderedList.get(i).getLowerestYCoordinate());
                    objects.add(unOderedList.get(i));
                    map.put(unOderedList.get(i).getLowerestYCoordinate(),objects);
                }else {
                    List<GraphicalImageComponent> objects = new ArrayList<>();
                    objects.add(unOderedList.get(i));
                    map.put(unOderedList.get(i).getLowerestYCoordinate(), objects);
                }
                partitiallyOrderedList.remove(unOderedList.get(i).getLowerestXCoordinate());
                // update with new hash map which contain new element
                partitiallyOrderedList.put(unOderedList.get(i).getLowerestXCoordinate(),map);
            }
            else {
                //if hash map does not contain the x coordinate as key of the hash map
                HashMap<Double,List<GraphicalImageComponent>> map = new HashMap<>();
                List<GraphicalImageComponent> objects = new ArrayList<>();
                objects.add(unOderedList.get(i));
                map.put(unOderedList.get(i).getLowerestYCoordinate(),objects);
                partitiallyOrderedList.put(unOderedList.get(i).getLowerestXCoordinate(),map);
            }
        }

        /*
        * after filling the element in to has map structure following is the code for ordering object.
        */
       Set<Double> keys = partitiallyOrderedList.keySet();

       Double[] array = new Double[keys.size()];
       Iterator<Double> itr = keys.iterator();
       int i=0;
       while (itr.hasNext()){
           array[i] = itr.next();
           i++;
       }
        // Ordering with verticle sweep line (considering x coordinates)
       Arrays.sort(array);


       for(int j=0;j<array.length;j++){

           HashMap<Double,List<GraphicalImageComponent>> map = partitiallyOrderedList.get(array[j]);
           Set<Double> key_2 = map.keySet();
           Double[] array_2 = new Double[key_2.size()];
           Iterator<Double> itr_2 = key_2.iterator();
           int k=0;
           while (itr_2.hasNext()){
               array_2[k] = itr_2.next();
               k++;
           }
           // Ordering with horizontal sweep line (considering lowest y coordinates)
           Arrays.sort(array_2);
           for(int y=0;y < array_2.length ;y++){
               List<GraphicalImageComponent> objects = map.get(array_2[y]);
               // order element which contain same lowest y coordinate
               if(objects != null) {
                   Collections.sort(objects, new Comparator<GraphicalImageComponent>() {
                       @Override
                       public int compare(GraphicalImageComponent o1, GraphicalImageComponent o2) {
                           return Double.compare(o1.getHieght(), o2.getHieght());
                       }
                   });

                   Collections.reverse(objects);
                   for (int x = 0; x < objects.size(); x++) {
                       orderedList.add(objects.get(x));
                   }
               }
           }
       }

       return orderedList;
    }

    public List<GraphicalImageComponent> getOrderedList(List<GraphicalImageComponent> unOderedList) {

        order(unOderedList);
        System.out.println(orderedList.size());
        return orderedList;
    }
}
