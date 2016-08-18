
package br.cefetmg.inf.barns.proxy;

import br.cefetmg.inf.barns.util.AbstractInOut;
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
            
            int command; //Tipo de envio: Private(1) ; Grupo(2) ; ou All(3)
            //Receiver target; 
            // !-IMPORTANT Modelo de implementação que pede por uma classe receiver
            //Receiver seria classe mãe de User e de Group
            //Receiver target = new Group/User ();
            // !-IMPORTANT END
            String msg;
            
            command = reader.readInt();
            switch (command) {
                case 1:
                    //target = new User();
                    //target = reader.readObject();
                    break;
                case 2:
                    break;
                case 3:
                    break;
            }
            
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
