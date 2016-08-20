
package br.cefetmg.inf.barns;

import br.cefetmg.inf.Server;
import br.cefetmg.inf.barns.Idomain.Receiver;
import br.cefetmg.inf.barns.domain.Action;
import br.cefetmg.inf.barns.domain.Group;
import br.cefetmg.inf.barns.domain.Message;
import br.cefetmg.inf.barns.domain.MessageUpdate;
import br.cefetmg.inf.barns.domain.User;
import br.cefetmg.inf.ibarns.IBarns;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Felipe Rabelo 
 * @author Digao
 */
public class Barns_Service implements IBarns{

    @Override
    public String sendPrivateMessage(Message m) {
        String retorno = "";
        for(int i = 0 ; i < m.getReceiver().size() ; i++){
          if(!Server.allUsers.contains(m.getReceiver().get(i))){
              User user = (User) m.getReceiver().get(i);
              retorno+= "o usuario: " + user.getUserName() +  " nao foi encontrado \n";
          }
        }
        if(retorno.isEmpty()){
            Server.messageBuffer.add(m);
        }
        return retorno;
    }

    @Override
    public String sendToGroup(Message m) {
        String retorno = "";
        for(int i = 0 ; i < m.getReceiver().size() ; i++){
          if(!Server.allGroups.contains(m.getReceiver().get(i))){
              Group group = (Group) m.getReceiver().get(i);
              retorno+= "o grupo: " + group.getName() +  " nao foi encontrado \n";
          }
        }
        if(retorno.isEmpty()){
            Server.messageBuffer.add(m);
        }
        return retorno;
    }

    @Override
    public String sendToAll(Message m) {
        List<User> allUsers =  Server.allUsers;
        for(int i = 0; i < allUsers.size(); i++){
            m.addReceiver((Receiver) allUsers.get(i));
        }
        Server.messageBuffer.add(m);
        return "";
    }
    
    public MessageUpdate getMessageUpdates(String userName ){
        User u = new User(userName);

        List<Message> MessageList = Server.messageBuffer;
        for(int i = 0; i < MessageList.size(); i++){
            Message currentMessage = MessageList.get(i);
                for(int j = 0 ; j < currentMessage.getReceiver().size(); j++){
                    if(currentMessage.getReceiver().get(j).getClass() == u.getClass()){
                        User user = (User) currentMessage.getReceiver().get(j);
                        Server.messageBuffer.get(i).getReceiver().remove(user);
                        if(u.getUserName() == user.getUserName())
                            return new MessageUpdate(currentMessage.getText(), 
                                    currentMessage.getSender() , 
                                    user ,
                                    null
                            );
                    }
                    else if(currentMessage.getReceiver().get(j).getClass() == Group.class){
                        Group group = (Group) currentMessage.getReceiver().get(j);
                        for(int k = 0; k < group.getParticipants().size(); k++){
                            if(u.getUserName() == group.getParticipants().get(k).getUserName()){
                                User usuario = group.getParticipants().get(k);
                                Server.messageBuffer.get(i).getReceiver().remove(group);
                                group.removeUser(usuario);
                                Server.messageBuffer.get(i).getReceiver().add(group);
                                return new MessageUpdate(currentMessage.getText(), 
                                    currentMessage.getSender() , 
                                    (Receiver) usuario,
                                    group
                                );
                            }
                        }
                    }
                }
                //firstUpdate = MessageList.get(i);
        }
        return null;
    }
    
    public Action getActionUpdates(String userName){
       List<Action> actions =  Server.actionsBuffer;
       for(int i = 0 ; i < actions.size() ; i++){
           if (actions.get(i).getUserName().equals(userName)){
               Server.actionsBuffer.remove(i);
               return actions.get(i);
           }
       }
       return null;
    }
    
    public boolean addToGroup(User user, String groupName){
        for(int i = 0; i < Server.allGroups.size() ; i++){
            if(Server.allGroups.get(i).getName().equals(groupName)){
                return Server.allGroups.get(i).addParticipants(user);
            }
        }
        return false;
    }
    
    public boolean removeFromGroup(User user, String groupName){
        for(int i = 0; i < Server.allGroups.size() ; i++){
            if(Server.allGroups.get(i).getName().equals(groupName)){
                return Server.allGroups.get(i).removeUser(user);
            }
        }
        return false;
    }
    
    public boolean removeGroup(String groupName){
         for(int i = 0; i < Server.allGroups.size() ; i++){
             if(Server.allGroups.get(i).getName().equals(groupName)){
                Server.allGroups.remove(i);
                return true;
            }
         }
        return false;
    }
    
    public boolean createGroup(Group group){
        if(Server.allGroups.contains(group))
            return false;
        else{
            Server.allGroups.add(group);
            return true;
        }
    }
    
}
