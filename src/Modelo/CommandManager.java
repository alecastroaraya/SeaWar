/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author aleja
 */
public class CommandManager {
    private HashMap<String, ICommand> menuComandos = new HashMap<String, ICommand>();
    private ArrayList<String> stringsComandos = new ArrayList<String>();
    
    public void setCommand(String operation, ICommand command){
        menuComandos.put(operation, command);
    }
    
    public void runCommand(String command, String operation){
        menuComandos.get(command).execute(operation);
    }
    
    public ICommand getCommand(String command){
        return menuComandos.get(command);
    }
    
    public CommandManager getInstance(){
        return this;
    }

    public ArrayList<String> getStringsComandos() {
        return stringsComandos;
    }

    public void setStringsComandos(ArrayList<String> stringsComandos) {
        this.stringsComandos = stringsComandos;
    }
    
    
    
}
