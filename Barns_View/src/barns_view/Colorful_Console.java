/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barns_view;

import barns_view.constants.ReadErrorConstants;
import br.cefetmg.inf.barns.domain.MessageUpdate;
import br.cefetmg.inf.barns.domain.User;
import br.cefetmg.inf.barns.util.Constants;
import java.util.Scanner;

/**
 *
 * @author Digão
 */
public class Colorful_Console {

    Scanner leitor;

    Colorful_Console() {
        leitor = new Scanner(System.in);
    }

    public void write(MessageUpdate u) {
        if (u == null) {
            return;
        }
        String printedMessage = new String();
        if (((User) u.getSender()).getUserName().equals("SYSTEM")) {
            printedMessage += Constants.COLOR_RED;
            printedMessage += ((User) u.getSender()).getUserName();
            printedMessage += Constants.COLOR_RESET;
        } else if (((User) u.getSender()).getUserName().startsWith("(ALL)")) {
            printedMessage += Constants.COLOR_YELLOW
                    + ((User) u.getSender()).getUserName()
                    + Constants.COLOR_RESET;
        } else {
            printedMessage += Constants.COLOR_CYAN;
            printedMessage += Constants.COLOR_RESET;
        }
        if (u.getGroupOfOrigin() != null) {
            printedMessage += " @ ";
            printedMessage += Constants.COLOR_PURPLE;
            printedMessage += u.getGroupOfOrigin().getName();
            printedMessage += Constants.COLOR_RESET;
        }
        printedMessage += ": ";
        printedMessage += u.getText();
        System.out.println(printedMessage);
    }

    public void write(String text) {
        System.out.println(text);
    }

    public String read() {
        return leitor.nextLine();
    }
}
