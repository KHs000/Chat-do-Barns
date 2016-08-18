/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.inf.barns.domain;

import br.cefetmg.inf.barns.Idomain.Receiver;
import br.cefetmg.inf.barns.Idomain.Sender;
import java.util.List;

/**
 *
 * @author Aluno
 */
public class Group implements Sender, Receiver{
    private List<User> participants;
    
    public Group() {
      
    }
    
    
    
    public List<User> getParticipants() {
        return participants;
    }

    public void setParticipants(List<User> participants) {
        this.participants = participants;
    }
    
    public boolean addParticipants(User user){
        if(participants.contains(user) == false){
            participants.add(user);
            return true;
        }
        else 
           return false;
    }
   
    public boolean removeUser(User user){
        if(participants.contains(user)){
            participants.remove(user);
            return true;
        }
        else 
           return false;
    }
    
}
