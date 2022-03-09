package Sorters;

import java.util.*;
import Tools.*;

public class MagnitudeComparator implements Comparator<QuakeEntry> {
  private int asc;

  public MagnitudeComparator(boolean ascending) {
    asc = ascending ? 1 : -1;
  }

  public int compare(QuakeEntry q1, QuakeEntry q2) {
    return Double.compare(q1.getMagnitude(), q2.getMagnitude()) * asc;
  }
}
