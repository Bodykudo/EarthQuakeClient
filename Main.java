import java.util.*;

import Tools.*;
import Filters.*;
import Sorters.*;

public class Main {
  public static void main(String[] args) {
    EarthQuakeClient client = new EarthQuakeClient("data/nov20quakedata.atom");
    ArrayList<QuakeEntry> quakesList = client.getQuakes();

    // Example 1 for printing number of quakes
    int printedNum = 5;
    System.out.println("These are examples for " + printedNum + " different quakes:");
    client.printQuakes(quakesList, 5);

    // Example 2 for filtering quakes near to Durham, NC
    Location loc = new Location(35.9886, -78.9072);
    ArrayList<QuakeEntry> nearbyQuakes = client.filterQuakes(quakesList,
        new DistanceFilter(loc, 1000000, "ClosestQuakes"));
    System.out.println("Quakes that are nearby Durham, NC count: " + nearbyQuakes.size());

    // Example 3 for filtering quakes with Minimum Magnitude 3.0 and that ends with
    // the word Japan
    MatchAllFilter multiFilters = new MatchAllFilter();
    multiFilters.addFilter(new MinMagFilter(3.0, "Magnitude"));
    multiFilters.addFilter(new PhraseFilter("ends", "Japan", "Phrase"));
    ArrayList<QuakeEntry> filteredList = client.filterQuakes(quakesList, multiFilters);
    System.out.println("The filtered quakes count: " + filteredList.size());

    // Example 4 for sorting quakes according to the nearest in an ascending order
    Collections.sort(quakesList, new DistanceComparator(loc, true));
    int num = 10;
    System.out.println("The closest " + num + " quakes are:");
    for (int i = 0; i < num; i++) {
      System.out.println(quakesList.get(i));
    }

    // Example 5 for sorting with a built in method in a descending order
    Collections.sort(quakesList, new TitleAndDepthComparator(false));
    int num2 = 10;
    System.out.println("The result " + num2 + " quakes from sorting are:");
    for (int i = 0; i < num2; i++) {
      System.out.println(quakesList.get(i));
    }
  }
}
