
package br.cefetmg.inf.ibarns;

import br.cefetmg.inf.barns.domain.Group;
import br.cefetmg.inf.barns.domain.Message;
import br.cefetmg.inf.barns.domain.User;
import java.util.List;

/**
 *
 * @author Felipe Rabelo
 */
public interface IBarns {
    public String sendPrivateMessage (Message m);
    public String sendToGroup (Message m);
    public String sendToAll (Message m);
    /*public String removeGroup(String groupName, String user);
    public String createGroup(Group g);
    public List<Group> listGroups (String userName);
    /*
    * Group é um objeto com o nome do grupo e dois usuários que são o dono do 
    * grupo e o usuário a ser removido nessa ordem  
    * 
    */
    // public String removeFromGroup(User user, String groupName, String userToRemove);
    /*
    * Group é um objeto com o nome do grupo e dois usuários que são o parte do 
    * grupo e o usuário a ser adicionado nessa ordem  
    */
   /* public String addToGroup(User user, String groupName,String userToAdd);
    public List<User> listUsers();
    public List<User>  getGroupUsers(String groupName, String userName);
    public String login(String nickName);
    */
}
