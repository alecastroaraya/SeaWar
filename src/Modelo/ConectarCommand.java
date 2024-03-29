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
public class ConectarCommand implements ICommand{
    private Cliente jugador;

    public ConectarCommand(Cliente jugador) {
        this.jugador = jugador;
    }
    

    @Override
    public void execute(String texto, InterfazCliente vista) {
        if (this.jugador.getHiloCliente().isConectado()){
            this.jugador.getRefPantalla().getTxaConsola().append("Error: Ya está conectado.\n");
            return;
        }
        this.jugador.conectar();
        this.jugador.getHiloCliente().setConectado(true);
    }

    @Override
    public void execute() {
        this.jugador.conectar();
    }

    @Override
    public void execute(String texto) {
        String nombre = this.jugador.getSecondParameter(texto);
        this.jugador.conectar(nombre);
        if (this.jugador.getHiloCliente() == null){
            this.jugador.getRefPantalla().getTxaConsola().append("Error: No se pudo conectar al servidor porque ya se llegó al límite de jugadores.\n");
            return;
        }
        this.jugador.getHiloCliente().setConectado(true);
    }
    
    
}
