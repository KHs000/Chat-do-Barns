
package br.cefetmg.inf.barns.proxy;

import br.cefetmg.inf.barns.Barns_Service;
import br.cefetmg.inf.barns.domain.Message;
import br.cefetmg.inf.barns.domain.MessageUpdate;
import br.cefetmg.inf.barns.util.AbstractInOut;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

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
            else if(command == 3){}
            else if(command == 6){
                
            }
            else if(command == 9){
                String userName  = (String) reader.readObject();
                MessageUpdate res = processor.getMessageUpdates(userName);
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
