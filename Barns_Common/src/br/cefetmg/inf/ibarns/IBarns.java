
package br.cefetmg.inf.ibarns;

/**
 *
 * @author Felipe Rabelo
 */
public interface IBarns {
    public String sendPrivateMessage (User sender, User receiver, String msg);
    public String sendToGroup (String sender, Group receiver, String msg);
    public String sendToAll (String sender, String msg);
}
