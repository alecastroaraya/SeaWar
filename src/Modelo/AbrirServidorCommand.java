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
public class AbrirServidorCommand implements ICommand{
    private InterfazCliente clientePantalla;

    public AbrirServidorCommand(InterfazCliente clientePantalla) {
        this.clientePantalla = clientePantalla;
    }
    
    public void abrirServer(InterfazCliente clientePantalla){
        Servidor srv = new Servidor(clientePantalla);
        srv.start();
        clientePantalla.getTxaConsola().append("El servidor ha sido abierto." + '\n');
    }
    
    public void abrirServer(InterfazCliente clientePantalla, int limiteMaximo){
        Servidor srv = new Servidor(clientePantalla, limiteMaximo);
        srv.start();
        clientePantalla.getTxaConsola().append("El servidor ha sido abierto." + '\n');
    }

    @Override
    public void execute(String texto, InterfazCliente vista) {
        this.abrirServer(clientePantalla);
    }

    @Override
    public void execute() {
        this.abrirServer(clientePantalla);
    }

    @Override
    public void execute(String texto) {
        String stringCantidad;
        int cantidadJugadores = 0;
        stringCantidad = clientePantalla.getRefCliente().getSecondParameter(texto);
            
        try{
            cantidadJugadores = Integer.parseInt(stringCantidad);
            
        } catch(NumberFormatException e){
            clientePantalla.getTxaConsola().append("Error: No se pudo empezar el servidor porque la cantidad máxima de jugadores dada es inválida.\n");
            return;
        }
        
        if (cantidadJugadores < 2){
            clientePantalla.getTxaConsola().append("No se pudo empezar el servidor porque el número mínimo de jugadores es al menos 2.\n");
            return;
        }
            
        
        else if (cantidadJugadores > 6){
            clientePantalla.getTxaConsola().append("No se pudo empezar el servidor porque el número máximo de jugadores es 6.\n");
            return;
        }
        
        int limiteMax;
        limiteMax = Integer.parseInt(stringCantidad);
        this.abrirServer(clientePantalla,limiteMax);
    }
    

}
