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
public class ChatCommand implements ICommand{
    private String msg;
    private InterfazCliente vista;
    
    public ChatCommand(InterfazCliente vista) {
        this.vista = vista;
    }

    public ChatCommand(String msg, InterfazCliente vista) {
        this.msg = msg;
        this.vista = vista;
    }
    
    public void enviarMensaje(){
        vista.getTxaChat().append(msg + "\n");
    }
    
    public void enviarMensaje(String msg) throws IOException{
        vista.refCliente.hiloCliente.writer.writeInt(2);     // Se envia al servidor la accion de enviar un mensaje por chat y se envia el mensaje
        vista.refCliente.hiloCliente.writer.writeUTF(msg);
        //vista.getTxaChat().append(msg + "\n");
    }

    public void execute() {
        this.enviarMensaje();
    }
    
    @Override
    public void execute(String msg, InterfazCliente vista) {
        this.enviarMensaje();
    }

    @Override
    public void execute(String texto) {
        String msg = this.vista.getRefCliente().getChatMsg(texto);
        try {
            this.enviarMensaje(msg);
        } catch (IOException ex) {
            Logger.getLogger(ChatCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
