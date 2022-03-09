
/*
  EarthQuakeClient is a tool to do basic operations on a set of quakes
  to call the clicent
  EarthQuakeClient client = new EarthQuakeClient(source)
  source is the source of the quake data - for example "data/nov20quakedata.atom"

  To return an ArrayList of all quakes:
  client.getQuakes();

  To print a number of quakes:
  client.printQuakes(ArrayList<QuakeEntry> quakeData, int number)

  // An example
  ArrayList<QuakeEntry> quakesList = client.getQuakes();
  client.printQuakes(quakesList, 5);

  To return an ArrayList of filtered quakes:
  client.filterQuakes(ArrayList<QuakeEntry> quakeData, Filter filter)
  Where quakeData is an ArrayList of quakes, filter is the used filter
  Current filters are:
  MagnitudeFilter(double minValue, double maxValue, String filterName)
  MinMagFilter(double minValue, String filterName)
  DepthFilter(double minValue, double maxValue, String filterName)
  DistanceFilter(Location location, double maxValue, String filterName)
  "ALl VALUES ARE IN METERS"
  PhraseFilter(String type, String phrase, String filterName) -type can be "starts", "ends", "any"

  An example for calling DistanceFilter:
  Location loc = new Location(35.9886, -78.9072)
  ArrayList<QuakeEntry> quakesList = client.getQuakes();
  ArrayList<QuakeEntry> filteredList = client.filterQuakes(quakesList, new DistanceFilter(loc, 1000000, "ClosestQuakes"));

  MatchAllFilter() can be used to have more than one filter
  MatchAllFilter.add(Filter filter)

  An example:
  ArrayList<QuakeEntry> quakesList = client.getQuakes();
  MatchAllFilter multiFilters = new MatchAllFilter();
  multiFilters.addFilter(new MinMagFilter(3.0, "Magnitude"));
  multiFilters.addFilter(new PhraseFilter("ends", "Japan", "Phrase"));
  ArrayList<QuakeEntry> filteredList = client.filterQuakes(quakesList, multiFilters);

  To sort an ArrayList of quakes:
  Collections.sort(ArrayList<QuakeEntry> quakeData, Comparator compare)
  Where default Comparator is to sort by magnitude and depth in an ascending order
  Current Comparator are:
  MagnitudeComparator(boolean ascending);
  DistanceComparator(Location location, boolean ascending);
  TitleAndDepthComparator(boolean ascending);
  TitleLastAndMagnitudeComparator(boolean ascending);

  An example for sorting quakes:
  ArrayList<QuakeEntry> quakesList = client.getQuakes();
  Location loc = new Location(35.9886, -78.9072)
  Collections.sort(quakesList, new DistanceComparator(loc, true));

  Or you can use built in methods:
  client.sortDefault(ArrayList<QuakeEntry> quakeData); --This sorts according to magnitude and depth in an ascending order
  client.sortByMagnitude(ArrayList<QuakeEntry> quakeData, boolean ascending);
  client.sortByDistance(ArrayList<QuakeEntry> quakeData, Location loc, boolean ascending);
  client.sortByTitleAndDepth(ArrayList<QuakeEntry> quakeData, boolean ascending);
  client.sortByLastWordInTitleThenByMagnitude(ArrayList<QuakeEntry> quakeData, boolean ascending)

  An example:
  ArrayList<QuakeEntry> quakesList = client.getQuakes();
  client.sortByTitleAndDepth(quakesList, false);
*/

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
