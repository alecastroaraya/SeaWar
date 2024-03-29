/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progra3;

import Controlador.ClienteController;
import Vista.*;
import Modelo.*;
/**
 *
 * @author aleja
 */
public class Progra3 {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        // TODO code application logic here
        
        CommandManager cmdMenu = new CommandManager();
        cmdMenu.getStringsComandos().add("salir");
        cmdMenu.getStringsComandos().add("chat");
        cmdMenu.getStringsComandos().add("iniciar");
        cmdMenu.getStringsComandos().add("abrirservidor");
        cmdMenu.getStringsComandos().add("conectar");
        cmdMenu.getStringsComandos().add("agregar");
        cmdMenu.getStringsComandos().add("lista");
        cmdMenu.getStringsComandos().add("seleccionar");
        cmdMenu.getStringsComandos().add("listo");
        cmdMenu.getStringsComandos().add("atacar");
        cmdMenu.setCommand("salir", new SalirCommand());
        try{
        InterfazCliente pantalla = new InterfazCliente();
        Cliente cliente = new Cliente(pantalla);
        cmdMenu.setCommand("conectar", new ConectarCommand(cliente));
        cmdMenu.setCommand("chat", new ChatCommand(pantalla));
        cmdMenu.setCommand("agregar", new AgregarCommand(pantalla));
        cmdMenu.setCommand("iniciar", new IniciarCommand());
        cmdMenu.setCommand("abrirservidor", new AbrirServidorCommand(pantalla));
        cmdMenu.setCommand("lista", new ListaCommand(pantalla));
        cmdMenu.setCommand("seleccionar", new SeleccionarCommand(pantalla));
        cmdMenu.setCommand("listo", new ListoCommand(pantalla));
        cmdMenu.setCommand("atacar", new AtacarCommand(pantalla));
        ClienteController controlador = new ClienteController(pantalla,cliente,cmdMenu);
        pantalla.getLblVidaLuchador1().setVisible(false);
        pantalla.getLblNumCasillasLuchador1().setVisible(false);
        pantalla.getLblVidaLuchador2().setVisible(false);
        pantalla.getLblNumCasillasLuchador2().setVisible(false);
        pantalla.getLblVidaLuchador3().setVisible(false);
        pantalla.getLblNumCasillasLuchador3().setVisible(false);
        pantalla.setVisible(true); 
        pantalla.setTitle("War on the Seas - Nombre: No dado todavía");
        }
        catch(Exception e){
            
        }
        
    }
    
}
