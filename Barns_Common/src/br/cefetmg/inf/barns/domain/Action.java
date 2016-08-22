/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.inf.barns.domain;

import br.cefetmg.inf.barns.util.Constants;

/**
 *
 * @author Digao <Digao at CEFET-MG>
 */
@Deprecated
public class Action {
    private int actionType;
    private String userName;
    private Group group;
    
    public Action(){
        actionType = 2011; 
        userName = "";
        group = new Group("");
    }
    
    public Action(int actionType, String userName, Group group) {
        this.actionType = actionType;
        this.userName = userName;
        this.group = group;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public int getActionType() {
        return actionType;
    }

    public void setActionType(int actionType) {
        this.actionType = actionType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    
}
