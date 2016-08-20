/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.inf.barns.domain;

import br.cefetmg.inf.barns.Idomain.Receiver;
import br.cefetmg.inf.barns.Idomain.Sender;

/**
 *
 * @author Digao <Digao at CEFET-MG>
 */
public class MessageUpdate {
    
    String text;
    Sender sender;
    Receiver receiver;
    Group groupOfOrigin;
    
    public MessageUpdate(){
        text = null;
        sender = null;
        receiver = null;
        groupOfOrigin = null;
    }
    
    public MessageUpdate(String text, Sender sender, Receiver receiver,Group groupOfOrigin) {
        this.text = text;
        this.sender = sender;
        this.receiver = receiver;
        this.groupOfOrigin = groupOfOrigin;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Sender getSender() {
        return sender;
    }

    public void setSender(Sender sender) {
        this.sender = sender;
    }

    public Receiver getReceiver() {
        return receiver;
    }

    public void setReceiver(Receiver receiver) {
        this.receiver = receiver;
    }

    public Group getGroupOfOrigin() {
        return groupOfOrigin;
    }

    public void setGroupOfOrigin(Group groupOfOrigin) {
        this.groupOfOrigin = groupOfOrigin;
    }

    
    
    @Override
    public String toString() {
        return "MessageUpdate{" + "text=" + text 
                + ", sender=" + sender 
                + ", receiver=" + receiver 
                + ", groupName = " + groupOfOrigin
                + '}';
    }
    
    
    
}
