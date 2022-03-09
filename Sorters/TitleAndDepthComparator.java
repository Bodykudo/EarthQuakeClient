package Sorters;

import java.util.*;
import Tools.*;

public class TitleAndDepthComparator implements Comparator<QuakeEntry> {
  private int asc;

  public TitleAndDepthComparator(boolean ascending) {
    asc = ascending ? 1 : -1;
  }

  public int compare(QuakeEntry q1, QuakeEntry q2) {
    if (q1.getInfo().compareTo(q2.getInfo()) < 0) {
      return -1 * asc;
    } else if (q1.getInfo().compareTo(q2.getInfo()) > 0) {
      return 1 * asc;
    } else {
      return Double.compare(q1.getDepth(), q2.getDepth()) * asc;
    }
  }
}
