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
import java.util.Random;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.primefaces.model.tagcloud.DefaultTagCloudItem;
import org.primefaces.model.tagcloud.DefaultTagCloudModel;
import org.primefaces.model.tagcloud.TagCloudModel;

/**
 *
 * @author evka
 */
@Named(value = "guestNameTagCloudView")
@RequestScoped
public class GuestNameTagCloudView implements Serializable {

    private static final long serialVersionUID = 33L;

    private Random rand = new Random();
    private TagCloudModel model;
    
    @EJB
    private GuestDao guestDao;
    
    public GuestNameTagCloudView() {
    }
    
    @PostConstruct
    public void init() {
        List<Guest> guests = guestDao.getAllValid();
        Collections.shuffle(guests);
        model = new DefaultTagCloudModel();
        for (int i=0; i < 30; i++) {
            model.addTag(new DefaultTagCloudItem(guests.get(i).getName(), rand.nextInt(4) + 1));
        }
    }

    public TagCloudModel getModel() {
        return model;
    }
    
}
