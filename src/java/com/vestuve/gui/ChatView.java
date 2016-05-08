/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vestuve.gui;

import com.vestuve.LanguageSwitcher;
import com.vestuve.dao.ChatDao;
import com.vestuve.dao.GuestDao;
import com.vestuve.model.ChatMessage;
import com.vestuve.model.Guest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.NoResultException;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author evka
 */
@Named(value = "chatView")
@SessionScoped
public class ChatView implements Serializable {

    private static final long serialVersionUID = 75L;
    
    @EJB
    private ChatDao chatDao;
    
    @Inject
    private RsvpView rsvpView;
    
    private ChatMessage message;
    
    @Inject
    private LanguageSwitcher msg;
    
    public ChatView() {
    }

    public ChatMessage getMessage() {
        if (message == null) {
            message = new ChatMessage();
        }
        return message;
    }
    
    public void saveMessage() {
        if (rsvpView.getGuest() != null) {
            message.setGuest(rsvpView.getGuest());
            getMessage().setTime(new Date());
            chatDao.save(getMessage());
            message = null;
        }
    }
    
    public List<ChatMessage> getAllMessages() {
        return chatDao.getAllMessages();
    }
}
