package com.bluu.hdm.web.filter;

import com.bluu.hdm.web.enums.FilterOperationEnum;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class TemplateFilter implements Filters {

    private String name;

    @Override
    public List<FilterField> getFilters() {
        final List<FilterField> filters = new ArrayList<>();
        if (StringUtils.isNotBlank(name)) {
            filters.add(new FilterField("template", FilterOperationEnum.LIKE, name));
        }

        return filters;
    }

    @Override
    public boolean isFilled() {
        return StringUtils.isNotBlank(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
