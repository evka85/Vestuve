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
public enum AttendanceState {
    
    UNKNOWN("UNKNOWN"),
    ATTENDING("ATTENDING"),
    NOT_ATTENDING("NOT_ATTENDING");
    
    private final String value;
    
    AttendanceState(String v) {
        value = v;
    }
    
    public String value() {
        return value;
    }

    public static AttendanceState fromValue(String v) {
        for (AttendanceState c: AttendanceState.values()) {
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
