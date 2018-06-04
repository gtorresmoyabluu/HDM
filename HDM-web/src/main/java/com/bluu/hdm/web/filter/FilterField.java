package com.bluu.hdm.web.filter;

import com.bluu.hdm.web.enums.FilterOperationEnum;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class FilterField {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    // Condición AND
    private String name;
    private FilterOperationEnum operation;

    // Lista de condiciones OR
    private final List<FilterField> orConditions;
    private Object value;

    public FilterField(List<FilterField> orConditions) {
        this.orConditions = orConditions;
        name = null;
        operation = null;
        value = null;
    }

    public FilterField(String name, FilterOperationEnum operation, Object value) {
        this.name = name;
        this.operation = operation;
        this.value = value;
        orConditions = null;
    }

    public String getName() {
        return name;
    }

    public FilterOperationEnum getOperation() {
        return operation;
    }

    public Object getValue() {
        return value;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOperation(FilterOperationEnum operation) {
        this.operation = operation;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    public String toString() {
        if (orConditions == null) {
            // Se trata de una condición "normal" que será añadida como un AND en la consulta
            String rightSide = null;
            if (value instanceof String) {
                rightSide = (String) value;
            } else if (value instanceof Number) {
                rightSide = value.toString();
            } else if (value.getClass().isEnum()) {
                rightSide = ((Enum<?>) value).name();
            } else if (value instanceof Date) {
                rightSide = sdf.format(value);
            } else
                rightSide = value.toString();

            if (StringUtils.isBlank(rightSide)) {
                return null;
            }

            // Left side. LIKE is case insensitive. Others are case sensitive
            String leftSide;
            if (operation == FilterOperationEnum.LIKE || operation == FilterOperationEnum.LIKE_LEFT
                || operation == FilterOperationEnum.LIKE_RIGHT) {
                leftSide = "lower(" + name + ")";
            } else if (value instanceof Date) {
                leftSide = name;
            } else {
                leftSide = name;
            }

            if (StringUtils.isBlank(leftSide)) {
                return null;
            }

            String result = leftSide;
            switch (operation) {
                case EQ:
                    result += " = ";
                    if ((value instanceof String) || (value instanceof Date) || value.getClass().isEnum()) {
                        result += "'" + rightSide + "'";
                    } else {
                        result += rightSide;
                    }
                    break;
                case LIKE:
                    result += " like '%" + rightSide.toLowerCase() + "%'";
                    break;
                case LIKE_LEFT:
                    result += " like '%" + rightSide.toLowerCase() + "'";
                    break;
                case LIKE_RIGHT:
                    result += " like '" + rightSide.toLowerCase() + "%'";
                    break;
                case GT:
                    if (value instanceof Date) {
                        result += " >= STR_TO_DATE('" + rightSide + "','%d/%m/%Y %H:%i:%s')"; // MYSQL VERSION
                        // result += " >= TO_DATE('" + rightSide + "','DD/MM/YYYY HH24:MI:SS')"; //ORACLE & POSTGREE VERSION
                    }
                    break;
                case LT:
                    if (value instanceof Date) {
                        result += " <= STR_TO_DATE('" + rightSide + "','%d/%m/%Y %H:%i:%s')"; // MYSQL VERSION
                        // result += " <= TO_DATE('" + rightSide + "','DD/MM/YYYY HH24:MI:SS')"; //ORACLE & POSTGREE VERSION
                    }
                    break;
            }

            return result;

        } else {
            // Se trata de una condición agrupada OR que será añadida como una condición AND en la consulta
            String result = "(";

            for (int i = 0; i < orConditions.size(); i++) {
                if (i > 0) {
                    result += " OR ";
                }

                result += orConditions.get(i).toString();
            }

            result += ")";

            return result;
        }
    }
}
