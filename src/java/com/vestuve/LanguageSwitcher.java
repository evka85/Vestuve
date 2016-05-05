/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vestuve;

import java.io.Serializable;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.faces.context.FacesContext;

/**
 *
 * @author Evaldas Juska <evka85@gmail.com>
 */
@Named(value="language")
@SessionScoped
public class LanguageSwitcher implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Locale locale = new Locale("lt");
    
    /**
     * Creates a new instance of LanguageSwitcher
     */
    public LanguageSwitcher() {
    }
    
    public Locale getLocale() {
        return locale;
    }

    public String getLanguage() {
        return locale.getLanguage();
    }

    public void changeLanguage(String language) {
        locale = new Locale(language);
        FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale(language));
    }
    
    public String getMessage(String key) {
        ResourceBundle msg = ResourceBundle.getBundle("com.vestuve.i18n.messages", locale);
        return msg.getString(key);
    }
}
