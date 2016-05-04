/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vestuve.util;

import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author Evaldas Juska
 */
public class EmailSender {
    
    private static String EMAIL_BRIDE_AND_GROOM = "agne.kazimieraityte@gmail.com,evaldas.juska@cern.ch,evka85@gmail.com";
    
    private static Session getMailcern() throws NamingException {
        Context c = new InitialContext();
        return (Session) c.lookup("mail/main");
    }

    public static void sendToBrideAndGroom(String subject, String body) throws NamingException, MessagingException {
        send(EMAIL_BRIDE_AND_GROOM, subject, body);
    }
    
    public static void send(String email, String subject, String body) throws NamingException, MessagingException {
        Session mailcern = getMailcern();
        MimeMessage message = new MimeMessage(mailcern);
        message.setSubject(subject);
        message.setRecipients(RecipientType.TO, InternetAddress.parse(email, false));
        message.setText(body);
        Transport.send(message);
    }
}
