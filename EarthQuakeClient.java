import java.util.*;
import Tools.*;
import Filters.*;
import Sorters.*;

public class EarthQuakeClient {
  ReadQuakes quakes;

  public EarthQuakeClient(String source) {
    quakes = new ReadQuakes(source);
  }

  public ArrayList<QuakeEntry> getQuakes() {
    return quakes.getQuakes();
  }

  public ArrayList<QuakeEntry> filterQuakes(ArrayList<QuakeEntry> quakeData, Filter f) {
    ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
    for (QuakeEntry qe : quakeData) {
      if (f.satisfies(qe)) {
        answer.add(qe);
      }
    }
    return answer;
  }

  public void sortDefault(ArrayList<QuakeEntry> quakeData) {
    Collections.sort(quakeData);
  }

  public void sortByMagnitude(ArrayList<QuakeEntry> quakeData, boolean ascending) {
    Collections.sort(quakeData, new MagnitudeComparator(ascending));
  }

  public void sortByDistance(ArrayList<QuakeEntry> quakeData, Location loc, boolean ascending) {
    Collections.sort(quakeData, new DistanceComparator(loc, ascending));
  }

  public void sortByTitleAndDepth(ArrayList<QuakeEntry> quakeData, boolean ascending) {
    Collections.sort(quakeData, new TitleAndDepthComparator(ascending));
  }

  public void sortByLastWordInTitleThenByMagnitude(ArrayList<QuakeEntry> quakeData, boolean ascending) {
    Collections.sort(quakeData, new TitleLastAndMagnitudeComparator(ascending));
  }

  public void printQuakes(ArrayList<QuakeEntry> list, int num) {
    for (int i = 0; i < (num >= list.size() ? list.size() : num); i++) {
      QuakeEntry qe = list.get(i);
      Location loc = qe.getLocation();
      System.out.printf("%4.2f, %4.2f, %4.2f, %s\n",
          loc.getLatitude(), loc.getLongitude(),
          qe.getMagnitude(), qe.getInfo());
    }
  }
}