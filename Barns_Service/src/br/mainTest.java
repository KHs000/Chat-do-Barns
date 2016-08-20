/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br;

import br.cefetmg.inf.Server;
import br.cefetmg.inf.barns.Barns_Service;
import br.cefetmg.inf.barns.Idomain.Receiver;
import br.cefetmg.inf.barns.Idomain.Sender;
import br.cefetmg.inf.barns.domain.Group;
import br.cefetmg.inf.barns.domain.Message;
import br.cefetmg.inf.barns.domain.MessageUpdate;
import br.cefetmg.inf.barns.domain.User;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Digao <Digao at CEFET-MG>
 */
public class mainTest {
    public static void main(String[] args){
        
        Barns_Service bs = new Barns_Service();
        /*
        Group g = new Group("GRUPO DE TESTE");        
        User u  = new User("JOOJU");
        User E  = new User("JOOJE");
        g.addParticipants(u);
        g.addParticipants(E);
        Receiver r = (Receiver) g;
        Sender se = (Sender) E;
        Server s = new Server();
        List<Receiver> recebedores = new ArrayList();
        recebedores.add(r);
        Message m  = new Message("Sua mãe", se , recebedores);
        Server.messageBuffer.add(m);
        MessageUpdate update = bs.getMessageUpdates("JOOJU");
        System.out.println(update);
        System.out.println(update.getGroupOfOrigin());*/
        
        Server s = new Server();
        User jozao = new User("JOZAO");
        User dijo = new User("DIJORGINIS");
        Group turmaDosMacaco = new Group("turmaDosMacaco");
        turmaDosMacaco.addParticipants(dijo);
        List<Receiver> recebidores = new ArrayList<Receiver>();
        recebidores.add((Receiver) dijo);
        Server.allUsers.add(jozao);
        Server.allUsers.add(dijo);
        Message m = new Message();
        m.setSender(jozao);
        m.setText("COLE VIADO");
        m.setReceivers(recebidores);
        bs.createGroup(turmaDosMacaco);
        String sendReturn  = bs.sendPrivateMessage(m);
        System.out.println("SEND RETURN: " + sendReturn); 
        
        System.out.println(Server.messageBuffer);
        MessageUpdate update = bs.getMessageUpdates(dijo.getUserName());
        System.out.println(update);
        
    }
}