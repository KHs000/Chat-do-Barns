
package br.cefetmg.inf.barns.proxy;

import br.cefetmg.inf.barns.Barns_Service;
import br.cefetmg.inf.barns.domain.Group;
import br.cefetmg.inf.barns.domain.Message;
import br.cefetmg.inf.barns.domain.MessageUpdate;
import br.cefetmg.inf.barns.domain.User;
import br.cefetmg.inf.barns.util.AbstractInOut;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

/**
 *
 * @author Aluno
 */
public class BarnsSkeleton implements Runnable{
    
    private Socket socket;
    
    public BarnsSkeleton (Socket socket) {this.socket = socket;}
    
    private Socket getSocket() {
        return this.socket;        
    }
    
    public void process () {
        ObjectOutputStream writer;
        ObjectInputStream reader;
        Barns_Service processor = new Barns_Service();
        
        try {
            //DEFLAGRAR MENSAGENS PARA COM BASE NA FUNÇÃO (pm, gm, am)
            
            writer = AbstractInOut.getObjectWriter(this.getSocket());
            reader = AbstractInOut.getObjectReader(this.getSocket());
            
            int command; //Tipo de envio: Private(1) ; Grupo(2) ; ou All(3)
            //Receiver target; 
            // !-IMPORTANT Modelo de implementação que pede por uma classe receiver
            //Receiver seria classe mãe de User e de Group
            //Receiver target = new Group/User ();
            // !-IMPORTANT END
            
            command = reader.readInt();
            
            if(command == 1){
                Message pm  = (Message) reader.readObject();
                String res = processor.sendPrivateMessage(pm);
                writer.writeObject(res);
                writer.flush();
            }
            else if(command == 2){
                Message gm  = (Message) reader.readObject();
                String res = processor.sendToGroup(gm);
                writer.writeObject(res);
                writer.flush();
            }
            else if(command == 3){
                Message allM  = (Message) reader.readObject();
                String res = processor.sendToAll(allM);
                writer.writeObject(res);
                writer.flush();
            }
            else if(command == 5){
                String s  = (String) reader.readObject();
                String user  = (String) reader.readObject();
                String res = processor.removeGroup(s,user);
                writer.writeObject(res);
                writer.flush();
            }
            else if(command == 6){
                Group g  = (Group) reader.readObject();
                String res = processor.createGroup(g);
                writer.writeObject(res);
                writer.flush();
            }
            else if(command == 9){
                String userName  = (String) reader.readObject();
                MessageUpdate res = processor.getMessageUpdates(userName);
                writer.writeObject(res);
                writer.flush();
            }
            else if(command == 11){
                String userName  = (String) reader.readObject();
                List<Group> groups = processor.listGroups(userName);
                writer.writeObject(groups);
                writer.flush();
            }
            else if(command == 26){
                Group g  = (Group) reader.readObject();
                String res = processor.removeFromGroup(g.getClonedParticipants().get(0), 
                        g.getName(),
                        g.getClonedParticipants().get(1).getUserName());
                writer.writeObject(res);
                writer.flush();
            }
            else if(command == 27){
                Group g  = (Group) reader.readObject();
                String res = processor.addToGroup(g.getClonedParticipants().get(0), 
                        g.getName(),
                        g.getClonedParticipants().get(1).getUserName());
                writer.writeObject(res);
                writer.flush();
            }
            else if(command == 60){
                List<User> users = processor.listUsers();
                writer.writeObject(users);
                writer.flush();
            }
            else if(command == 63){
                String groupName = (String) reader.readObject();
                String userName  = (String) reader.readObject();
                List<User> users = processor.getGroupUsers(groupName, userName);
                writer.writeObject(users);
                writer.flush();
            }
            else if(command == 0){
                String userName  = (String) reader.readObject();
                String res = processor.login(userName);
                writer.writeObject(res);
                writer.flush();
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    
 @Override
    public void run() {

        try {
            this.process();
            this.getSocket().close();
        } catch (IOException ex) {       
            throw new RuntimeException(ex.getMessage());
        }
    }
}
