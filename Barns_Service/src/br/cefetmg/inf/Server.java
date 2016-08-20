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
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Digao <Digao at CEFET-MG>
 */

public class Server {
    public static List<Group> allGroups = new ArrayList<Group>();
    public static List<User> allUsers = new ArrayList<User>();
    public static List<Message> messageBuffer =  new ArrayList<Message>();
    public static List<Action> actionsBuffer =  new ArrayList<Action>();
    
    public static void main (String args[]) throws IOException {
        
        ServerSocket server = null;
        
        try {
            server = new ServerSocket(2223);

            while(true) {
                Socket socket = server.accept();
                /*Barns_Service chat = new Barns_Service(socket);
                Thread t = new Thread(chat);
                t.start();*/
            }
        }
        catch(Exception e) {
            if (server != null)
                server.close();
        }
    }
}
