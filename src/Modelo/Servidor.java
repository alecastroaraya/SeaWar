/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.DataOutput;
import static java.lang.Thread.sleep;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import Modelo.ThreadCliente;
import Vista.InterfazCliente;
import Vista.PantallaServidor;
import java.io.IOException;
import java.io.Serializable;
import javax.swing.JOptionPane;
import java.lang.*;
import java.util.Collections;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Servidor extends Thread implements Serializable{
    
    private InterfazCliente adminCliente;
    public ArrayList<ThreadServidor> conexiones;            // Las conexiones de los jugadores de la partida al servidor
    private boolean running = true;
    transient private ServerSocket srv;
    private int turno = 0;                                  // Numero de turno de la partida
    private int limiteMax;                                  // Limite maximo de jugadores
    private boolean partidaIniciada = false;
    private boolean maximoAlcanzado = false;                // Si se ha o no alcanzado el limite maximo de jugadores de la partida
    public boolean flagCargado = false;
    public Servidor partidaGuardada;
    private boolean turnosStarted = false;
    private ArrayList<String> nombreOrder;

    public Servidor(InterfazCliente clientePantalla) {
        conexiones = new ArrayList<ThreadServidor>();
        adminCliente = clientePantalla;
        nombreOrder = new ArrayList<String>();
    }
    
    public Servidor(InterfazCliente clientePantalla, int limiteMaximo) {
        conexiones = new ArrayList<ThreadServidor>();
        adminCliente = clientePantalla;
        limiteMax = limiteMaximo;
        nombreOrder = new ArrayList<String>();
    }

    public void iniciarPartida() throws IOException {          // Se empieza la partida
        this.partidaIniciada = true;
        this.signalIniciarPartida();
    }
    
    
    
    public void guardarPartida() throws IOException {          // Para guardar la partida actual con serializable
        
        for (int i = 0; i < conexiones.size(); i++) {
            ThreadServidor current = conexiones.get(i);
            current.writer.writeInt(5);
        }
    }
    
    public void signalIniciarPartida() throws IOException{
        for (int i = 0; i < conexiones.size(); i++) {
            ThreadServidor current = conexiones.get(i);
            current.writer.writeInt(4);
        }
    }

    public boolean isTurnosStarted() {
        return turnosStarted;
    }

    public void setTurnosStarted(boolean turnosStarted) {
        this.turnosStarted = turnosStarted;
    }
    
    
    
    public void siguienteTurno() throws IOException{
        turno = turno + 1;
        
        String nextTurno = conexiones.get(turno).nombre;
        this.enviarMensaje("Ahora es el turno del jugador " + nextTurno + ".");
        
        for (int i = 0; i < conexiones.size(); i++){
            System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
            ThreadServidor current = conexiones.get(i);
            current.writer.writeInt(8);
            current.writer.writeUTF(nextTurno);
        }
        
        turno = 0;
    }

    public ArrayList<String> getNombreOrder() {
        return nombreOrder;
    }

    public void setNombreOrder(ArrayList<String> nombreOrder) {
        this.nombreOrder = nombreOrder;
    }
    
    public void proximoTurno(String turnoActual) throws IOException{
        String nombreTurno = "";
        
        for (int i = 0; i < nombreOrder.size(); i++) {
            if (turnoActual.contains(nombreOrder.get(i))){
                if (i + 1 >= nombreOrder.size()){
                    nombreTurno = nombreOrder.get(0);
                    break;
                }
                else
                    nombreTurno = nombreOrder.get(i+1);
            }
        }
        
        for (int i = 0; i < conexiones.size(); i++) {
            ThreadServidor current = conexiones.get(i);
            current.writer.writeInt(8);
            current.writer.writeUTF(nombreTurno);
        }
    }
        
    public void enviarTurnoInicial() throws IOException{            // Manda el turno inicial
        for (int i = 0; i < conexiones.size(); i++){
            nombreOrder.add(conexiones.get(i).nombre);
        }
        this.enviarMensaje("" + nombreOrder);
        Collections.shuffle(nombreOrder);
        this.enviarMensaje("El orden aleatorio de la partida será: ");
        for (int i = 0; i < conexiones.size(); i++){
            this.enviarMensaje(i+1 + ". " + nombreOrder.get(i));
        }
        
        for (int i = 0; i < conexiones.size(); i++){
            ThreadServidor current = conexiones.get(i);
            current.writer.writeInt(8);
            current.writer.writeUTF(nombreOrder.get(0));
        }
        this.turnosStarted = true;
        
        return;
    }

    public void stopserver(){
        running = false;
    }
    
    public String getFirstTurn() throws IOException{
        Random randomNum = new Random();
        turno = randomNum.nextInt(this.conexiones.size());
        
        for (int i = 0; i < conexiones.size(); i++){
            ThreadServidor current = conexiones.get(i);
            current.writer.writeInt(8);
            current.writer.writeUTF(conexiones.get(turno).nombre);
        }
        return conexiones.get(turno).nombre;
    }
    
    public String getNextTurno(){               // Retorna el nombre del jugador que va siguiente
        if ( ++turno >= conexiones.size())
            turno = 0;
        
        return conexiones.get(turno).nombre;
    }
    
    public String getTurno(){                   // Retorna el nombre del jugador cuyo turno es actualmente
        return conexiones.get(turno).nombre;
    }
    
    public String numTurnoInicial(){
        Random randomNum = new Random();
        turno = randomNum.nextInt(this.conexiones.size());
        String nombreTurno = this.conexiones.get(turno).getNombre();
        return nombreTurno;
    }

    public InterfazCliente getAdminCliente() {
        return adminCliente;
    }

    public void setAdminCliente(InterfazCliente adminCliente) {
        this.adminCliente = adminCliente;
    }
    
    public void enviarMensaje(String msg) throws IOException{
        for (int i = 0; i < conexiones.size(); i++) {
            ThreadServidor current = conexiones.get(i);
            current.writer.writeInt(6);
            current.writer.writeUTF(msg);
        }
    }
    
    public void run(){
        int contadorDeConexiones = 0;
        
        this.getAdminCliente().getTxaConsola().append("El límite de jugadores de la partida es: " + this.getLimiteMax() + '\n');
        try{
            srv = new ServerSocket(35577);
            while (running){
                if (contadorDeConexiones <= this.getLimiteMax() && this.isMaximoAlcanzado() == false){          // Mientras no se ha llegado al límmite maximo, se aceptan conexiones nuevas
                   
                   this.enviarMensaje("-Esperando más jugadores...");
                   this.enviarMensaje("-El límite máximo de jugadores para esta partida es " + this.getLimiteMax() + ". Cantidad actual de jugadores: " + contadorDeConexiones);
                    
                }
                    
                Socket nuevaConexion = srv.accept();
                if (!partidaIniciada){              // Mientras no se empezado la partida, se aceptan conexiones nuevas
                    contadorDeConexiones++;

                    if (contadorDeConexiones > this.getLimiteMax()){
                        this.enviarMensaje("-Conexión denegada: Límite máximo de jugadores alcanzado.");
                        
                        
                    }
                    
                    else if (contadorDeConexiones <= this.getLimiteMax()){
                        
                        
                        
                        // nuevo thread
                        ThreadServidor newThread = new ThreadServidor(nuevaConexion, this);
                        conexiones.add(newThread);
                        newThread.start();
                        this.enviarMensaje("-Conexión " + contadorDeConexiones + " aceptada.");
                        if (conexiones.size() != this.getLimiteMax()){
                            this.enviarMensaje("Falta(n) " + (this.getLimiteMax() - this.conexiones.size()) + " jugador(es) por conectarse.");
                        }
                        
                    }
                        
                        if (contadorDeConexiones == this.getLimiteMax()){           // Al llegar al límite máximo de jugadores, se para de aceptar nuevas conexiones y se empiza la partida
                            
                        this.enviarMensaje("-El límite máximo de jugadores para esta partida es " + limiteMax + ". Cantidad actual de jugadores: " + contadorDeConexiones);
                        this.enviarMensaje("-Cantidad máxima de jugadores alcanzada. No se permitirán más conexiones.");
                        
                            //refPantalla.addMessage("-Iniciando partida...");
                            this.setMaximoAlcanzado(true);
                            //this.partidaIniciada = true;
                            srv.close();
                            //this.signalIniciarPartida();

                        }
                    
                }
                else{
                    // OutputStream socket para poder hacer un writer
                    this.enviarMensaje("-Conexión denegada: partida iniciada");
                    
                }
                
                if (partidaIniciada){
                    System.out.println("hola");
                }
                
            }
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    public boolean isFlagCargado() {
        return flagCargado;
    }

    public void setFlagCargado(boolean flagCargado) {
        this.flagCargado = flagCargado;
    }

    public ArrayList<ThreadServidor> getConexiones() {
        return conexiones;
    }

    public void setConexiones(ArrayList<ThreadServidor> conexiones) {
        this.conexiones = conexiones;
    }
    
    public int getLimiteMax() {
        return limiteMax;
    }

    public void setLimiteMax(int limiteMax) {
        this.limiteMax = limiteMax;
    }

    public boolean isMaximoAlcanzado() {
        return maximoAlcanzado;
    }

    public void setMaximoAlcanzado(boolean maximoAlcanzado) {
        this.maximoAlcanzado = maximoAlcanzado;
    }

    public boolean isPartidaIniciada() {
        return partidaIniciada;
    }

    public void setPartidaIniciada(boolean partidaIniciada) {
        this.partidaIniciada = partidaIniciada;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public void setTurno(int turno) {
        this.turno = turno;
    }
    
    public int getNumTurno(){
        return turno;
    }
    
}
