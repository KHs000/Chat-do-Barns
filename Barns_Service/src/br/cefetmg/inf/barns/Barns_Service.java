package br.cefetmg.inf.barns;

import br.cefetmg.inf.Server;
import br.cefetmg.inf.barns.Idomain.Receiver;
import br.cefetmg.inf.barns.domain.Action;
import br.cefetmg.inf.barns.domain.Group;
import br.cefetmg.inf.barns.domain.Message;
import br.cefetmg.inf.barns.domain.MessageUpdate;
import br.cefetmg.inf.barns.domain.User;
import br.cefetmg.inf.barns.util.Constants;
import br.cefetmg.inf.ibarns.IBarns;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Felipe Rabelo
 * @author Digao
 */
public class Barns_Service implements IBarns {

    @Override
    public String sendPrivateMessage(Message m) {
        String retorno = "";
        for (int i = 0; i < m.getReceiver().size(); i++) {
            if (!Server.allUsers.contains(m.getReceiver().get(i))) {
                List<User> listosa = Server.allUsers;
                User user = (User) m.getReceiver().get(i);
                retorno += "o usuario: " + user.getUserName() + " nao foi encontrado \n";
            }
        }
        if (retorno.isEmpty()) {
            Server.messageBuffer.add(m);
        }
        return retorno;
    }

    @Override
    public String sendToGroup(Message m) {
        String retorno = "";
        for (int i = 0; i < m.getReceiver().size(); i++) {
            if (!Server.allGroups.contains(m.getReceiver().get(i))) {
                Group group = (Group) m.getReceiver().get(i);
                retorno += "o grupo nao foi encontrado";
            } else {
                int index = Server.allGroups.indexOf(m.getReceiver().get(i));
                List<User> users = Server.allGroups.get(index).getClonedParticipants();
                if(!Server.allGroups.get(index).getParticipants().contains(m.getSender())){
                    return "o grupo: nao foi encontrado";
                }
                ((Group) m.getReceiver().get(i)).setParticipants(users);
                ((Group) m.getReceiver().get(i)).removeUser((User) m.getSender());
                
            }
        }
        if (retorno.isEmpty()) {
            Server.messageBuffer.add(m);
        }
        return retorno;
    }

    @Override
    public String sendToAll(Message m) {
        List<User> allUsers = Server.allUsers;
        m.getReceiver().clear();
        for (int i = 0; i < allUsers.size(); i++) {
            m.addReceiver((Receiver) allUsers.get(i));
        }
        String userName;
        userName = ((User) m.getSender()).getUserName();
        m.getReceiver().remove(new User(userName));
        userName = "(ALL)" + userName;
        m.setSender(new User(userName));
        //m.getReceiver().remove(new User(((User) m.getSender()).getUserName().)
        m.removeDuplicatedReceivers();
        Server.messageBuffer.add(m);
        return "";
    }

    /**
     * Pega a primeira menssagem do usuário, e remove o do buffer de menssagems;
     * *** INCLUINDO GRUPOS ***
     * e atuliza a timeout engine (que apaga um usuario inativo)
     */
    public static MessageUpdate getMessageUpdates(String userName) {
        
        User u = new User(userName);
        Server.lastUpdateRequest.replace(userName, 0);
        List<Message> MessageList = Server.messageBuffer;
        for (int i = 0; i < MessageList.size(); i++) {
            Message currentMessage = MessageList.get(i);
            for (int j = 0; j < currentMessage.getReceiver().size(); j++) {
                if (currentMessage.getReceiver().get(j).getClass() == u.getClass()) {
                    User user = (User) currentMessage.getReceiver().get(j);
                    if (u.getUserName().equals(user.getUserName())) {
                        Server.messageBuffer.get(i).getReceiver().remove(user);
                        
                        return new MessageUpdate(currentMessage.getText(),
                                currentMessage.getSender(),
                                user,
                                null
                        );
                    }
                } else if (currentMessage.getReceiver().get(j).getClass() == Group.class) {
                    Group group = (Group) currentMessage.getReceiver().get(j);
                    for (int k = 0; k < group.getParticipants().size(); k++) {
                        if (u.getUserName().equals(group.getParticipants().get(k).getUserName())) {
                            User usuario = group.getParticipants().get(k);
                            Server.messageBuffer.get(i).getReceiver().remove(group);
                            group.removeUser(usuario);
                            Server.messageBuffer.get(i).getReceiver().add(group);

                            return new MessageUpdate(currentMessage.getText(),
                                    currentMessage.getSender(),
                                    (Receiver) usuario,
                                    group
                            );
                        }
                    }
                }
            }
            if(currentMessage.getReceiver().isEmpty())
                Server.messageBuffer.remove(i);
        }
        return null;
    }

    @Deprecated
    public Action getActionUpdates(String userName) {
        List<Action> actions = Server.actionsBuffer;
        for (int i = 0; i < actions.size(); i++) {
            if (actions.get(i).getUserName().equals(userName)) {
                Server.actionsBuffer.remove(i);
                return actions.get(i);
            }
        }
        return null;
    }

    public String addToGroup(User user, String groupName,String userToAdd) {
        
        for (int i = 0; i < Server.allGroups.size(); i++) {
            if (Server.allGroups.get(i).getName().equals(groupName)) {
                if(Server.allGroups.get(i).getClonedParticipants().contains(user) == false){
                    return "o grupo não existe, ou você n esta grupo";
                }
                boolean result = Server.allGroups.get(i).addParticipants(new User(userToAdd));
                if (result == true) {
                    List<Receiver> receivers = new ArrayList<>();
                    receivers.add((Receiver) user);
                    Message notification = new Message("Voce foi adicionado ao grupo: "
                            + groupName, Server.SYSTEM, receivers);
                    return "sucesso";
                } else {
                    return "o participante ja esta no grupo";
                }
            }
        }
        return "falha";
    }

    public String removeFromGroup(User user, String groupName, String userToRemove) {        
        for (int i = 0; i < Server.allGroups.size(); i++) {
            if (Server.allGroups.get(i).getName().equals(groupName)) {
                if(!Server.allGroups.get(i).getClonedParticipants().get(0).equals(user))
                    return "o grupo nao existe, ou voce n tem direitos suficientes para remover usuarios";
                boolean result = Server.allGroups.get(i).removeUser(new User(userToRemove));
                if (result == true) {
                    List<Receiver> receivers = new ArrayList<>();
                    receivers.add((Receiver) user);
                    Message notification = new Message("Voce foi removido do grupo: "
                            + groupName, Server.SYSTEM, receivers);
                    return "";
                } else {
                    return "O usuário n foi encontrado";
                }
            }
        }
        return "O usuário n foi encontrado";
    }

    public String removeGroup(String groupName, String user) {
        for (int i = 0; i < Server.allGroups.size(); i++) {
            if (Server.allGroups.get(i).getName().equals(groupName)) {
                if(!Server.allGroups.get(i).getClonedParticipants().get(0).equals(new User(user)))
                    return "o grupo nao existe, ou voce n tem direitos suficientes para apaga-lo";
                List<Receiver> receivers = new ArrayList<>();
                receivers.add((Receiver) Server.allGroups.get(i));
                Message notification = new Message("O grupo: "
                        + Server.allGroups.get(i).getName() + " foi destruído", Server.SYSTEM, receivers);
                Server.allGroups.remove(i);
                Server.messageBuffer.add(notification);
                return "Destruido";
            }
        }
        return "O grupo n foi encontrado";
    }

    public String createGroup(Group group) {
        if (Server.allGroups.contains(group)) {
            return "O grupo ja existe";
        } else {
            Server.allGroups.add(group);
            List<Receiver> receivers = new ArrayList<>();
            //receivers.add((Receiver) group.getClonedParticipants().get(0));
            for (int i = 0; i < group.getParticipants().size(); i++) {
                receivers.add(group.getClonedParticipants().get(i));
            }
            Message notification = new Message("Voce foi adicionado ao grupo "+ group.getName() + " por "
                    + group.getParticipants().get(0).getUserName()
                    , Server.SYSTEM, receivers);
            Server.messageBuffer.add(notification);
            return "Sucesso na criação";
        }
    }
    
    
    public List<User> getGroupUsers(String groupName, String userName){
        List<Group> g = Server.allGroups;
        for (int i = 0; i < Server.allGroups.size(); i++) {
            if(Server.allGroups.get(i).getName().equals(groupName)){
                if(Server.allGroups.get(i).getClonedParticipants().contains(new User(userName))){
                    return Server.allGroups.get(i).getClonedParticipants();
                }
            }    
        }
        return null;
    }
    
    public List<Group> listGroups(String userName){
        User user = new User(userName);
        List<Group> grupos = new ArrayList<>();
        for (int i = 0; i < Server.allGroups.size(); i++) {
            if(Server.allGroups.get(i).getParticipants().contains(user)){
                grupos.add(Server.allGroups.get(i));
            }
        }
        if(grupos.isEmpty())
            return null;
        else 
            return grupos;
    }
    
     
    public String login(String userName){
        User user = new User(userName);
        if(Server.allUsers.contains(user)){
            return null;
        }
        else{
            Server.allUsers.add(user);
            Server.lastUpdateRequest.put(userName, 0);
            //initializeGC(userName);
        }
        return userName;
    }
    
    @Deprecated
    private void initializeGC(String userName) {
        Runnable GarbageCollectorThread = new Runnable() {
            @Override
            public void run() {
                Integer lastUpdateTime = Server.lastUpdateRequest.get(userName);
                if(lastUpdateTime > 10000)
                    destroyUser(userName);
            }
        };
        new Thread(GarbageCollectorThread).start();
    }
    
    public List<User> listUsers(){

        List<User> users = new ArrayList<>();
        for (int i = 0; i < Server.allUsers.size(); i++) {
            users.add(Server.allUsers.get(i));
        }
        if(users.isEmpty())
            return null;
        else 
            return users;
    }
    
    public void destroyUser(String userName){
        /* tira todas as menssagens do buffer direcionadas a esse usuario*/
        while(getMessageUpdates(userName) != null){
            getMessageUpdates(userName);
        }
        
        
         /*remove o usuario de todos os grupos */
        List<Group> gruposDoUsuario = listGroups(userName);  
        /* tira esse usuario da lista de usuarios*/
        Server.allUsers.remove(new User(userName));
        Server.lastUpdateRequest.remove(userName);
        if(gruposDoUsuario == null) 
            return;
        for (int i = 0; i < gruposDoUsuario.size(); i++) {
            if(Server.allGroups.contains(gruposDoUsuario.get(i))){
                Server.allGroups.get(i).removeUser(new User(userName));
            }
        }
        
    }
}
