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
public enum MenuChoice {
    
    UNKNOWN("UNKNOWN"),
    PORK("PORK"),
    CHICKEN("CHICKEN"),
    VEGETARIAN("VEGETARIAN");
    
    private final String value;
    
    MenuChoice(String v) {
        value = v;
    }
    
    public String value() {
        return value;
    }

    public static MenuChoice fromValue(String v) {
        for (MenuChoice c: MenuChoice.values()) {
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
