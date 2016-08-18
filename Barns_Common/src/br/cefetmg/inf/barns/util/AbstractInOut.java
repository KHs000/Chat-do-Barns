
package br.cefetmg.inf.barns.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 *
 * @author Felipe Rabelo
 */
public class AbstractInOut {
    
    public static ObjectInputStream getObjectReader(Socket socket) throws IOException{
        ObjectInputStream reader;
        
        reader = new ObjectInputStream(socket.getInputStream());

        return reader;
    }
    
    public static ObjectOutputStream getObjectWriter(Socket socket) throws IOException{
        ObjectOutputStream reader;
        
        reader = new ObjectOutputStream(socket.getOutputStream());

        return reader;
    }    
    
    public static BufferedReader getBufferedReader(Socket socket) throws IOException{
           BufferedReader reader;

            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

           return reader;
       }    
    
    public static BufferedWriter getBufferedWriter(Socket socket) throws IOException{
        BufferedWriter writer;

        writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        return writer;
    }
    
}
