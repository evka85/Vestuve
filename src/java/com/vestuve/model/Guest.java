/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vestuve.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author Evaldas Juska <evka85@gmail.com>
 */
@Entity
@Table(
    name="vst_guests",
    uniqueConstraints={
        @UniqueConstraint(columnNames={"secret"})
})
@NamedQueries({
    @NamedQuery(name = "Guest.findBySecret", query = "select g from Guest as g where g.secret = :secret"),
    @NamedQuery(name = "Guest.findAll", query = "select g from Guest as g order by g.name"),
    @NamedQuery(name = "Guest.findAllValid", query = "select g from Guest as g where g.state <> com.vestuve.model.AttendanceState.NOT_ATTENDING order by g.name")
})
public class Guest implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Basic()
    @Column(name = "name", nullable=false)
    private String name;

    @Basic()
    @Column(name = "secret", nullable=false)
    private String secret;

    @Basic()
    @Enumerated(EnumType.STRING)
    @Column(name = "state", nullable=false)
    private AttendanceState state = AttendanceState.UNKNOWN;
    
    @Basic()
    @Column(name = "picture_url", nullable=false)
    private String pictureUrl;
    
    @Basic()
    @Enumerated(EnumType.STRING)
    @Column(name = "menu_choice", nullable=false)
    private MenuChoice menuChoice = MenuChoice.UNKNOWN;

    @Basic()
    @Enumerated(EnumType.STRING)
    @Column(name = "attending_church", nullable=false)
    private AttendanceState attendingChurch = AttendanceState.UNKNOWN;
    
    @Basic()
    @Enumerated(EnumType.STRING)
    @Column(name = "gap_activity", nullable=false)
    private GapActivityChoice gapActivity = GapActivityChoice.UNKNOWN;

    @Basic()
    @Enumerated(EnumType.STRING)
    @Column(name = "attending_afterparty", nullable=false)
    private AttendanceState attendingAfterparty = AttendanceState.UNKNOWN;
    
    //TODO: languages, kids
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public AttendanceState getState() {
        return state;
    }

    public void setState(AttendanceState state) {
        this.state = state;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public MenuChoice getMenuChoice() {
        return menuChoice;
    }

    public void setMenuChoice(MenuChoice menuChoice) {
        this.menuChoice = menuChoice;
    }

    public AttendanceState getAttendingChurch() {
        return attendingChurch;
    }

    public void setAttendingChurch(AttendanceState attendingChurch) {
        this.attendingChurch = attendingChurch;
    }

    public AttendanceState getAttendingAfterparty() {
        return attendingAfterparty;
    }

    public void setAttendingAfterparty(AttendanceState attendingAfterparty) {
        this.attendingAfterparty = attendingAfterparty;
    }

    public GapActivityChoice getGapActivity() {
        return gapActivity;
    }

    public void setGapActivity(GapActivityChoice gapActivity) {
        this.gapActivity = gapActivity;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Guest)) {
            return false;
        }
        Guest other = (Guest) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.vestuve.model.Guest[ id=" + id + " ]";
    }
    
}
