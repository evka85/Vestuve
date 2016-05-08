/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vestuve.dao;

import com.vestuve.model.ChatMessage;
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
public class ChatDao {

    @PersistenceContext(unitName = "VestuvePU")
    private EntityManager em;

    public ChatDao() {
    }
    
    public List<ChatMessage> getAllMessages() {
        return em.createNamedQuery("ChatMessage.findAll", ChatMessage.class)
                .getResultList();
    }

    public void save(ChatMessage msg) {
        em.persist(msg);
    }
}
