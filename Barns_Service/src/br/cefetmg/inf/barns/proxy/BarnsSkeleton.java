
package br.cefetmg.inf.barns.proxy;

import 
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author Aluno
 */
public class BarnsSkeleton {
    
    private Socket socket;
    
    public BarnsSkeleton (Socket socket) {this.socket = socket;}
    
    private Socket getSocket() {
        return this.socket;        
    }
    
    public void process () {
        ObjectOutputStream writer;
        ObjectInputStream reader;
        
        try {
            //DEFLAGRAR MENSAGENS PARA COM BASE NA FUNÇÃO (pm, gm, am)
            
            writer = AbstractInOut.getObjectWriter(this.getSocket());
            reader = AbstractInOut.getObjectReader(this.getSocket());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
