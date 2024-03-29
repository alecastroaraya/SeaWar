/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Vista.InterfazCliente;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author aleja
 */
public class ListoCommand implements ICommand{
    private InterfazCliente clientePantalla;

    public ListoCommand(InterfazCliente clientePantalla) {
        this.clientePantalla = clientePantalla;
    }
    
    public void clienteListo(ThreadCliente jugador) throws IOException{
        if (jugador.getPeleadores().size() != 3){
            this.clientePantalla.getTxaConsola().append("Error: No puede estar listo todavía porque no ha seleccionado sus 3 luchadores.\n");
            return;
        }
        jugador.setListo(true);
        String nombre = this.clientePantalla.getRefCliente().getHiloCliente().getNombre();
        clientePantalla.refCliente.hiloCliente.writer.writeInt(6);
        clientePantalla.refCliente.hiloCliente.writer.writeUTF("El jugador " + nombre + " está listo para jugar.");
        clientePantalla.refCliente.hiloCliente.writer.writeInt(7);
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
        try {
            this.clienteListo(clientePantalla.getRefCliente().getHiloCliente());
        } catch (IOException ex) {
            Logger.getLogger(ListoCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
