/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Vista.InterfazCliente;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author aleja
 */
public class ListaCommand implements ICommand, Serializable{
    private InterfazCliente clientePantalla;
    private ArrayList<Luchador> listaLuchadores;

    public ListaCommand(InterfazCliente clientePantalla) {
        this.clientePantalla = clientePantalla;
        this.listaLuchadores = (ArrayList<Luchador>)FileManager.readObject("src/Archivos/listaluchadores.dat");
    }
    
    public void mostrarLista(ArrayList<Luchador> listaLuchadores){
        clientePantalla.getTxaConsola().append("---------------------------------------------------------------------------------------------------------------------"
                + "-------------------------------------------------------------\n");
        clientePantalla.getTxaConsola().append("\tMostrando lista de luchadores seleccionables. Escriba el numero de luchador que desea seleccionar.\n\n");
        int numLuchador = 1;
        for (int i = 0; i < listaLuchadores.size(); i++){
            clientePantalla.getTxaConsola().append(numLuchador + ". " + "| Nombre: " + listaLuchadores.get(i).getNombre() + " | Ataque: " + listaLuchadores.get(i).getAtaques().get(i).getNombre()
            + " | Direccion de imagen: " + listaLuchadores.get(i).getImagenNombre() + '\n');
            
        }
        clientePantalla.getTxaConsola().append("\n\t\t\tFin de la lista de luchadores.\n");
        clientePantalla.getTxaConsola().append("----------------------------------------------------------------------------------------------------------------------"
                + "-------------------------------------------------------------\n");
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
        ArrayList<Luchador> lista = (ArrayList<Luchador>)FileManager.readObject("src/Archivos/listaluchadores.dat");
        this.mostrarLista(lista);
    }
}
