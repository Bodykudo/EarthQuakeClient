package Filters;

import java.util.*;
import Tools.*;

public class MatchAllFilter implements Filter {
  private ArrayList<Filter> filters;

  public MatchAllFilter() {
    filters = new ArrayList<Filter>();
  }

  public void addFilter(Filter f) {
    filters.add(f);
  }

  public boolean satisfies(QuakeEntry qe) {
    for (Filter f : filters) {
      if (!f.satisfies(qe)) {
        return false;
      }
    }
    return true;
  }

  public String getName() {
    StringBuilder filterName = new StringBuilder("");
    int count = 0;
    for (Filter f : filters) {
      if (count == filters.size() - 1) {
        filterName.append(f.getName());
      } else {
        filterName.append(f.getName() + ", ");
      }
      count++;
    }
    return filterName.toString();
  }
}
