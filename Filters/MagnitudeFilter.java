package Filters;

import Tools.*;

public class MagnitudeFilter implements Filter {
  private double minMag;
  private double maxMag;
  private String filterName;

  public MagnitudeFilter(double min, double max, String name) {
    minMag = min;
    maxMag = max;
    filterName = name;
  }

  public boolean satisfies(QuakeEntry qe) {
    if (qe.getMagnitude() >= minMag && qe.getMagnitude() <= maxMag) {
      return true;
    } else {
      return false;
    }
  }

  public String getName() {
    return filterName;
  }
}
