/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barns_view;
import barns_view.constants.ReadErrorConstants;
import java.util.Scanner;

/**
 *
 * @author Digão
 */
public class Console_Reader {
    Scanner leitor;
    
    Console_Reader(){
        leitor = new Scanner(System.in); 
    }
    
 
    public void write(String text){System.out.println(text);}
    public String read(){
        return leitor.next();
    }
}
