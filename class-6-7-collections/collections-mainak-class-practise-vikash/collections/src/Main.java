import java.util.*;

public class Main {
    private static void printList(List<Integer> mylist){

        for( int i =0 ; i < mylist.size(); i ++  )
        {
            System.out.println(mylist.get(i) + "");
        }

    }
    public static void main(String[] args) {

        System.out.println("Prictsing collection");

        // Arraylist
       // List<Integer> myList = new ArrayList<>();
        List<Integer> myList = new LinkedList<>();
        myList.add(23);
        myList.add(24);
        myList.add(25);
        myList.add(26);
        myList.add(27);

        printList(myList);

        System.out.println("size of array list is :" + myList.size());
        System.out.println("list is empty :" + myList.isEmpty());
        System.out.println("list contains 40 :" + myList.contains(40));
        //System.out.println( "removed" + myList.remove(2));
        System.out.println( "removed :" + myList.remove(myList.indexOf(25)));


        printList(myList);

        Map<String,Integer> populationByCity = new HashMap<>();
        populationByCity.put("mami",23000);
        populationByCity.put("hyderabad",25000);
        populationByCity.put("stockholm",29000);
        populationByCity.put("mumbai",22000);
      /* for (Map.Entry<String,Integer> set: populationByCity.entrySet()){
           System.out.println(set.getKey() + " = " + set.getValue());
       }*/
        for (String popkey : populationByCity.keySet()){
            System.out.println("population of " + popkey +
                    " : " + populationByCity.get(popkey) );
        }
    }

}