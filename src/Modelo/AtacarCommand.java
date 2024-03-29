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
public class AtacarCommand implements ICommand{
    private InterfazCliente clientePantalla;

    public AtacarCommand(InterfazCliente clientePantalla) {
        this.clientePantalla = clientePantalla;
    }
    
    public void atacar() throws IOException{
        clientePantalla.refCliente.hiloCliente.writer.writeInt(6);
        clientePantalla.refCliente.hiloCliente.writer.writeUTF("Fuck this shit nibba");
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
            this.atacar();
        } catch (IOException ex) {
            Logger.getLogger(AtacarCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
