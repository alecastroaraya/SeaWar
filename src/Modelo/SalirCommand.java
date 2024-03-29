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
public class SalirCommand implements ICommand{
    
    public void salirse(){
        System.exit(0);
    }

    public void execute() {
        this.salirse();
    }

    @Override
    public void execute(String texto, InterfazCliente vista) {
        this.salirse();
    }
    
    public void execute(String texto) {
        this.salirse();
    }
    
}
