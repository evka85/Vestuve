/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vestuve.model;

/**
 *
 * @author Evaldas Juska <evka85@gmail.com>
 */
public enum GapActivityChoice {
    
    UNKNOWN("UNKNOWN"),
    BUS("BUS"),
    TOUR_VILNIUS("TOUR_VILNIUS"),
    TOUR_PRESIDENT_OFFICE("TOUR_PRESIDENT_OFFICE"),
    FREE_TIME("FREE_TIME");
    
    
    private final String value;
    
    GapActivityChoice(String v) {
        value = v;
    }
    
    public String value() {
        return value;
    }

    public static GapActivityChoice fromValue(String v) {
        for (GapActivityChoice c: GapActivityChoice.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

    public String getValue() {
        return value;
    }
}
