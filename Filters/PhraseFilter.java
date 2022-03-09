package Filters;

import Tools.*;

public class PhraseFilter implements Filter {
  private String type;
  private String phrase;
  private String filterName;

  public PhraseFilter(String type, String phrase, String name) {
    this.type = type;
    this.phrase = phrase;
    filterName = name;
  }

  public boolean satisfies(QuakeEntry qe) {
    if (type.equals("starts")) {
      if (qe.getInfo().startsWith(phrase)) {
        return true;
      }
    } else if (type.equals("ends")) {
      if (qe.getInfo().endsWith(phrase)) {
        return true;
      }
    } else if (type.equals("any")) {
      if (qe.getInfo().contains(phrase)) {
        return true;
      }
    }
    return false;
  }

  public String getName() {
    return filterName;
  }
}
