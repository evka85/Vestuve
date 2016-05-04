/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vestuve.gui;

import com.vestuve.LanguageSwitcher;
import com.vestuve.util.EmailSender;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Evaldas Juska
 */
@Named(value = "contactView")
@RequestScoped
public class ContactView implements Serializable {

    private static final long serialVersionUID = 55L;

    private String subject;
    private String body;
    
//    @Inject
//    RsvpView rsvpView;
    
    @Inject
    private LanguageSwitcher msg;

    public ContactView() {
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String send() {
        System.out.println("Sending message...");
        System.out.println("Message subject = " + subject);
        System.out.println("Message body = " + body);
        
        try {
            EmailSender.sendToBrideAndGroom(subject, body);
            
            subject = null;
            body = null;
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg.getMessage("text.contact.f_contact.msg_sent"),  msg.getMessage("text.contact.f_contact.msg_sent.details")));
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg.getMessage("text.contact.f_contact.err_failed_send"),  msg.getMessage("text.contact.f_contact.err_failed_send.details")));
        }
                
        return "success";
    }
    
}
