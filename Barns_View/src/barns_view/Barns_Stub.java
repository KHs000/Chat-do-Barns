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
import br.cefetmg.inf.barns.util.Constants;
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
    private Colorful_Console console; 

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
        try {
            Socket socket = new Socket(this.serverAddress, this.serverPort);
            ObjectOutputStream writer = AbstractInOut.getObjectWriter(socket);
            ObjectInputStream reader = AbstractInOut.getObjectReader(socket);
            writer.writeInt(3);
            writer.writeObject(m);
            writer.flush();
            String res = (String) reader.readObject();
            return res;

        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(Barns_Stub.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "sucess";
    }

    // public String processData(){}
    public MessageUpdate getUpdate(String userName) {
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

    public String createGroup(Group g) {
        try {
            Socket socket = new Socket(this.serverAddress, this.serverPort);
            ObjectOutputStream writer = AbstractInOut.getObjectWriter(socket);
            ObjectInputStream reader = AbstractInOut.getObjectReader(socket);
            writer.writeInt(6);
            writer.writeObject(g);
            writer.flush();
            String res = (String) reader.readObject();
            return res;

        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(Barns_Stub.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "sucess";
    }

    public String removeGroup(String groupName, String user) {
        try {
            Socket socket = new Socket(this.serverAddress, this.serverPort);
            ObjectOutputStream writer = AbstractInOut.getObjectWriter(socket);
            ObjectInputStream reader = AbstractInOut.getObjectReader(socket);
            writer.writeInt(5);
            writer.writeObject(groupName);
            writer.writeObject(user);
            writer.flush();
            String res = (String) reader.readObject();
            return res;

        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(Barns_Stub.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "sucess";
    }

    public String removeFromGroup(Group g) {
        try {
            Socket socket = new Socket(this.serverAddress, this.serverPort);
            ObjectOutputStream writer = AbstractInOut.getObjectWriter(socket);
            ObjectInputStream reader = AbstractInOut.getObjectReader(socket);
            writer.writeInt(26);
            writer.writeObject(g);
            writer.flush();
            String res = (String) reader.readObject();
            return res;

        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(Barns_Stub.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "sucess";
    }

    public String addToGroup(Group g) {
        try {
            Socket socket = new Socket(this.serverAddress, this.serverPort);
            ObjectOutputStream writer = AbstractInOut.getObjectWriter(socket);
            ObjectInputStream reader = AbstractInOut.getObjectReader(socket);
            writer.writeInt(27);
            writer.writeObject(g);
            writer.flush();
            String res = (String) reader.readObject();
            return res;

        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(Barns_Stub.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "sucess";
    }

    
    public String login(String nickName) {
        try {
            Socket socket = new Socket(this.serverAddress, this.serverPort);
            ObjectOutputStream writer = AbstractInOut.getObjectWriter(socket);
            ObjectInputStream reader = AbstractInOut.getObjectReader(socket);
            writer.writeInt(0);
            writer.writeObject(nickName);
            writer.flush();
            String res = (String) reader.readObject();
            return res;

        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(Barns_Stub.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "sucess";
    }

    public String listaGrupos(String userName) {
        try {
            Socket socket = new Socket(this.serverAddress, this.serverPort);
            ObjectOutputStream writer = AbstractInOut.getObjectWriter(socket);
            ObjectInputStream reader = AbstractInOut.getObjectReader(socket);
            writer.writeInt(11);
            writer.writeObject(userName);
            writer.flush();
            List<Group> res = (List<Group>) reader.readObject();
            if (res == null) {
                return null;
            }
            String processedString = "";
            for (int i = 0; i < res.size(); i++) {
                processedString += res.get(i).getName();
                processedString += "\n";
            }
            return processedString;

        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(Barns_Stub.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public String listaAll() {
        try {
            Socket socket = new Socket(this.serverAddress, this.serverPort);
            ObjectOutputStream writer = AbstractInOut.getObjectWriter(socket);
            ObjectInputStream reader = AbstractInOut.getObjectReader(socket);
            writer.writeInt(60);
            writer.flush();
            List<User> res = (List<User>) reader.readObject();
            if (res == null) {
                return null;
            }
            String processedString = "";
            for (int i = 0; i < res.size(); i++) {
                processedString += res.get(i).getUserName();
                processedString += "\n";
            }
            return processedString;

        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(Barns_Stub.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public String listGroupUsers(String groupName, String userName) {
        try {
            Socket socket = new Socket(this.serverAddress, this.serverPort);
            ObjectOutputStream writer = AbstractInOut.getObjectWriter(socket);
            ObjectInputStream reader = AbstractInOut.getObjectReader(socket);
            writer.writeInt(63);
            writer.writeObject(groupName);
            writer.writeObject(userName);
            writer.flush();
            List<User> res = (List<User>) reader.readObject();
            if (res == null) {
                return "esse grupo nao existe, ou voce nao tem direitos suficientes";
            }
            String processedString = "";
            for (int i = 0; i < res.size(); i++) {
                processedString += res.get(i).getUserName();
                processedString += "\n";
            }
            return processedString;

        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(Barns_Stub.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public String magicStringPreProcessor(String stringToBeProcessed, String userName) {
        String command;
        String[] targets;
        String message = null;
        try {
            stringToBeProcessed = stringToBeProcessed.trim();
            if (stringToBeProcessed.startsWith("help")) {
                return Constants.helpString;
            } else {
                if (stringToBeProcessed.startsWith("listg")) {
                    String grupos = listaGrupos(userName);
                    if (grupos == null) {
                        return "vc n esta em nenhum grupo";
                    } else {
                        return grupos;
                    }
                } else if (stringToBeProcessed.startsWith("listu")) {
                    String usuarios = listaAll();
                    return usuarios;
                }
            }
            int firstSpace = stringToBeProcessed.indexOf(" ");
            command = stringToBeProcessed.substring(0, firstSpace);
            if (!command.equalsIgnoreCase("pm")
                    && !command.equalsIgnoreCase("all")
                    && !command.equalsIgnoreCase("gm")
                    && !command.equalsIgnoreCase("create")
                    && !command.equalsIgnoreCase("destroy")
                    && !command.equalsIgnoreCase("login")
                    && !command.equalsIgnoreCase("listu")
                    && !command.equalsIgnoreCase("listg")
                    && !command.equalsIgnoreCase("removeFrom")
                    && !command.equalsIgnoreCase("addTo")
                    && !command.equalsIgnoreCase("listFrom")) {
                return "UNKNOWN COMMAND digite help para mais detalhes";
            }
            stringToBeProcessed = stringToBeProcessed.substring(firstSpace);
            targets = stringToBeProcessed.split(",");
            for (int i = 0; i < targets.length; i++) {
                targets[i] = targets[i].trim();
            }
            int messageSpaceDelimeter = targets[targets.length - 1].indexOf(" ");
            if (messageSpaceDelimeter == -1
                    && !command.equals("destroy")
                    && !command.equals("create")
                    && !command.equals("all")
                    && !command.equals("login")
                    && !command.equalsIgnoreCase("listu")
                    && !command.equalsIgnoreCase("listg")
                    && !command.equalsIgnoreCase("listFrom")) {
                return "INSUFICIENT ARGUMENTS";
            }
            if (!command.equals("destroy")
                    && !command.equals("all")
                    && !command.equals("login")
                    && !command.equals("create")
                    && !command.equalsIgnoreCase("listu")
                    && !command.equalsIgnoreCase("listg")
                    && !command.equalsIgnoreCase("listFrom")) {
                message = targets[targets.length - 1].substring(messageSpaceDelimeter);
                targets[targets.length - 1] = targets[targets.length - 1].substring(0, messageSpaceDelimeter);
                message = message.trim();
            }

        } catch (Exception e) {
            return "SINTAX_ERROR digite help para mais detalhes";
        }
        if (command.equals("pm")) {
            List<Receiver> r = new ArrayList<>();
            for (int i = 0; i < targets.length; i++) {
                r.add(new User(targets[i]));
            }
            Message m = new Message(message, new User(userName), r);
            return sendPrivateMessage(m);
        } else if (command.equals("all")) {
            if (targets.length > 1) {
                return "INVALID ARGUMENTS digite help para mais detalhes";
            }
            String msgTest = targets[0];
            List<Receiver> recebedores = new ArrayList<>();
            recebedores.add(new User("\0"));
            Message m = new Message(msgTest, new User(userName), recebedores);
            return sendToAll(m);
        } else if (command.equals("gm")) {
            List<Receiver> r = new ArrayList<>();
            for (int i = 0; i < targets.length; i++) {
                r.add(new Group(targets[i]));
            }
            Message m = new Message(message, new User(userName), r);
            return sendToGroup(m);
        } else if (command.equals("create")) {
            List<User> groupUsers = new ArrayList<>();
            Group g = new Group(targets[0].split(" ")[0]);
            g.addParticipants(new User(userName));
            if (targets[0].split(" ").length > 1) {
                g.addParticipants(new User(targets[0].split(" ")[1]));
            }
            for (int i = 1; i < targets.length; i++) {
                g.addParticipants(new User(targets[i]));
            }

            return createGroup(g);
        } else if (command.equals("destroy")) {
            if (targets.length > 1) {
                return "INVALID ARGUMENTS digite help para mais detalhes";
            }
            String groupName = targets[0];
            return removeGroup(groupName, userName);
        } else if (command.equals("login")) {
            if (targets.length > 1) {
                return "INVALID ARGUMENTS digite help para mais detalhes";
            }
            String nickName = targets[0];
            return login(nickName);
        } else if (command.equalsIgnoreCase("removeFrom")) {
            if (targets.length > 1) {
                return "INVALID ARGUMENTS digite help para mais detalhes";
            }
            Group g = new Group(targets[0]);
            g.addParticipants(new User(userName));
            g.addParticipants(new User(message));
            return removeFromGroup(g);
        } else if (command.equalsIgnoreCase("addTo")) {
            if (targets.length > 1) {
                return "INVALID ARGUMENTS digite help para mais detalhes";
            }
            Group g = new Group(targets[0]);
            g.addParticipants(new User(userName));
            g.addParticipants(new User(message));
            return addToGroup(g);
        } else if (command.equalsIgnoreCase("listFrom")) {
            if (targets.length > 1) {
                return "INVALID ARGUMENTS digite help para mais detalhes";
            }
            String groupName = targets[0];
            return listGroupUsers(groupName, userName);
        }
        return "SUCESS";
    }

    

   

    
    
   


}
