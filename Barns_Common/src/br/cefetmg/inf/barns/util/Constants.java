/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.inf.barns.util;

/**
 *
 * @author Digao <Digao at CEFET-MG>
 */
public class Constants {
    public static final int ACTION_ADD_TO_GROUP = 987;
    public static final int ACTION_REMOVE_FROM_GROUP = 986;
    
    public static final String COLOR_RESET = "\u001B[0m";
    public static final String COLOR_BLACK = "\u001B[30m";
    public static final String COLOR_RED = "\u001B[31m";
    public static final String COLOR_GREEN = "\u001B[32m";
    public static final String COLOR_YELLOW = "\u001B[33m";
    public static final String COLOR_BLUE = "\u001B[34m";
    public static final String COLOR_PURPLE = "\u001B[35m";
    public static final String COLOR_CYAN = "\u001B[36m";
    public static final String COLOR_WHITE = "\u001B[37m";
    
    public static final String helpString = 
              "pm user ,[user , ...] text \n"
            + "gm groupName, [groupName, ...] text \n"
            + "all text \n"
            + "create groupName user ,[user , ...] \n"
            + "destroy groupName           /* voce deve ser o dono do grupo para destrui-lo */ \n "
            + "addTo groupName user          /* voce deve estar no grupo para adicionar pessoas*/ \n"
            + "removeFrom groupName user        /* voce deve ser o dono do grupo para destrui-lo */ \n"
            + "listFrom groupName        /* voce deve estar no grupo para listar seus membros */ \n"
            + "listu        /* lista todos os usuarios*/ \n"
            + "listg        /* lista os grupos em que voce esta */\n"
            ;
              
}
