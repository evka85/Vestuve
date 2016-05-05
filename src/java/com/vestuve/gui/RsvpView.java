/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vestuve.gui;

import com.vestuve.LanguageSwitcher;
import com.vestuve.dao.GuestDao;
import com.vestuve.model.Guest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Collections;
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
@Named(value = "rsvpView")
@SessionScoped
public class RsvpView implements Serializable {

    private static final long serialVersionUID = 50L;
    
    private static final String PICTURE_RESOURCE_DIR = "/resources/img/guests/";

    private String secret;
    private Guest guest;
    
    private UploadedFile uploadedPicture;
    
    @EJB
    private GuestDao guestDao;
    
    @Inject
    private LanguageSwitcher msg;
    
    public RsvpView() {
    }

    public String getSecret() {
        return secret;
    }
    
    public void setSecret(String secret) {
        this.secret = secret;
    }
    
    public String loadGuest() {
        try {
            guest = guestDao.getGuest(secret);
        } catch (Exception ex) {
            guest = null;
        }
     
        if (guest == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg.getMessage("text.rsvp.f_secret.err_invalid_secret.title"),  msg.getMessage("text.rsvp.f_secret.err_invalid_secret.msg")));
            return "";
        } else {
            String title = String.format(msg.getMessage("text.rsvp.f_secret.success.title"), guest.getName());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, title,  msg.getMessage("text.rsvp.f_secret.success.msg")));
        }
        
        secret = "";
        
        return "login_success";
    }
    
    public void logout() {
        guest = null;
    }
    
    public Guest getGuest() {
        return guest;
    }
    
    public void saveGuestInfo() {
        guestDao.update(guest);
        String title = String.format(msg.getMessage("text.rsvp.f_data.save_success.title"), guest.getName());
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, title,  msg.getMessage("text.rsvp.f_data.save_success.msg")));
        System.out.println("Info updated for guest " + guest.getName());
    }

    public UploadedFile getUploadedPicture() {
        return uploadedPicture;
    }

    public void setUploadedPicture(UploadedFile uploadedPicture) {
        this.uploadedPicture = uploadedPicture;
    }
    
    public void uploadPicture() throws IOException {
        if ((uploadedPicture != null) && (uploadedPicture.getFileName() != null) && (!uploadedPicture.getFileName().isEmpty()) && (guest != null)) {
            byte[] bytes=null;
            bytes = uploadedPicture.getContents();
            if (bytes.length > 1048576) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg.getMessage("text.rsvp.f_data.pic_upload_error_too_big.title"),  msg.getMessage("text.rsvp.f_data.pic_upload_error_too_big.msg")));
                return;
            }
            String filename = "guest" + guest.getId() + "_" + (new File(uploadedPicture.getFileName())).getName();
            String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath(PICTURE_RESOURCE_DIR);
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(path + File.separator + filename)));
            stream.write(bytes);
            stream.close();
            
            String picUrl = PICTURE_RESOURCE_DIR + filename;
            guestDao.updatePicUrl(guest, picUrl);
            guest.setPictureUrl(picUrl);
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg.getMessage("text.rsvp.f_data.pic_upload_success.title"),  msg.getMessage("text.rsvp.f_data.pic_upload_success.msg")));
            
            System.out.println("Picture uploaded for guest " + guest.getName() + ": " + picUrl);
        }
    }
}
