package Tools;

import java.util.*;

public class ReadQuakes {
  private String source;
  private ArrayList<QuakeEntry> list;

  public ReadQuakes(String src) {
    EarthQuakeParser parser = new EarthQuakeParser();
    source = src;
    list = parser.read(source);
    System.out.println("read data for " + list.size() + " quakes");
  }

  public ArrayList<QuakeEntry> getQuakes() {
    return list;
  }
}
