
package br.cefetmg.inf.ibarns;

/**
 *
 * @author Felipe Rabelo
 */
public interface IBarns {
    public String pm (String sender, String receiver, String msg);
    public String gm (String sender, Group receiver, String msg);
    public String am (String sender, String msg);
}
