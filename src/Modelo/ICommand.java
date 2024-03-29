/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Vista.InterfazCliente;

/**
 *
 * @author aleja
 */
public interface ICommand {
    
    void execute(String texto, InterfazCliente vista);
    void execute();
    void execute(String texto);
}
