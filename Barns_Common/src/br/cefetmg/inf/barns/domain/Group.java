/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.inf.barns.domain;

import br.cefetmg.inf.barns.Idomain.Receiver;
import br.cefetmg.inf.barns.Idomain.Sender;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 *
 * @author Aluno
 */
public class Group implements Sender, Receiver{
    private List<User> participants;
    
    public Group() {
      participants = null;
    }
    
    
    
    public List<User> getParticipants() {
        return participants;
    }

    public void setParticipants(List<User> participants) {
        participants = participants.stream().distinct().collect(Collectors.toList());
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.participants);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Group other = (Group) obj;
        if (!Objects.equals(this.participants, other.participants)) {
            return false;
        }
        return true;
    }
    
    
    
}
