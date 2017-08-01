package com.tutor.controller.preprocessor;

import com.tutor.model.graphicalPOJOObject.GraphicalImageComponent;

import java.lang.reflect.Array;
import java.util.*;

import static org.eclipse.persistence.config.TargetDatabase.Database;

/**
 * Created by Madhavi Ruwandika on 6/11/2017.
 */
public class ObjectSequenceOrderGenerator {


    private HashMap<Double,HashMap<Double,List<GraphicalImageComponent>>> partitiallyOrderedList;
    private List<GraphicalImageComponent> orderedList;

    public ObjectSequenceOrderGenerator() {
        partitiallyOrderedList = new HashMap<>();
        orderedList = new ArrayList<>();
    }

    public void order(List<GraphicalImageComponent> unOderedList){

        for(int i=0;i<unOderedList.size();i++){
            if(partitiallyOrderedList.containsKey(unOderedList.get(i).getLowerestXCoordinate())){
                HashMap<Double,List<GraphicalImageComponent>> map =
                        partitiallyOrderedList.get(unOderedList.get(i).getLowerestXCoordinate());


                if(map.containsKey(unOderedList.get(i).getLowerestYCoordinate())){
                    List<GraphicalImageComponent> objects = map.get(unOderedList.get(i).getLowerestYCoordinate());
                    objects.add(unOderedList.get(i));
                    map.put(unOderedList.get(i).getLowerestYCoordinate(),objects);
                }else {
                    List<GraphicalImageComponent> objects = new ArrayList<>();
                    objects.add(unOderedList.get(i));
                    map.put(unOderedList.get(i).getLowerestYCoordinate(),objects);
                }

                partitiallyOrderedList.remove(unOderedList.get(i).getLowerestXCoordinate());
                partitiallyOrderedList.put(unOderedList.get(i).getLowerestXCoordinate(),map);
            }
            else {
                HashMap<Double,List<GraphicalImageComponent>> map = new HashMap<>();
                List<GraphicalImageComponent> objects = new ArrayList<>();
                objects.add(unOderedList.get(i));
                map.put(unOderedList.get(i).getLowerestYCoordinate(),objects);

                partitiallyOrderedList.put(unOderedList.get(i).getLowerestXCoordinate(),map);
            }
        }


       Set<Double> keys = partitiallyOrderedList.keySet();

       Double[] array = new Double[keys.size()];
       Iterator<Double> itr = keys.iterator();
       int i=0;
       while (itr.hasNext()){
           array[i] = itr.next();
           i++;
       }
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
           Arrays.sort(array_2);
           for(int y=0;y < array_2.length ;y++){
               List<GraphicalImageComponent> objects = map.get(array_2[y]);

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

    }

    public List<GraphicalImageComponent> getOrderedList() {
        return orderedList;
    }
}
