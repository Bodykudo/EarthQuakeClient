package Sorters;

import java.util.*;
import Tools.*;

public class DistanceComparator implements Comparator<QuakeEntry> {
  private Location fromWhere;
  private int asc;

  public DistanceComparator(Location where, boolean ascending) {
    fromWhere = where;
    asc = ascending ? 1 : -1;
  }

  public int compare(QuakeEntry q1, QuakeEntry q2) {
    double dist1 = q1.getLocation().distanceTo(fromWhere);
    double dist2 = q2.getLocation().distanceTo(fromWhere);
    return Double.compare(dist1, dist2) * asc;
  }

}
