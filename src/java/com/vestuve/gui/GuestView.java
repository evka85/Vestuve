/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vestuve.gui;

import com.vestuve.dao.GuestDao;
import com.vestuve.model.Guest;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author evka
 */
@Named(value = "guestView")
@RequestScoped
public class GuestView implements Serializable {

    private static final long serialVersionUID = 32L;

    private List<Guest> guests;
    
    @EJB
    private GuestDao guestDao;
    
    public GuestView() {
    }
    
    @PostConstruct
    public void init() {
        guests = guestDao.getAllValid();
        Collections.shuffle(guests);
    }

    public List<Guest> getGuests() {
        return guests;
    }

    public List<Guest> getGuestsForAdmin() {
        return guestDao.getAllForAdmin();
    }    
}
