/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vestuve.dao;

import com.vestuve.model.Guest;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author evka
 */
@Stateless
public class GuestDao {

    @PersistenceContext(unitName = "VestuvePU")
    private EntityManager em;

    public GuestDao() {
    }
    
    public Guest getGuest(String secret) {
        List<Guest> ret = em.createNamedQuery("Guest.findBySecret", Guest.class)
                .setParameter("secret", secret)
                .getResultList();
        
        return ret.size() > 0 ? ret.get(0) : null;
    }
    
    public List<Guest> getAll() {
        return em.createNamedQuery("Guest.findAll", Guest.class)
                .getResultList();
    }

    public List<Guest> getAllValid() {
        return em.createNamedQuery("Guest.findAllValid", Guest.class)
                .getResultList();
    }
    
    public void update(Guest guest) {
        em.merge(guest);
    }
    
    public void updatePicUrl(Guest guest, String picUrl) {
        Guest guestDb = em.find(Guest.class, guest.getId());
        guestDb.setPictureUrl(picUrl);
        em.merge(guestDb);
    }
}
