
package br.cefetmg.inf.ibarns;

import br.cefetmg.inf.barns.domain.Message;

/**
 *
 * @author Felipe Rabelo
 */
public interface IBarns {
    public String sendPrivateMessage (Message m);
    public String sendToGroup (Message m);
    public String sendToAll (Message m);
}
