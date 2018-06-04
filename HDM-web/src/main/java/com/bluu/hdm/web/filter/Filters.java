package com.bluu.hdm.web.filter;

import java.util.List;

public interface Filters {

    boolean isFilled();

    List<FilterField> getFilters();
}
