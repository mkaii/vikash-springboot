import java.util.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {

    private static void printList(List<Integer> myList)
    {
        for(Integer item :  myList)
        {
            //System.out.println(item);
            System.out.print(item + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        System.out.println("Practising collections.....");

        //Arraylist

        //List<Integer> myList = new ArrayList<>();
        List<Integer> myList = new LinkedList<>();
        myList.add(23);
        myList.add(25);
        myList.add(27);
        myList.add(29);
        myList.add(61);

        printList(myList);

        System.out.println("\nSize of arraylist is : " + myList.size());
        System.out.println("List is empty : " + myList.isEmpty());
        System.out.println("list contains 100: " + myList.contains(100));

        System.out.println("Removed " + myList.remove(myList.indexOf(29)) + " from the list: " );

        printList(myList);

        // practising maps using hashMap implementation

        Map<String,Integer> populationMap = new HashMap<>();
        populationMap.put("Stockholm", 9000000);
        populationMap.put("Noida", 2000000);
        populationMap.put("Delhi", 3000000);
        populationMap.put("Telangana", 8000000);

        for (String popKey : populationMap.keySet())
        {
            System.out.println("population of " + popKey + " : "  + populationMap.get(popKey));
        }


    }
}