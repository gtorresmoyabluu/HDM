package com.bluu.hdm.rest.util;

public enum WeekDaysEnum {
    SUNDAY(0),
    MONDAY(1),
    TUESDAY(2),
    WEDNESDAY(3),
    THURSDAY(4),
    FRIDAY(5),
    SATURDAY(6);

    private long numday;

    WeekDaysEnum(long numday) {
        this.numday = numday;
    }

    public long getNumday() {
        return numday;
    }

    public static WeekDaysEnum fromValue(long value) {
        for (WeekDaysEnum item : values()) {
            if (item.getNumday() == value) {
                return item;
            }
        }
        return null;
    }

}
