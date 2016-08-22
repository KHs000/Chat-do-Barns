/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.inf.barns.domain;

import br.cefetmg.inf.barns.Idomain.Receiver;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 *
 * @author Aluno
 */
public class Group implements Receiver{
    private List<User> participants;
    private String name;
    
    public Group(String name) {
      participants = new ArrayList<User>();
      this.name = name;
    }
    
    
    
    public List<User> getParticipants() {
        return participants;
    }

    public void setParticipants(List<User> participants) {
        participants = participants.stream().distinct().collect(Collectors.toList());
        this.participants = participants;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        hash = 89 * hash + Objects.hashCode(this.participants);
        hash = 89 * hash + Objects.hashCode(this.name);
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
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.participants, other.participants)) {
            return false;
        }
        return true;
    }

    
    @Override
    public String toString() {
        return "Group{" + "participants=" + participants + ", name=" + name + '}';
    }
    
    
    
}
