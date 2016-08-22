/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barns_view;

import br.cefetmg.inf.barns.Idomain.Receiver;
import br.cefetmg.inf.barns.Idomain.Sender;
import br.cefetmg.inf.barns.domain.Group;
import br.cefetmg.inf.barns.domain.Message;
import br.cefetmg.inf.barns.domain.MessageUpdate;
import br.cefetmg.inf.barns.domain.User;
import br.cefetmg.inf.barns.util.AbstractInOut;
import br.cefetmg.inf.ibarns.IBarns;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Digao <Digao at CEFET-MG>
 */
public class Barns_Stub implements IBarns {
    private String serverAddress;
    private int serverPort;
    
    public Barns_Stub(String serverAddress, int serverPort) {
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;        
    }
    
    @Override
    public String sendPrivateMessage(Message m) {
        try {  
            Socket socket = new Socket(this.serverAddress, this.serverPort);
            ObjectOutputStream writer = AbstractInOut.getObjectWriter(socket);
            ObjectInputStream reader = AbstractInOut.getObjectReader(socket);
            writer.writeInt(1);
            writer.writeObject(m);
            writer.flush();
            String res = (String) reader.readObject(); 
            return res;
            
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(Barns_Stub.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "sucess";
    }
    
    @Override
    public String sendToGroup(Message m) {
        try {  
            Socket socket = new Socket(this.serverAddress, this.serverPort);
            ObjectOutputStream writer = AbstractInOut.getObjectWriter(socket);
            ObjectInputStream reader = AbstractInOut.getObjectReader(socket);
            writer.writeInt(2);
            writer.writeObject(m);
            writer.flush();
            String res = (String) reader.readObject(); 
            return res;
            
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(Barns_Stub.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "sucess";
    }

    @Override
    public String sendToAll(Message m) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
   // public String processData(){}
    
    public MessageUpdate getUpdate(String userName){
        try {  
            Socket socket = new Socket(this.serverAddress, this.serverPort);
            ObjectOutputStream writer = AbstractInOut.getObjectWriter(socket);
            ObjectInputStream reader = AbstractInOut.getObjectReader(socket);
            writer.writeInt(9);
            writer.writeObject(userName);
            writer.flush();
            MessageUpdate res = (MessageUpdate) reader.readObject(); 
            return res;
            
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(Barns_Stub.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    } 
    
    public String createGroup(Group g){
        return null;
    }
    
    public String magicStringPreProcessor(String stringToBeProcessed, String userName) {
        String command;
        String[] targets;
        String message;
        try {
            stringToBeProcessed = stringToBeProcessed.trim();
            int firstSpace = stringToBeProcessed.indexOf(" ");
            command = stringToBeProcessed.substring(0, firstSpace);
            if (!command.equalsIgnoreCase("pm")
                    && !command.equalsIgnoreCase("all")
                    && !command.equalsIgnoreCase("gm")) {
                return "UNKNOWN COMMAND";
            }
            stringToBeProcessed = stringToBeProcessed.substring(firstSpace);
            targets = stringToBeProcessed.split(",");
            for (int i = 0; i < targets.length; i++) {
                targets[i] = targets[i].trim();
            }
            int messageSpaceDelimeter = targets[targets.length - 1].indexOf(" ");
            if (messageSpaceDelimeter == -1) {
                return "INSUFICIENT ARGUMENTS";
            }
            message = targets[targets.length - 1].substring(messageSpaceDelimeter);
            targets[targets.length - 1] = targets[targets.length - 1].substring(0, messageSpaceDelimeter);
            message = message.trim();

        } catch (Exception e) {
            return "SINTAX_ERROR";
        }
        if(command.equals("pm")){
            List<Receiver> r = new ArrayList<>();
            for (int i = 0; i < targets.length; i++) {
                r.add(new User(targets[i]));
            }
            Message m = new Message(message, new User(userName), r);
            return sendPrivateMessage(m);
        }
        else if(command.equals("all")){}
        else if(command.equals("gm")){}
        return "SUCESS";
    }
}
