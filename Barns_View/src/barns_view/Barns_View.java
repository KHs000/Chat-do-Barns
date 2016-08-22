/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barns_view;

import br.cefetmg.inf.barns.domain.MessageUpdate;
import br.cefetmg.inf.ibarns.IBarns;

/**
 *
 * @author Conta Única
 */
public class Barns_View {

    static Colorful_Console console = new Colorful_Console();

    public static void initializeUpdater() {
        Thread updater = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                        Barns_Stub Barns = new Barns_Stub("localhost", 7894);
                        MessageUpdate up = Barns.getUpdate("robson");
                        console.write(up);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        updater.start();
    }

    public static void initiliazeReader() {
        Thread readerThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(2);
                        Barns_Stub Barns = new Barns_Stub("localhost", 7894);
                        String command = console.read();                     
                            String res = Barns.magicStringPreProcessor(command, "robson");
                            System.out.println("RES : " + res);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        readerThread.start();
    }

    public static void main(String[] args) {
        initializeUpdater();
        initiliazeReader();

        //System.out.println("\u001B[35m" + new Barns_Stub.magicStringPreProcessor("pm jooj ", "jobson"));
    }
}
