/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.inf;

import br.cefetmg.inf.barns.Barns_Service;
import br.cefetmg.inf.barns.domain.Action;
import br.cefetmg.inf.barns.domain.Group;
import br.cefetmg.inf.barns.domain.Message;
import br.cefetmg.inf.barns.domain.User;
import br.cefetmg.inf.barns.proxy.BarnsSkeleton;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Digao <Digao at CEFET-MG>
 */
public class Server {

    public static List<Group> allGroups = new ArrayList<Group>();
    public static List<User> allUsers = new ArrayList<User>();
    public static Map<String, Integer> lastUpdateRequest = new HashMap<String, Integer>();
    public static List<Message> messageBuffer = new ArrayList<Message>();
    public static User SYSTEM = new User("SYSTEM");
    @Deprecated
    public static List<Action> actionsBuffer = new ArrayList<Action>();

    public static void main(String args[]) throws IOException {
        //map.get("");
        /*
        allUsers.add(new User("kok"));
        allUsers.add(new User("robson"));
        */
        ServerSocket server = null;

        try {
            server = new ServerSocket(7894);
            initializeTimeoutEngine();
            
            while (true) {
                Socket socket = server.accept();
                BarnsSkeleton chat = new BarnsSkeleton(socket);
                Thread t = new Thread(chat);
                t.start();
                
            }
        } catch (Exception e) {
            if (server != null) {
                server.close();
            }
        }
    }

    public static void initializeTimeoutEngine() {
        Runnable GarbageCollectorThread = new Runnable() {
            @Override
            public void run() {
                while(true){
                    try{
                        Thread.sleep(1);} catch (InterruptedException ex) {
                        Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    for (Map.Entry<String, Integer> entry : lastUpdateRequest.entrySet()) {
                        String userName = entry.getKey();
                        Integer value = entry.getValue();
                        entry.setValue(value+=1);
                        System.out.println(entry.getKey() + "  " + entry.getValue());
                        if(value > 5000){
                            Barns_Service.destroyUser(userName);
                        }
                    }
                }
            }
        };
        new Thread(GarbageCollectorThread).start();
    }
   
    
}
