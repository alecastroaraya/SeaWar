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
public class IniciarCommand implements ICommand{
    
    public void iniciar(){
        System.out.println("Iniciando servidor...");
    }

    @Override
    public void execute(String texto, InterfazCliente vista) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void execute() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void execute(String texto) {
        this.iniciar();
    }
    
}
