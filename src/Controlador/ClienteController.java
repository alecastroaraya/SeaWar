/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.ChatCommand;
import Modelo.Cliente;
import Modelo.CommandManager;
import Modelo.SalirCommand;
import Modelo.Servidor;
import Vista.InterfazCliente;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * @author aleja
 */
public class ClienteController implements ActionListener, MouseListener {
    
    private InterfazCliente vista;
    private Cliente modelo;
    private CommandManager cmdMenu;

    public ClienteController(InterfazCliente vista, Cliente modelo, CommandManager cmdMenu) {
        this.vista = vista;
        this.modelo = modelo;
        this.cmdMenu = cmdMenu;
        _init_();
    }

    private void _init_() {
        vista.getTxfConsola().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String texto = vista.getTxfConsola().getText();
        vista.getTxaConsola().append(">> " + texto + '\n');
        String command = modelo.getCommand(texto);
        String parameter = texto;
        vista.getTxfConsola().setText("");
        if (cmdMenu.getStringsComandos().contains(command))
            cmdMenu.runCommand(command,parameter);
        else
            vista.getTxaConsola().append("Error: comando inválido." + '\n');
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mousePressed(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
