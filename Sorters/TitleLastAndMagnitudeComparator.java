package Sorters;

import java.util.*;
import Tools.*;

public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry> {
  private int asc;

  public TitleLastAndMagnitudeComparator(boolean ascending) {
    asc = ascending ? 1 : -1;
  }

  public int compare(QuakeEntry q1, QuakeEntry q2) {
    String lastWord1 = q1.getInfo().substring(q1.getInfo().lastIndexOf(" ") + 1);
    String lastWord2 = q2.getInfo().substring(q2.getInfo().lastIndexOf(" ") + 1);
    if (lastWord1.compareTo(lastWord2) < 0) {
      return -1 * asc;
    } else if (lastWord1.compareTo(lastWord2) > 0) {
      return 1 * asc;
    } else {
      return Double.compare(q1.getMagnitude(), q2.getMagnitude()) * asc;
    }
  }
}
