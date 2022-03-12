# EarthQuakeClient: Study quakes easily!

EarthQuakeClient is a tool to do basic operations on a set of quakes

To call the client:
`EarthQuakeClient client = new EarthQuakeClient(source)`

Source is the source of the quake data - for example "data/nov20quakedata.atom"

To return an ArrayList of all quakes:
`client.getQuakes();`

To print a number of quakes:
`client.printQuakes(ArrayList<QuakeEntry> quakeData, int number)`

An example for printing 5 quakes:

`ArrayList<QuakeEntry> quakesList = client.getQuakes();`

`client.printQuakes(quakesList, 5);`

To return an ArrayList of filtered quakes:

`client.filterQuakes(ArrayList<QuakeEntry> quakeData, Filter filter)`

Where quakeData is an ArrayList of quakes, filter is the used filter. Current filters are:
```Java
MagnitudeFilter(double minValue, double maxValue, String filterName)

MinMagFilter(double minValue, String filterName)

DepthFilter(double minValue, double maxValue, String filterName)

DistanceFilter(Location location, double maxValue, String filterName)

// ALl VALUES ARE IN METERS

PhraseFilter(String type, String phrase, String filterName) // Type can be "starts", "ends", "any"
```

An example for calling DistanceFilter:
```Java
Location loc = new Location(35.9886, -78.9072)

ArrayList<QuakeEntry> quakesList = client.getQuakes();

ArrayList<QuakeEntry> filteredList = client.filterQuakes(quakesList, new DistanceFilter(loc, 1000000, "ClosestQuakes"));

```

`MatchAllFilter()` can be used to have more than one filter

`MatchAllFilter.add(Filter filter)`

An example for using MatchAllFilter:
```Java
ArrayList<QuakeEntry> quakesList = client.getQuakes();
MatchAllFilter multiFilters = new MatchAllFilter();
multiFilters.addFilter(new MinMagFilter(3.0, "Magnitude"));
multiFilters.addFilter(new PhraseFilter("ends", "Japan", "Phrase"));
ArrayList<QuakeEntry> filteredList = client.filterQuakes(quakesList, multiFilters);
```

To sort an ArrayList of quakes: 
`Collections.sort(ArrayList<QuakeEntry> quakeData, Comparator compare)`

Where default Comparator is to sort by magnitude and depth in an ascending order, current comparators are:
```Java
MagnitudeComparator(boolean ascending);
DistanceComparator(Location location, boolean ascending);
TitleAndDepthComparator(boolean ascending);
TitleLastAndMagnitudeComparator(boolean ascending);
```

An example for sorting quakes:
```Java
ArrayList<QuakeEntry> quakesList = client.getQuakes();
Location loc = new Location(35.9886, -78.9072)
Collections.sort(quakesList, new DistanceComparator(loc, true));
```

Or you can use built in methods:
```Java
client.sortDefault(ArrayList<QuakeEntry> quakeData); //This sorts according to magnitude and depth in an ascending order
client.sortByMagnitude(ArrayList<QuakeEntry> quakeData, boolean ascending);
client.sortByDistance(ArrayList<QuakeEntry> quakeData, Location loc, boolean ascending);
client.sortByTitleAndDepth(ArrayList<QuakeEntry> quakeData, boolean ascending);
client.sortByLastWordInTitleThenByMagnitude(ArrayList<QuakeEntry> quakeData, boolean ascending)
```

An example:
```Java
ArrayList<QuakeEntry> quakesList = client.getQuakes();
client.sortByTitleAndDepth(quakesList, false);
```
