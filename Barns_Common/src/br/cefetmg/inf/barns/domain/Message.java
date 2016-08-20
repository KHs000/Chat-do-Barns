package br.cefetmg.inf.barns.domain;

import br.cefetmg.inf.barns.Idomain.Receiver;
import br.cefetmg.inf.barns.Idomain.Sender;
import java.util.List;
import java.util.stream.Collectors;

public class Message {

    private String text;
    private Sender sender;
    private List<Receiver> receivers;

    public Message() {
        text = null;
        sender = null;
        receivers = null;
    }

    
    public Message(String text, Sender sender, List<Receiver> receiver) {
        this.text = text;
        this.sender = sender;
        this.receivers = receiver;
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

    public List<Receiver> getReceiver() {
        return receivers;
    }

    public boolean addReceiver(Receiver receiver){
        if(receivers.contains(receiver) == false){
            receivers.add(receiver);
            return true;
        }
        else 
           return false;
    }
    
    public boolean removeReceiver(Receiver receiver){
        if(receivers.contains(receiver) == true){
            receivers.remove(receiver);
            return true;
        }
        else 
           return false;
    }
    
    public void setReceivers(List<Receiver> receivers) {
        receivers = receivers.stream().distinct().collect(Collectors.toList());
        this.receivers = receivers;
    }

    public void removeDuplicatedReceivers(){
        receivers = receivers.stream().distinct().collect(Collectors.toList());
    }
    
    
}
